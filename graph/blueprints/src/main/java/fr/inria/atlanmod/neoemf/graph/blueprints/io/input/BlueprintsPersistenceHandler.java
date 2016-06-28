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

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.io.AlreadyExistingIdException;
import fr.inria.atlanmod.neoemf.io.UnknownReferencedIdException;
import fr.inria.atlanmod.neoemf.io.beans.MetaClassifier;
import fr.inria.atlanmod.neoemf.io.hash.HasherFactory;
import fr.inria.atlanmod.neoemf.io.impl.AbstractPersistenceHandler;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.eclipse.emf.ecore.InternalEObject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class BlueprintsPersistenceHandler extends AbstractPersistenceHandler<BlueprintsPersistenceBackend> {

    private static final char SEPARATOR = ':';
    private static final String POSITION = "position";
    private static final String CONTAINER = "eContainer";
    private static final String CONTAINING_FEATURE = "containingFeature";
    private static final String SIZE_LITERAL = "size";

    private static final Id ROOT_ID = new StringId("ROOT");
    private static final String ROOT_FEATURE_NAME = "eContents";

    private boolean isFirstElement = true;

    public BlueprintsPersistenceHandler(BlueprintsPersistenceBackend persistenceBackend) {
        super(persistenceBackend);
    }

    @Override
    protected Id hashId(String reference) {
        String hash = HasherFactory.md5().hash(reference).toString();
        return new StringId(hash);
    }

    @Override
    protected void addElement(Id id, String nsUri, String name, boolean root) throws Exception {
        if (isFirstElement) {
            // Create the 'ROOT' node with the default metaclass
            MetaClassifier metaClassifier = MetaClassifier.getDefault();

            Vertex vertex = getPersistenceBackend().addVertex(ROOT_ID.toString());
            vertex.setProperty(BlueprintsPersistenceBackend.ECLASS_NAME, metaClassifier.getLocalName());
            vertex.setProperty(BlueprintsPersistenceBackend.EPACKAGE_NSURI, metaClassifier.getNamespace().getUri());
            isFirstElement = false;

            NeoLogger.debug("Create the 'ROOT' node : {0}:{1}", metaClassifier.getNamespace().getUri(), metaClassifier.getLocalName());
        }

        Vertex vertex;
        try {
            vertex = getPersistenceBackend().addVertex(id.toString());
        } catch (IllegalArgumentException e) {
            throw new AlreadyExistingIdException();
        }
        vertex.setProperty(BlueprintsPersistenceBackend.ECLASS_NAME, name);
        vertex.setProperty(BlueprintsPersistenceBackend.EPACKAGE_NSURI, nsUri);

        if (root) {
            // Add the current element as content of the 'ROOT' node
            NeoLogger.debug("Defines {0}:{1} as content of the 'ROOT' node", nsUri, name);
            addReference(ROOT_ID, ROOT_FEATURE_NAME, -1, false, id);
        }
    }

    @Override
    protected void setMetaClass(Id id, Id metaClassId) {
        Vertex vertex = getPersistenceBackend().getVertex(id.toString());
        checkNotNull(vertex, "Unable to find an element with Id = " + id.toString());

        Vertex metaClassVertex = getPersistenceBackend().getVertex(metaClassId.toString());
        checkNotNull(vertex, "Unable to find metaclass with Id = " + metaClassId.toString());

        vertex.addEdge(BlueprintsPersistenceBackend.INSTANCE_OF, metaClassVertex);
    }

    @Override
    protected void addAttribute(Id id, String name, int index, String value) throws Exception {
        Vertex vertex = getPersistenceBackend().getVertex(id.toString());
        checkNotNull(vertex, "Unable to find an element with Id = " + id.toString());

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
        Vertex vertex = getPersistenceBackend().getVertex(id.toString());
        checkNotNull(vertex, "Unable to find an element with Id = " + id.toString());

        Vertex referencedVertex = getPersistenceBackend().getVertex(idReference.toString());
        if (referencedVertex == null) {
            throw new UnknownReferencedIdException();
        }

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
