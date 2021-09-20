package org.atlanmod.neoemf.io.binary.converter;

/**
 *
 * +--+--+--+--+--+--+--+--+
 * |Code |    Value       |
 * +--+--+--+--+--+--+--+--+
 *
 * Code:
 * 00 - 0x00 + value - The Value represents the Length of the String Segment
 * 01 - 0x40 + value - The Value represents a pointer/reference to a previous written String Segment (lower than 64)
 * 10 - 0x80 + value - Not used
 * 11 - 0xC0 + value - The Value represents a Delimiter.
 *
 */
class SegmentCode {
    private static final byte CODE_MASK = (byte) 0xC0;      // 0xC0 == 1100 0000
    private static final byte VALUE_MASK = (byte) 0x3F;     // 0x3F == 0011 1111
    private static final byte STRING_CODE = (byte) 0x00;    // 0X00 == 0000 0000
    private static final byte REFERENCE_CODE = (byte) 0x40; // 0x40 == 0100 0000
    private static final byte DELIMITER_CODE = (byte) 0xC0; // 0xC0 == 1100 0000
    private final byte value;
    private final byte code;

    public SegmentCode(byte codeWithValue) {
        this.value = cleanCode(codeWithValue);
        this.code = cleanValue(codeWithValue);
    }

    public boolean isStringLength() {
        return code == STRING_CODE;
    }

    public boolean isReference() {
        return code == REFERENCE_CODE;
    }

    public boolean isDelimiter() {
        return code == DELIMITER_CODE;
    }

    public byte value() {
        return value;
    }

    public static byte cleanCode(byte valueWithCode) {
        int clean = valueWithCode & VALUE_MASK;
        return (byte) clean;
    }

    public static byte cleanValue(byte valueWithCode) {
        int clean = valueWithCode & CODE_MASK;
        return (byte) clean;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SegmentCode{");
        sb.append("value=")
                .append(value)
                .append(", code=")
                .append(code)
                .append('}');
        return sb.toString();
    }
}
