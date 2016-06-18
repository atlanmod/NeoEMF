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
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.io.InternalHandler;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public abstract class AbstractInternalHandler extends AbstractPersistenceNotifier implements InternalHandler {

    private final Map<String, Id> idRegistry;

    private final Deque<Id> idStack;

    private final Cache<String, List<UnlinkedElement>> unlinkedElement;

    protected AbstractInternalHandler() {
        this.idRegistry = new HashMap<>();
        this.idStack = new ArrayDeque<>();
        this.unlinkedElement = CacheBuilder.newBuilder().build();
    }

    @Override
    public void handleStartDocument() throws Exception {
        notifyStartDocument();
    }

    @Override
    public void handleStartElement(String prefix, String namespace, String localName, String reference) throws Exception {
        Id id = registerId(checkNotNull(reference));

        idStack.addLast(id);
        notifyStartElement(id, namespace, localName);

        tryLink(reference, id);
    }

    @Override
    public void handleAttribute(String namespace, String localName, String value) throws Exception {
        notifyAttribute(idStack.getLast(), namespace, localName, value);
    }

    @Override
    public void handleReference(String namespace, String localName, String reference) throws Exception {
        Id idReference = getId(reference);

        if (idReference == null) {
            addUnlinked(new UnlinkedElement(idStack.getLast(), namespace, localName, reference));
            return;
        }

        notifyReference(idStack.getLast(), namespace, localName, idReference);
    }

    @Override
    public void handleEndElement() throws Exception {
        notifyEndElement(idStack.removeLast());
    }

    @Override
    public void handleEndDocument() throws Exception {
        long unlinkedNumber = unlinkedElement.size();
        if(unlinkedNumber > 0) {
            NeoLogger.warn("Some elements have not been linked ({0})", unlinkedNumber);
            //for (String e : unlinkedElement.asMap().keySet()) {
            //    NeoLogger.warn(" > " + e);
            //}
            unlinkedElement.invalidateAll();
        }

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
        return idRegistry.get(reference);
    }

    /**
     * Register a reference and return its newly created {@link Id id}.
     *
     * @param reference the reference to register
     *
     * @return the newly create {@code Id} of the registered reference
     */
    private Id registerId(String reference) {
        Id id = StringId.generate();
        idRegistry.put(reference, id);
        return id;
    }

    private void addUnlinked(UnlinkedElement element) throws ExecutionException {
        try {
            List<UnlinkedElement> unlinkedElementsList = unlinkedElement.get(element.reference,
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
        List<UnlinkedElement> unlinkedElementList = unlinkedElement.getIfPresent(reference);
        if (unlinkedElementList != null) {
            for (UnlinkedElement e : unlinkedElementList) {
                notifyReference(e.id, e.namespace, e.localName, id);
            }
            unlinkedElement.invalidate(reference);
        }
    }

    /**
     * An element that could not be linked at its creation.
     */
    private class UnlinkedElement {

        public final Id id;
        public final String namespace;
        public final String localName;
        public final String reference;

        public UnlinkedElement(Id id, String namespace, String localName, String reference) {
            this.id = id;
            this.namespace = namespace;
            this.localName = localName;
            this.reference = reference;
        }
    }
}
