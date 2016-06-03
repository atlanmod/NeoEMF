/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.resources;

import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;

public interface BlueprintsNeo4jResourceOptions extends BlueprintsResourceOptions {
    
    String OPTIONS_BLUEPRINTS_TYPE_NEO4J = "com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph";
    String OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE = "blueprints.neo4j.conf.cache_type";
    String OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS = "blueprints.neo4j.conf.use_memory_mapped_buffers";
    String OPTIONS_BLUEPRINTS_NEO4J_STRINGS_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.propertystore.db.strings.mapped_memory";
    String OPTIONS_BLUEPRINTS_NEO4J_ARRAYS_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.propertystore.db.arrays.mapped_memory";
    String OPTIONS_BLUEPRINTS_NEO4J_NODES_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.nodestore.db.mapped_memory";
    String OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.propertystore.db.mapped_memory";
    String OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.relationshipstore.db.mapped_memory";
    
    /**
     * Possible values for @{see OPTIONS_GRAPH_NEO4J_CACHE_TYPE}.
     * The cache type hpc is not available because the embedded Neo4j the Community Edition.
     */
    enum CacheType {
        NONE("none"),
        SOFT("soft"),
        WEAK("weak"),
        STRONG("strong");
        
        
        
        private final String stringValue;
        
        CacheType(String stringValue) {
            this.stringValue = stringValue;
        }
        
        @Override
        public String toString() {
            return stringValue;
        }
    }
    
    /**
     * Possible values for @{see OPTIONS_GRAPH_NEO4J_USE_MEMORY_MAPPED_BUFFER}.
     */
    enum UseMemoryMappedBuffer {
        TRUE("true"),
        FALSE("false");
        
        private final String stringValue;
        
        UseMemoryMappedBuffer(String stringValue) {
            this.stringValue = stringValue;
        }
        
        @Override
        public String toString() {
            return stringValue;
        }
    }
}
