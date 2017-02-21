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

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptions;
import fr.inria.atlanmod.neoemf.option.InvalidOptionException;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;

import java.util.Map;

/**
 * An abstract {@link PersistenceOptions} that holds generic options for all Blueprints based implementations.
 * <p>
 * <b>Note:</b> Not implemented yet.
 * <p>
 * <b>Future:</b> This class is not used in the current release of the tool, it will simplify option management in the
 * near future.
 *
 * @see AbstractBlueprintsOptionsBuilder
 */
@Experimental
public abstract class AbstractBlueprintsOptions extends AbstractPersistenceOptions {

    @SuppressWarnings("JavaDoc")
    protected AbstractBlueprintsOptions() {
        super();
    }

    @Override
    public Map<String, Object> toMap() throws InvalidOptionException {
        return super.toMap();
    }

    @Override
    public void fromMap(Map<?, ?> options) {
        super.fromMap(options);
    }
}
