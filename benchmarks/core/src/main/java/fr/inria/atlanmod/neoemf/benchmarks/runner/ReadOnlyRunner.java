package fr.inria.atlanmod.neoemf.benchmarks.runner;

import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactoryAse15;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadOnlyRunnerState;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * A {@link Runner} that provides benchmark methods for read-only queries.
 */
public class ReadOnlyRunner extends Runner {

    @Benchmark
    public long traverse(ReadOnlyRunnerState state) {
        return QueryFactory.queryCountAllElements().apply(state.resource());
    }

    @Benchmark
    public int classDeclarationAttributes(ReadOnlyRunnerState state) {
        return QueryFactory.queryClassDeclarationAttributes().apply(state.resource());
    }

    @Benchmark
    public int grabats(ReadOnlyRunnerState state) {
        return QueryFactory.queryGrabats().apply(state.resource());
    }

    @Benchmark
    public int invisibleMethodDeclarations(ReadOnlyRunnerState state) {
        return QueryFactory.queryInvisibleMethodDeclarations().apply(state.resource());
    }

    @Benchmark
    public int orphanNonPrimitiveTypes(ReadOnlyRunnerState state) {
        return QueryFactory.queryOrphanNonPrimitivesTypes().apply(state.resource());
    }

    @Benchmark
    public int thrownExceptionsPerPackage(ReadOnlyRunnerState state) {
        return QueryFactory.queryThrownExceptionsPerPackage().apply(state.resource());
    }

    @Benchmark
    public int unusedMethodsWithList(ReadOnlyRunnerState state) {
        return QueryFactory.queryUnusedMethodsWithList().apply(state.resource());
    }

    @Benchmark
    public int unusedMethodsWithLoop(ReadOnlyRunnerState state) {
        return QueryFactory.queryUnusedMethodsWithLoop().apply(state.resource());
    }

    // region ASE 2015

    @Benchmark
    public int ase15CommentsTagContent(ReadOnlyRunnerState state) {
        return QueryFactoryAse15.queryCommentsTagContent().apply(state.resource());
    }

    @Benchmark
    public int ase15Grabats(ReadOnlyRunnerState state) {
        return QueryFactoryAse15.queryGrabats().apply(state.resource());
    }

    @Benchmark
    public int ase15SpecificInvisibleMethodDeclarations(ReadOnlyRunnerState state) {
        return QueryFactoryAse15.querySpecificInvisibleMethodDeclarations().apply(state.resource());
    }

    @Benchmark
    public int ase15ThrownExceptions(ReadOnlyRunnerState state) {
        return QueryFactoryAse15.queryThrownExceptions().apply(state.resource());
    }

    @Benchmark
    public int ase15BranchStatements(ReadOnlyRunnerState state) {
        return QueryFactoryAse15.queryBranchStatements().apply(state.resource());
    }

    //endregion
}
