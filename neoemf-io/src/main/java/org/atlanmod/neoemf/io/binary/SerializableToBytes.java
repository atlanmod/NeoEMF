package org.atlanmod.neoemf.io.binary;

public interface SerializableToBytes {
    void writeOn(BufferWrangler buffer);

    void readFrom(BufferWrangler buffer);
}
