package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.neoemf.io.binary.Style;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;

public class SingleContainerReferenceWithProxyResolvingAdapter extends SingleReferenceAdapter {
    private Style style;

    public SingleContainerReferenceWithProxyResolvingAdapter(FeatureIdentifier identifier) {
        super(identifier);
        this.style = identifier.style();
    }

/*    //@Override
    public void saveValueOn(EObjectOutputStream stream, InternalEObject eObject) throws IOException {
      //  this.writeIdOn(stream);

        InternalEObject value = (InternalEObject) eObject.eGet(this.index(), false, true);
        this.save(stream, value);
        stream.flush();
    }

    private void save(EObjectOutputStream stream, InternalEObject value) throws IOException {
        assert Objects.nonNull(value);

        //EObjectAdapter adapter = stream.adapt(value);
        //if (adapter.getAndMarkAlreadyWritten()) {
          //  stream.writeId(adapter.id());
          //  return;
        //}

       // EClassAdapter eClassAdapter = stream.adapt(value.eClass());
  //      eClassAdapter.writeOn(stream);

        Resource resource = value.eResource();
      //  if (resource != stream.resource() && resource != null) {
 //           stream.writeId(-1);
   //         stream.writeURI(resource.getURI(), resource.getURIFragment(value));
     //   } else if (value.eIsProxy()) {
     //       stream.writeId(-1);
       //     stream.writeURI(value.eProxyURI());
     //   }

  //      if (!style.useProxyAttributes()) {
   //         eClassAdapter.saveAllFeatures(stream, value);
      //      stream.writeId(0); // End of EObject
        }
//    }

  //  @Override
    public void writeOn(ByteBuffer buffer, Object value) {
        throw Throwables.notImplementedYet("SingleContainerReferenceWithProxyResolvingAdapter::writeOn");
    }*/
}
