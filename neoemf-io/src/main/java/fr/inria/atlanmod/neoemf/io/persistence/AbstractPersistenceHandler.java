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
import fr.inria.atlanmod.neoemf.io.structure.Element;
import fr.inria.atlanmod.neoemf.io.structure.Identifier;
import fr.inria.atlanmod.neoemf.io.structure.MetaClass;
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
 * An {@link PersistenceHandler} that persists data in a {@link PersistenceBackend}, based on received events.
 *
 * @param <P> the type of the {@link PersistenceBackend} targeted by this handler.
 */
public abstract class AbstractPersistenceHandler<P extends PersistenceBackend> implements PersistenceHandler {

    /**
     * The default cache size.
     * <p>
     * It is calculated according to the maximum memory dedicated to the JVM.
     */
    protected static final long DEFAULT_CACHE_SIZE = adaptFromMemory(2000);

    /**
     * The default operation between commits.
     * <p>
     * It is calculated according to the maximum memory dedicated to the JVM.
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
     * <p>
     * In case of conflict detection only.
     */
    private final HashMultimap<String, UnlinkedElement> unlinkedElementsMap;

    /**
     * In-memory cache that holds conflicted {@link Id}s, identified by their literal representation.
     * <p>
     * In case of conflict detection only.
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
     * <p>
     * <b>Note:</b> The formulas can be improved, for sure.
     *
     * @param value the value to adapt
     *
     * @return the adapted value
     *
     * @see #DEFAULT_CACHE_SIZE
     * @see #OPS_BETWEEN_COMMITS_DEFAULT
     */
    private static long adaptFromMemory(int value) {
        double maxMemoryGB = (double) Runtime.getRuntime().maxMemory() / 1000 / 1000 / 1000;

        double factor = maxMemoryGB;
        if (maxMemoryGB > 1) {
            factor *= 2;
        }
        long computedValue = Math.round(value * factor);

        // Return 1 in the worst case to avoid division by 0.
        return Math.max(computedValue, 1);
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
    public void handleStartDocument() {
        // Do nothing
    }

    @Override
    public void handleStartElement(final Element element) {
        Id id = createElement(element);
        Id metaClassId = getOrCreateMetaClass(element.metaClass());

        setMetaClass(id, metaClassId);

        elementIdStack.addLast(id);
    }

    @Override
    public void handleAttribute(final Attribute attribute) {
        Id id;
        if (isNull(attribute.id())) {
            id = elementIdStack.getLast();
        }
        else {
            id = getOrCreateId(attribute.id());
        }

        addAttribute(id, attribute.name(), attribute.index(), attribute.many(), attribute.value());
        incrementAndCommit();
    }

    @Override
    public void handleReference(final Reference reference) {
        Id id;
        if (isNull(reference.id())) {
            id = elementIdStack.getLast();
        }
        else {
            id = getOrCreateId(reference.id());
        }

        Id idReference = getOrCreateId(reference.idReference());

        try {
            addReference(id, reference.name(), reference.index(), reference.many(), reference.containment(), idReference);
            incrementAndCommit();
        }
        catch (NoSuchElementException e) {
            // Referenced element does not exist : we save it in a cache
            unlinkedElementsMap.put(reference.idReference().value(), new UnlinkedElement(id, reference));
        }
    }

    @Override
    public void handleEndElement() {
        elementIdStack.removeLast();
    }

    @Override
    public void handleEndDocument() {
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
    public void handleCharacters(String characters) {
        // Do nothing
    }

    /**
     * Creates an element from the given {@code element} with the given {@code id}.
     * <p>
     * If {@code id} is {@code null}, it is calculated by the {@link #getId(String)} method.
     *
     * @param element the information about the new element
     * @param id         the identifier of the element
     *
     * @return the given {@code id}
     *
     * @throws NullPointerException if any of the parameters is {@code null}
     */
    protected Id createElement(@Nonnull final Element element, @Nonnull final Id id) {
        checkNotNull(element);
        checkNotNull(id);

        addElement(id, element.ns().uri(), element.className(), element.root());
        incrementAndCommit();

        tryLink(element.id().value(), id);

        return id;
    }

    /**
     * Creates an element from the given {@code element}, and creates its {@link Id}.
     *
     * @param element the information about the new element
     *
     * @return the {@link Id} of the created element
     *
     * @throws NullPointerException if the {@code element} is {@code null} or if it does not have an {@link Id}
     */
    private Id createElement(@Nonnull final Element element) {
        checkNotNull(element.id());

        String idValue = element.id().value();

        Id id = createId(element.id());

        boolean conflict = false;
        do {
            try {
                createElement(element, id);
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
     * Creates a metaclass form the given {@code metaClass} and returns its {@link Id}.
     *
     * @param metaClass the meta classifier
     *
     * @return the {@link Id} of the created metaclass
     *
     * @throws NullPointerException if the {@code metaClass} is {@code null}
     */
    protected Id getOrCreateMetaClass(@Nonnull final MetaClass metaClass) {
        String idValue = metaClass.ns().uri() + ':' + metaClass.name();

        Id id = metaclassIdCache.get(idValue, key -> {

            // If metaclass doesn't already exist, we create it
            Id newId = createId(Identifier.generated(idValue));

            boolean conflict = false;
            do {
                try {
                    addElement(newId, metaClass.ns().uri(), metaClass.name(), false);
                }
                catch (AlreadyExistingIdException e) {
                    newId = createId(Identifier.generated(newId.toString()));
                    conflict = true;
                }
            }
            while (conflict);

            return newId;
        });

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
        Id id = conflictElementIdCache.getIfPresent(identifier.value());
        if (isNull(id)) {
            id = elementIdCache.get(identifier.value(), value -> createId(identifier));
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
        String idValue = identifier.value();

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
            this(id, reference.name(), reference.index(), reference.many(), reference.containment());
        }
    }
}
