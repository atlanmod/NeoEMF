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

package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import fr.inria.atlanmod.neoemf.benchmarks.datastore.Backend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.BerkeleyDbBackend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.CdoBackend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.MapDbBackend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.Neo4jBackend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.TinkerBackend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.XmiBackend;
import fr.inria.atlanmod.neoemf.option.CommonOptions;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static java.util.Objects.isNull;

/**
 * This state contains all the benchmarks parameters, and provides a ready-to-use {@link Backend} and the preloaded
 * resource file. The datastore is not loaded.
 */
@State(Scope.Thread)
public class RunnerState {

    /**
     * The name of the current {@link org.eclipse.emf.ecore.resource.Resource} file.
     * <p>
     * By default, all registered resources are used.
     */
    @Param({"set1", "set2", "set3", "set4", "set5"})
    protected String r;

    /**
     * The name of the current {@link Backend}.
     * <p>
     * By default, all registered {@link Backend}s are used.
     */
    @Param({
            XmiBackend.NAME,
            CdoBackend.NAME,
            MapDbBackend.NAME,
            BerkeleyDbBackend.NAME,
            TinkerBackend.NAME,
            Neo4jBackend.NAME,
    })
    protected String b;

    /**
     * The name of the current {@link fr.inria.atlanmod.neoemf.data.store.Store}s.
     * <p>
     * By default, no store is used.
     */
    @Param("a")
    protected String o;

    /**
     * The current {@link Backend}.
     */
    private Backend backend;

    /**
     * The current {@link org.eclipse.emf.ecore.resource.Resource} file.
     */
    private File resourceFile;

    /**
     * Returns the current backend.
     */
    @Nonnull
    public Backend getBackend() throws Exception {
        if (isNull(backend)) {
            Map<String, Class<? extends Backend>> allBackends = allBackends();
            checkArgument(allBackends.containsKey(b), "No backend named '%s' is registered", b);
            backend = allBackends.get(b).newInstance();
        }
        return backend;
    }

    /**
     * Returns the current resource file.
     */
    @Nonnull
    public File getResourceFile() throws Exception {
        return resourceFile;
    }

    /**
     * Loads and creates the current resource file.
     * <p/>
     * This method is automatically called when setup the trial level.
     */
    @Setup(Level.Trial)
    public void initResource() throws Exception {
        Log.info("Initializing the resource");
        resourceFile = getBackend().getOrCreateResource(r);
    }

    /**
     * Returns all existing {@link Backend} instances.
     *
     * @return an immutable map
     */
    @Nonnull
    private Map<String, Class<? extends Backend>> allBackends() {
        Map<String, Class<? extends Backend>> map = new HashMap<>();

        map.put(XmiBackend.NAME, XmiBackend.class);
        map.put(CdoBackend.NAME, CdoBackend.class);
        map.put(MapDbBackend.NAME, MapDbBackend.class);
        map.put(BerkeleyDbBackend.NAME, BerkeleyDbBackend.class);
        map.put(TinkerBackend.NAME, TinkerBackend.class);
        map.put(Neo4jBackend.NAME, Neo4jBackend.class);

        return Collections.unmodifiableMap(map);
    }

    /**
     * Returns all existing {@link PersistentStoreOptions} instances.
     *
     * @return an immutable map
     */
    @Nonnull
    public CommonOptions getOptions() {
        CommonOptions options = CommonOptions.newBuilder();

        String lowerOptions = o.toLowerCase();

        // Cache features
        if (lowerOptions.contains("f")) {
            Log.debug("Use feature caching");
            options.cacheFeatures();
        }

        // Cache presence
        if (lowerOptions.contains("i")) {
            Log.debug("Use presence caching");
            options.cacheIsSet();
        }

        // Cache sizes
        if (lowerOptions.contains("s")) {
            Log.debug("Use size caching");
            options.cacheSizes();
        }

        // Logging
        Matcher levelMatcher = Pattern.compile("l\\{([a-zA-Z]+)\\}").matcher(lowerOptions);
        if (levelMatcher.find()) {
            fr.inria.atlanmod.neoemf.util.log.Level level = fr.inria.atlanmod.neoemf.util.log.Level.valueOf(levelMatcher.group(1).toUpperCase());
            Log.debug("Use logging with level = {0}", level.name());
            options.log(level);
        }
        else if (lowerOptions.contains("l")) {
            Log.debug("Use logging with default level");
            options.log();
        }

        // Auto-saving
        Matcher chuckMatcher = Pattern.compile("a\\{(-?[0-9]+)\\}").matcher(lowerOptions);
        if (chuckMatcher.find()) {
            long chunk = Long.parseLong(chuckMatcher.group(1));
            Log.debug("Use auto-saving with chunk = {0,number,#}", chunk);
            options.autoSave(chunk);
        }
        else { // Defined by default
            Log.debug("Use auto-saving with default chunk");
            options.autoSave();
        }

        return options;
    }
}