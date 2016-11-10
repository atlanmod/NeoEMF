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

import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadOnlyRunnerState;

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
@Timeout(time = 1, timeUnit = TimeUnit.HOURS)
@Warmup(
        iterations = RunnerConstants.DEFAULT_WARMUP_ITERATIONS,
        batchSize = RunnerConstants.DEFAULT_OPERATIONS
)
@Measurement(
        iterations = RunnerConstants.DEFAULT_MEASUREMENT_ITERATIONS,
        batchSize = RunnerConstants.DEFAULT_OPERATIONS
)
@OperationsPerInvocation(RunnerConstants.DEFAULT_OPERATIONS)
@Fork(
        value = RunnerConstants.DEFAULT_FORKS,
        jvmArgs = {
                "-Dfile.encoding=utf-8",
                "-server",
                "-XX:+UseConcMarkSweepGC",
                "-XX:+DisableExplicitGC",
                "-XX:+CMSClassUnloadingEnabled"
        },
        jvmArgsAppend = RunnerConstants.DEFAULT_XMX
)
public abstract class Runner {

    @Benchmark
    public void loadUnload(ReadOnlyRunnerState state, Blackhole bh) throws Exception {
        // Run @Setup and @TearDown to show their impact on results
        bh.consume(state.getResource());
    }

    @Benchmark
    public Integer traverse(ReadOnlyRunnerState state) throws Exception {
        return QueryFactory.queryCountAllElements(state.getResource()).callWithTime();
    }
}
