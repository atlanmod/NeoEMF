/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.commons.annotation.VisibleForTesting;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.processor.CounterProcessor;
import fr.inria.atlanmod.neoemf.io.processor.LoggingProcessor;
import fr.inria.atlanmod.neoemf.io.processor.NoopProcessor;
import fr.inria.atlanmod.neoemf.io.processor.Processor;
import fr.inria.atlanmod.neoemf.io.processor.TimerProcessor;
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

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * The builder that creates {@link Reader} and {@link Writer} instances.
 */
@ParametersAreNonnullByDefault
public final class Migrator<T> {

    /**
     * The 4-bit header of a ZIP file.
     */
    private static final int ZIP_HEADER = 0x04034b50; // ZipConstants#LOCSIG

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
    private final Set<Writer> writers = new HashSet<>();

    /**
     * A set that holds all {@link OutputStream}s to close after the migration.
     */
    @Nonnull
    private final Set<OutputStream> streamsToClose = new HashSet<>();

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
        checkArgument(file.exists(), "file does not exists : %s", file.getAbsolutePath());
        checkArgument(!file.isDirectory(), "file must not be a directory");
        checkArgument(file.canRead(), "file cannot be read");

        return fromXmi(new FileInputStream(file));
    }

    /**
     * Creates a {@code Migrator} that reads XMI content from an {@link InputStream}. The content can be compressed.
     *
     * @param stream the stream of the XMI content to read
     *
     * @return a new migrator
     *
     * @throws IOException if an I/O error occurs during the creation
     */
    @Nonnull
    public static Migrator<InputStream> fromXmi(InputStream stream) throws IOException {
        return new Migrator<>(XmiStreamReader.class, adaptStream(stream));
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
     * Adapts the specified {@code stream} to read its content correctly.
     * <p>
     * If the content is compressed, then it returns a new {@link InputStream} on top of the content, located by a
     * {@link ZipInputStream}.
     *
     * @param stream the base stream
     *
     * @return the adapted {@code stream}
     */
    @Nonnull
    private static InputStream adaptStream(@WillNotClose InputStream stream) throws IOException {
        final PushbackInputStream pbis = new PushbackInputStream(stream, 4);

        // Read file signature
        final byte[] sign = new byte[4];
        pbis.unread(sign, 0, pbis.read(sign));

        // Check the ZIP signature (== 0x04034b50 in little endian order)
        final int header = ByteBuffer.wrap(sign)
                .order(ByteOrder.LITTLE_ENDIAN)
                .getInt();

        if (header == ZIP_HEADER) {
            ZipInputStream zis = new ZipInputStream(pbis);

            ZipEntry e;
            while (nonNull(e = zis.getNextEntry())) {
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
     * Specifies the {@code writer} where to send the reading events.
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
     * Specifies the {@code mapper} where to write the data.
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
     * Specifies the XMI {@code file} where to write the data.
     *
     * @param file the file where to write
     *
     * @return this migrator (for chaining)
     *
     * @throws IOException if an I/O error occurs during the creation
     * @see #toXmi(File, boolean)
     */
    @Nonnull
    public Migrator<T> toXmi(File file) throws IOException {
        return toXmi(file, false);
    }

    /**
     * Specifies the XMI {@code file} where to write the data.
     *
     * @param file           the file where to write
     * @param useCompression {@code true} if the XMI file must be compressed
     *
     * @return this migrator (for chaining)
     *
     * @throws IOException if an I/O error occurs during the creation
     */
    @Nonnull
    public Migrator<T> toXmi(File file, boolean useCompression) throws IOException {
        OutputStream out = new FileOutputStream(file);
        streamsToClose.add(out);

        if (useCompression) {
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(file));
            zipOut.putNextEntry(new ZipEntry(ZXMI_CONTENT));
            streamsToClose.add(zipOut);

            out = zipOut;
        }

        return toXmi(out);
    }

    //endregion

    //region Processors

    /**
     * Specifies the {@code stream} where to write the data.
     *
     * @param stream the file where to write
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> toXmi(@WillNotClose OutputStream stream) throws IOException {
        return to(new XmiStreamWriter(stream));
    }

    /**
     * Adds a pre/post-processing feature.
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
     * Adds the {@code logging} feature.
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> withLogger() {
        return with(LoggingProcessor.class);
    }

    /**
     * Adds the {@code event-counting} feature.
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> withCounter() {
        return with(CounterProcessor.class);
    }

    //endregion

    /**
     * Adds the {@code timing} feature.
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> withTimer() {
        return with(TimerProcessor.class);
    }

    /**
     * Runs the migration.
     */
    // TODO Handle constructor parameters
    @SuppressWarnings("JavaReflectionMemberAccess")
    public void migrate() throws IOException {
        checkNotNull(writers, "writers");

        Processor processor = new NoopProcessor(writers.toArray(new Writer[writers.size()]));

        Reader<T> reader;

        try {
            for (Class<? extends Processor> c : processorClasses) {
                processor = c.getConstructor(Handler.class).newInstance(processor);
            }

            reader = readerClass.getConstructor(Handler.class).newInstance(processor);
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw Throwables.wrap(e, IllegalStateException.class); // Should never happen
        }

        reader.read(source);
        closeAll();
    }

    /**
     * Closes all internal streams.
     */
    private void closeAll() {
        for (OutputStream out : streamsToClose) {
            try {
                if (ZipOutputStream.class.isInstance(out)) {
                    ZipOutputStream zos = ZipOutputStream.class.cast(out);
                    zos.closeEntry();
                }

                out.close();
            }
            catch (IOException e) {
                Log.warn(e);
            }
        }
    }
}