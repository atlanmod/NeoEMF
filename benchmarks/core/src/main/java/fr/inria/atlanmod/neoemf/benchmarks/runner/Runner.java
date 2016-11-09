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

import fr.inria.atlanmod.neoemf.benchmarks.Configuration;
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadRunnerState;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Timeout;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.SampleTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Timeout(
        time = Configuration.DEFAULT_ITERATION_TIMEOUT,
        timeUnit = TimeUnit.MINUTES
)
@Warmup(
        iterations = Configuration.DEFAULT_WARMUP_ITERATIONS,
        time = Configuration.DEFAULT_ITERATION_TIME,
        timeUnit = TimeUnit.MILLISECONDS,
        batchSize = Configuration.DEFAULT_OPERATIONS
)
@Measurement(
        iterations = Configuration.DEFAULT_MEASUREMENT_ITERATIONS,
        time = Configuration.DEFAULT_ITERATION_TIME,
        timeUnit = TimeUnit.MILLISECONDS,
        batchSize = Configuration.DEFAULT_OPERATIONS
)
@OperationsPerInvocation(Configuration.DEFAULT_OPERATIONS)
@Fork(value = Configuration.DEFAULT_FORKS, jvmArgs = {
        "-Dfile.encoding=utf-8",
        "-server",
        "-Xmx8g",
        "-XX:+UseConcMarkSweepGC",
        "-XX:+DisableExplicitGC",
        "-XX:+CMSClassUnloadingEnabled"
})
public abstract class Runner {

    protected static final Logger LOG = LogManager.getLogger();

    @Benchmark
    public void loadUnload(ReadRunnerState state, Blackhole bh) throws Exception {
        // Run @Setup and @TearDown implicitly
        bh.consume(state.getResource());
    }

    @Benchmark
    public Integer traverse(ReadRunnerState state) throws Exception {
        return QueryFactory.queryCountAllElements(state.getResource()).callWithTime();
    }
}
