/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link IdConverter}.
 *
 * @param <T> the type of the result of the converter
 */
@ParametersAreNonnullByDefault
public abstract class AbstractIdConverter<T> implements IdConverter<T> {

    /**
     * The current provider of {@link Id} instances.
     */
    @Nonnull
    protected final IdProvider provider = Id.getProvider();
}
