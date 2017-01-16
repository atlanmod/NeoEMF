/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.persistence;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.HashMultimap;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.io.AlreadyExistingIdException;
import fr.inria.atlanmod.neoemf.io.hash.HasherFactory;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Identifier;
import fr.inria.atlanmod.neoemf.io.structure.MetaClassifier;
import fr.inria.atlanmod.neoemf.io.structure.Reference;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * A {@link PersistenceHandler} that persists data in a {@link PersistenceBackend}, based on received events.
 *
 * @param <P> the type of the {@link PersistenceBackend} targeted by this handler.
 */
public abstract class AbstractPersistenceHandler<P extends PersistenceBackend> implements PersistenceHandler {

    /**
     * The default cache size.
     *
     * @note It is calculated according to the maximum memory dedicated to the JVM.
     */
    protected static final long DEFAULT_CACHE_SIZE = adaptFromMemory(2000);

    /**
     * The default operation between commits.
     *
     * @note It is calculated according to the maximum memory dedicated to the JVM.
     */
    private static final long OPS_BETWEEN_COMMITS_DEFAULT = adaptFromMemory(50000);

    /**
     * The persistence back-end where to store data.
     */
    private final P backend;

    /**
     * Queue holding the current {@link Id} chain (current element and its parent).
     * <p>
     * It is updated after each addition/deletion of an element.
     */
    private final Deque<Id> elementIdStack;

    /**
     * In-memory cache that holds the recently processed {@link Id}s, identified by their literal representation.
     */
    private final Cache<String, Id> elementIdCache;

    /**
     * In-memory cache that holds the registered metaclasses, identified by a {@link String} formatted as follow:
     * {@code "uri:localName"}.
     */
    private final Cache<String, Id> metaclassIdCache;

    /**
     * Map holding the unlinked elements, waiting until their reference is created.
     *
     * @note In case of conflict detection only.
     */
    private final HashMultimap<String, UnlinkedElement> unlinkedElementsMap;

    /**
     * In-memory cache that holds conflicted {@link Id}s, identified by their literal representation.
     *
     * @note In case of conflict detection only.
     */
    private final Cache<String, Id> conflictElementIdCache;

    /**
     * Current number of modifications modulo {@link #OPS_BETWEEN_COMMITS_DEFAULT}.
     * Used for automatically saves modifications as calls are made.
     */
    private long opCount;

    /**
     * Constructs a new {@code AbstractPersistenceHandler} on top of the {@code backend}.
     *
     * @param backend the persistence back-end where to store data
     */
    protected AbstractPersistenceHandler(P backend) {
        this.backend = backend;
        this.opCount = 0;
        this.elementIdStack = new ArrayDeque<>();

        this.elementIdCache = Caffeine.newBuilder().maximumSize(DEFAULT_CACHE_SIZE).build();
        this.metaclassIdCache = Caffeine.newBuilder().build();

        this.unlinkedElementsMap = HashMultimap.create();
        this.conflictElementIdCache = Caffeine.newBuilder().build();

        NeoLogger.info("{0} created", getClass().getSimpleName());
        NeoLogger.info("{0} chunk = {1}", getClass().getSimpleName(), OPS_BETWEEN_COMMITS_DEFAULT);
    }

    /**
     * Adapts the given {@code value} according to the maximum memory dedicated to the JVM.
     *
     * @param value the value to adapt
     *
     * @return the adapted value
     *
     * @note The formulas can be improved, for sure.
     * @see #DEFAULT_CACHE_SIZE
     * @see #OPS_BETWEEN_COMMITS_DEFAULT
     */
    private static long adaptFromMemory(int value) {
        long maxMemoryGB = Runtime.getRuntime().maxMemory() / 1000 / 1000 / 1000;

        long factor = maxMemoryGB;
        if (maxMemoryGB > 1) {
            factor *= 2;
        }

        return value * factor;
    }

    /**
     * Returns the {@code backend} where to store data.
     *
     * @return the persistence back-end
     */
    protected P getPersistenceBackend() {
        return backend;
    }

    /**
     * Returns the {@link Id} of the given {@code reference}.
     *
     * @param reference the reference
     *
     * @return the {@link Id}
     */
    protected abstract Id getId(String reference);

    /**
     * Adds a new element.
     *
     * @param id    the identifier of the new element
     * @param nsUri the URI of the new element
     * @param name  the name of the new element
     * @param root  {@code true} if the new element is a root node
     */
    protected abstract void addElement(Id id, String nsUri, String name, boolean root);

    /**
     * Adds a new attribute to the element identified by the given {@code id}.
     *
     * @param id    the identifier of the element
     * @param name  the name of the attribute
     * @param index the index of the attribute if it's a multi-valued attribute
     * @param many  {@code true} if the attribute is multi-valued
     * @param value the value of the attribute
     */
    protected abstract void addAttribute(Id id, String name, int index, boolean many, Object value);

    /**
     * Adds a new reference to the element identified by the given {@code id}.
     *
     * @param id          the identifier of the element
     * @param name        the name of the reference
     * @param index       the index of the reference if it's a multi-valued reference
     * @param many        {@code true} if the reference is multi-valued
     * @param containment {@code true} if the reference is a containment
     * @param idReference the identifier of the referenced element
     */
    protected abstract void addReference(Id id, String name, int index, boolean many, boolean containment, Id idReference);

    /**
     * Defines the metaclass to the element identified by the given {@code id}.
     *
     * @param id          the identifier of the element
     * @param metaClassId the identifier of the metaclass
     */
    protected abstract void setMetaClass(Id id, Id metaClassId);

    @Override
    public void processStartDocument() {
        // Do nothing
    }

    @Override
    public void processStartElement(final Classifier classifier) {
        Id id = createElement(classifier);
        Id metaClassId = getOrCreateMetaClass(classifier.getMetaClassifier());

        setMetaClass(id, metaClassId);

        elementIdStack.addLast(id);
    }

    @Override
    public void processAttribute(final Attribute attribute) {
        Id id;
        if (isNull(attribute.getId())) {
            id = elementIdStack.getLast();
        }
        else {
            id = getOrCreateId(attribute.getId());
        }

        addAttribute(id,
                attribute.getLocalName(),
                attribute.getIndex(),
                attribute.isMany(),
                attribute.getValue());

        incrementAndCommit();
    }

    @Override
    public void processReference(final Reference reference) {
        Id id;
        if (isNull(reference.getId())) {
            id = elementIdStack.getLast();
        }
        else {
            id = getOrCreateId(reference.getId());
        }

        Id idReference = getOrCreateId(reference.getIdReference());

        try {
            addReference(id,
                    reference.getLocalName(),
                    reference.getIndex(),
                    reference.isMany(),
                    reference.isContainment(),
                    idReference);

            incrementAndCommit();
        }
        catch (NoSuchElementException e) {
            // Referenced element does not exist : we save it in a cache
            unlinkedElementsMap.put(reference.getIdReference().getValue(), new UnlinkedElement(id, reference));
        }
    }

    @Override
    public void processEndElement() {
        elementIdStack.removeLast();
    }

    @Override
    public void processEndDocument() {
        long unlinkedNumber = unlinkedElementsMap.size();
        if (unlinkedNumber > 0) {
            NeoLogger.warn("Some elements have not been linked ({0})", unlinkedNumber);
            for (String e : unlinkedElementsMap.asMap().keySet()) {
                NeoLogger.warn(" > " + e);
            }
            unlinkedElementsMap.clear();
        }

        long conflictedId = conflictElementIdCache.estimatedSize();
        if (conflictedId > 0) {
            NeoLogger.info("{0} key conflicts", conflictElementIdCache.estimatedSize());
            conflictElementIdCache.invalidateAll();
        }

        backend.save();
    }

    @Override
    public void processCharacters(String characters) {
        // Do nothing
    }

    /**
     * Creates an element from the given {@code classifier} with the given {@code id}.
     * <p>
     * If {@code id} is {@code null}, it is calculated by the {@link #getId(String)} method.
     *
     * @param classifier the information about the new element
     * @param id         the identifier of the element
     *
     * @return the given {@code id}
     *
     * @throws NullPointerException if any of the parameters is {@code null}
     */
    protected Id createElement(@Nonnull final Classifier classifier, @Nonnull final Id id) {
        checkNotNull(classifier);
        checkNotNull(id);

        addElement(id,
                classifier.getNamespace().getUri(),
                classifier.getClassName(),
                classifier.isRoot());

        incrementAndCommit();

        tryLink(classifier.getId().getValue(), id);

        return id;
    }

    /**
     * Creates an element from the given {@code classifier}, and creates its {@link Id}.
     *
     * @param classifier the information about the new element
     *
     * @return the {@link Id} of the created element
     *
     * @throws NullPointerException if the {@code classifier} is {@code null} or if it does not have an {@link Id}
     */
    private Id createElement(@Nonnull final Classifier classifier) {
        checkNotNull(classifier.getId());

        String idValue = classifier.getId().getValue();

        Id id = createId(classifier.getId());
        boolean conflict = false;

        do {
            try {
                createElement(classifier, id);
                elementIdCache.put(idValue, id);
            }
            catch (AlreadyExistingIdException e) {
                // Id already exists in the back-end : try another
                id = createId(Identifier.generated(id.toString()));
                conflictElementIdCache.put(idValue, id);
                conflict = true;
            }
        }
        while (conflict);

        return id;
    }

    /**
     * Creates a metaclass form the given {@code metaClassifier} and returns its {@link Id}.
     *
     * @param metaClassifier the meta classifier
     *
     * @return the {@link Id} of the created metaclass
     *
     * @throws NullPointerException if the {@code metaClassifier} is {@code null}
     */
    protected Id getOrCreateMetaClass(@Nonnull final MetaClassifier metaClassifier) {
        String idValue = metaClassifier.getNamespace().getUri() + ':' + metaClassifier.getLocalName();

        // Gets from cache
        Id id = metaclassIdCache.getIfPresent(idValue);

        // If metaclass doesn't already exist, we create it
        if (isNull(id)) {
            id = createId(Identifier.generated(idValue));
            boolean conflict = false;

            do {
                try {
                    addElement(id,
                            metaClassifier.getNamespace().getUri(),
                            metaClassifier.getLocalName(),
                            false);

                    metaclassIdCache.put(idValue, id);
                }
                catch (AlreadyExistingIdException e) {
                    id = createId(Identifier.generated(id.toString()));
                    conflict = true;
                }
            }
            while (conflict);
        }

        incrementAndCommit();

        return id;
    }

    /**
     * Returns the {@link Id} of the given {@code identifier}.
     *
     * @param identifier the representation of the {@link Id}
     *
     * @return the registered {@link Id} of the given identifier, or {@code null} if the identifier is not registered.
     */
    @Nullable
    private Id getOrCreateId(final Identifier identifier) {
        Id id = conflictElementIdCache.getIfPresent(identifier.getValue());
        if (isNull(id)) {
            id = elementIdCache.get(identifier.getValue(), value -> createId(identifier));
        }
        return id;
    }

    /**
     * Creates an {@link Id} from the given {@code identifier}.
     *
     * @param identifier the representation of the {@link Id}
     *
     * @return the {@link Id}
     */
    private Id createId(final Identifier identifier) {
        String idValue = identifier.getValue();

        // If identifier has been generated we hash it, otherwise we use the original
        if (identifier.isGenerated()) {
            idValue = HasherFactory.md5().hash(idValue).toString();
        }

        return getId(idValue);
    }

    /**
     * Tries to link elements that have not been linked at their creation.
     *
     * @param reference the reference of the targeted element
     * @param id        the identifier of the targeted element
     */
    private void tryLink(final String reference, final Id id) {
        for (UnlinkedElement e : unlinkedElementsMap.removeAll(reference)) {
            addReference(e.id, e.name, e.index, e.many, e.containment, id);
        }
    }

    /**
     * Increments the operation counter, and commit the persistence back-end if the number of operation is equals to
     * {@code OPS_BETWEEN_COMMITS_DEFAULT}.
     */
    private void incrementAndCommit() {
        opCount = (opCount + 1) % OPS_BETWEEN_COMMITS_DEFAULT;
        if (opCount == 0) {
            backend.save();
        }
    }

    /**
     * A simple representation of an element that could not be linked when it was created.
     */
    private class UnlinkedElement {

        /**
         * The identifier of this element.
         */
        public final Id id;

        /**
         * The name of the reference.
         */
        public final String name;

        /**
         * The index of the reference.
         */
        public final int index;

        /**
         * Whether the reference is multi-valued.
         */
        public final boolean many;

        /**
         * Whether the reference is a containment.
         */
        public final boolean containment;

        /**
         * Constructs a new {@code UnlinkedElement} with the given {@code id} and information about the {@link
         * Reference}.
         *
         * @param id          the identifier of the unlinked element
         * @param name        the name of the reference
         * @param index       the index of the reference
         * @param many        {@code true} if the reference is multi-valued
         * @param containment {@code true} if the reference is a containment
         */
        public UnlinkedElement(Id id, String name, int index, boolean many, boolean containment) {
            this.id = id;
            this.name = name;
            this.index = index;
            this.many = many;
            this.containment = containment;
        }

        /**
         * Constructs a new {@code UnlinkedElement} with the given {@code id} and {@code reference}.
         *
         * @param id        the identifier of the unlinked element
         * @param reference the concerned reference
         */
        public UnlinkedElement(Id id, Reference reference) {
            this(id, reference.getLocalName(), reference.getIndex(), reference.isMany(), reference.isContainment());
        }
    }
}
