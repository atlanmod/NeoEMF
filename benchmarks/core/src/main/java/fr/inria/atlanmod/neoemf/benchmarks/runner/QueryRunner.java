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

import java.util.UUID;

public class QueryRunner extends Runner {

    //@Benchmark
    public Integer classDeclarationAttributes(RunnerState state) throws Exception {
        return QueryFactory.queryClassDeclarationAttributes(state.getResource()).callWithTime();
    }

    //@Benchmark
    public Integer grabats(RunnerState state) throws Exception {
        return QueryFactory.queryGrabats09(state.getResource()).callWithTime();
    }

    //@Benchmark
    public Integer invisibleMethodDeclarations(RunnerState state) throws Exception {
        return QueryFactory.queryInvisibleMethodDeclarations(state.getResource()).callWithTime();
    }

    //@Benchmark
    public Integer orphanNonPrimitiveTypes(RunnerState state) throws Exception {
        return QueryFactory.queryOrphanNonPrimitivesTypes(state.getResource()).callWithTime();
    }

    //@Benchmark
    public Void renameAllMethods(RunnerState state) throws Exception {
        String name = UUID.randomUUID().toString();
        Void result = QueryFactory.queryRenameAllMethods(state.getResource(), name).callWithTime();
        state.getBackend().saveResource(state.getResource());
        return result;
    }

    //@Benchmark
    public Integer thrownExceptionsPerPackage(RunnerState state) throws Exception {
        return QueryFactory.queryThrownExceptionsPerPackage(state.getResource()).callWithTime();
    }

    //@Benchmark
    public Integer unusedMethodsWithList(RunnerState state) throws Exception {
        return QueryFactory.queryUnusedMethodsWithList(state.getResource()).callWithTime();
    }

    //@Benchmark
    public Integer unusedMethodsWithLoop(RunnerState state) throws Exception {
        return QueryFactory.queryUnusedMethodsWithLoop(state.getResource()).callWithTime();
    }

    //@Benchmark
    public Integer branchStatementsAse2015(RunnerState state) throws Exception {
        return QueryFactoryASE2015.queryCommentsTagContentAse2015(state.getResource()).callWithMemoryAndTime();
    }

    //@Benchmark
    public Integer grabatsAse2015(RunnerState state) throws Exception {
        return QueryFactoryASE2015.queryGrabatsAse2015(state.getResource()).callWithMemoryAndTime();
    }

    //@Benchmark
    public Integer invisibleMethodDeclarationsAse2015(RunnerState state) throws Exception {
        return QueryFactoryASE2015.queryInvisibleMethodDeclarations(state.getResource()).callWithMemoryAndTime();
    }

    //@Benchmark
    public Integer specificInvisibleMethodDeclarationsAse2015(RunnerState state) throws Exception {
        return QueryFactoryASE2015.querySpecificInvisibleMethodDeclarationsAse2015(state.getResource()).callWithMemoryAndTime();
    }

    //@Benchmark
    public Integer thrownExceptionsAse2015(RunnerState state) throws Exception {
        return QueryFactoryASE2015.queryThrownExceptionsAse2015(state.getResource()).callWithMemoryAndTime();
    }
}