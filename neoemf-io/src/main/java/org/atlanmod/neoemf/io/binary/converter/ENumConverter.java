package org.atlanmod.neoemf.io.binary.converter;

import org.atlanmod.commons.primitive.Ints;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.Converter;
import org.eclipse.emf.common.util.Enumerator;

import static org.atlanmod.commons.Guards.checkInstanceOf;

class ENumConverter implements Converter {
    @Override
    public byte[] toBytes(Object object) {
        checkInstanceOf(object, Enumerator.class);

        Enumerator literal = (Enumerator) object;
        return Ints.toBytes(literal.getValue());
    }

    @Override
    public void writeOn(BufferWrangler buffer, Object value) {
        byte[] bytes = this.toBytes(value);

        assert buffer.remaining() >= bytes.length;

        buffer.put(bytes);
    }

    @Override
    public Object readFrom(BufferWrangler buffer) {
        return null;
    }
}
