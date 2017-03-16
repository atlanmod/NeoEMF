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

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link AbstractPersistenceOptionsBuilder} subclass that creates HBase specific options.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 */
@ParametersAreNonnullByDefault
public class HBaseOptionsBuilder extends AbstractPersistenceOptionsBuilder<HBaseOptionsBuilder, HBaseOptions> {

    /**
     * Constructs a new {@code HBaseOptionsBuilder}.
     */
    protected HBaseOptionsBuilder() {
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.hbase.HBaseBackend}.
     * <p>
     * This mapping corresponds to:
     * <ul>
     * <li>an {@link Object[]} representation of multi-valued attributes</li>
     * <li>a {@link String} representation for single-valued references</li>
     * <li>a {@link String[]} representation for multi-valued references</li>
     * </ul>
     * <p>
     * <b>Note:</b> This is the default mapping.
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.data.mapper.ReferenceWithString
     * @see fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithArrays
     * @see fr.inria.atlanmod.neoemf.data.mapper.ManyReferenceWithStrings
     */
    public HBaseOptionsBuilder withArraysAndStrings() {
        return mapping(HBaseResourceOptions.MAPPING_ARRAYS_STRINGS);
    }
}
