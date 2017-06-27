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
import fr.inria.atlanmod.neoemf.Tags;
import fr.inria.atlanmod.neoemf.io.mock.DummyElement;
import fr.inria.atlanmod.neoemf.io.mock.DummyWriter;
import fr.inria.atlanmod.neoemf.io.reader.XmiStreamReader;
import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.structure.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;

import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public abstract class AbstractInputTest extends AbstractTest {

    /**
     * A map that holds the mapping between an XPath and its {@code xmi:id} value.
     */
    private static final Map<String, String> MAPPING = new HashMap<>();

    static {
        MAPPING.put("/@Model.0", "_PYE3oE0VEeeM143ZRH6p9g");

        MAPPING.put("/@Model.0/@orphanTypes.5", "_PZgbAk0VEeeM143ZRH6p9g");
        MAPPING.put("/@Model.0/@orphanTypes.9", "_PZgbBk0VEeeM143ZRH6p9g");

        MAPPING.put("/@Model.0/@ownedElements.0", "_PYNagE0VEeeM143ZRH6p9g");
        MAPPING.put("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0", "_PYOooU0VEeeM143ZRH6p9g");
        MAPPING.put("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0", "_PYOook0VEeeM143ZRH6p9g");
        MAPPING.put("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@modifier.0", "_PYUIME0VEeeM143ZRH6p9g");
        MAPPING.put("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.1/@returnType.0", "_PYWkdU0VEeeM143ZRH6p9g");
        MAPPING.put("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2", "_PYXLgE0VEeeM143ZRH6p9g");
        MAPPING.put("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2/@modifier.0", "_PYXLg00VEeeM143ZRH6p9g");
        MAPPING.put("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.3", "_PYXLh00VEeeM143ZRH6p9g");
        MAPPING.put("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.1/@bodyDeclarations.5/@returnType.0", "_PZAExk0VEeeM143ZRH6p9g");

        MAPPING.put("/@Model.0/@ownedElements.1", "_PZKcwE0VEeeM143ZRH6p9g");
        MAPPING.put("/@Model.0/@ownedElements.1/@ownedPackages.1", "_PZOuME0VEeeM143ZRH6p9g");

        MAPPING.put("/@Model.0/@ownedElements.2/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0", "_PZbijE0VEeeM143ZRH6p9g");

        MAPPING.put("/@Model.0/@compilationUnits.0", "_PZhpIE0VEeeM143ZRH6p9g");

        MAPPING.put("/@Model.0/@compilationUnits.1", "_PZhpOE0VEeeM143ZRH6p9g");
        MAPPING.put("/@Model.0/@compilationUnits.1/@imports.1", "_PZhpOk0VEeeM143ZRH6p9g");
        MAPPING.put("/@Model.0/@compilationUnits.1/@imports.2", "_PZhpO00VEeeM143ZRH6p9g");
        MAPPING.put("/@Model.0/@compilationUnits.1/@imports.10", "_PZiQNE0VEeeM143ZRH6p9g");
    }

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
        IOResourceManager.registerPackage("java");
    }

    @Before
    public void readResource() throws IOException {
        DummyWriter writer = new DummyWriter();

        try (InputStream in = new URL(getSample().toString()).openStream()) {
            Migrator.fromXmi(in)
                    .to(writer)
                    .withCounter()
                    .withTimer()
//                    .withLogger()
                    .migrate();
        }

        root = checkNotNull(writer.getRoot());
    }

    /**
     * Returns the {@link URI} of the XMI file to use for this test-case.
     *
     * @return the stream
     */
    @Nonnull
    protected abstract URI getSample();

    /**
     * Checks whether this test-case use {@code xmi:id} instead of XPath as references.
     *
     * @return {@code true} if this test-case use {@code xmi:id} instead of XPath as references
     */
    protected abstract boolean useIds();

    /**
     * Retrieves the identifier of the given {@code path} if {@code useIds() == true}.
     *
     * @param path the path to retrieve
     *
     * @return the identifier
     *
     * @see #useIds()
     */
    protected String getId(String path) {
        return checkNotNull(useIds() ? MAPPING.get(path) : path, "Undefined path: %s", path);
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
    protected void assertValidAttribute(BasicAttribute attribute, String name, String value, int index) {
        assertThat(attribute.name()).isEqualTo(name);
        assertThat(attribute.value()).isEqualTo(value);
        assertThat(attribute.index()).isEqualTo(index);
    }

    /**
     * Check that the elements and their children are properly processed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testElementsAndChildren() {
        DummyElement o;
        DummyElement child;

        assertValidElement(root, getId("/@Model.0"), "Model", "fr.inria.atlanmod.kyanos.tests", 19);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            o = childFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidElement(o, getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0"), "ownedElements", "TestCreateResource", 7);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                child = childFrom(o, 0);
                assertValidElement(child, getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@modifier.0"), "modifier", null, 0);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                child = childFrom(o, 3);
                assertValidElement(child, getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2"), "bodyDeclarations", "tearDownAfterClass", 5);
            }

            //@Model/@ownedElements.1
            o = childFrom(root, 1);
            assertValidElement(o, getId("/@Model.0/@ownedElements.1"), "ownedElements", "java", 5);

            //@Model/@orphanTypes.5
            o = childFrom(root, 8);
            assertValidElement(o, getId("/@Model.0/@orphanTypes.5"), "orphanTypes", "void", 0);

            //@Model/@compilationUnits.1
            o = childFrom(root, 17);
            assertValidElement(o, getId("/@Model.0/@compilationUnits.1"), "compilationUnits", "TestXmi.java", 16);
            {
                //@Model/@compilationUnits.1/@imports.2
                child = childFrom(o, 2);
                assertValidElement(child, getId("/@Model.0/@compilationUnits.1/@imports.2"), "imports", null, 0);
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

        List<BasicReference> references;

        references = root.references();
        assertThat(references).hasSize(19);
        assertValidReference(references.get(0), "ownedElements", getId("/@Model.0/@ownedElements.0"), -1, true, true);
        assertValidReference(references.get(12), "orphanTypes", getId("/@Model.0/@orphanTypes.9"), -1, true, true);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            o = childFrom(root, 0, 0, 0, 0, 0, 0);
            references = o.references();
            assertThat(references).hasSize(8);
            assertValidReference(references.get(0), "originalCompilationUnit", getId("/@Model.0/@compilationUnits.0"), -1, false, false);
            assertValidReference(references.get(5), "bodyDeclarations", getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.3"), -1, true, true);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                child = childFrom(o, 0);
                assertThat(child.references()).isEmpty();

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                child = childFrom(o, 3);
                references = child.references();
                assertThat(references).hasSize(6);
                assertValidReference(references.get(0), "originalCompilationUnit", getId("/@Model.0/@compilationUnits.0"), -1, false, false);
                assertValidReference(references.get(2), "modifier", getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2/@modifier.0"), -1, false, true);
            }

            //@Model/@ownedElements.1
            o = childFrom(root, 1);
            references = o.references();
            assertThat(references).hasSize(5);
            assertValidReference(references.get(1), "ownedPackages", getId("/@Model.0/@ownedElements.1/@ownedPackages.1"), -1, true, true);

            //@Model/@orphanTypes.5
            o = childFrom(root, 8);
            references = o.references();

            assertThat(references).hasSize(12);
            assertValidReference(references.get(0), "usagesInTypeAccess", getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.1/@returnType.0"), 0, true, false);
            assertValidReference(references.get(9), "usagesInTypeAccess", getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.1/@bodyDeclarations.5/@returnType.0"), 9, true, false);

            //@Model/@compilationUnits.1
            o = childFrom(root, 17);
            references = o.references();
            assertThat(references).hasSize(18);
            assertValidReference(references.get(0), "package", getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0"), -1, false, false);
            assertValidReference(references.get(3), "imports", getId("/@Model.0/@compilationUnits.1/@imports.1"), -1, true, true);
            assertValidReference(references.get(12), "imports", getId("/@Model.0/@compilationUnits.1/@imports.10"), -1, true, true);
            {
                //@Model/@compilationUnits.1/@imports.2
                child = childFrom(o, 2);
                references = child.references();
                assertThat(references).hasSize(2);
                assertValidReference(references.get(0), "originalCompilationUnit", getId("/@Model.0/@compilationUnits.1"), -1, false, false);
                assertValidReference(references.get(1), "importedElement", getId("/@Model.0/@ownedElements.2/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0"), -1, false, false);
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

        List<BasicAttribute> attributes;

        attributes = root.attributes();
        assertThat(attributes).isEmpty(); // Assert that 'xmi:version' and 'xmlns' don't exist
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            o = childFrom(root, 0, 0, 0, 0, 0, 0);
            attributes = o.attributes();
            assertThat(attributes).isEmpty();
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                child = childFrom(o, 0);
                attributes = child.attributes();
                assertThat(attributes).hasSize(1);
                assertValidAttribute(attributes.get(0), "visibility", "public", 0);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                child = childFrom(o, 3);
                attributes = child.attributes();
                assertThat(attributes).isEmpty();
            }

            //@Model/@ownedElements.1
            o = childFrom(root, 1);
            attributes = o.attributes();
            assertThat(attributes).hasSize(1);
            assertValidAttribute(attributes.get(0), "proxy", "true", 0);

            //@Model/@orphanTypes.5
            o = childFrom(root, 8);
            attributes = o.attributes();
            assertThat(attributes).isEmpty();

            //@Model/@compilationUnits.1
            o = childFrom(root, 17);
            attributes = o.attributes();
            assertThat(attributes).hasSize(1);
            assertValidAttribute(attributes.get(0), "originalFilePath", "C:\\Eclipse\\eclipse-SDK-4.3.1-win32-x86_64-Blue\\eclipse\\workspace\\fr.inria.atlanmod.kyanos.tests\\src\\fr\\inria\\atlanmod\\kyanos\\tests\\TestXmi.java", 0);
            {
                //@Model/@compilationUnits.1/@imports.2
                child = childFrom(o, 2);
                assertThat(child.attributes()).isEmpty();
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
        assertThat(catchThrowable(() -> new XmiStreamReader(null).read(null)))
                .isInstanceOf(NullPointerException.class);
    }
}
