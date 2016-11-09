package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import java.io.File;

public class ReadRunnerState extends AbstractRunnerState {

    @Override
    protected File getResourceFile() throws Exception {
        return resourceFile;
    }
}
