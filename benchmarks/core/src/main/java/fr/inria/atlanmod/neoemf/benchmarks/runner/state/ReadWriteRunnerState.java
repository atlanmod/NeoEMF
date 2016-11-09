package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import java.io.File;

public class ReadWriteRunnerState extends AbstractRunnerState {

    @Override
    protected File getResourceFile() throws Exception {
        return backendInst.copy(resourceFile);
    }
}
