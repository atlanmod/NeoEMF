package org.atlanmod.neoemf.io.binary;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;
import fr.inria.atlanmod.neoemf.io.writer.AbstractWriter;
import org.atlanmod.commons.collect.Flags;
import org.atlanmod.commons.log.Log;
import org.atlanmod.neoemf.io.binary.adapter.*;

import org.atlanmod.neoemf.io.binary.frame.*;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.stream.Collectors;

import static org.atlanmod.commons.Preconditions.requireThat;
import static org.atlanmod.commons.io.Numbers.uint;

public class BinaryWriter extends AbstractWriter<FileChannel> {
    private final FileChannel channel;
    private final BinaryAdapterProvider provider;

    private final Header header = Header.createDefaultHeader();

    private final WriterMetadata metadata;
    private final Options options;

    private BufferWrangler buffer;
    private Index index = new Index();
    private final References references = new References(index);


    // Current values modified during traversal
    private final Deque<ClassAdapter> classAdapters = new ArrayDeque<>();
    private final Deque<Id> elementIds = new LinkedList<>();
    private Flags currentAttributeFlags;
    private final List<AttributeAdapter> currentAttributeValues = new ArrayList<>(16);


    public BinaryWriter(FileChannel target) {
        super(target);
        this.channel = target;
        this.options = Options.empty();
        this.provider = new BinaryAdapterProvider(options);
        this.metadata = provider.createWriterMetadata();
        this.buffer = provider.allocateBuffer(10024);
    }

    @Override
    public void onInitialize() throws IOException {
        this.writeHeader();
        Log.info("Start Writing Attributes at position {0}", channel.position());
    }

    @Override
    public void onStartElement(ProxyElement element) throws IOException {
        super.onStartElement(element);

        EClass eClass = element.getMetaClass().getOrigin();
        ClassAdapter classAdapter = metadata.adapt(element.getMetaClass());
        /*if (classAdapter.expectedAttributeFlagsLength() == 0) {
            Log.info("Class {0} has no attributes", classAdapter.name());
        }*/


        //Log.info("Starting element {0} of eClass ({1}) and adapter {2}" , element, eClass.getName(), classAdapter);
        if (element.isRoot()) Log.info("Root: {0} id: {1}", element, element.getId().getResolved());

        // Attributes
        Id id = element.getId().getResolved();
        currentAttributeFlags = classAdapter.createAttributeFlags();
        index.store(id, classAdapter.index());
        classAdapters.add(classAdapter);
        elementIds.addLast(id);

        // References
        references.onStartElement(classAdapter, id);

    }

    public void onStartAttributeList() throws IOException {
        this.currentAttributeValues.clear();
    }

    /**
     * @see fr.inria.atlanmod.neoemf.io.writer.AbstractXmiStreamWriter#onAttribute(ProxyAttribute, List)
     */
    @Override
    public void onAttribute(ProxyAttribute attribute, List<Object> values) throws IOException {
        //Log.info("onAttribute");

        ClassAdapter currentClassAdapter = classAdapters.getLast();
        AttributeAdapter adapter = currentClassAdapter.adapt(attribute);
        currentAttributeValues.add(adapter.withValues(values));
        currentAttributeFlags.set(adapter.index());
    }

    public void onEndAttributeList() throws IOException {
        this.writeAttributeValues();
        buffer.flip();
        channel.write(buffer.toByteBuffer());
        buffer.clear();
    }

    @Override
    public void onReference(ProxyReference reference, List<Id> values) throws IOException {
        references.onReference(reference, values);
    }


    @Override
    public void onEndElement() throws IOException {
        super.onEndElement();
        classAdapters.removeLast();
        elementIds.removeLast();
    }

    private void writeHeader() throws IOException {
        //Log.info("Writing header: {0}", header);

        BufferWrangler buffer = header.serialize();
        channel.write(buffer.toByteBuffer());
    }


    private void writeMetadata() throws IOException {
        metadata.setObjectCount(index.size());
        ByteBuffer buffer = metadata.serialize();
        channel.write(buffer);


        /* Debug
        File metadataFile = new File("/Users/sunye/.neoemf/metadata.bin");
        boolean append = false;
        FileChannel channel = new FileOutputStream(metadataFile, append).getChannel();
        buffer.flip();
        channel.write(buffer);
        channel.close();
         */
    }


    @NotNull
    public static List<InternalEObject> asInternaEObjects(EList<EObject> contents) {
        assert Objects.nonNull(contents);

        List<InternalEObject> contentsAsInternals = new ArrayList<>(contents.size());
        for (EObject each : contents) {
            contentsAsInternals.add((InternalEObject) each);
        }
        return contentsAsInternals;
    }

    @Deprecated
    public void flush() {
    }


    @Override
    public void onComplete() throws IOException {
        final long indexStartPosition = channel.position();
        this.writeIndex();

        final long referenceStartPosition = channel.position();
        this.writePendingReferences();

        final long metadataStartPosition = channel.position();
        this.writeMetadata();

        header.withAttributesLength(uint(indexStartPosition - Header.SIZE));
        header.withIndexLength(uint(referenceStartPosition - indexStartPosition));
        header.withReferencesLength(uint(metadataStartPosition - referenceStartPosition));
        header.withMetadataLength(uint(channel.position() - metadataStartPosition));

        channel.position(0);
        this.writeHeader(); // Update header

        Log.info("Header info: {0}", header);
        Log.info("Wrote {0} bytes (channel size)", channel.size());
    }

    private void writeAttributeValues() {
        ClassAdapter currentClassAdapter = classAdapters.getLast();

        // 1. Write Flags
        /*
        Log.info("Class adapter: {0}, Attributes: {1}, Element Flags: {2}",
                currentClassAdapter.name(), currentClassAdapter.expectedAttributeFlagsLength(), currentAttributeFlags);
         */
        int previous = buffer.position();
        buffer.put(currentAttributeFlags.toBytes());


        // 2. Write Set Attribute Values (Ordered by the adapter index)
        List<AttributeAdapter> attributes = currentAttributeValues.stream()
                .sorted(Comparator.comparingInt(AttributeAdapter::index))
                .collect(Collectors.toList());

        /*Log.info("Wrote {0} Attributes for Element {1}",
                attributes.size(), index.indexFor(elementIds.getLast()));*/

        for (AttributeAdapter each : attributes) {
            each.writeAttributeValueOn(buffer);
        }
    }


    private void writePendingReferences() throws IOException {
        BufferWrangler buffer = provider.allocateBuffer(references.estimatedSize());
        references.writeOn(buffer);

        buffer.flip();
        channel.write(buffer.toByteBuffer());
    }


    /**
     * Writes all Ids to file, ordered by their index value.
     */
    private void writeIndex() throws IOException {
        int requiredLength = index.size() * Long.BYTES * 2;
        BufferWrangler buffer = provider.allocateBuffer(requiredLength);
        buffer.put(index);
        buffer.flip();
        channel.write(buffer.toByteBuffer());
        Log.info("WRITE INDEX wrote {0} bytes", buffer.toByteBuffer().limit());

        // Debug (will be deleted)
        /*
        File indexFile = new File("/Users/sunye/.neoemf/index.bin");
        boolean append = false;
        FileChannel channel = new FileOutputStream(indexFile, append).getChannel();
        buffer.flip();
        channel.write(buffer.toByteBuffer());
        channel.close();

         */
    }

}
