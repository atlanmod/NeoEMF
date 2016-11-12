/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.hbase.datastore;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.hbase.datastore.estores.impl.DirectWriteHBaseResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.hbase.datastore.estores.impl.ReadOnlyHBaseResourceEStoreImpl;

/**
 * Dummy backend implementation for HBase to fit core architecture.
 * <p/>
 * The real access to the HBase Table is done in {@link DirectWriteHBaseResourceEStoreImpl} and
 * {@link ReadOnlyHBaseResourceEStoreImpl}.
 */
public class HBasePersistenceBackend implements PersistenceBackend {

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