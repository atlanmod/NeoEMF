package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;

public class MultiReferenceWithProxyResolvingAdapter extends MultivaluedReferenceAdapter {
    public MultiReferenceWithProxyResolvingAdapter(FeatureIdentifier identifier) {
        super(identifier);
    }

/*    //@Override
    public void saveValueOn(EObjectOutputStream stream, InternalEObject eObject) throws IOException {
        //this.writeIdOn(stream);
        EList<EObject> values = (EList<EObject>) eObject.eGet(this.index(), false, true);

        List<InternalEObject> contents = EObjectOutputStream.asInternaEObjects(values);
        this.saveReferenceListWithProxyResolving(stream, contents);
        stream.flush();
    }

    private void saveReferenceListWithProxyResolving(EObjectOutputStream stream, List<InternalEObject> contents) throws IOException {
        //stream.writeId(contents.size());
        for (InternalEObject each : contents) {
          //  EObjectAdapter adapter = stream.adapt(each);
            //adapter.writeContainerWithProxyResolving(stream);
        }
    }

   // @Override
    public void writeOn(ByteBuffer buffer, Object value) {
        throw Throwables.notImplementedYet("SingleReferenceAdapter::writeOn");
    }*/
}
