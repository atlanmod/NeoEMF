package org.atlanmod.neoemf.io.binary.converter;

import org.atlanmod.commons.Guards;
import org.atlanmod.commons.Throwables;
import org.atlanmod.neoemf.io.binary.BufferWrangler;

class Delimiter extends Segment {
    public static final int FIRST_DELIMITER = 0x20;
    public static final int LAST_DELIMITER = 0x2F;
    public static final int DELIMITER_MARK = 0xC0;
    private static final Delimiter[] cache = new Delimiter[LAST_DELIMITER - FIRST_DELIMITER + 1];
    private final char value;

    static {
        initializeCache();
    }

    public static boolean isDelimiter(char c) {
        return (c >= FIRST_DELIMITER && c <= LAST_DELIMITER);
    }

    private Delimiter(char c) {
        this.value = c;
    }

    public byte encode() {
        final int encoded = (value - FIRST_DELIMITER) | DELIMITER_MARK;
        return (byte) encoded;
    }

    private static void initializeCache() {
        for (int i = 0; i < cache.length; i++) {
            char each = (char) (FIRST_DELIMITER + i);
            cache[i] = new Delimiter(each);
        }
    }

    public static Delimiter valueOf(char c) {
        Guards.checkArgument(c >= FIRST_DELIMITER, "Expecting {0} to be greater than or equal to {1}", FIRST_DELIMITER);
        Guards.checkArgument(c <= LAST_DELIMITER, "Expecting {0} to be less than or equal to {1}", LAST_DELIMITER);

        int pos = c - FIRST_DELIMITER;
        return cache[pos];
    }

    public static char charAt(int i) {
        Guards.checkPositionIndex(i, cache.length);
        return cache[i].value;
    }

    @Override
    public void writeOn(BufferWrangler buffer) {
        buffer.put(this.encode());
    }

    @Override
    public void readFrom(BufferWrangler buffer) {
        throw Throwables.notImplementedYet("Delimiter::readFrom()");
    }
}
