package fr.inria.atlanmod.neoemf.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractPersistentResourceOptionsBuilder<B extends AbstractPersistentResourceOptionsBuilder<B>> {

    private final Map<String, Object> options;

    private final List<PersistentResourceOptions.StoreOption> storeOptions;

    protected AbstractPersistentResourceOptionsBuilder() {
        options = new HashMap<>();
        storeOptions = new ArrayList<>();
    }

    /**
     * Returns an immutable empty {@link Map}.
     */
    public static Map<String, Object> noOption() {
        return Collections.emptyMap();
    }

    /**
     * Returns a immutable {@link Map} containing all defined options.
     */
    public final Map<String, Object> asMap() {
        validate();

        if (!storeOptions.isEmpty()) {
            option(PersistentResourceOptions.STORE_OPTIONS, Collections.unmodifiableList(storeOptions));
        }
        return Collections.unmodifiableMap(options);
    }

    /**
     * Returns {@code true} if no conflict is detected, {@code false} otherwise.
     */
    protected boolean validate() {
        return true;
    }

    @SuppressWarnings("unchecked")
    private B me() {
        return (B) this;
    }

    protected B storeOption(PersistentResourceOptions.StoreOption storeOption) {
        this.storeOptions.add(storeOption);
        return me();
    }

    public B option(String key, Object value) {
        options.put(key, value);
        return me();
    }

    public B cacheIsSet() {
        return storeOption(PersistentResourceOptions.EStoreOption.CACHE_IS_SET);
    }

    public B cacheSizes() {
        return storeOption(PersistentResourceOptions.EStoreOption.CACHE_SIZE);
    }

    public B cacheFeatures() {
        return storeOption(PersistentResourceOptions.EStoreOption.CACHE_STRUCTURAL_FEATURE);
    }

    public B log() {
        return storeOption(PersistentResourceOptions.EStoreOption.LOG);
    }

    public B countLoadedObjects() {
        return storeOption(PersistentResourceOptions.EStoreOption.COUNT_LOADED_OBJECT);
    }
}
