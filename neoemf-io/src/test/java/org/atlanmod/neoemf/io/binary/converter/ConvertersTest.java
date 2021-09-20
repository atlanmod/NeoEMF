package org.atlanmod.neoemf.io.binary.converter;

import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.atlanmod.neoemf.io.binary.converter.Converters.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ConvertersTest {

    private static BinaryAdapterProvider provider = new BinaryAdapterProvider();
    private BufferWrangler buffer;

    @BeforeEach
    void init() {
        this.buffer = provider.allocateBuffer(100);
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void conversionOfBooleans(boolean expected) {
        BooleanConverter converter = new BooleanConverter();
        converter.writeOn(buffer, expected);

        buffer.rewind();
        boolean actual = converter.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(bytes = {0, 1, -1, Byte.MIN_VALUE, Byte.MAX_VALUE})
    void conversionOfBytes(byte expected) {
        ByteConverter converter = new ByteConverter();
        converter.writeOn(buffer, expected);

        buffer.rewind();
        byte actual = converter.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(chars = {Character.MAX_VALUE, Character.MIN_VALUE, 'a', '4', 'ù'})
    void conversionOfChars(char expected) {
        CharConverter converter = new CharConverter();
        converter.writeOn(buffer, expected);

        buffer.rewind();
        char actual = converter.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(shorts = {Short.MAX_VALUE, Short.MIN_VALUE, -1, 0, 1})
    void conversionOfShorts(short expected) {
        ShortConverter converter = new ShortConverter();
        converter.writeOn(buffer, expected);

        buffer.rewind();
        short actual = converter.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MAX_VALUE, Integer.MIN_VALUE, -1, 0, 1})
    void conversionOfInts(int expected) {
        IntConverter converter = new IntConverter();
        converter.writeOn(buffer, expected);

        buffer.rewind();
        int actual = converter.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(longs = {Long.MAX_VALUE, Long.MIN_VALUE, -1, 0, 1})
    void conversionOfInts(long expected) {
        LongConverter converter = new LongConverter();
        converter.writeOn(buffer, expected);

        buffer.rewind();
        long actual = converter.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(floats = {Float.MAX_VALUE, Float.MIN_VALUE, -1, 0, 1})
    void conversionOfFloats(float expected) {
        FloatConverter converter = new FloatConverter();
        converter.writeOn(buffer, expected);

        buffer.rewind();
        float actual = converter.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.MAX_VALUE, Double.MIN_VALUE, -1, 0, 1})
    void conversionOfDoubles(double expected) {
        DoubleConverter converter = new DoubleConverter();
        converter.writeOn(buffer, expected);

        buffer.rewind();
        double actual = converter.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("dateProvider")
    void conversionOfDates(Date expected) {
        DateConverter converter = new DateConverter();
        converter.writeOn(buffer, expected);

        buffer.rewind();
        Date actual = converter.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Date> dateProvider() {
        return Stream.of(Calendar.getInstance().getTime(), new Date(0));
    }

    @ParameterizedTest
    @MethodSource("bigIntegerProvider")
    void conversionOfBigIntegers(BigInteger expected) {
        BigIntegerConverter converter = new BigIntegerConverter();
        converter.writeOn(buffer, expected);

        buffer.rewind();
        BigInteger actual = converter.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }
    static Stream<BigInteger> bigIntegerProvider() {
        return Stream.of("-1", "0", "1", "54", "123456789123456789", "-2876873575675678")
                .map(BigInteger::new);
    }

    @ParameterizedTest
    @MethodSource("bigDecimalProvider")
    void conversionOfBigDecimal(BigDecimal expected) {
        BigDecimalConverter converter = new BigDecimalConverter();
        converter.writeOn(buffer, expected);

        buffer.rewind();
        BigDecimal actual = converter.readFrom(buffer);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<BigDecimal> bigDecimalProvider() {
        return Stream.of("-1", "0", "1", "54", "123456789123456789", "-2876873575675678",
                "1112222211.2222222211", "1098491072963113850.7436076939614540479",
                "1098491071975459529.6201509049614540479", "1112222210.2222222211",
                "1237038244911605079.77528397755061728521", "-1237038244911605079.77528397755061728521")
                .map(BigDecimal::new);
    }
}