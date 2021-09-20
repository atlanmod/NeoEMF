package org.atlanmod.neoemf.io.binary.identifier;

import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.atlanmod.neoemf.tests.iceage.IceagePackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;


class PropertiesTest {

    @Test
    void test() {
        IceagePackage pack = IceagePackage.eINSTANCE;
        EAttribute attr = pack.getDifferentIntProperties_ATransientInt();
        Properties properties = new Properties(attr);
        byte actual = properties.toBytes()[0];

        // bit 0 is set;
        assertThat((actual & 1)).isNotZero();

        JavaPackage p = JavaPackage.eINSTANCE;
        System.out.println(pack.getNsURI());
        System.out.println(pack.getComplexAttributes().getClassifierID());
        System.out.println(EcoreUtil.getURI(pack));
    }

    @Test
    void testConstructPropertiesFromReference() {
        IceagePackage pack = IceagePackage.eINSTANCE;
        EReference ref = pack.getElement_Herd();
        Properties properties = new Properties(ref);

        byte actual = properties.toBytes()[0];

        // bit 0 is not set;
        assertThat((actual & 1)).isZero();
    }

    @Test
    void toAndFromBytes() {
        IceagePackage pack = IceagePackage.eINSTANCE;
        EAttribute attr = pack.getDifferentIntProperties_ATransientInt();
        Properties expected = new Properties(attr);
        byte[] bytes = expected.toBytes();
        Properties actual = Properties.fromBytes(bytes);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void writeToBufferAndReadBack() {
        IceagePackage pack = IceagePackage.eINSTANCE;
        EAttribute attr = pack.getDifferentIntProperties_ATransientInt();
        Properties expected = new Properties(attr);
        BinaryAdapterProvider provider = new BinaryAdapterProvider();
        BufferWrangler buffer = provider.readerFor(ByteBuffer.allocate(100));

        expected.writeOn(buffer);
        buffer.rewind();

        Properties actual = Properties.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }
}