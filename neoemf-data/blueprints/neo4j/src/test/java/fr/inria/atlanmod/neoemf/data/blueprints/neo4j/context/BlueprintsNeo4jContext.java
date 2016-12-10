package fr.inria.atlanmod.neoemf.data.blueprints.neo4j.context;

import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsContext;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

public class BlueprintsNeo4jContext extends BlueprintsContext {

    public static final String NAME = "Neo4j";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        return new BlueprintsNeo4jResourceBuilder(ePackage).neo4j().persistent().file(file).build();
    }

    @Override
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        return new BlueprintsNeo4jResourceBuilder(ePackage).neo4j().file(file).build();
    }
}
