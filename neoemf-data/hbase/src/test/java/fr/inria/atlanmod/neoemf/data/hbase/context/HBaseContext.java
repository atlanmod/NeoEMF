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

package fr.inria.atlanmod.neoemf.data.hbase.context;

import fr.inria.atlanmod.neoemf.context.AbstractContext;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.HBaseBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptions;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import org.eclipse.emf.common.util.URI;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the HBase implementation.
 */
@ParametersAreNonnullByDefault
public abstract class HBaseContext extends AbstractContext {

    /**
     * Creates a new {@code HBaseContext} with a mapping with arrays and strings.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getWithArraysAndStrings() {
        return new HBaseContext() {
            @Nonnull
            @Override
            public PersistenceOptions optionsBuilder() {
                return HBaseOptions.builder().withArraysAndStrings();
            }
        };
    }

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
        return HBaseBackendFactory.getInstance();
    }

    @Nonnull
    @Override
    public URI createUri(URI uri) {
        return UriBuilder.forScheme(uriScheme()).fromServer(HBaseCluster.host(), HBaseCluster.port(), uri);
    }

    @Nonnull
    @Override
    public URI createUri(File file) {
        return UriBuilder.forScheme(uriScheme()).fromServer(HBaseCluster.host(), HBaseCluster.port(), file.getName());
    }
}
