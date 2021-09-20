package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;
import org.eclipse.emf.ecore.EObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MultivaluedAttributeAdapter extends AttributeAdapter {

    public MultivaluedAttributeAdapter(FeatureIdentifier identifier) {
        super(identifier);
    }

    /**
     *
     * @param buffer
     * @param objectAdapter The adapter of the {@code EObject} sub-instance containing the value.
     */
    @Override
    public void writeAttributeValueOn(BufferWrangler buffer, ObjectAdapter objectAdapter) {
        EObject eObject = objectAdapter.adaptee();
        List<?> values = (List<?>) eObject.eGet(this.adaptee());

        buffer.put(UnsignedVarInt.fromInt(values.size()));
        for(Object value: values) {
            buffer.put(value, converter());
        }
    }

    @Override
    public void writeAttributeValueOn(BufferWrangler buffer) {
        buffer.put(UnsignedVarInt.fromInt(currentValues.size()));
        for(Object value: currentValues) {
            buffer.put(value, converter());
        }
    }

    @Override
    public Collection<Object> readAttributeValuesFrom(BufferWrangler buffer) {
        int size = buffer.getUnsignedVarInt().intValue();
        List<Object> values = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            values.add(this.converter().readFrom(buffer));
        }
        return values;
    }

    @Override
    public void readAttributeValueFrom(BufferWrangler buffer, ObjectAdapter objectAdapter) {
        EObject eObject = objectAdapter.adaptee();
        List<Object> values = (List<Object>) eObject.eGet(this.adaptee());

        UnsignedVarInt length = buffer.getUnsignedVarInt();
        for (int i = 0; i < length.intValue(); i++) {
            Object value = converter().readFrom(buffer);
            values.add(value);
        }
    }

    @Override
    public String toString() {
        return "MultivaluedAttribute: " + super.toString();
    }
}
