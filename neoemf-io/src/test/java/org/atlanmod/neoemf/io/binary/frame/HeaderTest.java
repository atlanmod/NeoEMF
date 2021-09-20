package org.atlanmod.neoemf.io.binary.frame;

import org.atlanmod.commons.reflect.MoreReflection;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.atlanmod.commons.io.Numbers.uint;

class HeaderTest {

    @Test
    void writeOn() {
        BinaryAdapterProvider provider = new BinaryAdapterProvider();
        BufferWrangler buffer = provider.allocateBuffer(Header.SIZE);
        Header expected = Header.createDefaultHeader()
                .withMetadataLength(uint(222))
                .withAttributesLength(uint(10))
                .withReferencesLength(uint(20))
                .withIndexLength(uint(30));
        expected.writeOn(buffer);

        buffer.rewind();
        Header actual = MoreReflection.softInstantiate(Header.class);
        actual.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }
}