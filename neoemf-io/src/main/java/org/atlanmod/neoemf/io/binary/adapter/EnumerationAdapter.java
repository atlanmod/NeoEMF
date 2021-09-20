package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.commons.Throwables;
import org.atlanmod.commons.io.UnsignedByte;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.identifier.FeatureIdentifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;

import java.util.Collection;
import java.util.List;

import static org.atlanmod.commons.io.Numbers.ubyte;

public class EnumerationAdapter extends AttributeAdapter {
    private final EEnum enumeration;

    public EnumerationAdapter(FeatureIdentifier identifier) {
        super(identifier);
        this.enumeration = (EEnum) identifier.asAttribute().getEAttributeType();
    }

    @Override
    public void writeAttributeValueOn(BufferWrangler buffer, ObjectAdapter objectAdapter) {
        throw Throwables.notImplementedYet("EnumerationAdapter::readAttributeValueFrom()");
    }


    @Override
    public void writeAttributeValueOn(BufferWrangler buffer) {
        String value = (String) currentValues.get(0);
        EEnumLiteral literal = enumeration.getEEnumLiteralByLiteral(value);
        UnsignedByte ub = ubyte(literal.getValue());
        buffer.put(ub);
    }

    @Override
    public void readAttributeValueFrom(BufferWrangler buffer, ObjectAdapter objectAdapter) {
        throw Throwables.notImplementedYet("EnumerationAdapter::readAttributeValueFrom()");
    }

    @Override
    public Collection<Object> readAttributeValuesFrom(BufferWrangler buffer) {
       return List.of(buffer.getUnsignedByte());
    }

    @Override
    public String toString() {
        return "EnumerationAttribute: " + super.toString();
    }
}
