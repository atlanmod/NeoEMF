package fr.inria.atlanmod.neoemf.graph.blueprints.io;

import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.io.AlreadyExistingIdException;
import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;

import java.util.NoSuchElementException;

/**
 * A {@link PersistenceHandler} for a {@link BlueprintsPersistenceBackend}.
 * <p/>
 * <b>NOTE :</b> This handler has a key conflicts resolution feature, but it consumes much more memory than a backend
 * without conflicts resolution. Make sure you have enough memory to avoid heap space.
 */
public class BlueprintsAwareHandler extends AbstractBlueprintsHandler {

    public BlueprintsAwareHandler(BlueprintsPersistenceBackend persistenceBackend) {
        super(persistenceBackend);
    }

    @Override
    protected Vertex getVertex(final Id id) {
        try {
            return loadedVertices.get(id, key -> getPersistenceBackend().getVertex(key));
        }
        catch (Exception e) {
            throw new NoSuchElementException("Unable to find an element with Id '" + id.toString() + "'");
        }
    }

    @Override
    protected Vertex createVertex(final Id id) {
        try {
            return loadedVertices.get(id, key -> getPersistenceBackend().addVertex(key));
        }
        catch (Exception e) {
            throw new AlreadyExistingIdException("Already existing Id '" + id.toString() + "'");
        }
    }
}
