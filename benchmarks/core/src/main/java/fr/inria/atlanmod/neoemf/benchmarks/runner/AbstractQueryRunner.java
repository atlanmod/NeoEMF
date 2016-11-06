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

import fr.inria.atlanmod.neoemf.benchmarks.QueryRunner;
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;
import fr.inria.atlanmod.neoemf.benchmarks.query.ase2015.QueryFactoryASE2015;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.Collections;
import java.util.UUID;

/**
 *
 */
public abstract class AbstractQueryRunner extends AbstractRunner implements QueryRunner {

    @Benchmark
    @Override
    public void classDeclarationAttributes() {
        try {
            QueryFactory.queryClassDeclarationAttributes(resource).callWithTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Benchmark
    @Override
    public void grabats() {
        try {
            QueryFactory.queryGrabats09(resource).callWithTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Benchmark
    @Override
    public void invisibleMethodDeclarations() {
        try {
            QueryFactory.queryInvisibleMethodDeclarations(resource).callWithTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Benchmark
    @Override
    public void orphanNonPrimitiveTypes() {
        try {
            QueryFactory.queryOrphanNonPrimitivesTypes(resource).callWithTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    //@Benchmark
    @Override
    public void renameAllMethods() {
        try {
            String name = UUID.randomUUID().toString();
            QueryFactory.queryRenameAllMethods(resource, name).callWithTime();
            resource.save(Collections.emptyMap());
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Benchmark
    @Override
    public void thrownExceptionsPerPackage() {
        try {
            QueryFactory.queryThrownExceptionsPerPackage(resource).callWithTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Benchmark
    @Override
    public void unusedMethodsWithList() {
        try {
            QueryFactory.queryUnusedMethodsWithList(resource).callWithTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Benchmark
    @Override
    public void unusedMethodsWithLoop() {
        try {
            QueryFactory.queryUnusedMethodsWithLoop(resource).callWithTime(); // Query result (loops)
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Benchmark
    @Override
    public void branchStatementsAse2015() {
        try {
            QueryFactoryASE2015.queryCommentsTagContentAse2015(resource).callWithMemoryAndTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Benchmark
    @Override
    public void grabatsAse2015() {
        try {
            QueryFactoryASE2015.queryGrabatsAse2015(resource).callWithMemoryAndTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Benchmark
    @Override
    public void invisibleMethodDeclarationsAse2015() {
        try {
            QueryFactoryASE2015.queryInvisibleMethodDeclarations(resource).callWithMemoryAndTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Benchmark
    @Override
    public void specificInvisibleMethodDeclarationsAse2015() {
        try {
            QueryFactoryASE2015.querySpecificInvisibleMethodDeclarationsAse2015(resource).callWithMemoryAndTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Benchmark
    @Override
    public void thrownExceptionsAse2015() {
        try {
            QueryFactoryASE2015.queryThrownExceptionsAse2015(resource).callWithMemoryAndTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }
}
