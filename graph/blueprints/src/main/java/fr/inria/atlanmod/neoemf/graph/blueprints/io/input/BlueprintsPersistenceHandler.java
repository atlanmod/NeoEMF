package fr.inria.atlanmod.neoemf.graph.blueprints.io.input;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.io.AlreadyExistingId;
import fr.inria.atlanmod.neoemf.io.UnknownReferencedId;
import fr.inria.atlanmod.neoemf.io.impl.AbstractPersistenceHandler;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class BlueprintsPersistenceHandler extends AbstractPersistenceHandler<BlueprintsPersistenceBackend> {

    private static final HashFunction HASHER = Hashing.md5();

    public BlueprintsPersistenceHandler(BlueprintsPersistenceBackend persistenceBackend) {
        super(persistenceBackend);
    }

    @Override
    protected Id hashId(String reference) {
        return new StringId(HASHER.newHasher().putString(reference, Charsets.UTF_8).hash().toString());
    }

    @Override
    protected void addElement(Id id, String namespace, String localName) throws Exception {
        Vertex vertex;
        try {
            vertex = getPersistenceBackend().addVertex(id.toString());
        } catch (IllegalArgumentException e) {
            throw new AlreadyExistingId();
        }
        vertex.setProperty(BlueprintsPersistenceBackend.ECLASS__NAME, localName);
        vertex.setProperty(BlueprintsPersistenceBackend.EPACKAGE__NSURI, namespace);
    }

    @Override
    protected void addAttribute(Id id, String namespace, String localName, String value) throws Exception {
        Vertex vertex = getPersistenceBackend().getVertex(id.toString());

        checkNotNull(vertex, "Unable to find an element with Id = " + id.toString());

        // TODO Probably some stuff before setting attribute. Index ?

        vertex.setProperty(localName, value);
    }

    @Override
    protected void addReference(Id id, String namespace, String localName, Id idReference) throws Exception {
        Vertex vertex = getPersistenceBackend().getVertex(id.toString());
        checkNotNull(vertex, "Unable to find an element with Id = " + id.toString());

        Vertex referencedVertex = getPersistenceBackend().getVertex(idReference.toString());
        if (referencedVertex == null) {
            throw new UnknownReferencedId();
        }

        // TODO Probably some stuff before adding edge. Index ?

        vertex.addEdge(localName, referencedVertex);
    }
}
