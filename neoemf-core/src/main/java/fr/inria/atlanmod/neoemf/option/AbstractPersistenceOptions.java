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

import fr.inria.atlanmod.neoemf.util.log.Level;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

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
     * Map that holds all defined key/value options in this builder.
     */
    @Nonnull
    private final Map<String, Object> options;

    /**
     * List that holds all defined store options in this builder.
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

    /**
     * Returns this instance, casted as a {@code <B>}.
     *
     * @return this instance
     */
    @SuppressWarnings("unchecked")
    private B me() {
        return (B) this;
    }

    /**
     * Adds a key/value in the created options. A custom configuration, which is not part of NeoEMF, can be added.
     *
     * @param key   the key to add
     * @param value the value of the {@code key}
     *
     * @return this builder (for chaining)
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    @Nonnull
    public B withOption(String key, Object value) {
        options.put(checkNotNull(key), checkNotNull(value));

        return me();
    }

    /**
     * Adds all key/value pairs of the {@code options} in the created options. A custom configuration, which is not part
     * of NeoEMF, can be added.
     * <p>
     * <b>Note:</b> In case of key conflicts, the value of the specified {@code options} will be used.
     *
     * @param options the options to add
     *
     * @return this builder (for chaining)
     *
     * @throws NullPointerException if the {@code options} is {@code null}
     */
    @Nonnull
    public B withOptions(Map<String, Object> options) {
        checkNotNull(options).forEach(this::withOption);

        return me();
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

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.Backend}. If the mapping has
     * already been defined, then it will be erased by the new.
     *
     * @param mapping the class name of the mapping to use
     *
     * @return this builder (for chaining)
     *
     * @throws NullPointerException if the {@code mapping} is {@code null}
     */
    @Nonnull
    protected B withMapping(String mapping) {
        return withOption(PersistentResourceOptions.MAPPING, checkNotNull(mapping));
    }

    /**
     * Adds the {@code cache-features} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.FeatureCachingStoreDecorator
     */
    @Nonnull
    public B cacheFeatures() {
        return withStore(PersistentStoreOptions.CACHE_STRUCTURAL_FEATURE);
    }

    /**
     * Adds the {@code cache-is-set} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.IsSetCachingStoreDecorator
     */
    @Nonnull
    public B cacheIsSet() {
        return withStore(PersistentStoreOptions.CACHE_IS_SET);
    }

    /**
     * Adds the {@code cache-containers} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.ContainerCachingStoreDecorator
     */
    @Nonnull
    public B cacheContainers() {
        return withStore(PersistentStoreOptions.CACHE_CONTAINER);
    }

    /**
     * Adds the {@code cache-metaclasses} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.MetaclassCachingStoreDecorator
     */
    @Nonnull
    public B cacheMetaclasses() {
        return withStore(PersistentStoreOptions.CACHE_METACLASS);
    }

    /**
     * Adds the {@code cache-sizes} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.SizeCachingStoreDecorator
     */
    @Nonnull
    public B cacheSizes() {
        return withStore(PersistentStoreOptions.CACHE_SIZE);
    }

    /**
     * Adds the {@code count-loaded-objects} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.LoadedObjectCounterStoreDecorator
     */
    @Nonnull
    public B countLoadedObjects() {
        return withStore(PersistentStoreOptions.COUNT_LOADED_OBJECT);
    }

    /**
     * Adds the {@code read-only} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.ReadOnlyStoreDecorator
     */
    @Nonnull
    public B readOnly() {
        return withStore(PersistentStoreOptions.READ_ONLY);
    }

    /**
     * Adds the {@code autoSave} feature with the given {@code chunk} size in the created options.
     * <p>
     * <b>WARNING:</b> When {@code chunk} is zero, the store will be saved at each call.
     *
     * @param chunk the number of database operations between each save
     *
     * @return this builder (for chaining)
     *
     * @throws InvalidOptionException if the {@code chunk} is {@code &lt; 0}
     * @see fr.inria.atlanmod.neoemf.data.store.AutoSaveStoreDecorator
     */
    @Nonnull
    public B autoSave(@Nonnegative long chunk) {
        if (chunk < 0) {
            throw new InvalidOptionException(String.format("The auto-save chuck cannot be lower than 0 (but it was %d)", chunk));
        }

        withStore(PersistentStoreOptions.AUTO_SAVE);
        return withOption(PersistentResourceOptions.AUTO_SAVE_CHUNK, chunk);
    }

    /**
     * Adds the {@code autoSave} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.AutoSaveStoreDecorator
     */
    @Nonnull
    public B autoSave() {
        return withStore(PersistentStoreOptions.AUTO_SAVE);
    }

    /**
     * Adds the {@code log} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.LoggingStoreDecorator
     */
    @Nonnull
    public B log() {
        return withStore(PersistentStoreOptions.LOG);
    }

    /**
     * Adds the {@code log} feature in the created options.
     *
     * @param level the logging {@link Level} to use
     *
     * @return this builder (for chaining)
     *
     * @throws NullPointerException if the {@code level} is {@code null}
     * @see fr.inria.atlanmod.neoemf.data.store.LoggingStoreDecorator
     */
    @Nonnull
    public B log(Level level) {
        withStore(PersistentStoreOptions.LOG);
        return withOption(PersistentResourceOptions.LOG_LEVEL, checkNotNull(level));
    }

    /**
     * Adds the {@code stats} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.StatsStoreDecorator
     */
    @Nonnull
    public B recordStats() {
        return withStore(PersistentStoreOptions.STATS);
    }
}
