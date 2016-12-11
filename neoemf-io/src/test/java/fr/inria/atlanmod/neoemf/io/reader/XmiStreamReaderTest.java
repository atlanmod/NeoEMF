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

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.io.mock.beans.ClassifierMock;
import fr.inria.atlanmod.neoemf.io.reader.xmi.XmiStreamReader;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * Tests that verify that the {@link XmiStreamReader} properly interprets the read data.
 */
public class XmiStreamReaderTest extends AllXmiReaderTest {

    @Override
    @Before
    public void setUp() throws Exception {
        this.sample = getXmiStandard();

        super.setUp();
    }

    /**
     * Check that the namespaces are properly read.
     */
    @Test
    public void testNamespaces() throws Exception {
        Namespace.Registry nsRegistry = Namespace.Registry.getInstance();
        Iterable<String> prefixes = nsRegistry.getPrefixes();
        assertThat(prefixes).containsExactlyInAnyOrder("xsi", "java", "xmi");

        ClassifierMock root = persistanceHandler.getElements().get(0);
        assertThat(root.getNamespace()).isNotNull();
        assertThat(root.getNamespace().getPrefix()).isEqualTo("java");
    }

    /**
     * Check that the elements and their children are properly read.
     */
    @Test
    public void testElementsAndChildren() throws Exception {
        assertThat(persistanceHandler.getElements()).isNotEmpty();

        ClassifierMock mock;
        ClassifierMock mockChild;

        ClassifierMock root = persistanceHandler.getElements().get(0);
        assertValidElement(root, "Model", 19, null);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = ClassifierMock.getChildFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidElement(mock, "ownedElements", 7, null);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = ClassifierMock.getChildFrom(mock, 0);
                assertValidElement(mockChild, "modifier", 0, null);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = ClassifierMock.getChildFrom(mock, 3);
                assertValidElement(mockChild, "bodyDeclarations", 5, null);
            }

            //@Model/@ownedElements.1
            mock = ClassifierMock.getChildFrom(root, 1);
            assertValidElement(mock, "ownedElements", 5, null);

            //@Model/@orphanTypes.5
            mock = ClassifierMock.getChildFrom(root, 8);
            assertValidElement(mock, "orphanTypes", 0, null);

            //@Model/@compilationUnits.1
            mock = ClassifierMock.getChildFrom(root, 17);
            assertValidElement(mock, "compilationUnits", 16, null);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = ClassifierMock.getChildFrom(mock, 2);
                assertValidElement(mockChild, "imports", 0, null);
            }
        }
    }

    /**
     * Check that the attributes are properly read.
     */
    @Test
    public void testAttributes() throws Exception {
        ClassifierMock mock;
        ClassifierMock mockChild;

        List<Attribute> attributeList;

        ClassifierMock root = persistanceHandler.getElements().get(0);
        assertThat(root.getAttributes()).isEmpty(); // Assert that 'xmi:version' and 'xmlns' don't exist
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = ClassifierMock.getChildFrom(root, 0, 0, 0, 0, 0, 0);
            attributeList = mock.getAttributes();
            assertThat(attributeList).isEmpty();
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = ClassifierMock.getChildFrom(mock, 0);
                attributeList = mockChild.getAttributes();
                assertThat(attributeList).hasSize(1);
                assertValidAttribute(attributeList.get(0), "visibility", "public");

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = ClassifierMock.getChildFrom(mock, 3);
                attributeList = mockChild.getAttributes();
                assertThat(attributeList).isEmpty();
            }

            //@Model/@ownedElements.1
            mock = ClassifierMock.getChildFrom(root, 1);
            attributeList = mock.getAttributes();
            assertThat(attributeList).hasSize(1);
            assertValidAttribute(attributeList.get(0), "proxy", "true");

            //@Model/@orphanTypes.5
            mock = ClassifierMock.getChildFrom(root, 8);
            attributeList = mock.getAttributes();
            assertThat(attributeList).isEmpty();

            //@Model/@compilationUnits.1
            mock = ClassifierMock.getChildFrom(root, 17);
            attributeList = mock.getAttributes();
            assertThat(attributeList).hasSize(1);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = ClassifierMock.getChildFrom(mock, 2);
                assertThat(mockChild.getAttributes()).isEmpty();
            }
        }
    }

    /**
     * Check that the XPath references are properly read.
     * Several references can be present in only one XML attribute.
     */
    @Test
    public void testReferences() throws Exception {
        ClassifierMock mock;
        ClassifierMock mockChild;

        List<Reference> referenceList;

        ClassifierMock root = persistanceHandler.getElements().get(0);
        assertThat(root.getReferences()).isEmpty();
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = ClassifierMock.getChildFrom(root, 0, 0, 0, 0, 0, 0);
            referenceList = mock.getReferences();
            assertThat(referenceList).hasSize(1);
            assertValidReference(referenceList.get(0), "originalCompilationUnit", 0, "//@compilationUnits.0");
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = ClassifierMock.getChildFrom(mock, 0);
                assertThat(mockChild.getReferences()).isEmpty();

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = ClassifierMock.getChildFrom(mock, 3);
                referenceList = mockChild.getReferences();
                assertThat(referenceList).hasSize(1);
                assertValidReference(referenceList.get(0), "originalCompilationUnit", 0, "//@compilationUnits.0");
            }

            //@Model/@ownedElements.1
            mock = ClassifierMock.getChildFrom(root, 1);
            assertThat(mock.getReferences()).isEmpty();

            //@Model/@orphanTypes.5
            mock = ClassifierMock.getChildFrom(root, 8);
            referenceList = mock.getReferences();
            assertThat(referenceList).hasSize(12);
            assertValidReference(referenceList.get(0), "usagesInTypeAccess", 0, "//@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.1/@returnType");
            assertValidReference(referenceList.get(9), "usagesInTypeAccess", 9, "//@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.1/@bodyDeclarations.5/@returnType");

            //@Model/@compilationUnits.1
            mock = ClassifierMock.getChildFrom(root, 17);
            referenceList = mock.getReferences();
            assertThat(referenceList).hasSize(2);
            assertValidReference(referenceList.get(0), "package", 0, "//@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0");
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = ClassifierMock.getChildFrom(mock, 2);
                referenceList = mockChild.getReferences();
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
    public void testMetaClasses() throws Exception {
        ClassifierMock mock;
        ClassifierMock mockChild;

        ClassifierMock root = persistanceHandler.getElements().get(0);
        Namespace ns = root.getNamespace();
        assertThat(root.getMetaClassifier()).isNull();
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = ClassifierMock.getChildFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidMetaClass(mock.getMetaClassifier(), "ClassDeclaration", ns);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = ClassifierMock.getChildFrom(mock, 0);
                assertThat(mockChild.getMetaClassifier()).isNull();

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = ClassifierMock.getChildFrom(mock, 3);
                assertValidMetaClass(mockChild.getMetaClassifier(), "MethodDeclaration", ns);
            }

            //@Model/@ownedElements.1
            mock = ClassifierMock.getChildFrom(root, 1);
            assertThat(mock.getMetaClassifier()).isNull();

            //@Model/@orphanTypes.5
            mock = ClassifierMock.getChildFrom(root, 8);
            assertValidMetaClass(mock.getMetaClassifier(), "PrimitiveTypeVoid", ns);

            //@Model/@compilationUnits.1
            mock = ClassifierMock.getChildFrom(root, 17);
            assertThat(mock.getMetaClassifier()).isNull();
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = ClassifierMock.getChildFrom(mock, 2);
                assertThat(mockChild.getMetaClassifier()).isNull();
            }
        }
    }

    @Test
    public void testMalformedMetaClass() throws Exception {
        Throwable thrown = catchThrowable(() -> read(getResourceFile("/io/xmi/sampleMalformedMetaClass.xmi")));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Check if the reader stop its execution if it hasn't any handler.
     */
    @Test
    public void testReaderWithoutHandler() throws Exception {
        Throwable thrown = catchThrowable(() -> new XmiStreamReader().read(null));
        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }
}
