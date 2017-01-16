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

import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.hbase.store.DirectWriteHBaseStore;
import fr.inria.atlanmod.neoemf.data.hbase.store.ReadOnlyHBaseStore;

/**
 * Mock {@link PersistenceBackend} implementation for HBase to fit core architecture.
 * <p>
 * This class does not access HBase database, but is here to fit the requirement of the
 * core architecture. For historical reasons the real access to the HBase Table 
 * is done in {@link DirectWriteHBaseStore} and {@link ReadOnlyHBaseStore}.
 * <p>
 * Moving HBase access to this class to fit NeoEMF backend architecture is planned in
 * a future release.
 * 
 * @see DirectWriteHBaseStore
 * @see ReadOnlyHBaseStore
 */
public class HBasePersistenceBackend extends AbstractPersistenceBackend {

    /**
     * The literal description of this back-end.
     */
    public static final String NAME = "hbase";

    /**
     * Constructs a new {@code HBasePersistenceBackend}.
     */
    protected HBasePersistenceBackend() {
    }

    @Override
    public boolean isClosed() {
        return true;
    }

    @Override
    public void close() {
    }

    @Override
    public void save() {
    }
}