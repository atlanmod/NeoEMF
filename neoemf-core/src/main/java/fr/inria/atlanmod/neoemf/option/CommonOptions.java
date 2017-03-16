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

package fr.inria.atlanmod.neoemf.option;

import fr.inria.atlanmod.neoemf.annotations.Experimental;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link PersistenceOptions} that holds common options.
 * <p>
 * <b>Note:</b> Not implemented yet.
 * <p>
 * <b>Note:</b> In a standard use, you should use the {@link PersistenceOptions} implementation corresponding to the
 * {@link fr.inria.atlanmod.neoemf.data.Backend} you want.
 * <p>
 * <b>Future:</b> This class is not used in the current release of the tool, it will simplify option management in the
 * near future.
 *
 * @see CommonOptionsBuilder
 */
@Experimental
@ParametersAreNonnullByDefault
public class CommonOptions extends AbstractPersistenceOptions {

    /**
     * Constructs a new {@code CommonOptions}.
     */
    protected CommonOptions() {
        super();
    }

    /**
     * Creates a new {@link Map} containing all default settings of {@code CommonOptions}.
     *
     * @return an immutable {@link Map}
     */
    @Nonnull
    public static Map<String, Object> noOption() {
        return new CommonOptionsBuilder().asMap();
    }

    /**
     * Constructs a new {@code CommonOptionsBuilder} instance.
     *
     * @return a new builder
     */
    @Nonnull
    public static CommonOptionsBuilder newBuilder() {
        return new CommonOptionsBuilder();
    }

    @Nonnull
    @Override
    public Map<String, Object> toMap() {
        return super.toMap();
    }

    @Override
    public void fromMap(Map<String, Object> options) {
        super.fromMap(options);
    }
}
