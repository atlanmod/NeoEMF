package fr.inria.atlanmod.neoemf.io.mock;

import fr.inria.atlanmod.neoemf.io.PersistenceHandler;

/**
 * A persistence handler that does nothing.
 * <p/>
 * Using for basic tests.
 */
public class MuteHandler implements PersistenceHandler {

    @Override
    public void handleStartElement(String namespace, String localName, String reference) throws Exception {

    }

    @Override
    public void handleAttribute(String namespace, String localName, String value) throws Exception {

    }

    @Override
    public void handleReference(String namespace, String localName, String reference) throws Exception {

    }

    @Override
    public void handleEndElement() throws Exception {

    }

    @Override
    public void handleStartDocument() throws Exception {

    }

    @Override
    public void handleEndDocument() throws Exception {

    }
}
