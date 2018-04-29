/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.config;

import fr.inria.atlanmod.commons.log.Level;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.bind.BindingEngine;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.store.AutoSavingStore;
import fr.inria.atlanmod.neoemf.data.store.ClassCachingStore;
import fr.inria.atlanmod.neoemf.data.store.ContainerCachingStore;
import fr.inria.atlanmod.neoemf.data.store.FeatureCachingStore;
import fr.inria.atlanmod.neoemf.data.store.ListeningStore;
import fr.inria.atlanmod.neoemf.data.store.ReadOnlyStore;
import fr.inria.atlanmod.neoemf.data.store.SizeCachingStore;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.listener.LoggingStoreListener;
import fr.inria.atlanmod.neoemf.data.store.listener.RecordingStoreListener;
import fr.inria.atlanmod.neoemf.data.store.listener.StoreListener;
import fr.inria.atlanmod.neoemf.data.store.listener.StoreStats;

import java.io.IOException;
import java.lang.reflect.Modifier;
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
     * The key identifying the {@link fr.inria.atlanmod.neoemf.data.BackendFactory} type.
     *
     * @see fr.inria.atlanmod.neoemf.data.BackendFactory#name()
     */
    static final String BACKEND_TYPE = createKey(BACKEND, "type");

    /**
     * The key identifying the {@link fr.inria.atlanmod.neoemf.data.BackendFactory} binding.
     */
    static final String BACKEND_VARIANT = createKey(BACKEND, "variant");

    /**
     * The key identifying the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.Backend}.
     */
    static final String BACKEND_MAPPING = createKey(BACKEND, "mapping");

    // endregion

    // region Store/Listener configuration

    /**
     * The base prefix for all internal options key related to {@link fr.inria.atlanmod.neoemf.data.store.Store}s
     * configuration.
     */
    static final String STORE = createKey(BASE_PREFIX, "store");

    /**
     * The key identifying all defined stores.
     */
    static final String STORE_TYPES = createKey(STORE, "types");

    /**
     * The key identifying all defined store listeners.
     */
    static final String STORE_LISTENERS = createKey(STORE, "listeners");

    // endregion

    /**
     * A map that holds all defined key/value options.
     */
    @Nonnull
    private final Map<String, Object> options = new TreeMap<>();

    /**
     * A set that holds all defined stores.
     */
    @Nonnull
    private final Set<Store> stores = new HashSet<>();

    /**
     * A set that holds all defined store listeners.
     */
    @Nonnull
    private final Set<StoreListener> listeners = new HashSet<>();

    /**
     * Constructs a new {@code BaseConfig} with default settings.
     * <p>
     * <b>NOTE:</b> This implementation is intended to configure a {@link fr.inria.atlanmod.neoemf.data.store.Store}'
     * chain. The created configuration is not associated with any {@link fr.inria.atlanmod.neoemf.data.BackendFactory},
     * so it cannot be used to configure a {@link fr.inria.atlanmod.neoemf.data.Backend}. Use the specific
     * implementation for this.
     */
    public BaseConfig() {
        if (getClass() != BaseConfig.class) { // The only exception
            setName(BindingEngine.nameOf(getClass()));

            if (useVariantWith(getClass())) {
                setVariant(BindingEngine.variantOf(getClass()));
            }
        }
    }

    /**
     * Returns {@code true} if the variant must be defined for the specified {@code type}.
     *
     * @param type the type
     *
     * @return {@code true} if the variant must be defined for the specified {@code type}
     */
    private static boolean useVariantWith(Class<?> type) {
        // TODO Improve this detection to avoid approximations
        return !Modifier.isAbstract(type.getModifiers()) && !type.getSimpleName().matches("Base[A-Z].*");
    }

    /**
     * @deprecated Use the default constructor instead.
     */
    @Nonnull
    @Deprecated
    public static Config newConfig() {
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
        checkNotNull(segments, "segments");
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
        view.put(STORE_TYPES, new HashSet<>(stores));
        view.put(STORE_LISTENERS, new HashSet<>(listeners));
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
    public String getVariant() {
        return this.<String>getOption(BACKEND_VARIANT)
                .<InvalidConfigException>orElseThrow(() -> new InvalidConfigException("The variant is not defined"));
    }

    @Nonnull
    @SuppressWarnings("UnusedReturnValue")
    private C setVariant(String variant) {
        return addOption(BACKEND_VARIANT, variant);
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
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        if (isReadOnlyKey().test(key)) {
            checkConflict(key, value);
        }

        options.put(key, value);
        return me();
    }

    // region Stores

    @Nonnull
    @Override
    public C addStore(Store store) {
        checkNotNull(store, "store");

        if (ListeningStore.class.isInstance(store)) {
            throw new InvalidConfigException(String.format("Cannot add %s: Use #addListener() instead", store.getClass().getName()));
        }

        if (!stores.add(store)) {
            Log.debug("The store has already been defined ({0})", store.getClass().getSimpleName());
        }

        return me();
    }

    @Nonnull
    @Override
    public C cacheFeatures() {
        return addStore(new FeatureCachingStore());
    }

    @Nonnull
    @Override
    public C cacheContainers() {
        return addStore(new ContainerCachingStore());
    }

    @Nonnull
    @Override
    public C cacheMetaClasses() {
        return addStore(new ClassCachingStore());
    }

    @Nonnull
    @Override
    public C cacheSizes() {
        return addStore(new SizeCachingStore());
    }

    @Nonnull
    @Override
    public C readOnly() {
        return addStore(new ReadOnlyStore());
    }

    @Nonnull
    @Override
    public C autoSave() {
        return addStore(new AutoSavingStore());
    }

    @Nonnull
    @Override
    public C autoSave(long chunk) {
        if (chunk < 0) {
            throw new InvalidConfigException(String.format("The auto-save chunk cannot be lower than 0 (but it was %d)", chunk));
        }

        return addStore(new AutoSavingStore(chunk));
    }

    // endregion

    // region Listeners

    @Nonnull
    @Override
    public C addListener(StoreListener listener) {
        checkNotNull(listener, "listener");

        if (!listeners.add(listener)) {
            Log.debug("The listener has already been defined ({0})", listener.getClass().getSimpleName());
        }

        return me();
    }

    @Nonnull
    @Override
    public C log() {
        return addListener(new LoggingStoreListener());
    }

    @Nonnull
    @Override
    public C log(Level level) {
        return addListener(new LoggingStoreListener(level));
    }

    @Nonnull
    @Override
    public C recordStats(StoreStats stats) {
        return addListener(new RecordingStoreListener(stats));
    }

    // endregion

    @Nonnull
    @Override
    public Map<String, ?> getOptions() {
        return Collections.unmodifiableMap(options);
    }

    @Nonnull
    @Override
    public Set<Store> getStores() {
        final Set<Store> view = new HashSet<>(stores);
        if (!listeners.isEmpty()) {
            view.add(new ListeningStore(listeners));
        }
        return Collections.unmodifiableSet(view);
    }

    // endregion

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public C merge(Map<String, ?> map) {
        checkNotNull(map, "map");

        // Copy the map to a mutable map
        final Map<String, Object> mutableMap = new TreeMap<>(map);

        // Process the defined listeners
        Optional.ofNullable(mutableMap.remove(STORE_LISTENERS))
                .map(s -> (Iterable<StoreListener>) s)
                .ifPresent(s -> s.forEach(this::addListener));

        // Process the defined stores
        Optional.ofNullable(mutableMap.remove(STORE_TYPES))
                .map(s -> (Iterable<Store>) s)
                .ifPresent(s -> s.forEach(this::addStore));

        // Process all other options
        mutableMap.forEach(this::addOption);

        return me();
    }

    @Nonnull
    @Override
    public final C merge(ImmutableConfig config) {
        checkNotNull(config, "config");

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
     * Returns this instance, casted as a {@code C}.
     *
     * @return this instance
     */
    @Nonnull
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
        options.put(BACKEND_MAPPING, checkNotNull(mappingClass.getName(), "mapping.name"));
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
            throw new InvalidConfigException(String.format("Values are different for '%s': actual = \"%s\", new = \"%s\"", key, actualValue.get(), newValue));
        }
    }
}
