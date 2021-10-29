/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.listener.CountingEventListener;
import fr.inria.atlanmod.neoemf.io.listener.EventListener;
import fr.inria.atlanmod.neoemf.io.listener.LoggingEventListener;
import fr.inria.atlanmod.neoemf.io.listener.ProgressEventListener;
import fr.inria.atlanmod.neoemf.io.listener.TimerEventListener;
import fr.inria.atlanmod.neoemf.io.processor.NoopProcessor;
import fr.inria.atlanmod.neoemf.io.processor.Processor;
import fr.inria.atlanmod.neoemf.io.reader.AbstractReader;
import fr.inria.atlanmod.neoemf.io.reader.DefaultMapperReader;
import fr.inria.atlanmod.neoemf.io.reader.Reader;
import fr.inria.atlanmod.neoemf.io.reader.xmi.XmiStreamReader;
import fr.inria.atlanmod.neoemf.io.writer.DefaultMapperWriter;
import fr.inria.atlanmod.neoemf.io.writer.Writer;
import fr.inria.atlanmod.neoemf.io.writer.json.JsonStreamWriter;
import fr.inria.atlanmod.neoemf.io.writer.xmi.XmiStreamWriter;

import org.atlanmod.commons.annotation.VisibleForTesting;
import org.atlanmod.commons.log.Level;
import org.atlanmod.commons.log.Log;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.WillNotClose;

import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkArgument;
import static org.atlanmod.commons.Preconditions.checkNotNull;
import static org.atlanmod.commons.Preconditions.checkState;

/**
 * The builder that creates {@link fr.inria.atlanmod.neoemf.io.reader.Reader} and {@link
 * fr.inria.atlanmod.neoemf.io.writer.Writer} instances.
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
    private final AbstractReader<T> reader;

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
    private final Set<EventListener> listeners = new HashSet<>();

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
     * @param reader the reader to use
     * @param source the source to read
     */
    private Migrator(AbstractReader<T> reader, T source) {
        this.reader = reader;
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
        return new Migrator<>(new XmiStreamReader(), uncompressIfNecessary(stream));
    }

    /**
     * Creates a {@code Migrator} that reads a {@link fr.inria.atlanmod.neoemf.data.mapping.DataMapper}.
     *
     * @param mapper the mapper to read
     *
     * @return a new migrator
     */
    @Nonnull
    public static Migrator<DataMapper> fromMapper(DataMapper mapper) {
        return new Migrator<>(new DefaultMapperReader(), mapper);
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
    private static InputStream uncompressIfNecessary(@WillNotClose InputStream stream) throws IOException {
        final PushbackInputStream pbis = new PushbackInputStream(stream, 4);

        // Read stream signature
        final byte[] sign = new byte[4];
        pbis.unread(sign, 0, pbis.read(sign));

        // Check the ZIP signature (== 0x04034b50 in little endian order)
        final int header = ByteBuffer.wrap(sign).order(ByteOrder.LITTLE_ENDIAN).getInt();

        if (header == ZIP_HEADER) {
            ZipInputStream zis = new ZipInputStream(pbis);

            ZipEntry e;
            while (nonNull(e = zis.getNextEntry())) {
                if (Objects.equals(e.getName(), ZXMI_CONTENT)) {
                    return zis;
                }
            }

            throw new FileNotFoundException(String.format("Malformed compressed file: missing '%s' entry", ZXMI_CONTENT));
        }

        return pbis;
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
    Migrator<T> to(Writer writer) {
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

    /**
     * Specifies the {@code stream} where to write the data.
     *
     * @param stream the file where to write
     *
     * @return this migrator (for chaining)
     *
     * @throws IOException if an I/O error occurs when writing
     */
    @Nonnull
    public Migrator<T> toXmi(@WillNotClose OutputStream stream) throws IOException {
        return to(new XmiStreamWriter(stream));
    }

    /**
     * Specifies the {@code stream} where to write the data.
     *
     * @param stream the file where to write
     *
     * @return this migrator (for chaining)
     *
     * @throws IOException if an I/O error occurs when writing
     */
    @Nonnull
    public Migrator<T> toJson(@WillNotClose OutputStream stream) throws  IOException {
        return to(new JsonStreamWriter(stream));
    }

    //endregion

    //region Listeners

    /**
     * Adds a pre/post-processing feature.
     *
     * @param listener the listener to add
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> with(EventListener listener) {
        listeners.add(listener);
        return this;
    }

    /**
     * Logs each event when they occur.
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> withLogger() {
        return with(new LoggingEventListener());
    }

    /**
     * Logs each event when they occur.
     *
     * @param level the logging level to use
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> withLogger(Level level) {
        return with(new LoggingEventListener(level));
    }

    /**
     * Counts the number of processed events (elements, attributes, references).
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> withCounter() {
        return with(new CountingEventListener());
    }

    /**
     * Displays the amount of time spent during the migration.
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> withTimer() {
        return with(new TimerEventListener());
    }

    /**
     * Displays the progress of the migration at regular intervals.
     * <p>
     * <b>WARNING:</b> This feature can only be used when reading a file or a stream.
     *
     * @return this migrator (for chaining)
     */
    @Nonnull
    public Migrator<T> withProgress() {
        checkState(source instanceof InputStream, "Progress feature can only be used when reading a file or stream");
        return with(new ProgressEventListener((InputStream) source));
    }

    //endregion

    /**
     * Runs the migration.
     *
     * @throws IOException if an I/O error occurs when migrating
     */
    public void migrate() throws IOException {
        checkNotNull(writers, "writers");

        try {
            // Bind handlers and notifiers
            Collection<Handler> handlers = new ArrayList<>(listeners.size() + writers.size());
            handlers.addAll(listeners);
            handlers.addAll(writers);
            reader.addNext(new NoopProcessor(handlers));

            reader.read(source);
        }
        finally {
            closeAll();
        }
    }

    /**
     * Closes all internal streams.
     */
    private void closeAll() {
        for (Closeable closeable : streamsToClose) {
            try {
                if (closeable instanceof ZipOutputStream) {
                    final ZipOutputStream zipStream = (ZipOutputStream) closeable;
                    zipStream.closeEntry();
                }

                closeable.close();
            }
            catch (IOException e) {
                Log.warn(e);
            }
        }
    }
}
