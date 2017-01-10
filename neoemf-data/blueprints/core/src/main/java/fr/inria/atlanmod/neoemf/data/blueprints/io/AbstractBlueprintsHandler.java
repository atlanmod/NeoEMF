/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.blueprints.io;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.io.persistence.AbstractPersistenceHandler;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Identifier;
import fr.inria.atlanmod.neoemf.io.structure.MetaClassifier;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public abstract class AbstractBlueprintsHandler extends AbstractPersistenceHandler<BlueprintsPersistenceBackend> {

    private static final char SEPARATOR = ':';
    private static final String POSITION = "position";
    private static final String CONTAINER = "eContainer";
    private static final String CONTAINING_FEATURE = "containingFeature";
    private static final String SIZE_LITERAL = "size";

    private static final Id ROOT_ID = new StringId("ROOT");
    private static final String ROOT_FEATURE_NAME = "eContents";

    protected final Cache<Id, Vertex> verticesCache;

    public AbstractBlueprintsHandler(BlueprintsPersistenceBackend backend) {
        super(backend);
        this.verticesCache = Caffeine.newBuilder().maximumSize(DEFAULT_CACHE_SIZE).build();
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
        return isNull(size) ? 0 : size;
    }

    private static void setSize(final Vertex vertex, final String name, final int size) {
        vertex.setProperty(formatKeyValue(name, SIZE_LITERAL), size);
    }

    private static String formatKeyValue(final String key, final Object value) {
        return key + SEPARATOR + value;
    }

    @Override
    protected Id getId(final String reference) {
        return new StringId(reference);
    }

    @Override
    protected void addElement(final Id id, final String nsUri, final String name, final boolean root) {
        Vertex vertex = createVertex(id);

        // Checks if the Vertex is not already defined
        if (nonNull(vertex.getProperty(BlueprintsPersistenceBackend.KEY_EPACKAGE_NSURI))) {
            throw new IllegalArgumentException(
                    "An element with the same Id (" + id.toString() + ") is already defined. " +
                            "Use a handler with a conflicts resolution feature instead.");
        }

        if (nonNull(name)) {
            vertex.setProperty(BlueprintsPersistenceBackend.KEY_ECLASS_NAME, name);
        }
        vertex.setProperty(BlueprintsPersistenceBackend.KEY_EPACKAGE_NSURI, nsUri);

        if (root) {
            // Add the current element as content of the 'ROOT' node
            addReference(ROOT_ID, ROOT_FEATURE_NAME, PersistentStore.NO_INDEX, false, false, id);
        }
    }

    @Override
    protected void addAttribute(final Id id, final String name, int index, final boolean many, final Object value) {
        Vertex vertex = getVertex(id);

        int size = getSize(vertex, name);

        if (index == PersistentStore.NO_INDEX) {
            index = size;
        }

        size++;
        setSize(vertex, name, size);

        vertex.setProperty(many ? formatKeyValue(name, index) : name, value);
    }

    @Override
    protected void addReference(final Id id, final String name, int index, final boolean many, final boolean containment, final Id idReference) {
        Vertex vertex = getVertex(id);
        Vertex referencedVertex = getVertex(idReference);

        // Update the containment reference if needed
        if (containment) {
            updateContainment(name, vertex, referencedVertex);
        }

        int size = getSize(vertex, name);

        if (index == PersistentStore.NO_INDEX) {
            index = size;
        }

        size++;
        setSize(vertex, name, size);

        Edge edge = vertex.addEdge(name, referencedVertex);
        edge.setProperty(POSITION, index);
    }

    @Override
    protected void setMetaClass(final Id id, final Id metaClassId) {
        Vertex vertex = getVertex(id);
        Vertex metaClassVertex = getVertex(metaClassId);

        vertex.addEdge(BlueprintsPersistenceBackend.KEY_INSTANCE_OF, metaClassVertex);
    }

    @Override
    public void processStartDocument() {
        createRootVertex();

        super.processStartDocument();
    }

    protected abstract Vertex getVertex(final Id id);

    protected abstract Vertex createVertex(final Id id);

    private void createRootVertex() {
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
}
