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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.commons.concurrent.MoreExecutors;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Backend} that provides a global behavior about the closure.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBackend implements Backend {

    /**
     * A set that holds all active {@link Backend} instances.
     */
    @Nonnull
    private static final Set<AbstractBackend> ACTIVE_BACKENDS = new HashSet<>();

    static {
        MoreExecutors.executeAtExit(() -> ACTIVE_BACKENDS.parallelStream().forEach(b -> b.close(false)));
    }

    /**
     * Whether this backend is closed.
     */
    private boolean isClosed;

    /**
     * Constructs a new {@code AbstractBackend}.
     */
    protected AbstractBackend() {
        if (isPersistent()) {
            ACTIVE_BACKENDS.add(this);
        }
    }

    @Override
    public final void close() {
        close(true);
    }

    /**
     * Cleanly closes this back-end, and releases any system resources associated with it. All modifications are saved
     * before closing.
     * <p>
     * If the back-end is already closed, then invoking this method has no effect.
     *
     * @param clean {@code true} if the registry must be cleaned after closure
     */
    private void close(boolean clean) {
        if (isClosed) {
            return;
        }

        try {
            save();
            safeClose();
        }
        catch (Exception ignored) {
        }
        finally {
            isClosed = true;

            if (clean && isPersistent()) {
                ACTIVE_BACKENDS.remove(this);
            }
        }
    }

    /**
     * Cleanly closes the database, and releases any system resources associated with it.
     *
     * @throws IOException if an I/O error occurs during the closure
     */
    protected abstract void safeClose() throws IOException;
}
