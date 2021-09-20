package org.atlanmod.neoemf.io.binary.converter;

import org.atlanmod.commons.Throwables;
import org.atlanmod.commons.collect.MoreArrays;
import org.atlanmod.commons.io.UnsignedByte;
import org.atlanmod.commons.primitive.*;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.CompressedInts;
import org.atlanmod.neoemf.io.binary.Converter;
import org.atlanmod.neoemf.io.binary.frame.Options;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EcorePackage;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.atlanmod.commons.Guards.checkArgument;
import static org.atlanmod.commons.Guards.checkInstanceOf;

public class Converters {
    private Map<Integer, Converter> converters = new HashMap<>();
    private Map<Integer, Converter> eListConverters = new HashMap<>();
    private Converter eNumConverter;
    private Converter stringConverter;
    private final Options options;

    public Converters() {
        this(Options.empty());
    }

    public Converters(Options options) {
        this.options = options;
        this.initialize();
    }

    private void initialize() {
        // @formatter:off
        converters.put(EcorePackage.EBOOLEAN,           new BooleanConverter());
        converters.put(EcorePackage.EBYTE,              new ByteConverter());
        converters.put(EcorePackage.ECHAR,              new CharConverter());
        converters.put(EcorePackage.EDOUBLE,            new DoubleConverter());
        converters.put(EcorePackage.EFLOAT,             new FloatConverter());
        converters.put(EcorePackage.ESHORT,             new ShortConverter());
        converters.put(EcorePackage.EINT,               new IntConverter());
        converters.put(EcorePackage.ELONG,              new LongConverter());
        converters.put(EcorePackage.EDATE,              new DateConverter());
        converters.put(EcorePackage.EBIG_INTEGER,       new BigIntegerConverter());
        converters.put(EcorePackage.EBIG_DECIMAL,       new BigDecimalConverter());
        converters.put(EcorePackage.EBYTE_ARRAY,        new ByteArrayConverter());
        converters.put(EcorePackage.EBOOLEAN_OBJECT,    new BooleanConverter());
        converters.put(EcorePackage.EBYTE_OBJECT,       new ByteConverter());
        converters.put(EcorePackage.ECHARACTER_OBJECT,  new ByteConverter());
        converters.put(EcorePackage.EDOUBLE_OBJECT,     new DoubleConverter());
        converters.put(EcorePackage.EFLOAT_OBJECT,      new FloatConverter());
        converters.put(EcorePackage.EINTEGER_OBJECT,    new IntConverter());
        converters.put(EcorePackage.ELONG_OBJECT,       new LongConverter());
        converters.put(EcorePackage.ESHORT_OBJECT,      new ShortConverter());
        converters.put(EcorePackage.EJAVA_OBJECT,       null); // Java Object is not Serializable.
        converters.put(EcorePackage.EJAVA_CLASS,        null);
        // @formatter:on

        eNumConverter = new ENumConverter();

        if (options.shouldUseUTF16()) {
            stringConverter = new SegmentalizedStringConverter(StandardCharsets.UTF_16);
        } else {
            stringConverter = new SegmentalizedStringConverter(StandardCharsets.UTF_8);
        }
        converters.put(EcorePackage.ESTRING, stringConverter);

        for (Map.Entry<Integer, Converter> each : this.converters.entrySet()) {
            eListConverters.put(each.getKey(), new EListConverter(each.getValue()));
        }

        eListConverters.put(EcorePackage.EFEATURE_MAP_ENTRY, null);
    }

    public Converter getPrimitiveTypeConverter(Integer i) {
        return this.converters.get(i);
    }

    public Converter getEListConverter(Integer i) {
        return this.eListConverters.get(i);
    }

    public Converter getEnumConverter() {
        return eNumConverter;
    }

    public Converter getStringConverter() {
        return stringConverter;
    }

    static class BooleanConverter implements Converter<Boolean> {
        @Override
        public byte[] toBytes(Object object) {
            checkArgument(Boolean.class.isInstance(object));

            return Booleans.toBytes((boolean) object);
        }

        @Override
        public void writeOn(BufferWrangler buffer, Boolean value) {
            assert Boolean.class.isInstance(value);
            assert buffer.remaining() >= 1;

            buffer.put(Booleans.toBytes((boolean) value));
        }

        @Override
        public Boolean readFrom(BufferWrangler buffer) {
            return Boolean.valueOf(buffer.get() != 0);
        }
    }

    static class ByteConverter implements Converter<Byte> {
        @Override
        public byte[] toBytes(Object object) {
            checkArgument(Byte.class.isInstance(object));

            byte[] bytes = {(byte) object};
            return bytes;
        }

        @Override
        public void writeOn(BufferWrangler buffer, Byte value) {
            assert Byte.class.isInstance(value);
            assert buffer.remaining() >= Byte.BYTES;

            buffer.put((byte) value);
        }

        @Override
        public Byte readFrom(BufferWrangler buffer) {
            return buffer.get();
        }

    }

    static class CharConverter implements Converter<Character> {
        @Override
        public byte[] toBytes(Object object) {
            return Chars.toBytes((char) object);
        }

        @Override
        public void writeOn(BufferWrangler buffer, Character value) {
            assert Character.class.isInstance(value);
            assert buffer.remaining() >= Character.BYTES;

            buffer.put(Chars.toBytes((char) value));
        }

        @Override
        public Character readFrom(BufferWrangler buffer) {
            byte[] bytes = new byte[Character.BYTES];
            buffer.get(bytes);

            return Bytes.toChar(bytes);
        }
    }

    static class ShortConverter implements Converter<Short> {
        @Override
        public byte[] toBytes(Object object) {
            checkArgument(Short.class.isInstance(object));
            return Shorts.toBytes((short) object);
        }

        @Override
        public void writeOn(BufferWrangler buffer, Short value) {
            assert Short.class.isInstance(value);
            assert buffer.remaining() >= Short.BYTES;

            buffer.put(Shorts.toBytes((short) value));
        }

        @Override
        public Short readFrom(BufferWrangler buffer) {
            byte[] bytes = new byte[Short.BYTES];
            buffer.get(bytes);

            return Bytes.toShort(bytes);
        }
    }

    static class IntConverter implements Converter<Integer> {
        @Override
        public byte[] toBytes(Object object) {
            checkArgument(Integer.class.isInstance(object));

            return Ints.toBytes((int) object);
        }

        @Override
        public void writeOn(BufferWrangler buffer, Integer value) {
            assert Integer.class.isInstance(value);
            assert buffer.remaining() >= Integer.BYTES;

            buffer.put(Ints.toBytes((int) value));
        }

        @Override
        public Integer readFrom(BufferWrangler buffer) {
            byte[] bytes = new byte[Integer.BYTES];
            buffer.get(bytes);

            return Bytes.toInt(bytes);
        }
    }

    static class LongConverter implements Converter<Long> {
        @Override
        public byte[] toBytes(Object object) {
            checkArgument(Long.class.isInstance(object));

            return Longs.toBytes((long) object);
        }

        @Override
        public void writeOn(BufferWrangler buffer, Long value) {
            assert Long.class.isInstance(value);
            assert buffer.remaining() >= Long.BYTES;

            buffer.put(Longs.toBytes((long) value));
        }

        @Override
        public Long readFrom(BufferWrangler buffer) {
            byte[] bytes = new byte[Long.BYTES];
            buffer.get(bytes);

            return Bytes.toLong(bytes);
        }
    }

    static class FloatConverter implements Converter<Float> {
        @Override
        public byte[] toBytes(Object object) {
            checkArgument(Float.class.isInstance(object));
            return Floats.toBytes((float) object);
        }

        @Override
        public void writeOn(BufferWrangler buffer, Float value) {
            assert Float.class.isInstance(value);
            assert buffer.remaining() >= Float.BYTES;

            buffer.put(Floats.toBytes((float) value));
        }

        @Override
        public Float readFrom(BufferWrangler buffer) {
            byte[] bytes = new byte[Float.BYTES];
            buffer.get(bytes);

            return Bytes.toFloat(bytes);
        }
    }

    static class DoubleConverter implements Converter<Double> {
        @Override
        public byte[] toBytes(Object object) {
            checkArgument(Double.class.isInstance(object));
            return Doubles.toBytes((double) object);
        }

        @Override
        public void writeOn(BufferWrangler buffer, Double value) {
            assert Double.class.isInstance(value);
            assert buffer.remaining() >= Double.BYTES;

            buffer.put(Doubles.toBytes((double) value));
        }

        @Override
        public Double readFrom(BufferWrangler buffer) {
            byte[] bytes = new byte[Double.BYTES];
            buffer.get(bytes);

            return Bytes.toDouble(bytes);
        }
    }

    static class DateConverter implements Converter<Date> {
        @Override
        public byte[] toBytes(Object object) {
            checkInstanceOf(object, Date.class);

            Date value = (Date) object;
            return Longs.toBytes(value.getTime());
        }

        @Override
        public void writeOn(BufferWrangler buffer, Date object) {
            assert buffer.remaining() >= Long.BYTES;

            Date value = (Date) object;
            buffer.put(Longs.toBytes(value.getTime()));
        }

        @Override
        public Date readFrom(BufferWrangler buffer) {
            byte[] bytes = new byte[Long.BYTES];
            buffer.get(bytes);

            return new Date(Bytes.toLong(bytes));
        }
    }

    static class BigIntegerConverter implements Converter<BigInteger> {
        @Override
        public byte[] toBytes(Object object) {
            checkInstanceOf(object, BigInteger.class);

            BigInteger value = (BigInteger) object;
            byte[] values = value.toByteArray();
            UnsignedByte length = UnsignedByte.fromInt(values.length);

            return MoreArrays.addAll(length.toBytes(), values);
        }

        @Override
        public void writeOn(BufferWrangler buffer, BigInteger value) {
            byte[] bytes = this.toBytes(value);
            assert buffer.remaining() >= bytes.length;
            buffer.put(bytes);
        }

        @Override
        public BigInteger readFrom(BufferWrangler buffer) {
            UnsignedByte length = buffer.getUnsignedByte();
            byte[] bytes = new byte[length.intValue()];
            buffer.get(bytes);

            return new BigInteger(bytes);
        }
    }

    static class BigDecimalConverter implements Converter<BigDecimal> {
        private final BigIntegerConverter bigIntegerToConverter = new BigIntegerConverter();

        @Override
        public byte[] toBytes(Object object) {
            checkInstanceOf(object, BigDecimal.class);
            BigDecimal value = (BigDecimal) object;

            return MoreArrays.addAll(Ints.toBytes(value.scale()),
                    bigIntegerToConverter.toBytes(value.unscaledValue()));
        }

        @Override
        public void writeOn(BufferWrangler buffer, BigDecimal value) {
            byte[] bytes = this.toBytes(value);

            assert buffer.remaining() >= bytes.length;

            buffer.put(bytes);
        }

        @Override
        public BigDecimal readFrom(BufferWrangler buffer) {
            byte[] bytes = new byte[Integer.BYTES];
            buffer.get(bytes);
            int scale = Bytes.toInt(bytes);
            BigInteger unscaled = bigIntegerToConverter.readFrom(buffer);
            return new BigDecimal(unscaled, scale);
        }
    }

    static class ByteArrayConverter implements Converter {
        @Override
        public byte[] toBytes(Object object) {
            checkInstanceOf(object, byte[].class);
            byte[] value = (byte[]) object;
            return MoreArrays.addAll(CompressedInts.toBytes(value.length), value);
        }

        @Override
        public void writeOn(BufferWrangler buffer, Object value) {
            byte[] bytes = this.toBytes(value);

            assert buffer.remaining() >= bytes.length;

            buffer.put(bytes);
        }

        @Override
        public Object readFrom(BufferWrangler buffer) {
            throw Throwables.notImplementedYet("readFrom");

        }
    }

    private static class EListConverter implements Converter {
        private final Converter converter;

        public EListConverter(Converter converter) {
            this.converter = converter;
        }

        @Override
        public byte[] toBytes(Object object) {
            checkInstanceOf(object, EList.class);
            EList<Object> elements = (EList<Object>) object;
            byte[] serialized = CompressedInts.toBytes(elements.size());
            for (Object each : elements) {
                serialized = MoreArrays.addAll(serialized, converter.toBytes(each));
            }
            return serialized;
        }

        @Override
        public void writeOn(BufferWrangler buffer, Object value) {
            byte[] bytes = this.toBytes(value);

            assert buffer.remaining() >= bytes.length;

            buffer.put(bytes);
        }

        @Override
        public Object readFrom(BufferWrangler buffer) {
            return null;
        }
    }
}
