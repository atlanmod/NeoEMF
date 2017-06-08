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

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.AbstractTest;
import fr.inria.atlanmod.neoemf.io.mock.DummyElement;
import fr.inria.atlanmod.neoemf.io.mock.DummyWriter;
import fr.inria.atlanmod.neoemf.io.processor.CounterProcessor;
import fr.inria.atlanmod.neoemf.io.processor.DirectWriteProcessor;
import fr.inria.atlanmod.neoemf.io.processor.EcoreProcessor;
import fr.inria.atlanmod.neoemf.io.processor.Processor;
import fr.inria.atlanmod.neoemf.io.processor.TimerProcessor;
import fr.inria.atlanmod.neoemf.io.processor.XPathProcessor;
import fr.inria.atlanmod.neoemf.io.reader.XmiStreamReader;
import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.structure.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;

import org.junit.Before;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nonnull;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractInputTest extends AbstractTest {

    /**
     * The root element of the read file.
     */
    protected DummyElement root;

    /**
     * Retrieves an element from the {@code root} element with the successive {@code indices}.
     *
     * @param root    the element from which to start the search
     * @param indices the index of the element, recursively in the children from the {@code root}
     *
     * @return the element
     */
    @Nonnull
    public static DummyElement childFrom(DummyElement root, int... indices) {
        checkArgument(indices.length > 0, "You must define at least one index");

        DummyElement child = root;

        for (int index : indices) {
            child = child.children().get(index);
        }

        return checkNotNull(child);
    }

    @BeforeClass
    public static void registerPackages() {
        IOResourceManager.registerPackage("java", "http://www.eclipse.org/MoDisco/Java/0.2.incubation/java");
        IOResourceManager.registerPackage("uml", "http://schema.omg.org/spec/UML/2.1");
    }

    @Before
    public void readResource() throws IOException {
        DummyWriter writer = new DummyWriter();

        Processor processor = new DirectWriteProcessor(writer);
        processor = new CounterProcessor(processor);
        processor = new TimerProcessor(processor);
        processor = new EcoreProcessor(processor);
        processor = new XPathProcessor(processor);

        new XmiStreamReader(processor).read(getSample());

        root = checkNotNull(writer.getRoot());
    }

    /**
     * Returns an {@link InputStream} on the XMI file to use for this test-case.
     *
     * @return the stream
     */
    @Nonnull
    protected abstract InputStream getSample();

    /**
     * Checks that the {@code element} has the given arguments.
     *
     * @param element   the element to test
     * @param id        the expected identifier
     * @param name      the expected name
     * @param className the expected class name
     * @param size      the expected size
     */
    protected void assertValidElement(DummyElement element, String id, String name, String className, int size) {
        assertThat(element.id().value()).isEqualTo(id);
        assertThat(element.name()).isEqualTo(name);
        assertThat(element.children()).hasSize(size);
        assertThat(element.className()).isEqualTo(className);
    }

    /**
     * Checks that the {@code metaclass} has the given arguments.
     *
     * @param metaclass the metaclass to test
     * @param name      the expected name
     * @param ns        the expected namespace URI
     */
    protected void assertValidMetaClass(BasicMetaclass metaclass, String name, BasicNamespace ns) {
        assertThat(metaclass.name()).isEqualTo(name);
        assertThat(metaclass.ns()).isSameAs(ns);
    }

    /**
     * Checks that the {@code reference} has the given arguments.
     *
     * @param reference     the reference to test
     * @param name          the expected name
     * @param value         the expected value
     * @param index         the expected index
     * @param isMany        {@code true} if the {@code reference} is multi-valued
     * @param isContainment {@code true} if the {@code reference} is a containment
     */
    protected void assertValidReference(BasicReference reference, String name, String value, int index, boolean isMany, boolean isContainment) {
        assertThat(reference.name()).isEqualTo(name);
        assertThat(reference.index()).isEqualTo(index);
        assertThat(reference.idReference().value()).isEqualTo(value);
        assertThat(reference.isContainment()).isEqualTo(isContainment);
        assertThat(reference.isMany()).isEqualTo(isMany);
    }

    /**
     * Checks that the {@code attribute} has the given arguments.
     *
     * @param attribute the attribute to test
     * @param name      the expected name
     * @param value     the expected value
     * @param index     the expected index
     */
    protected void assertValidAttribute(BasicAttribute attribute, String name, Object value, int index) {
        assertThat(attribute.name()).isEqualTo(name);
        assertThat(attribute.value()).isEqualTo(value);
        assertThat(attribute.index()).isEqualTo(index);
    }
}
