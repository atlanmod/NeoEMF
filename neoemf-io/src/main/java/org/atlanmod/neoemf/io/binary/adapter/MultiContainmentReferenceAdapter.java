package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;

public class MultiContainmentReferenceAdapter extends MultivaluedReferenceAdapter {

    public MultiContainmentReferenceAdapter(FeatureIdentifier identifier) {
        super(identifier);
    }

/*
    //@Override
    public void saveValueOn(EObjectOutputStream stream, InternalEObject eObject) throws IOException {
        //this.writeIdOn(stream);

        Object value = eObject.eGet(this.index(), false, true);
        List<InternalEObject> contents = EObjectOutputStream.asInternaEObjects((EList<EObject>) value);
        //stream.writeId(contents.size());
        for (InternalEObject each : contents) {
          //  EObjectAdapter adapter = stream.adapt(each);
          //  adapter.writeOn(stream);
        }
        //stream.flush();
    }

    //@Override
    public void writeOn(ByteBuffer buffer, Object value) {
        throw Throwables.notImplementedYet("SingleReferenceAdapter::writeOn");
    }*/
}
