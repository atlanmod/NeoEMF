package org.atlanmod.neoemf.io.binary.converter;

import org.atlanmod.commons.Guards;
import org.atlanmod.commons.collect.MoreArrays;
import org.atlanmod.commons.io.UnsignedByte;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.commons.log.Log;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.Converter;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.atlanmod.commons.Guards.checkNotNull;
import static org.atlanmod.commons.io.Numbers.uvarint;

public class StringConverter implements Converter<String> {

    private final Charset charset;

    public StringConverter() {
        this(StandardCharsets.UTF_8);
    }

    public StringConverter(Charset charset) {
        this.charset = charset;
    }

    @Override
    public byte[] toBytes(Object obj) {
        Guards.checkInstanceOf(obj, String.class);
        String value = (String) obj;

        byte[] bytes = value.getBytes(charset);
        UnsignedVarInt length = uvarint(bytes.length);

        return MoreArrays.addAll(length.toBytes(), bytes);
    }

    @Override
    public void writeOn(BufferWrangler buffer, String value) {
        //Log.info(value);
        checkNotNull(value, "value");

        byte[] bytes = value.getBytes(charset);
        UnsignedVarInt length = uvarint(bytes.length);
        buffer.put(length.toBytes());
        buffer.put(bytes);
    }

    @Override
    public String readFrom(BufferWrangler buffer) {
        UnsignedVarInt length = buffer.getUnsignedVarInt();
        byte[] bytes = new byte[length.intValue()];
        buffer.get(bytes);
        return new String(bytes, charset);
    }
}
