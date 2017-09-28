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

import fr.inria.atlanmod.commons.log.Level;
import fr.inria.atlanmod.neoemf.bind.Bindings;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A builder of {@link Map} options managed by {@link fr.inria.atlanmod.neoemf.data.BackendFactory}.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 */
@ParametersAreNonnullByDefault
public interface PersistenceOptions {

    /**
     * Retrieves the instance of {@code PersistenceOptions} that is associated to a {@link
     * fr.inria.atlanmod.neoemf.data.BackendFactory} wearing the given {@code name}.
     *
     * @param name the name of the factory
     *
     * @return a new instance of {@code PersistenceOptions}
     */
    @Nonnull
    static PersistenceOptions forName(String name) {
        return Bindings.findBy(PersistenceOptions.class, name, Bindings::nameOf);
    }

    /**
     * Retrieves the instance of {@code PersistenceOptions} that is associated to a {@link
     * fr.inria.atlanmod.neoemf.util.UriBuilder} which use the given {@code scheme}.
     *
     * @param scheme the scheme of the builder
     *
     * @return a new instance of {@code PersistenceOptions}
     */
    @Nonnull
    static PersistenceOptions forScheme(String scheme) {
        return Bindings.findBy(PersistenceOptions.class, scheme, Bindings::schemeOf);
    }

    /**
     * Returns an immutable {@link Map} containing all defined options.
     *
     * @return an immutable {@link Map}
     *
     * @throws InvalidOptionException if a conflict is detected during building
     */
    @Nonnull
    Map<String, Object> asMap();

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
    PersistenceOptions withOption(String key, Object value);

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
    PersistenceOptions withOptions(Map<String, Object> options);

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.Backend}. If the mapping has
     * already been defined, then it will be erased by the new.
     *
     * @param mapping the class name of the mapping to use
     *
     * @return this builder (for chaining)
     *
     * @throws NullPointerException   if the {@code mapping} is {@code null}
     * @throws InvalidOptionException if the {@code mapping} does not represent a {@link DataMapper} instance, or if the
     *                                associated class cannot be found
     */
    @Nonnull
    PersistenceOptions withMapping(String mapping);

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.Backend}. If the mapping has
     * already been defined, then it will be erased by the new.
     *
     * @param mapping the class of the mapping to use
     *
     * @return this builder (for chaining)
     *
     * @throws NullPointerException if the {@code mapping} is {@code null}
     */
    @Nonnull
    PersistenceOptions withMapping(Class<? extends DataMapper> mapping);

    /**
     * Adds the {@code cache-features} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.FeatureCacheStore
     */
    @Nonnull
    PersistenceOptions cacheFeatures();

    /**
     * Adds the {@code cache-containers} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.ContainerCacheStore
     */
    @Nonnull
    PersistenceOptions cacheContainers();

    /**
     * Adds the {@code cache-metaclasses} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.ClassCacheStore
     */
    @Nonnull
    PersistenceOptions cacheMetaClasses();

    /**
     * Adds the {@code cache-sizes} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.SizeCacheStore
     */
    @Nonnull
    PersistenceOptions cacheSizes();

    /**
     * Adds the {@code read-only} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.ReadOnlyStore
     */
    @Nonnull
    PersistenceOptions readOnly();

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
     * @see fr.inria.atlanmod.neoemf.data.store.AutoSaveStore
     */
    @Nonnull
    PersistenceOptions autoSave(@Nonnegative long chunk);

    /**
     * Adds the {@code autoSave} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.AutoSaveStore
     */
    @Nonnull
    PersistenceOptions autoSave();

    /**
     * Adds the {@code log} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.LogStore
     */
    @Nonnull
    PersistenceOptions log();

    /**
     * Adds the {@code log} feature in the created options.
     *
     * @param level the logging {@link Level} to use
     *
     * @return this builder (for chaining)
     *
     * @throws NullPointerException if the {@code level} is {@code null}
     * @see fr.inria.atlanmod.neoemf.data.store.LogStore
     */
    @Nonnull
    PersistenceOptions log(Level level);

    /**
     * Adds the {@code stats} feature in the created options.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.store.StatRecordStore
     */
    @Nonnull
    PersistenceOptions recordStats();
}
