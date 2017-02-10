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
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;
import fr.inria.atlanmod.neoemf.io.AlreadyExistingIdException;
import fr.inria.atlanmod.neoemf.io.hash.Hasher;
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
import java.util.Objects;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An {@link PersistenceHandler} that persists data in a {@link PersistenceBackend}, based on received events.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractPersistenceHandler implements PersistenceHandler {

    /**
     * The default cache size.
     *
     * @note It is calculated according to the maximum memory dedicated to the JVM.
     */
    private static final long DEFAULT_CACHE_SIZE = adaptFromMemory(2000L);

    /**
     * The default operation between commits.
     *
     * @note It is calculated according to the maximum memory dedicated to the JVM.
     */
    private static final long DEFAULT_AUTOCOMMIT_CHUNK = adaptFromMemory(50000L);

    /**
     * The default {@link Hasher} used to create unique identifiers.
     */
    private static final Hasher DEFAULT_HASHER = HasherFactory.md5();

    /**
     * The identifier of the root element.
     */
    private final static Id ROOT_ID = StringId.of("ROOT");

    /**
     * The property key used by the root element to define its content.
     */
    private static final String ROOT_FEATURE_NAME = "eContents";

    /**
     * The feature name of the name of an element.
     */
    private static final String FEATURE_NAME = "name";

    /**
     * The persistence back-end where to store data.
     */
    protected final PersistenceBackend backend;

    /**
     * Queue holding the current {@link Id} chain (current element and its parent).
     * <p>
     * It is updated after each addition/deletion of an element.
     */
    private final Deque<Id> idsStack;

    /**
     * In-memory cache that holds the recently processed {@link Id}s, identified by their literal representation.
     */
    private final Cache<String, Id> idsCache;

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
     * Current number of modifications modulo {@link #DEFAULT_AUTOCOMMIT_CHUNK}.
     * Used for automatically saves modifications as calls are made.
     */
    private long autocommitCount;

    /**
     * Constructs a new {@code AbstractPersistenceHandler} on top of the {@code backend}.
     *
     * @param backend the persistence back-end where to store data
     */
    protected AbstractPersistenceHandler(PersistenceBackend backend) {
        this.backend = backend;
        this.autocommitCount = 0;
        this.idsStack = new ArrayDeque<>();
        this.idsCache = Caffeine.newBuilder()
                .initialCapacity((int) DEFAULT_CACHE_SIZE / 10)
                .maximumSize(DEFAULT_CACHE_SIZE)
                .build();

        this.unlinkedElementsMap = HashMultimap.create();
        this.conflictElementIdCache = Caffeine.newBuilder().build();

        NeoLogger.info("{0} created", getClass().getSimpleName());
        NeoLogger.info("{0} chunk = {1}", getClass().getSimpleName(), DEFAULT_AUTOCOMMIT_CHUNK);
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
     * @see #DEFAULT_AUTOCOMMIT_CHUNK
     */
    private static long adaptFromMemory(long value) {
        long maxMemoryGB = Runtime.getRuntime().maxMemory() / 1000 / 1000 / 1000;

        long factor = maxMemoryGB;
        if (maxMemoryGB > 1) {
            factor *= 2;
        }

        return value * factor;
    }

    @Override
    public void handleStartDocument() {
        // Create the 'ROOT' node with the default metaclass
        MetaClass metaClass = MetaClass.getDefault();

        Element rootElement = new Element(metaClass.ns(), metaClass.name());
        rootElement.id(Identifier.generated(ROOT_ID.toString()));
        rootElement.className(metaClass.name());
        rootElement.root(false);
        rootElement.metaClass(metaClass);

        createElement(rootElement, ROOT_ID);
    }

    @Override
    public void handleStartElement(Element element) {
        idsStack.addLast(createElement(element));
    }

    @Override
    public void handleAttribute(Attribute attribute) {
        Id id = Optional.ofNullable(attribute.id()).map(this::getOrCreateId).orElse(idsStack.getLast());
        addAttribute(id, attribute.name(), attribute.index(), attribute.many(), attribute.value());
        incrementAndCommit();
    }

    @Override
    public void handleReference(Reference reference) {
        Id id = Optional.ofNullable(reference.id()).map(this::getOrCreateId).orElse(idsStack.getLast());
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
        idsStack.removeLast();
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
     *
     * @param element the information about the new element
     * @param id      the identifier of the element
     *
     * @return the given {@code id}
     *
     * @throws NullPointerException if any of the parameters is {@code null}
     */
    protected Id createElement(Element element, Id id) {
        checkNotNull(element);
        checkNotNull(id);

        persist(id);

        Optional.ofNullable(element.className()).ifPresent(v -> addAttribute(id, FEATURE_NAME, -1, false, v));

        updateInstanceOf(id, element.metaClass().name(), element.metaClass().ns().uri());

        if (element.root()) {
            // Add the current element as content of the 'ROOT' node
            addReference(ROOT_ID, ROOT_FEATURE_NAME, -1, false, false, id);
        }

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
    private Id createElement(Element element) {
        checkNotNull(element.id());

        String idValue = element.id().value();

        Id id = createId(element.id());

        boolean conflict = false;
        do {
            try {
                createElement(element, id);
                idsCache.put(idValue, id);
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
     * Returns the {@link Id} of the given {@code identifier}.
     *
     * @param identifier the representation of the {@link Id}
     *
     * @return the registered {@link Id} of the given identifier, or {@code null} if the identifier is not registered.
     */
    private Id getOrCreateId(Identifier identifier) {
        Id id = conflictElementIdCache.getIfPresent(identifier.value());
        if (isNull(id)) {
            id = idsCache.get(identifier.value(), value -> createId(identifier));
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
    private Id createId(Identifier identifier) {
        String idValue = identifier.value();

        // If identifier has been generated we hash it, otherwise we use the original
        if (identifier.isGenerated()) {
            idValue = DEFAULT_HASHER.hash(idValue).toString();
        }

        return StringId.of(idValue);
    }

    /**
     * Adds a new attribute to the element identified by the given {@code id}.
     *
     * @param id    the identifier of the element
     * @param name  the name of the attribute
     * @param index the index of the attribute if it's a multi-valued attribute
     * @param many  {@code true} if the attribute is multi-valued
     * @param value the value of the attribute
     */
    protected void addAttribute(Id id, String name, int index, boolean many, Object value) {
        checkExists(id);

        FeatureKey key = FeatureKey.of(id, name);

        if (!many) {
            backend.valueFor(key, value);
        }
        else {
            if (index == PersistentStore.NO_INDEX) {
                index = backend.sizeOf(key).orElse(0);
            }

            backend.addValue(key.withPosition(index), value);
        }
    }

    /**
     * Adds a new reference to the element identified by the given {@code id}.
     *
     * @param id           the identifier of the element
     * @param name         the name of the reference
     * @param index        the index of the reference if it's a multi-valued reference
     * @param many         {@code true} if the reference is multi-valued
     * @param containment  {@code true} if the reference is a containment
     * @param referencedId the identifier of the referenced element
     */
    protected void addReference(Id id, String name, int index, boolean many, boolean containment, Id referencedId) {
        checkExists(id);
        checkExists(referencedId);

        // Update the containment reference if needed
        if (containment) {
            updateContainment(id, name, referencedId);
        }

        FeatureKey key = FeatureKey.of(id, name);

        if (index == PersistentStore.NO_INDEX) {
            index = backend.sizeOf(key).orElse(0);
        }

        // FIXME Single-valued reference are not handled, and cause exception
        backend.addReference(key.withPosition(index), referencedId);
    }

    /**
     * Updates the containment identified by its {@code name} between the {@code id} and the {@code referencedId}.
     *
     * @param id           the parent vertex
     * @param name         the name of the property identifying the reference (parent -&gt; child)
     * @param referencedId the child vertex
     */
    protected void updateContainment(Id id, String name, Id referencedId) {
        Optional<ContainerValue> container = backend.containerOf(referencedId);
        if (!container.isPresent() || !Objects.equals(container.get().id(), id)) {
            backend.containerFor(referencedId, ContainerValue.of(id, name));
        }
    }

    /**
     * Defines the metaclass to the element identified by the given {@code id}.
     *
     * @param id   the identifier of the element
     * @param name the name of the metaclass
     * @param uri  the uri of the metaclass
     */
    protected void updateInstanceOf(Id id, String name, String uri) {
        Optional<MetaclassValue> metaclass = backend.metaclassOf(id);
        if (!metaclass.isPresent()) {
            backend.metaclassFor(id, MetaclassValue.of(name, uri));
        }
        else {
            throw new IllegalArgumentException("An element with the same Id (" + id + ") is already defined. Use a handler with a conflicts resolution feature instead.");
        }
    }

    /**
     * Tries to link elements that have not been linked at their creation.
     *
     * @param reference the reference of the targeted element
     * @param id        the identifier of the targeted element
     */
    private void tryLink(String reference, Id id) {
        for (UnlinkedElement e : unlinkedElementsMap.removeAll(reference)) {
            addReference(e.id, e.name, e.index, e.many, e.containment, id);
        }
    }

    /**
     * Increments the operation counter, and commit the persistence back-end if the number of operation is equals to
     * {@code OPS_BETWEEN_COMMITS_DEFAULT}.
     */
    private void incrementAndCommit() {
        autocommitCount = (autocommitCount + 1) % DEFAULT_AUTOCOMMIT_CHUNK;
        if (autocommitCount == 0) {
            backend.save();
        }
    }

    /**
     * Creates a new element identified by the specified {@code id}.
     *
     * @param id the identifier of the element
     *
     * @throws AlreadyExistingIdException if the {@code id} is already used as primary key for another element
     */
    protected abstract void persist(final Id id);

    /**
     * Checks whether the {@code id} is already existing in the back-end.
     *
     * @param id the identifier of the element
     *
     * @throws NoSuchElementException if no element is found with the {@code id}
     */
    protected abstract void checkExists(final Id id);

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
