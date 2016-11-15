package fr.inria.atlanmod.neoemf.map.resource;

import fr.inria.atlanmod.neoemf.resource.AbstractPersistentResourceOptionsBuilder;

public class MapResourceOptionsBuilder extends AbstractPersistentResourceOptionsBuilder<MapResourceOptionsBuilder> {

    protected MapResourceOptionsBuilder() {
    }

    public MapResourceOptionsBuilder autocommit() {
        return storeOption(MapResourceOptions.EStoreMapOption.AUTOCOMMIT);
    }

    public MapResourceOptionsBuilder directWrite() {
        return storeOption(MapResourceOptions.EStoreMapOption.DIRECT_WRITE);
    }

    public MapResourceOptionsBuilder directWriteLists() {
        return storeOption(MapResourceOptions.EStoreMapOption.DIRECT_WRITE_LISTS);
    }

    public MapResourceOptionsBuilder directWriteIndices() {
        return storeOption(MapResourceOptions.EStoreMapOption.DIRECT_WRITE_INDICES);
    }

    public MapResourceOptionsBuilder directWriteCacheMany() {
        return storeOption(MapResourceOptions.EStoreMapOption.CACHE_MANY);
    }
}
