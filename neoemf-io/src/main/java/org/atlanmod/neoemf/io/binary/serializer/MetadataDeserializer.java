package org.atlanmod.neoemf.io.binary.serializer;

import org.atlanmod.commons.io.UnsignedByte;
import org.atlanmod.commons.io.UnsignedInt;
import org.atlanmod.commons.io.UnsignedShort;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.atlanmod.neoemf.io.binary.frame.ReaderMetadata;
import org.atlanmod.neoemf.io.binary.identifier.Properties;

public class MetadataDeserializer {
    private final BinaryAdapterProvider provider;
    private final BufferWrangler reader;

    private UnsignedByte expectedPackages;
    private UnsignedShort expectedClasses;
    private UnsignedInt expectedObjects;

    private ReaderMetadata metadata;

    public MetadataDeserializer(BinaryAdapterProvider provider, BufferWrangler reader) {
        this.provider = provider;
        this.reader = reader;
        this.metadata = provider.createReaderMetadata();
    }

    public ReaderMetadata read() {
        this.readPreamble();
        this.readPackages();

        return metadata;
    }

    private void readPreamble() {
        expectedPackages = reader.getUnsignedByte();
        expectedClasses = reader.getUnsignedShort();
        expectedObjects = reader.getUnsignedInt();
        metadata.onPreamble(expectedPackages, expectedClasses, expectedObjects);
    }

    private void readPackages() {
        //Log.info("Reading EPackages Metadata");

        for (int i = 0; i < expectedPackages.intValue(); i++) {
            String uri = reader.getString();
            String namespace = reader.getString();
            metadata.onPackage(i, uri, namespace);
            this.readClasses();
        }
    }

    private void readClasses() {
        //Log.info("Reading EClasses Metadata");
        UnsignedShort expectedClasses = reader.getUnsignedShort();
        //Log.info("Reading {0} EClasses", expectedClasses);

        for (int i = 0; i < expectedClasses.intValue(); i++) {
            UnsignedShort index = reader.getUnsignedShort();
            UnsignedVarInt classifierID = reader.getUnsignedVarInt();
            String name = reader.getString();
            UnsignedInt instancesCount = reader.getUnsignedInt();

            metadata.onClass(index.intValue(), classifierID, name, instancesCount);

            this.readAttributes();
            this.readReferences();
        }

    }

    private void readAttributes() {
        //Log.info("Reading Features Metadata");
        UnsignedShort expectedAttributes = reader.getUnsignedShort();
        for (int i = 0; i < expectedAttributes.intValue(); i++) {
            UnsignedShort featureID = reader.getUnsignedShort();
            //UnsignedByte index = reader.getUnsignedByte();
            String name = reader.getString();
            Properties properties = Properties.readFrom(reader);

            //Log.info("(i: " + i + ", index: " + index + ")");
            metadata.onAttribute(i, featureID, name, properties);
        }
    }

    private void readReferences() {
        //Log.info("Reading Features Metadata");
        UnsignedShort expectedReferences = reader.getUnsignedShort();
        for (int i = 0; i < expectedReferences.intValue(); i++) {
            UnsignedShort featureID = reader.getUnsignedShort();
            //UnsignedByte index = reader.getUnsignedByte();
            String name = reader.getString();
            Properties properties = Properties.readFrom(reader);

            //Log.info("(i: " + i + ", index: " + index + ")");
            metadata.onReference(i, featureID, name, properties);
        }
    }
}
