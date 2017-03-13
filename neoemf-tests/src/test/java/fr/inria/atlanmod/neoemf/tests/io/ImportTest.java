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

package fr.inria.atlanmod.neoemf.tests.io;

import fr.inria.atlanmod.neoemf.Tags;

import org.eclipse.emf.ecore.EObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.IOException;

/**
 * A test case about the import from XMI to {@link fr.inria.atlanmod.neoemf.data.Backend}s.
 */
public class ImportTest extends AbstractIOTest {

    /**
     * Check that the elements are properly processed.
     * <p>
     * All elements must have an id and a class name.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    @Category({Tags.PersistentTests.class, Tags.IOTests.class})
    public void testElementsAndChildren() throws IOException {
        EObject eObject;
        EObject eObjectChild;

        EObject root = loadWithNeoEMF(getXmiStandard()).getContents().get(0);
        assertValidElement(root, "Model", 19, "fr.inria.atlanmod.kyanos.tests");
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            eObject = getChildFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidElement(eObject, "ClassDeclaration", 7, "TestCreateResource");
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                eObjectChild = getChildFrom(eObject, 0);
                assertValidElement(eObjectChild, "Modifier", 0, null);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                eObjectChild = getChildFrom(eObject, 3);
                assertValidElement(eObjectChild, "MethodDeclaration", 5, "tearDownAfterClass");
            }

            //@Model/@ownedElements.1
            eObject = getChildFrom(root, 1);
            assertValidElement(eObject, "Package", 5, "java");

            //@Model/@orphanTypes.5
            eObject = getChildFrom(root, 8);
            assertValidElement(eObject, "PrimitiveTypeVoid", 0, "void");

            //@Model/@compilationUnits.1
            eObject = getChildFrom(root, 17);
            assertValidElement(eObject, "CompilationUnit", 16, "TestXmi.java");
            {
                //@Model/@compilationUnits.1/@imports.2
                eObjectChild = getChildFrom(eObject, 2);
                assertValidElement(eObjectChild, "ImportDeclaration", 0, null);
            }
        }
    }

    /**
     * Check that the attributes are properly processed.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    @Category({Tags.PersistentTests.class, Tags.IOTests.class})
    public void testAttributes() throws IOException {
        EObject eObject;

        EObject root = loadWithNeoEMF(getXmiStandard()).getContents().get(0);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
            eObject = getChildFrom(root, 0, 0, 0, 0, 0, 0, 0);
            assertValidAttribute(eObject, "visibility", "public");

            //@Model/@ownedElements.1
            eObject = getChildFrom(root, 1);
            assertValidAttribute(eObject, "proxy", "true");

            //@Model/@compilationUnits.1
            eObject = getChildFrom(root, 17);
            assertValidAttribute(eObject, "originalFilePath", "C:\\Eclipse\\eclipse-SDK-4.3.1-win32-x86_64-Blue\\eclipse\\workspace\\fr.inria.atlanmod.kyanos.tests\\src\\fr\\inria\\atlanmod\\kyanos\\tests\\TestXmi.java");
        }
    }

    /**
     * Check that the XPath references/id are properly processed.
     * <p>
     * Containment and inverse reference must have been created. References previously detected as attributes, are now
     * well placed.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    @Category({Tags.PersistentTests.class, Tags.IOTests.class})
    public void testReferences() throws IOException {
        EObject eObject;
        EObject eObjectChild;

        EObject root = loadWithNeoEMF(getXmiStandard()).getContents().get(0);
        assertValidReference(root, "ownedElements", 0, "Package", "fr", true, true);
        assertValidReference(root, "orphanTypes", 5, "PrimitiveTypeVoid", "void", true, true);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            eObject = getChildFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidReference(eObject, "originalCompilationUnit", 0, "CompilationUnit", "TestCreateResource.java", false, false);
            assertValidReference(eObject, "bodyDeclarations", 0, "FieldDeclaration", null, true, true);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                eObjectChild = getChildFrom(eObject, 3);
                assertValidReference(eObjectChild, "originalCompilationUnit", 0, "CompilationUnit", "TestCreateResource.java", false, false);
                assertValidReference(eObjectChild, "modifier", 0, "Modifier", null, false, true);
            }

            //@Model/@ownedElements.1
            eObject = getChildFrom(root, 1);
            assertValidReference(eObject, "ownedPackages", 0, "Package", "io", true, true);

            //@Model/@orphanTypes.5
            eObject = getChildFrom(root, 8);
            assertValidReference(eObject, "usagesInTypeAccess", 0, "TypeAccess", null, true, false);
            assertValidReference(eObject, "usagesInTypeAccess", 9, "TypeAccess", null, true, false);

            //@Model/@compilationUnits.1
            eObject = getChildFrom(root, 17);
            assertValidReference(eObject, "package", 0, "Package", "tests", false, false);
            assertValidReference(eObject, "imports", 0, "ImportDeclaration", null, true, true);
            {
                //@Model/@compilationUnits.1/@imports.2
                eObjectChild = getChildFrom(eObject, 2);
                assertValidReference(eObjectChild, "originalCompilationUnit", 0, "CompilationUnit", "TestXmi.java", false, false);
                assertValidReference(eObjectChild, "importedElement", 2, "ClassDeclaration", "URI", false, false);
            }
        }
    }

    /**
     * Compares a model read with standard EMF and another read with NeoEMF.
     * <p>
     * The models use XPath as references.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    @Category({Tags.PersistentTests.class, Tags.IOTests.class})
    public void testCompare() throws IOException {
        File file = getXmiStandard();

        EObject emfObject = loadWithEMF(file).getContents().get(0);
        EObject neoObject = loadWithNeoEMF(file).getContents().get(0);

        assertEqualEObject(neoObject, emfObject);
    }

    /**
     * Compares a model read with standard EMF and another read with NeoEMF.
     * <p>
     * The models use {@code xmi:id} as references.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    @Ignore // FIXME Inverse references don't exist in EMF
    @Category({Tags.PersistentTests.class, Tags.IOTests.class})
    public void testCompareWithId() throws IOException {
        File file = getXmiWithId();

        EObject emfObject = loadWithEMF(file).getContents().get(0);
        EObject neoObject = loadWithNeoEMF(file).getContents().get(0);

        assertEqualEObject(neoObject, emfObject);
    }
}
