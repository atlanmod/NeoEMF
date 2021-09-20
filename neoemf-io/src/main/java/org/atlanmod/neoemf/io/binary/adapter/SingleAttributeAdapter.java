package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;
import org.eclipse.emf.ecore.EObject;

import java.util.Collection;
import java.util.List;

import static org.atlanmod.commons.Preconditions.requireThat;

public class SingleAttributeAdapter extends AttributeAdapter {

    public SingleAttributeAdapter(FeatureIdentifier identifier) {
        super(identifier);
    }

    /**
     *
     * @param buffer
     * @param objectAdapter The adapter of the {@code EObject} sub-instance containing the value.
     */
    @Override
    public void writeAttributeValueOn(BufferWrangler buffer, ObjectAdapter objectAdapter) {
        requireThat(converter()).isNotNull();

        EObject eObject = objectAdapter.adaptee();
        Object value = eObject.eGet(this.adaptee());
        buffer.put(value, converter());
    }

    @Override
    public void writeAttributeValueOn(BufferWrangler buffer) {
        requireThat(currentValues).isNotNull();
        Object value = currentValues.get(0);
        buffer.put(value, converter());
    }


    @Override
    public void readAttributeValueFrom(BufferWrangler buffer, ObjectAdapter objectAdapter) {
        requireThat(buffer).isNotNull();

        EObject eObject = objectAdapter.adaptee();
        Object value = converter().readFrom(buffer);

        eObject.eSet(this.adaptee(), value);
    }

    @Override
    public Collection<Object> readAttributeValuesFrom(BufferWrangler buffer) {
        return List.of(this.converter().readFrom(buffer));
    }

    @Override
    public String toString() {
        return "SingleAttribute: " + super.toString();
    }
}
