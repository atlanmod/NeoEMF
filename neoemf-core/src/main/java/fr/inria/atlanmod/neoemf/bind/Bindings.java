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
import fr.inria.atlanmod.neoemf.util.service.ServiceResolver;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
    public static Optional<String> variantOf(Class<?> type) {
        checkNotNull(type, "type");

        final Optional<FactoryBinding> annotation = Optional.of(type)
                .filter(t -> t.isAnnotationPresent(FactoryBinding.class))
                .map(t -> t.getAnnotation(FactoryBinding.class));

        if (annotation.isPresent()) {
            if (annotation.get().concrete()) {
                return annotation.map(FactoryBinding::variant);
            }

            return Optional.empty();
        }

        throw new BindingException(String.format("%s is not annotated with %s: Unable to retrieve the variant", type.getName(), FactoryBinding.class.getName()));
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
     * @throws BindingException if no instance of {@code type} is found for the {@code value} by using the {@code valueMapping}
     * @see ServiceResolver#resolve(Class)
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <T> T find(Class<? super T> type, Function<Class<? extends BackendFactory>, String> valueMapping, String value, @Nullable String variant) {
        final String variantOrDefault = Optional.ofNullable(variant).orElse(FactoryBinding.DEFAULT_VARIANT);

        // Find all objects that match the value and variant
        final List<? super T> services = ServiceResolver.getInstance()
                .resolve(type)
                .filter(t -> Optional.ofNullable(t.getClass().getDeclaredAnnotation(FactoryBinding.class))
                        .filter(a -> Objects.equals(value, valueMapping.apply(a.factory())))
                        .filter(a -> Objects.equals(variantOrDefault, a.variant()))
                        .isPresent())
                .collect(Collectors.toList());

        // Ensure that only one type is relevant
        if (services.isEmpty()) {
            throw new BindingException(String.format("Unable to find a %s instance for value '%s' and variant '%s'; No relevant type found", type.getName(), value, variantOrDefault));
        }
        else if (services.size() > 1) {
            throw new BindingException(String.format("Unable to find a %s instance for value '%s' and variant '%s'; Several relevant types found : %s", type.getName(), value, variantOrDefault, services));
        }

        return (T) services.get(0);
    }
}
