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

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.option.InvalidOptionException;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link PersistenceOptions} that holds Blueprints TinkerGraph specific options.
 * <p>
 * <b>Note:</b> Not implemented yet.
 * <p>
 * <b>Future:</b> This class is not used in the current release of the tool, it will simplify option management in the
 * near future.
 *
 * @see BlueprintsOptionsBuilder
 */
@Experimental
@ParametersAreNonnullByDefault
public class BlueprintsOptions extends AbstractBlueprintsOptions {

    /**
     * Constructs a new {@code BlueprintsOptions}.
     */
    protected BlueprintsOptions() {
        super();
    }

    /**
     * Creates a new {@link Map} containing all default settings of {@code BlueprintsOptions}.
     *
     * @return an immutable {@link Map}
     */
    @Nonnull
    public static Map<String, Object> noOption() {
        return new BlueprintsOptionsBuilder().asMap();
    }

    /**
     * Constructs a new {@link BlueprintsOptionsBuilder} instance with default settings.
     *
     * @return a new builder
     */
    @Nonnull
    public static BlueprintsOptionsBuilder newBuilder() {
        return new BlueprintsOptionsBuilder();
    }

    @Override
    public Map<String, Object> toMap() throws InvalidOptionException {
        return super.toMap();
    }

    @Override
    public void fromMap(Map<?, ?> options) {
        super.fromMap(options);
    }
}
