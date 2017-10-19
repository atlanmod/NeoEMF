/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.commons.log.Level;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.commons.primitive.Strings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link Store} wrapper that logs every calls in the {@link Log}.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public class LogStore extends AbstractListenerStore {

    /**
     * The default logging level.
     */
    @Nonnull
    public static final Level DEFAULT_LEVEL = Level.INFO;

    /**
     * The default {@link Level} for logging.
     */
    @Nonnull
    private final Level level;

    /**
     * Constructs a new {@code LogStore}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    public LogStore(Store store) {
        this(store, DEFAULT_LEVEL);
    }

    /**
     * Constructs a new {@code LogStore} with the given logging {@code level}.
     *
     * @param store the underlying store
     * @param level the logging level
     */
    @VisibleForReflection
    public LogStore(Store store, Level level) {
        super(store);
        this.level = level;
    }

    @Override
    protected <K, V, R> void onSuccess(String methodName, @Nullable K key, @Nullable V value, @Nullable R result) {
        Log.log(level, "[{0}] Called {1}()" + (nonNull(key) ? " for {2}" : Strings.EMPTY) + (nonNull(value) ? " with {3}" : Strings.EMPTY) + (nonNull(result) ? " = {4}" : Strings.EMPTY),
                backend(), methodName, key, value, result);
    }

    @Override
    protected <K, V> void onFailure(String methodName, @Nullable K key, @Nullable V value, Throwable e) {
        Log.log(level, "[{0}] Called {1}()" + (nonNull(key) ? " for {2}" : Strings.EMPTY) + (nonNull(value) ? " with {3}" : Strings.EMPTY) + " but failed with {4}",
                backend(), methodName, key, value, e.getClass().getSimpleName());
    }
}
