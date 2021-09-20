package org.atlanmod.neoemf.io.binary.legacy;

import org.atlanmod.commons.primitive.Doubles;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.Converter;
import org.atlanmod.neoemf.io.binary.converter.StringConverter;

import static org.atlanmod.commons.Guards.checkArgument;

public class DoubleToStringConverter implements Converter<Double> {
    private org.atlanmod.neoemf.io.binary.converter.StringConverter stringConverter = new StringConverter();

    @Override
    public byte[] toBytes(Object object) {
        checkArgument(Double.class.isInstance(object));
        return Doubles.toBytes((double) object);
    }

    @Override
    public void writeOn(BufferWrangler buffer, Double value) {
        assert Double.class.isInstance(value);

        stringConverter.writeOn(buffer, Double.toString((double) value));
    }

    @Override
    public Double readFrom(BufferWrangler buffer) {
        return null;
    }
}
