package fr.inria.atlanmod.neoemf.io.impl;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.io.PersistenceHandler;

/**
 * An handler able to persist newly created data in a {@link PersistenceBackend persistence backend}.
 */
public abstract class AbstractPersistenceHandler<P extends PersistenceBackend> implements PersistenceHandler {

    private static final int OPS_BETWEEN_COMMITS_DEFAULT = 100000;

    private int opCount;

    private final P persistenceBackend;

    public AbstractPersistenceHandler(P persistenceBackend) {
        this.persistenceBackend = persistenceBackend;
        this.opCount = 0;
    }

    public P getPersistenceBackend() {
        return persistenceBackend;
    }

    @Override
    public void handleStartDocument() throws Exception {
        // Do nothing
    }

    @Override
    public void handleStartElement(Id id, String namespace, String localName) throws Exception {
        incrementAndCommit();
    }

    @Override
    public void handleAttribute(Id id, String namespace, String localName, String value) throws Exception {
        incrementAndCommit();
    }

    @Override
    public void handleReference(Id id, String namespace, String localName, Id idReference) throws Exception {
        incrementAndCommit();
    }

    @Override
    public void handleEndElement(Id id) throws Exception {
        // Do nothing
    }

    @Override
    public void handleEndDocument() throws Exception {
        persistenceBackend.save();
    }

    private void incrementAndCommit() {
        opCount = (opCount + 1) % OPS_BETWEEN_COMMITS_DEFAULT;
        if (opCount == 0) {
            persistenceBackend.save();
        }
    }
}
