package org.atlanmod.neoemf.io.binary.frame;

import fr.inria.atlanmod.neoemf.io.proxy.ProxyClass;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.adapter.ClassAdapter;
import org.atlanmod.neoemf.io.binary.adapter.PackageAdapter;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.atlanmod.neoemf.io.binary.adapter.ObjectAdapter;
import org.atlanmod.neoemf.io.binary.serializer.MetadataSerializer;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriterMetadata extends Metadata {
    private Map<EPackage, PackageAdapter> packages = new HashMap<EPackage, PackageAdapter>();
    private Map<EObject, ObjectAdapter> objects = new HashMap<>();
    private int objectCount;


    public WriterMetadata(BinaryAdapterProvider provider) {
        super(provider);
    }
    public Map<EPackage, PackageAdapter> packages() {
        return packages;
    }

    public int getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(int objectCount) {
        this.objectCount = objectCount;
    }

    public List<ClassAdapter> classes() {
        List<ClassAdapter> adapters = new ArrayList<>();
        for (PackageAdapter each: packages.values()) {
            adapters.addAll(each.classes().values());
        }

        return adapters;
    }

    public Map<EObject, ObjectAdapter> objects() {
        return objects;
    }

    // region Classes
    public ClassAdapter adapt(EClass eClass) {
        EPackage ePackage = eClass.getEPackage();
        PackageAdapter packageAdapter = this.adapt(ePackage);
        return packageAdapter.adapt(eClass);
    }

    // endregion

    // region Packages


    public PackageAdapter adapt(EPackage ePackage) {
        return packages.computeIfAbsent(ePackage, this::createAdapter);
    }

    // region Objects
    public ObjectAdapter adapt(EObject eObject) {
        return this.objects.computeIfAbsent(eObject, this::createAdapter);
    }

    private ObjectAdapter createAdapter(EObject eObject) {
        ClassAdapter classAdapter = this.adapt(eObject.eClass());
        return classAdapter.adapt(eObject);
    }
    // endregion


    public ByteBuffer serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(estimateSize());
        this.writeOn(buffer);
        return buffer.flip();
    }

    private void writeOn(ByteBuffer buffer) {
        BufferWrangler writer = provider.writerFor(buffer);
        MetadataSerializer serializer = new MetadataSerializer(writer, this);
        serializer.write();
    }

    public ClassAdapter adapt(ProxyClass proxyClass) {
        return this.adapt(proxyClass.getOrigin());
    }

    private int estimateSize() {
        return this.classes().size() * 400;
    }
}
