package org.atlanmod.neoemf.io.binary.serializer;

import org.atlanmod.commons.collect.Flags;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.atlanmod.neoemf.io.binary.adapter.ObjectAdapter;
import org.atlanmod.neoemf.io.binary.frame.WriterMetadata;
import org.atlanmod.neoemf.tests.iceage.IceageFactory;
import org.atlanmod.neoemf.tests.iceage.IceagePackage;
import org.atlanmod.neoemf.tests.iceage.PrimitiveAttributes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AttributeValuesSerializerTest {

    private BinaryAdapterProvider provider;
    private AttributeValuesSerializer serializer;
    private WriterMetadata metadata;
    private IceagePackage iceagePackage;
    private IceageFactory factory;
    private BufferWrangler buffer;

    @BeforeEach
    void init() {
        this.provider = new BinaryAdapterProvider();
        this.serializer = provider.createAttributeValuesSerializer();
        this.metadata = provider.createWriterMetadata();
        this.iceagePackage = IceagePackage.eINSTANCE;
        this.factory = iceagePackage.getIceageFactory();
        this.buffer = provider.allocateBuffer(200);

        metadata.adapt(iceagePackage);
    }

    @Test
    void testSerializeSingleObjectWithOneAttribute() {
        String expected = "To be or Not to be";
        PrimitiveAttributes pa = factory.createPrimitiveAttributes();
        pa.setAString(expected);
        ObjectAdapter adapter = metadata.adapt(pa);
        serializer.writeOn(buffer, adapter);

        buffer.rewind();
        // 1 - Class Index
        UnsignedVarInt classIndex = buffer.getUnsignedVarInt();
        // 2 - Flags
        // PrimitiveAttributes has 9 attributes, flags need 2 bytes
        byte[] bytes = new byte[2];
        buffer.get(bytes);

        // 3 - Get String Value
        String actual = buffer.getString();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testSerializeSingleObjectWithCorrectFlags() {
        PrimitiveAttributes newObject = factory.createPrimitiveAttributes();
        newObject.setAnInt(12345);
        newObject.setABoolean(false);

        ObjectAdapter adapter = metadata.adapt(newObject);
        Flags expected = adapter.classAdapter().createFlagsFor(adapter);

        serializer.writeOn(buffer, adapter);
        buffer.rewind();
        buffer.getUnsignedVarInt(); // Class Identifier

        // 2 - Flags
        // PrimitiveAttributes has 9 attributes, flags need 2 bytes
        byte[] bytes = new byte[2];
        buffer.get(bytes);
        Flags actual = Flags.fromBytes(bytes);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testSerializeSingleObjectWithCorrectClassIndex() {
        PrimitiveAttributes newObject = factory.createPrimitiveAttributes();
        ObjectAdapter adapter = metadata.adapt(newObject);
        int expected = adapter.classAdapter().index();

        serializer.writeOn(buffer, adapter);
        buffer.rewind();
        int actual = buffer.getUnsignedShort().intValue();

        assertThat(actual).isEqualTo(expected);
    }
}