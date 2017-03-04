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

import fr.inria.atlanmod.neoemf.data.mapper.PersistenceMapper;

import java.io.Closeable;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An adapter on top of a database that provides specific methods for communicating with the database that it uses.
 * Each {@code PersistenceBackend} manage one single instance of a database.
 * <p>
 * It does not provide model-level translation; these functions are handled by
 * {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore}s.
 *
 * @see fr.inria.atlanmod.neoemf.data.store.DirectWriteStore
 */
@ParametersAreNonnullByDefault
public interface PersistenceBackend extends PersistenceMapper, Closeable {

    @Override
    void save();

    /**
     * Cleanly stops the underlying database and releases any system resources associated with it. If the database is
     * already closed then invoking this method has no effect.
     * <p>
     * All modifications are saved before closing.
     *
     * @see #isClosed()
     */
    @Override
    void close();

    /**
     * Returns whether the underlying database is closed.
     *
     * @return {@code true} if the database is closed, otherwise {@code false}
     *
     * @see #close()
     */
    boolean isClosed();

    /**
     * Returns whether this {@code PersistenceBackend} is distributed.
     *
     * @return {@code true} if the back-end is distributed, {@code false} otherwise.
     */
    boolean isDistributed();

    /**
     * Copies all the contents from this {@code PersistenceBackend} to the {@code target}.
     *
     * @param target the {@code PersistenceBackend} to copy the database contents to
     */
    void copyTo(PersistenceBackend target);
}

