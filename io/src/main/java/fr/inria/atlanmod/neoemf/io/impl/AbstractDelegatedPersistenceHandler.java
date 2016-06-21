package fr.inria.atlanmod.neoemf.io.impl;

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
    public void handleStartElement(String namespace, String localName, String reference) throws Exception {
        handler.handleStartElement(namespace, localName, reference);
    }

    @Override
    public void handleAttribute(String namespace, String localName, String value) throws Exception {
        handler.handleAttribute(namespace, localName, value);
    }

    @Override
    public void handleReference(String namespace, String localName, String reference) throws Exception {
        handler.handleReference(namespace, localName, reference);
    }

    @Override
    public void handleEndElement() throws Exception {
        handler.handleEndElement();
    }

    @Override
    public void handleEndDocument() throws Exception {
        handler.handleEndDocument();
    }
}
