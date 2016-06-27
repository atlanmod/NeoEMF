/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.HashMultimap;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.io.AlreadyExistingIdException;
import fr.inria.atlanmod.neoemf.io.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.UnknownReferencedIdException;
import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.NamedElement;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An handler able to persist newly created data in a {@link PersistenceBackend persistence backend}.
 */
public abstract class AbstractPersistenceHandler<P extends PersistenceBackend> implements PersistenceHandler {

    private static final int OPS_BETWEEN_COMMITS_DEFAULT = 100000;
    private static final int ID_CACHE_SIZE = 10000;

    private int opCount;

    private final P persistenceBackend;

    private final Deque<Id> idStack;

    /**
     * Cache of unlinked elements, waiting until their reference is created.
     */
    // TODO Create a better structure to keep unlinked elements (Heap space issues)
    private final HashMultimap<String, UnlinkedElement> unlinkedElements;

    /**
     * Cache of recently processed {@code Id}.
     */
    private final Cache<String, Id> idsCache;

    /**
     * Cache of conflited {@code Id}.
     */
    private final Cache<String, Id> conflictedIdsCache;

    /**
     * Cache of registered metaclasses.
     */
    private final Cache<String, Id> metaclassesCache;

    public AbstractPersistenceHandler(P persistenceBackend) {
        this.persistenceBackend = persistenceBackend;
        this.opCount = 0;
        this.idStack = new ArrayDeque<>();
        this.unlinkedElements = HashMultimap.create();
        this.idsCache = CacheBuilder.newBuilder().maximumSize(ID_CACHE_SIZE).build();
        this.conflictedIdsCache = CacheBuilder.newBuilder().build();
        this.metaclassesCache = CacheBuilder.newBuilder().build();
    }

    protected P getPersistenceBackend() {
        return persistenceBackend;
    }

    protected abstract Id hashId(String reference);

    protected abstract void addElement(Id id, String nsUri, String name) throws Exception;

    protected abstract void addAttribute(Id id, String nsUri, String name, int index, String value) throws Exception;

    protected abstract void addReference(Id id, String nsUri, String name, int index, Id idReference) throws Exception;

    protected abstract void setMetaClass(Id id, Id metaClassId);

    @Override
    public void handleStartDocument() throws Exception {
        // Do nothing
    }

    @Override
    public void handleStartElement(Classifier classifier) throws Exception {
        checkNotNull(classifier.getId());

        boolean retry;

        // Hash reference a first time
        Id id = hashId(classifier.getId());

        do {
            try {
                // Try to persist with the current Id
                addElement(id, classifier.getNamespace().getUri(), classifier.getClassName());
                retry = false;
            }
            catch (AlreadyExistingIdException e) {
                // Id is already present in the backend : try with another Id
                NeoLogger.warn("Conflict with Id " + id);

                // Hash reference another time + Store (or update) in cache
                id = hashId(id.toString());
                conflictedIdsCache.put(classifier.getId(), id);

                retry = true;
            }
        } while (retry);

        idStack.addLast(id);
        tryLink(classifier.getId(), id);

        createMetaClass(classifier.getMetaclass());

        incrementAndCommit();
    }

    private void createMetaClass(NamedElement metaClass) throws Exception {
        String metaclassKey = metaClass.getNamespace().getUri() + ':' + metaClass.getLocalName();

        // Gets from cache
        Id metaClassId = metaclassesCache.getIfPresent(metaclassKey);

        // If metaclass doesn't already exist, we create it
        if (metaClassId == null) {
            boolean retry;

            // Hash reference a first time
            metaClassId = hashId(metaclassKey);

            do {
                try {
                    // Try to persist with the current Id
                    addElement(metaClassId, metaClass.getNamespace().getUri(), metaClass.getLocalName());
                    metaclassesCache.put(metaclassKey, metaClassId);
                    retry = false;
                }
                catch (AlreadyExistingIdException e) {
                    // Hash reference another time + Store (or update) in cache
                    metaClassId = hashId(metaClassId.toString());
                    retry = true;
                }
            }
            while (retry);
        }

        setMetaClass(idStack.getLast(), metaClassId);
    }

    @Override
    public void handleAttribute(Attribute attribute) throws Exception {
        Id currentId;
        if (attribute.getId() == null) {
            currentId = idStack.getLast();
        } else {
            currentId = getId(attribute.getId());
        }

        addAttribute(currentId, attribute.getNamespace().getUri(), attribute.getLocalName(), attribute.getIndex(), attribute.getValue());

        incrementAndCommit();
    }

    @Override
    public void handleReference(Reference reference) throws Exception {
        Id currentId;
        if (reference.getId() == null) {
            currentId = idStack.getLast();
        } else {
            currentId = getId(reference.getId());
        }

        Id idReference = getId(reference.getValue());

        try {
            addReference(currentId, reference.getNamespace().getUri(), reference.getLocalName(), reference.getIndex(), idReference);
        } catch (UnknownReferencedIdException e) {
            addUnlinked(reference.getValue(), new UnlinkedElement(currentId, reference.getNamespace().getUri(), reference.getLocalName(), reference.getIndex()));
        }

        incrementAndCommit();
    }

    @Override
    public void handleEndElement() throws Exception {
        idStack.removeLast();
    }

    @Override
    public void handleEndDocument() throws Exception {
        long unlinkedNumber = unlinkedElements.size();
        if(unlinkedNumber > 0) {
            NeoLogger.warn("Some elements have not been linked ({0})", unlinkedNumber);
            for (String e : unlinkedElements.asMap().keySet()) {
                NeoLogger.warn(" > " + e);
            }
            unlinkedElements.clear();
        }

        NeoLogger.info("{0} key conflicts", conflictedIdsCache.size());

        persistenceBackend.save();
    }

    /**
     * Get the {@link Id id} of the given reference.
     *
     * @param reference the reference
     *
     * @return the registered {@code Id} of the given reference, or {@code null} if the reference is not registered.
     */
    private Id getId(String reference) {
        Id id = conflictedIdsCache.getIfPresent(reference);
        if (id == null) {
            id = idsCache.getIfPresent(reference);
            if (id == null) {
                id = hashId(reference);
                idsCache.put(reference, id);
            }
        }
        return id;
    }

    /**
     * Defines an element as unlinked and stores it in a cache until the referenced element will be created.
     *
     * @param reference the reference of the targetted element
     * @param element the element to store
     */
    private void addUnlinked(String reference, UnlinkedElement element) {
        unlinkedElements.put(reference, element);
    }

    /**
     * Tries to link elements that have not been linked at their creation.
     *
     * @param reference the reference of the targetted element
     * @param id the identifier of the targetted element
     */
    private void tryLink(String reference, Id id) throws Exception {
        for (UnlinkedElement e : unlinkedElements.removeAll(reference)) {
            addReference(e.id, e.nsUri, e.name, e.index, id);
        }
    }

    /**
     * Increments the operation counter, and commit the persistence backend if the number of operation is equals to
     * {@code OPS_BETWEEN_COMMITS_DEFAULT}.
     */
    private void incrementAndCommit() {
        opCount = (opCount + 1) % OPS_BETWEEN_COMMITS_DEFAULT;
        if (opCount == 0) {
            persistenceBackend.save();
        }
    }

    /**
     * An element that could not be linked at its creation.
     */
    private class UnlinkedElement {

        public final Id id;
        public final String nsUri;
        public final String name;
        public final int index;

        public UnlinkedElement(Id id, String nsUri, String name, int index) {
            this.id = id;
            this.nsUri = nsUri;
            this.name = name;
            this.index = index;
        }
    }
}
