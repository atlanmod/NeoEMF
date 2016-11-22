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

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.mock.beans.ClassifierMock;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class XmiProcessorTest extends AllXmiProcessorTest {

    @Override
    @Before
    public void setUp() throws Exception {
        registerEPackageFromEcore("java", "http://www.eclipse.org/MoDisco/Java/0.2.incubation/java");
        this.sample = getXmiStandard();

        super.setUp();
    }

    /**
     * Check that the elements are properly processed.
     * <p/>
     * All elements must have an id and a class name.
     */
    @Test
    public void testElementsAndChildren() throws Exception {
        assertThat(persistanceHandler.getElements()).isNotEmpty();

        ClassifierMock mock;
        ClassifierMock mockChild;

        ClassifierMock root = persistanceHandler.getElements().get(0);
        assertValidElement(root, "Model", 19, "/@Model.0", "fr.inria.atlanmod.kyanos.tests", true);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = ClassifierMock.getChildFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidElement(mock, "ownedElements", 7, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0", "TestCreateResource", false);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = ClassifierMock.getChildFrom(mock, 0);
                assertValidElement(mockChild, "modifier", 0, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@modifier.0", null, false);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = ClassifierMock.getChildFrom(mock, 3);
                assertValidElement(mockChild, "bodyDeclarations", 5, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2", "tearDownAfterClass", false);
            }

            //@Model/@ownedElements.1
            mock = ClassifierMock.getChildFrom(root, 1);
            assertValidElement(mock, "ownedElements", 5, "/@Model.0/@ownedElements.1", "java", false);

            //@Model/@orphanTypes.5
            mock = ClassifierMock.getChildFrom(root, 8);
            assertValidElement(mock, "orphanTypes", 0, "/@Model.0/@orphanTypes.5", "void", false);

            //@Model/@compilationUnits.1
            mock = ClassifierMock.getChildFrom(root, 17);
            assertValidElement(mock, "compilationUnits", 16, "/@Model.0/@compilationUnits.1", "TestXmi.java", false);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = ClassifierMock.getChildFrom(mock, 2);
                assertValidElement(mockChild, "imports", 0, "/@Model.0/@compilationUnits.1/@imports.2", null, false);
            }
        }
    }

    /**
     * Check that the attributes are properly processed.
     */
    @Test
    public void testAttributes() throws Exception {
        ClassifierMock mock;
        ClassifierMock mockChild;

        List<Attribute> attributeList;

        ClassifierMock root = persistanceHandler.getElements().get(0);
        attributeList = root.getAttributes();
        assertThat(attributeList).isEmpty();
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
                assertValidAttribute(attributeList.get(0), "visibility", 0, "public");

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = ClassifierMock.getChildFrom(mock, 3);
                attributeList = mockChild.getAttributes();
                assertThat(attributeList).isEmpty();
            }

            //@Model/@ownedElements.1
            mock = ClassifierMock.getChildFrom(root, 1);
            attributeList = mock.getAttributes();
            assertThat(attributeList).hasSize(1);
            assertValidAttribute(attributeList.get(0), "proxy", 0, "true");

            //@Model/@orphanTypes.5
            mock = ClassifierMock.getChildFrom(root, 8);
            attributeList = mock.getAttributes();
            assertThat(attributeList).isEmpty();

            //@Model/@compilationUnits.1
            mock = ClassifierMock.getChildFrom(root, 17);
            attributeList = mock.getAttributes();
            assertThat(attributeList).hasSize(1);
            assertValidAttribute(attributeList.get(0), "originalFilePath", 0, "C:\\Eclipse\\eclipse-SDK-4.3.1-win32-x86_64-Blue\\eclipse\\workspace\\fr.inria.atlanmod.kyanos.tests\\src\\fr\\inria\\atlanmod\\kyanos\\tests\\TestXmi.java");
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = ClassifierMock.getChildFrom(mock, 2);
                assertThat(mockChild.getAttributes()).isEmpty();
            }
        }
    }

    /**
     * Check that the {@code XPath} references/id are properly processed.
     * <p/>
     * Containment and inverse reference must have been created.
     * References previously detected as attributes, are now well placed.
     */
    @Test
    public void testReferences() throws Exception {
        ClassifierMock mock;
        ClassifierMock mockChild;

        List<Reference> referenceList;

        ClassifierMock root = persistanceHandler.getElements().get(0);
        referenceList = root.getReferences();
        assertThat(referenceList).hasSize(19); // Now contains containment
        assertValidReference(referenceList.get(0), "ownedElements", UNKNOWN_INDEX, "/@Model.0/@ownedElements.0", true, true);
        assertValidReference(referenceList.get(12), "orphanTypes", UNKNOWN_INDEX, "/@Model.0/@orphanTypes.9", true, true);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = ClassifierMock.getChildFrom(root, 0, 0, 0, 0, 0, 0);
            referenceList = mock.getReferences();
            assertThat(referenceList).hasSize(8); // Now contains containment
            assertValidReference(referenceList.get(0), "originalCompilationUnit", 0, "/@Model.0/@compilationUnits.0", false, false);
            assertValidReference(referenceList.get(5), "bodyDeclarations", UNKNOWN_INDEX, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.3", true, true);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = ClassifierMock.getChildFrom(mock, 0);
                assertThat(mockChild.getReferences()).isEmpty();

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = ClassifierMock.getChildFrom(mock, 3);
                referenceList = mockChild.getReferences();
                assertThat(referenceList).hasSize(6); // Now contains containment
                assertValidReference(referenceList.get(0), "originalCompilationUnit", 0, "/@Model.0/@compilationUnits.0", false, false);
                assertValidReference(referenceList.get(2), "modifier", UNKNOWN_INDEX, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2/@modifier.0", false, true);
            }

            //@Model/@ownedElements.1
            mock = ClassifierMock.getChildFrom(root, 1);
            referenceList = mock.getReferences();
            assertThat(referenceList).hasSize(5); // Now contains containment
            assertValidReference(referenceList.get(1), "ownedPackages", UNKNOWN_INDEX, "/@Model.0/@ownedElements.1/@ownedPackages.1", true, true);

            //@Model/@orphanTypes.5
            mock = ClassifierMock.getChildFrom(root, 8);
            referenceList = mock.getReferences();
            assertThat(referenceList).hasSize(12);
            assertValidReference(referenceList.get(0), "usagesInTypeAccess", 0, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.1/@returnType.0", true, false);
            assertValidReference(referenceList.get(9), "usagesInTypeAccess", 9, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.1/@bodyDeclarations.5/@returnType.0", true, false);

            //@Model/@compilationUnits.1
            mock = ClassifierMock.getChildFrom(root, 17);
            referenceList = mock.getReferences();
            assertThat(referenceList).hasSize(18); // Now contains containment
            assertValidReference(referenceList.get(0), "package", 0, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0", false, false);
            assertValidReference(referenceList.get(3), "imports", UNKNOWN_INDEX, "/@Model.0/@compilationUnits.1/@imports.1", true, true);
            assertValidReference(referenceList.get(12), "imports", UNKNOWN_INDEX, "/@Model.0/@compilationUnits.1/@imports.10", true, true);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = ClassifierMock.getChildFrom(mock, 2);
                referenceList = mockChild.getReferences();
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
    public void testMetaClasses() throws Exception {
        ClassifierMock mock;
        ClassifierMock mockChild;

        ClassifierMock root = persistanceHandler.getElements().get(0);
        Namespace ns = root.getNamespace();
        assertValidMetaClass(root.getMetaClassifier(), "Model", ns);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = ClassifierMock.getChildFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidMetaClass(mock.getMetaClassifier(), "ClassDeclaration", ns);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = ClassifierMock.getChildFrom(mock, 0);
                assertValidMetaClass(mockChild.getMetaClassifier(), "Modifier", ns);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = ClassifierMock.getChildFrom(mock, 3);
                assertValidMetaClass(mockChild.getMetaClassifier(), "MethodDeclaration", ns);
            }

            //@Model/@ownedElements.1
            mock = ClassifierMock.getChildFrom(root, 1);
            assertValidMetaClass(mock.getMetaClassifier(), "Package", ns);

            //@Model/@orphanTypes.5
            mock = ClassifierMock.getChildFrom(root, 8);
            assertValidMetaClass(mock.getMetaClassifier(), "PrimitiveTypeVoid", ns);

            //@Model/@compilationUnits.1
            mock = ClassifierMock.getChildFrom(root, 17);
            assertValidMetaClass(mock.getMetaClassifier(), "CompilationUnit", ns);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = ClassifierMock.getChildFrom(mock, 2);
                assertValidMetaClass(mockChild.getMetaClassifier(), "ImportDeclaration", ns);
            }
        }
    }
}
