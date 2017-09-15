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

import fr.inria.atlanmod.neoemf.bind.annotation.FactoryBinding;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link PersistenceOptions} that creates Blueprints TinkerGraph specific options.
 * <p>
 * This builder doesn't contain specific methods for now: the only {@code TinkerGraph} configuration supported is the
 * graph type, which is set by default.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 */
@FactoryBinding(BlueprintsBackendFactory.class)
@ParametersAreNonnullByDefault
public class BlueprintsOptions extends AbstractBlueprintsOptions<BlueprintsOptions> {

    /**
     * Constructs a new {@code BlueprintsOptions}.
     */
    protected BlueprintsOptions() {
        withIndices();
    }

    /**
     * Creates a new {@link Map} containing all default settings of {@code BlueprintsOptions}.
     *
     * @return an immutable {@link Map}
     */
    @Nonnull
    public static Map<String, Object> noOption() {
        return builder().asMap();
    }

    /**
     * Constructs a new {@code BlueprintsOptions} instance with default settings.
     *
     * @return a new builder
     */
    @Nonnull
    public static BlueprintsOptions builder() {
        return new BlueprintsOptions();
    }
}
