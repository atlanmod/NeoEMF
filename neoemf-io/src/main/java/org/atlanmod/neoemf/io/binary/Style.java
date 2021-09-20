package org.atlanmod.neoemf.io.binary;

import org.atlanmod.commons.primitive.Ints;
import org.eclipse.emf.common.util.Enumerator;

import java.nio.ByteBuffer;
import java.util.Date;

public class Style {
    private static final Style EMPTY_STYLE = new Style(0);

    private static final int STYLE_BINARY_FLOATING_POINT = 1 << 0;
    private static final int STYLE_BINARY_DATE = 1 << 1;
    private static final int STYLE_PROXY_ATTRIBUTES = 1 << 2;
    private static final int STYLE_BINARY_ENUMERATOR = 1 << 3;
    private static final int STYLE_DATA_CONVERTER = 1 << 4;
    private int values;

    public Style(int styleValue) {
        this.values = styleValue;
    }

    /**
     * A Boolean save option to specify whether {@link Enumerator enumerator} values will be serialized using an int
     * value instead of using a String representation.
     *
     * @return
     */
    public boolean useBinaryEnumerator() {
        return ((values & STYLE_BINARY_ENUMERATOR) != 0);
    }

    /**
     * A Boolean save option to specify whether float and double values are encoded using
     * {@link Float#floatToIntBits(float)} and
     * {@link Double#doubleToLongBits(double)} respectively, instead of a String representation.
     *
     * @return
     */
    public boolean useBinaryFloatingPoint() {
        return ((values & STYLE_BINARY_FLOATING_POINT) != 0);
    }

    /**
     * A Boolean save option to specify whether {@link Date date} values will be serialized using a {@code long}
     * value instead of a {@code String} representation.
     *
     * @return
     */
    public boolean useBinaryDate() {
        return ((values & STYLE_BINARY_DATE) != 0);
    }

    /**
     * @return
     */
    public boolean useDataConverters() {
        return ((values & STYLE_DATA_CONVERTER) != 0);
    }

    /**
     * A Boolean save option to specify whether serialized proxies will include the serialization of their attribute values.
     *
     * @return
     */
    public boolean useProxyAttributes() {
        return ((values & STYLE_PROXY_ATTRIBUTES) == 0);
    }

    public void writeOn(ByteBuffer buffer) {
        assert buffer.remaining() >= Integer.BYTES;

        buffer.put(Ints.toBytes(values));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Style{")
                .append("date: ")
                .append(useBinaryDate())
                .append(" converters=")
                .append(useDataConverters())
                .append(" floats=")
                .append(useBinaryFloatingPoint())
                .append(" proxy=")
                .append(useProxyAttributes())
                .append(" enumerators=")
                .append(useBinaryEnumerator());


        return builder.toString();
    }

    public static Style empty() {
        return EMPTY_STYLE;
    }
}
