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

package fr.inria.atlanmod.neoemf.data.blueprints.option;

import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

/**
 * A specific of {@link AbstractBlueprintsOptionsBuilder} that creates Blueprints {@link TinkerGraph} specific
 * options.
 * <p>
 * This builder doesn't contain specific methods for now: the only {@link TinkerGraph} configuration
 * supported is the graph type, which is set in the constructor.
 */
public class BlueprintsOptionsBuilder extends AbstractBlueprintsOptionsBuilder<BlueprintsOptionsBuilder, BlueprintsOptions> {

    /**
     * Creates a new builder
     * @return a fresh instance of the builder
     */
    public static BlueprintsOptionsBuilder newBuilder() {
        return new BlueprintsOptionsBuilder();
    }
}
