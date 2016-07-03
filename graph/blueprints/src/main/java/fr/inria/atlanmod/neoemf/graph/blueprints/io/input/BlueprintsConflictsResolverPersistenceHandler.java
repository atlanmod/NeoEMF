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
import fr.inria.atlanmod.neoemf.io.AlreadyExistingIdException;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.Identifier;
import fr.inria.atlanmod.neoemf.io.beans.MetaClassifier;
import fr.inria.atlanmod.neoemf.io.impl.AbstractPersistenceHandler;

import org.eclipse.emf.ecore.InternalEObject;

import java.util.NoSuchElementException;
import java.util.concurrent.Callable;

/**
 * A {@link fr.inria.atlanmod.neoemf.io.PersistenceHandler persistence handler} for a
 * {@link BlueprintsPersistenceBackend Blueprints persistence backend}.
 * <p/>
 * <b>NOTE :</b> This handler has a key conflicts resolution feature, but it consumes much more memory than a backend
 * without conflicts resolution. Make sure you have enough memory to avoid heap space.
 */
class BlueprintsConflictsResolverPersistenceHandler extends AbstractPersistenceHandler<BlueprintsPersistenceBackend> {

    private static final char SEPARATOR = ':';
    private static final String POSITION = "position";
    private static final String CONTAINER = "eContainer";
    private static final String CONTAINING_FEATURE = "containingFeature";
    private static final String SIZE_LITERAL = "size";

    private static final Id ROOT_ID = new StringId("ROOT");
    private static final String ROOT_FEATURE_NAME = "eContents";

    protected final Cache<Id, Vertex> loadedVertices;

    public BlueprintsConflictsResolverPersistenceHandler(BlueprintsPersistenceBackend persistenceBackend) {
        super(persistenceBackend);
        loadedVertices = CacheBuilder.newBuilder().maximumSize(DEFAULT_CACHE_SIZE).build();
    }

    @Override
    protected Id getId(final String reference) {
        return new StringId(reference);
    }

    @Override
    public void handleStartDocument() throws Exception {
        createRootVertex();

        super.handleStartDocument();
    }

    @Override
    protected void addElement(final Id id, final String nsUri, final String name, final boolean root) throws Exception {
        Vertex vertex = createVertex(id);

        // Checks if the Vertex is not already defined
        if (vertex.getProperty(BlueprintsPersistenceBackend.ECLASS_NAME) != null) {
            throw new IllegalArgumentException(
                    "An element with the same Id (" + id.toString() + ") is already defined. " +
                            "Use a handler with a conflicts resolution feature instead.");
        }

        vertex.setProperty(BlueprintsPersistenceBackend.ECLASS_NAME, name);
        vertex.setProperty(BlueprintsPersistenceBackend.EPACKAGE_NSURI, nsUri);

        if (root) {
            // Add the current element as content of the 'ROOT' node
            addReference(ROOT_ID, ROOT_FEATURE_NAME, InternalEObject.EStore.NO_INDEX, false, id);
        }
    }

    @Override
    protected void setMetaClass(final Id id, final Id metaClassId) throws Exception {
        Vertex vertex = getVertex(id);
        Vertex metaClassVertex = getVertex(metaClassId);

        vertex.addEdge(BlueprintsPersistenceBackend.INSTANCE_OF, metaClassVertex);
    }

    @Override
    protected void addAttribute(final Id id, final String name, int index, final String value) throws Exception {
        Vertex vertex = getVertex(id);

        int size = getSize(vertex, name);

        if (index == InternalEObject.EStore.NO_INDEX) {
            index = size;
        }

        size++;
        setSize(vertex, name, size);

        vertex.setProperty(formatKeyValue(name, index), value);
    }

    @Override
    protected void addReference(final Id id, final String name, int index, final boolean containment, final Id idReference) throws Exception {
        Vertex vertex = getVertex(id);
        Vertex referencedVertex = getVertex(idReference);

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

    protected Vertex getVertex(final Id id) throws Exception {
        try {
            return loadedVertices.get(id, new Callable<Vertex>() {
                @Override
                public Vertex call() throws Exception {
                    return getPersistenceBackend().getVertex(id.toString());
                }
            });
        } catch (Exception e) {
            throw new NoSuchElementException("Unable to find an element with Id '" + id.toString() + "'");
        }
    }

    protected Vertex createVertex(final Id id) throws Exception {
        try {
            return loadedVertices.get(id, new Callable<Vertex>() {
                @Override
                public Vertex call() throws Exception {
                    return getPersistenceBackend().addVertex(id.toString());
                }
            });
        } catch (Exception e) {
            throw new AlreadyExistingIdException("Already existing Id '" + id.toString() + "'");
        }
    }

    private void createRootVertex() throws Exception {
        // Create the 'ROOT' node with the default metaclass
        MetaClassifier metaClassifier = MetaClassifier.getDefault();

        Classifier rootClassifier = new Classifier(
                metaClassifier.getNamespace(),
                metaClassifier.getLocalName());

        rootClassifier.setId(Identifier.generated(ROOT_ID.toString()));
        rootClassifier.setClassName(metaClassifier.getLocalName());
        rootClassifier.setRoot(false);
        rootClassifier.setMetaClassifier(metaClassifier);

        Id id = createElement(rootClassifier, ROOT_ID);
        Id metaClassId = getOrCreateMetaClass(metaClassifier);

        setMetaClass(id, metaClassId);
    }

    private static void updateContainment(final String localName, final Vertex parentVertex, final Vertex childVertex) {
        for (Edge edge : childVertex.getEdges(Direction.OUT, CONTAINER)) {
            edge.remove();
        }

        Edge edge = childVertex.addEdge(CONTAINER, parentVertex);
        edge.setProperty(CONTAINING_FEATURE, localName);
    }

    private static Integer getSize(final Vertex vertex, final String name) {
        Integer size = vertex.getProperty(formatKeyValue(name, SIZE_LITERAL));
        return size != null ? size : 0;
    }

    private static void setSize(final Vertex vertex, final String name, final int size) {
        vertex.setProperty(formatKeyValue(name, SIZE_LITERAL), size);
    }

    private static String formatKeyValue(final String key, final Object value) {
        return key + SEPARATOR + value;
    }
}
