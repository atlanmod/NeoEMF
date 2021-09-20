package org.atlanmod.neoemf.io.binary.legacy;

import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.Converter;
import org.atlanmod.neoemf.io.binary.converter.StringConverter;

@Deprecated
public class LegacyDataConverter implements Converter {
    private org.atlanmod.neoemf.io.binary.converter.StringConverter stringConverter = new StringConverter();

    @Override
    public byte[] toBytes(Object object) {
        return stringConverter.toBytes(object.toString());
    }

    @Override
    public void writeOn(BufferWrangler buffer, Object value) {
        stringConverter.writeOn(buffer, value.toString());
    }

    @Override
    public Object readFrom(BufferWrangler buffer) {
        return null;
    }
}
