package org.atlanmod.neoemf.io.binary.frame;

import org.atlanmod.commons.Preconditions;
import org.atlanmod.commons.io.UnsignedByte;
import org.atlanmod.commons.io.UnsignedInt;
import org.atlanmod.commons.io.UnsignedShort;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.commons.log.Log;
import org.atlanmod.neoemf.io.binary.adapter.*;
import org.atlanmod.neoemf.io.binary.identifier.Properties;
import org.eclipse.emf.ecore.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ReaderMetadata extends Metadata {
    private final EPackage.Registry registry = EPackage.Registry.INSTANCE;
    private PackageAdapter[] packages;
    private ClassAdapter[] classes;
    private PackageAdapter lastPackageRead;
    private ClassAdapter lastClassRead;
    private long expectedObjects;

    public ReaderMetadata(BinaryAdapterProvider provider) {
        super(provider);
    }

    public void onPreamble(UnsignedByte expectedPackages, UnsignedShort expectedClasses, UnsignedInt expectedObjects) {
        Log.info("Expecting {0} packages, {1} classes, and {2} objects",
                expectedPackages, expectedClasses, expectedObjects);

        this.packages = new PackageAdapter[expectedPackages.intValue()];
        this.classes = new ClassAdapter[expectedClasses.intValue()];
        this.expectedObjects = expectedObjects.longValue();
    }

    public void onPackage(int index, String uri, String namespace) {
        Log.info("Reading package {0} metadata", namespace);
        EPackage ePackage = registry.getEPackage(namespace);
        if (Objects.isNull(ePackage)) {
            throw new RuntimeException("EPackage " + namespace + " not found, resource could not be loaded");
        } else {
            Log.info("Found EPackage: " + ePackage);
            this.put(index, ePackage);
        }
    }

    public void onClass(int index, UnsignedVarInt classifierID, String name, UnsignedInt instancesCount) {
        //Log.info("Reading class name:{0}, index:{1}, id:{2} metadata", name, index, classifierID);
        assert Objects.nonNull(lastPackageRead);

        EClass eClass = (EClass) lastPackageRead.adaptee().getEClassifier(name);
        if (Objects.isNull(eClass)) {
            throw new RuntimeException("EClass " + name + " not found, resource could not be loaded");
        } else {
            //Log.info("Found EClass: " + eClass);
            this.put(index, eClass);
        }
    }

    public void onAttribute(int index, UnsignedShort featureID, String name, Properties properties) {
        Preconditions.requireThat(featureID).isNotNull();
        Preconditions.requireThat(name).isNotNull();
        Preconditions.requireThat(properties).isNotNull();

        //Log.info("Reading feature name: {0}, ID: {1}, index: {2}, and properties: {3}", name, featureID, index, properties);

        EClass eClass = lastClassRead.adaptee();
        EAttribute currentAttribute = (EAttribute) eClass.getEStructuralFeature(name);
        AttributeAdapter currentAdapter = lastClassRead.adapt(currentAttribute);
        this.checkFeature(index, featureID, name, properties, currentAdapter);
    }


    public void onReference(int index, UnsignedShort featureID, String name, Properties properties) {
        // Log.info("Reading feature name: {0}, id: {1}, and properties: {2}",  name, featureID, properties);
        EClass eClass = lastClassRead.adaptee();
        EReference currentReference = (EReference) eClass.getEStructuralFeature(name);
        ReferenceAdapter currentAdapter = lastClassRead.adapt(currentReference);
        checkFeature(index, featureID, name, properties, currentAdapter);
    }

    /**
     * Just for verification that the Ecore Model did not change meanwhile
     */
    private void checkFeature(int index, UnsignedShort featureID, String name,
                              Properties properties, FeatureAdapter currentAdapter) {

        int incompatibilityCount = 0;

        if (featureID.intValue() != currentAdapter.featureID()) {
            Log.warn("Existing feature {0} has a different ID. Current: {1}, Loaded: {2}",
                    currentAdapter.name(), currentAdapter.featureID(), featureID);
            incompatibilityCount++;
        }

        if (!properties.equals(currentAdapter.properties())) {
            Log.warn("Existing feature {0} has different Properties. Current: {0}, Loaded: {1}",
                    currentAdapter.name(), currentAdapter.properties(), properties);
            incompatibilityCount++;
        }

        if (index != currentAdapter.index()) {
            Log.warn("Existing feature {0} has a different Index. Current: {1}, Loaded: {2}",
                    currentAdapter.name(), currentAdapter.index(), index);
            incompatibilityCount++;
        }

        if (incompatibilityCount > 0) {
            throw new RuntimeException("FeatureAdapter " + currentAdapter
                    + " is not compatible  with name: " + name
                    + " id: " + featureID
                    + " properties: " + properties
                    + " index: " + index
                    + "\n Resource can not be loaded");
        }
    }

    private void put(int index, EPackage ePackage) {
        assert index < packages.length;

        lastPackageRead = this.createAdapter(ePackage);
        packages[index] = lastPackageRead;
    }

    private void put(int index, EClass eClass) {
        lastClassRead = lastPackageRead.adapt(eClass);
        classes[index] = lastClassRead;
    }

    public ClassAdapter classAdapterFromIndex(int index) {
        Preconditions.requireThat(index).isLessThan(classes.length);
        return classes[index];
    }

    public int classAdapterSize() {
        return classes.length;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReaderMetadata{");
        sb.append("registry=").append(registry);
        sb.append(", packages=").append(Arrays.toString(packages));
        sb.append('}');
        return sb.toString();
    }


    public boolean isCompatibleWith(WriterMetadata other) {
        return this.packages.length == other.packages().size()
                && this.classes.length == other.classes().size()
                && List.of(packages).containsAll(other.packages().values())
                && List.of(classes).containsAll(other.classes())
                && this.classIndexesAreCompatible(other);
    }

    /**
     * Ensures that every EClass written with a given index retrieves the
     * same index when metadata is read.
     *
     * @param other
     * @return
     */
    private boolean classIndexesAreCompatible(WriterMetadata other) {
        for (int i = 0; i < classes.length; i++) {
            EClass eClass = classes[i].adaptee();
            ClassAdapter adapter = other.adapt(eClass);
            if (i != adapter.index()) return false;
        }
        return true;
    }

    public long getExpectedObjects() {
        return expectedObjects;
    }

}
