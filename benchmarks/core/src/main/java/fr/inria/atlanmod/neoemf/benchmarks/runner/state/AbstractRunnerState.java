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

    /**
     * The {@link Resource} instance.
     */
    protected Resource resourceInst;

    protected File resourceFile;

    /**
     * The {@link Backend} instance.
     */
    protected Backend backendInst;

    @Param({
            "fr.inria.atlanmod.kyanos.tests.xmi",
            "fr.inria.atlanmod.neo4emf.neo4jresolver.xmi",
            "org.eclipse.gmt.modisco.java.kyanos.xmi",
            "org.eclipse.jdt.core.xmi",
            "org.eclipse.jdt.source.all.xmi",
    })
    protected String resource;

    @Param({
            XmiBackend.NAME,
            CdoBackend.NAME,
            NeoMapBackend.NAME,
            NeoGraphBackend.NAME,
            NeoGraphNeo4jBackend.NAME,
    })
    protected String backend;

    public Backend getBackend() {
        if (Objects.isNull(backendInst)) {
            throw new NullPointerException();
        }
        return backendInst;
    }

    public Resource getResource() {
        if (Objects.isNull(resourceInst)) {
            throw new NullPointerException();
        }
        return resourceInst;
    }

    protected abstract File getResourceFile() throws Exception;

    @Setup(Level.Trial)
    public void setupTrial() throws Exception {
        String className = CLASS_PREFIX + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, backend) + CLASS_SUFFIX;
        backendInst = (Backend) Runner.class.getClassLoader().loadClass(className).newInstance();
        resourceFile = backendInst.create(resource);
    }

    @Setup(Level.Invocation)
    public void setupInvocation() throws Exception {
        resourceInst = backendInst.load(getResourceFile());
    }

    @TearDown(Level.Invocation)
    public void tearDownInvocation() throws Exception {
        backendInst.unload(resourceInst);
        resourceInst = null;
        Backend.clean();
    }
}