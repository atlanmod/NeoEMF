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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.io.AlreadyExistingIdException;
import fr.inria.atlanmod.neoemf.io.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.UnknownReferencedIdException;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
    private final Cache<String, List<UnlinkedElement>> unlinkedElements;

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
        this.unlinkedElements = CacheBuilder.newBuilder().build();
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

    protected abstract void linkElementToMetaClass(Id id, Id metaClassId);

    @Override
    public void handleStartDocument() throws Exception {
        // Do nothing
    }

    @Override
    public void handleStartElement(String nsUri, String name, String reference) throws Exception {
        checkNotNull(reference);

        boolean retry;

        // Hash reference a first time
        Id id = hashId(reference);

        do {
            try {
                // Try to persist with the current Id
                addElement(id, nsUri, name);
                retry = false;
            }
            catch (AlreadyExistingIdException e) {
                // Id is already present in the backend : try with another Id
                NeoLogger.warn("Conflict with Id " + id);

                // Hash reference another time + Store (or update) in cache
                id = hashId(id.toString());
                conflictedIdsCache.put(reference, id);

                retry = true;
            }
        } while (retry);

        idStack.addLast(id);

        incrementAndCommit();

        tryLink(reference, id);
    }

    @Override
    public void handleMetaClass(String nsUri, String name) throws Exception {
        String metaclassKey = nsUri + ":" + name;

        Id metaClassId = metaclassesCache.getIfPresent(metaclassKey);

        // If metaclass doesn't already exist, we create it
        if (metaClassId == null) {
            boolean retry;

            // Hash reference a first time
            metaClassId = hashId(metaclassKey);

            do {
                try {
                    // Try to persist with the current Id
                    addElement(metaClassId, nsUri, name);
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

        linkElementToMetaClass(idStack.getLast(), metaClassId);
    }

    @Override
    public void handleAttribute(String nsUri, String name, int index, String value) throws Exception {
        addAttribute(idStack.getLast(), nsUri, name, index, value);

        incrementAndCommit();
    }

    @Override
    public void handleReference(String nsUri, String name, int index, String reference) throws Exception {
        Id currentId = idStack.getLast();
        Id idReference = getId(reference);

        try {
            addReference(currentId, nsUri, name, index, idReference);
        } catch (UnknownReferencedIdException e) {
            addUnlinked(reference, new UnlinkedElement(currentId, nsUri, name, index));
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
            unlinkedElements.invalidateAll();
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
        List<UnlinkedElement> unlinkedElementsList = unlinkedElements.getIfPresent(reference);

        if (unlinkedElementsList == null) {
            unlinkedElementsList = new ArrayList<>();
            unlinkedElements.put(reference, unlinkedElementsList);
        }

        unlinkedElementsList.add(element);
    }

    /**
     * Tries to link elements that have not been linked at their creation.
     *
     * @param reference the reference of the targetted element
     * @param id the identifier of the targetted element
     */
    private void tryLink(String reference, Id id) throws Exception {
        List<UnlinkedElement> unlinkedElementList = unlinkedElements.getIfPresent(reference);

        if (unlinkedElementList != null) {
            for (UnlinkedElement e : unlinkedElementList) {
                addReference(e.id, e.nsUri, e.name, e.index, id);
            }
            unlinkedElements.invalidate(reference);
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
