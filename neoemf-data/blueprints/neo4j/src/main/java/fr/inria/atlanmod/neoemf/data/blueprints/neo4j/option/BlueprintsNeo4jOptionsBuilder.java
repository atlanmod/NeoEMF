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

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option;

import fr.inria.atlanmod.neoemf.data.blueprints.option.AbstractBlueprintsOptionsBuilder;

public class BlueprintsNeo4jOptionsBuilder extends AbstractBlueprintsOptionsBuilder<BlueprintsNeo4jOptionsBuilder, BlueprintsNeo4jOptions> {

    protected BlueprintsNeo4jOptionsBuilder() {
        graph(BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
    }

    public static BlueprintsNeo4jOptionsBuilder newBuilder() {
        return new BlueprintsNeo4jOptionsBuilder();
    }

    protected BlueprintsNeo4jOptionsBuilder cache(BlueprintsNeo4jResourceOptions.CacheType type) {
        return option(BlueprintsNeo4jResourceOptions.CACHE_TYPE, type);
    }

    public BlueprintsNeo4jOptionsBuilder noCache() {
        return cache(BlueprintsNeo4jResourceOptions.CacheType.NONE);
    }

    public BlueprintsNeo4jOptionsBuilder softCache() {
        return cache(BlueprintsNeo4jResourceOptions.CacheType.SOFT);
    }

    public BlueprintsNeo4jOptionsBuilder weakCache() {
        return cache(BlueprintsNeo4jResourceOptions.CacheType.WEAK);
    }

    public BlueprintsNeo4jOptionsBuilder strongCache() {
        return cache(BlueprintsNeo4jResourceOptions.CacheType.STRONG);
    }

    public BlueprintsNeo4jOptionsBuilder memoryMappedBuffers() {
        return option(BlueprintsNeo4jResourceOptions.USE_MEMORY_MAPPED_BUFFERS, true);
    }

    public BlueprintsNeo4jOptionsBuilder memoryMappedBuffers(boolean memoryBuffers) {
        return option(BlueprintsNeo4jResourceOptions.USE_MEMORY_MAPPED_BUFFERS, memoryBuffers);
    }

    public BlueprintsNeo4jOptionsBuilder stringsMappedBuffer(String size) {
        return option(BlueprintsNeo4jResourceOptions.STRINGS_MAPPED_MEMORY, size);
    }

    public BlueprintsNeo4jOptionsBuilder arraysMappedBuffer(String size) {
        return option(BlueprintsNeo4jResourceOptions.ARRAYS_MAPPED_MEMORY, size);
    }

    public BlueprintsNeo4jOptionsBuilder nodesMappedBuffer(String size) {
        return option(BlueprintsNeo4jResourceOptions.NODES_MAPPED_MEMORY, size);
    }

    public BlueprintsNeo4jOptionsBuilder propertiesMappedBuffer(String size) {
        return option(BlueprintsNeo4jResourceOptions.PROPERTIES_MAPPED_MEMORY, size);
    }

    public BlueprintsNeo4jOptionsBuilder relationshipsMappedBuffer(String size) {
        return option(BlueprintsNeo4jResourceOptions.RELATIONSHIPS_MAPPED_MEMORY, size);
    }
}
