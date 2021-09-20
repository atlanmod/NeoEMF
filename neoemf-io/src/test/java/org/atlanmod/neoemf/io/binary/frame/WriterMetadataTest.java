package org.atlanmod.neoemf.io.binary.frame;

import org.atlanmod.neoemf.io.binary.adapter.ClassAdapter;
import org.atlanmod.neoemf.io.binary.adapter.PackageAdapter;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.atlanmod.neoemf.tests.iceage.IceagePackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WriterMetadataTest {
    private BinaryAdapterProvider provider = new BinaryAdapterProvider();

    @Test
    void testAdaptPackage() {
        WriterMetadata metadata = provider.createWriterMetadata();
        IceagePackage iceage = IceagePackage.eINSTANCE;
        PackageAdapter adapter = metadata.adapt(iceage);

        assertThat(metadata.packages()).containsOnlyKeys(iceage);
    }

    @Test
    void testAdaptClass() {
        BinaryAdapterProvider provider = new BinaryAdapterProvider();
        WriterMetadata metadata = provider.createWriterMetadata();
        IceagePackage ePackage = IceagePackage.eINSTANCE;
        PackageAdapter packageAdapter = metadata.adapt(ePackage);
        List<ClassAdapter> adapters = new ArrayList<>();

        for (EClassifier each: ePackage.getEClassifiers()) {
            adapters.add(metadata.adapt((EClass) each));
        }
        assertThat(packageAdapter.classes().values()).containsAll(adapters);
    }
}