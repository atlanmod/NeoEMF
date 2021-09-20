package org.atlanmod.neoemf.io.binary.frame;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionsTest {

    @Test
    void initialEncodingIsUTF_8() {
        Options options = new Options();
        assertThat(options.shouldUseUTF8()).isTrue();
        assertThat(options.shouldUseUTF16()).isFalse();
    }

    @Test
    void useUTF16ShouldUnsetUTF8() {
        Options options = new Options();
        options.useUTF16();
        assertThat(options.shouldUseUTF16()).isTrue();
        assertThat(options.shouldUseUTF8()).isFalse();
    }

    @Test
    void useUTF8ShouldUnsetUTF16() {
        Options options = new Options();
        options.useUTF8();
        assertThat(options.shouldUseUTF8()).isTrue();
        assertThat(options.shouldUseUTF16()).isFalse();
    }

    @Test
    void decodingAfterEncodingShouldCreateSameObject() {
        Options expected = new Options();
        expected.useUTF16();
        ByteBuffer buffer = ByteBuffer.wrap(new byte[Options.BYTES]);
        expected.writeOn(buffer);
        buffer.rewind();
        byte[] bytes = new byte[Options.BYTES];
        buffer.get(bytes);
        Options actual = Options.fromBytes(bytes);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testCreateEmptyOptions() {
        Options empty =  Options.empty();

        assertThat(empty.shouldUseUTF8()).isTrue();
        assertThat(empty.shouldUseUTF16()).isFalse();
    }
}
