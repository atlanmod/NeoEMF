package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import java.io.File;

/**
 * This state provided a ready-to-use datastore. It is preloaded and unloaded automatically from a temporary copy of the
 * default datastore, to avoid overwriting the original datastore.
 */
public class ReadWriteRunnerState extends ReadOnlyRunnerState {

    @Override
    protected File getStoreLocation() {
        try {
            return getBackend().copy(super.getStoreLocation());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
