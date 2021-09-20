package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.commons.Preconditions;
import org.atlanmod.commons.io.UnsignedNumber;
import org.atlanmod.commons.io.UnsignedShort;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;
import org.atlanmod.neoemf.io.binary.identifier.Properties;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public abstract class FeatureAdapter<T> extends EcoreAdapter {
    //private BinaryAdapterProvider provider;
    private final String name;
    private final int featureID;
    protected Properties properties;

    private  EStructuralFeature.Internal adaptee;
    private int index;
    protected ClassAdapter eClassAdapter;

    public FeatureAdapter(FeatureIdentifier identifier) {
        //this.provider = identifier.provider();
        this.adaptee = identifier.asInternal();
        this.name = adaptee.getName();
        this.index = identifier.index();
        this.eClassAdapter = identifier.classAdapter();
        this.featureID = identifier.featureID();

        //Log.info("Creating " + this.getClass().getName() +   "for feature: " + eClass.getName() + "::" + name + " id: " + featureID);
    }


    public int index() {
        return index;
    }

    public int featureID() {
        return featureID;
    }

    public String name() {
        return name;
    }

    public String convertToString(Object value) {
        throw new RuntimeException("Not Yet Implemented: " + this.getClass());
    }


    public Properties properties() {
        Preconditions.requireThat(properties).isNotNull();

        return  properties;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("name:")
                .append(name)
                .append(", index: ")
                .append(index);
        sb.append(", id:").append(featureID);
        sb.append(", properties=").append(properties);
        sb.append('}');
        return sb.toString();
    }
}
