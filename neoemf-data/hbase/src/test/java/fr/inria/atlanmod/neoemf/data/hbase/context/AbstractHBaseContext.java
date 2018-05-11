/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.hbase.context;

import fr.inria.atlanmod.neoemf.context.AbstractRemoteContext;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.HBaseBackendFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the HBase implementation.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractHBaseContext extends AbstractRemoteContext {

    @Override
    public boolean isInitialized() {
        return HBaseCluster.isInitialized();
    }

    @Override
    public Context init() {
        HBaseCluster.init();
        return this;
    }

    @Nonnull
    @Override
    public String name() {
        return "HBase";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return new HBaseBackendFactory();
    }

    @Nonnull
    @Override
    protected String getHost() {
        return HBaseCluster.host();
    }

    @Override
    protected int getPort() {
        return HBaseCluster.port();
    }
}
