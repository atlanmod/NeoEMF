package fr.inria.atlanmod.neoemf.io.writer.binary;

import fr.inria.atlanmod.neoemf.io.Migrator;
import fr.inria.atlanmod.neoemf.io.provider.UriProvider;
import fr.inria.atlanmod.neoemf.io.util.ResourceManager;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;
import org.atlanmod.commons.AbstractFileBasedTest;
import org.atlanmod.commons.log.Log;
import org.atlanmod.commons.primitive.Strings;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BinaryWriterTest extends AbstractFileBasedTest {

    @BeforeAll
    static void registerPackages() {
        ResourceManager.registerAllPackages();
    }

    /**
     * Checks the copy from a XMI file to another.
     *
     * @param uri            the URI of the file to copy
     */
    @ParameterizedTest(name = "[{index}] source = {0} ; useCompression = {1}")
    @ArgumentsSource(UriProvider.All.class)
    void testBinCopy(URI uri) throws IOException {
        final File targetFile = new File(currentTempFile() + ".bin");
        final File xmiTarget = new File(currentTempFile() + ".xmi");
        Log.info("Exporting XMI to {0}", targetFile);

        try (InputStream in = new URL(uri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toBin(targetFile)
                    .migrate();
        }

        Migrator.fromBin(targetFile)
                .toXmi(xmiTarget)
                .migrate();

    }

    @Test
    void testLongFile() throws IOException {
        final String sourceFile = "file:///Users/sunye/.neoemf/benchmarks/resources/org.eclipse.jdt.source.all.xmi";
        final File targetFile = new File("/Users/sunye/.neoemf/benchmarks/resources/org.eclipse.jdt.source.all.bin");

        try (InputStream in = new URL(sourceFile).openStream()) {
            Migrator.fromXmi(in)
                    .toBin(targetFile)
                    .migrate();
        }
    }

    @Test
    void copyJDTSourceAll() throws IOException {
        final String sourceFile = "file:///Users/sunye/.neoemf/benchmarks/resources/org.eclipse.jdt.source.all.xmi";
        final String targetFile = "file:///Users/sunye/.neoemf/benchmarks/resources/org.eclipse.jdt.source.all.emf";

        ResourceSet rset = new ResourceSetImpl();
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("xmi", new XMIResourceFactoryImpl());
        m.put("emf", new ResourceFactoryImpl() {
            @Override
            public Resource createResource(URI uri) {
                return new BinaryResourceImpl(uri);
            }
        });
        Resource xmiResource = rset.createResource(URI.createURI(sourceFile));
        Resource binResource = rset.createResource(URI.createURI(targetFile));

        Log.info("Start loading XMI resource");
        xmiResource.load(null);

        Log.info("Copying contents");
        binResource.getContents().addAll(xmiResource.getContents());

        Log.info("Start saving BIN resource");
        binResource.save(Collections.emptyMap());
    }

    @Test
    void test() {
        System.out.println(Arrays.toString(JavaPackage.eINSTANCE.getFieldDeclaration().getEAllAttributes().toArray()));
        ByteBuffer buffer = ByteBuffer.allocate(100);
        System.out.println(buffer.position());
        buffer.get(new byte[0]);
        System.out.println(buffer.position());
    }
}