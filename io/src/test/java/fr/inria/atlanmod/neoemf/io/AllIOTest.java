package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.AllTest;

import java.io.File;

public abstract class AllIOTest extends AllTest {

    private static final String THIN_XMI = "/fr.inria.atlanmod.kyanos.tests.xmi";
    private static final String LIGHT_XMI = "/fr.inria.atlanmod.neo4emf.neo4jresolver.xmi";
    private static final String MEDIUM_XMI = "/org.eclipse.gmt.modisco.java.kyanos.xmi";
    private static final String HEAVY_XMI = "/org.eclipse.jdt.core.xmi";
    private static final String MONSTER_XMI = "/org.eclipse.jdt.source.all.xmi";

    protected File getThinXmi() {
        return getResourceFile(THIN_XMI);
    }

    protected File getLightXmi() {
        return getResourceFile(LIGHT_XMI);
    }

    protected File getMediumXmi() {
        return getResourceFile(MEDIUM_XMI);
    }

    protected File getHeavyXmi() {
        return getResourceFile(HEAVY_XMI);
    }

    protected File getMonsterXmi() {
        return getResourceFile(MONSTER_XMI);
    }

    private File getResourceFile(String path) {
        return new File(XmiReaderTest.class.getResource(path).getFile());
    }
}
