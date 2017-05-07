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

package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsContext;
import fr.inria.atlanmod.neoemf.data.mapper.AbstractPersistenceMapperTest;

/**
 * A test-case that checks the behavior of {@link BlueprintsBackendIndices} by using the TinkerGraph implementation.
 */
public class BlueprintsIndicesTest extends AbstractPersistenceMapperTest {

    @Override
    public Context context() {
        return BlueprintsContext.getWithIndices();
    }
}
