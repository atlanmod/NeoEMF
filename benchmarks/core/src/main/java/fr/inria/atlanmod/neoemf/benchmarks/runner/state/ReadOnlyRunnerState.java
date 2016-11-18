package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import fr.inria.atlanmod.neoemf.benchmarks.datastore.Backend;

import org.eclipse.emf.ecore.resource.Resource;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;

import java.io.File;
import java.util.Objects;

/**
 * This state provided a ready-to-use datastore. It is automatically preloaded and unloaded from the default location.
 */
public class ReadOnlyRunnerState extends RunnerState {

    protected Resource resource;

    protected File storeFile;

    /**
     * Returns the resource loaded from the datastore.
     */
    public Resource getResource() {
        if (Objects.isNull(resource)) {
            throw new NullPointerException();
        }
        return resource;
    }

    /**
     * Returns the location of the datastore.
     */
    protected File getStoreLocation() {
        return storeFile;
    }

    @Setup(Level.Trial)
    public void initResource() throws Exception {
        log.info("Initializing the datastore");
        storeFile = getBackend().createStore(getResourceFile());
    }

    @Setup(Level.Invocation)
    public void loadResource() throws Exception {
        log.info("Loading the resource");
        resource = getBackend().load(getStoreLocation());
    }

    @TearDown(Level.Invocation)
    public void unloadResource() throws Exception {
        log.info("Unloading the resource");
        if (!Objects.isNull(resource)) {
            getBackend().unload(resource);
            resource = null;
        }
        Backend.clean();
    }
}
