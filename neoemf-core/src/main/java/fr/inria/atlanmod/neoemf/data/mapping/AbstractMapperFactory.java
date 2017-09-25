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

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.bind.BindingException;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.option.InvalidOptionException;
import fr.inria.atlanmod.neoemf.option.PersistentResourceOptions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static java.util.Objects.isNull;

/**
 * An abstract factory that helps the creation of {@link DataMapper} instances.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractMapperFactory {

    /**
     * Retrieves the mapping to use from the {@code options}.
     *
     * @param options the options containing the mapping
     *
     * @return the class name of the mapping to use
     *
     * @throws InvalidOptionException if the mapping is not defined
     */
    @Nonnull
    protected static String mappingFrom(Map<String, Object> options) {
        if (!options.containsKey(PersistentResourceOptions.MAPPING)) {
            throw new InvalidOptionException("No mapping is defined");
        }

        return options.get(PersistentResourceOptions.MAPPING).toString();
    }

    /**
     * Creates a new instance of the represented {@link DataMapper}.
     *
     * @param className  the name of the class to instantiate
     * @param parameters the parameters of the constructor
     *
     * @return a new instance of {@link Store}
     *
     * @throws BindingException         if an error occurs during the instantiation
     * @throws IllegalArgumentException if the class is not assignable from {@link DataMapper}
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected final <T> T newInstanceOf(String className, ConstructorParameter... parameters) {
        try {
            ClassLoader currentClassLoader = getClass().getClassLoader();
            Class<? extends DataMapper> type = (Class<? extends DataMapper>) Class.forName(className, true, currentClassLoader);

            return newInstanceOf(type, parameters);
        }
        catch (ClassCastException e) {
            throw new IllegalArgumentException(String.format("%s is not assignable from %s", className, DataMapper.class.getName()));
        }
        catch (ClassNotFoundException e) {
            throw new BindingException(e);
        }
    }

    /**
     * Creates a new instance of the represented {@link DataMapper}.
     *
     * @param mapperClass the class to instantiate
     * @param parameters  the parameters of the constructor
     *
     * @return a new instance of {@link Store}
     *
     * @throws BindingException if an error occurs during the instantiation
     */
    @Nonnull
    @SuppressWarnings({"unchecked", "JavaReflectionMemberAccess"})
    protected final <T> T newInstanceOf(Class<? extends DataMapper> mapperClass, ConstructorParameter... parameters) {
        Class<?>[] types = Arrays.stream(parameters)
                .map(p -> p.type)
                .toArray(Class<?>[]::new);

        Object[] values = Arrays.stream(parameters)
                .map(p -> p.value)
                .toArray();

        try {
            Constructor<?> constructor = mapperClass.getDeclaredConstructor(types);
            constructor.setAccessible(true);

            return (T) constructor.newInstance(values);
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new BindingException(e);
        }
    }

    /**
     * A simple wrapper for constructor parameters.
     *
     * @see #newInstanceOf(String, ConstructorParameter...)
     * @see Class#getDeclaredConstructor(Class[])
     * @see Constructor#newInstance(Object...)
     */
    @Immutable
    @ParametersAreNonnullByDefault
    protected static final class ConstructorParameter {

        /**
         * The value to use in the constructor.
         */
        @Nonnull
        private final Object value;

        /**
         * The declared type of the value in the constructor.
         */
        @Nonnull
        private final Class<?> type;

        /**
         * Constructs a new {@code ConstructorParameter} with the value, and the declared type.
         *
         * @param value the value to use in the constructor
         * @param type  the declared type of the value in the constructor
         */
        public ConstructorParameter(Object value, @Nullable Class<?> type) {
            this.value = value;

            if (isNull(type)) {
                type = value.getClass();
            }

            checkArgument(type.isInstance(value));
            this.type = type;
        }

        /**
         * Constructs a new {@code ConstructorParameter} with the value, and the direct {@link Class} of the {@code
         * value}.
         *
         * @param value the value to use in the constructor
         */
        public ConstructorParameter(Object value) {
            this(value, null);
        }
    }
}
