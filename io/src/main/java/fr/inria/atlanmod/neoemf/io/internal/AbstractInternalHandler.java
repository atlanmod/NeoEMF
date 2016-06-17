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

package fr.inria.atlanmod.neoemf.io.internal;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.impl.StringId;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public abstract class AbstractInternalHandler extends AbstractInternalNotifier implements InternalHandler {

    private Map<String, Id> idRegistry;

    public AbstractInternalHandler() {
        this.idRegistry = new HashMap<>();
    }

    @Override
    public void handleStartDocument() {
        notifyStartDocument();
    }

    @Override
    public void handleStartElement(String namespace, String localName) {
        /*
         * TODO What is the reference Id for this element ?
         * Create a strcture to determine the reference name of this element.
         */
        //registerId(reference);
        notifyStartElement(namespace, localName);
    }

    @Override
    public void handleAttribute(String namespace, String localName, String value) {
        notifyAttribute(namespace, localName, value);
    }

    @Override
    public void handleReference(String namespace, String localName, String reference) {
        /*
         * TODO Link a reference to an Id.
         * If the Id exists, we link the current attribute with its reference, otherwise, we wait in a thread.
         */
        Id idReference = getId(reference);

        notifyReference(namespace, localName, reference);
    }

    @Override
    public void handleEndElement() {
        notifyEndElement();
    }

    @Override
    public void handleEndDocument() {
        notifyEndDocument();
    }

    /**
     * Get the {@link Id id} of the given reference.
     *
     * @param reference the reference
     *
     * @return the registered {@code Id} of the given reference, or {@code null} if the reference is not registered.
     */
    protected Id getId(String reference) {
        return idRegistry.get(reference);
    }

    /**
     * Register a reference and return its newly created {@link Id id}.
     *
     * @param reference the reference to register
     *
     * @return the newly create {@code Id} of the registered reference
     */
    protected Id registerId(String reference) {
        Id id = StringId.generate();
        idRegistry.put(reference, id);
        return id;
    }
}
