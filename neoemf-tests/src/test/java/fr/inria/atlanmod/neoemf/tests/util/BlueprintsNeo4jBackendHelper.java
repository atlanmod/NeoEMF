package fr.inria.atlanmod.neoemf.tests.util;

import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class BlueprintsNeo4jBackendHelper extends BlueprintsBackendHelper {

    public static final String NAME = "Neo4j";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        return new BlueprintsResourceBuilder(ePackage).neo4j().persistent().file(file).build();
    }

    @Override
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        return new BlueprintsResourceBuilder(ePackage).neo4j().file(file).build();
    }
}
