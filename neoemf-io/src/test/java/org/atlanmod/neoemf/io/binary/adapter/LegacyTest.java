package org.atlanmod.neoemf.io.binary.adapter;

import fr.inria.atlanmod.neoemf.io.provider.OptionsProvider;
import fr.inria.atlanmod.neoemf.io.util.IceAgeResourceInitializer;
import org.atlanmod.neoemf.io.binary.BinaryWriter;
import org.atlanmod.neoemf.tests.iceage.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static fr.inria.atlanmod.neoemf.io.util.IceAgeResourceInitializer.multivaluedAttributes;
import static fr.inria.atlanmod.neoemf.io.util.IceAgeResourceInitializer.primitiveAttributes;
import static org.assertj.core.api.Assertions.assertThat;

public class LegacyTest {
    private ByteArrayOutputStream expected;
    private ByteArrayOutputStream actual;
    private BinaryResourceImpl.EObjectOutputStream oldBOS;
    private BinaryWriter neoBOS;
    private static IceageFactory FACTORY = IceagePackage.eINSTANCE.getIceageFactory();
/*
    @BeforeEach
    void setup() {
        expected = new ByteArrayOutputStream();
        actual = new ByteArrayOutputStream();
    }

    private void initializeResources(Map map) throws IOException {
        oldBOS = new BinaryResourceImpl.EObjectOutputStream(expected, map);
        neoBOS = new BinaryWriter(actual, map);
    }

    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(OptionsProvider.Version.class)
    void testWriteHeader(Map options) throws IOException {
        this.initializeResources(options);
        oldBOS.flush();
        neoBOS.flush();

        System.out.println(Arrays.toString(expected.toByteArray()));
        assertThat(expected.toByteArray()).isEqualTo(actual.toByteArray());
    }

    @ValueSource(strings = {"A simple String with ascii chars", "éá§àçè@#"})
    @ParameterizedTest
    void testWriteString(String value) throws IOException {
        this.initializeResources(Collections.emptyMap());
        oldBOS.writeString(value);
        oldBOS.flush();

        //neoBOS.writeString(value);
        //neoBOS.flush();

        //assertThat(actual.toByteArray()).isEqualTo(expected.toByteArray());
    }

    @ValueSource(strings = {"https://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/util/ArrayList.java",
            "file:///usr/local/etc/options.xml"})
    @ParameterizedTest
    void testWriteURL(String value) throws IOException {
        this.initializeResources(Collections.emptyMap());
        URI uri = URI.createURI(value);

        oldBOS.writeURI(uri);
        oldBOS.flush();

        //neoBOS.writeURI(uri);
        neoBOS.flush();

        assertThat(actual.toByteArray()).isEqualTo(expected.toByteArray());
    }


    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(OptionsProvider.Style.class)
    void testSingleElementWithPrimitiveTypes(Map options) throws IOException {
        this.initializeResources(options);

        Resource resource = new ResourceImpl();
        resource.getContents().add(primitiveAttributes());
        oldBOS.saveResource(resource);
        oldBOS.flush();

        neoBOS.saveResource(resource);
        neoBOS.flush();

        System.out.println(actual.size());

        assertThat(actual.toByteArray()).isEqualTo(expected.toByteArray());
    }


    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(OptionsProvider.Style.class)
    void testPrimitiveAttributesWithSameString(Map options) throws IOException {
        this.initializeResources(options);

        Resource resource = new ResourceImpl();
        resource.getContents().addAll(IceAgeResourceInitializer.primitiveAttributesWithSameString(100));
        oldBOS.saveResource(resource);
        oldBOS.flush();

        neoBOS.saveResource(resource);
        neoBOS.flush();

        System.out.println(actual.size());

        assertThat(actual.toByteArray()).isEqualTo(expected.toByteArray());
    }

    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(OptionsProvider.Style.class)
    void testSingleElementWithMultivaluedTypes(Map options) throws IOException {
        this.initializeResources(options);
        Resource resource = new ResourceImpl();
        resource.getContents().add(multivaluedAttributes());
        oldBOS.saveResource(resource);
        oldBOS.flush();

        neoBOS.saveResource(resource);
        neoBOS.flush();

        System.out.println(actual.size());

        assertThat(actual.toByteArray()).isEqualTo(expected.toByteArray());
    }

    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(OptionsProvider.Style.class)
    void testMultipleElementsWithPrimitiveTypes(Map options) throws IOException {
        this.initializeResources(options);

        Resource resource = new ResourceImpl();
        for (int i = 0; i < 200; i++) {
            resource.getContents().add(primitiveAttributes());
        }

        oldBOS.saveResource(resource);
        oldBOS.flush();

        neoBOS.saveResource(resource);
        neoBOS.flush();

        System.out.println(actual.size());
        assertThat(actual.toByteArray()).isEqualTo(expected.toByteArray());
    }

    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(OptionsProvider.Style.class)
    void testSingleNumericAttribute(Map options) throws IOException {
        this.initializeResources(options);
        Numeric numeric = IceAgeResourceInitializer.numeric();

        Resource resource = new ResourceImpl();
        resource.getContents().add(numeric);

        oldBOS.saveResource(resource);
        oldBOS.flush();

        neoBOS.saveResource(resource);
        neoBOS.flush();

        System.out.println(actual.size());
        assertThat(actual.toByteArray()).isEqualTo(expected.toByteArray());
    }


    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(OptionsProvider.Style.class)
    void testSimpleComposition(Map options) throws IOException {
        this.initializeResources(options);

        Herd herd = FACTORY.createHerd();
        PrimitiveAttributes pa = FACTORY.createPrimitiveAttributes();
        herd.getChildren().add(pa);

        Resource resource = new ResourceImpl();
        resource.getContents().add(herd);
        oldBOS.saveResource(resource);
        oldBOS.flush();

        neoBOS.saveResource(resource);
        neoBOS.flush();

        System.out.println(actual.size());

        assertThat(actual.toByteArray()).isEqualTo(expected.toByteArray());
    }

    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(OptionsProvider.Style.class)
    void testHierarchicalComposition(Map options) throws IOException {
        this.initializeResources(options);

        Resource resource = new ResourceImpl();
        resource.getContents().add(IceAgeResourceInitializer.hierarchicalFolders(100));
        oldBOS.saveResource(resource);
        oldBOS.flush();

        neoBOS.saveResource(resource);
        neoBOS.flush();

        System.out.println(actual.size());
        assertThat(actual.toByteArray()).isEqualTo(expected.toByteArray());
    }

    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(OptionsProvider.Style.class)
    void testFeatureMaps(Map options) throws IOException {
        this.initializeResources(options);

        Resource resource = new ResourceImpl();
        resource.getContents().add(IceAgeResourceInitializer.featureMaps());
        oldBOS.saveResource(resource);
        oldBOS.flush();

        neoBOS.saveResource(resource);
        neoBOS.flush();

        System.out.println(actual.size());
        assertThat(actual.toByteArray()).isEqualTo(expected.toByteArray());
    }


    public static void nain(String[] args) {
        int[] ints = {9, 97, 116, 108, 97, 110, 109, 111, 100, 1, 1, 7, 7, 110, 101, 111, 101, 109, 102, 1};
        //int[] ints =  {3, 9, 99, 104, 105, 108, 100, 114, 101, 110, 2, 2, 1, 2, 20, 80, 114, 105, 109, 105, 116, 105, 118, 101, 65, 116, 116, 114, 105, 98, 117, 116, 101, 115, 1, 1};
        //int[] ints = {4, 30, 102, 101, 97, 116, 117, 114, 101, 77, 97, 112, 82, 101, 102, 101, 114, 101, 110, 99, 101, 67, 111, 108, 108, 101, 99, 116, 105, 111, 110, 2, 1, 1, 6, 17, 101, 120, 111, 116, 105, 99, 80, 114, 105, 109, 105, 116, 105, 118, 101, 115};
        //int[] ints = {-119, 101, 109, 102, 10, 13, 26, 10, 0, 2, 1, 1, 35, 104, 116, 116, 112, 58, 47, 47, 119, 119, 119, 46, 110, 101, 111, 101, 109, 102, 46, 99, 111, 109, 47, 116, 101, 115, 116, 115, 47, 105, 99, 101, 97, 103, 101, 1, 35, 104, 116, 116, 112, 58, 47, 47, 119, 119, 119, 46, 110, 101, 111, 101, 109, 102, 46, 99, 111, 109, 47, 116, 101, 115, 116, 115, 47, 105, 99, 101, 97, 103, 101, 2, 47, 1, 12, 70, 101, 97, 116, 117, 114, 101, 77, 97, 112, 115, 5, 30, 102, 101, 97, 116, 117, 114, 101, 77, 97, 112, 65, 116, 116, 114, 105, 98, 117, 116, 101, 67, 111, 108, 108, 101, 99, 116, 105, 111, 110, 4, 1, 1, 7, 25, 102, 101, 97, 116, 117, 114, 101, 77, 97, 112, 65, 116, 116, 114, 105, 98, 117, 116, 101, 84, 121, 112, 101, 49, 4, 111, 114, 103, 1, 1, 7, 9, 97, 116, 108, 97, 110, 109, 111, 100, 1, 1, 7, 7, 110, 101, 111, 101, 109, 102, 1};
        //int[] expected = {-119, 101, 109, 102, 10, 13, 26, 10, 0, 2, 1, 1, 35, 104, 116, 116, 112, 58, 47, 47, 119, 119, 119, 46, 110, 101, 111, 101, 109, 102, 46, 99, 111, 109, 47, 116, 101, 115, 116, 115, 47, 105, 99, 101, 97, 103, 101, 1, 35, 104, 116, 116, 112, 58, 47, 47, 119, 119, 119, 46, 110, 101, 111, 101, 109, 102, 46, 99, 111, 109, 47, 116, 101, 115, 116, 115, 47, 105, 99, 101, 97, 103, 101, 2, 47, 1, 12, 70, 101, 97, 116, 117, 114, 101, 77, 97, 112, 115, 5, 30, 102, 101, 97, 116, 117, 114, 101, 77, 97, 112, 65, 116, 116, 114, 105, 98, 117, 116, 101, 67, 111, 108, 108, 101, 99, 116, 105, 111, 110, 4, 1, 1, 7, 25, 102, 101, 97, 116, 117, 114, 101, 77, 97, 112, 65, 116, 116, 114, 105, 98, 117, 116, 101, 84, 121, 112, 101, 49, 4, 111, 114, 103, 1, 1, 7, 9, 97, 116, 108, 97, 110, 109, 111, 100, 1, 1, 7, 7, 110, 101, 111, 101, 109, 102, 1};
        byte[] bytes = new byte[ints.length];
        //byte[] bytes2 = new byte[expected.length];
        for (int i = 0; i < ints.length; i++) {
            bytes[i] = (byte) ints[i];
            //bytes2[i] = (byte) expected[i];
        }

        System.out.println(new String(bytes));
        //System.out.println(new String(bytes2));
    }
    */
}
