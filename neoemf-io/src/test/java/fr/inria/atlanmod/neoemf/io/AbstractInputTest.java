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

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.io.mock.DummyElement;
import fr.inria.atlanmod.neoemf.io.mock.DummyWriter;
import fr.inria.atlanmod.neoemf.io.reader.XmiStreamReader;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;

import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
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
    private DummyElement root;

    /**
     * Retrieves an element from the {@code root} element with the successive {@code indices}.
     *
     * @param root    the element from which to start the search
     * @param indices the index of the element, recursively in the children from the {@code root}
     *
     * @return the element
     */
    @Nonnull
    private static DummyElement childFrom(DummyElement root, int... indices) {
        checkArgument(indices.length > 0, "You must define at least one index");

        DummyElement child = root;

        for (int index : indices) {
            child = child.children().get(index);
        }

        return checkNotNull(child);
    }

    @BeforeClass
    public static void registerPackages() {
        IOResourceManager.registerAllPackages();
    }

    @Before
    public void readResource() throws IOException {
        DummyWriter writer = new DummyWriter();

        try (InputStream in = new URL(getResourceUri().toString()).openStream()) {
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
    protected abstract URI getResourceUri();

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
    protected Id getId(String path) {
        Id id = useIds()
                ? StringId.of(MAPPING.get(path))
                : StringId.generate(path);

        return checkNotNull(id, "Undefined path: %s", path);
    }

    /**
     * Checks that the {@code element} has the given arguments.
     *
     * @param element the element to test
     * @param id      the expected identifier
     * @param name    the expected name
     * @param size    the expected size
     */
    protected void assertValidElement(DummyElement element, Id id, String name, int size) {
        assertThat(element.id()).isEqualTo(id);
        assertThat(element.name()).isEqualTo(name);
        assertThat(element.children()).hasSize(size);
    }

    /**
     * Checks that the {@code metaClass} has the given arguments.
     *
     * @param metaClass the meta-class to test
     * @param nsPrefix  the expected namespace prefix
     * @param name      the expected name
     */
    protected void assertValidMetaClass(BasicMetaclass metaClass, String nsPrefix, String name) {
        assertThat(metaClass.ns().prefix()).isSameAs(nsPrefix);
        assertThat(metaClass.name()).isEqualTo(name);
    }

    /**
     * Checks that the {@code reference} has the given arguments.
     *
     * @param reference     the reference to test
     * @param name          the expected name
     * @param idReference   the expected reference
     * @param isMany        {@code true} if the {@code reference} is multi-valued
     * @param isContainment {@code true} if the {@code reference} is a containment
     */
    protected void assertValidReference(BasicReference reference, String name, Id idReference, boolean isMany, boolean isContainment) {
        assertThat(reference.name()).isEqualTo(name);
        assertThat(reference.value()).isEqualTo(idReference);
        assertThat(reference.isContainment()).isEqualTo(isContainment);
        assertThat(reference.isMany()).isEqualTo(isMany);
    }

    /**
     * Checks that the {@code attribute} has the given arguments.
     *
     * @param attribute the attribute to test
     * @param name      the expected name
     * @param value     the expected value
     */
    protected void assertValidAttribute(BasicAttribute attribute, String name, String value) {
        assertThat(attribute.name()).isEqualTo(name);
        assertThat(attribute.value()).isEqualTo(value);
    }

    /**
     * Check that the elements and their children are properly processed.
     */
    @Test
    public void testElementsAndChildren() {
        DummyElement o;
        DummyElement child;

        assertValidElement(root, getId("/@Model.0"), "Model", 19);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            o = childFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidElement(o, getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0"), "ownedElements", 7);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                child = childFrom(o, 0);
                assertValidElement(child, getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@modifier.0"), "modifier", 0);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                child = childFrom(o, 3);
                assertValidElement(child, getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2"), "bodyDeclarations", 5);
            }

            //@Model/@ownedElements.1
            o = childFrom(root, 1);
            assertValidElement(o, getId("/@Model.0/@ownedElements.1"), "ownedElements", 5);

            //@Model/@orphanTypes.5
            o = childFrom(root, 8);
            assertValidElement(o, getId("/@Model.0/@orphanTypes.5"), "orphanTypes", 0);

            //@Model/@compilationUnits.1
            o = childFrom(root, 17);
            assertValidElement(o, getId("/@Model.0/@compilationUnits.1"), "compilationUnits", 16);
            {
                //@Model/@compilationUnits.1/@imports.2
                child = childFrom(o, 2);
                assertValidElement(child, getId("/@Model.0/@compilationUnits.1/@imports.2"), "imports", 0);
            }
        }
    }

    /**
     * Check that the XPath references are properly processed.
     */
    @Test
    public void testReferences() {
        DummyElement o;
        DummyElement child;

        List<BasicReference> references;

        references = root.references();
        assertThat(references).hasSize(19);
        assertValidReference(references.get(0), "ownedElements", getId("/@Model.0/@ownedElements.0"), true, true);
        assertValidReference(references.get(12), "orphanTypes", getId("/@Model.0/@orphanTypes.9"), true, true);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            o = childFrom(root, 0, 0, 0, 0, 0, 0);
            references = o.references();
            assertThat(references).hasSize(8);
            assertValidReference(references.get(0), "originalCompilationUnit", getId("/@Model.0/@compilationUnits.0"), false, false);
            assertValidReference(references.get(5), "bodyDeclarations", getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.3"), true, true);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                child = childFrom(o, 0);
                assertThat(child.references()).isEmpty();

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                child = childFrom(o, 3);
                references = child.references();
                assertThat(references).hasSize(6);
                assertValidReference(references.get(0), "originalCompilationUnit", getId("/@Model.0/@compilationUnits.0"), false, false);
                assertValidReference(references.get(2), "modifier", getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2/@modifier.0"), false, true);
            }

            //@Model/@ownedElements.1
            o = childFrom(root, 1);
            references = o.references();
            assertThat(references).hasSize(5);
            assertValidReference(references.get(1), "ownedPackages", getId("/@Model.0/@ownedElements.1/@ownedPackages.1"), true, true);

            //@Model/@orphanTypes.5
            o = childFrom(root, 8);
            references = o.references();

            assertThat(references).hasSize(12);
            assertValidReference(references.get(0), "usagesInTypeAccess", getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.1/@returnType.0"), true, false);
            assertValidReference(references.get(9), "usagesInTypeAccess", getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.1/@bodyDeclarations.5/@returnType.0"), true, false);

            //@Model/@compilationUnits.1
            o = childFrom(root, 17);
            references = o.references();
            assertThat(references).hasSize(18);
            assertValidReference(references.get(0), "package", getId("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0"), false, false);
            assertValidReference(references.get(3), "imports", getId("/@Model.0/@compilationUnits.1/@imports.1"), true, true);
            assertValidReference(references.get(12), "imports", getId("/@Model.0/@compilationUnits.1/@imports.10"), true, true);
            {
                //@Model/@compilationUnits.1/@imports.2
                child = childFrom(o, 2);
                references = child.references();
                assertThat(references).hasSize(2);
                assertValidReference(references.get(0), "originalCompilationUnit", getId("/@Model.0/@compilationUnits.1"), false, false);
                assertValidReference(references.get(1), "importedElement", getId("/@Model.0/@ownedElements.2/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0"), false, false);
            }
        }
    }

    /**
     * Check that the attributes are properly processed.
     */
    @Test
    public void testAttributes() {
        DummyElement o;
        DummyElement child;

        List<BasicAttribute> attributes;

        attributes = root.attributes();
        assertThat(attributes).hasSize(1); // Assert that 'xmi:version' and 'xmlns' don't exist
        assertValidAttribute(attributes.get(0), "name", "fr.inria.atlanmod.kyanos.tests");
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            o = childFrom(root, 0, 0, 0, 0, 0, 0);
            attributes = o.attributes();
            assertThat(attributes).hasSize(1);
            assertValidAttribute(attributes.get(0), "name", "TestCreateResource");
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                child = childFrom(o, 0);
                attributes = child.attributes();
                assertThat(attributes).hasSize(1);
                assertValidAttribute(attributes.get(0), "visibility", "public");

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                child = childFrom(o, 3);
                attributes = child.attributes();
                assertThat(attributes).hasSize(1);
                assertValidAttribute(attributes.get(0), "name", "tearDownAfterClass");
            }

            //@Model/@ownedElements.1
            o = childFrom(root, 1);
            attributes = o.attributes();
            assertThat(attributes).hasSize(2);
            assertValidAttribute(attributes.get(0), "name", "java");
            assertValidAttribute(attributes.get(1), "proxy", "true");

            //@Model/@orphanTypes.5
            o = childFrom(root, 8);
            attributes = o.attributes();
            assertThat(attributes).hasSize(1);
            assertValidAttribute(attributes.get(0), "name", "void");

            //@Model/@compilationUnits.1
            o = childFrom(root, 17);
            attributes = o.attributes();
            assertThat(attributes).hasSize(2);
            assertValidAttribute(attributes.get(0), "name", "TestXmi.java");
            assertValidAttribute(attributes.get(1), "originalFilePath", "C:\\Eclipse\\eclipse-SDK-4.3.1-win32-x86_64-Blue\\eclipse\\workspace\\fr.inria.atlanmod.kyanos.tests\\src\\fr\\inria\\atlanmod\\kyanos\\tests\\TestXmi.java");
            {
                //@Model/@compilationUnits.1/@imports.2
                child = childFrom(o, 2);
                assertThat(child.attributes()).isEmpty();
            }
        }
    }

    /**
     * Check that the meta-classes ('xsi:type' or 'xmi:type') are properly processed.
     */
    @Test
    public void testMetaClasses() {
        DummyElement o;
        DummyElement child;

        assertValidMetaClass(root.metaClass(), "java", "Model");
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            o = childFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidMetaClass(o.metaClass(), "java", "ClassDeclaration");
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                child = childFrom(o, 0);
                assertValidMetaClass(child.metaClass(), "java", "Modifier");

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                child = childFrom(o, 3);
                assertValidMetaClass(child.metaClass(), "java", "MethodDeclaration");
            }

            //@Model/@ownedElements.1
            o = childFrom(root, 1);
            assertValidMetaClass(o.metaClass(), "java", "Package");

            //@Model/@orphanTypes.5
            o = childFrom(root, 8);
            assertValidMetaClass(o.metaClass(), "java", "PrimitiveTypeVoid");

            //@Model/@compilationUnits.1
            o = childFrom(root, 17);
            assertValidMetaClass(o.metaClass(), "java", "CompilationUnit");
            {
                //@Model/@compilationUnits.1/@imports.2
                child = childFrom(o, 2);
                assertValidMetaClass(child.metaClass(), "java", "ImportDeclaration");
            }
        }
    }

    /**
     * Check if the reader stop its execution if it hasn't any handler.
     */
    @Test
    public void testReaderWithoutHandler() {
        //noinspection ConstantConditions
        assertThat(
                catchThrowable(() -> new XmiStreamReader(null).read(null))
        ).isInstanceOf(NullPointerException.class);
    }
}
