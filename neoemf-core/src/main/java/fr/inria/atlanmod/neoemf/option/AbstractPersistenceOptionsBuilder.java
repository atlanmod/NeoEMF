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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractPersistenceOptionsBuilder<B extends AbstractPersistenceOptionsBuilder<B, O>, O extends AbstractPersistenceOptions> {

    private final Map<String, Object> options;

    private final List<PersistentStoreOptions> storeOptions;

    protected AbstractPersistenceOptionsBuilder() {
        this.options = new HashMap<>();
        this.storeOptions = new ArrayList<>();
    }

    /**
     * Returns an immutable empty {@link Map}.
     */
    public static Map<String, Object> noOption() {
        return Collections.emptyMap();
    }

    /**
     * Returns a immutable {@link Map} containing all defined options.
     *
     * @throws InvalidOptionException if a conflit is detected during building
     */
    public final Map<String, Object> asMap() throws InvalidOptionException {
        validate();

        if (!storeOptions.isEmpty()) {
            option(PersistentResourceOptions.STORE_OPTIONS, Collections.unmodifiableList(storeOptions));
        }
        return Collections.unmodifiableMap(options);
    }

    /**
     * Validates the defined options, and checks if there is conflit between them.
     *
     * @throws InvalidOptionException if a conflit is detected
     */
    protected void validate() throws InvalidOptionException {
        // Do nothing, for now
    }

    @SuppressWarnings("unchecked")
    private B me() {
        return (B) this;
    }

    protected B storeOption(PersistentStoreOptions storeOption) {
        this.storeOptions.add(storeOption);
        return me();
    }

    public B option(String key, Object value) {
        options.put(key, value);
        return me();
    }

    public B cacheIsSet() {
        return storeOption(CommonStoreOptions.CACHE_IS_SET);
    }

    public B cacheSizes() {
        return storeOption(CommonStoreOptions.CACHE_SIZE);
    }

    public B cacheFeatures() {
        return storeOption(CommonStoreOptions.CACHE_STRUCTURAL_FEATURE);
    }

    public B log() {
        return storeOption(CommonStoreOptions.LOG);
    }

    public B countLoadedObjects() {
        return storeOption(CommonStoreOptions.COUNT_LOADED_OBJECT);
    }
}
