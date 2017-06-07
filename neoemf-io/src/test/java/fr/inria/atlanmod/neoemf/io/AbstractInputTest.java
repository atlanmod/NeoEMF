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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractInputTest extends AbstractTest {

    protected static final int UNKNOWN_INDEX = -1;

    /**
     * The root element of the read file.
     */
    protected DummyElement root;

    /**
     * The XMI file to read.
     */
    protected InputStream sample;

    /**
     * Returns the XMI file that uses XPath references.
     *
     * @return the XMI file
     */
    protected static InputStream getXmiStandard() {
        return AbstractInputTest.class.getResourceAsStream("/io/xmi/sampleStandard.xmi");
    }

    /**
     * Returns the XMI file that uses {@code xmi:id} references.
     *
     * @return the XMI file
     */
    protected static InputStream getXmiWithId() {
        return AbstractInputTest.class.getResourceAsStream("/io/xmi/sampleWithId.xmi");
    }

    /**
     * Registers a EPackage in {@link Registry} according to its {@code prefix} and {@code uri}, from an Ecore file.
     * <p>
     * The targeted Ecore file must be present in {@code /resources/ecore}.
     */
    protected static void registerPackageFromEcore(String prefix, String uri) {
        File file = new File(AbstractInputTest.class.getResource("/io/ecore/{name}.ecore".replaceAll("\\{name\\}", prefix)).getFile());

        EPackage ePackage = null;

        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

        ResourceSet rs = new ResourceSetImpl();

        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(rs.getPackageRegistry());
        rs.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

        Resource r = rs.getResource(URI.createFileURI(file.toString()), true);
        EObject eObject = r.getContents().get(0);
        if (EPackage.class.isInstance(eObject)) {
            ePackage = EPackage.class.cast(eObject);
            rs.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
        }

        assertThat(ePackage).isNotNull();

        Registry.INSTANCE.put(uri, ePackage);
    }

    /**
     * Reads the given XMI {@code file} and returns the analyzed structure.
     *
     * @param stream a stream containing the XMI structure to read
     *
     * @return the structure of the XMI file
     *
     * @throws IOException if an error occurred during the I/O process
     */
    protected static DummyElement read(InputStream stream) throws IOException {
        DummyWriter writer = new DummyWriter();

        Processor processor = new DirectWriteProcessor(writer);
        processor = new CounterProcessor(processor);
        processor = new TimerProcessor(processor);
        processor = new EcoreProcessor(processor);
        processor = new XPathProcessor(processor);

        new XmiStreamReader(processor).read(stream);

        return writer.getRoot();
    }

    @Before
    public void readResource() throws IOException {
        root = read(sample);
        assertThat(root).isNotNull();
    }

    @After
    public final void unregisterNamespaces() {
        BasicNamespace.Registry.getInstance().clean();
    }

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
