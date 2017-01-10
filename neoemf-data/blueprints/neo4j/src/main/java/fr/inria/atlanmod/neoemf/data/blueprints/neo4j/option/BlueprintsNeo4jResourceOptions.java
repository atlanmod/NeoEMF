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

public interface BlueprintsNeo4jResourceOptions extends BlueprintsResourceOptions {

    String GRAPH_TYPE_NEO4J = "com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph";

    String CACHE_TYPE = "blueprints.neo4j.conf.cache_type";

    String USE_MEMORY_MAPPED_BUFFERS = "blueprints.neo4j.conf.use_memory_mapped_buffers";

    String STRINGS_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.propertystore.db.strings.mapped_memory";

    String ARRAYS_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.propertystore.db.arrays.mapped_memory";

    String NODES_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.nodestore.db.mapped_memory";

    String PROPERTIES_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.propertystore.db.mapped_memory";

    String RELATIONSHIPS_MAPPED_MEMORY = "blueprints.neo4j.conf.neostore.relationshipstore.db.mapped_memory";

    /**
     * Possible values for {@link #CACHE_TYPE}.
     * <p>
     * The cache type hpc is not available because the embedded Neo4j the Community Edition.
     */
    enum CacheType {
        NONE("none"),
        SOFT("soft"),
        WEAK("weak"),
        STRONG("strong");

        private final String value;

        CacheType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
