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
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An handler able to persist newly created data in a {@link PersistenceBackend}.
 *
 * @param <P> the type of the {@link PersistenceBackend} targeted by this handler.
 */
public abstract class AbstractPersistenceHandler<P extends PersistenceBackend> implements PersistenceHandler {

    /**
     * The default cache size. It is calculated according to the maximum memory dedicated to the JVM.
     */
    protected static final long DEFAULT_CACHE_SIZE = adaptFromMemory(2000);

    /**
     * The default operation between commits. It is calculated according to the maximum memory dedicated to the JVM.
     */
    private static final long OPS_BETWEEN_COMMITS_DEFAULT = adaptFromMemory(50000);

    /**
     * The persistence backend where to store data.
     */
    private final P backend;

    /**
     *
     */
    private final Deque<Id> elementIdStack;

    /**
     * Cache of recently processed {@code Id}.
     */
    private final Cache<String, Id> elementIdCache;

    /**
     * Cache of registered metaclasses.
     */
    private final Cache<String, Id> metaclassIdCache;

    /**
     * Cache of unlinked elements, waiting until their reference is created.
     * <p>
     * @note In case of conflict detection only.
     */
    private final HashMultimap<String, UnlinkedElement> unlinkedElementsMap;

    /**
     * Cache of conflited {@code Id}.
     * <p>
     * @note In case of conflict detection only.
     */
    private final Cache<String, Id> conflictElementIdCache;

    private long opCount;

    /**
     * Instantiates a new {@code AbstractPersistenceHandler} with its targeted {@link PersistenceBackend}.
     *
     * @param backend the targetted persistence back-end
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
     * Adapts the given {@code value} according to the max memory.
     * <p>
     * The formulas can be improved, for sure.
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
     * Returns the targeted {@link PersistenceBackend}.
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
     * @return the {@link Id} of the given {@code reference}
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
     * @param index the index of the attribute if it's a multivalued attribute
     * @param many  {@code true} if the attribute is multivalued
     * @param value the value of the attribute
     */
    protected abstract void addAttribute(Id id, String name, int index, boolean many, Object value);

    /**
     * Adds a new reference to the element identified by the given {@code id}.
     *
     * @param id          the identifier of the element
     * @param name        the name of the reference
     * @param index       the index of the reference if it's a multivalued reference
     * @param many        {@code true} if the reference is multivalued
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
     */
    protected Id createElement(final Classifier classifier, final Id id) {
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
     * Creates an element from the given {@code classifier}.
     *
     * @param classifier the information about the new element
     *
     * @return the identifier of the newly created element
     */
    private Id createElement(final Classifier classifier) {
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
     * @return the {@link Id} of the newly created metaclass
     */
    protected Id getOrCreateMetaClass(final MetaClassifier metaClassifier) {
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
     * Get the {@link Id} of the given identifier.
     *
     * @param identifier the identifier
     *
     * @return the registered {@code Id} of the given identifier, or {@code null} if the identifier is not registered.
     */
    private Id getOrCreateId(final Identifier identifier) {
        Id id = conflictElementIdCache.getIfPresent(identifier.getValue());
        if (isNull(id)) {
            id = elementIdCache.getIfPresent(identifier.getValue());
            if (isNull(id)) {
                id = createId(identifier);
                elementIdCache.put(identifier.getValue(), id);
            }
        }
        return id;
    }

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
     * @param reference the reference of the targetted element
     * @param id        the identifier of the targetted element
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
     * An element that could not be linked at its creation.
     */
    private class UnlinkedElement {

        public final Id id;
        public final String name;
        public final int index;
        public final boolean many;
        public final boolean containment;

        public UnlinkedElement(Id id, String name, int index, boolean many, boolean containment) {
            this.id = id;
            this.name = name;
            this.index = index;
            this.many = many;
            this.containment = containment;
        }

        public UnlinkedElement(Id id, Reference reference) {
            this(id, reference.getLocalName(), reference.getIndex(), reference.isMany(), reference.isContainment());
        }
    }
}
