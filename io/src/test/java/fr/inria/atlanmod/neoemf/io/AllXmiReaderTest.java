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

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.MetaClassifier;
import fr.inria.atlanmod.neoemf.io.beans.Namespace;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.io.input.xmi.XmiStreamReader;
import fr.inria.atlanmod.neoemf.io.internal.InternalHandler;
import fr.inria.atlanmod.neoemf.io.internal.impl.DefaultInternalHandler;
import fr.inria.atlanmod.neoemf.io.mock.StructuralPersistanceHandler;
import fr.inria.atlanmod.neoemf.io.mock.beans.ClassifierMock;

import org.junit.Before;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

/**
 *
 */
public abstract class AllXmiReaderTest extends AllInputTest {

    @Before
    public void setUp() throws Exception {
        Namespace.Registry.getInstance().clean();
        persistanceHandler = read(sample);
    }

    protected void assertValidElement(final ClassifierMock mock, final String name, final int size, final String id) {
        assertThat(mock.getLocalName(), equalTo(name));
        assertThat(mock.getElements(), hasSize(size));

        if (id == null) {
            assertThat(mock.getId(), nullValue());
        } else {
            assertThat(mock.getId().getValue(), equalTo(id));
        }
    }

    protected void assertValidMetaClass(final MetaClassifier metaClassifier, final String name, final Namespace ns) {
        assertThat(metaClassifier.getLocalName(), equalTo(name));
        assertThat(metaClassifier.getNamespace(), is(ns));
    }

    protected void assertValidReference(final Reference reference, final String name, final int index, final String idReference) {
        assertThat(reference.getLocalName(), equalTo(name));
        assertThat(reference.getIndex(), is(index));
        assertThat(reference.getIdReference().getValue(), equalTo(idReference));
    }

    protected void assertValidAttribute(final Attribute attribute, final String name, final Object value) {
        assertThat(attribute.getLocalName(), equalTo(name));
        assertThat(attribute.getValue(), equalTo(value));
    }

    protected StructuralPersistanceHandler read(String filePath) throws Exception {
        StructuralPersistanceHandler persistanceHandler = new StructuralPersistanceHandler();

        XmiStreamReader reader = new XmiStreamReader();
        reader.setShowProgress(false);

        InternalHandler internalHandler = new DefaultInternalHandler();
        internalHandler.addHandler(persistanceHandler);

        reader.addHandler(internalHandler);
        reader.read(XmiStreamReaderTest.class.getResourceAsStream(filePath));

        return persistanceHandler;
    }
}
