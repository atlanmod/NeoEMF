/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.graph.blueprints.resources;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BlueprintsResourceSaveTest extends AllTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private static final String TEST_FILENAME = "graphResourceSaveOptionTestFile";

    protected String testFilePath = TEST_FILENAME;

    protected String configFileName = "/config.properties";

    protected PersistenceBackendFactory persistenceBackendFactory;
    protected File testFile;
    protected Map<Object, Object> options;
    protected ResourceSet resSet;
    protected Resource resource;

    @Before
    public void setUp() {
        options = new HashMap<>();
        persistenceBackendFactory = BlueprintsPersistenceBackendFactory.getInstance();

        PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, persistenceBackendFactory);
        testFile = temporaryFolder.getRoot().toPath().resolve(testFilePath + new Date().getTime()).toFile();
        resSet = new ResourceSetImpl();
        resSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.getInstance());
        resource = resSet.createResource(NeoBlueprintsURI.createNeoGraphURI(testFile));
    }

    @After
    public void tearDown() {
        resource.unload();
        resSet.getResourceFactoryRegistry().getProtocolToFactoryMap().clear();
        PersistenceBackendFactoryRegistry.unregisterAll();

        temporaryFolder.delete();

        if (temporaryFolder.getRoot().exists()) {
            try {
                FileUtils.forceDeleteOnExit(temporaryFolder.getRoot());
            } catch (IOException e) {
                NeoLogger.warn(e);
            }
        }

        testFile = null;
    }

    protected int getKeyCount(PropertiesConfiguration configuration) {
        Iterator<String> keyIterator = configuration.getKeys();
        int keyCount = 0;
        while (keyIterator.hasNext()) {
            keyCount++;
            keyIterator.next();
        }
        return keyCount;
    }

    @Test
    public void testSaveGraphResourceNoOption() throws IOException, ConfigurationException {
        resource.save(Collections.emptyMap());

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists(); // "Config file does not exist"

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertThat(configuration.containsKey(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE)).isTrue();
        assertThat(configuration.getString(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE)).isEqualTo(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE_DEFAULT);
        assertThat(getKeyCount(configuration)).isEqualTo(3); // "Too much content in the .properties file"
    }

    @Test
    public void testSaveGraphResourceDefaultGraphTypeOption() throws IOException, ConfigurationException {
        options.put(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE, BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE_DEFAULT);
        resource.save(options);

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists(); // "Config file does not exist"

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertThat(configuration.containsKey(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE)).isTrue();
        assertThat(configuration.getString(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE)).isEqualTo(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE_DEFAULT);
        assertThat(getKeyCount(configuration)).isEqualTo(3); // "Too much content in the .properties file"
    }
}
