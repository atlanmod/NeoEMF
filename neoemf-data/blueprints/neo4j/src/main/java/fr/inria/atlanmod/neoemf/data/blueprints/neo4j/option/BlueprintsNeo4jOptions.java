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

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option;

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.data.blueprints.option.AbstractBlueprintsOptions;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;

/**
 * A {@link PersistenceOptions} that holds Blueprints Neo4j specific options.
 * <p>
 * <b>Note:</b> Not implemented yet.
 * <p>
 * <b>Future:</b> This class is not used in the current release of the tool, it will simplify option management in the
 * near future.
 */
@Experimental
public class BlueprintsNeo4jOptions extends AbstractBlueprintsOptions {

    @SuppressWarnings("JavaDoc")
    private BlueprintsNeo4jOptions() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
