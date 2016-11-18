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
 * <p/>
 * It is used for simple queries.
 */
public class ReadOnlyRunnerState extends RunnerState {

    protected Resource resource;

    protected File storeFile;

    /**
     * Returns the current resource loaded from the datastore.
     */
    public Resource getResource() {
        if (Objects.isNull(resource)) {
            throw new NullPointerException();
        }
        return resource;
    }

    protected File getStoreLocation() {
        return storeFile;
    }

    /**
     * Loads and creates the current datastore.
     * <p/>
     * This method is automatically called when setup the trial level.
     */
    @Setup(Level.Trial)
    public void initStore() throws Exception {
        log.info("Initializing the datastore");
        storeFile = getBackend().createStore(getResourceFile());
    }

    /**
     * Loads the current resource.
     * <p/>
     * This method is automatically called when setup the invocation level.
     */
    @Setup(Level.Invocation)
    public void loadResource() throws Exception {
        log.info("Loading the resource");
        resource = getBackend().load(getStoreLocation());
    }

    /**
     * Unloads the current resource.
     * <p/>
     * This method is automatically called when tear down the invocation level.
     */
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
