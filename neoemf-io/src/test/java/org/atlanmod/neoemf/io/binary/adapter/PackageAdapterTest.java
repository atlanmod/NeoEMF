package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.identifier.PackageIdentifier;
import org.atlanmod.neoemf.tests.iceage.IceagePackage;
import org.eclipse.emf.ecore.EPackage;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;

class PackageAdapterTest {

    @Test
    void writeAndReadOnFromByteBuffer() {
        /*
        ByteBuffer buffer = ByteBuffer.allocate(100000);
        EcoreAdapterProvider provider = new EcoreAdapterProvider();
        BufferWrangler wrangler = new BufferWrangler(provider, buffer);

        EPackage ePackage = IceagePackage.eINSTANCE;
        EPackageAdapter expected = provider.createEPackageAdapter(ePackage);
        expected.writeOn(wrangler);

        buffer.rewind();

        EPackageAdapter actual = new EPackageAdapter(new PackageIdentifier(0, provider, ePackage));
        actual.readFrom(buffer);
        assertThat(actual).isEqualTo(expected);
*/
    }
}