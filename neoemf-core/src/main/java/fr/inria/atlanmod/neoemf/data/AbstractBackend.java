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

import fr.inria.atlanmod.commons.Copier;
import fr.inria.atlanmod.commons.concurrent.MoreThreads;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.store.Store;

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
        MoreThreads.executeAtExit(() -> ACTIVE_BACKENDS.parallelStream().forEach(b -> b.close(false)));
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
            innerClose();
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
    protected abstract void innerClose() throws IOException;

    @Override
    @SuppressWarnings("unchecked")
    public void copyTo(DataMapper target) {
        if (isDistributed()) {
            Log.warn("Copy of a distributed back-end may lead to unexpected errors");
        }

        if (getClass() == target.getClass()) {
            innerCopyTo(target);
        }
        else if (Store.class.isInstance(target) && getClass() == Store.class.cast(target).backend().getClass()) {
            innerCopyTo(Store.class.cast(target).backend());
        }
        else {
            defaultCopyTo(target);
        }
    }

    @SuppressWarnings("unchecked")
    private void defaultCopyTo(DataMapper target) {
        Copier<DataMapper> copier;

        try {
            final String directCopierTypeName = "fr.inria.atlanmod.neoemf.io.DirectDataCopier";
            final Class<Copier<DataMapper>> directCopierType = (Class<Copier<DataMapper>>) Class.forName(directCopierTypeName);

            copier = directCopierType.newInstance();
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            Log.warn("The direct copy is not supported. Make sure the `neoemf-io` module is in the classpath", e);

            copier = new DataCopier();
        }

        copier.copy(this, target);
    }

    /**
     * Copies the content of this back-end to the {@code target}, using a specific implementation when the {@code
     * target} is an instance of this back-end.
     *
     * @param target the back-end where to store the copied content
     *
     * @throws UnsupportedOperationException if this back-end does not support the specific copy
     */
    protected void innerCopyTo(DataMapper target) {
        throw new UnsupportedOperationException(String.format("%s does not support specific copy", getClass().getName()));
    }

    /**
     * The default implementation of a {@link Copier} of {@link DataMapper} instances.
     */
    @ParametersAreNonnullByDefault
    private static class DataCopier implements Copier<DataMapper> {

        @Override
        public void copy(DataMapper source, DataMapper target) {
            // TODO Implements this method
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }
}
