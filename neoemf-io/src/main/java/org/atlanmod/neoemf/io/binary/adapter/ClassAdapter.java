package org.atlanmod.neoemf.io.binary.adapter;

import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;
import org.atlanmod.commons.Guards;
import org.atlanmod.commons.collect.Flags;
import org.atlanmod.commons.io.Numbers;
import org.atlanmod.commons.io.UnsignedShort;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.commons.log.Log;
import org.atlanmod.neoemf.io.binary.identifier.ClassIdentifier;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;
import org.eclipse.emf.ecore.*;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.atlanmod.commons.io.Numbers.uvarint;

public class ClassAdapter extends EcoreAdapter {
    private final int index;
    private final BinaryAdapterProvider provider;
    private final String name;
    private final UnsignedVarInt classifierID;
    private int instancesCount = 0;
    private EClass adaptee;
    //private Map<EStructuralFeature, FeatureAdapter> features = new HashMap<>();
    private final Map<EStructuralFeature, AttributeAdapter> attributes = new HashMap<>();
    private final Map<EStructuralFeature, ReferenceAdapter> references = new HashMap<>();
    private final int[] indexCache;
    private final AttributeAdapter[] attributeAdapterCache;
    private final ReferenceAdapter[] referenceAdapterCache;

    public ClassAdapter(ClassIdentifier identifier) {
        this.index = identifier.index();
        this.adaptee = identifier.eClass();
        this.name = adaptee.getName();
        this.classifierID = uvarint(adaptee.getClassifierID());
        this.provider = identifier.provider();

        // Initialize caches:
        final int featureCount = adaptee.getFeatureCount();
        this.indexCache = new int[featureCount];
        this.attributeAdapterCache = new AttributeAdapter[featureCount];
        this.referenceAdapterCache = new ReferenceAdapter[featureCount];

        this.initializeFeatures();
        this.verify();
    }

    /**
     * Returns an index for a given featureID of the adapted ECLass.
     *
     * @param featureID a featureID
     * @return an {@code int}
     */
    public int indexFor(int featureID) {
        Guards.checkElementIndex(featureID, indexCache.length);

        return indexCache[featureID];
    }

    /**
     * Returns the {@code ReferenceAdapter} corresponding to the index.
     *
     * @param index a ReferenceAdapter index
     * @return an instance of {@code ReferenceAdapter}
     */
    public ReferenceAdapter referenceAdapterFor(int index) {
        Guards.checkElementIndex(index, referenceAdapterCache.length);

        return referenceAdapterCache[index];
    }

    /**
     * Returns the {@code AttributeAdapter} corresponding to the index.
     *
     * @param index a AttributeAdapter index
     * @return an instance of {@code AttributeAdapter}
     */
    public AttributeAdapter attributeAdapterFor(int index) {
        Guards.checkElementIndex(index, referenceAdapterCache.length);

        return attributeAdapterCache[index];
    }

    public String dumpIndexes() {
        return Arrays.toString(indexCache);
    }

    public List<AttributeAdapter> attributes() {
        return attributes.values().stream()
                .sorted(Comparator.comparingInt(AttributeAdapter::index))
                .collect(Collectors.toList());
    }

    public List<ReferenceAdapter> references() {
        return references.values().stream()
                .sorted(Comparator.comparingInt(ReferenceAdapter::index))
                .collect(Collectors.toList());
    }

    private void initializeFeatures() {
        this.initializeAttributes();
        this.initializeReferences();
    }

    /**
     * Creates a AttributeAdapter for each feature that is a sub-instance of {@code EAttribute}
     */
    private void initializeAttributes() {
        final AtomicInteger index = new AtomicInteger(0);
        adaptee.getEAllStructuralFeatures().stream()
                .filter(x -> x instanceof EAttribute)
                .map(attr ->
                        new FeatureIdentifier(index.getAndIncrement(), provider, attr, this))
                .forEach(identifier -> {
                    AttributeAdapter adapter = provider.createEAttributeAdapter(identifier);
                    attributes.put(identifier.asAttribute(), adapter);
                    indexCache[identifier.featureID()] = identifier.index();
                    attributeAdapterCache[identifier.index()] = adapter;
                });
    }

    /**
     * Creates a ReferenceAdapter for each feature that is a sub-instance of {@code EReference}
     */
    private void initializeReferences() {
        final AtomicInteger index = new AtomicInteger(0);
        adaptee.getEAllStructuralFeatures().stream()
                .filter(x -> x instanceof EReference)
                .map(attr ->
                        new FeatureIdentifier(index.getAndIncrement(), provider, attr, this))
                .forEach(identifier -> {
                    final ReferenceAdapter adapter = provider.createEReferenceAdapter(identifier);
                    references.put(identifier.asReference(), adapter);
                    indexCache[identifier.featureID()] = identifier.index();
                    referenceAdapterCache[identifier.index()] = adapter;
                });
    }

    /**
     * Expected length, in bytes, for Attribute Flags
     *
     * @return the length
     */
    public int expectedAttributeFlagsLength() {
        return (int) Math.ceil((double) attributes.size() / Byte.SIZE);
    }


    public int expectedReferenceFlagsLength() {
        return (int) Math.ceil((double) references.size() / Byte.SIZE);
    }

    public AttributeAdapter adapt(EAttribute attribute) {
        Guards.checkArgument(attributes.containsKey(attribute), "attribute unknown");

        return attributes.get(attribute);
    }

    public ReferenceAdapter adapt(EReference reference) {
        return references.get(reference);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClassAdapter{ index: ")
                .append(index)
                .append(", name: ")
                .append(name);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassAdapter that = (ClassAdapter) o;
        return instancesCount == that.instancesCount
                && Objects.equals(name, that.name)
                && Objects.equals(classifierID, that.classifierID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, provider, name, classifierID, instancesCount, attributes, references);
    }

    public int index() {
        return index;
    }

    public String name() {
        return name;
    }

    public UnsignedVarInt id() {
        return classifierID;
    }

    public int instancesCount() {
        return instancesCount;
    }

    public EClass adaptee() {
        return adaptee;
    }

    public EObject instantiate() {
        return adaptee.getEPackage().getEFactoryInstance().create(adaptee);
    }

    public int getFeatureCount() {
        return adaptee.getFeatureCount();
    }

    public int getReferenceCount() {
        return references.size();
    }

    public int getAttributeCount() {
        return attributes.size();
    }

    /*
    public Map<EStructuralFeature, FeatureAdapter> features() {
        return features;
    }
    */

    /*
    public void saveAllFeatures(EObjectOutputStream stream, InternalEObject eObject) throws IOException {
        for (int i = 0, length = features_array.length; i < length; ++i) {
            EFeatureAdapter each = features_array[i];
            if (!each.isTransient() && eObject.eIsSet(i)) {
                each.saveValueOn(stream, eObject);
                stream.flush();
            }
        }
    }

     */

    // region I/O


   /*
    public void writeOn(BufferWrangler writer) {
        writer.put(UnsignedShort.fromInt(index));
        writer.put(classifierID);
        writer.putString(name);
        writer.put(UnsignedInt.fromInt(instancesCount));
        this.writeFeaturesOn(writer);
    }

    public void writeFeaturesOn(BufferWrangler writer) {
        requireThat(writer).isNotNull();

        writer.put(UnsignedShort.fromInt(features.size()));
        for (FeatureAdapter each : features.values()) {
            each.writeOn(writer);
        }
    }
    */

    public Flags createFlagsFor(ObjectAdapter objectAdapter) {
        EObject eObject = objectAdapter.adaptee();
        Flags flags = createAttributeFlags();

        for (AttributeAdapter each : attributes.values()) {
            if (eObject.eIsSet(each.adaptee())) {
                flags.set(each.index());
            }
        }

        return flags;
    }

    @NotNull
    public Flags createAttributeFlags() {
        Flags flags = new Flags(attributes.size());
        return flags;
    }

    @NotNull
    public Flags createReferenceFlags() {
        Flags flags = new Flags(references.size());
        return flags;
    }

    public ObjectAdapter readAttributeValuesFrom(ByteBuffer buffer) {
        // 2. Read Flags
        Flags flags = this.readFlagsFrom(buffer);

        return null;
    }

    private Flags readFlagsFrom(ByteBuffer buffer) {
        int length = attributes.size();
        byte[] bytes = new byte[length];//FIXME
        buffer.get(bytes);
        return Flags.fromBytes(bytes);
    }

    public ObjectAdapter adapt(EObject eObject) {
        ObjectAdapter adapter = provider.createObjectAdapter(this, eObject);
        instancesCount++;

        return adapter;
    }
    // endregion

    private void verify() {
        BitSet readFeatures = new BitSet();
        for (EStructuralFeature each : adaptee.getEAllStructuralFeatures()) {
            int id = this.adaptee.getFeatureID(each);
            // each.getFeatureID() - does not work properly
            if (!readFeatures.get(id)) {
                readFeatures.set(id);
            } else {
                Log.warn("Class {0} has more than one features with the same id: {1}",
                        adaptee.getName(), id);
            }
        }
    }

    public AttributeAdapter adapt(ProxyAttribute attribute) {
        return this.adapt(attribute.getOrigin());
    }

    public ReferenceAdapter adapt(ProxyReference reference) {
        ReferenceAdapter adapter = this.adapt(reference.getOrigin());
        if (Objects.isNull(adapter)) {
            Log.warn("Could not find adapter for {0}", reference.getOrigin());
            Log.warn("Available references: {0}", this.references.keySet());
        }

        return adapter;
    }

}
