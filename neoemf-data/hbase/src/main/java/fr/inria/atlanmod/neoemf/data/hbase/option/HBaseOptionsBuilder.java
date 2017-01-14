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

package fr.inria.atlanmod.neoemf.data.hbase.option;

import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;

import javax.annotation.Nonnull;

/**
 * ???
 * <p>
 * All features are all optional: options can be created using all or none of them.
 */
public class HBaseOptionsBuilder extends AbstractPersistenceOptionsBuilder<HBaseOptionsBuilder, HBaseOptions> {

    /**
     * Constructs a new {@code HBaseOptionsBuilder}.
     *
     * @note This constructor is protected for API consistency purpose, to create a new builder use {@link
     * #newBuilder()}
     */
    protected HBaseOptionsBuilder() {
    }

    /**
     * Constructs a new {@code HBaseOptionsBuilder} instance.
     *
     * @return a new builder
     */
    @Nonnull
    public static HBaseOptionsBuilder newBuilder() {
        return new HBaseOptionsBuilder();
    }

    /**
     * ???
     *
     * @return this builder (for chaining)
     */
    @Nonnull
    public HBaseOptionsBuilder readOnly() {
        return option(HBaseResourceOptions.READ_ONLY, true);
    }
}
