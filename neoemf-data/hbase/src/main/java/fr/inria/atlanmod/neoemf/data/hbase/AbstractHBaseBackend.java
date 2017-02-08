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
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 */
@ParametersAreNonnullByDefault
abstract class AbstractHBaseBackend extends AbstractPersistenceBackend implements HBaseBackend {

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
    public void create(Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean has(Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public Optional<ContainerValue> containerOf(Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void containerFor(Id id, ContainerValue container) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Nonnull
    @Override
    public Optional<MetaclassValue> metaclassOf(Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void metaclassFor(Id id, MetaclassValue metaclass) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
