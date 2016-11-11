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
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadOnlyRunnerState;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadWriteRunnerState;

import org.eclipse.emf.ecore.resource.Resource;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Timeout;
import org.openjdk.jmh.annotations.Warmup;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@Timeout(time = 1, timeUnit = TimeUnit.HOURS)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Fork(
        value = 1,
        jvmArgs = {
                "-Dfile.encoding=utf-8",
                "-server",
                "-XX:+UseConcMarkSweepGC",
                "-XX:+DisableExplicitGC",
                "-XX:+CMSClassUnloadingEnabled"
        },
        jvmArgsAppend = "-Xmx8g"
)
public class Runner {

    /*
     * Simple queries.
     */

    @Benchmark
    public Integer traverse(ReadOnlyRunnerState state) throws Exception {
        return QueryFactory.queryCountAllElements(state.getResource()).callWithTime();
    }

    /*
     * Read-only queries.
     */

    @Benchmark
    public Integer classDeclarationAttributes(ReadOnlyRunnerState state) throws Exception {
        return QueryFactory.queryClassDeclarationAttributes(state.getResource()).callWithTime();
    }

    @Benchmark
    public Integer grabats(ReadOnlyRunnerState state) throws Exception {
        return QueryFactory.queryGrabats(state.getResource()).callWithTime();
    }

    @Benchmark
    public Integer invisibleMethodDeclarations(ReadOnlyRunnerState state) throws Exception {
        return QueryFactory.queryInvisibleMethodDeclarations(state.getResource()).callWithTime();
    }

    @Benchmark
    public Integer orphanNonPrimitiveTypes(ReadOnlyRunnerState state) throws Exception {
        return QueryFactory.queryOrphanNonPrimitivesTypes(state.getResource()).callWithTime();
    }

    @Benchmark
    public Integer thrownExceptionsPerPackage(ReadOnlyRunnerState state) throws Exception {
        return QueryFactory.queryThrownExceptionsPerPackage(state.getResource()).callWithTime();
    }

    @Benchmark
    public Integer unusedMethodsWithList(ReadOnlyRunnerState state) throws Exception {
        return QueryFactory.queryUnusedMethodsWithList(state.getResource()).callWithTime();
    }

    @Benchmark
    public Integer unusedMethodsWithLoop(ReadOnlyRunnerState state) throws Exception {
        return QueryFactory.queryUnusedMethodsWithLoop(state.getResource()).callWithTime();
    }

    /*
     * Read/Write queries.
     */

    @Benchmark
    public Void renameAllMethods(ReadWriteRunnerState state) throws Exception {
        String name = UUID.randomUUID().toString();
        Resource resource = state.getResource();
        Void result = QueryFactory.queryRenameAllMethods(resource, name).callWithTime();
        state.getBackend().save(resource);
        return result;
    }

    /*
     * ASE 2015 queries.
     */

    @Benchmark
    public Integer commentsTagContentAse2015(ReadOnlyRunnerState state) throws Exception {
        return QueryFactoryASE2015.queryCommentsTagContent(state.getResource()).callWithMemoryUsage();
    }

    @Benchmark
    public Integer grabatsAse2015(ReadOnlyRunnerState state) throws Exception {
        return QueryFactoryASE2015.queryGrabats(state.getResource()).callWithMemoryUsage();
    }

    @Benchmark
    public Integer specificInvisibleMethodDeclarationsASE2015(ReadOnlyRunnerState state) throws Exception {
        return QueryFactoryASE2015.querySpecificInvisibleMethodDeclarations(state.getResource()).callWithMemoryUsage();
    }

    @Benchmark
    public Integer thrownExceptionsASE2015(ReadOnlyRunnerState state) throws Exception {
        return QueryFactoryASE2015.queryThrownExceptions(state.getResource()).callWithMemoryUsage();
    }
}
