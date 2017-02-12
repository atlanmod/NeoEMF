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
 * Test cases about {@link PersistenceBackendFactoryRegistry}.
 */
public class PersistenceBackendFactoryRegistryTest extends AbstractTest {

    /**
     * The fake URI scheme of the first {@link PersistenceBackendFactory}.
     */
    private static final String MOCK_1 = "mock1";

    /**
     * The fake URI scheme of the second {@link PersistenceBackendFactory}.
     */
    private static final String MOCK_2 = "mock2";

    /**
     * The first {@link PersistenceBackendFactory}.
     */
    private final PersistenceBackendFactory persistenceBackendFactory_1 = mock(PersistenceBackendFactory.class);

    /**
     * The second {@link PersistenceBackendFactory}.
     */
    private final PersistenceBackendFactory persistenceBackendFactory_2 = mock(PersistenceBackendFactory.class);

    /**
     * Unregisters all factories in the {@link PersistenceBackendFactoryRegistry}.
     */
    @Before
    public void unregisterFactories() {
        PersistenceBackendFactoryRegistry.unregisterAll();
    }

    /**
     * Checks the registration of one {@link PersistenceBackendFactory}.
     */
    @Test
    public void testSingleAdd() {
        PersistenceBackendFactoryRegistry.register(MOCK_1, persistenceBackendFactory_1);
        assertThat(PersistenceBackendFactoryRegistry.getFactories()).hasSize(1);

        PersistenceBackendFactory registeredFactory = PersistenceBackendFactoryRegistry.getFactoryProvider(MOCK_1);
        assertThat(registeredFactory).isNotNull().isSameAs(persistenceBackendFactory_1);
    }

    /**
     * Checks the registration of several {@link PersistenceBackendFactory}s.
     */
    @Test
    public void testMultipleAdd() {
        PersistenceBackendFactoryRegistry.register(MOCK_1, persistenceBackendFactory_1);
        PersistenceBackendFactoryRegistry.register(MOCK_2, persistenceBackendFactory_2);
        assertThat(PersistenceBackendFactoryRegistry.getFactories()).hasSize(2);

        PersistenceBackendFactory registeredFactory1 = PersistenceBackendFactoryRegistry.getFactoryProvider(MOCK_1);
        assertThat(registeredFactory1).isNotNull().isSameAs(persistenceBackendFactory_1);

        PersistenceBackendFactory registeredFactory2 = PersistenceBackendFactoryRegistry.getFactoryProvider(MOCK_2);
        assertThat(registeredFactory2).isNotNull().isSameAs(persistenceBackendFactory_2);
    }
}
