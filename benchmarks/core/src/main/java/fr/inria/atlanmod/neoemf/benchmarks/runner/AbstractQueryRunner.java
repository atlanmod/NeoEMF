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

import fr.inria.atlanmod.neoemf.benchmarks.Backend;
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryRunner;
import fr.inria.atlanmod.neoemf.benchmarks.query.ase2015.QueryFactoryASE2015;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.UUID;

public abstract class AbstractQueryRunner extends AbstractRunner implements QueryRunner {

    public AbstractQueryRunner(Backend backend) {
        super(backend);
    }

    @Benchmark
    @Override
    public void classDeclarationAttributes() throws Exception {
        QueryFactory.queryClassDeclarationAttributes(resource).callWithTime();
    }

    @Benchmark
    @Override
    public void grabats() throws Exception {
        QueryFactory.queryGrabats09(resource).callWithTime();
    }

    @Benchmark
    @Override
    public void invisibleMethodDeclarations() throws Exception {
        QueryFactory.queryInvisibleMethodDeclarations(resource).callWithTime();
    }

    @Benchmark
    @Override
    public void orphanNonPrimitiveTypes() throws Exception {
        QueryFactory.queryOrphanNonPrimitivesTypes(resource).callWithTime();
    }

    //@Benchmark
    @Override
    public void renameAllMethods() throws Exception {
        String name = UUID.randomUUID().toString();
        QueryFactory.queryRenameAllMethods(resource, name).callWithTime();
        backend.saveResource(resource);
    }

    @Benchmark
    @Override
    public void thrownExceptionsPerPackage() throws Exception {
        QueryFactory.queryThrownExceptionsPerPackage(resource).callWithTime();
    }

    @Benchmark
    @Override
    public void unusedMethodsWithList() throws Exception {
        QueryFactory.queryUnusedMethodsWithList(resource).callWithTime();
    }

    @Benchmark
    @Override
    public void unusedMethodsWithLoop() throws Exception {
        QueryFactory.queryUnusedMethodsWithLoop(resource).callWithTime();
    }

    @Benchmark
    @Override
    public void branchStatementsAse2015() throws Exception {
        QueryFactoryASE2015.queryCommentsTagContentAse2015(resource).callWithMemoryAndTime();
    }

    @Benchmark
    @Override
    public void grabatsAse2015() throws Exception {
        QueryFactoryASE2015.queryGrabatsAse2015(resource).callWithMemoryAndTime();
    }

    @Benchmark
    @Override
    public void invisibleMethodDeclarationsAse2015() throws Exception {
        QueryFactoryASE2015.queryInvisibleMethodDeclarations(resource).callWithMemoryAndTime();
    }

    @Benchmark
    @Override
    public void specificInvisibleMethodDeclarationsAse2015() throws Exception {
        QueryFactoryASE2015.querySpecificInvisibleMethodDeclarationsAse2015(resource).callWithMemoryAndTime();
    }

    @Benchmark
    @Override
    public void thrownExceptionsAse2015() throws Exception {
        QueryFactoryASE2015.queryThrownExceptionsAse2015(resource).callWithMemoryAndTime();
    }
}
