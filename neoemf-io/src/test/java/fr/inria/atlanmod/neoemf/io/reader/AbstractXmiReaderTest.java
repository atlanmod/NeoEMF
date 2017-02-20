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

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.io.AbstractInputTest;
import fr.inria.atlanmod.neoemf.io.mock.StructuralPersistanceWriter;
import fr.inria.atlanmod.neoemf.io.mock.beans.ElementMock;
import fr.inria.atlanmod.neoemf.io.processor.DirectWriteProcessor;
import fr.inria.atlanmod.neoemf.io.processor.Processor;
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

public abstract class AbstractXmiReaderTest extends AbstractInputTest {

    @Before
    public void readResource() throws IOException {
        persistanceHandler = read(sample);
    }

    @After
    public final void unregisterNamespaces() {
        Namespace.Registry.getInstance().clean();
    }

    protected void assertValidElement(ElementMock mock, String name, int size, String id) {
        assertThat(mock.name()).isEqualTo(name);
        assertThat(mock.elements()).hasSize(size);

        if (isNull(id)) {
            assertThat(mock.id()).isNull();
        }
        else {
            assertThat(mock.id().value()).isEqualTo(id);
        }
    }

    protected void assertValidMetaClass(RawMetaclass metaClass, String name, Namespace ns) {
        assertThat(metaClass.name()).isEqualTo(name);
        assertThat(metaClass.ns()).isEqualTo(ns);
    }

    protected void assertValidReference(RawReference reference, String name, int index, String idReference) {
        assertThat(reference.name()).isEqualTo(name);
        assertThat(reference.index()).isEqualTo(index);
        assertThat(reference.idReference().value()).isEqualTo(idReference);
    }

    protected void assertValidAttribute(RawAttribute attribute, String name, Object value) {
        assertThat(attribute.name()).isEqualTo(name);
        assertThat(attribute.value()).isEqualTo(value);
    }

    protected StructuralPersistanceWriter read(File filePath) throws IOException {
        StructuralPersistanceWriter persistanceHandler = new StructuralPersistanceWriter();

        Processor processor = new DirectWriteProcessor(persistanceHandler);

        new XmiStAXCursorStreamReader(processor).read(new FileInputStream(filePath));

        return persistanceHandler;
    }
}
