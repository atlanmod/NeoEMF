package fr.inria.atlanmod.neoemf.map.option;

import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;

public class MapOptionsBuilder extends AbstractPersistenceOptionsBuilder<MapOptionsBuilder> {

    protected MapOptionsBuilder() {
    }

    public static MapOptionsBuilder newBuilder() {
        return new MapOptionsBuilder();
    }

    public MapOptionsBuilder autocommit() {
        return storeOption(MapStoreOptions.AUTOCOMMIT);
    }

    public MapOptionsBuilder directWrite() {
        return storeOption(MapStoreOptions.DIRECT_WRITE);
    }

    public MapOptionsBuilder directWriteLists() {
        return storeOption(MapStoreOptions.DIRECT_WRITE_LISTS);
    }

    public MapOptionsBuilder directWriteIndices() {
        return storeOption(MapStoreOptions.DIRECT_WRITE_INDICES);
    }

    public MapOptionsBuilder directWriteCacheMany() {
        return storeOption(MapStoreOptions.CACHE_MANY);
    }
}
