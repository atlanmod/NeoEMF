package org.atlanmod.neoemf.io.binary;

import org.atlanmod.commons.io.UnsignedInt;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.atlanmod.commons.io.Numbers.uvarint;

class BufferWranglerTest {

    private BinaryAdapterProvider provider;

    @BeforeEach
    void init() {
        this.provider = new BinaryAdapterProvider();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "To be or not to be", "!è§à"})
    void testPutGetString(String expected) {
        BufferWrangler buffer = provider.allocateBuffer(100);
        buffer.putString(expected);

        buffer.rewind();
        String actual = buffer.getString();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testPutGetListOfStrings() {
        BufferWrangler buffer = provider.allocateBuffer(100);
        List<String> expected = List.of("To be or not to be", "11183297", "Any String");
        for (String each : expected) {
            buffer.putString(each);
        }

        buffer.rewind();

        int length = expected.size();
        List<String> actual = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            actual.add(buffer.getString());
        }
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 1, 42, UnsignedInt.MAX_VALUE})
    void testPutGetUnsignedInt(long value) {
        BufferWrangler buffer = provider.allocateBuffer(100);
        UnsignedInt expected = UnsignedInt.fromLong(value);
        buffer.put(expected);

        buffer.rewind();
        UnsignedInt actual = buffer.getUnsignedInt();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testPutGetUnsignedVarInt() {
        BufferWrangler buffer = provider.allocateBuffer(100);
        UnsignedVarInt zero = uvarint(0);
        UnsignedVarInt maxInt = uvarint(Integer.MAX_VALUE);
        UnsignedVarInt maxShort = uvarint(Short.MAX_VALUE);
        buffer.put(zero)
                .put(maxInt)
                .put(maxShort)
                .rewind();

        assertThat(buffer.getUnsignedVarInt()).isEqualTo(zero);
        assertThat(buffer.getUnsignedVarInt()).isEqualTo(maxInt);
        assertThat(buffer.getUnsignedVarInt()).isEqualTo(maxShort);
    }
}