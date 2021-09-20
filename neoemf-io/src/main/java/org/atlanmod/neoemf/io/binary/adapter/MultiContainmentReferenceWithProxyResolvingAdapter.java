package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;

public class MultiContainmentReferenceWithProxyResolvingAdapter extends MultivaluedReferenceAdapter {
    public MultiContainmentReferenceWithProxyResolvingAdapter(FeatureIdentifier identifier) {
        super(identifier);
    }

 /*   //@Override
    public void saveValueOn(EObjectOutputStream stream, InternalEObject eObject) throws IOException {
        Object value = eObject.eGet(this.index(), false, true);
        List<InternalEObject> contents = stream.asInternaEObjects((EList<EObject>) value);
        saveValuesOn(stream, contents);
    }

    private void saveValuesOn(EObjectOutputStream stream, List<InternalEObject> contents) throws IOException {
       // this.writeIdOn(stream);
        //stream.writeId(contents.size());
        for (InternalEObject each : contents) {
            //EObjectAdapter adapter = stream.adapt(each);
           // adapter.writeProxyContainmentReferenceOn(stream);
        }
    }

  //  @Override
    public void writeOn(ByteBuffer buffer, Object value) {
        throw Throwables.notImplementedYet("SingleReferenceAdapter::writeOn");
    }*/
}
