package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import com.google.common.base.CaseFormat;

import fr.inria.atlanmod.neoemf.benchmarks.datastore.Backend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.CdoBackend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.NeoMapdbBackend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.NeoNeo4jBackend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.NeoTinkerBackend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.XmiBackend;
import fr.inria.atlanmod.neoemf.benchmarks.runner.Runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.File;
import java.util.Objects;

/**
 * This state contains all the benchmarks parameters, and provides a ready-to-use {@link Backend} and the preloaded
 * resource file. The datastore is not loaded.
 */
@State(Scope.Thread)
public class RunnerState {

    protected static final Logger log = LogManager.getLogger();

    private static final String CLASS_PREFIX = Backend.class.getPackage().getName() + ".";
    private static final String CLASS_SUFFIX = Backend.class.getSimpleName();

    @Param({
            "fr.inria.atlanmod.kyanos.tests.xmi",
            "fr.inria.atlanmod.neo4emf.neo4jresolver.xmi",
            "org.eclipse.gmt.modisco.java.kyanos.xmi",
            "org.eclipse.jdt.core.xmi",
            "org.eclipse.jdt.source.all.xmi",
    })
    protected String r;

    @Param({
            XmiBackend.NAME,
            CdoBackend.NAME,
            NeoMapdbBackend.NAME,
            NeoTinkerBackend.NAME,
            NeoNeo4jBackend.NAME,
    })
    protected String b;

    private Backend backend;

    private File resourceFile;

    /**
     * Returns the current backend.
     */
    public Backend getBackend() throws Exception {
        if (Objects.isNull(backend)) {
            String className = CLASS_PREFIX + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, b) + CLASS_SUFFIX;
            backend = (Backend) Runner.class.getClassLoader().loadClass(className).newInstance();
        }
        return backend;
    }

    /**
     * Returns the current resource file.
     */
    public File getResourceFile() throws Exception {
        return resourceFile;
    }

    /**
     * Loads and creates the current resource file.
     * <p/>
     * This method is automatically called when setup the trial level.
     */
    @Setup(Level.Trial)
    public void initResource() throws Exception {
        log.info("Initializing the resource");
        resourceFile = getBackend().getOrCreateResource(r);
    }
}