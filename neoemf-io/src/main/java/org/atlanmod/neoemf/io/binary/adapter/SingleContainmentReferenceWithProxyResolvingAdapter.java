package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.neoemf.io.binary.Style;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;

public class SingleContainmentReferenceWithProxyResolvingAdapter extends SingleReferenceAdapter {
    private Style style;

    public SingleContainmentReferenceWithProxyResolvingAdapter(FeatureIdentifier identifier) {
        super(identifier);
        this.style = identifier.style();
    }

/*   // @Override
    public void saveValueOn(EObjectOutputStream stream, InternalEObject eObject) throws IOException {
        //this.writeIdOn(stream);

        InternalEObject value = (InternalEObject) eObject.eGet(this.index(), false, true);
        this.save(stream, value);
        //stream.flush();
    }

    private void save(EObjectOutputStream stream, InternalEObject value) throws IOException {
        assert Objects.nonNull(value);

        //EObjectAdapter adapter = stream.adapt(value);
  //      adapter.writeProxyContainmentReferenceOn(stream);
    }
  //  @Override
    public void writeOn(ByteBuffer buffer, Object value) {
        throw Throwables.notImplementedYet("SingleReferenceAdapter::writeOn");
    }*/
}
