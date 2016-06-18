package fr.inria.atlanmod.neoemf.io.impl;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.PersistenceHandler;

/**
 *
 */
public abstract class AbstractDelegatedPersistenceHandler implements PersistenceHandler {

    private final PersistenceHandler handler;

    public AbstractDelegatedPersistenceHandler(PersistenceHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handleStartDocument() throws Exception {
        handler.handleStartDocument();
    }

    @Override
    public void handleStartElement(Id id, String namespace, String localName) throws Exception {
        handler.handleStartElement(id, namespace, localName);
    }

    @Override
    public void handleAttribute(Id id, String namespace, String localName, String value) throws Exception {
        handler.handleAttribute(id, namespace, localName, value);
    }

    @Override
    public void handleReference(Id id, String namespace, String localName, Id idReference) throws Exception {
        handler.handleReference(id, namespace, localName, idReference);
    }

    @Override
    public void handleEndElement(Id id) throws Exception {
        handler.handleEndElement(id);
    }

    @Override
    public void handleEndDocument() throws Exception {
        handler.handleEndDocument();
    }
}
