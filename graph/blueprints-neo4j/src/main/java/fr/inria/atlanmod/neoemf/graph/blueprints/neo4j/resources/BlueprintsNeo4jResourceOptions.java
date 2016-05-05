/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p>
 * Contributors:
 * Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.resources;

import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;

public interface BlueprintsNeo4jResourceOptions extends BlueprintsResourceOptions {
    
    public final static String OPTIONS_BLUEPRINTS_TYPE_NEO4J = "com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph";
    public final static String OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE = "blueprints.neo4j.conf.cache_type";
    public final static String OPTIONS_BLUEPRINTS_NEO4J_USE_MEMORY_MAPPED_BUFFERS = "blueprints.neo4j.conf.use_memory_mapped_buffers";
    public final static String OPTIONS_BLUEPRINTS_NEO4J_STRINGS_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.propertystore.db.strings.mapped_memory";
    public final static String OPTIONS_BLUEPRINTS_NEO4J_ARRAYS_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.propertystore.db.arrays.mapped_memory";
    public final static String OPTIONS_BLUEPRINTS_NEO4J_NODES_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.nodestore.db.mapped_memory";
    public final static String OPTIONS_BLUEPRINTS_NEO4J_PROPERTIES_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.propertystore.db.mapped_memory";
    public final static String OPTIONS_BLUEPRINTS_NEO4J_RELATIONSHIPS_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.relationshipstore.db.mapped_memory";
    
    /**
     * Possible values for @{see OPTIONS_GRAPH_NEO4J_CACHE_TYPE}
     * The cache type hpc is not available because the embedded Neo4j the Community Edition
     */
    public static enum CACHE_TYPE {
        NONE("none"),
        SOFT("soft"),
        WEAK("weak"),
        STRONG("strong");
        
        
        
        private final String stringValue;
        
        private CACHE_TYPE(String stringValue) {
            this.stringValue = stringValue;
        }
        
        @Override
        public String toString() {
            return this.stringValue;
        }
    }
    
    /**
     * Possible values for @{see OPTIONS_GRAPH_NEO4J_USE_MEMORY_MAPPED_BUFFER}
     */
    public static enum USE_MEMORY_MAPPED_BUFFER {
        TRUE("true"),
        FALSE("false");
        
        private final String stringValue;
        
        private USE_MEMORY_MAPPED_BUFFER(String stringValue) {
            this.stringValue = stringValue;
        }
        
        @Override
        public String toString() {
            return this.stringValue;
        }
    }
    
}
