package fr.inria.atlanmod.neoemf.option;

/**
 * {@link PersistentStoreOptions} that are available for all modules.
 */
public enum CommonStoreOptions implements PersistentStoreOptions {
    CACHE_IS_SET,
    CACHE_SIZE,
    CACHE_STRUCTURAL_FEATURE,
    LOG,
    COUNT_LOADED_OBJECT
}
