package org.atlanmod.neoemf.io.binary.serializer;

import org.atlanmod.commons.io.Numbers;
import org.atlanmod.commons.io.UnsignedByte;
import org.atlanmod.commons.io.UnsignedInt;
import org.atlanmod.commons.io.UnsignedShort;
import org.atlanmod.commons.log.Log;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.adapter.*;
import org.atlanmod.neoemf.io.binary.frame.WriterMetadata;

import static org.atlanmod.commons.io.Numbers.*;

public class MetadataSerializer {

    private final WriterMetadata metadata;
    private final BufferWrangler writer;

    public MetadataSerializer(BufferWrangler writer, WriterMetadata metadata) {
        this.writer = writer;
        this.metadata = metadata;
    }

    public void write() {
        writePreamble();
        writePackages();
    }

    private void writePreamble() {
        Log.info("Write preamble with {0} packages, {1} classes, and {2} objects",
                metadata.packages().size(),
                metadata.classes().size(),
                metadata.getObjectCount());

        UnsignedByte packagesLength = ubyte(metadata.packages().size());
        UnsignedShort classesLength = ushort(metadata.classes().size());
        UnsignedInt objectsLength   = uint(metadata.getObjectCount());

        writer.put(packagesLength);
        writer.put(classesLength);
        writer.put(objectsLength);
    }

    // region Packages

    private void writePackages() {
        for (PackageAdapter each : metadata.packages().values()) {
            writePackage(each);
        }
    }

    private void writePackage(PackageAdapter each) {
        writer.putString(each.uri());
        writer.putString(each.namespace());
        this.writeClasses(each);
    }

    // endregion

    // region classes
    private void writeClasses(PackageAdapter adapter) {
        UnsignedShort classesLength = UnsignedShort.fromInt(adapter.classes().size());
        writer.put(classesLength);

        for (ClassAdapter each : adapter.classes().values()) {
            writeClass(each);
        }
    }

    private void writeClass(ClassAdapter adapter) {
        writer.put(UnsignedShort.fromInt(adapter.index()));
        writer.put(adapter.id());
        writer.putString(adapter.name());
        writer.put(UnsignedInt.fromInt(adapter.instancesCount()));
        writeAttributes(adapter);
        writeReferences(adapter);
    }
    // endregion

    //region Attributes

    private void writeAttributes(ClassAdapter adapter) {
        //int position = 0;
        writer.put(UnsignedShort.fromInt(adapter.attributes().size()));
        for (AttributeAdapter each : adapter.attributes()) {
            //Log.info("Writing index {0} at position {1}", each.index(), position++);
            writeAttribute(each);
        }
    }

    private void writeAttribute(AttributeAdapter each) {
        writer.put(ushort(each.featureID()));
        //writer.put(UnsignedByte.fromInt(each.index()));
        writer.putString(each.name());
        each.properties().writeOn(writer);
    }

    // endregion

    //region References
    private void writeReferences(ClassAdapter adapter) {
        //int position = 0;
        writer.put(UnsignedShort.fromInt(adapter.references().size()));
        for (ReferenceAdapter each : adapter.references()) {
            //Log.info("Writing index {0} at position {1}", each.index(), position++);
            writeReference(each);
        }
    }

    private void writeReference(FeatureAdapter each) {
        writer.put(ushort(each.featureID()));
        //writer.put(UnsignedByte.fromInt(each.index()));
        writer.putString(each.name());
        each.properties().writeOn(writer);
    }
    // endregion

}
