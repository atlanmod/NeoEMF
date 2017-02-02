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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.hbase.store.DirectWriteHBaseStore;
import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

/**
 * Mock {@link PersistenceBackend} implementation for HBase to fit core architecture.
 * <p>
 * This class does not access HBase database, but is here to fit the requirement of the core architecture. For
 * historical reasons the real access to the HBase Table is done in {@link DirectWriteHBaseStore}.
 * <p>
 * Moving HBase access to this class to fit NeoEMF back-end architecture is planned in a future release.
 *
 * @see DirectWriteHBaseStore
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
    public void save() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isClosed() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isDistributed() {
        return true;
    }

    @Override
    public ContainerInfo containerFor(Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void storeContainer(Id id, ContainerInfo container) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ClassInfo metaclassFor(Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void storeMetaclass(Id id, ClassInfo metaclass) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Object storeValue(FeatureKey key, Object value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Object valueOf(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Object removeFeature(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isFeatureSet(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Object storeValueAtIndex(MultivaluedFeatureKey key, Object value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Object removeFeatureAtIndex(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isFeatureSetAtIndex(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int sizeOf(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Object valueAtIndex(MultivaluedFeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}