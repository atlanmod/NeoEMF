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

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithArrays;

import org.apache.hadoop.hbase.client.Table;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Mock {@link PersistenceBackend} implementation for HBase to fit core architecture.
 * <p>
 * This class does not access HBase database, but is here to fit the requirement of the core architecture. For
 * historical reasons the real access to the HBase Table is done in {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore}.
 * <p>
 * Moving HBase access to this class to fit NeoEMF back-end architecture is planned in a future release.
 *
 * @see fr.inria.atlanmod.neoemf.data.store.DirectWriteStore
 */
@ParametersAreNonnullByDefault
class HBaseBackendArrays extends AbstractHBaseBackend implements ManyValueWithArrays {

    /**
     * Constructs a new {@code HBaseBackendArrays} on th given {@code table}
     *
     * @param table the HBase table
     */
    protected HBaseBackendArrays(Table table) {
        super(table);
    }
}