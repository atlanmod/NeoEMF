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

import java.io.Closeable;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 */
@ParametersAreNonnullByDefault
public interface DataManager extends Closeable {

    /**
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
