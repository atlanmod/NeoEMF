package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.Converter;
import org.atlanmod.neoemf.io.binary.converter.Converters;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;
import org.atlanmod.neoemf.io.binary.identifier.Properties;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;

import java.util.Collection;
import java.util.List;

public abstract class AttributeAdapter extends FeatureAdapter {

    protected EDataType eDataType;
    protected EFactory eFactory;
    protected EAttribute eAttribute;
    private final Converter converter;
    protected List<Object> currentValues;

    public AttributeAdapter(FeatureIdentifier identifier) {
        super(identifier);
        this.eAttribute = identifier.asAttribute();
        this.properties = new Properties(eAttribute);

        this.eDataType = eAttribute.getEAttributeType();
        this.eFactory = eDataType.getEPackage().getEFactoryInstance();
        Converters converters = identifier.provider().converters();

        if (eDataType instanceof EEnum) {

            converter = converters.getEnumConverter();
        } else {
            converter = converters.getPrimitiveTypeConverter(eDataType.getClassifierID());
        }
    }

    public EAttribute adaptee() {
        return eAttribute;
    }


    public Converter converter() {
        return converter;
    }

    public AttributeAdapter withValues(List<Object> value) {
        this.currentValues = value;
        return this;
    }

    public abstract void writeAttributeValueOn(BufferWrangler buffer, ObjectAdapter objectAdapter);
    public abstract void writeAttributeValueOn(BufferWrangler buffer);
    public abstract void readAttributeValueFrom(BufferWrangler buffer, ObjectAdapter objectAdapter);
    public abstract Collection<Object> readAttributeValuesFrom(BufferWrangler buffer);

}
