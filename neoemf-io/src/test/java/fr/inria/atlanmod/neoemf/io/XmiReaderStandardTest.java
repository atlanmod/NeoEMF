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
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Nonnull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class XmiReaderStandardTest extends AbstractInputTest {

    @Nonnull
    @Override
    protected InputStream getSample() {
        return IOResourceManager.xmiStandard();
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
        DummyElement o;
        DummyElement child;

        assertValidElement(root, "/@Model.0", "Model", "fr.inria.atlanmod.kyanos.tests", 19);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            o = childFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidElement(o, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0", "ownedElements", "TestCreateResource", 7);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                child = childFrom(o, 0);
                assertValidElement(child, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@modifier.0", "modifier", null, 0);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                child = childFrom(o, 3);
                assertValidElement(child, "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2", "bodyDeclarations", "tearDownAfterClass", 5);
            }

            //@Model/@ownedElements.1
            o = childFrom(root, 1);
            assertValidElement(o, "/@Model.0/@ownedElements.1", "ownedElements", "java", 5);

            //@Model/@orphanTypes.5
            o = childFrom(root, 8);
            assertValidElement(o, "/@Model.0/@orphanTypes.5", "orphanTypes", "void", 0);

            //@Model/@compilationUnits.1
            o = childFrom(root, 17);
            assertValidElement(o, "/@Model.0/@compilationUnits.1", "compilationUnits", "TestXmi.java", 16);
            {
                //@Model/@compilationUnits.1/@imports.2
                child = childFrom(o, 2);
                assertValidElement(child, "/@Model.0/@compilationUnits.1/@imports.2", "imports", null, 0);
            }
        }
    }

    /**
     * Check that the attributes are properly processed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testAttributes() {
        DummyElement o;
        DummyElement child;

        List<BasicAttribute> attributeList;

        attributeList = root.attributes();
        assertThat(attributeList).isEmpty(); // Assert that 'xmi:version' and 'xmlns' don't exist
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            o = childFrom(root, 0, 0, 0, 0, 0, 0);
            attributeList = o.attributes();
            assertThat(attributeList).isEmpty();
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                child = childFrom(o, 0);
                attributeList = child.attributes();
                assertThat(attributeList).hasSize(1);
                assertValidAttribute(attributeList.get(0), "visibility", "public", 0);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                child = childFrom(o, 3);
                attributeList = child.attributes();
                assertThat(attributeList).isEmpty();
            }

            //@Model/@ownedElements.1
            o = childFrom(root, 1);
            attributeList = o.attributes();
            assertThat(attributeList).hasSize(1);
            assertValidAttribute(attributeList.get(0), "proxy", "true", 0);

            //@Model/@orphanTypes.5
            o = childFrom(root, 8);
            attributeList = o.attributes();
            assertThat(attributeList).isEmpty();

            //@Model/@compilationUnits.1
            o = childFrom(root, 17);
            attributeList = o.attributes();
            assertThat(attributeList).hasSize(1);
            assertValidAttribute(attributeList.get(0), "originalFilePath", "C:\\Eclipse\\eclipse-SDK-4.3.1-win32-x86_64-Blue\\eclipse\\workspace\\fr.inria.atlanmod.kyanos.tests\\src\\fr\\inria\\atlanmod\\kyanos\\tests\\TestXmi.java", 0);
            {
                //@Model/@compilationUnits.1/@imports.2
                child = childFrom(o, 2);
                assertThat(child.attributes()).isEmpty();
            }
        }
    }

    /**
     * Check that the XPath references are properly processed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testReferences() {
        DummyElement o;
        DummyElement child;

        List<BasicReference> referenceList;

        referenceList = root.references();
        assertThat(referenceList).hasSize(19); // Now contains containment
        assertValidReference(referenceList.get(0), "ownedElements", "/@Model.0/@ownedElements.0", -1, true, true);
        assertValidReference(referenceList.get(12), "orphanTypes", "/@Model.0/@orphanTypes.9", -1, true, true);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            o = childFrom(root, 0, 0, 0, 0, 0, 0);
            referenceList = o.references();
            assertThat(referenceList).hasSize(8); // Now contains containment
            assertValidReference(referenceList.get(0), "originalCompilationUnit", "/@Model.0/@compilationUnits.0", 0, false, false);
            assertValidReference(referenceList.get(5), "bodyDeclarations", "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.3", -1, true, true);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                child = childFrom(o, 0);
                assertThat(child.references()).isEmpty();

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                child = childFrom(o, 3);
                referenceList = child.references();
                assertThat(referenceList).hasSize(6); // Now contains containment
                assertValidReference(referenceList.get(0), "originalCompilationUnit", "/@Model.0/@compilationUnits.0", 0, false, false);
                assertValidReference(referenceList.get(2), "modifier", "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2/@modifier.0", -1, false, true);
            }

            //@Model/@ownedElements.1
            o = childFrom(root, 1);
            referenceList = o.references();
            assertThat(referenceList).hasSize(5); // Now contains containment
            assertValidReference(referenceList.get(1), "ownedPackages", "/@Model.0/@ownedElements.1/@ownedPackages.1", -1, true, true);

            //@Model/@orphanTypes.5
            o = childFrom(root, 8);
            referenceList = o.references();
            assertThat(referenceList).hasSize(12);
            assertValidReference(referenceList.get(0), "usagesInTypeAccess", "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.1/@returnType.0", 0, true, false);
            assertValidReference(referenceList.get(9), "usagesInTypeAccess", "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.1/@bodyDeclarations.5/@returnType.0", 9, true, false);

            //@Model/@compilationUnits.1
            o = childFrom(root, 17);
            referenceList = o.references();
            assertThat(referenceList).hasSize(18); // Now contains containment
            assertValidReference(referenceList.get(0), "package", "/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0", 0, false, false);
            assertValidReference(referenceList.get(3), "imports", "/@Model.0/@compilationUnits.1/@imports.1", -1, true, true);
            assertValidReference(referenceList.get(12), "imports", "/@Model.0/@compilationUnits.1/@imports.10", -1, true, true);
            {
                //@Model/@compilationUnits.1/@imports.2
                child = childFrom(o, 2);
                referenceList = child.references();
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
        DummyElement o;
        DummyElement child;

        BasicNamespace ns = root.ns();
        assertValidMetaClass(root.metaClass(), "Model", ns);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            o = childFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidMetaClass(o.metaClass(), "ClassDeclaration", ns);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                child = childFrom(o, 0);
                assertValidMetaClass(child.metaClass(), "Modifier", ns);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                child = childFrom(o, 3);
                assertValidMetaClass(child.metaClass(), "MethodDeclaration", ns);
            }

            //@Model/@ownedElements.1
            o = childFrom(root, 1);
            assertValidMetaClass(o.metaClass(), "Package", ns);

            //@Model/@orphanTypes.5
            o = childFrom(root, 8);
            assertValidMetaClass(o.metaClass(), "PrimitiveTypeVoid", ns);

            //@Model/@compilationUnits.1
            o = childFrom(root, 17);
            assertValidMetaClass(o.metaClass(), "CompilationUnit", ns);
            {
                //@Model/@compilationUnits.1/@imports.2
                child = childFrom(o, 2);
                assertValidMetaClass(child.metaClass(), "ImportDeclaration", ns);
            }
        }
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
