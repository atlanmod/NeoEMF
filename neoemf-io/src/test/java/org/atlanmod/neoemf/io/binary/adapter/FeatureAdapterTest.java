package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.commons.io.UnsignedShort;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.Converter;
import org.atlanmod.neoemf.io.binary.frame.WriterMetadata;
import org.atlanmod.neoemf.tests.iceage.IceagePackage;
import org.eclipse.emf.ecore.EClass;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.*;

class FeatureAdapterTest {

    @Test
    void writeOn() {
        /*
        ByteBuffer buffer = ByteBuffer.allocate(100000);
        EcoreAdapterProvider provider = new EcoreAdapterProvider();
        BufferWrangler wrangler = new BufferWrangler(provider, buffer);

        IceagePackage iceagePackage = IceagePackage.eINSTANCE;
        WriterMetadata metadata = new WriterMetadata(provider);
        EClass eClass = iceagePackage.getPrimitiveAttributes();
        ClassAdapter classAdapter = metadata.adapt(eClass);
        classAdapter.writeOn(wrangler);

        buffer.rewind();

        Converter<String> converter = provider.getStringConverter();
        String name = converter.readFrom(buffer);
        UnsignedShort id = UnsignedShort.fromShort(buffer.getShort());

        assertThat(name).isEqualTo(eClass.getName());
        assertThat(id.intValue()).isEqualTo(eClass.getClassifierID());

         */
    }
}