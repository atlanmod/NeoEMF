package fr.inria.atlanmod.neoemf.io.impl;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.PersistenceHandler;

/**
 *
 */
public abstract class AbstractPersistenceNotifier extends AbstractNotifier<PersistenceHandler> {

    protected final void notifyStartElement(Id id, String namespace, String localName) throws Exception {
        for (PersistenceHandler h : getHandlers()) {
            h.handleStartElement(id, namespace, localName);
        }
    }

    protected final void notifyAttribute(Id id, String namespace, String localName, String value) throws Exception {
        for (PersistenceHandler h : getHandlers()) {
            h.handleAttribute(id, namespace, localName, value);
        }
    }

    protected final void notifyReference(Id id, String namespace, String localName, Id idReference) throws Exception {
        for (PersistenceHandler h : getHandlers()) {
            h.handleReference(id, namespace, localName, idReference);
        }
    }

    protected final void notifyEndElement(Id id) throws Exception {
        for (PersistenceHandler h : getHandlers()) {
            h.handleEndElement(id);
        }
    }
}
