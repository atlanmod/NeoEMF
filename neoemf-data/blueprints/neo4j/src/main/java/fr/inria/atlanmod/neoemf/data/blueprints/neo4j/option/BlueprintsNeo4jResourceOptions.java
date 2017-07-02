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

import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * {@link fr.inria.atlanmod.neoemf.option.PersistentResourceOptions} that hold Blueprints Neo4j related resource-level
 * features, such as cache type, usage of memory mapped files, or internal buffer sizes.
 */
@ParametersAreNonnullByDefault
public interface BlueprintsNeo4jResourceOptions extends BlueprintsResourceOptions {

    /**
     * The option value to define {@code Neo4jGraph} as the graph implementation to use.
     */
    String GRAPH_TYPE_NEO4J = "com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph";

    /**
     * The option key to define the cache type used by Neo4j.
     *
     * @see CacheType
     */
    String CACHE_TYPE = "blueprints.neo4j.conf.cache_type";

    /**
     * The option key to enable/disable the usage of memory mapped files.
     * <p>
     * If set to {@code true} Neo4j will use the operating systems memory mapping functionality for the file buffer
     * cache windows. If set to false Neo4j will use its own buffer implementation. In this case the buffers will reside
     * in the JVM heap which needs to be increased accordingly. The default value for this parameter is {@code true},
     * except on Windows.
     */
    String USE_MEMORY_MAPPED_BUFFERS = "blueprints.neo4j.conf.use_memory_mapped_buffers";

    /**
     * The option key to set the maximum amount of memory to use for the file buffer cache of the string property
     * storage file.
     */
    String STRINGS_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.propertystore.db.strings.mapped_memory";

    /**
     * The option key to set the maximum amount of memory to use for the file buffer cache of the array property storage
     * file.
     */
    String ARRAYS_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.propertystore.db.arrays.mapped_memory";

    /**
     * The option key to set the maximum amount of memory to use for the file buffer cache of the node storage file.
     */
    String NODES_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.nodestore.db.mapped_memory";

    /**
     * The option key to set the maximum amount of memory to use for the file buffer cache of the property storage file.
     */
    String PROPERTIES_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.propertystore.db.mapped_memory";

    /**
     * The option key to set the maximum amount of memory to use for the file buffer cache of the relationship store
     * file.
     */
    String RELATIONSHIPS_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.relationshipstore.db.mapped_memory";

    /**
     * Possible values for {@link #CACHE_TYPE}.
     * <p>
     * The cache type "hpc" is not available because the embedded Neo4j is the Community Edition.
     */
    enum CacheType {

        /**
         * Do not use a high level cache. No objects will be cached.
         */
        NONE("none"),

        /**
         * Provides optimal utilization of the available memory. Suitable for high performance traversal. May run into
         * GC issues under high load if the frequently accessed parts of the graph does not fit in the cache.
         * <p>
         * This is the default cache implementation.
         */
        SOFT("soft"),

        /**
         * Provides short life span for cached objects. Suitable for high throughput applications where a larger portion
         * of the graph than what can fit into memory is frequently accessed.
         */
        WEAK("weak"),

        /**
         * This cache will cache all data in the entire graph. It will never release memory held by the cache. Provides
         * optimal performance if your graph is small enough to fit in memory.
         */
        STRONG("strong");

        /**
         * The value of the property.
         */
        private final String value;

        /**
         * Constructs a new {@code CacheType} with its {@code value}.
         *
         * @param value the value of the property
         */
        CacheType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
