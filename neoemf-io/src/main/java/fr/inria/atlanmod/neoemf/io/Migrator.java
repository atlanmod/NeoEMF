/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.common.annotation.Experimental;
import fr.inria.atlanmod.common.annotation.VisibleForTesting;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.processor.CounterProcessor;
import fr.inria.atlanmod.neoemf.io.processor.DirectWriteProcessor;
import fr.inria.atlanmod.neoemf.io.processor.LoggingProcessor;
import fr.inria.atlanmod.neoemf.io.processor.Processor;
import fr.inria.atlanmod.neoemf.io.processor.ProgressProcessor;
import fr.inria.atlanmod.neoemf.io.reader.DefaultMapperReader;
import fr.inria.atlanmod.neoemf.io.reader.Reader;
import fr.inria.atlanmod.neoemf.io.reader.XmiStreamReader;
import fr.inria.atlanmod.neoemf.io.writer.DefaultMapperWriter;
import fr.inria.atlanmod.neoemf.io.writer.Writer;
import fr.inria.atlanmod.neoemf.io.writer.XmiStreamWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.WillNotClose;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * The builder that creates {@link Reader} and {@link Writer} instances.
 */
@ParametersAreNonnullByDefault
public final class Migrator<T> {

    /**
     * The 4-bit header of a compressed {@code xmi} file.
     */
    private static final int ZXMI_HEADER = 0x04034b50;

    /**
     * The name of the only entry of a compressed {@code xmi} file.
     */
    private static final String ZXMI_CONTENT = "ResourceContents";

    /**
     * The class of the {@link Reader} to use.
     */
    @Nonnull
    private final Class<? extends Reader<T>> readerClass;

    /**
     * The source to read.
     */
    @Nonnull
    private final T source;

    /**
     * The deque that holds the classes of all {@link Processor}s to use.
     * <p>
     * Essential processors must be placed at the end of this queue, while the rest must be placed at the beginning.
     */
    @Nonnull
    private final Set<Class<? extends Processor>> processorClasses = new HashSet<>();

    /**
     * The set that holds all {@link Writer} to use.
     */
    @Nonnull
    private Set<Writer> writers = new HashSet<>();

    /**
     * Constructs a new {@code Migrator} with the given arguments.
     *
     * @param readerClass the class of the {@link Reader} to use
     * @param source      the source to read
     */
    private Migrator(Class<? extends Reader<T>> readerClass, T source) {
        this.readerClass = readerClass;
        this.source = source;
    }

    //region Readers

    /**
     * Creates a {@code Migrator} that reads a XMI file. The file can be compressed.
     *
     * @param file the XMI file to read
     *
     * @return a new migrator
     *
     * @throws IOException if an I/O error occurs during the creation
     */
    @Nonnull
    public static Migrator<InputStream> fromXmi(File file) throws IOException {
        checkArgument(file.exists());
        checkArgument(!file.isDirectory());
        checkArgument(file.canRead());

        return fromXmi(new FileInputStream(file));
    }

    /**
     * Creates a {@code Migrator} that reads XMI content from an {@link InputStream}.
     *
     * @param stream the stream of the XMI content to read
     *
     * @return a new migrator
     *
     * @throws IOException if an I/O error occurs during the creation
     */
    @Nonnull
    public static Migrator<InputStream> fromXmi(InputStream stream) throws IOException {
        return new Migrator<>(XmiStreamReader.class, getInputStream(stream));
    }

    /**
     * Creates a {@code Migrator} that reads a {@link DataMapper}.
     *
     * @param mapper the mapper to read
     *
     * @return a new migrator
     */
    @Nonnull
    public static Migrator<DataMapper> fromMapper(DataMapper mapper) {
        return new Migrator<>(DefaultMapperReader.class, mapper);
    }

    /**
     * TODO
     *
     * @param stream the base stream
     *
     * @return the adapted {@code stream}
     */
    @WillNotClose
    private static InputStream getInputStream(InputStream stream) throws IOException {
        final PushbackInputStream pbis = new PushbackInputStream(stream, 4);

        // Read file signature
        final byte[] sign = new byte[4];
        pbis.unread(sign, 0, pbis.read(sign));

        // Check the ZIP signature (== 0x04034b50 in little endian order)
        final int header = ByteBuffer.wrap(sign)
                .order(ByteOrder.LITTLE_ENDIAN)
                .getInt();

        if (header == ZXMI_HEADER) {
            ZipInputStream zis = new ZipInputStream(pbis);

            for (ZipEntry e; nonNull(e = zis.getNextEntry()); ) {
                if (Objects.equals(e.getName(), ZXMI_CONTENT)) {
                    return zis;
                }
            }

            throw new FileNotFoundException(String.format("Malformed ZXMI file: missing '%s' entry", ZXMI_CONTENT));
        }
        else {
            return pbis;
        }
    }

    //endregion

    //region Writers

    /**
     * TODO
     *
     * @param writer the writer where to write
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    @VisibleForTesting
    public Migrator<T> to(Writer writer) {
        writers.add(writer);
        return this;
    }

    /**
     * TODO
     *
     * @param mapper the mapper where to write
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> toMapper(DataMapper mapper) {
        return to(new DefaultMapperWriter(mapper));
    }

    /**
     * TODO
     *
     * @param file the file where to write
     *
     * @return this migrator (for chaining)
     *
     * @throws IOException if an I/O error occurs during the creation
     */
    @Nonnull
    @Experimental
    public Migrator<T> toZXmi(File file) throws IOException {
        ZipOutputStream output = new ZipOutputStream(new FileOutputStream(file));
        output.putNextEntry(new ZipEntry(ZXMI_CONTENT));
        return toXmi(output);
    }

    /**
     * TODO
     *
     * @param file the file where to write
     *
     * @return this migrator (for chaining)
     *
     * @throws IOException if an I/O error occurs during the creation
     */
    @Nonnull
    @Experimental
    public Migrator<T> toXmi(File file) throws IOException {
        return toXmi(new FileOutputStream(file));
    }

    //endregion

    //region Processors

    /**
     * TODO
     *
     * @param stream the file where to write
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    @Experimental
    public Migrator<T> toXmi(OutputStream stream) {
        return to(new XmiStreamWriter(stream));
    }

    /**
     * TODO
     *
     * @param processor the processor to add
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    private Migrator<T> with(Class<? extends Processor> processor) {
        processorClasses.add(processor);
        return this;
    }

    /**
     * TODO
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> withLogger() {
        return with(LoggingProcessor.class);
    }

    /**
     * TODO
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> withCounter() {
        return with(CounterProcessor.class);
    }

    //endregion

    /**
     * TODO
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> withTimer() {
        return with(ProgressProcessor.class);
    }

    /**
     * Runs the migration.
     */
    public void migrate() throws IOException {
        checkNotNull(writers);

        Processor processor = new DirectWriteProcessor(writers.toArray(new Writer[writers.size()]));

        Reader<T> reader;

        try {
            for (Class<? extends Processor> c : processorClasses) {
                processor = c.getConstructor(Processor.class).newInstance(processor);
            }

            reader = readerClass.getConstructor(Handler.class).newInstance(processor);
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        reader.read(source);
    }
}