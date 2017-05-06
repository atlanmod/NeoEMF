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

import fr.inria.atlanmod.neoemf.Tags;
import fr.inria.atlanmod.neoemf.io.mock.DummyElement;
import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * Tests that verify that the XMI reader properly interprets the read data.
 */
public class XmiStreamReaderTest extends AbstractXmiReaderTest {

    @Override
    @Before
    public void readResource() throws IOException {
        this.sample = getXmiStandard();
        super.readResource();
    }

    /**
     * Check that the namespaces are properly read.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testNamespaces() {
        BasicNamespace.Registry nsRegistry = BasicNamespace.Registry.getInstance();
        Iterable<String> prefixes = nsRegistry.getPrefixes();
        assertThat(prefixes).containsExactlyInAnyOrder("xsi", "java", "xmi");

        DummyElement root = persistanceHandler.getRoot();
        assertThat(root.ns()).isNotNull();
        assertThat(root.ns().prefix()).isEqualTo("java");
    }

    /**
     * Check that the elements and their children are properly read.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testElementsAndChildren() {
        assertThat(persistanceHandler.getRoot()).isNotNull();

        DummyElement mock;
        DummyElement mockChild;

        DummyElement root = persistanceHandler.getRoot();
        assertValidElement(root, "Model", 19, null);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = DummyElement.childFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidElement(mock, "ownedElements", 7, null);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = DummyElement.childFrom(mock, 0);
                assertValidElement(mockChild, "modifier", 0, null);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = DummyElement.childFrom(mock, 3);
                assertValidElement(mockChild, "bodyDeclarations", 5, null);
            }

            //@Model/@ownedElements.1
            mock = DummyElement.childFrom(root, 1);
            assertValidElement(mock, "ownedElements", 5, null);

            //@Model/@orphanTypes.5
            mock = DummyElement.childFrom(root, 8);
            assertValidElement(mock, "orphanTypes", 0, null);

            //@Model/@compilationUnits.1
            mock = DummyElement.childFrom(root, 17);
            assertValidElement(mock, "compilationUnits", 16, null);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = DummyElement.childFrom(mock, 2);
                assertValidElement(mockChild, "imports", 0, null);
            }
        }
    }

    /**
     * Check that the attributes are properly read.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testAttributes() {
        DummyElement mock;
        DummyElement mockChild;

        List<BasicAttribute> attributeList;

        DummyElement root = persistanceHandler.getRoot();
        assertThat(root.attributes()).isEmpty(); // Assert that 'xmi:version' and 'xmlns' don't exist
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = DummyElement.childFrom(root, 0, 0, 0, 0, 0, 0);
            attributeList = mock.attributes();
            assertThat(attributeList).isEmpty();
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = DummyElement.childFrom(mock, 0);
                attributeList = mockChild.attributes();
                assertThat(attributeList).hasSize(1);
                assertValidAttribute(attributeList.get(0), "visibility", "public");

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = DummyElement.childFrom(mock, 3);
                attributeList = mockChild.attributes();
                assertThat(attributeList).isEmpty();
            }

            //@Model/@ownedElements.1
            mock = DummyElement.childFrom(root, 1);
            attributeList = mock.attributes();
            assertThat(attributeList).hasSize(1);
            assertValidAttribute(attributeList.get(0), "proxy", "true");

            //@Model/@orphanTypes.5
            mock = DummyElement.childFrom(root, 8);
            attributeList = mock.attributes();
            assertThat(attributeList).isEmpty();

            //@Model/@compilationUnits.1
            mock = DummyElement.childFrom(root, 17);
            attributeList = mock.attributes();
            assertThat(attributeList).hasSize(1);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = DummyElement.childFrom(mock, 2);
                assertThat(mockChild.attributes()).isEmpty();
            }
        }
    }

    /**
     * Check that the XPath references are properly read.
     * Several references can be present in only one XML attribute.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testReferences() {
        DummyElement mock;
        DummyElement mockChild;

        List<BasicReference> referenceList;

        DummyElement root = persistanceHandler.getRoot();
        assertThat(root.references()).isEmpty();
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = DummyElement.childFrom(root, 0, 0, 0, 0, 0, 0);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(1);
            assertValidReference(referenceList.get(0), "originalCompilationUnit", 0, "//@compilationUnits.0");
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = DummyElement.childFrom(mock, 0);
                assertThat(mockChild.references()).isEmpty();

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = DummyElement.childFrom(mock, 3);
                referenceList = mockChild.references();
                assertThat(referenceList).hasSize(1);
                assertValidReference(referenceList.get(0), "originalCompilationUnit", 0, "//@compilationUnits.0");
            }

            //@Model/@ownedElements.1
            mock = DummyElement.childFrom(root, 1);
            assertThat(mock.references()).isEmpty();

            //@Model/@orphanTypes.5
            mock = DummyElement.childFrom(root, 8);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(12);
            assertValidReference(referenceList.get(0), "usagesInTypeAccess", 0, "//@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.1/@returnType");
            assertValidReference(referenceList.get(9), "usagesInTypeAccess", 9, "//@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.1/@bodyDeclarations.5/@returnType");

            //@Model/@compilationUnits.1
            mock = DummyElement.childFrom(root, 17);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(2);
            assertValidReference(referenceList.get(0), "package", 0, "//@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0");
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = DummyElement.childFrom(mock, 2);
                referenceList = mockChild.references();
                assertThat(referenceList).hasSize(2);
                assertValidReference(referenceList.get(0), "originalCompilationUnit", 0, "//@compilationUnits.1");
                assertValidReference(referenceList.get(1), "importedElement", 0, "//@ownedElements.2/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0");
            }
        }
    }

    /**
     * Check that the metaclasses ('xsi:type' or 'xmi:type') are properly read.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testMetaClasses() {
        DummyElement mock;
        DummyElement mockChild;

        DummyElement root = persistanceHandler.getRoot();
        BasicNamespace ns = root.ns();
        assertThat(root.metaClass()).isNull();
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = DummyElement.childFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidMetaClass(mock.metaClass(), "ClassDeclaration", ns);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = DummyElement.childFrom(mock, 0);
                assertThat(mockChild.metaClass()).isNull();

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = DummyElement.childFrom(mock, 3);
                assertValidMetaClass(mockChild.metaClass(), "MethodDeclaration", ns);
            }

            //@Model/@ownedElements.1
            mock = DummyElement.childFrom(root, 1);
            assertThat(mock.metaClass()).isNull();

            //@Model/@orphanTypes.5
            mock = DummyElement.childFrom(root, 8);
            assertValidMetaClass(mock.metaClass(), "PrimitiveTypeVoid", ns);

            //@Model/@compilationUnits.1
            mock = DummyElement.childFrom(root, 17);
            assertThat(mock.metaClass()).isNull();
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = DummyElement.childFrom(mock, 2);
                assertThat(mockChild.metaClass()).isNull();
            }
        }
    }

    @Test
    @Category(Tags.IOTests.class)
    public void testMalformedMetaClass() {
        Throwable thrown = catchThrowable(() -> read(getResourceFile("/io/xmi/sampleMalformedMetaClass.xmi")));
        assertThat(thrown).isInstanceOf(IOException.class);
        assertThat(thrown).hasCauseInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Check if the reader stop its execution if it hasn't any handler.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testReaderWithoutHandler() {
        Throwable thrown = catchThrowable(() -> new XmiStAXCursorStreamReader(null).read(null));
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }
}
