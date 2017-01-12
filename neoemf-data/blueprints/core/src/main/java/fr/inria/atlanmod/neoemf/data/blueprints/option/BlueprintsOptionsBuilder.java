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

package fr.inria.atlanmod.neoemf.data.blueprints.option;

import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

import javax.annotation.Nonnull;

/**
 * A specific {@link AbstractBlueprintsOptionsBuilder} that creates Blueprints TinkerGraph specific options.
 * <p>
 * This builder doesn't contain specific methods for now: the only {@link TinkerGraph} configuration
 * supported is the graph type, which is set by default.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 */
public class BlueprintsOptionsBuilder extends AbstractBlueprintsOptionsBuilder<BlueprintsOptionsBuilder, BlueprintsOptions> {

    /**
     * Instantiates a new {@code BlueprintsOptionsBuilder}.
     * <p>
     * This constructor is protected for API consistency purpose, to create a new builder use {@link #newBuilder()}.
     */
    protected BlueprintsOptionsBuilder() {
    }

    /**
     * Constructs a new {@code BlueprintsOptionsBuilder} instance.
     * @return a new builder
     */
    @Nonnull
    public static BlueprintsOptionsBuilder newBuilder() {
        return new BlueprintsOptionsBuilder();
    }
}
