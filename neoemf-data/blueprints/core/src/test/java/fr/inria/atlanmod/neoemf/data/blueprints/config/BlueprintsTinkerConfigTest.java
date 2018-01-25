/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.config;

import fr.inria.atlanmod.neoemf.AbstractUnitTest;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsContext;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsUri;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link BlueprintsTinkerConfig}.
 */
@ParametersAreNonnullByDefault
public class BlueprintsTinkerConfigTest extends AbstractUnitTest {

    /**
     * The {@link Resource} used for this test-case.
     */
    protected PersistentResource resource;

    @Nonnull
    @Override
    protected Context context() {
        return BlueprintsContext.getDefault();
    }

    /**
     * Initializes the {@link #resource}.
     */
    @BeforeEach
    public void initResource() throws IOException {
        URI uri = BlueprintsUri.builder().fromFile(currentTempFile());
        resource = PersistentResource.class.cast(new ResourceSetImpl().createResource(uri));
    }

    /**
     * Cleanly closes the {@link #resource}.
     */
    @AfterEach
    public void closeResource() {
        resource.unload();
    }

    /**
     * Checks the definition of the {@link BlueprintsTinkerConfig#BLUEPRINTS_GRAPH} option, with the default type.
     */
    @Test
    public void testDefaultGraphTypeOption() throws IOException {
        resource.save(BlueprintsTinkerConfig.newConfig());

        ImmutableConfig config = loadConfig();
        assertConfigurationHasEntry(config, BlueprintsTinkerConfig.BLUEPRINTS_GRAPH, BlueprintsTinkerConfig.BLUEPRINTS_GRAPH_TINKER);
        assertConfigurationHasSize(config, 5);
    }

    /**
     * Retrieves the configuration according to the current {@link #currentTempFile()}.
     *
     * @return the current configuration
     */
    @Nonnull
    protected ImmutableConfig loadConfig() throws IOException {
        Path configPath = currentTempFile().toPath();

        Optional<Config> config = Config.load(configPath);
        assertThat(config).isNotEmpty();

        return config.<IllegalStateException>orElseThrow(IllegalStateException::new);
    }

    /**
     * Checks that the {@code config} has the expected {@code size}.
     *
     * @param config       the configuration to check
     * @param expectedSize the expected size
     */
    protected void assertConfigurationHasSize(ImmutableConfig config, int expectedSize) {
        assertThat(config.toMap()).hasSize(expectedSize + 2);
    }

    /**
     * Checks that the {@code config} has the expected {@code value} for the given {@code key}.
     *
     * @param config the configuration to check
     * @param key    the key to look for
     * @param value  the expected value
     */
    protected void assertConfigurationHasEntry(ImmutableConfig config, String key, String value) {
        assertThat(config.hasOption(key)).isTrue();
        assertThat(config.<String>getOption(key)).contains(value);
    }
}
