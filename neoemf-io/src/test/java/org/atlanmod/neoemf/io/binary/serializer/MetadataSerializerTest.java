package org.atlanmod.neoemf.io.binary.serializer;

import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.adapter.PackageAdapter;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.atlanmod.neoemf.io.binary.frame.ReaderMetadata;
import org.atlanmod.neoemf.io.binary.frame.WriterMetadata;
import org.atlanmod.neoemf.tests.iceage.IceagePackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;

public class MetadataSerializerTest {

    @Test
    void tesWriteToBuffer() {
        BinaryAdapterProvider provider = new BinaryAdapterProvider();
        WriterMetadata writtenMetadata = provider.createWriterMetadata();
        IceagePackage ePackage = IceagePackage.eINSTANCE;
        PackageAdapter packageAdapter = writtenMetadata.adapt(ePackage);
        for (EClassifier each : ePackage.getEClassifiers()) {
            writtenMetadata.adapt((EClass) each);
        }

        ByteBuffer buffer = ByteBuffer.allocate(10000);
        BufferWrangler wrangler = provider.writerFor(buffer);
        MetadataSerializer serializer = new MetadataSerializer(wrangler, writtenMetadata);
        serializer.write();

        buffer.rewind();

        MetadataDeserializer deserializer = new MetadataDeserializer(provider, wrangler);
        ReaderMetadata readMetadata = deserializer.read();

        assertThat(readMetadata.isCompatibleWith(writtenMetadata)).isTrue();
    }

    @Test
    void testWriteToBufferWithTwoPackages() {
        BinaryAdapterProvider provider = new BinaryAdapterProvider();
        WriterMetadata writtenMetadata = provider.createWriterMetadata();
        IceagePackage ePackage = IceagePackage.eINSTANCE;
        PackageAdapter packageAdapter = writtenMetadata.adapt(ePackage);
        for (EClassifier each : ePackage.getEClassifiers()) {
            writtenMetadata.adapt((EClass) each);
        }

        JavaPackage javaPackage = JavaPackage.eINSTANCE;
        writtenMetadata.adapt(javaPackage);
        for (EClassifier each : javaPackage.getEClassifiers()) {
            if (each instanceof EClass)
                writtenMetadata.adapt((EClass) each);
        }

        ByteBuffer buffer = ByteBuffer.allocate(60000);
        BufferWrangler wrangler = provider.writerFor(buffer);
        MetadataSerializer serializer = new MetadataSerializer(wrangler, writtenMetadata);
        serializer.write();

        buffer.rewind();

        MetadataDeserializer deserializer = new MetadataDeserializer(provider, wrangler);
        ReaderMetadata readMetadata = deserializer.read();

        assertThat(readMetadata.isCompatibleWith(writtenMetadata)).isTrue();

        System.out.println(readMetadata);
    }

    @Test
    void test() {
        JavaPackage javaPackage = JavaPackage.eINSTANCE;

        EClass eClass = javaPackage.getEClassifiers().stream().filter((x) -> x.getName().equals("UnresolvedTypeDeclaration")).map(x -> (EClass) x).findFirst().get();

        eClass.getEAllStructuralFeatures().stream()
                .map(EStructuralFeature::getFeatureID)
                .map(i -> eClass.getEStructuralFeature(i))
                .map(EStructuralFeature::getFeatureID)
                .forEach(System.out::println);


/*                .map(EClass::)
                .flatMap(x -> x.stream())
                .map(EStructuralFeature::getFeatureID)
                .collect(Collectors.toList());
        ids.stream().*/

    }
}
