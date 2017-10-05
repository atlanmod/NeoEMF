/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.BerkeleyDbAdapter;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.BlueprintsAdapter;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.CdoAdapter;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.MapDbAdapter;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.XmiAdapter;
import fr.inria.atlanmod.neoemf.config.Config;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import static fr.inria.atlanmod.commons.Preconditions.checkState;
import static java.util.Objects.nonNull;

/**
 * This state contains all the benchmarks parameters, and provides a ready-to-use {@link Adapter} and the preloaded
 * resource file. <p> <p>Note:</p> It does not load the datastores.
 */
@State(Scope.Thread)
public class RunnerState {

    /**
     * A map that holds all existing {@link Adapter} instances, identified by their name.
     */
    @Nonnull
    private static final Map<String, Class<? extends Adapter>> ADAPTERS = new HashMap<>();

    static {
        ADAPTERS.put("xmi", XmiAdapter.class);
        ADAPTERS.put("cdo", CdoAdapter.class);
        ADAPTERS.put("mapdb-i", MapDbAdapter.WithIndices.class);
        ADAPTERS.put("mapdb-a", MapDbAdapter.WithArrays.class);
        ADAPTERS.put("mapdb-l", MapDbAdapter.WithLists.class);
        ADAPTERS.put("berkeleydb-i", BerkeleyDbAdapter.WithIndices.class);
        ADAPTERS.put("berkeleydb-a", BerkeleyDbAdapter.WithArrays.class);
        ADAPTERS.put("berkeleydb-l", BerkeleyDbAdapter.WithLists.class);
        ADAPTERS.put("tinker", BlueprintsAdapter.Tinker.class);
        ADAPTERS.put("neo4j", BlueprintsAdapter.Neo4j.class);
    }

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
    })
    protected String a;

    /**
     * The name of the current store chain.
     */
    @Param("AMC")
    protected String o;

    /**
     * {@code "true"} if the direct import has to be used when creating or importing resources.
     */
    @Param("true")
    protected String direct;

    // endregion

    /**
     * The current {@link org.eclipse.emf.ecore.resource.Resource} file.
     */
    private File resourceFile;

    /**
     * Returns the current adapter.
     */
    @Nonnull
    public Adapter adapter() {
        try {
            Adapter adapter;
            Class<? extends Adapter> instance = ADAPTERS.get(a);
            checkState(nonNull(instance), "No adapter named '%s' is registered", a);
            adapter = ADAPTERS.get(a).newInstance();
            return adapter;
        }
        catch (InstantiationException | IllegalAccessException e) {
            Log.error(e);
            throw new RuntimeException(e);
        }
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
        return Boolean.valueOf(direct);
    }

    /**
     * Loads and creates the current resource file.
     */
    @Setup(Level.Trial)
    public void initResource() throws IOException {
        Log.info("Initializing the resource");
        resourceFile = adapter().getOrCreateResource(r);
    }

    /**
     * Returns all existing {@link Config} instances.
     */
    @Nonnull
    public Config baseConfig() {
        return ConfigParser.parse(o);
    }
}