/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.AbstractActivator;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;

public class Activator extends AbstractActivator {

    @Override
    protected String name() {
        return "Blueprints";
    }

    @Override
    protected String scheme() {
        return BlueprintsURI.SCHEME;
    }

    @Override
    protected PersistenceBackendFactory factory() {
        return BlueprintsPersistenceBackendFactory.getInstance();
    }
}
