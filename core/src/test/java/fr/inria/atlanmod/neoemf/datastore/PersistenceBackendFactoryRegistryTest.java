/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.datastore;

import fr.inria.atlanmod.neoemf.AllTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class PersistenceBackendFactoryRegistryTest extends AllTest {

    private PersistenceBackendFactory persistenceBackendFactory1 = Mockito.mock(PersistenceBackendFactory.class);
    private PersistenceBackendFactory persistenceBackendFactory2 = Mockito.mock(PersistenceBackendFactory.class);

    @Before
    public void setUp() {
        PersistenceBackendFactoryRegistry.unregisterAll();
    }

    @Test
    public void testSingleAdd() {
        PersistenceBackendFactoryRegistry.register("mock1", persistenceBackendFactory1);
        assertThat(PersistenceBackendFactoryRegistry.getFactories().size(), equalTo(1));

        PersistenceBackendFactory registeredFactory = PersistenceBackendFactoryRegistry.getFactoryProvider("mock1");
        assertThat(registeredFactory, notNullValue());
        assertThat(registeredFactory, sameInstance(persistenceBackendFactory1));
    }

    @Test
    public void testMulltipleAdd() {
        PersistenceBackendFactoryRegistry.register("mock1", persistenceBackendFactory1);
        PersistenceBackendFactoryRegistry.register("mock2", persistenceBackendFactory2);
        assertThat(PersistenceBackendFactoryRegistry.getFactories().size(), equalTo(2));

        PersistenceBackendFactory registeredFactory1 = PersistenceBackendFactoryRegistry.getFactoryProvider("mock1");
        assertThat(registeredFactory1, notNullValue());
        assertThat(registeredFactory1, sameInstance(persistenceBackendFactory1));

        PersistenceBackendFactory registeredFactory2 = PersistenceBackendFactoryRegistry.getFactoryProvider("mock2");
        assertThat(registeredFactory2, notNullValue());
        assertThat(registeredFactory2, sameInstance(persistenceBackendFactory2));
    }

}
