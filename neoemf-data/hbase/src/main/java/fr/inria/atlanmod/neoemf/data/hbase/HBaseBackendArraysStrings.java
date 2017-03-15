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

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapper.ManyReferenceWithStrings;
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithArrays;
import fr.inria.atlanmod.neoemf.data.mapper.ReferenceWithString;

import org.apache.hadoop.hbase.client.Table;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link HBaseBackend} that use a {@link ManyValueWithArrays} mapping for storing attributes and
 * {@link ReferenceWithString}/{@link ManyReferenceWithStrings} mappings for storing references.
 *
 * @see HBaseBackendFactory
 */
@ParametersAreNonnullByDefault
class HBaseBackendArraysStrings extends AbstractHBaseBackend implements ManyValueWithArrays, ReferenceWithString, ManyReferenceWithStrings {

    /**
     * Constructs a new {@code HBaseBackendArrays} on th given {@code table}
     * <p>
     * <b>Note:</b> This constructor is protected. To create a new {@link HBaseBackend} use {@link
     * BackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, java.util.Map)}.
     *
     * @param table the HBase table
     */
    protected HBaseBackendArraysStrings(Table table) {
        super(table);
    }
}