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

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.io.AlreadyExistingIdException;
import fr.inria.atlanmod.neoemf.io.UnknownReferencedIdException;
import fr.inria.atlanmod.neoemf.io.hash.HasherFactory;
import fr.inria.atlanmod.neoemf.io.impl.AbstractPersistenceHandler;

import org.eclipse.emf.ecore.InternalEObject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class BlueprintsPersistenceHandler extends AbstractPersistenceHandler<BlueprintsPersistenceBackend> {

    public BlueprintsPersistenceHandler(BlueprintsPersistenceBackend persistenceBackend) {
        super(persistenceBackend);
    }

    @Override
    protected Id hashId(String reference) {
        String hash = HasherFactory.md5().hash(reference).toString();
        return new StringId(hash);
    }

    @Override
    protected void addElement(Id id, String nsUri, String name) throws Exception {
        Vertex vertex;
        try {
            vertex = getPersistenceBackend().addVertex(id.toString());
        } catch (IllegalArgumentException e) {
            throw new AlreadyExistingIdException();
        }
        vertex.setProperty(BlueprintsPersistenceBackend.ECLASS_NAME, name);
        vertex.setProperty(BlueprintsPersistenceBackend.EPACKAGE_NSURI, nsUri);
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
    protected void addAttribute(Id id, String nsUri, String name, int index, String value) throws Exception {
        Vertex vertex = getPersistenceBackend().getVertex(id.toString());
        checkNotNull(vertex, "Unable to find an element with Id = " + id.toString());

        int size = getSize(vertex, name);

        if (index == InternalEObject.EStore.NO_INDEX) {
            index = size;
        }

        size++;
        setSize(vertex, name, size);

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

        if (index == InternalEObject.EStore.NO_INDEX) {
            index = size;
        }

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
