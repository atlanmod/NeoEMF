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

package fr.inria.atlanmod.neoemf.graph.blueprints.option;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

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
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class BlueprintsResourceSaveTest extends AllTest {

    private static final String TEST_FILENAME = "graphResourceSaveOptionTestFile";
    protected final String configFileName = "/config.properties";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    protected String testFilePath = TEST_FILENAME;
    protected File testFile;

    protected PersistenceBackendFactory persistenceBackendFactory;
    protected ResourceSet resSet;
    protected Resource resource;

    @Before
    public void setUp() {
        persistenceBackendFactory = BlueprintsPersistenceBackendFactory.getInstance();

        PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME, persistenceBackendFactory);
        testFile = temporaryFolder.getRoot().toPath().resolve(testFilePath + new Date().getTime()).toFile();
        resSet = new ResourceSetImpl();
        resSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
        resource = resSet.createResource(BlueprintsURI.createFileURI(testFile));
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
            }
            catch (IOException e) {
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
        assertThat(configuration.containsKey(BlueprintsResourceOptions.GRAPH_TYPE)).isTrue();
        assertThat(configuration.getString(BlueprintsResourceOptions.GRAPH_TYPE)).isEqualTo(BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
        assertThat(getKeyCount(configuration)).isEqualTo(3); // "Too much content in the .properties file"
    }

    @Test
    public void testSaveGraphResourceDefaultGraphTypeOption() throws IOException, ConfigurationException {
        resource.save(BlueprintsOptionsBuilder.newBuilder().asMap());

        File configFile = new File(testFile + configFileName);
        assertThat(configFile).exists(); // "Config file does not exist"

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertThat(configuration.containsKey(BlueprintsResourceOptions.GRAPH_TYPE)).isTrue();
        assertThat(configuration.getString(BlueprintsResourceOptions.GRAPH_TYPE)).isEqualTo(BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
        assertThat(getKeyCount(configuration)).isEqualTo(3); // "Too much content in the .properties file"
    }
}