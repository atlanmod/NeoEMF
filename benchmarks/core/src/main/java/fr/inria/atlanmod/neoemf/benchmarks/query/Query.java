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

package fr.inria.atlanmod.neoemf.benchmarks.query;

import fr.inria.atlanmod.common.log.Log;

import javax.annotation.Nullable;

import static java.util.Objects.nonNull;

/**
 * @param <V> the result type of method {@code call}
 */
@FunctionalInterface
public interface Query<V> {

    @Nullable
    V call();

    @Nullable
    default V callWithResult() {
        V result;

        Log.info("Start query");

        result = call();

        Log.info("End query");

        if (nonNull(result) && Number.class.isInstance(result)) {
            Log.info("Query returns: {0}", result);
        }

        return result;
    }
}
