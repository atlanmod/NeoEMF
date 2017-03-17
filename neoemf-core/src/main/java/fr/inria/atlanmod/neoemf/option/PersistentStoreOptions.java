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

package fr.inria.atlanmod.neoemf.option;

import fr.inria.atlanmod.neoemf.data.store.AbstractStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.AutoSaveStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.FeatureCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.IsSetCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoadedObjectCounterStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.LoggingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.ReadOnlyStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.SizeCachingStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.Store;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * Represents options related to database access, managed by {@link fr.inria.atlanmod.neoemf.data.BackendFactory}.
 */
@ParametersAreNonnullByDefault
public enum PersistentStoreOptions {

    /**
     * Caches the presence of a value.
     *
     * @see IsSetCachingStoreDecorator
     */
    CACHE_IS_SET(IsSetCachingStoreDecorator.class),

    /**
     * Caches the size data.
     *
     * @see SizeCachingStoreDecorator
     */
    CACHE_SIZE(SizeCachingStoreDecorator.class),

    /**
     * Caches {@link org.eclipse.emf.ecore.EStructuralFeature}s.
     *
     * @see FeatureCachingStoreDecorator
     */
    CACHE_STRUCTURAL_FEATURE(FeatureCachingStoreDecorator.class),

    /**
     * Counts all loaded objects.
     *
     * @see LoadedObjectCounterStoreDecorator
     */
    COUNT_LOADED_OBJECT(LoadedObjectCounterStoreDecorator.class),

    /**
     * Logs every call to a methods.
     *
     * @see LoggingStoreDecorator
     */
    LOG(LoggingStoreDecorator.class, PersistentResourceOptions.LOG_LEVEL),

    /**
     * Automatically saves modifications as calls are made.
     *
     * @see AutoSaveStoreDecorator
     */
    AUTO_SAVE(AutoSaveStoreDecorator.class, PersistentResourceOptions.AUTO_SAVE_CHUNK),

    /**
     * Only allows read operations.
     *
     * @see ReadOnlyStoreDecorator
     */
    READ_ONLY(ReadOnlyStoreDecorator.class);

    /**
     * The type of the represented {@link Store}
     */
    private final Class<? extends AbstractStoreDecorator> type;

    /**
     * The optional parameters of this option.
     */
    private final List<String> parameters;

    /**
     * Constructs a new {@code PersistentStoreOptions} with the given {@code type}.
     *
     * @param type       the type of the represented {@link Store}
     * @param parameters the optional parameters of this option
     */
    PersistentStoreOptions(Class<? extends AbstractStoreDecorator> type, String... parameters) {
        this.type = checkNotNull(type);
        this.parameters = Arrays.stream(parameters).collect(Collectors.toList());
    }

    /**
     * Creates a new instance of the represented {@link Store}.
     *
     * @param parameters the parameters of the constructor of the new instance
     *
     * @return a new instance of {@link Store}
     */
    @Nonnull
    public Store newInstance(Object... parameters) {
        try {
            List<Class<?>> types = Arrays.stream(parameters).map(Object::getClass).collect(Collectors.toList());
            types.set(0, Store.class);

            return type.getConstructor(types.toArray(new Class<?>[types.size()])).newInstance(parameters);
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the optional parameters of this option.
     *
     * @return an immutable list
     */
    @Nonnull
    public List<String> parameters() {
        return parameters;
    }
}
