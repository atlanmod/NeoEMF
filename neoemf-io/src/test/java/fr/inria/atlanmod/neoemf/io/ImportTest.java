/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.io.reader.XmiStreamReader;
import fr.inria.atlanmod.neoemf.io.util.InMemoryElement;
import fr.inria.atlanmod.neoemf.io.util.InMemoryWriter;
import fr.inria.atlanmod.neoemf.io.util.ResourceManager;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * An abstract test-case about the import of a model.
 */
@SuppressWarnings("ConstantConditions")
@ParametersAreNonnullByDefault
class ImportTest extends AbstractTest {

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
     * Retrieves an element from the {@code root} element with the successive {@code indices}.
     *
     * @param root    the element from which to start the search
     * @param indices the index of the element, recursively in the children from the {@code root}
     *
     * @return the element
     */
    @Nonnull
    private static InMemoryElement childFrom(InMemoryElement root, int... indices) {
        checkArgument(indices.length > 0, "You must define at least one index");

        InMemoryElement child = root;

        for (int index : indices) {
            child = child.children().get(index);
        }

        return checkNotNull(child);
    }

    @BeforeAll
    static void registerPackages() {
        ResourceManager.registerAllPackages();
    }

    /**
     * Check that the elements and their children are properly processed.
     */
    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(UriProvider.class)
    void testElementsAndChildren(URI uri, Boolean withId) throws IOException {
        InMemoryElement root = readResource(uri);

        InMemoryElement o;
        InMemoryElement child;

        assertValidElement(root, resolve("/@Model.0", withId), "Model", 19);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            o = childFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidElement(o, resolve("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0", withId), "ownedElements", 7);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                child = childFrom(o, 0);
                assertValidElement(child, resolve("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@modifier.0", withId), "modifier", 0);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                child = childFrom(o, 3);
                assertValidElement(child, resolve("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2", withId), "bodyDeclarations", 5);
            }

            //@Model/@ownedElements.1
            o = childFrom(root, 1);
            assertValidElement(o, resolve("/@Model.0/@ownedElements.1", withId), "ownedElements", 5);

            //@Model/@orphanTypes.5
            o = childFrom(root, 8);
            assertValidElement(o, resolve("/@Model.0/@orphanTypes.5", withId), "orphanTypes", 0);

            //@Model/@compilationUnits.1
            o = childFrom(root, 17);
            assertValidElement(o, resolve("/@Model.0/@compilationUnits.1", withId), "compilationUnits", 16);
            {
                //@Model/@compilationUnits.1/@imports.2
                child = childFrom(o, 2);
                assertValidElement(child, resolve("/@Model.0/@compilationUnits.1/@imports.2", withId), "imports", 0);
            }
        }
    }

    /**
     * Check that the XPath references are properly processed.
     */
    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(UriProvider.class)
    void testReferences(URI uri, Boolean withId) throws IOException {
        InMemoryElement root = readResource(uri);

        InMemoryElement o;
        InMemoryElement child;

        List<BasicReference> references;

        references = root.references();
        assertThat(references).hasSize(19);
        assertValidReference(references.get(0), "ownedElements", resolve("/@Model.0/@ownedElements.0", withId), true, true);
        assertValidReference(references.get(12), "orphanTypes", resolve("/@Model.0/@orphanTypes.9", withId), true, true);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            o = childFrom(root, 0, 0, 0, 0, 0, 0);
            references = o.references();
            assertThat(references).hasSize(8);
            assertValidReference(references.get(0), "originalCompilationUnit", resolve("/@Model.0/@compilationUnits.0", withId), false, false);
            assertValidReference(references.get(5), "bodyDeclarations", resolve("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.3", withId), true, true);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                child = childFrom(o, 0);
                assertThat(child.references()).isEmpty();

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                child = childFrom(o, 3);
                references = child.references();
                assertThat(references).hasSize(6);
                assertValidReference(references.get(0), "originalCompilationUnit", resolve("/@Model.0/@compilationUnits.0", withId), false, false);
                assertValidReference(references.get(2), "modifier", resolve("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.2/@modifier.0", withId), false, true);
            }

            //@Model/@ownedElements.1
            o = childFrom(root, 1);
            references = o.references();
            assertThat(references).hasSize(5);
            assertValidReference(references.get(1), "ownedPackages", resolve("/@Model.0/@ownedElements.1/@ownedPackages.1", withId), true, true);

            //@Model/@orphanTypes.5
            o = childFrom(root, 8);
            references = o.references();

            assertThat(references).hasSize(12);
            assertValidReference(references.get(0), "usagesInTypeAccess", resolve("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0/@bodyDeclarations.1/@returnType.0", withId), true, false);
            assertValidReference(references.get(9), "usagesInTypeAccess", resolve("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.1/@bodyDeclarations.5/@returnType.0", withId), true, false);

            //@Model/@compilationUnits.1
            o = childFrom(root, 17);
            references = o.references();
            assertThat(references).hasSize(18);
            assertValidReference(references.get(0), "package", resolve("/@Model.0/@ownedElements.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0", withId), false, false);
            assertValidReference(references.get(3), "imports", resolve("/@Model.0/@compilationUnits.1/@imports.1", withId), true, true);
            assertValidReference(references.get(12), "imports", resolve("/@Model.0/@compilationUnits.1/@imports.10", withId), true, true);
            {
                //@Model/@compilationUnits.1/@imports.2
                child = childFrom(o, 2);
                references = child.references();
                assertThat(references).hasSize(2);
                assertValidReference(references.get(0), "originalCompilationUnit", resolve("/@Model.0/@compilationUnits.1", withId), false, false);
                assertValidReference(references.get(1), "importedElement", resolve("/@Model.0/@ownedElements.2/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedPackages.0/@ownedElements.0", withId), false, false);
            }
        }
    }

    /**
     * Check that the attributes are properly processed.
     */
    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(UriProvider.class)
    void testAttributes(URI uri) throws IOException {
        InMemoryElement root = readResource(uri);

        InMemoryElement o;
        InMemoryElement child;

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
            assertValidAttribute(attributes.get(1), "proxy", true);

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
    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(UriProvider.class)
    void testMetaClasses(URI uri) throws IOException {
        InMemoryElement root = readResource(uri);

        InMemoryElement o;
        InMemoryElement child;

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
    void testReaderWithoutHandler() {
        assertThat(
                catchThrowable(() -> new XmiStreamReader(null).read(null))
        ).isInstanceOf(NullPointerException.class);
    }

    /**
     * Reads a resource file from the given {@code uri}.
     *
     * @param uri the URI of the resource
     *
     * @return the root of the read resource
     */
    @Nonnull
    private InMemoryElement readResource(URI uri) throws IOException {
        InMemoryWriter writer = new InMemoryWriter();

        try (InputStream in = new URL(uri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .to(writer)
                    .withCounter()
                    .withTimer()
//                    .withLogger()
                    .migrate();
        }

        return checkNotNull(writer.getRoot());
    }

    /**
     * Retrieves the identifier of the given {@code path} if {@code useIds() == true}.
     *
     * @param path the path to retrieve
     *
     * @return the identifier
     */
    @Nonnull
    private Id resolve(String path, boolean withId) {
        Id id = withId
                ? Id.getProvider().generate(MAPPING.get(path))
                : Id.getProvider().generate(path);

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
    private void assertValidElement(InMemoryElement element, Id id, String name, int size) {
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
    private void assertValidMetaClass(BasicMetaclass metaClass, String nsPrefix, String name) {
        assertThat(metaClass.ns().prefix()).isEqualTo(nsPrefix);
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
    private void assertValidReference(BasicReference reference, String name, Id idReference, boolean isMany, boolean isContainment) {
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
    private void assertValidAttribute(BasicAttribute attribute, String name, Object value) {
        assertThat(attribute.name()).isEqualTo(name);
        assertThat(attribute.value()).isEqualTo(value);
    }

    /**
     * An {@link ArgumentsProvider} with all {@link URI}s managed by {@link ResourceManager}, associated with all {@link
     * Boolean} variants.
     */
    @ParametersAreNonnullByDefault
    static class UriProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(ResourceManager.xmiStandard(), false),
                    Arguments.of(ResourceManager.xmiWithId(), true),
                    Arguments.of(ResourceManager.zxmiStandard(), false),
                    Arguments.of(ResourceManager.zxmiWithId(), true)
            );
        }
    }
}
