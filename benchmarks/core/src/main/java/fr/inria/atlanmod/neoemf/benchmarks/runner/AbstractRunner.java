/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner;

import com.google.common.collect.Iterators;

import fr.inria.atlanmod.neoemf.benchmarks.Creator;
import fr.inria.atlanmod.neoemf.benchmarks.Runner;
import fr.inria.atlanmod.neoemf.benchmarks.util.BenchmarkUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.SampleTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = BenchmarkUtil.DEFAULT_FORKS, jvmArgs = {
        "-Dfile.encoding=utf-8",
        "-server",
        "-Xmx8g",
        "-XX:+UseConcMarkSweepGC",
        "-XX:+DisableExplicitGC",
        "-XX:+CMSClassUnloadingEnabled"
})
@Warmup(iterations = BenchmarkUtil.DEFAULT_ITERATIONS, batchSize = BenchmarkUtil.DEFAULT_BATCH_SIZE)
@Measurement(iterations = BenchmarkUtil.DEFAULT_ITERATIONS, batchSize = BenchmarkUtil.DEFAULT_BATCH_SIZE)
@OperationsPerInvocation(BenchmarkUtil.DEFAULT_BATCH_SIZE)
public abstract class AbstractRunner implements Runner {

    protected static final Logger LOG = LogManager.getLogger();

    /**
     * All loaded global resource paths.
     *
     * @see #loadGlobalResourcesAndStores()
     */
    private static String[] PATHS;

    protected Resource resource;

    /**
     * The current index in {@link #PATHS}.
     */
    // TODO: Find a better way to navigate between the differents resources
    @SuppressWarnings("unused")
    @Param({"0", "1", "2", "3", "4"})
    private int currentIndex;

    /**
     * Loads global resources and stores them in {@link #PATHS}.
     */
    @Setup(Level.Trial)
    public final void loadGlobalResourcesAndStores() {
        PATHS = getCreator().createAll();
    }

    /**
     * Returns the load options of a {@link Resource} for this {@link Runner}.
     */
    protected Map<Object, Object> getLoadOptions() {
        return Collections.emptyMap();
    }

    /**
     * Returns the save options of a {@link Resource} for this {@link Runner}.
     */
    protected Map<Object, Object> getSaveOptions() {
        return Collections.emptyMap();
    }

    /**
     * Returns the {@link Creator} associated with this {@link Runner}.
     */
    protected abstract Creator getCreator();

    @Setup(Level.Iteration)
    public abstract void createResource() throws IOException;

    @TearDown(Level.Iteration)
    public abstract void destroyResource();

    // TODO: Copy resource in a temp dir to avoid overwritting
    protected final String getPath() throws IOException {
        try {
            return PATHS[currentIndex];
        } catch (Exception e) {
            throw new IOException("Unloaded resource at index " + currentIndex);
        }
    }

    @Benchmark
    @Override
    public void traverse() {
        try {
            LOG.info("Start counting");
            Instant begin = Instant.now();
            int count = Iterators.size(resource.getAllContents());
            Instant end = Instant.now();
            LOG.info("End counting");
            LOG.info("Resource {} contains {} elements", resource.getURI(), count);
            LOG.info("Time spent: {}", Duration.between(begin, end));
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }
}
