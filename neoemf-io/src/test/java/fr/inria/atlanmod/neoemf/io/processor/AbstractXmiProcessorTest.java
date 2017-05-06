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
import fr.inria.atlanmod.neoemf.io.mock.DummyElement;
import fr.inria.atlanmod.neoemf.io.mock.DummyWriter;
import fr.inria.atlanmod.neoemf.io.reader.XmiStAXCursorStreamReader;
import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.structure.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;

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
        BasicNamespace.Registry.getInstance().clean();
    }

    protected void assertValidElement(DummyElement mock, String name, int size, String id, String className, boolean root) {
        assertThat(mock.name()).isEqualTo(name);
        assertThat(mock.children()).hasSize(size);
        assertThat(mock.className()).isEqualTo(className);
        assertThat(mock.isRoot()).isEqualTo(root);

        if (isNull(id)) {
            assertThat(mock.id()).isNull();
        }
        else {
            assertThat(mock.id().value()).isEqualTo(id);
        }
    }

    protected void assertValidMetaClass(BasicMetaclass metaClass, String name, BasicNamespace ns) {
        assertThat(metaClass.name()).isEqualTo(name);
        assertThat(metaClass.ns()).isSameAs(ns);
    }

    protected void assertValidReference(BasicReference reference, String name, int index, String idReference, boolean many, boolean containment) {
        assertThat(reference.name()).isEqualTo(name);
        assertThat(reference.index()).isEqualTo(index);
        assertThat(reference.idReference().value()).isEqualTo(idReference);
        assertThat(reference.isContainment()).isEqualTo(containment);
        assertThat(reference.isMany()).isEqualTo(many);
    }

    protected void assertValidAttribute(BasicAttribute attribute, String name, int index, Object value) {
        assertThat(attribute.name()).isEqualTo(name);
        assertThat(attribute.value()).isEqualTo(value);
        assertThat(attribute.index()).isEqualTo(index);
    }

    private DummyWriter read(File filePath) throws IOException {
        DummyWriter persistanceHandler = new DummyWriter();

        Processor processor = new DirectWriteProcessor(persistanceHandler);
        processor = new CounterProcessor(processor);
        processor = new TimerProcessor(processor);
        processor = new EcoreProcessor(processor);
        processor = new XPathProcessor(processor);

        new XmiStAXCursorStreamReader(processor).read(new FileInputStream(filePath));

        return persistanceHandler;
    }
}
