package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.neoemf.io.binary.*;
import org.atlanmod.neoemf.io.binary.converter.Converters;
import org.atlanmod.neoemf.io.binary.frame.Options;
import org.atlanmod.neoemf.io.binary.frame.ReaderMetadata;
import org.atlanmod.neoemf.io.binary.frame.WriterMetadata;
import org.atlanmod.neoemf.io.binary.identifier.ClassIdentifier;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;
import org.atlanmod.neoemf.io.binary.identifier.PackageIdentifier;
import org.atlanmod.neoemf.io.binary.serializer.AttributeValuesSerializer;
import org.atlanmod.neoemf.io.binary.serializer.MetadataDeserializer;
import org.eclipse.emf.ecore.*;

import java.nio.ByteBuffer;

public class BinaryAdapterProvider {

    private final Converters converters;
    private final Options options;
    private int nextClassId = 0;
    private int nextPackageId = 0;
    private int nextObjectId = 0;
    private AttributeValuesSerializer attributeValuesSerializer = new AttributeValuesSerializer(this);

    public BinaryAdapterProvider(Options options) {
        this.options = options;
        this.converters = new Converters(options);
    }

    public BinaryAdapterProvider() {
        this(Options.empty());
    }

    public PackageAdapter createEPackageAdapter(EPackage ePackage) {
        PackageIdentifier identifier = new PackageIdentifier(nextPackageId++, this, ePackage);
        return new PackageAdapter(identifier);
    }

    public ObjectAdapter createObjectAdapter(ClassAdapter classAdapter, EObject eObject) {
        return new ObjectAdapter(classAdapter, eObject, nextObjectId++);
    }

    public ClassAdapter createEClassAdapter(EClass eClass) {
        ClassIdentifier identifier = new ClassIdentifier(nextClassId++, this, eClass);
        return new ClassAdapter(identifier);
    }

    public ReferenceAdapter createEReferenceAdapter(FeatureIdentifier identifier) {
        EReference reference = identifier.asReference();
        FeatureKind kind = FeatureKind.get(reference);

        switch (kind) {

            case EOBJECT_PROXY_RESOLVING:
            case EOBJECT_CONTAINER_PROXY_RESOLVING:
                return new SingleContainerReferenceWithProxyResolvingAdapter(identifier);

            case EOBJECT_CONTAINMENT_PROXY_RESOLVING:
                return new SingleContainmentReferenceWithProxyResolvingAdapter(identifier);

            case EOBJECT:
            case EOBJECT_CONTAINMENT:
                return new SingleReferenceAdapter(identifier);

            case EOBJECT_LIST:
            case EOBJECT_CONTAINMENT_LIST:
                return new MultiContainmentReferenceAdapter(identifier);

            case EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING:
                return new MultiContainmentReferenceWithProxyResolvingAdapter(identifier);

            case EOBJECT_LIST_PROXY_RESOLVING:
                return new MultiReferenceWithProxyResolvingAdapter(identifier);

            default:
                throw new RuntimeException("Unhandled case " + kind);
        }
    }

    public AttributeAdapter createEAttributeAdapter(FeatureIdentifier identifier) {
        EAttribute eAttribute= identifier.asAttribute();
        //EDataType type = eAttribute.getEAttributeType();

        if (identifier.isFeatureMap()) {
            return new FeatureMapAdapter(identifier);
        } else if (identifier.isEnumeration()) {
            return new EnumerationAdapter(identifier);
        } else if (eAttribute.isMany()) {
            return new MultivaluedAttributeAdapter(identifier);
        } else {
            return new SingleAttributeAdapter(identifier);
        }
    }

    public FeatureAdapter createEFeatureAdapter(FeatureIdentifier featureIdentifier) {
        if (featureIdentifier.isAttribute()) {
            return createEAttributeAdapter(featureIdentifier);
        } else {
            return createEReferenceAdapter(featureIdentifier);
        }
    }

    public Converter<String> getStringConverter() {
        return converters.getStringConverter();
    }

    public Converters converters() {
        return converters;
    }

    public AttributeValuesSerializer getObjectSerializer() {
        return attributeValuesSerializer;
    }

    public BufferWrangler readerFor(ByteBuffer buffer) {
        return new BufferWrangler(this, buffer);
    }

    public BufferWrangler writerFor(ByteBuffer buffer) {
        return new BufferWrangler(this, buffer);
    }

    public BufferWrangler allocateBuffer(int capacity) {
        return new BufferWrangler(this, ByteBuffer.allocate(capacity));
    }

    public WriterMetadata createWriterMetadata() {
        return new WriterMetadata(this);
    }

    public ReaderMetadata createReaderMetadata() {
        return new ReaderMetadata(this);
    }

    public AttributeValuesSerializer createAttributeValuesSerializer() {
        return new AttributeValuesSerializer(this);
    }

    public MetadataDeserializer createMetadataDeserializer(ByteBuffer buffer) {
        return new MetadataDeserializer(this, new BufferWrangler(this, buffer));
    }
}
