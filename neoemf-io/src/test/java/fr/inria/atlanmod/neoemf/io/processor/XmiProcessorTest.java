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

public class XmiProcessorTest extends AbstractXmiProcessorTest {

    @Override
    @Before
    public void readResource() throws IOException {
        registerEPackageFromEcore("java", "http://www.eclipse.org/MoDisco/Java/0.2.incubation/java");
        this.sample = getXmiStandard();
        super.readResource();
    }

    /**
     * Check that the elements are properly processed.
     * <p>
     * All elements must have an id and a class name.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testElementsAndChildren() {
        assertThat(persistanceHandler.getRoot()).isNotNull();

        DummyElement mock;
        DummyElement mockChild;

        DummyElement root = persistanceHandler.getRoot();
        assertValidElement(root, "Model", 19, "/@Model.0", "fr.inria.atlanmod.kyanos.tests", true);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = DummyElement.childFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidElement(mock, "ownedElements", 7, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0", "TestCreateResource", false);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = DummyElement.childFrom(mock, 0);
                assertValidElement(mockChild, "modifier", 0, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@modifier.0", null, false);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = DummyElement.childFrom(mock, 3);
                assertValidElement(mockChild, "bodyDeclarations", 5, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2", "tearDownAfterClass", false);
            }

            //@Model/@ownedElements.1
            mock = DummyElement.childFrom(root, 1);
            assertValidElement(mock, "ownedElements", 5, "/@Model.0/@ownedElements.1", "java", false);

            //@Model/@orphanTypes.5
            mock = DummyElement.childFrom(root, 8);
            assertValidElement(mock, "orphanTypes", 0, "/@Model.0/@orphanTypes.5", "void", false);

            //@Model/@compilationUnits.1
            mock = DummyElement.childFrom(root, 17);
            assertValidElement(mock, "compilationUnits", 16, "/@Model.0/@compilationUnits.1", "TestXmi.java", false);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = DummyElement.childFrom(mock, 2);
                assertValidElement(mockChild, "imports", 0, "/@Model.0/@compilationUnits.1/@imports.2", null, false);
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

        DummyElement root = persistanceHandler.getRoot();
        attributeList = root.attributes();
        assertThat(attributeList).isEmpty();
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
                assertValidAttribute(attributeList.get(0), "visibility", 0, "public");

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = DummyElement.childFrom(mock, 3);
                attributeList = mockChild.attributes();
                assertThat(attributeList).isEmpty();
            }

            //@Model/@ownedElements.1
            mock = DummyElement.childFrom(root, 1);
            attributeList = mock.attributes();
            assertThat(attributeList).hasSize(1);
            assertValidAttribute(attributeList.get(0), "proxy", 0, "true");

            //@Model/@orphanTypes.5
            mock = DummyElement.childFrom(root, 8);
            attributeList = mock.attributes();
            assertThat(attributeList).isEmpty();

            //@Model/@compilationUnits.1
            mock = DummyElement.childFrom(root, 17);
            attributeList = mock.attributes();
            assertThat(attributeList).hasSize(1);
            assertValidAttribute(attributeList.get(0), "originalFilePath", 0, "C:\\Eclipse\\eclipse-SDK-4.3.1-win32-x86_64-Blue\\eclipse\\workspace\\fr.inria.atlanmod.kyanos.tests\\src\\fr\\inria\\atlanmod\\kyanos\\tests\\TestXmi.java");
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = DummyElement.childFrom(mock, 2);
                assertThat(mockChild.attributes()).isEmpty();
            }
        }
    }

    /**
     * Check that the XPath references/id are properly processed.
     * <p>
     * Containment and inverse reference must have been created.
     * References previously detected as attributes, are now well placed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testReferences() {
        DummyElement mock;
        DummyElement mockChild;

        List<BasicReference> referenceList;

        DummyElement root = persistanceHandler.getRoot();
        referenceList = root.references();
        assertThat(referenceList).hasSize(19); // Now contains containment
        assertValidReference(referenceList.get(0), "ownedElements", UNKNOWN_INDEX, "/@Model.0/@ownedElements.0", true, true);
        assertValidReference(referenceList.get(12), "orphanTypes", UNKNOWN_INDEX, "/@Model.0/@orphanTypes.9", true, true);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = DummyElement.childFrom(root, 0, 0, 0, 0, 0, 0);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(8); // Now contains containment
            assertValidReference(referenceList.get(0), "originalCompilationUnit", 0, "/@Model.0/@compilationUnits.0", false, false);
            assertValidReference(referenceList.get(5), "bodyDeclarations", UNKNOWN_INDEX, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.3", true, true);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = DummyElement.childFrom(mock, 0);
                assertThat(mockChild.references()).isEmpty();

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = DummyElement.childFrom(mock, 3);
                referenceList = mockChild.references();
                assertThat(referenceList).hasSize(6); // Now contains containment
                assertValidReference(referenceList.get(0), "originalCompilationUnit", 0, "/@Model.0/@compilationUnits.0", false, false);
                assertValidReference(referenceList.get(2), "modifier", UNKNOWN_INDEX, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2/@modifier.0", false, true);
            }

            //@Model/@ownedElements.1
            mock = DummyElement.childFrom(root, 1);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(5); // Now contains containment
            assertValidReference(referenceList.get(1), "ownedPackages", UNKNOWN_INDEX, "/@Model.0/@ownedElements.1/@ownedPackages.1", true, true);

            //@Model/@orphanTypes.5
            mock = DummyElement.childFrom(root, 8);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(12);
            assertValidReference(referenceList.get(0), "usagesInTypeAccess", 0, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.1/@returnType.0", true, false);
            assertValidReference(referenceList.get(9), "usagesInTypeAccess", 9, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.1/@bodyDeclarations.5/@returnType.0", true, false);

            //@Model/@compilationUnits.1
            mock = DummyElement.childFrom(root, 17);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(18); // Now contains containment
            assertValidReference(referenceList.get(0), "package", 0, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0", false, false);
            assertValidReference(referenceList.get(3), "imports", UNKNOWN_INDEX, "/@Model.0/@compilationUnits.1/@imports.1", true, true);
            assertValidReference(referenceList.get(12), "imports", UNKNOWN_INDEX, "/@Model.0/@compilationUnits.1/@imports.10", true, true);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = DummyElement.childFrom(mock, 2);
                referenceList = mockChild.references();
                assertThat(referenceList).hasSize(2);
                assertValidReference(referenceList.get(0), "originalCompilationUnit", 0, "/@Model.0/@compilationUnits.1", false, false);
                assertValidReference(referenceList.get(1), "importedElement", 0, "/@Model.0/@ownedElements.2/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0", false, false);
            }
        }
    }

    /**
     * Check that the metaclasses ({@code xsi:type} or {@code xmi:type}) are properly processed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testMetaClasses() {
        DummyElement mock;
        DummyElement mockChild;

        DummyElement root = persistanceHandler.getRoot();
        BasicNamespace ns = root.ns();
        assertValidMetaClass(root.metaClass(), "Model", ns);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = DummyElement.childFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidMetaClass(mock.metaClass(), "ClassDeclaration", ns);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = DummyElement.childFrom(mock, 0);
                assertValidMetaClass(mockChild.metaClass(), "Modifier", ns);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = DummyElement.childFrom(mock, 3);
                assertValidMetaClass(mockChild.metaClass(), "MethodDeclaration", ns);
            }

            //@Model/@ownedElements.1
            mock = DummyElement.childFrom(root, 1);
            assertValidMetaClass(mock.metaClass(), "Package", ns);

            //@Model/@orphanTypes.5
            mock = DummyElement.childFrom(root, 8);
            assertValidMetaClass(mock.metaClass(), "PrimitiveTypeVoid", ns);

            //@Model/@compilationUnits.1
            mock = DummyElement.childFrom(root, 17);
            assertValidMetaClass(mock.metaClass(), "CompilationUnit", ns);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = DummyElement.childFrom(mock, 2);
                assertValidMetaClass(mockChild.metaClass(), "ImportDeclaration", ns);
            }
        }
    }
}
