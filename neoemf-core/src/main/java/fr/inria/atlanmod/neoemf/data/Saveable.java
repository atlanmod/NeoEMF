/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import java.io.Closeable;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object that can save its current state.
 */
@ParametersAreNonnullByDefault
public interface Saveable extends Closeable {

    /**
     * {@inheritDoc}
     * <p>
     * Cleanly closes this manager, clear all data in-memory and releases any system resources associated with it. All
     * modifications are saved before closing.
     * <p>
     * If the manager is already closed, then invoking this method has no effect.
     */
    @Override
    void close();

    /**
     * Saves all changes made on this manager since the last call.
     */
    void save();
}
