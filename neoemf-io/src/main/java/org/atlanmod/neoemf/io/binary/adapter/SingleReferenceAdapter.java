package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.commons.Preconditions;
import org.atlanmod.commons.io.Numbers;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;
import org.eclipse.emf.ecore.EObject;

import java.util.Collection;
import java.util.List;

import static org.atlanmod.commons.Preconditions.requireThat;
import static org.atlanmod.commons.io.Numbers.uvarint;

public class SingleReferenceAdapter extends ReferenceAdapter {
    public SingleReferenceAdapter(FeatureIdentifier identifier) {
        super(identifier);
    }

    @Override
    public void writeValuesOn(BufferWrangler buffer) {
        requireThat(currentValues).isNotNull();

        Integer target = currentValues.get(0);
        buffer.put(uvarint(target));
    }

    public Collection<UnsignedVarInt> readValuesFrom(BufferWrangler buffer) {
        UnsignedVarInt target = buffer.getUnsignedVarInt();
        return List.of(target);
    }

    @Override
    public void readValueFrom(BufferWrangler buffer, ObjectAdapter objectAdapter) {
        Preconditions.requireThat(buffer).isNotNull();

        EObject eObject = objectAdapter.adaptee();
       /* Object value = converter().readFrom(buffer);

        eObject.eSet(this.adaptee(), value);

        */
    }
}
