package fr.inria.atlanmod.neoemf.benchmarks.runner;

import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadWriteRunnerState;

import org.eclipse.emf.ecore.resource.Resource;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.IOException;
import java.util.UUID;

/**
 * A {@link Runner} that provides benchmark methods for read-write queries.
 */
public class ReadWriteRunner extends Runner {

    @Benchmark
    public int renameAllMethods(ReadWriteRunnerState state) throws IOException {
        String name = UUID.randomUUID().toString();
        Resource resource = state.resource();
        Integer result = QueryFactory.queryRenameAllMethods(name).apply(resource);
        state.adapter().save(resource, state.options());
        return result;
    }
}
