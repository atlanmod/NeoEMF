package org.atlanmod.neoemf.io.binary.serializer;

import org.atlanmod.commons.io.UnsignedByte;
import org.atlanmod.commons.io.UnsignedInt;
import org.atlanmod.commons.io.UnsignedShort;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.adapter.ClassAdapter;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.atlanmod.neoemf.io.binary.adapter.ObjectAdapter;
import org.atlanmod.neoemf.io.binary.frame.ReaderMetadata;
import org.atlanmod.neoemf.io.binary.frame.WriterMetadata;
import org.atlanmod.neoemf.tests.iceage.IceageFactory;
import org.atlanmod.neoemf.tests.iceage.IceagePackage;
import org.atlanmod.neoemf.tests.iceage.MultivaluedAttributes;
import org.atlanmod.neoemf.tests.iceage.PrimitiveAttributes;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AttributeSerializationTest {
    private BinaryAdapterProvider provider;
    private AttributeValuesSerializer serializer;
    private WriterMetadata writerMetadata;
    private ReaderMetadata readerMetadata;
    private IceagePackage iceagePackage;
    private IceageFactory factory;
    private BufferWrangler buffer;

    @BeforeEach
    void init() {
        this.provider = new BinaryAdapterProvider();
        this.serializer = provider.createAttributeValuesSerializer();
        this.writerMetadata = provider.createWriterMetadata();
        this.readerMetadata = provider.createReaderMetadata();
        this.iceagePackage = IceagePackage.eINSTANCE;
        this.factory = iceagePackage.getIceageFactory();
        this.buffer = provider.allocateBuffer(1000);
        writerMetadata.adapt(iceagePackage);
        readerMetadata.onPreamble(UnsignedByte.fromInt(1), UnsignedShort.fromInt(5), UnsignedInt.fromInt(100));
        readerMetadata.onPackage(0, null, iceagePackage.getNsURI());
    }

    @Test
    void testSerializeSingleObjectWithSingleValuedAttributes() {
        PrimitiveAttributes expected = factory.createPrimitiveAttributes();
        expected.setAString("To be, or not to be:");
        expected.setAnInt(1);
        expected.setABoolean(true);
        expected.setAShort(Short.MAX_VALUE);
        expected.setALong(123L);
        expected.setAChar('é');
        expected.setADouble(12343.22);
        expected.setAFloat(1238.87e10f);
        expected.setAByte(Byte.MIN_VALUE);

        ObjectAdapter adapter = writerMetadata.adapt(expected);
        serializer.writeOn(buffer, adapter);

        buffer.rewind();

        ClassAdapter classAdapter = adapter.classAdapter();
        readerMetadata.onClass(adapter.index(), classAdapter.id(), classAdapter.name(), UnsignedInt.fromInt(1));
        AttributeValuesDeserializer deserializer = new AttributeValuesDeserializer(provider, readerMetadata);
        PrimitiveAttributes actual = (PrimitiveAttributes) deserializer.readFrom(buffer);
        assertThat(EcoreUtil.equals(actual, expected)).isTrue();
    }

    @Test
    void testSerializeSingleObjectWithMultivaluedAttributes() {
        MultivaluedAttributes expected = factory.createMultivaluedAttributes();
        expected.getMultivaluedString().addAll(List.of("To be, or not to be: ",
                "that is the question:",
                "Whether 'tis nobler in the mind to suffer",
                "The slings and arrows of outrageous fortune,",
                "Or to take arms against a sea of troubles",
                "And by opposing end them? To die: to sleep;",
                "No more; and by a sleep to say we end",
                "The heart-ache and the thousand natural shocks")
                );

        expected.getMultivaluedInt().addAll(List.of(0, -1, 1, Integer.MIN_VALUE, Integer.MAX_VALUE));
        expected.getMultivaluedDouble().addAll(List.of(0., Double.MIN_VALUE, Double.MAX_VALUE));

        ObjectAdapter adapter = writerMetadata.adapt(expected);
        serializer.writeOn(buffer, adapter);

        buffer.rewind();

        ClassAdapter classAdapter = adapter.classAdapter();
        readerMetadata.onClass(adapter.index(), classAdapter.id(), classAdapter.name(), UnsignedInt.fromInt(1));
        AttributeValuesDeserializer deserializer = new AttributeValuesDeserializer(provider, readerMetadata);
        EObject actual = deserializer.readFrom(buffer);
        assertThat(EcoreUtil.equals(actual, expected)).isTrue();
    }
}
