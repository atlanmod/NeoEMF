package org.atlanmod.neoemf.io.binary.frame;

import org.atlanmod.commons.collect.Flags;
import org.atlanmod.neoemf.io.binary.BufferWrangler;

import java.nio.ByteBuffer;
import java.util.Objects;

public class Options {
    private final Flags options;
    public static int BYTES = 2;
    public static int USE_UTF_16 = 0;
    private static final Options EMPTY_OPTIONS = new Options();

    public Options() {
        this.options = new Flags(BYTES * Byte.SIZE);
    }

    private Options(byte[] bytes) {
        this.options =  Flags.fromBytes(bytes);
    }

    // region Encoding
    public Options useUTF16() {
        options.set(USE_UTF_16);
        return this;
    }

    public Options useUTF8() {
        options.set(USE_UTF_16, false);
        return this;
    }

    public boolean shouldUseUTF16() {
        return options.get(USE_UTF_16);
    }

    public boolean shouldUseUTF8() {
        return !options.get(USE_UTF_16);
    }
    // endregion

    // region Serialization
    public void writeOn(ByteBuffer buffer) {
        buffer.put(options.toBytes());
    }

    public void writeOn(BufferWrangler buffer) {
        buffer.put(options.toBytes());
    }

    public static Options fromBytes(byte[] bytes) {
        return new Options(bytes);
    }
    // endregion


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Options other = (Options) o;
        return options.equals(other.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(options);
    }

    public byte[] toBytes() {
        return this.options.toBytes();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Options{");
        sb.append("Use UTF-16=")
                .append(options.get(USE_UTF_16));
        sb.append('}');
        return sb.toString();
    }

    public static Options empty() {
        return EMPTY_OPTIONS;
    }
}
