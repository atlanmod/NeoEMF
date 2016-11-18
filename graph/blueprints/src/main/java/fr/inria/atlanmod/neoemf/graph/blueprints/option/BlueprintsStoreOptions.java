package fr.inria.atlanmod.neoemf.graph.blueprints.option;

import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;

public enum BlueprintsStoreOptions implements PersistentStoreOptions {
    AUTOCOMMIT,
    DIRECT_WRITE,
    CACHE_MANY
}
