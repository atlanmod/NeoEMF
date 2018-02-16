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

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.Collection;
import java.util.Map;

/**
 * A {@link Runner} that provides benchmark methods for read-only queries.
 */
public class ReadOnlyRunner extends Runner {

    @Benchmark
    public Long traverse(ReadOnlyRunnerState state) {
        return QueryFactory.countAllElements().executeOn(state.resource());
    }

    @Benchmark
    public Map<String, Iterable<NamedElement>> classDeclarationAttributes(ReadOnlyRunnerState state) {
        return QueryFactory.getClassFields().executeOn(state.resource());
    }

    @Benchmark
    public Collection<ClassDeclaration> grabats(ReadOnlyRunnerState state) {
        return QueryFactory.grabats().executeOn(state.resource());
    }

    @Benchmark
    public Collection<MethodDeclaration> invisibleMethodDeclarations(ReadOnlyRunnerState state) {
        return QueryFactory.getInvisibleMethods().executeOn(state.resource());
    }

    @Benchmark
    public Collection<Type> orphanNonPrimitiveTypes(ReadOnlyRunnerState state) {
        return QueryFactory.getOrphanNonPrimitiveTypes().executeOn(state.resource());
    }

    @Benchmark
    public Map<String, Iterable<TypeAccess>> thrownExceptionsPerPackage(ReadOnlyRunnerState state) {
        return QueryFactory.getThrownExceptionsPerPackage().executeOn(state.resource());
    }

    @Benchmark
    public Collection<MethodDeclaration> unusedMethodsWithList(ReadOnlyRunnerState state) {
        return QueryFactory.getUnusedMethodsWithList().executeOn(state.resource());
    }

    @Benchmark
    public Collection<MethodDeclaration> unusedMethodsWithLoop(ReadOnlyRunnerState state) {
        return QueryFactory.getUnusedMethodsWithLoop().executeOn(state.resource());
    }

    // region ASE 2015

    @Benchmark
    public Collection<TextElement> ase15CommentsTagContent(ReadOnlyRunnerState state) {
        return QueryFactoryASE.getTagComments().executeOn(state.resource());
    }

    @Benchmark
    public Collection<ClassDeclaration> ase15Grabats(ReadOnlyRunnerState state) {
        return QueryFactoryASE.grabats().executeOn(state.resource());
    }

    @Benchmark
    public Collection<MethodDeclaration> ase15SpecificInvisibleMethodDeclarations(ReadOnlyRunnerState state) {
        return QueryFactoryASE.getInvisibleMethodsSpecific().executeOn(state.resource());
    }

    @Benchmark
    public Collection<TypeAccess> ase15ThrownExceptions(ReadOnlyRunnerState state) {
        return QueryFactoryASE.getThrownExceptions().executeOn(state.resource());
    }

    @Benchmark
    public Collection<Statement> ase15BranchStatements(ReadOnlyRunnerState state) {
        return QueryFactoryASE.getBranchStatements().executeOn(state.resource());
    }

    //endregion
}
