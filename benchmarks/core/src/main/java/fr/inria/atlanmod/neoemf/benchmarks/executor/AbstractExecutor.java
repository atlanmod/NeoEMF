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

package fr.inria.atlanmod.neoemf.benchmarks.executor;

import com.google.common.collect.Iterators;

import fr.inria.atlanmod.neoemf.benchmarks.Traverser;
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
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

@Fork(value = 1, jvmArgs = {
        "-Dfile.encoding=utf-8",
        "-server",
        "-Xmx8g",
        "-XX:+UseConcMarkSweepGC",
        "-XX:+DisableExplicitGC",
        "-XX:+CMSClassUnloadingEnabled"
})
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
public abstract class AbstractExecutor implements Traverser {

    private static final Logger LOG = LogManager.getLogger();

    protected static String[] PATHS;

    protected Resource resource;

    @Param({"0", "1", "2", "3", "4"})
    @SuppressWarnings("unused")
    private int currentIndex;

    @Setup(Level.Trial)
    public abstract void loadResourcesAndStores();

    @Setup(Level.Iteration)
    public abstract void createResource() throws IOException;

    @TearDown(Level.Iteration)
    public abstract void destroyResource();

    protected String getPath() throws IOException {
        Path source = Paths.get(PATHS[currentIndex]);
        Path target = BenchmarkUtil.getBenchDirectory().resolve(new File(PATHS[currentIndex]).getName());

        Files.copy(source, target);

        target.toFile().deleteOnExit();

        return target.toAbsolutePath().toString();
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
