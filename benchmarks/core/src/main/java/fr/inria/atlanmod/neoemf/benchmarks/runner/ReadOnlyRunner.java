/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner;

import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactoryASE;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadOnlyRunnerState;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * A {@link Runner} that provides benchmark methods for read-only queries.
 */
public class ReadOnlyRunner extends Runner {

    @Benchmark
    public long traverse(ReadOnlyRunnerState state) {
        return QueryFactory.countAllElements().apply(state.resource());
    }

    @Benchmark
    public int classDeclarationAttributes(ReadOnlyRunnerState state) {
        return QueryFactory.countClassFields().apply(state.resource());
    }

    @Benchmark
    public int grabats(ReadOnlyRunnerState state) {
        return QueryFactory.grabats().apply(state.resource());
    }

    @Benchmark
    public int invisibleMethodDeclarations(ReadOnlyRunnerState state) {
        return QueryFactory.countInvisibleMethods().apply(state.resource());
    }

    @Benchmark
    public int orphanNonPrimitiveTypes(ReadOnlyRunnerState state) {
        return QueryFactory.countOrphanNonPrimitiveTypes().apply(state.resource());
    }

    @Benchmark
    public int thrownExceptionsPerPackage(ReadOnlyRunnerState state) {
        return QueryFactory.countThrownExceptionsPerPackage().apply(state.resource());
    }

    @Benchmark
    public int unusedMethodsWithList(ReadOnlyRunnerState state) {
        return QueryFactory.countUnusedMethodsWithList().apply(state.resource());
    }

    @Benchmark
    public int unusedMethodsWithLoop(ReadOnlyRunnerState state) {
        return QueryFactory.countUnusedMethodsWithLoop().apply(state.resource());
    }

    // region ASE 2015

    @Benchmark
    public int ase15CommentsTagContent(ReadOnlyRunnerState state) {
        return QueryFactoryASE.countTagComments().apply(state.resource());
    }

    @Benchmark
    public int ase15Grabats(ReadOnlyRunnerState state) {
        return QueryFactoryASE.grabatsASE().apply(state.resource());
    }

    @Benchmark
    public int ase15SpecificInvisibleMethodDeclarations(ReadOnlyRunnerState state) {
        return QueryFactoryASE.countInvisibleMethodsSpecific().apply(state.resource());
    }

    @Benchmark
    public int ase15ThrownExceptions(ReadOnlyRunnerState state) {
        return QueryFactoryASE.countThrownExceptions().apply(state.resource());
    }

    @Benchmark
    public int ase15BranchStatements(ReadOnlyRunnerState state) {
        return QueryFactoryASE.countBranchStatements().apply(state.resource());
    }

    //endregion
}
