/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.config;

import fr.inria.atlanmod.commons.annotation.Builder;
import fr.inria.atlanmod.commons.log.Level;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.bind.Bindings;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.store.Store;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * The base {@link Config} that creates and manages common configuration that are available for all back-end
 * implementations. It also provides all methods for building and querying a configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 *
 * @param <C> the "self"-type of this {@link Config}
 */
@Builder("newConfig")
@ParametersAreNonnullByDefault
// TODO Split the back-end configuration and stores configuration in different classes
public class BaseConfig<C extends BaseConfig<C>> implements Config {

    /**
     * The base prefix for all internal options key related to the NeoEMF behavior.
     */
    public static final String BASE_PREFIX = "neoemf";

    // region Backend configuration

    /**
     * The base prefix for all internal options related to {@link fr.inria.atlanmod.neoemf.data.Backend}s
     * configuration.
     */
    static final String BACKEND = createKey(BASE_PREFIX, "backend");

    /**
     * The key identifying the {@link fr.inria.atlanmod.neoemf.data.BackendFactory}.
     *
     * @see fr.inria.atlanmod.neoemf.data.BackendFactory#name()
     */
    static final String BACKEND_TYPE = createKey(BACKEND, "type");

    /**
     * The key identifying the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.Backend}.
     */
    static final String BACKEND_MAPPING = createKey(BACKEND, "mapping");

    // endregion

    // region Store configuration

    /**
     * The base prefix for all internal options key related to {@link fr.inria.atlanmod.neoemf.data.store.Store}s
     * configuration.
     */
    static final String STORE = createKey(BASE_PREFIX, "store");

    /**
     * The key identifying all defined store definitions.
     */
    static final String STORE_TYPES = createKey(STORE, "types");

    /**
     * The key identifying the number of operations between each save in auto-save mode.
     */
    static final String STORE_AUTOSAVE_CHUNK = createKey(STORE, "autosave", "chunk");

    /**
     * The key identifying the logging level in log mode.
     */
    static final String STORE_LOG_LEVEL = createKey(STORE, "log", "level");

    // endregion

    /**
     * Map that holds all defined key/value options.
     */
    @Nonnull
    private final Map<String, Object> options = new TreeMap<>();

    /**
     * List that holds all defined store definitions.
     */
    @Nonnull
    private final Set<ConfigType<? extends Store>> stores = new HashSet<>();

    /**
     * Constructs a new {@code BaseConfig}.
     */
    protected BaseConfig() {
        if (getClass() != BaseConfig.class) { // The only exception
            setName(Bindings.factoryFor(getClass()).name());
        }
    }

    /**
     * Constructs a new {@code BaseConfig} instance.
     * <p>
     * <b>NOTE:</b> This implementation is intended to configure a {@link fr.inria.atlanmod.neoemf.data.store.Store}'
     * chain in a transient context. The created configuration is not associated with any {@link
     * fr.inria.atlanmod.neoemf.data.BackendFactory}, so it cannot be used to configure a {@link
     * fr.inria.atlanmod.neoemf.data.Backend}. Use the specific implementation for this.
     *
     * @return a new configuration
     */
    @Nonnull
    public static BaseConfig<?> newConfig() {
        return new BaseConfig<>();
    }

    /**
     * Creates a new key from the given {@code segments}. Each segment will be delimited by a dot.
     *
     * @param segments the segments of the key
     *
     * @return the created key
     */
    @Nonnull
    protected static String createKey(String... segments) {
        checkNotNull(segments);
        return Arrays.stream(segments).collect(Collectors.joining("."));
    }

    @Override
    public void save(Path directory) throws IOException {
        ConfigFile configFile = ConfigFile.load(directory);

        final Predicate<String> isPersistentKey = isPersistentKey();

        toMap().entrySet().stream()
                .filter(e -> isPersistentKey.test(e.getKey()))
                .forEach(e -> configFile.put(e.getKey(), String.valueOf(e.getValue())));

        configFile.save();
    }

    @Nonnull
    @Override
    public final Map<String, ?> toMap() {
        Map<String, Object> view = new TreeMap<>(options);
        view.put(STORE_TYPES, getStoreTypes());
        return Collections.unmodifiableMap(view);
    }

    @Nonnull
    @Override
    public String getName() {
        return this.<String>getOption(BACKEND_TYPE)
                .<InvalidConfigException>orElseThrow(() -> new InvalidConfigException("The name is not defined"));
    }

    @Nonnull
    @SuppressWarnings("UnusedReturnValue")
    private C setName(String name) {
        return addOption(BACKEND_TYPE, name);
    }

    @Nonnull
    @Override
    public String getMapping() {
        return this.<String>getOption(BACKEND_MAPPING)
                .<InvalidConfigException>orElseThrow(() -> new InvalidConfigException("The mapping is not defined"));
    }

    @Nonnull
    @Override
    public C setMapping(String mappingType) {
        return setMappingWithCheck(mappingType, true);
    }

    @Nonnull
    @Override
    public <T> C addOption(String key, T value) {
        if (isReadOnlyKey().test(key)) {
            checkConflict(key, value);
        }

        options.put(checkNotNull(key), checkNotNull(value));
        return me();
    }

    @Nonnull
    @Override
    public C addStoreType(ConfigType<? extends Store> storeType) {
        checkNotNull(storeType);

        if (!stores.add(storeType)) {
            Log.debug("The store has already been defined ({0})", storeType);
        }

        return me();
    }

    @Nonnull
    @Override
    public C cacheFeatures() {
        return addStoreType(DefaultStoreTypes.CACHE_FEATURE);
    }

    @Nonnull
    @Override
    public C cacheContainers() {
        return addStoreType(DefaultStoreTypes.CACHE_CONTAINER);
    }

    @Nonnull
    @Override
    public C cacheMetaClasses() {
        return addStoreType(DefaultStoreTypes.CACHE_METACLASS);
    }

    @Nonnull
    @Override
    public C cacheSizes() {
        return addStoreType(DefaultStoreTypes.CACHE_SIZE);
    }

    @Nonnull
    @Override
    public C readOnly() {
        return addStoreType(DefaultStoreTypes.READONLY);
    }

    @Nonnull
    @Override
    public C autoSave() {
        return addStoreType(DefaultStoreTypes.AUTOSAVE);
    }

    @Nonnull
    @Override
    public C autoSave(long chunk) {
        if (chunk < 0) {
            throw new InvalidConfigException(String.format("The auto-save chunk cannot be lower than 0 (but it was %d)", chunk));
        }

        return autoSave().addOption(STORE_AUTOSAVE_CHUNK, chunk);
    }

    @Nonnull
    @Override
    public C log() {
        return addStoreType(DefaultStoreTypes.LOG);
    }

    @Nonnull
    @Override
    public C log(Level level) {
        return log().addOption(STORE_LOG_LEVEL, checkNotNull(level));
    }

    @Nonnull
    @Override
    public C recordStats() {
        return addStoreType(DefaultStoreTypes.STATS);
    }

    @Nonnull
    @Override
    public Map<String, ?> getOptions() {
        return Collections.unmodifiableMap(options);
    }

    @Nonnull
    @Override
    public Set<ConfigType<? extends Store>> getStoreTypes() {
        return Collections.unmodifiableSet(stores);
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public C merge(Map<String, ?> map) {
        checkNotNull(map);

        // Copy the map to a mutable map
        final Map<String, Object> mutableMap = new TreeMap<>(map);

        // Process the defined stores
        Optional.ofNullable(mutableMap.remove(STORE_TYPES))
                .map(s -> (Set<ConfigType<Store>>) s)
                .ifPresent(s -> s.forEach(this::addStoreType));

        // Process all other options
        mutableMap.forEach(this::addOption);

        return me();
    }

    @Nonnull
    @Override
    public final C merge(ImmutableConfig config) {
        checkNotNull(config);

        return merge(config.toMap());
    }

    // region Filters

    /**
     * Returns a {@link Predicate} used to filter the options to be saved in a configuration file, in order to retrieve
     * them in a future execution.
     * <p>
     * By default, only the options related to a back-end are saved.
     *
     * @return a new predicate
     *
     * @see #save(Path)
     */
    @Nonnull
    @OverridingMethodsMustInvokeSuper
    protected Predicate<String> isPersistentKey() {
        return s -> s.startsWith(BACKEND);
    }

    /**
     * Returns a {@link Predicate} used to filter read-only keys. These keys can not be modified after their first
     * declaration.
     *
     * @return a new predicate
     *
     * @see #addOption(String, Object)
     */
    @Nonnull
    @OverridingMethodsMustInvokeSuper
    protected Predicate<String> isReadOnlyKey() {
        return s -> s.startsWith(BACKEND);
    }

    // endregion

    /**
     * Returns this instance, casted as a {@link C}.
     *
     * @return this instance
     */
    @SuppressWarnings("unchecked")
    private C me() {
        return (C) this;
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.Backend}. If the mapping has
     * already been defined and {@code checkConflict == false}, then it will be erased by the new.
     *
     * @param mappingType   the type name of the mapping to use
     * @param checkConflict {@code true} if the {@code mappingType} must be compared with the current mapping to check
     *                      collision
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected final C setMappingWithCheck(String mappingType, boolean checkConflict) {
        if (checkConflict) {
            checkConflict(BACKEND_MAPPING, mappingType);
        }

        Class<? extends DataMapper> mappingClass;
        try {
            mappingClass = (Class<? extends DataMapper>) Class.forName(mappingType);
        }
        catch (ClassNotFoundException e) {
            throw new InvalidConfigException(e);
        }
        catch (ClassCastException e) {
            throw new InvalidConfigException(
                    String.format("The %s must be assignable to %s", mappingType, DataMapper.class.getName()));
        }

        // Don't use addOption to avoid double checking
        options.put(BACKEND_MAPPING, checkNotNull(mappingClass.getName()));
        return me();
    }

    /**
     * Checks a collision between the current value, if defined, and the {@code newValue}. The current value and the new
     * must be identical.
     *
     * @param key      the key of the value to check
     * @param newValue the new value
     *
     * @throws InvalidConfigException if the previous value and the new are not equal
     */
    private <T> void checkConflict(String key, T newValue) {
        Optional<T> actualValue = getOption(key);
        if (actualValue.isPresent() && !Objects.equals(newValue, actualValue.get())) {
            throw new InvalidConfigException(String.format("Values are different for %s: actual = \"%s\", new = \"%s\"", key, actualValue.get(), newValue));
        }
    }
}
