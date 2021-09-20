package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.commons.Preconditions;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.commons.log.Log;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;

import java.util.ArrayList;
import java.util.Collection;

import static org.atlanmod.commons.io.Numbers.uvarint;

public class MultivaluedReferenceAdapter extends ReferenceAdapter {

    public MultivaluedReferenceAdapter(FeatureIdentifier identifier) {
        super(identifier);
    }

    @Override
    public void writeValuesOn(BufferWrangler buffer) {
        Preconditions.requireThat(currentValues).isNotNull();

        //int before = buffer.position();
        buffer.put(uvarint(currentValues.size()));
        for (Integer value : currentValues) {
            buffer.put(uvarint(value));
        }
        //Log.info("Wrote {0} multivalued references of size: {1}", currentValues.size(), buffer.position() - before);
    }

    public Collection<UnsignedVarInt> readValuesFrom(BufferWrangler buffer) {
        int size = buffer.getUnsignedVarInt().intValue();
        Collection<UnsignedVarInt> targets = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            targets.add(buffer.getUnsignedVarInt());
        }
        return targets;
    }
}
