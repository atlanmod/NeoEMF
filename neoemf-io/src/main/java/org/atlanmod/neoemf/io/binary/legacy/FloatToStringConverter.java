package org.atlanmod.neoemf.io.binary.legacy;

import org.atlanmod.commons.primitive.Floats;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.Converter;
import org.atlanmod.neoemf.io.binary.converter.StringConverter;

import static org.atlanmod.commons.Guards.checkArgument;

public class FloatToStringConverter implements Converter<Float> {
    private org.atlanmod.neoemf.io.binary.converter.StringConverter stringConverter = new StringConverter();

    @Override
    public byte[] toBytes(Object object) {
        checkArgument(Float.class.isInstance(object));
        return Floats.toBytes((float) object);
    }

    @Override
    public void writeOn(BufferWrangler buffer, Float value) {
        assert Float.class.isInstance(value);
        assert buffer.remaining() >= Float.BYTES;

        stringConverter.writeOn(buffer, Float.toString(value));
    }

    @Override
    public Float readFrom(BufferWrangler buffer) {
        return null;
    }
}
