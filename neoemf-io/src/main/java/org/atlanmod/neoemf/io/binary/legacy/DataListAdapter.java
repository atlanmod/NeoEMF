package org.atlanmod.neoemf.io.binary.legacy;

import org.atlanmod.commons.Throwables;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.BinaryWriter;
import org.atlanmod.neoemf.io.binary.Converter;
import org.atlanmod.neoemf.io.binary.adapter.AttributeAdapter;
import org.atlanmod.neoemf.io.binary.adapter.ObjectAdapter;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;
import org.eclipse.emf.ecore.InternalEObject;

import java.io.IOException;
import java.util.Collection;

/**
 * For Compatibility with Eclipse EMF BinaryResource save/load
 */
public class DataListAdapter extends AttributeAdapter {

    private final Converter<String> stringConverter;

    public DataListAdapter(FeatureIdentifier identifier) {
        super(identifier);
        stringConverter = identifier.provider().getStringConverter();
    }

    @Override
    public void writeAttributeValueOn(BufferWrangler buffer, ObjectAdapter objectAdapter) {

    }

    @Override
    public void writeAttributeValueOn(BufferWrangler buffer) {

    }

    @Override
    public void readAttributeValueFrom(BufferWrangler buffer, ObjectAdapter objectAdapter) {
        throw Throwables.notImplementedYet("DataListAdapter::readAttributeValueFrom()");
    }

    @Override
    public Collection<Object> readAttributeValuesFrom(BufferWrangler buffer) {
        throw Throwables.notImplementedYet("DataListAdapter::readValuesFrom()");
    }


    //@Override
    public void saveValueOn(BinaryWriter stream, InternalEObject eObject) throws IOException {
        //this.writeIdOn(stream);
        //ByteBuffer buffer = stream.getByteBuffer();
        /*
        Object value = eObject.eGet(this.index(), false, true);

        List<?> dataValues = (List<?>) value;
        int length = dataValues.size();
        buffer.put(CompressedInts.toBytes(length + 1));
        for (int j = 0; j < length; ++j) {
            String literal = dataValues.get(j).toString();
            stringConverter.writeOn(buffer, literal);
        }

         */
    }

    public String convertToString(Object value) {
        return this.eFactory.convertToString(this.eDataType, value);
    }

}
