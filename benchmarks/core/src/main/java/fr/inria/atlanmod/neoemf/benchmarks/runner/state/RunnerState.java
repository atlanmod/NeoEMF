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

import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.BerkeleyDbAdapter;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.CdoAdapter;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.MapDbAdapter;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Neo4jAdapter;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.TinkerAdapter;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.XmiAdapter;
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
 * This state contains all the benchmarks parameters, and provides a ready-to-use {@link Adapter} and the preloaded
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
     * The name of the current {@link Adapter}.
     * <p>
     * By default, all registered {@link Adapter}s are used.
     */
    @Param({
            XmiAdapter.NAME,
            CdoAdapter.NAME,
            MapDbAdapter.NAME,
            BerkeleyDbAdapter.NAME,
            TinkerAdapter.NAME,
            Neo4jAdapter.NAME,
    })
    protected String a;

    /**
     * The name of the current {@link fr.inria.atlanmod.neoemf.data.store.Store}s.
     * <p>
     * By default, no store is used.
     */
    @Param("a")
    protected String o;

    /**
     * The current {@link Adapter}.
     */
    private Adapter adapter;

    /**
     * The current {@link org.eclipse.emf.ecore.resource.Resource} file.
     */
    private File resourceFile;

    /**
     * Returns the current adapter.
     */
    @Nonnull
    public Adapter getAdapter() throws Exception {
        if (isNull(adapter)) {
            Map<String, Class<? extends Adapter>> allAdapters = allAdapters();
            checkArgument(allAdapters.containsKey(a), "No adapter named '%s' is registered", a);
            adapter = allAdapters.get(a).newInstance();
        }
        return adapter;
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
        resourceFile = getAdapter().getOrCreateResource(r);
    }

    /**
     * Returns all existing {@link Adapter} instances.
     *
     * @return an immutable map
     */
    @Nonnull
    private Map<String, Class<? extends Adapter>> allAdapters() {
        Map<String, Class<? extends Adapter>> map = new HashMap<>();

        map.put(XmiAdapter.NAME, XmiAdapter.class);
        map.put(CdoAdapter.NAME, CdoAdapter.class);
        map.put(MapDbAdapter.NAME, MapDbAdapter.class);
        map.put(BerkeleyDbAdapter.NAME, BerkeleyDbAdapter.class);
        map.put(TinkerAdapter.NAME, TinkerAdapter.class);
        map.put(Neo4jAdapter.NAME, Neo4jAdapter.class);

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
        if (lowerOptions.contains("p")) {
            Log.debug("Use presence caching");
            options.cacheIsSet();
        }

        // Cache sizes
        if (lowerOptions.contains("s")) {
            Log.debug("Use size caching");
            options.cacheSizes();
        }

        // Cache metaclasses
        if (lowerOptions.contains("m")) {
            Log.debug("Use metaclass caching");
            options.cacheMetaclasses();
        }

        // Cache containers
        if (lowerOptions.contains("c")) {
            Log.debug("Use container caching");
            options.cacheContainers();
        }

        // Stats recording
        if (lowerOptions.contains("r")) {
            Log.debug("Use statistics recording");
            options.recordStats();
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