/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner;

import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadOnlyAtomicState;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadWriteAtomicState;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.WriteOnlyAtomicState;

import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link BaseRunner} that provides benchmark methods for atomic queries.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@ParametersAreNonnullByDefault
public class AtomicRunner extends BaseRunner {

    @Benchmark
    public String getSingleAttribute(ReadOnlyAtomicState state) {
        final Package root = state.root();
        return root.getName();
    }

    @Benchmark
    public void setSingleAttribute(WriteOnlyAtomicState state) {
        final Package root = state.root();
        root.setName("org.neoemf");
    }

    @Benchmark
    public void removeSingleAttribute(ReadWriteAtomicState state) {
        final Package root = state.root();
        root.setName(null);
    }

    @Benchmark
    public Model getSingleReference(ReadOnlyAtomicState state) {
        final Package root = state.root();
        return root.getModel();
    }

    @Benchmark
    public void setSingleReference(WriteOnlyAtomicState state) {
        final Package root = state.root();
        root.setModel(state.create(JavaPackage.MODEL));
    }

    @Benchmark
    public void removeSingleReference(ReadWriteAtomicState state) {
        final Package root = state.root();
        root.setModel(null);
    }
}
