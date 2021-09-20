package org.atlanmod.neoemf.io.binary.converter;

import org.atlanmod.commons.Guards;
import org.atlanmod.commons.Preconditions;
import org.atlanmod.commons.primitive.Ints;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.Converter;
import org.atlanmod.neoemf.io.binary.legacy.StringConverter;
import org.eclipse.emf.common.util.Enumerator;

import java.nio.ByteBuffer;

class ENumToStringConverter implements Converter {

    private StringConverter stringConverter = new StringConverter();

    @Override
    public byte[] toBytes(Object object) {
        Guards.checkInstanceOf(object, Enumerator.class);

        Enumerator literal = (Enumerator) object;
        return Ints.toBytes(literal.getValue());
    }

    @Override
    public void writeOn(BufferWrangler buffer, Object value) {
        Enumerator literal = (Enumerator) value;

        this.stringConverter.writeOn(buffer, literal.getLiteral());
    }

    @Override
    public Object readFrom(BufferWrangler buffer) {
        return null;
    }
}
