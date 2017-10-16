/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractMapperFactory;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link BackendFactory}.
 */
@Singleton
@ParametersAreNonnullByDefault
public abstract class AbstractBackendFactory extends AbstractMapperFactory implements BackendFactory {

    /**
     * Constructs a new {@code AbstractBackendFactory}.
     */
    protected AbstractBackendFactory() {
    }
}
