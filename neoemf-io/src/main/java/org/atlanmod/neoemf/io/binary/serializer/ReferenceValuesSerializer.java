package org.atlanmod.neoemf.io.binary.serializer;

import org.atlanmod.commons.collect.Flags;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.adapter.*;
import org.eclipse.emf.ecore.EObject;

import static org.atlanmod.commons.Preconditions.requireThat;

public class ReferenceValuesSerializer {
    private final BinaryAdapterProvider provider;

    public ReferenceValuesSerializer(BinaryAdapterProvider provider) {
        this.provider = provider;
    }

    public void writeOn(BufferWrangler buffer, ObjectAdapter objectAdapter) {
        requireThat(buffer).isNotNull();
        requireThat(objectAdapter).isNotNull();
        requireThat(objectAdapter.classAdapter()).isNotNull();

        ClassAdapter classAdapter = objectAdapter.classAdapter();
        // 1. Write ClassIndex
        buffer.put(UnsignedVarInt.fromInt(classAdapter.index()));

        // 2. Write Flags
        Flags flags = classAdapter.createFlagsFor(objectAdapter);
        byte[] bytes = flags.toBytes();
        buffer.put(bytes);

        // 3. Write Set Attribute Values (Ordered by the adapter index)
        EObject eObject = objectAdapter.adaptee();
        for (ReferenceAdapter each : classAdapter.references()) {
            if (eObject.eIsSet(each.adaptee())) {
                //each.writeValueOn(buffer, objectAdapter);
            }
        }
    }
}
