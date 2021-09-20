package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.commons.log.Log;
import org.eclipse.emf.ecore.EObject;

import java.nio.ByteBuffer;

public class ObjectAdapter {
    private final int id;
    private final EObject adaptee;
    private final ClassAdapter classAdapter;

    public ObjectAdapter(ClassAdapter classAdapter, EObject eObject, int id) {
        this.id = id;
        this.adaptee = eObject;
        this.classAdapter = classAdapter;
        Log.info("Creating EObjectAdapter for eObject id: " + id);
    }

    public int index() {
        return id;
    }

    public void writeOn(ByteBuffer buffer) {

    }

    public ClassAdapter classAdapter() {
        return classAdapter;
    }

    public EObject adaptee() {
        return adaptee;
    }

    /*
    private void writeContentsOn(EObjectOutputStream stream) throws IOException {
        //stream.writeByte((byte) 0);
        stream.writeId(id);
        EClass eClass = adapteee().eClass();
        EClassAdapter eClassAdapter = stream.adapt(eClass);
        eClassAdapter.writeOn(stream);
        eClassAdapter.saveAllFeatures(stream, adapteee());
        stream.writeId(0); // End of EObject
        //stream.writeByte((byte) 0);
        stream.flush();
    }

    void writeReferenceOn(EObjectOutputStream stream) throws IOException {
        stream.writeId(id());
    }


    void writeIdOn(EObjectOutputStream stream) throws IOException {
        stream.writeId(id());
    }

    void writeProxyContainmentReferenceOn(EObjectOutputStream stream) throws IOException {
        if (alreadyWritten) {
            this.writeIdOn(stream);
            return;
        }
        alreadyWritten = true;
        EClassAdapter eClassAdapter = stream.adapt(this.adaptee.eClass());
        eClassAdapter.writeOn(stream);

        Resource.Internal resource = this.adaptee.eDirectResource();
        if (resource != null) {
            stream.writeId(-1);
            stream.writeURI(resource.getURI(), resource.getURIFragment(this.adaptee));
        } else if (this.adaptee.eIsProxy()) {
            stream.writeId(-1);
            stream.writeURI(this.adaptee.eProxyURI());
        }
        if (!style.useProxyAttributes()) {
            eClassAdapter.saveAllFeatures(stream, this.adaptee);
            stream.writeId(0); // End of EObject
            stream.flush();
        }
    }

    void writeContainerWithProxyResolving(EObjectOutputStream stream) throws IOException {
        if (alreadyWritten) {
            this.writeIdOn(stream);
            return;
        }
        alreadyWritten = true;
        EClassAdapter eClassAdapter = stream.adapt(this.adaptee.eClass());
        eClassAdapter.writeOn(stream);

        Resource resource = adaptee.eResource();
        if (resource != stream.resource() && resource != null) {
            stream.writeId(-1);
            stream.writeURI(resource.getURI(), resource.getURIFragment(adaptee));
            if (style.useProxyAttributes()) {
                return;
            }
        } else if (adaptee.eIsProxy()) {
            stream.writeId(-1);
            stream.writeURI(adaptee.eProxyURI());
            if (style.useProxyAttributes()) {
                return;
            }
        }
        eClassAdapter.saveAllFeatures(stream, adaptee);
        stream.writeId(0); // End of EObject
        stream.flush();
    }

     */
}
