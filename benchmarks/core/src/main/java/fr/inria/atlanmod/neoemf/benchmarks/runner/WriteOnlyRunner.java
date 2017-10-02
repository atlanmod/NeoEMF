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

package fr.inria.atlanmod.neoemf.benchmarks.runner;

import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadOnlyRunnerState;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.RunnerState;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * A {@link Runner} that provides benchmark methods for the initialization of resources and data stores.
 */
public class WriteOnlyRunner extends Runner {

    /**
     * Creates a new resource according to the current {@code state}. If the resource already exists, then this method
     * does nothing.
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 0)
    public void initResource(@SuppressWarnings("unused") RunnerState state) {
        // Let setup and read down methods to create resource
    }

    /**
     * Creates a new resource and store according to the current {@code state}. If the resource and store already exist,
     * then this method does nothing.
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 0)
    public void initStore(@SuppressWarnings("unused") ReadOnlyRunnerState state) {
        // Let setup and tear down methods create resource and stores
    }

    /**
     * Creates a new store in a temporary location.
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    public void create(RunnerState state) throws IOException {
        state.adapter().createTempStore(state.resourceFile(), state.baseConfig(), state.useDirectImport());
    }
}
