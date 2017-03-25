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

package fr.inria.atlanmod.neoemf.data.mapper;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.context.CoreContext;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BoundedTransientBackend;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test case for {@link BoundedTransientBackend}.
 */
public class BoundedTransientBackendTest extends AbstractPersistenceMapperTest {

    @Override
    public void testGetSetSameContainer() {
        Id containerId0 = StringId.of("ContainerId0");

        ContainerDescriptor container = ContainerDescriptor.of(containerId0, "Container0");

        Id id1 = StringId.of("Id1");

        // Define the containers
        mapper.containerFor(id0, container);
        assertThat(mapper.containerOf(id0)).isPresent().contains(container);

        mapper.containerFor(id1, container);
        assertThat(mapper.containerOf(id1)).isNotPresent();
    }

    @Override
    public void testGetSetDifferentContainer() {
        Id containerId0 = StringId.of("ContainerId0");

        ContainerDescriptor container0 = ContainerDescriptor.of(containerId0, "Container0");
        ContainerDescriptor container1 = ContainerDescriptor.of(containerId0, "Container1");

        Id id1 = StringId.of("Id1");

        // Define the containers
        mapper.containerFor(id0, container0);
        assertThat(mapper.containerOf(id0)).isPresent().contains(container0);

        mapper.containerFor(id1, container1);
        assertThat(mapper.containerOf(id1)).isNotPresent();

        // Replace the existing container
        mapper.containerFor(id0, container1);
        assertThat(mapper.containerOf(id0)).isPresent().contains(container1);
    }

    @Override
    public Context context() {
        return new CoreContext() {

            @Override
            public Backend createMapper(File file) throws IOException {
                return BoundedTransientBackend.forId(id0);
            }
        };
    }
}
