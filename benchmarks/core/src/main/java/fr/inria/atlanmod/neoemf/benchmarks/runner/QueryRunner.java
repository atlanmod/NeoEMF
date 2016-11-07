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
    public void classDeclarationAttributes(RunnerState state) throws Exception {
        QueryFactory.queryClassDeclarationAttributes(state.getResource()).callWithTime();
    }

    //@Benchmark
    public void grabats(RunnerState state) throws Exception {
        QueryFactory.queryGrabats09(state.getResource()).callWithTime();
    }

    //@Benchmark
    public void invisibleMethodDeclarations(RunnerState state) throws Exception {
        QueryFactory.queryInvisibleMethodDeclarations(state.getResource()).callWithTime();
    }

    //@Benchmark
    public void orphanNonPrimitiveTypes(RunnerState state) throws Exception {
        QueryFactory.queryOrphanNonPrimitivesTypes(state.getResource()).callWithTime();
    }

    //@Benchmark
    public void renameAllMethods(RunnerState state) throws Exception {
        String name = UUID.randomUUID().toString();
        QueryFactory.queryRenameAllMethods(state.getResource(), name).callWithTime();
        state.getBackend().saveResource(state.getResource());
    }

    //@Benchmark
    public void thrownExceptionsPerPackage(RunnerState state) throws Exception {
        QueryFactory.queryThrownExceptionsPerPackage(state.getResource()).callWithTime();
    }

    //@Benchmark
    public void unusedMethodsWithList(RunnerState state) throws Exception {
        QueryFactory.queryUnusedMethodsWithList(state.getResource()).callWithTime();
    }

    //@Benchmark
    public void unusedMethodsWithLoop(RunnerState state) throws Exception {
        QueryFactory.queryUnusedMethodsWithLoop(state.getResource()).callWithTime();
    }

    //@Benchmark
    public void branchStatementsAse2015(RunnerState state) throws Exception {
        QueryFactoryASE2015.queryCommentsTagContentAse2015(state.getResource()).callWithMemoryAndTime();
    }

    //@Benchmark
    public void grabatsAse2015(RunnerState state) throws Exception {
        QueryFactoryASE2015.queryGrabatsAse2015(state.getResource()).callWithMemoryAndTime();
    }

    //@Benchmark
    public void invisibleMethodDeclarationsAse2015(RunnerState state) throws Exception {
        QueryFactoryASE2015.queryInvisibleMethodDeclarations(state.getResource()).callWithMemoryAndTime();
    }

    //@Benchmark
    public void specificInvisibleMethodDeclarationsAse2015(RunnerState state) throws Exception {
        QueryFactoryASE2015.querySpecificInvisibleMethodDeclarationsAse2015(state.getResource()).callWithMemoryAndTime();
    }

    //@Benchmark
    public void thrownExceptionsAse2015(RunnerState state) throws Exception {
        QueryFactoryASE2015.queryThrownExceptionsAse2015(state.getResource()).callWithMemoryAndTime();
    }
}
