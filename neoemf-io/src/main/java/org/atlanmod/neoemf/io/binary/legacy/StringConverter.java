package org.atlanmod.neoemf.io.binary.legacy;

import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.CompressedInts;
import org.atlanmod.commons.primitive.Chars;
import org.atlanmod.neoemf.io.binary.Converter;

import java.nio.ByteBuffer;

public class StringConverter implements Converter<String> {

    public StringConverter( ) {
    }

    @Override
    public byte[] toBytes(Object object) {
        return new byte[0];
    }

    @Override
    public void writeOn(BufferWrangler buffer, String value) {

        if (value == null) {
            buffer.put(Converter.NULL);
            return;
        }

        // 1 - Length
        int length = value.length();
        buffer.put(CompressedInts.toBytes(length + 1));

        // 2 - Contents
        char[] characters = new char[length];
        value.getChars(0, length, characters, 0);
        LOOP:
        for (int i = 0; i < length; ++i) {
            char character = characters[i];
            if (character == 0 || character > 0xFF) {
                buffer.put((byte)0);
                buffer.put(Chars.toBytes(character));
                while (++i < length) {
                    buffer.put(Chars.toBytes(characters[i]));
                }
                break LOOP;
            } else {
                buffer.put((byte) character);
            }
        }
    }

    @Override
    public String readFrom(BufferWrangler buffer) {
        return null;
    }

    @Override
    public String toString() {
        return "StringConverter{}";
    }
}
