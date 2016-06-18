package fr.inria.atlanmod.neoemf.graph.blueprints.io.xmi;

import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.io.impl.AbstractPersistenceHandler;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class BlueprintsPersistenceHandler extends AbstractPersistenceHandler<BlueprintsPersistenceBackend> {

    public BlueprintsPersistenceHandler(BlueprintsPersistenceBackend persistenceBackend) {
        super(persistenceBackend);
    }

    @Override
    public void handleStartElement(Id id, String namespace, String localName) throws Exception {
        Vertex vertex = getPersistenceBackend().addVertex(id.toString());
        vertex.setProperty(BlueprintsPersistenceBackend.ECLASS__NAME, localName);
        vertex.setProperty(BlueprintsPersistenceBackend.EPACKAGE__NSURI, namespace);

        super.handleStartElement(id, namespace, localName);
    }

    @Override
    public void handleAttribute(Id id, String namespace, String localName, String value) throws Exception {
        Vertex vertex = getPersistenceBackend().getVertex(id.toString());
        checkNotNull(vertex, "Unable to find an element with Id = " + id.toString());

        // TODO Probably some stuff before setting attribute

        vertex.setProperty(localName, value);

        super.handleAttribute(id, namespace, localName, value);
    }

    @Override
    public void handleReference(Id id, String namespace, String localName, Id idReference) throws Exception {
        Vertex vertex = getPersistenceBackend().getVertex(id.toString());
        Vertex referencedVertex = getPersistenceBackend().getVertex(idReference.toString());

        checkNotNull(vertex, "Unable to find an element with Id = " + id.toString());
        checkNotNull(referencedVertex, "Unable to find a referenced element with Id = " + id.toString());

        // TODO Probably some stuff before adding edge

        vertex.addEdge(localName, referencedVertex);

        super.handleReference(id, namespace, localName, idReference);
    }
}
