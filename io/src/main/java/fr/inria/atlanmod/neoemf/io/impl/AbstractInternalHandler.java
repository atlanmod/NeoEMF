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

import com.google.common.base.Charsets;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.io.AlreadyExistingId;
import fr.inria.atlanmod.neoemf.io.InternalHandler;
import fr.inria.atlanmod.neoemf.io.UnknownReferencedId;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public abstract class AbstractInternalHandler extends AbstractPersistenceNotifier implements InternalHandler {

    private static final HashFunction HASHER = Hashing.murmur3_32();

    private final Deque<Id> idStack;

    private final Cache<String, List<UnlinkedElement>> unlinkedElements;

    private final Cache<String, Id> conflictedIds;

    private long conflits = 0;

    protected AbstractInternalHandler() {
        this.idStack = new ArrayDeque<>();
        this.unlinkedElements = CacheBuilder.newBuilder().build();
        this.conflictedIds = CacheBuilder.newBuilder().build();
    }

    private static Id hashId(String reference) {
        return new StringId(HASHER.newHasher().putString(reference, Charsets.UTF_8).hash().toString());
    }

    @Override
    public void handleStartDocument() throws Exception {
        notifyStartDocument();
    }

    @Override
    public void handleStartElement(String prefix, String namespace, String localName, String reference) throws Exception {
        checkNotNull(reference);

        boolean retry;

        // Hash reference a first time
        Id id = hashId(reference);

        do {
            try {
                // Try to persist with the current Id
                notifyStartElement(id, namespace, localName);
                retry = false;
            }
            catch (AlreadyExistingId e) {
                // Id is already present in the backend : try with another Id
                conflits++;
                NeoLogger.warn("Conflict with Id = " + id);

                // Hash reference another time + Store (or update) in cache
                id = hashId(id.toString());
                conflictedIds.put(reference, id);

                retry = true;
            }
        } while (retry);

        idStack.addLast(id);

        tryLink(reference, id);
    }

    @Override
    public void handleAttribute(String namespace, String localName, String value) throws Exception {
        notifyAttribute(idStack.getLast(), namespace, localName, value);
    }

    @Override
    public void handleReference(String namespace, String localName, String reference) throws Exception {
        Id idReference = getId(reference);

        try {
            notifyReference(idStack.getLast(), namespace, localName, idReference);
        } catch (UnknownReferencedId e) {
            addUnlinked(reference, new UnlinkedElement(idStack.getLast(), namespace, localName));
        }
    }

    @Override
    public void handleEndElement() throws Exception {
        notifyEndElement(idStack.removeLast());
    }

    @Override
    public void handleEndDocument() throws Exception {
        long unlinkedNumber = unlinkedElements.size();
        if(unlinkedNumber > 0) {
            NeoLogger.warn("Some elements have not been linked ({0})", unlinkedNumber);
            //for (String e : unlinkedElements.asMap().keySet()) {
            //    NeoLogger.warn(" > " + e);
            //}
            unlinkedElements.invalidateAll();
        }

        NeoLogger.info("{0} key conflicts", conflits);

        notifyEndDocument();
    }

    /**
     * Get the {@link Id id} of the given reference.
     *
     * @param reference the reference
     *
     * @return the registered {@code Id} of the given reference, or {@code null} if the reference is not registered.
     */
    private Id getId(String reference) {
        Id id = conflictedIds.getIfPresent(reference);
        if (id != null) {
            NeoLogger.warn("Get from conflit cache : " + id);
            return id;
        } else {
            return hashId(reference);
        }
    }

    private void addUnlinked(String reference, UnlinkedElement element) throws ExecutionException {
        try {
            List<UnlinkedElement> unlinkedElementsList = unlinkedElements.get(reference,
                    new Callable<List<UnlinkedElement>>() {
                        @Override
                        public List<UnlinkedElement> call() throws Exception {
                            return new ArrayList<>();
                        }
                    });
            unlinkedElementsList.add(element);
        }
        catch (ExecutionException e) {
            NeoLogger.error(e);
            throw e;
        }
    }
    private void tryLink(String reference, Id id) throws Exception {
        List<UnlinkedElement> unlinkedElementList = unlinkedElements.getIfPresent(reference);
        if (unlinkedElementList != null) {
            for (UnlinkedElement e : unlinkedElementList) {
                notifyReference(e.id, e.namespace, e.localName, id);
            }
            unlinkedElements.invalidate(reference);
        }
    }

    /**
     * An element that could not be linked at its creation.
     */
    private class UnlinkedElement {

        public final Id id;
        public final String namespace;
        public final String localName;

        public UnlinkedElement(Id id, String namespace, String localName) {
            this.id = id;
            this.namespace = namespace;
            this.localName = localName;
        }
    }
}
