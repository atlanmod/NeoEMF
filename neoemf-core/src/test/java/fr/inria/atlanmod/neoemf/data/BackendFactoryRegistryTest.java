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

import fr.inria.atlanmod.neoemf.AbstractTest;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * A test-case that checks the behavior of {@link BackendFactoryRegistry}.
 */
public class BackendFactoryRegistryTest extends AbstractTest {

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
    @Before
    public void unregisterFactories() {
        BackendFactoryRegistry.unregisterAll();
    }

    /**
     * Checks the registration of one {@link BackendFactory}.
     */
    @Test
    public void testSingleAdd() {
        BackendFactoryRegistry.register(MOCK_1, factory1);
        assertThat(BackendFactoryRegistry.getFactories()).hasSize(1);

        BackendFactory registeredFactory = BackendFactoryRegistry.getFactoryProvider(MOCK_1);
        assertThat(registeredFactory).isNotNull().isSameAs(factory1);
    }

    /**
     * Checks the registration of several {@link BackendFactory}s.
     */
    @Test
    public void testMultipleAdd() {
        BackendFactoryRegistry.register(MOCK_1, factory1);
        BackendFactoryRegistry.register(MOCK_2, factory2);
        assertThat(BackendFactoryRegistry.getFactories()).hasSize(2);

        BackendFactory registeredFactory1 = BackendFactoryRegistry.getFactoryProvider(MOCK_1);
        assertThat(registeredFactory1).isNotNull().isSameAs(factory1);

        BackendFactory registeredFactory2 = BackendFactoryRegistry.getFactoryProvider(MOCK_2);
        assertThat(registeredFactory2).isNotNull().isSameAs(factory2);
    }
}
