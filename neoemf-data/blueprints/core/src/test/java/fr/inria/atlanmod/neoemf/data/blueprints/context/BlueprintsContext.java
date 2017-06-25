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

package fr.inria.atlanmod.neoemf.data.blueprints.context;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptions;

/**
 * A specific {@link Context} for the Blueprints implementation.
 */
@FunctionalInterface
public interface BlueprintsContext extends Context {

    /**
     * Creates a new {@code BlueprintsContext} with a mapping with indices.
     *
     * @return a new context.
     */
    static Context getWithIndices() {
        return (BlueprintsContext) () -> BlueprintsOptions.builder().withIndices();
    }

    @Override
    default String name() {
        return "Tinker";
    }

    @Override
    default BackendFactory factory() {
        return BlueprintsBackendFactory.getInstance();
    }
}
