package org.atlanmod.neoemf.io.binary.identifier;

import org.atlanmod.neoemf.io.binary.Style;
import org.atlanmod.neoemf.io.binary.adapter.ClassAdapter;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * "Object Parameter" class for creating adapters for EAttributes and EReferences
 */
@ParametersAreNonnullByDefault
public class FeatureIdentifier extends Identifier {
    private final EStructuralFeature feature;
    private final ClassAdapter classAdapter;
    private final int featureID;
    private Style style;

    public FeatureIdentifier(int index, BinaryAdapterProvider provider, EStructuralFeature feature, ClassAdapter classAdapter) {
        super(index, provider);
        this.feature = feature;
        this.classAdapter = classAdapter;
        this.featureID = classAdapter.adaptee().getFeatureID(feature);
    }

    public EStructuralFeature feature() {
        return feature;
    }

    public EAttribute asAttribute() {
        return (EAttribute) feature;
    }

    public EReference asReference() {
        return (EReference) feature;
    }

    public EStructuralFeature.Internal asInternal() {
        return (EStructuralFeature.Internal) feature;
    }

    public ClassAdapter classAdapter() {
        return classAdapter;
    }

    public boolean isAttribute() {
        return  feature instanceof EAttribute;
    }

    public boolean isReference() {
        return feature instanceof EReference;
    }

    public boolean isFeatureMap() {
        return asInternal().isFeatureMap();
    }

    public boolean isEnumeration() {
        // I cannot find a better way to find out if the EAttribute is an Enumeration
        return isAttribute()
                && asAttribute().getEAttributeType() instanceof EEnum;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Style style() {
        return style;
    }

    public int featureID() {
        return this.featureID;
    }
}
