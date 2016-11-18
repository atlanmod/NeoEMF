package fr.inria.atlanmod.neoemf.map.option;

import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;

public enum MapStoreOptions implements PersistentStoreOptions {
    AUTOCOMMIT,
    DIRECT_WRITE,
    DIRECT_WRITE_LISTS,
    DIRECT_WRITE_INDICES,
    CACHE_MANY
}
