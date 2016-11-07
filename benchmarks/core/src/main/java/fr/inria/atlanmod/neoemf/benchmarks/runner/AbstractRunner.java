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

import fr.inria.atlanmod.neoemf.benchmarks.Backend;
import fr.inria.atlanmod.neoemf.benchmarks.Runner;
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;
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

import java.io.File;
import java.io.IOException;
import java.util.List;
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
@Warmup(iterations = BenchmarkUtil.DEFAULT_WARMUP_ITERATIONS, batchSize = BenchmarkUtil.DEFAULT_BATCH_SIZE)
@Measurement(iterations = BenchmarkUtil.DEFAULT_MEASUREMENT_ITERATIONS, batchSize = BenchmarkUtil.DEFAULT_BATCH_SIZE)
@OperationsPerInvocation(BenchmarkUtil.DEFAULT_BATCH_SIZE)
public abstract class AbstractRunner implements Runner {

    protected static final Logger LOG = LogManager.getLogger();

    protected final Backend backend;

    protected Resource resource;

    private List<File> paths;

    @Param({"0", "1"})
    private int currentPathIndex;

    public AbstractRunner(Backend backend) {
        this.backend = backend;
    }

    @Setup(Level.Trial)
    public void setupTrial() throws Exception {
        paths = backend.createAll();
    }

    @Setup(Level.Iteration)
    public void setupIteration() throws Exception {
        try {
            // TODO: Copy resource in a temp dir to avoid overwritting
            resource = backend.loadResource(paths.get(currentPathIndex).getAbsolutePath());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new IOException("Resource at index " + currentPathIndex + " does not exist");
        }
    }

    @TearDown(Level.Iteration)
    public void tearDownIteration() throws Exception {
        backend.unloadResource(resource);
    }

    @Benchmark
    @Override
    public void traverse() throws Exception {
        QueryFactory.queryCountAllElements(resource).callWithTime();
    }
}
