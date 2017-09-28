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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.commons.AbstractTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link BackendFactoryRegistry}.
 */
@ParametersAreNonnullByDefault
public class BackendFactoryRegistryTest extends AbstractTest {

    /**
     * The instance of the registry.
     */
    private static final BackendFactoryRegistry REGISTRY = BackendFactoryRegistry.getInstance();

    /**
     * The fake URI scheme of the first {@link BackendFactory}.
     */
    private static final String MOCK_1 = "mock1";

    /**
     * The fake URI scheme of the second {@link BackendFactory}.
     */
    private static final String MOCK_2 = "mock2";

    /**
     * The first {@link BackendFactory}.
     */
    private final BackendFactory factory1 = mock(BackendFactory.class);

    /**
     * The second {@link BackendFactory}.
     */
    private final BackendFactory factory2 = mock(BackendFactory.class);

    /**
     * Unregisters all factories in the {@link BackendFactoryRegistry}.
     */
    @BeforeEach
    void unregisterFactories() throws Exception {
        REGISTRY.unregisterAll();
    }

    /**
     * Checks the registration of one {@link BackendFactory}.
     */
    @Test
    public void testSingleAdd() throws Exception {
        REGISTRY.register(MOCK_1, factory1);
        assertThat(REGISTRY.getFactories()).hasSize(1);

        BackendFactory registeredFactory = REGISTRY.getFactoryProvider(MOCK_1);
        assertThat(registeredFactory).isNotNull().isSameAs(factory1);
    }

    /**
     * Checks the registration of several {@link BackendFactory}s.
     */
    @Test
    public void testMultipleAdd() throws Exception {
        REGISTRY.register(MOCK_1, factory1);
        REGISTRY.register(MOCK_2, factory2);
        assertThat(REGISTRY.getFactories()).hasSize(2);

        BackendFactory registeredFactory1 = REGISTRY.getFactoryProvider(MOCK_1);
        assertThat(registeredFactory1).isNotNull().isSameAs(factory1);

        BackendFactory registeredFactory2 = REGISTRY.getFactoryProvider(MOCK_2);
        assertThat(registeredFactory2).isNotNull().isSameAs(factory2);
    }
}
