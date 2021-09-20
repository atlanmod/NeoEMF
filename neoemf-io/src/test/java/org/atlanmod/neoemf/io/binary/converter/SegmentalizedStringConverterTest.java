package org.atlanmod.neoemf.io.binary.converter;

import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class SegmentalizedStringConverterTest {

    private SegmentalizedStringConverter converter;
    private BinaryAdapterProvider provider = new BinaryAdapterProvider();

    @BeforeEach
    void init() {
        converter = new SegmentalizedStringConverter(StandardCharsets.UTF_8);
    }

    @Test
    void writeReadSingleString() {
        BufferWrangler buffer = provider.allocateBuffer(400);
        final String expected = "AAAA";
        converter.writeOn(buffer, expected);
        buffer.rewind();
        String actual = converter.readFrom(buffer);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"AAAA BBBB", "AAAA/BBBB", "AAAA/BBBB/AAAA"})
    void writeReadStringWithSegment(String expected) {
        BufferWrangler buffer = provider.allocateBuffer(400);
        converter.writeOn(buffer, expected);
        buffer.rewind();
        String actual = converter.readFrom(buffer);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ@ABCDEFGHIJKLMNOPQRSTUVWXYZ@ABCDEFGHIJKLMNOPQRSTUVWXYZ"})
    void writeReadStringWithLongSegment(String expected) {
        BufferWrangler buffer = provider.allocateBuffer(400);
        converter.writeOn(buffer, expected);
        buffer.rewind();
        String actual = converter.readFrom(buffer);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testTokenize() {
        Collection<Segment> segments = converter.tokenize("aaa bbb");
        assertThat(segments.size()).isEqualTo(3);
    }

    @Test
    void testTokenizeWithLongString() {
        String str = "1234567890123456789012345678901234567890123456789012345678901234567890ETER";
        Collection<Segment> segments = converter.tokenize(str);
        assertThat(segments.size()).isEqualTo(1);
    }

    @Test
    void testSerializationWithLongText() {
        String expected = "The Java programming language also provides operators that perform bitwise and bit shift operations on integral types. The operators discussed in this section are less commonly used. Therefore, their coverage is brief; the intent is to simply make you aware that these operators exist.\n" +
                "\n" +
                "The unary bitwise complement operator \"~\" inverts a bit pattern; it can be applied to any of the integral types, making every \"0\" a \"1\" and every \"1\" a \"0\". For example, a byte contains 8 bits; applying this operator to a value whose bit pattern is \"00000000\" would change its pattern to \"11111111\".\n" +
                "\n" +
                "The signed left shift operator \"<<\" shifts a bit pattern to the left, and the signed right shift operator \">>\" shifts a bit pattern to the right. The bit pattern is given by the left-hand operand, and the number of positions to shift by the right-hand operand. The unsigned right shift operator \">>>\" shifts a zero into the leftmost position, while the leftmost position after \">>\" depends on sign extension.\n" +
                "\n" +
                "The bitwise & operator performs a bitwise AND operation.\n" +
                "\n" +
                "The bitwise ^ operator performs a bitwise exclusive OR operation.\n" +
                "\n" +
                "The bitwise | operator performs a bitwise inclusive OR operation.\n" +
                "\n" +
                "The following program, BitDemo, uses the bitwise AND operator to print the number \"2\" to standard output.";
        BufferWrangler buffer = provider.allocateBuffer(expected.length()*2);
        converter.writeOn(buffer, expected);
        System.out.println("Buffer position: " + buffer.position());
        System.out.println("String size: " + expected.length());
        buffer.rewind();
        String actual = converter.readFrom(buffer);
        assertThat(actual).isEqualTo(expected);
    }
}