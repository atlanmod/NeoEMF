/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.bind;

import fr.inria.atlanmod.commons.reflect.ReflectionException;
import fr.inria.atlanmod.neoemf.data.BackendFactory;

import java.util.Set;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An engine able to retrieve bound objects.
 */
@ParametersAreNonnullByDefault
public interface BindingEngine {

    /**
     * Retrieves all {@link BackendFactory} instances that are visible in the classpath.
     *
     * @return a set of initialized factories
     *
     * @throws ReflectionException if an error occurs during the instantiation of any factory
     */
    @Nonnull
    Set<BackendFactory> findFactories();

    /**
     * Retrieves the instance of the {@code type} that is bound to a {@link BackendFactory} with the given {@code value},
     * by using the speficied {@code valueMapping}.
     * <p>
     * The {@code type} <b>must</b> be annotated with {@link FactoryBinding}.
     *
     * @param type         the type of the instance to look for
     * @param valueMapping the mapping function to retrieve the value from the factory bound by the annotation
     * @param value        the value to look for
     * @param variant      the variant to look for
     * @param <T>          the type of the instance
     *
     * @return a new instance of the {@code type}
     *
     * @throws BindingException    if no instance of {@code type} is found for the {@code value} by using the {@code valueMapping}
     * @throws ReflectionException if an error occurs during the instantiation
     */
    @Nonnull
    <T> T find(Class<? super T> type, Function<Class<? extends BackendFactory>, String> valueMapping, String value, @Nullable String variant);
}
