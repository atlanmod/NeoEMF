package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import com.google.common.base.CaseFormat;

import fr.inria.atlanmod.neoemf.benchmarks.datastore.Backend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.CdoBackend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.NeoGraphBackend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.NeoGraphNeo4jBackend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.NeoMapBackend;
import fr.inria.atlanmod.neoemf.benchmarks.datastore.XmiBackend;
import fr.inria.atlanmod.neoemf.benchmarks.runner.Runner;

import org.eclipse.emf.ecore.resource.Resource;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import java.io.File;
import java.util.Objects;

@State(Scope.Thread)
public abstract class AbstractRunnerState {

    private static final String CLASS_PREFIX = Backend.class.getPackage().getName() + ".";
    private static final String CLASS_SUFFIX = Backend.class.getSimpleName();

    protected Resource resource;
    protected File resourceFile;

    protected Backend backend;

    @Param({
            "fr.inria.atlanmod.kyanos.tests.xmi",
            "fr.inria.atlanmod.neo4emf.neo4jresolver.xmi",
            "org.eclipse.gmt.modisco.java.kyanos.xmi",
            "org.eclipse.jdt.core.xmi",
            "org.eclipse.jdt.source.all.xmi",
    })
    private String r;

    @Param({
            XmiBackend.NAME,
            CdoBackend.NAME,
            NeoMapBackend.NAME,
            NeoGraphBackend.NAME,
            NeoGraphNeo4jBackend.NAME,
    })
    private String b;

    public Backend getBackend() {
        if (Objects.isNull(backend)) {
            throw new NullPointerException();
        }
        return backend;
    }

    public Resource getResource() {
        if (Objects.isNull(resource)) {
            throw new NullPointerException();
        }
        return resource;
    }

    protected abstract File getResourceFile() throws Exception;

    @Setup(Level.Trial)
    public void setupTrial() throws Exception {
        String className = CLASS_PREFIX + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, b) + CLASS_SUFFIX;
        backend = (Backend) Runner.class.getClassLoader().loadClass(className).newInstance();
        resourceFile = backend.create(r);
    }

    @Setup(Level.Invocation)
    public void setupInvocation() throws Exception {
        resource = backend.load(getResourceFile());
    }

    @TearDown(Level.Invocation)
    public void tearDownInvocation() throws Exception {
        if (!Objects.isNull(resource)) {
            backend.unload(resource);
            resource = null;
        }
        Backend.clean();
    }
}