package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import java.io.File;

public class ReadOnlyRunnerState extends AbstractRunnerState {

    @Override
    protected File getResourceFile() {
        return resourceFile;
    }
}
