package org.atlanmod.neoemf.io.binary.adapter;

import fr.inria.atlanmod.neoemf.core.Id;
import org.atlanmod.commons.Throwables;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;
import org.atlanmod.neoemf.io.binary.identifier.Properties;
import org.eclipse.emf.ecore.EReference;

import java.util.Collection;
import java.util.List;

public abstract class ReferenceAdapter extends FeatureAdapter {
    private EReference eReference;
    protected List<Integer> currentValues;

    public ReferenceAdapter(FeatureIdentifier identifier) {
        super(identifier);
        this.eReference = identifier.asReference();
        this.properties = new Properties(eReference);
    }

    public EReference adaptee() {
        return eReference;
    }

    public void writeValuesOn(BufferWrangler buffer) {
        throw Throwables.notImplementedYet("ReferenceAdapter::writeValueOn()");
    }

    public Collection<UnsignedVarInt> readValuesFrom(BufferWrangler buffer) {
        throw Throwables.notImplementedYet("ReferenceAdapter::writeValueOn()");
    }

    public void readValueFrom(BufferWrangler buffer, ObjectAdapter destination) {
        throw Throwables.notImplementedYet("ReferenceAdapter::readValueFrom()");
    }

    public ReferenceAdapter withValues(List<Integer> value) {
        this.currentValues = value;
        return this;
    }
}
