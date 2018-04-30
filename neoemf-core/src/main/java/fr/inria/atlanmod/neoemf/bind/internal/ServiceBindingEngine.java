/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.bind.internal;

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.bind.BindingEngine;
import fr.inria.atlanmod.neoemf.bind.BindingException;
import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.data.BackendFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link BindingEngine} that uses a {@link ServiceLoader} to retrieve bound objects.
 *
 * <b>WARNING:</b> This implementation is not compatible with OSGi yet.
 */
@ParametersAreNonnullByDefault
public class ServiceBindingEngine implements BindingEngine {

    public ServiceBindingEngine() {
        Log.info("Using a ServiceBindingEngine");
    }

    @Nonnull
    @Override
    public Set<BackendFactory> findFactories() {
        return MoreIterables.stream(ServiceLoader.load(BackendFactory.class)).collect(Collectors.toSet());
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public <T> T find(Class<? super T> type, Function<Class<? extends BackendFactory>, String> valueMapping, String value, @Nullable String variant) {
        final String variantOrDefault = Optional.ofNullable(variant).orElse(FactoryBinding.DEFAULT_VARIANT);

        // Find all objects that match the value and variant
        List<? super T> relevantObjects = MoreIterables.stream(ServiceLoader.load(type))
                .filter(t -> {
                    final FactoryBinding a = t.getClass().getDeclaredAnnotation(FactoryBinding.class);

                    return nonNull(a)
                            && Objects.equals(value, valueMapping.apply(a.factory()))
                            && Objects.equals(variantOrDefault, a.variant());
                })
                .collect(Collectors.toList());

        // Ensure that only one type is relevant
        if (relevantObjects.isEmpty()) {
            throw new BindingException(String.format("Unable to find a %s instance for value '%s' and variant '%s'; No relevant type found", type.getName(), value, variantOrDefault));
        }
        else if (relevantObjects.size() > 1) {
            throw new BindingException(String.format("Unable to find a %s instance for value '%s' and variant '%s'; Several relevant types found : %s", type.getName(), value, variantOrDefault, relevantObjects));
        }

        return (T) relevantObjects.get(0);
    }
}
