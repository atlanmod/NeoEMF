package fr.inria.atlanmod.neoemf.io.util;

import org.atlanmod.neoemf.tests.iceage.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Utility class to dynamically create XMI resources.
 */
public class IceAgeResourceInitializer {

    public static String FILE_NAME = "./neoemf-io/src/test/resources/xmi/iceage.xmi";
    public static String PROXY_CONTAINER = "./neoemf-io/src/test/resources/xmi/proxy-container.xmi";
    public static String PROXY_CONTAINMENT = "./neoemf-io/src/test/resources/xmi/proxy-containment.xmi";
    private static IceageFactory FACTORY = IceagePackage.eINSTANCE.getIceageFactory();
    private static ResourceSetImpl RESOURCES = new ResourceSetImpl();

    static {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        IceagePackage.eINSTANCE.eClass();
    }

    public static void initializeSampleFilesWithProxy() throws IOException {
        final File containerFile = new File(PROXY_CONTAINER);
        final File containmentFile = new File(PROXY_CONTAINMENT);

        System.out.println(RESOURCES);

        Resource containerResource = RESOURCES.createResource(URI.createFileURI(containerFile.getAbsolutePath()));
        Resource containmentResource = RESOURCES.createResource(URI.createFileURI(containmentFile.getAbsolutePath()));

        // options
        Map<String, Object> options = new HashMap<>();
        options.put(XMLResource.OPTION_PROXY_ATTRIBUTES, true);


        // CONTAINER MANY
        Herd herd = FACTORY.createHerd();
        IceAge iceAge = FACTORY.createIceAge();
        IceAge frozenAge = FACTORY.createIceAge();

        Moment now = FACTORY.createMoment();
        now.setDate(new Date());
        Moment later = FACTORY.createMoment();

        iceAge.setMoment(now);
        iceAge.setResolvingMoment(later);

        frozenAge.setMoment(FACTORY.createMoment());
        frozenAge.setResolvingMoment(FACTORY.createMoment());





        PrimitiveAttributes attr1 = FACTORY.createPrimitiveAttributes();
        PrimitiveAttributes attr2 = FACTORY.createPrimitiveAttributes();
        PrimitiveAttributes attr3 = FACTORY.createPrimitiveAttributes();
        PrimitiveAttributes attr4 = FACTORY.createPrimitiveAttributes();
        attr1.setAString("Primitive Attribute One");
        attr2.setAString("Primitive Attribute Two");
        attr3.setAString("Primitive Attribute Three");
        attr4.setAString("Primitive Attribute Four");
        herd.getElements().addAll(List.of(attr1, attr2));
        herd.getChildren().addAll(List.of(attr2, attr3));

        InitializedPrimitive prim1 = FACTORY.createInitializedPrimitive();
        InitializedPrimitive prim2 = FACTORY.createInitializedPrimitive();
        InitializedPrimitive prim3 = FACTORY.createInitializedPrimitive();
        InitializedPrimitive prim4 = FACTORY.createInitializedPrimitive();
        InitializedPrimitive prim5 = FACTORY.createInitializedPrimitive();
        InitializedPrimitive prim6 = FACTORY.createInitializedPrimitive();

        iceAge.setChief(prim1);
        iceAge.setResolvingChief(prim2);
        iceAge.getFollowers().addAll(List.of(prim3, prim4));
        iceAge.getResolvingFollowers().addAll(List.of(prim5, prim6));

        // Add to resources
        containmentResource.getContents().addAll(List.of(attr1, attr2, prim1, prim2, prim3, prim4, prim5, prim6, now, later));
        containerResource.getContents().addAll(List.of(herd, iceAge, frozenAge));

        // Save files
        containerResource.save(options);
        containmentResource.save(options);
    }

    public static void initializeSampleFile() throws IOException {
        final File targetFile = new File(FILE_NAME);

        Herd container = FACTORY.createHerd();

        // Numeric
        Numeric numeric = numeric();

        // Primitive
        PrimitiveAttributes primitive = FACTORY.createPrimitiveAttributes();
        primitive.setABoolean(false);
        primitive.setAByte((byte) 8);
        primitive.setAChar('h');
        primitive.setADouble(Double.MAX_VALUE);
        primitive.setAFloat(Float.MAX_VALUE);
        primitive.setALong(Long.MAX_VALUE);
        primitive.setAString("NeoEMF");
        primitive.setAnInt(Integer.MIN_VALUE);
        primitive.setAShort(Short.MAX_VALUE);

        // More primitive attributes
        List<PrimitiveAttributes> prims = instatiatePrimitiveAttributes(10);
        prims.get(0).setABoolean(true);
        prims.get(1).setAByte(Byte.MAX_VALUE);
        prims.get(2).setAChar('€');
        prims.get(3).setADouble(Double.MAX_VALUE);
        prims.get(4).setAFloat(Float.MAX_VALUE);
        prims.get(5).setALong(Long.MAX_VALUE);
        prims.get(6).setAString("NeoEMF");
        prims.get(7).setAnInt(Integer.MIN_VALUE);
        prims.get(8).setAShort(Short.MAX_VALUE);

        // Mammoths
        List<Mammoth> mams = instatiateMammoths(4);
        mams.get(0).setAge(10);
        mams.get(1).setName("Manny");
        mams.get(2).setAlive(true);
        mams.get(3).setBodyFat((float) 40.0);

        // Multi-valued
        MultivaluedAttributes multi = FACTORY.createMultivaluedAttributes();
        multi.getMultivaluedString().addAll(List.of("Java", "Ruby", "COBOL", "Fortran"));
        multi.getMultivaluedInt().addAll(List.of(Integer.MIN_VALUE, Integer.MAX_VALUE, 42, 0));
        multi.getMultivaluedDouble().addAll(List.of(Double.MAX_VALUE, Double.MIN_VALUE, Double.MIN_NORMAL));

        Folder f1 = hierarchicalFolders(10);

        // Feature Maps

        FeatureMaps maps = featureMaps();

        // Complex Attributes

        ComplexAttributes complex = FACTORY.createComplexAttributes();
        complex.setADate(new Date());

        container.getElements().add(numeric);
        container.getElements().add(primitive);
        container.getElements().add(multi);
        container.getElements().add(f1);
        container.getElements().add(maps);
        container.getElements().add(complex);
        container.getElements().addAll(prims);
        container.getElements().addAll(mams);

        // Some more primitives with String
        for (int i = 0; i < 20; i++) {
            PrimitiveAttributes each = FACTORY.createPrimitiveAttributes();
            each.setAString("This is a very long string. Since the same String is written several times, it will be compacted by the serializer");
            container.getElements().add(each);
        }


        Resource resource = new ResourceSetImpl().createResource(URI.createFileURI(targetFile.getAbsolutePath()));
        resource.getContents().add(container);
        resource.save(Collections.emptyMap());
    }

    @NotNull
    public static FeatureMaps featureMaps() {
        FeatureMaps maps = FACTORY.createFeatureMaps();


/*        maps.getCommonPrimitives().addAll(List.of(
                FACTORY.createPrimitiveAttributes(),
                FACTORY.createPrimitiveAttributes(),
                FACTORY.createPrimitiveAttributes()));*/

        //maps.getExoticPrimitives().add(FACTORY.createPrimitiveAttributes());

        maps.getFeatureMapAttributeType1().addAll(List.of("org", "atlanmod", "neoemf"));
        //maps.getFeatureMapAttributeType2().add("https://www.eclipse.org/forums/index.php/t/454887/");


        //maps.getFeatureMapAttributeType2().add("BBB");
        //maps.getFeatureMapAttributeType1().addAll(List.of("AAA", "AAA", "AAA"));

        return maps;
    }

    @NotNull
    public static Folder hierarchicalFolders(int count) {
        Folder root = FACTORY.createFolder();
        Folder current = root;

        for (int i = 0; i < count; i++) {
            Folder newFolder = FACTORY.createFolder();
            current.getContents().add(newFolder);
            current = newFolder;
        }
        return root;
    }

    @NotNull
    public static Numeric numeric() {
        Numeric numeric = FACTORY.createNumeric();
        numeric.setABigDecimal(BigDecimal.valueOf(123412345678901L, 2));
        numeric.setABigInteger(new BigInteger("1234567890987654321"));
        return numeric;
    }
    
    @NotNull
    public static List<PrimitiveAttributes> primitiveAttributesWithSameString(int count) {
        List<PrimitiveAttributes> instances = instatiatePrimitiveAttributes(count);
        for (PrimitiveAttributes each : instances) {
            each.setAString("This is a very long string. Since the same String is written several times, it will be compacted by the serializer");
        }

        return instances;
    }

    private static List<PrimitiveAttributes> instatiatePrimitiveAttributes(int count) {
        List<PrimitiveAttributes> instances = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            instances.add(FACTORY.createPrimitiveAttributes());
        }
        return instances;
    }

    private static List<Mammoth> instatiateMammoths(int count) {
        List<Mammoth> instances = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            instances.add(FACTORY.createMammoth());
        }
        return instances;
    }

    public static PrimitiveAttributes primitiveAttributes() {
        PrimitiveAttributes pa = FACTORY.createPrimitiveAttributes();
        pa.setAnInt(Integer.MAX_VALUE);
        pa.setAByte(Byte.MAX_VALUE);
        pa.setABoolean(true);
        pa.setAChar('é');
        pa.setALong(Long.MAX_VALUE);
        pa.setAString("A simple string for testing");

        return pa;
    }

    public static MultivaluedAttributes multivaluedAttributes() {
        MultivaluedAttributes ma = FACTORY.createMultivaluedAttributes();
        ma.getMultivaluedInt().addAll(List.of(Integer.MIN_VALUE, 0, Integer.MAX_VALUE));
        ma.getMultivaluedString().addAll(List.of("&&&&", "bbbbbbb", "jqdslj qsdqs - .sqdks"));
        return ma;
    }

    public static void main(String[] args) throws IOException {
        initializeSampleFilesWithProxy();
        initializeSampleFile();
    }
}
