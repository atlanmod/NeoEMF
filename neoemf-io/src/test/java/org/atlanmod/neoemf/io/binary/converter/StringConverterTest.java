package org.atlanmod.neoemf.io.binary.converter;

import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class StringConverterTest {

    private StringConverter converter = new StringConverter();
    private BinaryAdapterProvider provider = new BinaryAdapterProvider();
    private BufferWrangler buffer;

    @BeforeEach
    void init() {
        this.buffer = provider.allocateBuffer(100);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdefghijklmopq", "&éàçè§$€*£æ"})
    void testWriteReadOnByteBuffer(String expected) {
        converter.writeOn(buffer, expected);
        buffer.rewind();
        String actual = converter.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }
}