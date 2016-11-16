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

import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.MetaClassifier;
import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.Reference;
import fr.inria.atlanmod.neoemf.io.processor.impl.XPathProcessor;
import fr.inria.atlanmod.neoemf.io.reader.xmi.XmiStreamReader;
import fr.inria.atlanmod.neoemf.io.processor.Processor;
import fr.inria.atlanmod.neoemf.io.processor.impl.EcoreProcessor;
import fr.inria.atlanmod.neoemf.io.mock.StructuralPersistanceHandler;
import fr.inria.atlanmod.neoemf.io.mock.beans.ClassifierMock;

import org.junit.Before;

import java.io.File;
import java.io.FileInputStream;

import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class AllXmiProcessorTest extends AllInputTest {

    @Before
    public void setUp() throws Exception {
        Namespace.Registry.getInstance().clean();
        persistanceHandler = read(sample);
    }

    protected void assertValidElement(final ClassifierMock mock, final String name, final int size, final String id, final String className, final boolean root) {
        assertThat(mock.getLocalName()).isEqualTo(name);
        assertThat(mock.getElements()).hasSize(size);
        assertThat(mock.getClassName()).isEqualTo(className);
        assertThat(mock.isRoot()).isEqualTo(root);

        if (isNull(id)) {
            assertThat(mock.getId()).isNull();
        }
        else {
            assertThat(mock.getId().getValue()).isEqualTo(id);
        }
    }

    protected void assertValidMetaClass(final MetaClassifier metaClassifier, final String name, final Namespace ns) {
        assertThat(metaClassifier.getLocalName()).isEqualTo(name);
        assertThat(metaClassifier.getNamespace()).isSameAs(ns);
    }

    protected void assertValidReference(final Reference reference, final String name, final int index, final String idReference, final boolean many, final boolean containment) {
        assertThat(reference.getLocalName()).isEqualTo(name);
        assertThat(reference.getIndex()).isEqualTo(index);
        assertThat(reference.getIdReference().getValue()).isEqualTo(idReference);
        assertThat(reference.isContainment()).isEqualTo(containment);
        assertThat(reference.isMany()).isEqualTo(many);
    }

    protected void assertValidAttribute(final Attribute attribute, final String name, final int index, final Object value) {
        assertThat(attribute.getLocalName()).isEqualTo(name);
        assertThat(attribute.getValue()).isEqualTo(value);
        assertThat(attribute.getIndex()).isEqualTo(index);
    }

    private StructuralPersistanceHandler read(File filePath) throws Exception {
        StructuralPersistanceHandler persistanceHandler = new StructuralPersistanceHandler();

        XmiStreamReader reader = new XmiStreamReader();
        reader.setShowProgress(false);

        Processor processor = new PersistenceNotifier();
        processor = new XPathProcessor(processor);
        processor = new EcoreProcessor(processor);
        processor.addHandler(persistanceHandler);

        reader.addHandler(processor);
        reader.read(new FileInputStream(filePath));

        return persistanceHandler;
    }
}
