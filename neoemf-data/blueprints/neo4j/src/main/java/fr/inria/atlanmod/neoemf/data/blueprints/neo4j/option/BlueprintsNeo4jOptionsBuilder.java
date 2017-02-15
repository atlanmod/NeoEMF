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

import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jResourceOptions.CacheType;
import fr.inria.atlanmod.neoemf.data.blueprints.option.AbstractBlueprintsOptionsBuilder;

import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * A specific {@link AbstractBlueprintsOptionsBuilder} that creates Blueprints Neo4j specific options.
 * <p>
 * This builder contains methods to set the Neo4j cache type, the low-level buffer sizes, and the use of memory mapped
 * files. The graph type is automatically set to {@link BlueprintsNeo4jResourceOptions#GRAPH_TYPE_NEO4J} in the builder
 * constructor.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 *
 * @see BlueprintsNeo4jResourceOptions
 */
public class BlueprintsNeo4jOptionsBuilder extends AbstractBlueprintsOptionsBuilder<BlueprintsNeo4jOptionsBuilder, BlueprintsNeo4jOptions> {

    /**
     * Constructs a new {@code BlueprintsNeo4jOptionsBuilder} and sets the graph type to
     * {@link BlueprintsNeo4jResourceOptions#GRAPH_TYPE_NEO4J}.
     *
     * @note This constructor is protected for API consistency purpose, to create a new builder use {@link
     * #newBuilder()}
     */
    protected BlueprintsNeo4jOptionsBuilder() {
        graph(BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
    }

    /**
     * Returns an immutable empty {@link Map}.
     *
     * @return an immutable {@link Map}
     */
    @Nonnull
    public static Map<String, Object> noOption() {
        return BlueprintsNeo4jOptionsBuilder.newBuilder().asMap();
    }

    /**
     * Constructs a new {@code BlueprintsNeo4jOptionsBuilder} instance.
     *
     * @return a new builder
     */
    @Nonnull
    public static BlueprintsNeo4jOptionsBuilder newBuilder() {
        return new BlueprintsNeo4jOptionsBuilder();
    }

    /**
     * Formats a {@code size} with the megabytes suffix.
     *
     * @param size the size to format
     *
     * @return the formatted size
     */
    private static String formatSize(int size) {
        return size + "M";
    }

    /**
     * Adds the {@code cache type} feature in the created options.
     *
     * @param type the {@link CacheType} to set
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsNeo4jResourceOptions.CacheType
     */
    @Nonnull
    protected BlueprintsNeo4jOptionsBuilder cache(BlueprintsNeo4jResourceOptions.CacheType type) {
        return option(BlueprintsNeo4jResourceOptions.CACHE_TYPE, type);
    }

    /**
     * Adds the {@code no cache} feature in the created options. This tells Neo4j to disable all its caches.
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsNeo4jResourceOptions.CacheType#NONE
     */
    @Nonnull
    public BlueprintsNeo4jOptionsBuilder noCache() {
        return cache(BlueprintsNeo4jResourceOptions.CacheType.NONE);
    }

    /**
     * Adds the {@code soft cache} feature in the created options. This tells Neo4j to use a cache containing soft
     * values.
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsNeo4jResourceOptions.CacheType#SOFT
     */
    @Nonnull
    public BlueprintsNeo4jOptionsBuilder softCache() {
        return cache(BlueprintsNeo4jResourceOptions.CacheType.SOFT);
    }

    /**
     * Adds the {@code weak cache} feature in the created options. This tells Neo4j to use a cache containing weak
     * values.
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsNeo4jResourceOptions.CacheType#WEAK
     */
    @Nonnull
    public BlueprintsNeo4jOptionsBuilder weakCache() {
        return cache(BlueprintsNeo4jResourceOptions.CacheType.WEAK);
    }

    /**
     * Adds the {@code strong cache} feature in the created options. This tells Neo4j to use a cache containing string
     * references.
     *
     * @return this builder (for chaining)
     *
     * @note Using this option the created cache cannot be garbage collected, and {@link OutOfMemoryError} can occur if
     * there is not enough memory to handle the model
     * @see BlueprintsNeo4jResourceOptions.CacheType#STRONG
     */
    @Nonnull
    public BlueprintsNeo4jOptionsBuilder strongCache() {
        return cache(BlueprintsNeo4jResourceOptions.CacheType.STRONG);
    }

    /**
     * Adds the {@code memory mapped buffer} feature in the created options. If the operating system supports memory
     * mapped files Neo4j will use them to speed up read/write operations.
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsNeo4jResourceOptions#USE_MEMORY_MAPPED_BUFFERS
     */
    @Nonnull
    public BlueprintsNeo4jOptionsBuilder memoryMappedBuffers() {
        return option(BlueprintsNeo4jResourceOptions.USE_MEMORY_MAPPED_BUFFERS, true);
    }

    /**
     * Adds the {@code memory mapped buffer} feature to the given value in the created options.
     *
     * @param memoryBuffers true to enable {@code memory mapped buffers}, false otherwise
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsNeo4jResourceOptions#USE_MEMORY_MAPPED_BUFFERS
     */
    @Nonnull
    public BlueprintsNeo4jOptionsBuilder memoryMappedBuffers(boolean memoryBuffers) {
        return option(BlueprintsNeo4jResourceOptions.USE_MEMORY_MAPPED_BUFFERS, memoryBuffers);
    }

    /**
     * Adds the {@code string mapped memory} property in the created options. This tells Neo4j the size of the buffer to
     * allocate to handle strings.
     *
     * @param size the size of the buffer in megabytes
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsNeo4jResourceOptions#STRINGS_MAPPED_MEMORY
     */
    @Nonnull
    public BlueprintsNeo4jOptionsBuilder stringsMappedBuffer(@Nonnegative int size) {
        return option(BlueprintsNeo4jResourceOptions.STRINGS_MAPPED_MEMORY, formatSize(size));
    }

    /**
     * Adds the {@code arrays mapped memory} property in the created options. This tells Neo4j the size of the buffer to
     * allocate to handle arrays.
     *
     * @param size the size of the buffer in megabytes
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsNeo4jResourceOptions#ARRAYS_MAPPED_MEMORY
     */
    @Nonnull
    public BlueprintsNeo4jOptionsBuilder arraysMappedBuffer(@Nonnegative int size) {
        return option(BlueprintsNeo4jResourceOptions.ARRAYS_MAPPED_MEMORY, formatSize(size));
    }

    /**
     * Adds the {@code node mapped memory} property in the created options. This tells Neo4j the size of the buffer to
     * allocate to handle nodes.
     *
     * @param size the size of the buffer in megabytes
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsNeo4jResourceOptions#NODES_MAPPED_MEMORY
     */
    @Nonnull
    public BlueprintsNeo4jOptionsBuilder nodesMappedBuffer(@Nonnegative int size) {
        return option(BlueprintsNeo4jResourceOptions.NODES_MAPPED_MEMORY, formatSize(size));
    }

    /**
     * Adds the {@code property mapped memory} property in the created options. This tells Neo4j the size of the buffer
     * to allocate to handle properties.
     *
     * @param size the size of the buffer in megabytes
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsNeo4jResourceOptions#PROPERTIES_MAPPED_MEMORY
     */
    @Nonnull
    public BlueprintsNeo4jOptionsBuilder propertiesMappedBuffer(@Nonnegative int size) {
        return option(BlueprintsNeo4jResourceOptions.PROPERTIES_MAPPED_MEMORY, formatSize(size));
    }

    /**
     * Adds the {@code relationship mapped memory} property in the created options. This tells Neo4j the size of the
     * buffer to allocate to handle properties.
     *
     * @param size the size of the buffer in megabytes
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsNeo4jResourceOptions#RELATIONSHIPS_MAPPED_MEMORY
     */
    @Nonnull
    public BlueprintsNeo4jOptionsBuilder relationshipsMappedBuffer(@Nonnegative int size) {
        return option(BlueprintsNeo4jResourceOptions.RELATIONSHIPS_MAPPED_MEMORY, formatSize(size));
    }
}
