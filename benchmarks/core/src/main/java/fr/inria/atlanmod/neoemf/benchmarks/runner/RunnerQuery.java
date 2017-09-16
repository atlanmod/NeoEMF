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
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactoryAse15;
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
        return QueryFactory.queryCountAllElements().apply(state.resource());
    }

    //endregion

    //region Read-only queries.

    @Benchmark
    public Integer classDeclarationAttributes(ReadOnlyRunnerState state) {
        return QueryFactory.queryClassDeclarationAttributes().apply(state.resource());
    }

    @Benchmark
    public Integer grabats(ReadOnlyRunnerState state) {
        return QueryFactory.queryGrabats().apply(state.resource());
    }

    @Benchmark
    public Integer invisibleMethodDeclarations(ReadOnlyRunnerState state) {
        return QueryFactory.queryInvisibleMethodDeclarations().apply(state.resource());
    }

    @Benchmark
    public Integer orphanNonPrimitiveTypes(ReadOnlyRunnerState state) {
        return QueryFactory.queryOrphanNonPrimitivesTypes().apply(state.resource());
    }

    @Benchmark
    public Integer thrownExceptionsPerPackage(ReadOnlyRunnerState state) {
        return QueryFactory.queryThrownExceptionsPerPackage().apply(state.resource());
    }

    @Benchmark
    public Integer unusedMethodsWithList(ReadOnlyRunnerState state) {
        return QueryFactory.queryUnusedMethodsWithList().apply(state.resource());
    }

    @Benchmark
    public Integer unusedMethodsWithLoop(ReadOnlyRunnerState state) {
        return QueryFactory.queryUnusedMethodsWithLoop().apply(state.resource());
    }

    //endregion

    //region Read/Write queries.

    @Benchmark
    public Integer renameAllMethods(ReadWriteRunnerState state) throws IOException {
        String name = UUID.randomUUID().toString();
        Resource resource = state.resource();
        Integer result = QueryFactory.queryRenameAllMethods(name).apply(resource);
        state.adapter().save(resource, state.options());
        return result;
    }

    //endregion

    //region ASE 2015 queries.

    @Benchmark
    public Integer ase15CommentsTagContent(ReadOnlyRunnerState state) {
        return QueryFactoryAse15.queryCommentsTagContent().apply(state.resource());
    }

    @Benchmark
    public Integer ase15Grabats(ReadOnlyRunnerState state) {
        return QueryFactoryAse15.queryGrabats().apply(state.resource());
    }

    @Benchmark
    public Integer ase15SpecificInvisibleMethodDeclarations(ReadOnlyRunnerState state) {
        return QueryFactoryAse15.querySpecificInvisibleMethodDeclarations().apply(state.resource());
    }

    @Benchmark
    public Integer ase15ThrownExceptions(ReadOnlyRunnerState state) {
        return QueryFactoryAse15.queryThrownExceptions().apply(state.resource());
    }

    @Benchmark
    public Integer ase15BranchStatements(ReadOnlyRunnerState state) {
        return QueryFactoryAse15.queryBranchStatements().apply(state.resource());
    }

    //endregion
}
