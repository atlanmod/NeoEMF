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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.LocalStoreAdapter;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.option.InvalidOptionException;
import fr.inria.atlanmod.neoemf.option.PersistentResourceOptions;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static fr.inria.atlanmod.common.Preconditions.checkState;
import static java.util.Objects.isNull;

/**
 * An abstract {@link BackendFactory} that processes common store options and manages the configuration.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBackendFactory implements BackendFactory {

    /**
     * Constructs a new {@code AbstractBackendFactory}.
     */
    protected AbstractBackendFactory() {
    }

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
     * Retrieves the stores to use from the {@code options}.
     *
     * @param options the options containing the stores declaration
     *
     * @return a list of stores declaration
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected static Set<PersistentStoreOptions> storesFrom(Map<String, Object> options) {
        try {
            if (options.containsKey(PersistentResourceOptions.STORES)) {
                return Set.class.cast(options.get(PersistentResourceOptions.STORES));
            }
        }
        catch (ClassCastException ignored) {
        }

        return Collections.emptySet();
    }

    /**
     * Creates a new instance of the represented {@link Store}.
     *
     * @param className  the name of the class to instantiate
     * @param parameters the parameters of the constructor
     *
     * @return a new instance of {@link Store}
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected final <T> T newInstanceOf(String className, ConstructorParameter... parameters) {
        try {
            Class<?> type = Class.forName(className, false, getClass().getClassLoader());

            List<Class<?>> types = Arrays.stream(parameters)
                    .map(p -> p.type)
                    .collect(Collectors.toList());

            Constructor<?> constructor = type.getDeclaredConstructor(types.toArray(new Class<?>[types.size()]));
            constructor.setAccessible(true);

            List<Object> values = Arrays.stream(parameters)
                    .map(p -> p.value)
                    .collect(Collectors.toList());

            return (T) constructor.newInstance(values.toArray());
        }
        catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public Backend createTransientBackend() {
        if (supportsTransient()) {
            return new DefaultTransientBackend();
        }
        else {
            return new InvalidTransientBackend();
        }
    }

    @Nonnull
    @Override
    public final Store createStore(Backend backend, PersistentResource resource, Map<String, Object> options) {
        checkNotNull(options);

        Store store = new DirectWriteStore(backend, resource);

        for (PersistentStoreOptions opt : storesFrom(options).stream().sorted().collect(Collectors.toList())) {
            List<ConstructorParameter> parameters = opt.parameters().stream()
                    .filter(options::containsKey)
                    .map(key -> new ConstructorParameter(options.get(key)))
                    .collect(Collectors.toList());

            parameters.add(0,
                    new ConstructorParameter(store, Store.class));

            store = newInstanceOf(opt.className(), parameters.toArray(new ConstructorParameter[parameters.size()]));
        }

        return LocalStoreAdapter.adapt(store);
    }

    /**
     * Creates and saves the NeoEMF configuration.
     * <p>
     * The configuration is stored as a properties file beside a database in order to identify a {@link
     * PersistentBackend}.
     *
     * @param directory the directory where the configuration must be stored
     * @param mapping   the used mapping
     *
     * @throws InvalidDataStoreException if the configuration cannot be created in the {@code directory}
     */
    protected final void processGlobalConfiguration(File directory, String mapping) {
        Path path = Paths.get(directory.getAbsolutePath()).resolve(CONFIG_FILE);
        Configuration configuration = Configuration.load(path);

        if (!configuration.containsKey(BACKEND_PROPERTY)) {
            configuration.setProperty(BACKEND_PROPERTY, name());
        }

        if (configuration.containsKey(PersistentResourceOptions.MAPPING)) {
            String savedMapping = configuration.getProperty(PersistentResourceOptions.MAPPING);
            checkState(Objects.equals(mapping, savedMapping),
                    "The back-end is mapped with %s (but actual is %s)", savedMapping, mapping);
        }
        else {
            configuration.setProperty(PersistentResourceOptions.MAPPING, mapping);
        }

        configuration.save();
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
         * Constructs a new {@code ConstructorParameter} with the value, and the direct {@link Class} of the
         * {@code value}.
         *
         * @param value the value to use in the constructor
         */
        public ConstructorParameter(Object value) {
            this(value, null);
        }
    }
}
