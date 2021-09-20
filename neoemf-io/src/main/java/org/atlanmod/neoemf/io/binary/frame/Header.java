package org.atlanmod.neoemf.io.binary.frame;

import org.atlanmod.commons.io.UnsignedInt;
import org.atlanmod.commons.io.UnsignedShort;
import org.atlanmod.commons.primitive.Strings;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.SerializableToBytes;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.atlanmod.commons.io.Numbers.uint;

public class Header implements SerializableToBytes {

    public static final byte[] NEOEMF = Strings.toBytes("neoemf");
    public static final UnsignedShort CURRENT_VERSION = UnsignedShort.fromInt(1);
    /**
     * The size (in bytes) of this header.
     */
    public static final int SIZE = NEOEMF.length // signature
            + UnsignedShort.BYTES      // version
            + UnsignedInt.BYTES        // Metadata Length
            + UnsignedInt.BYTES        // Attributes Length
            + UnsignedInt.BYTES        // References Length
            + UnsignedInt.BYTES        // Index Length
            + Options.BYTES;           // Options

    private byte[] signature;
    private UnsignedShort version;
    private UnsignedInt metadataLength = UnsignedInt.fromInt(0);
    private UnsignedInt attributesLength = UnsignedInt.fromInt(0);
    private UnsignedInt referencesLength = UnsignedInt.fromInt(0);
    private UnsignedInt indexLength = UnsignedInt.fromInt(0);
    private Options options = new Options();
    private final BinaryAdapterProvider provider = new BinaryAdapterProvider();

    public Header withSignature(byte[] signature) {
        this.signature = signature;
        return this;
    }


    public Header withOptions(Options options) {
        this.options = options;
        return this;
    }

    public Header withVersion(UnsignedShort version) {
        this.version = version;
        return this;
    }

    public Header withMetadataLength(UnsignedInt length) {
        this.metadataLength = length;
        return this;
    }

    public Header withAttributesLength(UnsignedInt length) {
        this.attributesLength = length;
        return this;
    }

    public Header withReferencesLength(UnsignedInt length) {
        this.referencesLength = length;
        return this;
    }

    public int getReferencesLength() {
        return this.referencesLength.intValue();
    }

    public int getAttributesLength() {
        return this.attributesLength.intValue();
    }

    public Header withIndexLength(UnsignedInt length) {
        this.indexLength = length;
        return this;
    }

    /**
     * Writes this header to a {@code ByteBuffer}.
     *
     * @param buffer the {@code ByteBuffer} to which this header will be written.
     */
    public void writeOn(BufferWrangler buffer) {
        assert buffer.remaining() >= SIZE : "Not enough space in ByteBuffer";

        buffer.put(signature)
                .put(version)
                .put(options.toBytes())
                .put(metadataLength)
                .put(attributesLength)
                .put(referencesLength)
                .put(indexLength);
    }

    public void readFrom(BufferWrangler buffer) {
        this.signature = new byte[Header.NEOEMF.length];
        buffer.get(signature);
        this.version = buffer.getUnsignedShort();
        byte[] readOptions = new byte[Options.BYTES];
        buffer.get(readOptions);
        this.options = Options.fromBytes(readOptions);
        this.metadataLength = buffer.getUnsignedInt();
        this.attributesLength = buffer.getUnsignedInt();
        this.referencesLength = buffer.getUnsignedInt();
        this.indexLength = buffer.getUnsignedInt();
    }

    public int getMetadataLength() {
        return metadataLength.intValue();
    }

    public int getIndexLength() {
        return indexLength.intValue();
    }

    public void setMetadataLength(long length) {
        this.metadataLength = uint(length);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Header header = (Header) other;
        return Arrays.equals(signature, header.signature)
                && version.equals(header.version)
                && metadataLength.equals(header.metadataLength)
                && attributesLength.equals(header.attributesLength)
                && referencesLength.equals(header.referencesLength)
                && indexLength.equals(header.indexLength);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(signature);
    }

    public BufferWrangler serialize() {
        BufferWrangler buffer = provider.allocateBuffer(SIZE);
        this.writeOn(buffer);
        return buffer.flip();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Header{");
        sb.append("signature=").append(Arrays.toString(signature));
        sb.append(", version=").append(version);
        sb.append(", metadataLength=").append(metadataLength);
        sb.append(", attributesLength=").append(attributesLength);
        sb.append(", referencesLength=").append(referencesLength);
        sb.append(", indexLength=").append(indexLength);
        sb.append(", options=").append(options);
        sb.append('}');
        return sb.toString();
    }

    public static Header createDefaultHeader() {
        return new Header()
                .withSignature(Arrays.copyOf(NEOEMF, NEOEMF.length))
                .withVersion(CURRENT_VERSION)
                .withOptions(Options.empty());
    }

}
