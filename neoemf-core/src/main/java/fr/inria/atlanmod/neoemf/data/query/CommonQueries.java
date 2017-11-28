/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.query;

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.mapping.ClassAlreadyExistsException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.functions.Consumer;

/**
 * Static utility class that provides common queries, and functions used to build queries.
 */
@Static
@ParametersAreNonnullByDefault
public final class CommonQueries {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private CommonQueries() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Returns a {@link Consumer} that process an already existing meta-class.
     *
     * @param <T> the type of the consumed object
     *
     * @return a consumer
     *
     * @see fr.inria.atlanmod.neoemf.data.mapping.ClassMapper#metaClassFor(Id, ClassBean)
     */
    @Nonnull
    public static <T> Consumer<T> classAlreadyExists() {
        return t -> {
            throw new ClassAlreadyExistsException();
        };
    }
}
