package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import java.io.File;

public class ReadWriteRunnerState extends AbstractRunnerState {

    @Override
    protected File getResourceFile() {
        try {
            return backend.copy(resourceFile);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
