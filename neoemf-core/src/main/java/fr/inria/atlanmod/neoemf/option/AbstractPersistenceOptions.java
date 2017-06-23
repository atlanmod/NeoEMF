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

import fr.inria.atlanmod.common.log.Level;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * An abstract {@link PersistenceOptions} that manages the assembly and the construction of {@link Map} options.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 *
 * @param <B> the "self"-type of this {@link PersistenceOptions}
 */
@ParametersAreNonnullByDefault
public abstract class AbstractPersistenceOptions<B extends AbstractPersistenceOptions<B>> implements PersistenceOptions {

    /**
     * Map that holds all defined key/value options.
     */
    @Nonnull
    private final Map<String, Object> options;

    /**
     * List that holds all defined store options.
     */
    @Nonnull
    private final Set<PersistentStoreOptions> stores;

    /**
     * Constructs a new {@code AbstractPersistenceOptions}.
     */
    protected AbstractPersistenceOptions() {
        this.options = new HashMap<>();
        this.stores = new HashSet<>();
    }

    @Nonnull
    @Override
    public final Map<String, Object> asMap() {
        if (!stores.isEmpty()) {
            withOption(PersistentResourceOptions.STORES, Collections.unmodifiableSet(stores));
        }

        return Collections.unmodifiableMap(options);
    }

    @Nonnull
    @Override
    public B withOption(String key, Object value) {
        options.put(checkNotNull(key), checkNotNull(value));

        return me();
    }

    @Nonnull
    @Override
    public B withOptions(Map<String, Object> options) {
        checkNotNull(options).forEach(this::withOption);

        return me();
    }

    @Nonnull
    @Override
    public B withMapping(String mapping) {
        Class<?> mappingClass;

        try {
            mappingClass = Class.forName(mapping);
        }
        catch (ClassNotFoundException e) {
            throw new InvalidOptionException(e);
        }

        if (!DataMapper.class.isAssignableFrom(mappingClass)) {
            throw new InvalidOptionException(
                    String.format("The %s must be assignable to %s", mapping, DataMapper.class.getName()));
        }

        return withOption(PersistentResourceOptions.MAPPING, checkNotNull(mapping));
    }

    @Nonnull
    @Override
    public B withMapping(Class<? extends DataMapper> mapping) {
        return withOption(PersistentResourceOptions.MAPPING, checkNotNull(mapping).getName());
    }

    @Nonnull
    @Override
    public B cacheFeatures() {
        return withStore(PersistentStoreOptions.CACHE_STRUCTURAL_FEATURE);
    }

    @Nonnull
    @Override
    public B cacheIsSet() {
        return withStore(PersistentStoreOptions.CACHE_IS_SET);
    }

    @Nonnull
    @Override
    public B cacheContainers() {
        return withStore(PersistentStoreOptions.CACHE_CONTAINER);
    }

    @Nonnull
    @Override
    public B cacheMetaclasses() {
        return withStore(PersistentStoreOptions.CACHE_METACLASS);
    }

    @Nonnull
    @Override
    public B cacheSizes() {
        return withStore(PersistentStoreOptions.CACHE_SIZE);
    }

    @Nonnull
    @Override
    public B countLoadedObjects() {
        return withStore(PersistentStoreOptions.COUNT_LOADED_OBJECT);
    }

    @Nonnull
    @Override
    public B readOnly() {
        return withStore(PersistentStoreOptions.READ_ONLY);
    }

    @Nonnull
    @Override
    public B autoSave(@Nonnegative long chunk) {
        if (chunk < 0) {
            throw new InvalidOptionException(String.format("The auto-save chuck cannot be lower than 0 (but it was %d)", chunk));
        }

        withStore(PersistentStoreOptions.AUTO_SAVE);
        return withOption(PersistentResourceOptions.AUTO_SAVE_CHUNK, chunk);
    }

    @Nonnull
    @Override
    public B autoSave() {
        return withStore(PersistentStoreOptions.AUTO_SAVE);
    }

    @Nonnull
    @Override
    public B log() {
        return withStore(PersistentStoreOptions.LOG);
    }

    @Nonnull
    @Override
    public B log(Level level) {
        withStore(PersistentStoreOptions.LOG);
        return withOption(PersistentResourceOptions.LOG_LEVEL, checkNotNull(level));
    }

    @Nonnull
    @Override
    public B recordStats() {
        return withStore(PersistentStoreOptions.STATS);
    }

    /**
     * Returns this instance, casted as a {@link B}.
     *
     * @return this instance
     */
    @SuppressWarnings("unchecked")
    private B me() {
        return (B) this;
    }

    /**
     * Adds a feature defined by the given {@code store} in the created options.
     *
     * @param store the option to add
     *
     * @return this builder (for chaining)
     *
     * @throws NullPointerException   if the {@code store} is {@code null}
     * @throws InvalidOptionException if the {@code store} has already been defined
     */
    @Nonnull
    protected B withStore(PersistentStoreOptions store) {
        checkNotNull(store);

        if (stores.contains(store)) {
            throw new InvalidOptionException(String.format("The store has already been defined (%s)", store.name()));
        }

        stores.add(store);

        return me();
    }
}
