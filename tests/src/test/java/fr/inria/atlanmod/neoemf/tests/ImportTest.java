package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.io.xmi.BlueprintsPersistenceHandler;
import fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.resources.BlueprintsNeo4jResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.io.AllIOTest;
import fr.inria.atlanmod.neoemf.io.CounterHandler;
import fr.inria.atlanmod.neoemf.io.IOManager;
import fr.inria.atlanmod.neoemf.io.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.input.xmi.XmiSaxReader;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportTest extends AllIOTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private File neo4jFile;

    @Before
    public void setUp() throws Exception {
        String timestamp = String.valueOf(new Date().getTime());
        neo4jFile = temporaryFolder.getRoot().toPath().resolve("import-Neo4j" + timestamp).toFile();
    }

    @Test
    public void testImportNeo4jThin() throws Exception {
        testImportWithSax(getThinXmi(), getNeo4jHandler());
    }

    @Test
    public void testImportNeo4jLight() throws Exception {
        testImportWithSax(getLightXmi(), getNeo4jHandler());
    }

    @Test
    public void testImportNeo4jMedium() throws Exception {
        testImportWithSax(getMediumXmi(), getNeo4jHandler());
    }

    @Test
    @Ignore("XMI file not present in commit")
    public void testImportNeo4jHeavy() throws Exception {
        testImportWithSax(getHeavyXmi(), getNeo4jHandler());
    }

    @Test
    @Ignore("XMI file not present in commit")
    public void testImportNeo4jMonster() throws Exception {
        // FIXME JVM crashed when loading this file
        testImportWithSax(getMonsterXmi(), getNeo4jHandler());
    }

    private PersistenceHandler getNeo4jHandler() throws Exception {
        Map<String, Object> options = new HashMap<>();

        List<PersistentResourceOptions.StoreOption> storeOptions = new ArrayList<>();
        storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.DIRECT_WRITE);

        options.put(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_TYPE_NEO4J);

        options.put(PersistentResourceOptions.STORE_OPTIONS, storeOptions);

        BlueprintsPersistenceBackend neo4jBackend = (BlueprintsPersistenceBackend)
                BlueprintsPersistenceBackendFactory.getInstance().createPersistentBackend(neo4jFile, options);

        return new CounterHandler(new BlueprintsPersistenceHandler(neo4jBackend), "counter1");
    }

    private void testImportWithSax(File file, PersistenceHandler persistenceHandler) throws Exception {
        try {
            IOManager.importFromFile(file, XmiSaxReader.class, persistenceHandler);
        } catch (Exception e) {
            NeoLogger.error(e);
            throw e;
        }
    }
}
