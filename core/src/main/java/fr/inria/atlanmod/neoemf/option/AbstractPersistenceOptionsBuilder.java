package fr.inria.atlanmod.neoemf.option;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractPersistenceOptionsBuilder<B extends AbstractPersistenceOptionsBuilder<B>> {

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
