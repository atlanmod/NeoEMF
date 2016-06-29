/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.graph.blueprints.io.input;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.MetaClassifier;
import fr.inria.atlanmod.neoemf.io.hash.HasherFactory;
import fr.inria.atlanmod.neoemf.io.impl.AbstractPersistenceHandlerNoConflict;

import org.eclipse.emf.ecore.InternalEObject;

import java.util.concurrent.Callable;

/**
 *
 */
public class BlueprintsPersistenceHandlerNoConflict extends AbstractPersistenceHandlerNoConflict<BlueprintsPersistenceBackend> {

    private static final char SEPARATOR = ':';
    private static final String POSITION = "position";
    private static final String CONTAINER = "eContainer";
    private static final String CONTAINING_FEATURE = "containingFeature";
    private static final String SIZE_LITERAL = "size";

    private static final Id ROOT_ID = new StringId("ROOT");
    private static final String ROOT_FEATURE_NAME = "eContents";

    private final Cache<Id, Vertex> loadedVertices;

    public BlueprintsPersistenceHandlerNoConflict(BlueprintsPersistenceBackend persistenceBackend) {
        super(persistenceBackend);
        loadedVertices = CacheBuilder.newBuilder().maximumSize(DEFAULT_CACHE_SIZE).build();
    }

    @Override
    protected Id hashId(String reference) {
        String hash = HasherFactory.md5().hash(reference).toString();
        return new StringId(hash);
    }

    @Override
    public void handleStartDocument() throws Exception {
        createRootVertex();

        super.handleStartDocument();
    }

    @Override
    protected void addElement(Id id, String nsUri, String name, boolean root) throws Exception {
        Vertex vertex = getOrCreateVertex(id);
        vertex.setProperty(BlueprintsPersistenceBackend.ECLASS_NAME, name);
        vertex.setProperty(BlueprintsPersistenceBackend.EPACKAGE_NSURI, nsUri);

        if (root) {
            // Add the current element as content of the 'ROOT' node
            addReference(ROOT_ID, ROOT_FEATURE_NAME, InternalEObject.EStore.NO_INDEX, false, id);
        }
    }

    @Override
    protected void setMetaClass(Id id, Id metaClassId) throws Exception {
        Vertex vertex = getOrCreateVertex(id);
        Vertex metaClassVertex = getOrCreateVertex(metaClassId);

        vertex.addEdge(BlueprintsPersistenceBackend.INSTANCE_OF, metaClassVertex);
    }

    @Override
    protected void addAttribute(Id id, String name, int index, String value) throws Exception {
        Vertex vertex = getOrCreateVertex(id);

        int size = getSize(vertex, name);

        if (index == InternalEObject.EStore.NO_INDEX) {
            index = size;
        }

        size++;
        setSize(vertex, name, size);

        vertex.setProperty(formatKeyValue(name, index), value);
    }

    @Override
    protected void addReference(Id id, String name, int index, boolean containment, Id idReference) throws Exception {
        Vertex vertex = getOrCreateVertex(id);
        Vertex referencedVertex = getOrCreateVertex(idReference);

        // Update the containment reference if needed
        if (containment) {
            updateContainment(name, vertex, referencedVertex);
        }

        int size = getSize(vertex, name);

        if (index == InternalEObject.EStore.NO_INDEX) {
            index = size;
        }

        size++;
        setSize(vertex, name, size);

        Edge edge = vertex.addEdge(name, referencedVertex);
        edge.setProperty(POSITION, index);
    }

    private Vertex getOrCreateVertex(final Id id) throws Exception {
        return loadedVertices.get(id, new Callable<Vertex>() {
            @Override
            public Vertex call() throws Exception {
                Vertex vertex = getPersistenceBackend().getVertex(id.toString());

                if (vertex == null) {
                    vertex = getPersistenceBackend().addVertex(id.toString());
                }

                return vertex;
            }
        });
    }

    private void createRootVertex() throws Exception {
        // Create the 'ROOT' node with the default metaclass
        MetaClassifier metaClassifier = MetaClassifier.getDefault();

        Classifier rootClassifier = new Classifier(
                metaClassifier.getNamespace(),
                metaClassifier.getLocalName());

        rootClassifier.setClassName(metaClassifier.getLocalName());
        rootClassifier.setRoot(false);
        rootClassifier.setMetaClassifier(metaClassifier);

        Id id = createElement(rootClassifier, ROOT_ID);
        Id metaClassId = getOrCreateMetaClass(metaClassifier);

        setMetaClass(id, metaClassId);
    }

    private static void updateContainment(String localName, Vertex parentVertex, Vertex childVertex) {
        for (Edge edge : childVertex.getEdges(Direction.OUT, CONTAINER)) {
            edge.remove();
        }

        Edge edge = childVertex.addEdge(CONTAINER, parentVertex);
        edge.setProperty(CONTAINING_FEATURE, localName);
    }

    private static Integer getSize(Vertex vertex, String name) {
        Integer size = vertex.getProperty(formatKeyValue(name, SIZE_LITERAL));
        return size != null ? size : 0;
    }

    private static void setSize(Vertex vertex, String name, int size) {
        vertex.setProperty(formatKeyValue(name, SIZE_LITERAL), size);
    }

    private static String formatKeyValue(String key, Object value) {
        return key + SEPARATOR + value;
    }
}
