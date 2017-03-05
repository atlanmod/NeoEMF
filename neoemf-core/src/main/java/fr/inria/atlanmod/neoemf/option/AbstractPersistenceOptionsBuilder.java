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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An abstract {@link PersistenceOptionsBuilder} that manages the assembly and the construction of
 * {@link PersistenceOptions}.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 *
 * @param <B> the "self"-type of this {@link PersistenceOptionsBuilder}
 * @param <O> the type of {@link PersistenceOptions} built by this builder
 */
@ParametersAreNonnullByDefault
public abstract class AbstractPersistenceOptionsBuilder<B extends AbstractPersistenceOptionsBuilder<B, O>, O extends AbstractPersistenceOptions> implements PersistenceOptionsBuilder {

    /**
     * Map that holds all defined key/value options in this builder.
     */
    @Nonnull
    private final Map<String, Object> options;

    /**
     * List that holds all defined store options in this builder.
     */
    @Nonnull
    private final List<PersistentStoreOptions> storeOptions;

    /**
     * Constructs a new {@code AbstractPersistenceOptionsBuilder}.
     */
    protected AbstractPersistenceOptionsBuilder() {
        this.options = new HashMap<>();
        this.storeOptions = new ArrayList<>();
    }

    @Nonnull
    @Override
    public final Map<String, Object> asMap() throws InvalidOptionException {
        validate();

        if (!storeOptions.isEmpty()) {
            option(PersistentResourceOptions.STORE_OPTIONS, Collections.unmodifiableList(storeOptions));
        }
        return Collections.unmodifiableMap(options);
    }

    /**
     * Validates the defined options, and checks if there is conflict between them.
     *
     * @throws InvalidOptionException if a conflict is detected
     */
    protected void validate() throws InvalidOptionException {
        // Do nothing, for now
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
     * Adds a feature defined by the given {@code storeOption} in the created options.
     *
     * @param storeOption the option to add
     *
     * @return this builder (for chaining)
     */
    @Nonnull
    protected B storeOption(@Nonnull PersistentStoreOptions storeOption) {
        checkNotNull(storeOption);

        this.storeOptions.add(storeOption);
        return me();
    }

    /**
     * Adds a key/value in the created options. A custom configuration, which is not part of NeoEMF, can be added.
     *
     * @param key   the key to add
     * @param value the value of the {@code key}
     *
     * @return this builder (for chaining)
     */
    @Nonnull
    public B option(@Nonnull String key, @Nonnull Object value) {
        checkNotNull(key);
        checkNotNull(value);

        options.put(key, value);
        return me();
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
        return storeOption(CommonStoreOptions.CACHE_IS_SET);
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
        return storeOption(CommonStoreOptions.CACHE_SIZE);
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
        return storeOption(CommonStoreOptions.CACHE_STRUCTURAL_FEATURE);
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
        return storeOption(CommonStoreOptions.LOG);
    }

    /**
     * Adds the {@code log} feature in the created options.
     *
     * @param level the logging {@link Level} to use
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.LoggingStoreDecorator
     */
    public B log(Level level) {
        storeOption(CommonStoreOptions.LOG);
        return option(CommonResourceOptions.LOGGING_LEVEL, level.name());
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
        return storeOption(CommonStoreOptions.COUNT_LOADED_OBJECT);
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
        return storeOption(CommonStoreOptions.READ_ONLY);
    }

    /**
     * Adds the {@code autocommit} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator
     */
    @Nonnull
    public B autocommit() {
        return storeOption(CommonStoreOptions.AUTOCOMMIT);
    }

    /**
     * Adds the {@code autocommit} feature with the given {@code chunk} size in the created options.
     *
     * @param chunk the number of database operations between each commit
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator
     */
    @Nonnull
    public B autocommit(@Nonnegative long chunk) {
        checkArgument(chunk >= 0);

        storeOption(CommonStoreOptions.AUTOCOMMIT);
        return option(CommonResourceOptions.AUTOCOMMIT_CHUNK, Long.toString(chunk));
    }
}
