/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.config.ConfigValue;
import fr.inria.atlanmod.neoemf.data.store.Store;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import javax.annotation.Nonnull;
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
    protected final <T> T createMapper(String typeName, ConfigValue<?>... parameters) {
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
     * @param mapperType the type to instantiate
     * @param parameters the parameters of the constructor
     *
     * @return a new instance of {@link Store}
     *
     * @throws InvalidDataMapperException if an error occurs during the instantiation
     */
    @Nonnull
    @SuppressWarnings({"unchecked", "JavaReflectionMemberAccess"})
    protected final <T> T createMapper(Class<? extends DataMapper> mapperType, ConfigValue<?>... parameters) {
        try {
            Class<?>[] types = Arrays.stream(parameters)
                    .map(ConfigValue::type)
                    .toArray(Class<?>[]::new);

            Constructor<?> constructor = mapperType.getDeclaredConstructor(types);
            constructor.setAccessible(true);

            Object[] values = Arrays.stream(parameters)
                    .map(ConfigValue::value)
                    .toArray();

            return (T) constructor.newInstance(values);
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new InvalidDataMapperException(e);
        }
    }
}
