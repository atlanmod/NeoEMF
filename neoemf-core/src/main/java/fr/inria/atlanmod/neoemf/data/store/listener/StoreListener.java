/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.listener;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object that listens to calls made on a {@link fr.inria.atlanmod.neoemf.data.Backend}.
 */
@ParametersAreNonnullByDefault
public interface StoreListener {

    /**
     * Handles the initialization of the back-end.
     * <p>
     * This method is called after the initialization of the back-end.
     */
    void onInitialize();

    /**
     * Handles a succeeded call on the back-end.
     *
     * @param callReport information about the call
     * @param <K>        the type of the key used during the call; nullable
     * @param <V>        the type of the value used during the call; nullable
     * @param <R>        the type of the result of the call; nullable
     */
    <K, V, R> void onSuccess(SuccessCallReport<K, V, R> callReport);

    /**
     * Handles a failed call on the back-end.
     *
     * @param callReport information about the call
     * @param <K>        the type of the key used during the call; nullable
     * @param <V>        the type of the value used during the call; nullable
     */
    <K, V> void onFailure(FailureCallReport<K, V> callReport);

    /**
     * Handles the closure of the back-end.
     * <p>
     * This method is called after closing the back-end.
     */
    void onClose();
}
