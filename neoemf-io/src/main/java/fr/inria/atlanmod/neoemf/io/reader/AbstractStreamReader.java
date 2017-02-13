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

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.io.AbstractNotifier;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An abstract {@link StreamReader} that notifies {@link Handler} and provides overall behavior for the management of
 * namespaces.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractStreamReader extends AbstractNotifier<Handler> implements StreamReader {

    /**
     * The timer to log reading progress.
     */
    private Timer progressTimer;

    /**
     * Constructs a new {@code AbstractStreamReader} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public AbstractStreamReader(Handler handler) {
        super(handler);
    }

    /**
     * Processes the start of the document.
     */
    protected final void readStartDocument() {
        notifyStartDocument();
        progress(0);
    }

    /**
     * Processes a {@link Namespace} declaration.
     *
     * @param prefix the prefix
     * @param uri    the URI associated with the {@code prefix}
     *
     * @see Namespace.Registry#register(String, String)
     */
    protected final void readNamespace(String prefix, String uri) {
        Namespace.Registry.getInstance().register(prefix, uri);
    }

    /**
     * Processes the end of the current document.
     */
    protected final void readEndDocument() {
        progress(100);
        notifyEndDocument();
    }

    /**
     * Processes characters.
     *
     * @param characters a set of characters, as {@link String}
     */
    protected void readCharacters(String characters) {
        notifyCharacters(characters);
    }

    @Override
    public final void read(InputStream stream) throws IOException {
        checkNotNull(stream);

        progressTimer = new Timer(true);
        progressTimer.schedule(new ProgressTimer(stream), 10000, 30000);

        try {
            run(stream);
        }
        catch (Exception e) {
            throw new IOException(e);
        }
        finally {
            progressTimer.cancel();
        }
    }

    /**
     * Runs the reading on the {@code stream}.
     *
     * @param stream the stream to read
     *
     * @throws Exception if an error occurred during the I/O process
     */
    public abstract void run(InputStream stream) throws Exception;

    /**
     * Logs the progress of the current reading.
     *
     * @param percent the percentage of data read on the total size of the data
     */
    protected void progress(double percent) {
        NeoLogger.debug("Progress : {0}", String.format("%5s", String.format("%,.0f %%", percent)));

        if (percent >= 100) {
            progressTimer.cancel();
        }
    }

    /**
     * A {@link TimerTask} that logs progress.
     */
    private class ProgressTimer extends TimerTask {

        /**
         * The stream to watch.
         */
        private final InputStream stream;

        /**
         * The total size of the stream.
         */
        private final long total;

        /**
         * Constructs a new {@code ProgressTimer}.
         *
         * @param stream the stream to watch
         *
         * @throws IOException if an I/O error occurs
         */
        private ProgressTimer(InputStream stream) throws IOException {
            this.stream = stream;
            this.total = stream.available();
        }

        @Override
        public void run() {
            try {
                progress((double) (total - stream.available()) / (double) total * 100d);
            }
            catch (Exception ignore) {
            }
        }
    }
}
