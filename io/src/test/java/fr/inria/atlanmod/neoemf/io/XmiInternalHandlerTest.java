/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Namespace;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.io.mock.beans.ClassifierMock;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

/**
 *
 */
public class XmiInternalHandlerTest extends AllXmiInternalHandlerTest {

    @Override
    @Before
    public void setUp() throws Exception {
        registerEPackageFromEcore("java");
        this.sample = "/xmi/sampleStd.xmi";

        super.setUp();
    }

    /**
     * Check that the elements are properly processed.
     * <p/>
     * All elements must have an id and a class name.
     */
    @Test
    public void testElementsAndChildren() throws Exception {
        assertThat(persistanceHandler.getElements(), not(empty()));

        ClassifierMock mock;
        ClassifierMock mockChild;

        ClassifierMock root = persistanceHandler.getElements().get(0);
        assertValidElement(root, "Model", 19, "/@Model.0", "Model", true);
        {
            //@Model/@name (block attribute)
            mock = ClassifierMock.getChildFrom(root, 0);
            assertThat(mock.getLocalName(), not(equalTo("name")));

            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = ClassifierMock.getChildFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidElement(mock, "ownedElements", 7, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0", "ClassDeclaration", false);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = ClassifierMock.getChildFrom(mock, 0);
                assertValidElement(mockChild, "modifier", 0, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@modifier.0", "Modifier", false);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = ClassifierMock.getChildFrom(mock, 3);
                assertValidElement(mockChild, "bodyDeclarations", 5, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2", "MethodDeclaration", false);
            }

            //@Model/@ownedElements.1
            mock = ClassifierMock.getChildFrom(root, 1);
            assertValidElement(mock, "ownedElements", 5, "/@Model.0/@ownedElements.1", "Package", false);

            //@Model/@orphanTypes.5
            mock = ClassifierMock.getChildFrom(root, 8);
            assertValidElement(mock, "orphanTypes", 0, "/@Model.0/@orphanTypes.5", "PrimitiveTypeVoid", false);

            //@Model/@compilationUnits.1
            mock = ClassifierMock.getChildFrom(root, 17);
            assertValidElement(mock, "compilationUnits", 16, "/@Model.0/@compilationUnits.1", "CompilationUnit", false);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = ClassifierMock.getChildFrom(mock, 2);
                assertValidElement(mockChild, "imports", 0, "/@Model.0/@compilationUnits.1/@imports.2", "ImportDeclaration", false);
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
        assertThat(attributeList, hasSize(1));
        assertValidAttribute(attributeList.get(0), "name", "fr.inria.atlanmod.kyanos.tests"); // Block attribute
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = ClassifierMock.getChildFrom(root, 0, 0, 0, 0, 0, 0);
            attributeList = mock.getAttributes();
            assertThat(attributeList, hasSize(1));
            assertValidAttribute(attributeList.get(0), "name", "TestCreateResource");
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = ClassifierMock.getChildFrom(mock, 0);
                attributeList = mockChild.getAttributes();
                assertThat(attributeList, hasSize(1));
                assertValidAttribute(attributeList.get(0), "visibility", "public");

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = ClassifierMock.getChildFrom(mock, 3);
                attributeList = mockChild.getAttributes();
                assertThat(attributeList, hasSize(1));
                assertValidAttribute(attributeList.get(0), "name", "tearDownAfterClass");
            }

            //@Model/@ownedElements.1
            mock = ClassifierMock.getChildFrom(root, 1);
            attributeList = mock.getAttributes();
            assertThat(attributeList, hasSize(2));
            assertValidAttribute(attributeList.get(0), "name", "java");
            assertValidAttribute(attributeList.get(1), "proxy", "true");

            //@Model/@orphanTypes.5
            mock = ClassifierMock.getChildFrom(root, 8);
            attributeList = mock.getAttributes();
            assertThat(attributeList, hasSize(1));
            assertValidAttribute(attributeList.get(0), "name", "void");

            //@Model/@compilationUnits.1
            mock = ClassifierMock.getChildFrom(root, 17);
            attributeList = mock.getAttributes();
            assertThat(attributeList, hasSize(2));
            assertValidAttribute(attributeList.get(0), "name", "TestXmi.java");
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = ClassifierMock.getChildFrom(mock, 2);
                assertThat(mockChild.getAttributes(), empty());
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
        assertThat(referenceList, hasSize(19)); // Now contains 'containment'
        assertValidReference(referenceList.get(0), "ownedElements", UNKNOWN_INDEX, "/@Model.0/@ownedElements.0", true, "model");
        assertValidReference(referenceList.get(12), "orphanTypes", UNKNOWN_INDEX, "/@Model.0/@orphanTypes.9", true, null);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            mock = ClassifierMock.getChildFrom(root, 0, 0, 0, 0, 0, 0);
            referenceList = mock.getReferences();
            assertThat(referenceList, hasSize(8)); // Now contains 'containment'
            assertValidReference(referenceList.get(0), "originalCompilationUnit", 0, "/@Model.0/@compilationUnits.0", false, null);
            assertValidReference(referenceList.get(5), "bodyDeclarations", UNKNOWN_INDEX, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.3", true, "abstractTypeDeclaration");
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                mockChild = ClassifierMock.getChildFrom(mock, 0);
                assertThat(mockChild.getReferences(), empty());

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                mockChild = ClassifierMock.getChildFrom(mock, 3);
                referenceList = mockChild.getReferences();
                assertThat(referenceList, hasSize(6)); // Now contains 'containment'
                assertValidReference(referenceList.get(0), "originalCompilationUnit", 0, "/@Model.0/@compilationUnits.0", false, null);
                assertValidReference(referenceList.get(2), "modifier", UNKNOWN_INDEX, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2/@modifier.0", true, "bodyDeclaration");
            }

            //@Model/@ownedElements.1
            mock = ClassifierMock.getChildFrom(root, 1);
            referenceList = mock.getReferences();
            assertThat(referenceList, hasSize(5)); // Now contains 'containment'
            assertValidReference(referenceList.get(1), "ownedPackages", UNKNOWN_INDEX, "/@Model.0/@ownedElements.1/@ownedPackages.1", true, "package");

            //@Model/@orphanTypes.5
            mock = ClassifierMock.getChildFrom(root, 8);
            referenceList = mock.getReferences();
            assertThat(referenceList, hasSize(12));
            assertValidReference(referenceList.get(0), "usagesInTypeAccess", 0, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.1/@returnType.0", false, "type");
            assertValidReference(referenceList.get(9), "usagesInTypeAccess", 9, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.1/@bodyDeclarations.5/@returnType.0", false, "type");

            //@Model/@compilationUnits.1
            mock = ClassifierMock.getChildFrom(root, 17);
            referenceList = mock.getReferences();
            assertThat(referenceList, hasSize(18)); // Now contains 'containment'
            assertValidReference(referenceList.get(0), "package", 0, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0", false, null);
            assertValidReference(referenceList.get(3), "imports", UNKNOWN_INDEX, "/@Model.0/@compilationUnits.1/@imports.1", true, null);
            assertValidReference(referenceList.get(12), "imports", UNKNOWN_INDEX, "/@Model.0/@compilationUnits.1/@imports.10", true, null);
            {
                //@Model/@compilationUnits.1/@imports.2
                mockChild = ClassifierMock.getChildFrom(mock, 2);
                referenceList = mockChild.getReferences();
                assertThat(referenceList, hasSize(2));
                assertValidReference(referenceList.get(0), "originalCompilationUnit", 0, "/@Model.0/@compilationUnits.1", false, null);
                assertValidReference(referenceList.get(1), "importedElement", 0, "/@Model.0/@ownedElements.2/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0", false, "usagesInImports");
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
