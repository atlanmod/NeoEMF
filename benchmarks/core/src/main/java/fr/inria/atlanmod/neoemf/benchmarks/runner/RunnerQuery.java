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

import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;
import fr.inria.atlanmod.neoemf.benchmarks.query.ase2015.QueryFactoryASE2015;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadOnlyRunnerState;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadWriteRunnerState;

import org.eclipse.emf.ecore.resource.Resource;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.IOException;
import java.util.UUID;

/**
 * Runner that provides benchmark methods for queries.
 */
public class RunnerQuery extends Runner {

    //region Simple queries.

    @Benchmark
    public Long traverse(ReadOnlyRunnerState state) {
        return QueryFactory.queryCountAllElements(state.getResource()).callWithResult();
    }

    //endregion

    //region Read-only queries.

    @Benchmark
    public Integer classDeclarationAttributes(ReadOnlyRunnerState state) {
        return QueryFactory.queryClassDeclarationAttributes(state.getResource()).callWithResult();
    }

    @Benchmark
    public Integer grabats(ReadOnlyRunnerState state) {
        return QueryFactory.queryGrabats(state.getResource()).callWithResult();
    }

    @Benchmark
    public Integer invisibleMethodDeclarations(ReadOnlyRunnerState state) {
        return QueryFactory.queryInvisibleMethodDeclarations(state.getResource()).callWithResult();
    }

    @Benchmark
    public Integer orphanNonPrimitiveTypes(ReadOnlyRunnerState state) {
        return QueryFactory.queryOrphanNonPrimitivesTypes(state.getResource()).callWithResult();
    }

    @Benchmark
    public Integer thrownExceptionsPerPackage(ReadOnlyRunnerState state) {
        return QueryFactory.queryThrownExceptionsPerPackage(state.getResource()).callWithResult();
    }

    @Benchmark
    public Integer unusedMethodsWithList(ReadOnlyRunnerState state) {
        return QueryFactory.queryUnusedMethodsWithList(state.getResource()).callWithResult();
    }

    @Benchmark
    public Integer unusedMethodsWithLoop(ReadOnlyRunnerState state) {
        return QueryFactory.queryUnusedMethodsWithLoop(state.getResource()).callWithResult();
    }

    //endregion

    //region Read/Write queries.

    @Benchmark
    public Integer renameAllMethods(ReadWriteRunnerState state) throws IOException {
        String name = UUID.randomUUID().toString();
        Resource resource = state.getResource();
        Integer result = QueryFactory.queryRenameAllMethods(resource, name).callWithResult();
        state.getAdapter().save(resource);
        return result;
    }

    //endregion

    //region ASE 2015 queries.

    @Benchmark
    public Integer ase15CommentsTagContent(ReadOnlyRunnerState state) {
        return QueryFactoryASE2015.queryCommentsTagContent(state.getResource()).callWithResult();
    }

    @Benchmark
    public Integer ase15Grabats(ReadOnlyRunnerState state) {
        return QueryFactoryASE2015.queryGrabats(state.getResource()).callWithResult();
    }

    @Benchmark
    public Integer ase15SpecificInvisibleMethodDeclarations(ReadOnlyRunnerState state) {
        return QueryFactoryASE2015.querySpecificInvisibleMethodDeclarations(state.getResource()).callWithResult();
    }

    @Benchmark
    public Integer ase15ThrownExceptions(ReadOnlyRunnerState state) {
        return QueryFactoryASE2015.queryThrownExceptions(state.getResource()).callWithResult();
    }

    //endregion
}
