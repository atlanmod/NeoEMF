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

package fr.inria.atlanmod.neoemf.data.blueprints.option;

import fr.inria.atlanmod.neoemf.AbstractUnitTest;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.InvalidOptionsException;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class BlueprintsResourceSaveTest extends AbstractUnitTest {

    protected final String configFileName = "/config.properties";

    protected Resource resource;

    private ResourceSet resourceSet;

    @Before
    public void setUp() {
        resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
        resource = resourceSet.createResource(BlueprintsURI.createFileURI(file()));
    }

    @After
    public void tearDown() {
        resource.unload();
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().clear();
    }

    @Override
    protected String name() {
        return "Blueprints";
    }

    @Override
    protected String uriScheme() {
        return BlueprintsURI.SCHEME;
    }

    @Override
    protected PersistenceBackendFactory persistenceBackendFactory() throws InvalidDataStoreException, InvalidOptionsException {
        return BlueprintsPersistenceBackendFactory.getInstance();
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

        File configFile = new File(file() + configFileName);
        assertThat(configFile).exists(); // "Config file does not exist"

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertThat(configuration.containsKey(BlueprintsResourceOptions.GRAPH_TYPE)).isTrue();
        assertThat(configuration.getString(BlueprintsResourceOptions.GRAPH_TYPE)).isEqualTo(BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
        assertThat(getKeyCount(configuration)).isEqualTo(3); // "Too much content in the .properties file"
    }

    @Test
    public void testSaveGraphResourceDefaultGraphTypeOption() throws IOException, ConfigurationException {
        resource.save(BlueprintsOptionsBuilder.newBuilder().asMap());

        File configFile = new File(file() + configFileName);
        assertThat(configFile).exists(); // "Config file does not exist"

        PropertiesConfiguration configuration = new PropertiesConfiguration(configFile);
        assertThat(configuration.containsKey(BlueprintsResourceOptions.GRAPH_TYPE)).isTrue();
        assertThat(configuration.getString(BlueprintsResourceOptions.GRAPH_TYPE)).isEqualTo(BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
        assertThat(getKeyCount(configuration)).isEqualTo(3); // "Too much content in the .properties file"
    }
}
