package org.atlanmod.neoemf.io.binary;

import java.nio.ByteBuffer;

public interface Converter<T> {

    byte NULL = 0;

    byte[] toBytes(Object object);

    void writeOn(BufferWrangler buffer, T value);

    T readFrom(BufferWrangler buffer);

}
