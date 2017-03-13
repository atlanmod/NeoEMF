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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ContainerMapper} that does not support {@link ContainerDescriptor} mapping.
 */
@ParametersAreNonnullByDefault
public interface ContainerMapperUnsupported extends ContainerMapper {

    @Override
    default boolean supportsContainerMapping() {
        return false;
    }

    @Nonnull
    @Override
    default Optional<ContainerDescriptor> containerOf(Id id) {
        throw new UnsupportedOperationException(String.format("%s does not support %s mapping", getClass().getSimpleName(), ContainerDescriptor.class.getSimpleName()));
    }

    @Override
    default void containerFor(Id id, ContainerDescriptor container) {
        throw new UnsupportedOperationException(String.format("%s does not support %s mapping", getClass().getSimpleName(), ContainerDescriptor.class.getSimpleName()));
    }
}
