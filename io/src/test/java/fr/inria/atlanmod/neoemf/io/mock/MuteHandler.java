package fr.inria.atlanmod.neoemf.io.mock;

import fr.inria.atlanmod.neoemf.io.PersistenceHandler;

/**
 * A persistence handler that does nothing.
 * <p/>
 * Using for basic tests.
 */
public class MuteHandler implements PersistenceHandler {

    @Override
    public void handleStartElement(String nsUri, String name, String reference) throws Exception {

    }

    @Override
    public void handleMetaClass(String nsUri, String name) throws Exception {

    }

    @Override
    public void handleAttribute(String nsUri, String name, int index, String value) throws Exception {

    }

    @Override
    public void handleReference(String nsUri, String name, int index, String reference) throws Exception {

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
