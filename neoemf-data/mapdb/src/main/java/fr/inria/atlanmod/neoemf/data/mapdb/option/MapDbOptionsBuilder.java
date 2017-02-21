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

import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;
import fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder;

import java.util.Map;

import javax.annotation.Nonnull;

/**
 * A {@link PersistenceOptionsBuilder} that creates MapDB specific options.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 */
public class MapDbOptionsBuilder extends AbstractPersistenceOptionsBuilder<MapDbOptionsBuilder, MapDbOptions> {

    /**
     * Constructs a new {@code MapDbOptionsBuilder}.
     * <p>
     * <b>Note:</b> This constructor is protected for API consistency purpose, to create a new builder use {@link
     * #newBuilder()}
     */
    protected MapDbOptionsBuilder() {
    }

    /**
     * Returns an immutable empty {@link Map}.
     *
     * @return an immutable {@link Map}
     */
    @Nonnull
    public static Map<String, Object> noOption() {
        return MapDbOptionsBuilder.newBuilder().asMap();
    }

    /**
     * Creates a new {@code MapDbOptionsBuilder}.
     *
     * @return a new builder
     */
    @Nonnull
    public static MapDbOptionsBuilder newBuilder() {
        return new MapDbOptionsBuilder();
    }
}
