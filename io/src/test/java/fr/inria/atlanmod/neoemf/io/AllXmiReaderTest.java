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

import java.io.File;
import java.io.FileInputStream;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(mock.getLocalName()).isEqualTo(name);
        assertThat(mock.getElements()).hasSize(size);

        if (id == null) {
            assertThat(mock.getId()).isNull();
        } else {
            assertThat(mock.getId().getValue()).isEqualTo(id);
        }
    }

    protected void assertValidMetaClass(final MetaClassifier metaClassifier, final String name, final Namespace ns) {
        assertThat(metaClassifier.getLocalName()).isEqualTo(name);
        assertThat(metaClassifier.getNamespace()).isEqualTo(ns);
    }

    protected void assertValidReference(final Reference reference, final String name, final int index, final String idReference) {
        assertThat(reference.getLocalName()).isEqualTo(name);
        assertThat(reference.getIndex()).isEqualTo(index);
        assertThat(reference.getIdReference().getValue()).isEqualTo(idReference);
    }

    protected void assertValidAttribute(final Attribute attribute, final String name, final Object value) {
        assertThat(attribute.getLocalName()).isEqualTo(name);
        assertThat(attribute.getValue()).isEqualTo(value);
    }

    protected StructuralPersistanceHandler read(File filePath) throws Exception {
        StructuralPersistanceHandler persistanceHandler = new StructuralPersistanceHandler();

        XmiStreamReader reader = new XmiStreamReader();
        reader.setShowProgress(false);

        InternalHandler internalHandler = new DefaultInternalHandler();
        internalHandler.addHandler(persistanceHandler);

        reader.addHandler(internalHandler);
        reader.read(new FileInputStream(filePath));

        return persistanceHandler;
    }
}
