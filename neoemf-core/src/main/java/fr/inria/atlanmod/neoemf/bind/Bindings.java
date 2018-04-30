/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.bind;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.reflect.MoreReflection;
import fr.inria.atlanmod.commons.reflect.ReflectionException;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.util.UriFactory;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A static utility class for binding.
 */
@Static
@ParametersAreNonnullByDefault
public final class Bindings {

    private Bindings() {
        throw Throwables.notInstantiableClass(getClass());
    }

    /**
     * Retrieves the URI scheme for the speficied {@code type}.
     * <p>
     * The {@code type} <b>must</b> be annotated with {@link FactoryBinding}, or implements {@link BackendFactory}.
     *
     * @param type the type
     *
     * @return the URI scheme
     *
     * @throws BindingException if no scheme is defined for this {@code type}
     */
    @Nonnull
    public static String schemeOf(Class<?> type) {
        return UriFactory.createScheme(nameOf(type));
    }

    /**
     * Retrieves the URI scheme for the specified {@code factory}.
     *
     * @param factory the factory
     *
     * @return the URI scheme
     *
     * @throws BindingException if no scheme is defined for this {@code type}
     */
    @Nonnull
    public static String schemeOf(BackendFactory factory) {
        return UriFactory.createScheme(factory.name());
    }

    /**
     * Retrieves the name for the specified {@code type}.
     * <p>
     * The {@code type} <b>must</b> be annotated with {@link FactoryBinding}, or implements {@link BackendFactory}.
     *
     * @param type the type
     *
     * @return the name
     *
     * @throws BindingException if no name is defined for this {@code type}
     */
    @Nonnull
    public static String nameOf(Class<?> type) {
        return findFactory(type).name();
    }

    /**
     * Retrieves the variant for the specified {@code type}.
     * <p>
     * The {@code type} must be annotated with {@link FactoryBinding}.
     *
     * @param type the type
     *
     * @return the variant
     */
    @Nonnull
    public static String variantOf(Class<?> type) {
        checkNotNull(type, "type");

        return Optional.of(type)
                .filter(t -> t.isAnnotationPresent(FactoryBinding.class))
                .map(t -> t.getAnnotation(FactoryBinding.class))
                .map(FactoryBinding::variant)
                .orElseThrow(() -> new BindingException(
                        String.format("%s is not annotated with %s: Unable to retrieve the variant",
                                type.getName(), FactoryBinding.class.getName())));
    }

    /**
     * Retrieves the {@link BackendFactory} associated to the {@code type}.
     * <p>
     * The {@code type} <b>must</b> be annotated with {@link FactoryBinding}, or implements {@link BackendFactory}.
     *
     * @param type the type of the instance to look for
     *
     * @return the instance of the {@link BackendFactory}
     *
     * @throws BindingException    if no instance of {@link BackendFactory} is found for the {@code type}
     * @throws ReflectionException if an error occurs during the instantiation
     */
    @Nonnull
    private static BackendFactory findFactory(Class<?> type) {
        return MoreReflection.newInstance(findFactoryType(type));
    }

    /**
     * Retrieves the type of the {@link BackendFactory} associated to the {@code type}.
     * <p>
     * The {@code type} <b>must</b> be annotated with {@link FactoryBinding}, or implements {@link BackendFactory}.
     *
     * @param type the type
     *
     * @return the type of the {@link BackendFactory}
     *
     * @throws BindingException if no instance of {@link BackendFactory} is found for the {@code type}
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    private static Class<? extends BackendFactory> findFactoryType(Class<?> type) {
        checkNotNull(type, "type");

        if (BackendFactory.class.isAssignableFrom(type)) {
            return (Class<? extends BackendFactory>) type;
        }
        else if (type.isAnnotationPresent(FactoryBinding.class)) {
            return type.getAnnotation(FactoryBinding.class).factory();
        }

        throw new BindingException(String.format("%s is not annotated with %s: Unable to retrieve the associated factory", type.getName(), FactoryBinding.class.getName()));
    }
}
