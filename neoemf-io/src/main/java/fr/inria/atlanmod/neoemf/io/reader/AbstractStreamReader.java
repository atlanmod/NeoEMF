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

import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.bean.BasicNamespace;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link StreamReader} that notifies {@link Handler} and provides overall behavior for the management of
 * namespaces.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractStreamReader extends AbstractReader<InputStream> implements StreamReader {

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
        notifyInitialize();
    }

    /**
     * Processes a {@link BasicNamespace} declaration.
     *
     * @param prefix the prefix
     * @param uri    the URI associated with the {@code prefix}
     *
     * @see BasicNamespace.Registry#register(String, String)
     */
    protected final void readNamespace(String prefix, String uri) {
        BasicNamespace.Registry.getInstance().register(prefix, uri);
    }

    /**
     * Processes the end of the current document.
     */
    protected final void readEndDocument() {
        notifyComplete();
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
    public final void read(InputStream source) throws IOException {
        checkNotNull(source);

        try {
            run(new BufferedInputStream(source));
        }
        catch (Exception e) {
            throw new IOException(e);
        }
        finally {
            BasicNamespace.Registry.getInstance().clean();
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
}
