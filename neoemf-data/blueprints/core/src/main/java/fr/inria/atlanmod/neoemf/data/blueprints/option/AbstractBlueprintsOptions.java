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

import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptions;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link fr.inria.atlanmod.neoemf.option.PersistenceOptions} that provides utility methods to create
 * generic Blueprints options.
 * <p>
 * Created options can be {@link BlueprintsResourceOptions} if they define resource-level features.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 *
 * @param <B> the "self"-type of this {@link fr.inria.atlanmod.neoemf.option.PersistenceOptions}
 *
 * @see BlueprintsResourceOptions
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBlueprintsOptions<B extends AbstractBlueprintsOptions<B>> extends AbstractPersistenceOptions<B> {

    /**
     * Constructs a new {@code AbstractBlueprintsOptions}.
     */
    protected AbstractBlueprintsOptions() {
    }

    /**
     * Adds the given {@code graphType} in the created options.
     *
     * @param graphType the type of the Blueprints graph
     *
     * @return this builder (for chaining)
     *
     * @see BlueprintsResourceOptions#GRAPH_TYPE
     */
    protected B graph(String graphType) {
        return withOption(BlueprintsResourceOptions.GRAPH_TYPE, graphType);
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackend}.
     * <p>
     * This mapping corresponds to a simple representation of multi-valued features, by using the {@link
     * fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean#position()}.
     * <p>
     * <b>Note:</b> This is the default mapping.
     *
     * @return this builder (for chaining)
     */
    public B withIndices() {
        return withMapping("fr.inria.atlanmod.neoemf.data.blueprints.DefaultBlueprintsBackend");
    }
}
