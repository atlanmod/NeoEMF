/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package ${package}.util;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.util.AbstractUriFactory;
import fr.inria.atlanmod.neoemf.util.UriFactory;

import ${package}.${databaseName}BackendFactory;

import org.osgi.service.component.annotations.Component;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.util.UriFactory} that creates ${databaseName} specific resource {@link org.eclipse.emf.common.util.URI}s.
 *
 * @see ${databaseName}BackendFactory
 * @see fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@Component(service = UriFactory.class)
@FactoryBinding(factory = ${databaseName}BackendFactory.class)
@ParametersAreNonnullByDefault
public class ${databaseName}UriFactory extends AbstractUriFactory {

    /**
     * Constructs a new {@code ${databaseName}UriFactory}.
     */
    public ${databaseName}UriFactory() {
        super(true, false);
    }
}
