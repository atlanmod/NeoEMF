package org.atlanmod.neoemf.io.binary;

public class CompressedInts {

    public static byte[] toBytes(int value) {
        if (value <= 0x3F) {
            return new byte[]{(byte) value};
        } else if (value <= 0x3FFF) {
            return new byte[]{(byte) (value >> 8 | 0x40), (byte) (value & 0xFF)};
        } else if (value <= 0x3FFFFF) {
            return new byte[]{(byte) (value >> 16 | 0x80), (byte) (value >> 8 & 0xFF), (byte) (value & 0xFF)};
        } else if (value <= 0x3FFFFFFF) {
            return new byte[]{(byte) (value >> 24 | 0xC0),
                    (byte) (value >> 16 & 0xFF),
                    (byte) (value >> 8 & 0xFF),
                    (byte) (value & 0xFF)};
        }
        else return new byte[0];
    }
}
