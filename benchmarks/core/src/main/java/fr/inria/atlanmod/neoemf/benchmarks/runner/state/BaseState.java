/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.AdapterFactory;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link State} that provides a ready-to-use {@link Adapter}.
 */
@State(Scope.Benchmark)
@ParametersAreNonnullByDefault
public abstract class BaseState {

    /**
     * The name of the current {@link Adapter}.
     */
    @Param({
            "xmi",
            "cdo",
            "neo4j",
            "berkeleydb-i",
            "mapdb-i",
    })
    protected String a;

    /**
     * The current {@link Adapter}.
     */
    private Adapter adapter;

    /**
     * Returns the current adapter.
     */
    @Nonnull
    public Adapter adapter() {
        return adapter;
    }

    /**
     * Initializes all defined arguments.
     */
    @Setup(Level.Trial)
    public void initAdapter() {
        adapter = AdapterFactory.createAdapter(a);
    }
}
