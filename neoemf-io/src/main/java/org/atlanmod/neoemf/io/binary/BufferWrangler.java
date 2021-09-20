package org.atlanmod.neoemf.io.binary;

import org.atlanmod.commons.io.*;
import org.atlanmod.commons.reflect.MoreReflection;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.atlanmod.neoemf.io.binary.frame.Header;

import java.nio.ByteBuffer;

/**
 * Decorator for {@code ByteBuffer}
 *
 * @see ByteBuffer
 */
public class BufferWrangler {
    protected final ByteBuffer buffer;
    protected final Converter<String> stringConverter;

    public BufferWrangler(BinaryAdapterProvider provider, ByteBuffer buffer) {
        this.buffer = buffer;
        this.stringConverter = provider.getStringConverter();
    }

    public BufferWrangler put(UnsignedNumber value) {
        value.writeOn(buffer);

        return this;
    }

    public BufferWrangler put(SerializableToBytes serializable) {
        serializable.writeOn(this);
        return this;
    }

    public <T extends SerializableToBytes> T get(Class<T> klass) {
        T serializable = MoreReflection.softInstantiate(klass);
        serializable.readFrom(this);
        return serializable;
    }

    public BufferWrangler put(byte[] src) {
        buffer.put(src);
        return this;
    }

    public BufferWrangler put(Object value, Converter converter) {
        buffer.put(converter.toBytes(value));
        return this;
    }

    public BufferWrangler putLong(long value) {
        buffer.putLong(value);
        return this;
    }

    public byte get() {
        return buffer.get();
    }

    public BufferWrangler get(byte[] dst) {
        buffer.get(dst);
        return this;
    }

    public ByteBuffer toByteBuffer() {
        return buffer;
    }

    // region String
    public void putString(String value) {
        stringConverter.writeOn(this, value);
    }

    public String getString() {
        return stringConverter.readFrom(this);
    }
    // endregion

    public UnsignedByte getUnsignedByte() {
        return UnsignedByte.fromByte(buffer.get());
    }

    public UnsignedInt getUnsignedInt() {
        return UnsignedInt.fromInt(buffer.getInt());
    }

    public UnsignedShort getUnsignedShort() {
        return UnsignedShort.fromShort(buffer.getShort());
    }

    public UnsignedVarInt getUnsignedVarInt() {
        return UnsignedVarInt.fromByteBuffer(buffer);
    }

    public BufferWrangler rewind() {
        buffer.rewind();
        return this;
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    public int position() {
        return buffer.position();
    }

    public byte[] array() {
        return buffer.array();
    }

    public int remaining() {
        return buffer.remaining();
    }

    public BufferWrangler put(byte value) {
        buffer.put(value);
        return this;
    }

    public BufferWrangler clear() {
        buffer.clear();
        return this;
    }

    public BufferWrangler flip() {
        buffer.flip();
        return this;
    }

    public Long getLong() {
        return buffer.getLong();
    }
}
