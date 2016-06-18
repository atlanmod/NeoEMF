package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.core.Id;

/**
 * A persistence handler that does nothing.
 * <p/>
 * Using for basic tests.
 */
public class VoidHandler implements PersistenceHandler {

    @Override
    public void handleStartElement(Id id, String namespace, String localName) throws Exception {

    }

    @Override
    public void handleAttribute(Id id, String namespace, String localName, String value) throws Exception {

    }

    @Override
    public void handleReference(Id id, String namespace, String localName, Id idReference) throws Exception {

    }

    @Override
    public void handleEndElement(Id id) throws Exception {

    }

    @Override
    public void handleStartDocument() throws Exception {

    }

    @Override
    public void handleEndDocument() throws Exception {

    }
}
