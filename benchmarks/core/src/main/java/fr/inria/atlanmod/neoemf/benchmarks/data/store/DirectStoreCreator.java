/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.data.store;

import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link StoreCreator} that uses a {@link fr.inria.atlanmod.neoemf.data.DataCopier}.
 * <p>
 * <b>WARNING:</b> Only for NeoEMF.
 */
@ParametersAreNonnullByDefault
public class DirectStoreCreator implements StoreCreator {

    /**
     *
     */
    private final Adapter.Internal adapter;

    /**
     * Constructs a new {@code DirectStoreCreator}.
     *
     * @param adapter
     */
    public DirectStoreCreator(Adapter.Internal adapter) {
        this.adapter = adapter;
    }

    @Override
    public void create(File resourceFile, URI uri, ImmutableConfig config) throws IOException {
        adapter.initAndGetEPackage();

        try (DataMapper mapper = adapter.createMapper(uri, config)) {
            Migrator.fromXmi(resourceFile).toMapper(mapper).migrate();
        }
    }
}
