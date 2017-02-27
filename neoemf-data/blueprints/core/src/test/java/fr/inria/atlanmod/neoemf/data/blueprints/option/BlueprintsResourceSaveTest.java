/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
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
import fr.inria.atlanmod.neoemf.data.PersistenceConfiguration;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsTest;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class BlueprintsResourceSaveTest extends AbstractUnitTest implements BlueprintsTest {

    /**
     * The {@link Resource} used for this test-case.
     */
    protected Resource resource;

    /**
     * Initializes the {@link #resource}.
     */
    @Before
    public final void initResource() {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
        resource = resourceSet.createResource(BlueprintsURI.createFileURI(file()));
    }

    /**
     * Cleanly closes the {@link #resource}.
     */
    @After
    public final void closeResource() {
        resource.unload();
    }

    @Test
    public void testSaveGraphResourceNoOption() throws IOException {
        resource.save(Collections.emptyMap());

        PersistenceConfiguration configuration = getConfiguration();
        assertThat(configuration.getProperty(BlueprintsResourceOptions.GRAPH_TYPE)).isEqualTo(BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
        assertConfigurationHasSize(configuration, 3);
    }

    @Test
    public void testSaveGraphResourceDefaultGraphTypeOption() throws IOException {
        resource.save(BlueprintsOptions.noOption());

        PersistenceConfiguration configuration = getConfiguration();
        assertThat(configuration.getProperty(BlueprintsResourceOptions.GRAPH_TYPE)).isEqualTo(BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Retrieves the {@link PersistenceConfiguration} according to the current {@link #file()}.
     *
     * @return the current configuration
     */
    protected PersistenceConfiguration getConfiguration() {
        File configurationFile = file().toPath().resolve("config.properties").toFile();
        assertThat(configurationFile).exists();
        return PersistenceConfiguration.load(configurationFile);
    }

    /**
     * Checks that the {@code configuration} has the expected {@code size}.
     *
     * @param configuration the configuration to check
     * @param expectedSize  the expected size
     */
    protected void assertConfigurationHasSize(PersistenceConfiguration configuration, int expectedSize) {
        assertThat(configuration.asMap().size()).isEqualTo(expectedSize);
    }

    /**
     * Checks that the {@code configuration} has the expected {@code value} for the given {@code key}.
     *
     * @param configuration the configuration to check
     * @param key           the key to look for
     * @param value         the expected value
     */
    protected void assertConfigurationHasEntry(PersistenceConfiguration configuration, String key, String value) {
        assertThat(configuration.containsKey(key)).isTrue();
        assertThat(configuration.getProperty(key)).isEqualTo(value);
    }
}
