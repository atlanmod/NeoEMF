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
import fr.inria.atlanmod.neoemf.io.mock.beans.ElementMock;
import fr.inria.atlanmod.neoemf.io.reader.Reader;
import fr.inria.atlanmod.neoemf.io.reader.XmiStAXCursorReader;
import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawMetaclass;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;

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
        Namespace.Registry.getInstance().clean();
    }

    protected void assertValidElement(final ElementMock mock, final String name, final int size, final String id, final String className, final boolean root) {
        assertThat(mock.name()).isEqualTo(name);
        assertThat(mock.elements()).hasSize(size);
        assertThat(mock.className()).isEqualTo(className);
        assertThat(mock.isRoot()).isEqualTo(root);

        if (isNull(id)) {
            assertThat(mock.id()).isNull();
        }
        else {
            assertThat(mock.id().value()).isEqualTo(id);
        }
    }

    protected void assertValidMetaClass(final RawMetaclass metaClass, final String name, final Namespace ns) {
        assertThat(metaClass.name()).isEqualTo(name);
        assertThat(metaClass.ns()).isSameAs(ns);
    }

    protected void assertValidReference(final RawReference reference, final String name, final int index, final String idReference, final boolean many, final boolean containment) {
        assertThat(reference.name()).isEqualTo(name);
        assertThat(reference.index()).isEqualTo(index);
        assertThat(reference.idReference().value()).isEqualTo(idReference);
        assertThat(reference.containment()).isEqualTo(containment);
        assertThat(reference.many()).isEqualTo(many);
    }

    protected void assertValidAttribute(final RawAttribute attribute, final String name, final int index, final Object value) {
        assertThat(attribute.name()).isEqualTo(name);
        assertThat(attribute.value()).isEqualTo(value);
        assertThat(attribute.index()).isEqualTo(index);
    }

    private StructuralPersistanceHandler read(File filePath) throws IOException {
        StructuralPersistanceHandler persistanceHandler = new StructuralPersistanceHandler();

        Processor processor = new PersistenceProcessor(persistanceHandler);
        processor = new CounterProcessor(processor);
        processor = new TimerProcessor(processor);
        processor = new EcoreProcessor(processor);
        processor = new XPathProcessor(processor);

        Reader reader = new XmiStAXCursorReader(processor);
        reader.read(new FileInputStream(filePath));

        return persistanceHandler;
    }
}
