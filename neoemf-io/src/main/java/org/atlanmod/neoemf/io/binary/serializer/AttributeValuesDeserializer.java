package org.atlanmod.neoemf.io.binary.serializer;

import org.atlanmod.commons.collect.Flags;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.adapter.AttributeAdapter;
import org.atlanmod.neoemf.io.binary.adapter.ClassAdapter;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.atlanmod.neoemf.io.binary.adapter.ObjectAdapter;
import org.atlanmod.neoemf.io.binary.frame.ReaderMetadata;
import org.eclipse.emf.ecore.EObject;

import java.util.List;

public class AttributeValuesDeserializer {
    private final BinaryAdapterProvider provider;
    private final ReaderMetadata metadata;

    public AttributeValuesDeserializer(BinaryAdapterProvider provider, ReaderMetadata metadata) {
        this.provider = provider;
        this.metadata = metadata;
    }

    public EObject readFrom(BufferWrangler buffer) {

        // 1. Read ClassIndex
        UnsignedVarInt cIndex = buffer.getUnsignedVarInt();
        ClassAdapter classAdapter = metadata.classAdapterFromIndex(cIndex.intValue());
        EObject eObject = classAdapter.instantiate();
        ObjectAdapter objectAdapter = classAdapter.adapt(eObject);

        // 2. Read Flags
        int length = classAdapter.expectedAttributeFlagsLength();
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        Flags flags = Flags.fromBytes(bytes);

        // 3. Read Attribute Values
        List<AttributeAdapter> attributes = classAdapter.attributes();
        for (AttributeAdapter each : attributes) {
            if (flags.get(each.index())) {
                each.readAttributeValueFrom(buffer, objectAdapter);
            }
        }


        return eObject;
    }
}
