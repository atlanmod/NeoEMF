package org.atlanmod.neoemf.io.binary;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyClass;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyValue;
import fr.inria.atlanmod.neoemf.io.reader.AbstractReader;
import org.atlanmod.commons.collect.Flags;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.commons.log.Log;
import org.atlanmod.commons.tuple.Pair;
import org.atlanmod.neoemf.io.binary.adapter.AttributeAdapter;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.atlanmod.neoemf.io.binary.adapter.ClassAdapter;
import org.atlanmod.neoemf.io.binary.adapter.ReferenceAdapter;
import org.atlanmod.neoemf.io.binary.frame.Header;
import org.atlanmod.neoemf.io.binary.frame.Index;
import org.atlanmod.neoemf.io.binary.frame.ReaderMetadata;
import org.atlanmod.neoemf.io.binary.serializer.MetadataDeserializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collection;

public class BinaryReader extends AbstractReader<FileChannel> {

    private FileChannel channel;
    private Header header;
    private BinaryAdapterProvider provider = new BinaryAdapterProvider();
    private ReaderMetadata metadata;
    private Index index;
    private ProxyElement[] elementCache;
    private ProxyClass[] classCache;


    @Override
    public void read(FileChannel source) throws IOException {
        this.channel = source;
        this.readHeader();
        long savePosition = channel.position();
        final long metadataPosition = channel.size() - header.getMetadataLength();
        channel.position(metadataPosition);
        this.readMetadata();

        long idsPosition = metadataPosition - (header.getIndexLength()
                + header.getReferencesLength()
                //+ metadata.getReferencesFrameLength()
        );
        channel.position(idsPosition);
        this.readIndex();
        this.initializeClassCache();
        this.initializeElementCache();

        this.readReferences();
        channel.position(savePosition);
        this.readAttributes();
    }

    private void readHeader() throws IOException {
        BufferWrangler buffer = provider.allocateBuffer(Header.SIZE);
        channel.read(buffer.toByteBuffer());
        buffer.rewind();
        this.header = buffer.get(Header.class);
        Log.info("Read header: {0}", header);
    }

    private void readMetadata() throws IOException {
        Log.info("Will read {0} bytes (channel size)", channel.size());

        ByteBuffer buffer = ByteBuffer.allocate(header.getMetadataLength());
        channel.read(buffer);
        buffer.rewind();
        MetadataDeserializer deserializer = provider.createMetadataDeserializer(buffer);
        this.metadata = deserializer.read();
    }



    private void readIndex() throws IOException {
        int size = header.getIndexLength();
        BufferWrangler buffer = provider.allocateBuffer(size);
        channel.read(buffer.toByteBuffer());
        buffer.rewind();
        this.index = buffer.get(Index.class);


    }

    private void initializeClassCache() {
        int size = metadata.classAdapterSize();
        this.classCache = new ProxyClass[size];
        for (int i = 0; i < size; i++) {
            ClassAdapter adapter = metadata.classAdapterFromIndex(i);
            classCache[i] = new ProxyClass(adapter.adaptee());
        }
    }

    private void initializeElementCache() {
        this.elementCache = new ProxyElement[index.size()];

        for (int i = 0; i < index.size(); i++) {
            Pair<Id, Integer> entry = index.idFor(i);
            elementCache[i] = new ProxyElement()
                    .setId(ProxyValue.raw(entry.left))
                    .setMetaClass(classCache[entry.right]);
        }
    }

    private void readAttributes() throws IOException {
        final long expectedObjects = metadata.getExpectedObjects();
        Log.info("Reading attribute values for {0} objects at position {1}",
                expectedObjects, channel.position());
        BufferWrangler buffer = provider.allocateBuffer(header.getAttributesLength());
        channel.read(buffer.toByteBuffer());
        buffer.rewind();
        for (int i = 0; i < expectedObjects; i++) {

            Integer classIndex = index.idFor(i).right;
            ClassAdapter adapter = metadata.classAdapterFromIndex(classIndex);
            byte[] bytes = new byte[adapter.expectedAttributeFlagsLength()];
            buffer.get(bytes);
            Flags flags = Flags.fromBytes(bytes);
            /*Log.info("Expected flag size: {0}; Read flags: {1}",
                    adapter.expectedAttributeFlagsLength(), flags);*/
            int readCount = 0;
            for (int j = 0; j < adapter.getAttributeCount(); j++) {
                if (flags.get(j)) {
                    readCount++;
                    AttributeAdapter aa = adapter.attributeAdapterFor(j);
                    Collection<Object> values = aa.readAttributeValuesFrom(buffer);

                    //ProxyValue.raw()
                    //Log.info("Read {0} targets for reference {1}:{2}", targets.size(), i, j);
                    // TODO
                }
            }
            /*Log.info("Reading {0} attributes for object {1}",
                    readCount, i);*/
        }

        /*
        this.notifyStartElement(null);
        this.notifyStartAttributeList();
        this.notifyAttribute(null);
        this.notifyEndAttributeList();

        this.notifyReference(null);

        this.notifyEndElement();

         */

    }

    private void readReferences() throws IOException {
        final long expectedObjects = metadata.getExpectedObjects();
        Log.info("Reading reference values for {0} objects", expectedObjects);

        BufferWrangler buffer = provider.allocateBuffer(header.getReferencesLength());
        channel.read(buffer.toByteBuffer());
        buffer.rewind();
        for (int i = 0; i < expectedObjects; i++) {
            //Log.info("Reading references for object {0}", i);
            Integer classIndex = index.idFor(i).right;
            ClassAdapter adapter = metadata.classAdapterFromIndex(classIndex);
            byte[] bytes = new byte[adapter.expectedReferenceFlagsLength()];
            buffer.get(bytes);
            Flags flags = Flags.fromBytes(bytes);
            //Log.info("Expected flag size: {0}; Read flags: {1}", adapter.expectedReferenceFlagsLength(), flags);
            for (int j = 0; j < adapter.getReferenceCount(); j++) {
                if (flags.get(j)) {
                    ReferenceAdapter ra = adapter.referenceAdapterFor(j);
                    Collection<UnsignedVarInt> targets = ra.readValuesFrom(buffer);
                    //Log.info("Read {0} targets for reference {1}:{2}", targets.size(), i, j);
                    // TODO
                }
            }
        }
    }

}
