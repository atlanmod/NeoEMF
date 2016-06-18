package fr.inria.atlanmod.neoemf.io.mock;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.PersistenceHandler;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

/**
 *
 */
public class VerboseHandler implements PersistenceHandler {

    @Override
    public void handleStartDocument() throws Exception {
        NeoLogger.info("Start document");
    }

    @Override
    public void handleStartElement(Id id, String namespace, String localName) throws Exception {
        NeoLogger.info("Start element : " + id);
    }

    @Override
    public void handleAttribute(Id id, String namespace, String localName, String value) throws Exception {
        NeoLogger.info("Attribute for " + id + " : " + localName + " = " + value);
    }

    @Override
    public void handleReference(Id id, String namespace, String localName, Id idReference) throws Exception {
        NeoLogger.info("Reference for " + id + " : " + localName + " = " + idReference);
    }

    @Override
    public void handleEndElement(Id id) throws Exception {
        NeoLogger.info("End element : " + id);
    }

    @Override
    public void handleEndDocument() throws Exception {
        NeoLogger.info("End document");
    }
}
