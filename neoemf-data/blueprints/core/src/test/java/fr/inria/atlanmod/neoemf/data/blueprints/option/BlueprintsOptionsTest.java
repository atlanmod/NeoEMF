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
import fr.inria.atlanmod.neoemf.data.Configuration;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsTest;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsUri;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of {@link BlueprintsOptions}.
 */
public class BlueprintsOptionsTest extends AbstractUnitTest implements BlueprintsTest {

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
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsUri.SCHEME, PersistentResourceFactory.getInstance());
        resource = resourceSet.createResource(BlueprintsUri.builder().fromFile(file()));
    }

    /**
     * Cleanly closes the {@link #resource}.
     */
    @After
    public final void closeResource() {
        resource.unload();
    }

    /**
     * Checks the definition of the {@link BlueprintsResourceOptions#GRAPH_TYPE} option, with the default type.
     *
     * @throws IOException if an I/O error occurs during the saving of the resource
     */
    @Test
    public void testDefaultGraphTypeOption() throws IOException {
        resource.save(BlueprintsOptions.noOption());

        Configuration configuration = getConfiguration();
        assertThat(configuration.get(BlueprintsResourceOptions.GRAPH_TYPE)).isEqualTo(BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
        assertConfigurationHasSize(configuration, 3);
    }

    /**
     * Retrieves the {@link Configuration} according to the current {@link #file()}.
     *
     * @return the current configuration
     */
    protected Configuration getConfiguration() {
        Path configurationFile = file().toPath().resolve("config.properties");
        assertThat(configurationFile).exists();

        try {
            return Configuration.load(configurationFile);
        }
        catch (IOException e) {
            throw new IllegalStateException(e); // Should not happen
        }
    }

    /**
     * Checks that the {@code configuration} has the expected {@code size}.
     *
     * @param configuration the configuration to check
     * @param expectedSize  the expected size
     */
    protected void assertConfigurationHasSize(Configuration configuration, int expectedSize) {
        assertThat(configuration.asMap().size()).isEqualTo(expectedSize);
    }

    /**
     * Checks that the {@code configuration} has the expected {@code value} for the given {@code key}.
     *
     * @param configuration the configuration to check
     * @param key           the key to look for
     * @param value         the expected value
     */
    protected void assertConfigurationHasEntry(Configuration configuration, String key, String value) {
        assertThat(configuration.contains(key)).isTrue();
        assertThat(configuration.get(key)).isEqualTo(value);
    }
}
