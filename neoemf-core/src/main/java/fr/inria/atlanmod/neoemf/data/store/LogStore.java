/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.commons.log.Level;
import fr.inria.atlanmod.commons.log.Log;

import javax.annotation.Nonnull;
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
    protected void onSuccess(CallInfo info) {
        log(info, true);
    }

    @Override
    protected void onFailure(CallInfo info) {
        log(info, false);
    }

    /**
     * Logs a call.
     *
     * @param info    information about the call
     * @param success {@code true} if the call succeeded, {@code false} otherwise
     */
    protected void log(CallInfo info, boolean success) {
        StringBuilder sb = new StringBuilder();

        // Append the name of the concerned backend
        sb.append("[").append(backend()).append("]");

        // Append the name of the called method
        sb.append(" Called ").append(info.method()).append("()");

        // Append the key used during the call
        if (nonNull(info.key())) {
            sb.append(" for ").append(info.key());
        }

        // Append the value used during the call
        if (nonNull(info.value())) {
            sb.append(" with ").append(info.value());
        }

        // Append the result of the call, or the exception thrown
        if (success && nonNull(info.result())) {
            sb.append(" = ").append(info.result());
        }
        else if (!success) {
            sb.append(" but failed");

            if (nonNull(info.thrownException())) {
                sb.append(" with ").append(info.thrownException().getClass().getName());
            }
        }

        Log.log(level, sb.toString());
    }
}
