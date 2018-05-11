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
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;

/**
 * This state contains all the benchmarks parameters, and provides a ready-to-use {@link Adapter} and the preloaded
 * resource file. <p> <p>Note:</p> It does not load the datastores.
 */
@State(Scope.Thread)
public class RunnerState {

    // region JMH parameters

    /**
     * The name of the current {@link org.eclipse.emf.ecore.resource.Resource} file.
     */
    @Param({
            "set1",
            "set2",
            "set3",
    })
    protected String r;

    /**
     * The name of the current {@link Adapter}.
     */
    @Param({
            "xmi",
            "cdo",
            "neo4j",
            "berkeleydb-i",
            "mapdb-i",
            "mongodb"
    })
    protected String a;

    /**
     * The name of the current store chain.
     */
    @Param("A")
    protected String o;

    /**
     * {@code "true"} if the direct import has to be used when creating or importing resources.
     */
    @Param("true")
    protected String direct;

    // endregion

    /**
     * The current {@link Adapter}.
     */
    private Adapter adapter;

    /**
     * The current {@link org.eclipse.emf.ecore.resource.Resource} file.
     */
    private File resourceFile;

    /**
     * {@code true} if the direct import has to be used when creating or importing resources.
     */
    private boolean useDirectImport;

    /**
     * The options to use with the defined adapter.
     */
    private ImmutableConfig baseConfig;

    // region Getters

    /**
     * Returns the current adapter.
     */
    @Nonnull
    public Adapter adapter() {
        return adapter;
    }

    /**
     * Returns the current resource file.
     */
    @Nonnull
    public File resourceFile() {
        return resourceFile;
    }

    /**
     * Returns {@code true} if the direct import has to be used when creating or importing resources.
     */
    public boolean useDirectImport() {
        return useDirectImport;
    }

    /**
     * Returns the options to use with the defined adapter.
     */
    @Nonnull
    public ImmutableConfig baseConfig() {
        return baseConfig;
    }

    // endregion

    /**
     * Initializes all defined arguments.
     */
    @Setup(Level.Trial)
    public void initArguments() throws IOException {
        adapter = AdapterFactory.createAdapter(a);
        baseConfig = ConfigParser.parse(o);
        useDirectImport = Boolean.valueOf(direct);
        resourceFile = adapter.getOrCreateResource(r);
    }
}