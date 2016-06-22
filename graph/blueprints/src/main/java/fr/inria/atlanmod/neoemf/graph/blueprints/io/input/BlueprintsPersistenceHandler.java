package fr.inria.atlanmod.neoemf.graph.blueprints.io.input;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.io.AlreadyExistingIdException;
import fr.inria.atlanmod.neoemf.io.UnknownReferencedIdException;
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
    protected void addElement(Id id, String nsUri, String name) throws Exception {
        Vertex vertex;
        try {
            vertex = getPersistenceBackend().addVertex(id.toString());
        } catch (IllegalArgumentException e) {
            throw new AlreadyExistingIdException();
        }
        vertex.setProperty(BlueprintsPersistenceBackend.ECLASS__NAME, name);
        vertex.setProperty(BlueprintsPersistenceBackend.EPACKAGE__NSURI, nsUri);
    }

    @Override
    protected void linkElementToMetaClass(Id id, Id metaClassId) {
        Vertex vertex = getPersistenceBackend().getVertex(id.toString());
        checkNotNull(vertex, "Unable to find an element with Id = " + id.toString());

        Vertex metaClassVertex = getPersistenceBackend().getVertex(metaClassId.toString());
        checkNotNull(vertex, "Unable to find metaclass with Id = " + metaClassId.toString());

        vertex.addEdge(BlueprintsPersistenceBackend.INSTANCE_OF, metaClassVertex);
    }

    @Override
    protected void addAttribute(Id id, String nsUri, String name, int index, String value) throws Exception {
        Vertex vertex = getPersistenceBackend().getVertex(id.toString());
        checkNotNull(vertex, "Unable to find an element with Id = " + id.toString());

        int size = getSize(vertex, name);
        size++;
        setSize(vertex, name, size);

        // TODO Serialize value to property

        vertex.setProperty(name + ":" + index, value);
    }

    @Override
    protected void addReference(Id id, String nsUri, String name, int index, Id idReference) throws Exception {
        Vertex vertex = getPersistenceBackend().getVertex(id.toString());
        checkNotNull(vertex, "Unable to find an element with Id = " + id.toString());

        Vertex referencedVertex = getPersistenceBackend().getVertex(idReference.toString());
        if (referencedVertex == null) {
            throw new UnknownReferencedIdException();
        }

        // TODO Update the containment reference if needed

        int size = getSize(vertex, name);
        size++;
        setSize(vertex, name, size);

        Edge edge = vertex.addEdge(name, referencedVertex);
        edge.setProperty("position", index);
    }

    private static Integer getSize(Vertex vertex, String name) {
        Integer size = vertex.getProperty(name + ":size");
        return size != null ? size : 0;
    }

    private static void setSize(Vertex vertex, String name, int size) {
        vertex.setProperty(name + ":size", size);
    }
}
