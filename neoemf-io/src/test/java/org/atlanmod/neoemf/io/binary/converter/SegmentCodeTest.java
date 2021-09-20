package org.atlanmod.neoemf.io.binary.converter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SegmentCodeTest {

    @Test
    void isStringLength() {
        byte expected = 13;
        SegmentCode code  = new SegmentCode(expected);
        byte actual = code.value();

        assertThat(actual).isEqualTo(expected);
        assertThat(code.isStringLength()).isTrue();
        assertThat(code.isDelimiter()).isFalse();
        assertThat(code.isReference()).isFalse();
    }

    @Test
    void isReference() {
        byte expected = 13;
        int withReferenceCode = 0x40 | expected;
        SegmentCode code = new SegmentCode((byte) withReferenceCode);
        byte actual = code.value();

        assertThat(actual).isEqualTo(expected);
        assertThat(code.isReference()).isTrue();
        assertThat(code.isDelimiter()).isFalse();
        assertThat(code.isStringLength()).isFalse();
    }

    @Test
    void isDelimiter() {
        byte expected = 13;
        byte withDelimiterCode = (byte) (0xC0 | expected);
        SegmentCode code = new SegmentCode((byte)0xC0);
        byte actual = code.value();

        //assertThat(actual).isEqualTo(expected);
        assertThat(code.isDelimiter()).isTrue();
        assertThat(code.isReference()).isFalse();
        assertThat(code.isStringLength()).isFalse();
    }

    @Test
    void testCleanValue() {
        byte expected = (byte) 0xC0;
        byte actual = SegmentCode.cleanValue((byte) 0xC0);
        assertThat(actual).isEqualTo(expected);
        System.out.println(actual);
    }
}