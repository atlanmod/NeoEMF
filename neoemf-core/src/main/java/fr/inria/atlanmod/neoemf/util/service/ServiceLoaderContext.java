/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util.service;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.commons.collect.MoreIterables;

import java.util.ServiceLoader;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ServiceContext} able to retrieve registered services from a {@link ServiceLoader}.
 */
@VisibleForReflection
@ParametersAreNonnullByDefault
public class ServiceLoaderContext implements ServiceContext {

    @Nonnull
    @Override
    public <T> Stream<T> getServices(Class<T> type) {
        return MoreIterables.parallelStream(ServiceLoader.load(type));
    }
}
