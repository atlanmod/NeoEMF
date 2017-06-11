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

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A common {@link PersistenceOptions} that creates common options that are available for all back-end
 * implementations.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 * <p>
 * <b>Note:</b> In a standard use, you should use the {@link PersistenceOptions} implementation corresponding to the
 * {@link fr.inria.atlanmod.neoemf.data.Backend} you want.
 */
@ParametersAreNonnullByDefault
public class CommonOptions extends AbstractPersistenceOptions<CommonOptions> {

    /**
     * Constructs a new {@code CommonOptions}.
     */
    protected CommonOptions() {
    }

    /**
     * Creates a new {@link Map} containing all default settings of {@code CommonOptions}.
     *
     * @return an immutable {@link Map}
     */
    @Nonnull
    public static Map<String, Object> noOption() {
        return builder().asMap();
    }

    /**
     * Constructs a new {@code CommonOptions} instance.
     *
     * @return a new builder
     */
    @Nonnull
    public static CommonOptions builder() {
        return new CommonOptions();
    }
}
