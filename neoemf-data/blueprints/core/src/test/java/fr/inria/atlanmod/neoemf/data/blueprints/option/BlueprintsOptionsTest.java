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
import fr.inria.atlanmod.neoemf.data.BackendConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsTest;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsUri;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link BlueprintsOptions}.
 */
@ParametersAreNonnullByDefault
public class BlueprintsOptionsTest extends AbstractUnitTest implements BlueprintsTest {

    /**
     * The {@link Resource} used for this test-case.
     */
    protected Resource resource;

    /**
     * Initializes the {@link #resource}.
     */
    @BeforeEach
    public final void initResource() {
        resource = new ResourceSetImpl().createResource(BlueprintsUri.builder().fromFile(file()));
    }

    /**
     * Cleanly closes the {@link #resource}.
     */
    @AfterEach
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

        BackendConfig config = getConfig();
        assertThat(config.get(BlueprintsResourceOptions.GRAPH_TYPE)).isEqualTo(BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
        assertConfigurationHasSize(config, 4);
    }

    /**
     * Retrieves the {@link BackendConfig} according to the current {@link #file()}.
     *
     * @return the current configuration
     */
    protected BackendConfig getConfig() {
        Path configFile = file().toPath().resolve("config.properties");
        assertThat(configFile).exists();

        try {
            return BackendConfig.load(configFile);
        }
        catch (IOException e) {
            throw new IllegalStateException(e); // Should never happen
        }
    }

    /**
     * Checks that the {@code config} has the expected {@code size}.
     *
     * @param config       the configuration to check
     * @param expectedSize the expected size
     */
    protected void assertConfigurationHasSize(BackendConfig config, int expectedSize) {
        assertThat(config.asMap().size()).isEqualTo(expectedSize);
    }

    /**
     * Checks that the {@code config} has the expected {@code value} for the given {@code key}.
     *
     * @param config the configuration to check
     * @param key    the key to look for
     * @param value  the expected value
     */
    protected void assertConfigurationHasEntry(BackendConfig config, String key, String value) {
        assertThat(config.has(key)).isTrue();
        assertThat(config.get(key)).isEqualTo(value);
    }
}
