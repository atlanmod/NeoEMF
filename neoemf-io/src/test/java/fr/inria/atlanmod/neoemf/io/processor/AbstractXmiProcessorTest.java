/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.AbstractInputTest;
import fr.inria.atlanmod.neoemf.io.mock.StructuralPersistanceHandler;
import fr.inria.atlanmod.neoemf.io.mock.beans.ClassifierMock;
import fr.inria.atlanmod.neoemf.io.persistence.PersistenceNotifier;
import fr.inria.atlanmod.neoemf.io.reader.Reader;
import fr.inria.atlanmod.neoemf.io.reader.XmiStAXCursorReader;
import fr.inria.atlanmod.neoemf.io.structure.RawMetaClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawNamespace;

import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.assertThat;

public class AbstractXmiProcessorTest extends AbstractInputTest {

    @Before
    public void readResource() throws IOException {
        persistanceHandler = read(sample);
    }

    @After
    public final void unregisterNamespaces() {
        RawNamespace.Registry.getInstance().clean();
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
            assertThat(mock.getId().value()).isEqualTo(id);
        }
    }

    protected void assertValidMetaClass(final RawMetaClassifier metaClassifier, final String name, final RawNamespace ns) {
        assertThat(metaClassifier.localName()).isEqualTo(name);
        assertThat(metaClassifier.namespace()).isSameAs(ns);
    }

    protected void assertValidReference(final RawReference reference, final String name, final int index, final String idReference, final boolean many, final boolean containment) {
        assertThat(reference.localName()).isEqualTo(name);
        assertThat(reference.index()).isEqualTo(index);
        assertThat(reference.idReference().value()).isEqualTo(idReference);
        assertThat(reference.containment()).isEqualTo(containment);
        assertThat(reference.many()).isEqualTo(many);
    }

    protected void assertValidAttribute(final RawAttribute attribute, final String name, final int index, final Object value) {
        assertThat(attribute.localName()).isEqualTo(name);
        assertThat(attribute.value()).isEqualTo(value);
        assertThat(attribute.index()).isEqualTo(index);
    }

    private StructuralPersistanceHandler read(File filePath) throws IOException {
        StructuralPersistanceHandler persistanceHandler = new StructuralPersistanceHandler();

        Reader reader = new XmiStAXCursorReader();

        Processor processor = new PersistenceNotifier();
        processor = new XPathProcessor(processor);
        processor = new EcoreProcessor(processor);
        processor.addHandler(persistanceHandler);

        reader.addHandler(processor);
        reader.read(new FileInputStream(filePath));

        return persistanceHandler;
    }
}
