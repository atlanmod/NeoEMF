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

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.HBaseBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptions;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseUri;

import org.eclipse.emf.common.util.URI;

import java.io.File;

/**
 * A specific {@link Context} for the HBase implementation.
 */
@FunctionalInterface
public interface HBaseContext extends Context {

    /**
     * Creates a new {@code HBaseContext} with a mapping with arrays and strings.
     *
     * @return a new context.
     */
    static Context getWithArraysAndStrings() {
        return (HBaseContext) () -> HBaseOptions.builder().withArraysAndStrings();
    }

    @Override
    default boolean isInitialized() {
        return HBaseCluster.isInitialized();
    }

    @Override
    default void init() {
        HBaseCluster.init();
    }

    @Override
    default String name() {
        return "HBase";
    }

    @Override
    default BackendFactory factory() {
        return HBaseBackendFactory.getInstance();
    }

    @Override
    default String uriScheme() {
        return HBaseUri.SCHEME;
    }

    @Override
    default URI createUri(URI uri) {
        return HBaseUri.builder().fromServer(HBaseCluster.host(), HBaseCluster.port(), uri);
    }

    @Override
    default URI createUri(File file) {
        return HBaseUri.builder().fromServer(HBaseCluster.host(), HBaseCluster.port(), file.getName());
    }
}
