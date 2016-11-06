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

package fr.inria.atlanmod.neoemf.benchmarks.executor;

import fr.inria.atlanmod.neoemf.benchmarks.QueryExecutor;
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;
import fr.inria.atlanmod.neoemf.benchmarks.QueryExecutorASE2015;
import fr.inria.atlanmod.neoemf.benchmarks.query.ase2015.QueryFactoryASE2015;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.UUID;

/**
 *
 */
public abstract class AbstractQueryExecutor extends AbstractExecutor implements QueryExecutor, QueryExecutorASE2015 {

    private static final Logger LOG = LogManager.getLogger();

    //@Benchmark
    @Override
    public void queryClassDeclarationAttributes() {
        try {
            QueryFactory.queryClassDeclarationAttributes(resource).callWithTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    //@Benchmark
    @Override
    public void queryGrabats() {
        try {
            QueryFactory.queryGrabats09(resource).callWithTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    //@Benchmark
    @Override
    public void queryInvisibleMethodDeclarations() {
        try {
            QueryFactory.queryInvisibleMethodDeclarations(resource).callWithTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    //@Benchmark
    @Override
    public void queryOrphanNonPrimitiveTypes() {
        try {
            QueryFactory.queryOrphanNonPrimitivesTypes(resource).callWithTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    //@Benchmark
    @Override
    public void queryRenameAllMethods() {
        try {
            String name = UUID.randomUUID().toString();
            QueryFactory.queryRenameAllMethods(resource, name).callWithTime();
            resource.save(Collections.emptyMap());
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    //@Benchmark
    @Override
    public void queryThrownExceptionsPerPackage() {
        try {
            QueryFactory.queryThrownExceptionsPerPackage(resource).callWithTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    //@Benchmark
    @Override
    public void queryUnusedMethodsList() {
        try {
            QueryFactory.queryUnusedMethodsList(resource).callWithTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    //@Benchmark
    @Override
    public void queryUnusedMethodsLoop() {
        try {
            QueryFactory.queryUnusedMethodsLoop(resource).callWithTime(); // Query result (loops)
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    //@Benchmark
    @Override
    public void queryASE2015GetBranchStatements() {
        try {
            QueryFactoryASE2015.queryAse2015GetCommentsTagContent(resource).callWithMemoryAndTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    //@Benchmark
    @Override
    public void queryASE2015Grabats09() {
        try {
            QueryFactoryASE2015.queryAse2015Grabats09(resource).callWithMemoryAndTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    //@Benchmark
    @Override
    public void queryASE2015InvisibleMethodDeclarations() {
        try {
            QueryFactoryASE2015.queryInvisibleMethodDeclarations(resource).callWithMemoryAndTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    //@Benchmark
    @Override
    public void queryASE2015SpecificInvisibleMethodDeclarations() {
        try {
            QueryFactoryASE2015.queryAse2015SpecificInvisibleMethodDeclarations(resource).callWithMemoryAndTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    //@Benchmark
    @Override
    public void queryASE2015ThrownExceptions() {
        try {
            QueryFactoryASE2015.queryAse2015ThrownExceptions(resource).callWithMemoryAndTime();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }
}
