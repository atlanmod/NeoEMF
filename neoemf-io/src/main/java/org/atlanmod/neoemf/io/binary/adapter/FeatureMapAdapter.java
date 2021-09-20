package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.commons.Throwables;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;

import java.util.Collection;

public class FeatureMapAdapter extends AttributeAdapter {

    public FeatureMapAdapter(FeatureIdentifier identifier) {
        super(identifier);
    }

    @Override
    public void writeAttributeValueOn(BufferWrangler buffer, ObjectAdapter objectAdapter) {

    }

    @Override
    public void writeAttributeValueOn(BufferWrangler buffer) {

    }

    @Override
    public void readAttributeValueFrom(BufferWrangler buffer, ObjectAdapter objectAdapter) {
        throw  Throwables.notImplementedYet("FeatureMapAdapter::readAttributeValueFrom()");
    }

    @Override
    public Collection<Object> readAttributeValuesFrom(BufferWrangler buffer) {
        throw  Throwables.notImplementedYet("FeatureMapAdapter::readValuesFrom()");
    }

    @Override
    public String toString() {
        return "FeatureMapAttribute: " + super.toString();
    }

    /*
    @Override
    public void saveValueOn(EObjectOutputStream stream, InternalEObject eObject) throws IOException {
        //stream.writeInt(0);
        this.writeIdOn(stream);
        Object value = eObject.eGet(this.id(), false, true);
        FeatureMap.Internal featureMap = (FeatureMap.Internal) value;
        stream.writeId(featureMap.size());
        //stream.writeInt(0);
        this.eClassAdapter.writeOn(stream);
        //stream.writeInt(0);

        for (FeatureMap.Entry each : featureMap) {
            this.save(stream, (FeatureMap.Entry.Internal) each);
            //stream.saveFeatureMapEntry((FeatureMap.Entry.Internal) each);
        }

    }

    private void save(EObjectOutputStream stream, FeatureMap.Entry.Internal entry) throws IOException {
        EFeatureAdapter featureAdapter = stream.adapt(entry.getEStructuralFeature());
        featureAdapter.decId(); // because there is a bug...
        //stream.writeInt(0);
        featureAdapter.writeIdOn(stream);  // writes '7' instead of 6
        //stream.writeInt(0);
        stream.saveFeatureMapEntry(entry);
    }

     */

    /*
    public String convertToString(Object value) {
        return this.eFactory.convertToString(this.eDataType, value);
    }

    @Override
    public void writeOn(ByteBuffer buffer, Object value) {
        throw Throwables.notImplementedYet("SingleReferenceAdapter::writeOn");
    }
    */
}