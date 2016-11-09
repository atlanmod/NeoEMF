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
import fr.inria.atlanmod.neoemf.benchmarks.query.ase2015.QueryFactoryASE2015;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadRunnerState;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadWriteRunnerState;

import org.eclipse.emf.ecore.resource.Resource;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.UUID;

public class QueryRunner extends Runner {

    /*
     * Read-only queries.
     */

    @Benchmark
    public Integer classDeclarationAttributes(ReadRunnerState state) throws Exception {
        return QueryFactory.queryClassDeclarationAttributes(state.getResource()).callWithTime();
    }

    @Benchmark
    public Integer grabats(ReadRunnerState state) throws Exception {
        return QueryFactory.queryGrabats09(state.getResource()).callWithTime();
    }

    @Benchmark
    public Integer invisibleMethodDeclarations(ReadRunnerState state) throws Exception {
        return QueryFactory.queryInvisibleMethodDeclarations(state.getResource()).callWithTime();
    }

    @Benchmark
    public Integer orphanNonPrimitiveTypes(ReadRunnerState state) throws Exception {
        return QueryFactory.queryOrphanNonPrimitivesTypes(state.getResource()).callWithTime();
    }

    @Benchmark
    public Integer thrownExceptionsPerPackage(ReadRunnerState state) throws Exception {
        return QueryFactory.queryThrownExceptionsPerPackage(state.getResource()).callWithTime();
    }

    @Benchmark
    public Integer unusedMethodsWithList(ReadRunnerState state) throws Exception {
        return QueryFactory.queryUnusedMethodsWithList(state.getResource()).callWithTime();
    }

    @Benchmark
    public Integer unusedMethodsWithLoop(ReadRunnerState state) throws Exception {
        return QueryFactory.queryUnusedMethodsWithLoop(state.getResource()).callWithTime();
    }

    @Benchmark
    public Integer branchStatementsAse2015(ReadRunnerState state) throws Exception {
        return QueryFactoryASE2015.queryCommentsTagContentAse2015(state.getResource()).callWithMemoryAndTime();
    }

    @Benchmark
    public Integer grabatsAse2015(ReadRunnerState state) throws Exception {
        return QueryFactoryASE2015.queryGrabatsAse2015(state.getResource()).callWithMemoryAndTime();
    }

    @Benchmark
    public Integer invisibleMethodDeclarationsAse2015(ReadRunnerState state) throws Exception {
        return QueryFactoryASE2015.queryInvisibleMethodDeclarations(state.getResource()).callWithMemoryAndTime();
    }

    @Benchmark
    public Integer specificInvisibleMethodDeclarationsAse2015(ReadRunnerState state) throws Exception {
        return QueryFactoryASE2015.querySpecificInvisibleMethodDeclarationsAse2015(state.getResource()).callWithMemoryAndTime();
    }

    @Benchmark
    public Integer thrownExceptionsAse2015(ReadRunnerState state) throws Exception {
        return QueryFactoryASE2015.queryThrownExceptionsAse2015(state.getResource()).callWithMemoryAndTime();
    }

    /*
     * Read/Write queries
     */

    @Benchmark
    public Void renameAllMethods(ReadWriteRunnerState state) throws Exception {
        String name = UUID.randomUUID().toString();
        Resource resource = state.getResource();
        Void result = QueryFactory.queryRenameAllMethods(resource, name).callWithTime();
        state.getBackend().save(resource);
        return result;
    }
}
