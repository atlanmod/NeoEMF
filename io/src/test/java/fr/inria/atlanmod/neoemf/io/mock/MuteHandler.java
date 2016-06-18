package fr.inria.atlanmod.neoemf.io.mock;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.PersistenceHandler;

/**
 * A persistence handler that does nothing.
 * <p/>
 * Using for basic tests.
 */
public class MuteHandler implements PersistenceHandler {

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
