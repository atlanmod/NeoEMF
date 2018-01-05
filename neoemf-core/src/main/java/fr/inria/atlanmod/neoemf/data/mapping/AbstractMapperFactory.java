/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.data.store.Store;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract factory that helps the creation of {@link DataMapper} instances.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractMapperFactory {

    /**
     * Creates a new instance of the represented {@link DataMapper}.
     *
     * @param typeName   the name of the type to instantiate
     * @param parameters the parameters of the constructor
     *
     * @return a new instance of {@link Store}
     *
     * @throws InvalidDataMapperException if an error occurs during the instantiation
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected final <T> T createMapper(String typeName, Object... parameters) {
        try {
            ClassLoader currentClassLoader = getClass().getClassLoader();
            Class<? extends DataMapper> type = (Class<? extends DataMapper>) Class.forName(typeName, true, currentClassLoader);

            return createMapper(type, parameters);
        }
        catch (ClassCastException e) {
            throw new InvalidDataMapperException(String.format("%s is not assignable from %s", typeName, DataMapper.class.getName()));
        }
        catch (ClassNotFoundException e) {
            throw new InvalidDataMapperException(e);
        }
    }

    /**
     * Creates a new instance of the represented {@link DataMapper}.
     *
     * @param type       the type to instantiate
     * @param parameters the parameters of the constructor
     *
     * @return a new instance of {@link Store}
     *
     * @throws InvalidDataMapperException if an error occurs during the instantiation
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected final <T> T createMapper(Class<? extends DataMapper> type, Object... parameters) {
        try {
            Class<?>[] types = Arrays.stream(parameters)
                    .map(Object::getClass)
                    .toArray(Class<?>[]::new);

            Constructor<?> constructor = findConstructor(type, types);

            return (T) constructor.newInstance(parameters);
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new InvalidDataMapperException(e);
        }
    }

    /**
     * Finds the constructor of the {@code type} that declare the given {@code parameterTypes}.
     *
     * @param type           the type where to find the constructor
     * @param parameterTypes the declared parameter types of the constructor
     *
     * @return the constructor
     *
     * @throws NoSuchMethodException if the constructor cannot be found with the given arguments
     */
    @Nonnull
    private Constructor<?> findConstructor(Class<?> type, Class<?>... parameterTypes) throws NoSuchMethodException {
        return Arrays.stream(type.getDeclaredConstructors())
                .peek(c -> c.setAccessible(true))
                .filter(c -> c.getParameterCount() == parameterTypes.length)
                .filter(c -> checkConstructor(c, parameterTypes))
                .findAny()
                .orElseThrow(() -> new NoSuchMethodException(type.getName() + ".<init>" + argumentTypesToString(parameterTypes)));
    }

    /**
     * Checks that the {@code constructor} declares the given {@code parameterTypes} in the same order.
     *
     * @param constructor    the constructor to check
     * @param parameterTypes the expected parameter types of the constructor
     *
     * @return {@code true} if the constructor declare the given {@code parameterTypes}
     */
    private boolean checkConstructor(Constructor<?> constructor, Class<?>... parameterTypes) {
        Class<?>[] realParameterTypes = constructor.getParameterTypes();
        return IntStream.range(0, parameterTypes.length).allMatch(i -> realParameterTypes[i].isAssignableFrom(parameterTypes[i]));
    }

    /**
     * Formats an array in a string.
     *
     * @param argTypes the array to format
     *
     * @return a formatted string
     */
    @Nonnull
    private String argumentTypesToString(@Nullable Class<?>[] argTypes) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        if (argTypes != null) {
            for (int i = 0; i < argTypes.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                Class<?> c = argTypes[i];
                sb.append((c == null) ? "null" : c.getName());
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
