package fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.resource;

import fr.inria.atlanmod.neoemf.graph.blueprints.resource.AbstractBlueprintsResourceOptionsBuilder;

public class BlueprintsNeo4jResourceOptionsBuilder extends AbstractBlueprintsResourceOptionsBuilder<BlueprintsNeo4jResourceOptionsBuilder> {

    protected BlueprintsNeo4jResourceOptionsBuilder() {
        graph(BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
    }

    protected BlueprintsNeo4jResourceOptionsBuilder cache(BlueprintsNeo4jResourceOptions.CacheType type) {
        return option(BlueprintsNeo4jResourceOptions.CACHE_TYPE, type);
    }

    public BlueprintsNeo4jResourceOptionsBuilder noCache() {
        return cache(BlueprintsNeo4jResourceOptions.CacheType.NONE);
    }

    public BlueprintsNeo4jResourceOptionsBuilder softCache() {
        return cache(BlueprintsNeo4jResourceOptions.CacheType.SOFT);
    }

    public BlueprintsNeo4jResourceOptionsBuilder weakCache() {
        return cache(BlueprintsNeo4jResourceOptions.CacheType.WEAK);
    }

    public BlueprintsNeo4jResourceOptionsBuilder strongCache() {
        return cache(BlueprintsNeo4jResourceOptions.CacheType.STRONG);
    }

    public BlueprintsNeo4jResourceOptionsBuilder memoryMappedBuffers() {
        return option(BlueprintsNeo4jResourceOptions.USE_MEMORY_MAPPED_BUFFERS, true);
    }

    public BlueprintsNeo4jResourceOptionsBuilder memoryMappedBuffers(boolean memoryBuffers) {
        return option(BlueprintsNeo4jResourceOptions.USE_MEMORY_MAPPED_BUFFERS, memoryBuffers);
    }

    public BlueprintsNeo4jResourceOptionsBuilder stringsMappedBuffer(String size) {
        return option(BlueprintsNeo4jResourceOptions.STRINGS_MAPPED_MEMORY, size);
    }

    public BlueprintsNeo4jResourceOptionsBuilder arraysMappedBuffer(String size) {
        return option(BlueprintsNeo4jResourceOptions.ARRAYS_MAPPED_MEMORY, size);
    }

    public BlueprintsNeo4jResourceOptionsBuilder nodesMappedBuffer(String size) {
        return option(BlueprintsNeo4jResourceOptions.NODES_MAPPED_MEMORY, size);
    }

    public BlueprintsNeo4jResourceOptionsBuilder propertiesMappedBuffer(String size) {
        return option(BlueprintsNeo4jResourceOptions.PROPERTIES_MAPPED_MEMORY, size);
    }

    public BlueprintsNeo4jResourceOptionsBuilder relationshipsMappedBuffer(String size) {
        return option(BlueprintsNeo4jResourceOptions.RELATIONSHIPS_MAPPED_MEMORY, size);
    }
}
