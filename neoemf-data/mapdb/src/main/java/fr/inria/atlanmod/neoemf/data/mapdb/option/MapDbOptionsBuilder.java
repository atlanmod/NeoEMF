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

package fr.inria.atlanmod.neoemf.data.mapdb.option;

import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;
import fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link PersistenceOptionsBuilder} that creates MapDB specific options.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 */
@ParametersAreNonnullByDefault
public class MapDbOptionsBuilder extends AbstractPersistenceOptionsBuilder<MapDbOptionsBuilder, MapDbOptions> {

    /**
     * Constructs a new {@code MapDbOptionsBuilder}.
     */
    protected MapDbOptionsBuilder() {
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackend}.
     * <p>
     * This mapping corresponds to a simple representation of multi-valued features, by using the {@link
     * ManyFeatureKey#position()}.
     * <p>
     * <b>Note:</b> This is the default mapping.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithIndices
     */
    public MapDbOptionsBuilder withIndices() {
        return mapping(MapDbResourceOptions.MAPPING_INDICES);
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackend}.
     * <p>
     * This mapping corresponds to an {@link Object[]} representation of multi-valued features.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithArrays
     */
    public MapDbOptionsBuilder withArrays() {
        return mapping(MapDbResourceOptions.MAPPING_ARRAYS);
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackend}.
     * <p>
     * This mapping corresponds to a {@link java.util.List} representation of multi-valued features.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithLists
     */
    public MapDbOptionsBuilder withLists() {
        return mapping(MapDbResourceOptions.MAPPING_LISTS);
    }
}
