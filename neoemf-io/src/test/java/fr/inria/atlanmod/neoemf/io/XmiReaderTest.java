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

import fr.inria.atlanmod.neoemf.Tags;
import fr.inria.atlanmod.neoemf.io.mock.DummyElement;
import fr.inria.atlanmod.neoemf.io.reader.XmiStreamReader;
import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class XmiReaderTest extends AbstractInputTest {

    @Override
    @Before
    public void readResource() throws IOException {
        registerPackageFromEcore("java", "http://www.eclipse.org/MoDisco/Java/0.2.incubation/java");
        this.sample = getXmiStandard();
        super.readResource();
    }

    /**
     * Check that the namespaces are properly processed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testNamespaces() {
        BasicNamespace.Registry nsRegistry = BasicNamespace.Registry.getInstance();
        Iterable<String> prefixes = nsRegistry.getPrefixes();
        assertThat(prefixes).containsExactlyInAnyOrder("xsi", "java", "xmi");

        assertThat(root.ns()).isNotNull();
        assertThat(root.ns().prefix()).isEqualTo("java");
    }

    /**
     * Check that the elements and their children are properly processed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testElementsAndChildren() {
        DummyElement mock;
        DummyElement mockChild;

        assertValidElement(root, "/@Model.0", "Model", "fr.inria.atlanmod.kyanos.tests", 19);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = DummyElement.from(root, 0, 0, 0, 0, 0, 0);
            assertValidElement(mock, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0", "ownedElements", "TestCreateResource", 7);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = DummyElement.from(mock, 0);
                assertValidElement(mockChild, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@modifier.0", "modifier", null, 0);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = DummyElement.from(mock, 3);
                assertValidElement(mockChild, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2", "bodyDeclarations", "tearDownAfterClass", 5);
            }

            //@Model/@ownedElements.1
            mock = DummyElement.from(root, 1);
            assertValidElement(mock, "/@Model.0/@ownedElements.1", "ownedElements", "java", 5);

            //@Model/@orphanTypes.5
            mock = DummyElement.from(root, 8);
            assertValidElement(mock, "/@Model.0/@orphanTypes.5", "orphanTypes", "void", 0);

            //@Model/@compilationUnits.1
            mock = DummyElement.from(root, 17);
            assertValidElement(mock, "/@Model.0/@compilationUnits.1", "compilationUnits", "TestXmi.java", 16);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = DummyElement.from(mock, 2);
                assertValidElement(mockChild, "/@Model.0/@compilationUnits.1/@imports.2", "imports", null, 0);
            }
        }
    }

    /**
     * Check that the attributes are properly processed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testAttributes() {
        DummyElement mock;
        DummyElement mockChild;

        List<BasicAttribute> attributeList;

        attributeList = root.attributes();
        assertThat(attributeList).isEmpty(); // Assert that 'xmi:version' and 'xmlns' don't exist
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = DummyElement.from(root, 0, 0, 0, 0, 0, 0);
            attributeList = mock.attributes();
            assertThat(attributeList).isEmpty();
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = DummyElement.from(mock, 0);
                attributeList = mockChild.attributes();
                assertThat(attributeList).hasSize(1);
                assertValidAttribute(attributeList.get(0), "visibility", "public", 0);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = DummyElement.from(mock, 3);
                attributeList = mockChild.attributes();
                assertThat(attributeList).isEmpty();
            }

            //@Model/@ownedElements.1
            mock = DummyElement.from(root, 1);
            attributeList = mock.attributes();
            assertThat(attributeList).hasSize(1);
            assertValidAttribute(attributeList.get(0), "proxy", "true", 0);

            //@Model/@orphanTypes.5
            mock = DummyElement.from(root, 8);
            attributeList = mock.attributes();
            assertThat(attributeList).isEmpty();

            //@Model/@compilationUnits.1
            mock = DummyElement.from(root, 17);
            attributeList = mock.attributes();
            assertThat(attributeList).hasSize(1);
            assertValidAttribute(attributeList.get(0), "originalFilePath", "C:\\Eclipse\\eclipse-SDK-4.3.1-win32-x86_64-Blue\\eclipse\\workspace\\fr.inria.atlanmod.kyanos.tests\\src\\fr\\inria\\atlanmod\\kyanos\\tests\\TestXmi.java", 0);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = DummyElement.from(mock, 2);
                assertThat(mockChild.attributes()).isEmpty();
            }
        }
    }

    /**
     * Check that the XPath references are properly processed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testReferences() {
        DummyElement mock;
        DummyElement mockChild;

        List<BasicReference> referenceList;

        referenceList = root.references();
        assertThat(referenceList).hasSize(19); // Now contains containment
        assertValidReference(referenceList.get(0), "ownedElements", "/@Model.0/@ownedElements.0", UNKNOWN_INDEX, true, true);
        assertValidReference(referenceList.get(12), "orphanTypes", "/@Model.0/@orphanTypes.9", UNKNOWN_INDEX, true, true);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = DummyElement.from(root, 0, 0, 0, 0, 0, 0);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(8); // Now contains containment
            assertValidReference(referenceList.get(0), "originalCompilationUnit", "/@Model.0/@compilationUnits.0", 0, false, false);
            assertValidReference(referenceList.get(5), "bodyDeclarations", "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.3", UNKNOWN_INDEX, true, true);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = DummyElement.from(mock, 0);
                assertThat(mockChild.references()).isEmpty();

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = DummyElement.from(mock, 3);
                referenceList = mockChild.references();
                assertThat(referenceList).hasSize(6); // Now contains containment
                assertValidReference(referenceList.get(0), "originalCompilationUnit", "/@Model.0/@compilationUnits.0", 0, false, false);
                assertValidReference(referenceList.get(2), "modifier", "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2/@modifier.0", UNKNOWN_INDEX, false, true);
            }

            //@Model/@ownedElements.1
            mock = DummyElement.from(root, 1);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(5); // Now contains containment
            assertValidReference(referenceList.get(1), "ownedPackages", "/@Model.0/@ownedElements.1/@ownedPackages.1", UNKNOWN_INDEX, true, true);

            //@Model/@orphanTypes.5
            mock = DummyElement.from(root, 8);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(12);
            assertValidReference(referenceList.get(0), "usagesInTypeAccess", "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.1/@returnType.0", 0, true, false);
            assertValidReference(referenceList.get(9), "usagesInTypeAccess", "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.1/@bodyDeclarations.5/@returnType.0", 9, true, false);

            //@Model/@compilationUnits.1
            mock = DummyElement.from(root, 17);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(18); // Now contains containment
            assertValidReference(referenceList.get(0), "package", "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0", 0, false, false);
            assertValidReference(referenceList.get(3), "imports", "/@Model.0/@compilationUnits.1/@imports.1", UNKNOWN_INDEX, true, true);
            assertValidReference(referenceList.get(12), "imports", "/@Model.0/@compilationUnits.1/@imports.10", UNKNOWN_INDEX, true, true);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = DummyElement.from(mock, 2);
                referenceList = mockChild.references();
                assertThat(referenceList).hasSize(2);
                assertValidReference(referenceList.get(0), "originalCompilationUnit", "/@Model.0/@compilationUnits.1", 0, false, false);
                assertValidReference(referenceList.get(1), "importedElement", "/@Model.0/@ownedElements.2/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0", 0, false, false);
            }
        }
    }

    /**
     * Check that the metaclasses ('xsi:type' or 'xmi:type') are properly processed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testMetaClasses() {
        DummyElement mock;
        DummyElement mockChild;

        BasicNamespace ns = root.ns();
        assertValidMetaClass(root.metaClass(), "Model", ns);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = DummyElement.from(root, 0, 0, 0, 0, 0, 0);
            assertValidMetaClass(mock.metaClass(), "ClassDeclaration", ns);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = DummyElement.from(mock, 0);
                assertValidMetaClass(mockChild.metaClass(), "Modifier", ns);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = DummyElement.from(mock, 3);
                assertValidMetaClass(mockChild.metaClass(), "MethodDeclaration", ns);
            }

            //@Model/@ownedElements.1
            mock = DummyElement.from(root, 1);
            assertValidMetaClass(mock.metaClass(), "Package", ns);

            //@Model/@orphanTypes.5
            mock = DummyElement.from(root, 8);
            assertValidMetaClass(mock.metaClass(), "PrimitiveTypeVoid", ns);

            //@Model/@compilationUnits.1
            mock = DummyElement.from(root, 17);
            assertValidMetaClass(mock.metaClass(), "CompilationUnit", ns);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = DummyElement.from(mock, 2);
                assertValidMetaClass(mockChild.metaClass(), "ImportDeclaration", ns);
            }
        }
    }

    @Test
    @Category(Tags.IOTests.class)
    public void testMalformedMetaClass() {
        InputStream stream = XmiReaderTest.class.getResourceAsStream("/io/xmi/sampleMalformedMetaClass.xmi");

        assertThat(
                catchThrowable(() -> read(stream))
        ).isInstanceOf(IOException.class).hasCauseInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Check if the reader stop its execution if it hasn't any handler.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testReaderWithoutHandler() {
        //noinspection ConstantConditions
        assertThat(
                catchThrowable(() -> new XmiStreamReader(null).read(null))
        ).isInstanceOf(NullPointerException.class);
    }
}
