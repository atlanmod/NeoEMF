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
import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.MetaClassifier;
import fr.inria.atlanmod.neoemf.io.beans.Reference;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An handler able to persist newly created data in a {@link PersistenceBackend persistence backend}.
 */
public abstract class AbstractPersistenceHandlerNoConflict<P extends PersistenceBackend> implements PersistenceHandler {

    private static final int OPS_BETWEEN_COMMITS_DEFAULT = 50000;
    protected static final int DEFAULT_CACHE_SIZE = getSizeCache();

    private int opCount;

    private final P persistenceBackend;

    private final Deque<Id> idStack;

    /**
     * Cache of recently processed {@code Id}.
     */
    private final Cache<String, Id> idCache;

    /**
     * Cache of registered metaclasses.
     */
    private final Cache<String, Id> metaclassCache;

    public AbstractPersistenceHandlerNoConflict(P persistenceBackend) {
        this.persistenceBackend = persistenceBackend;
        this.opCount = 0;
        this.idStack = new ArrayDeque<>();

        this.idCache = CacheBuilder.newBuilder().maximumSize(DEFAULT_CACHE_SIZE).build();
        this.metaclassCache = CacheBuilder.newBuilder().build();
    }

    protected P getPersistenceBackend() {
        return persistenceBackend;
    }

    protected abstract Id hashId(String reference);

    protected abstract void addElement(Id id, String nsUri, String name, boolean root) throws Exception;

    protected abstract void addAttribute(Id id, String name, int index, String value) throws Exception;

    protected abstract void addReference(Id id, String name, int index, boolean containment, Id idReference) throws Exception;

    protected abstract void setMetaClass(Id id, Id metaClassId) throws Exception;

    @Override
    public void handleStartDocument() throws Exception {
        // Do nothing
    }

    @Override
    public void handleStartElement(final Classifier classifier) throws Exception {
        Id id = createElement(classifier, hashId(classifier.getId()));
        Id metaClassId = getOrCreateMetaClass(classifier.getMetaClassifier());

        setMetaClass(id, metaClassId);

        idStack.addLast(id);
    }

    /**
     * Creates an element from the given {@code classifier} with the given {@code id}, and returns the given {@code id}

     * @return the given {@code id}
     */
    protected Id createElement(final Classifier classifier, final Id id) throws Exception {
        checkNotNull(id);

        addElement(id,
                classifier.getNamespace().getUri(),
                classifier.getClassName(),
                classifier.isRoot());

        incrementAndCommit();

        return id;
    }

    /**
     * Creates a metaclass form the given {@code metaClassifier} and returns its {@link Id}.

     * @return the {@link Id} of the newly created metaclass
     */
    protected Id getOrCreateMetaClass(final MetaClassifier metaClassifier) throws Exception {
        String metaClassKey = metaClassifier.getNamespace().getUri() + ':' + metaClassifier.getLocalName();

        // Gets from cache
        Id metaClassId = metaclassCache.getIfPresent(metaClassKey);

        // If metaclass doesn't already exist, we create it
        if (metaClassId == null) {
            boolean retry;

            // Hash reference a first time
            metaClassId = hashId(metaClassKey);

            do {
                try {
                    // Try to persist with the current Id
                    addElement(
                            metaClassId,
                            metaClassifier.getNamespace().getUri(),
                            metaClassifier.getLocalName(),
                            false);

                    metaclassCache.put(metaClassKey, metaClassId);
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

        incrementAndCommit();

        return metaClassId;
    }

    @Override
    public void handleAttribute(final Attribute attribute) throws Exception {
        Id id;
        if (attribute.getId() == null) {
            id = idStack.getLast();
        } else {
            id = getId(attribute.getId());
        }

        addAttribute(id,
                attribute.getLocalName(),
                attribute.getIndex(),
                attribute.getValue());

        incrementAndCommit();
    }

    @Override
    public void handleReference(final Reference reference) throws Exception {
        Id id;
        if (reference.getId() == null) {
            id = idStack.getLast();
        } else {
            id = getId(reference.getId());
        }

        Id idReference = getId(reference.getValue());

        addReference(id,
                reference.getLocalName(),
                reference.getIndex(),
                reference.isContainment(),
                idReference);

        Reference opposite = reference.getOpposite();
        if (opposite != null) {
            addReference(idReference,
                    opposite.getLocalName(),
                    opposite.getIndex(),
                    opposite.isContainment(),
                    id);
        }

        incrementAndCommit();
    }

    @Override
    public void handleEndElement() throws Exception {
        idStack.removeLast();
    }

    @Override
    public void handleEndDocument() throws Exception {
        persistenceBackend.save();
    }

    /**
     * Get the {@link Id id} of the given reference.
     *
     * @param reference the reference
     *
     * @return the registered {@code Id} of the given reference, or {@code null} if the reference is not registered.
     */
    protected Id getId(final String reference) {
        Id id = idCache.getIfPresent(reference);
        if (id == null) {
            id = hashId(reference);
            idCache.put(reference, id);
        }
        return id;
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

    private static int getSizeCache() {
        long maxMemory = Runtime.getRuntime().maxMemory() / 1000 / 1000 / 1000; //Xmx value in GB
        int offset = 20000; //elements by Gb of RAM

        return (int) (maxMemory * offset);
    }
}
