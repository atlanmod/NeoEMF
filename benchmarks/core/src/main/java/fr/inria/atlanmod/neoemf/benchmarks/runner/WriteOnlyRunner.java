/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner;

import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadOnlyResourceState;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ResourceState;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link BaseRunner} that provides benchmark methods for the initialization of resources and data stores.
 */
@ParametersAreNonnullByDefault
public class WriteOnlyRunner extends BaseRunner {

    /**
     * Creates a new resource according to the current {@code state}. If the resource already exists, then this method
     * does nothing.
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 0)
    public void initResource(@SuppressWarnings("unused") ResourceState state) {
        // Let the setup method creates the resource
    }

    /**
     * Creates a new resource and store according to the current {@code state}. If the resource and store already exist,
     * then this method does nothing.
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 0)
    public void initStore(@SuppressWarnings("unused") ReadOnlyResourceState state) {
        // Let the setup method creates the resource and its store
    }

    /**
     * Creates a new store in a temporary location.
     */
    @Benchmark
    public void create(ResourceState state) throws IOException {
        state.adapter().createTempStore(state.resourceFile(), state.baseConfig(), state.useDirectImport());
    }
}
