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

import fr.inria.atlanmod.neoemf.TestHelper;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptionsBuilder;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Map;

/**
 * A specific {@link TestHelper} for the Blueprints implementation.
 */
public class BlueprintsTestHelper extends AbstractBlueprintsTestHelper<BlueprintsTestHelper> {

    /**
     * Constructs a new {@code BlueprintsTestHelper} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    public BlueprintsTestHelper(EPackage ePackage) {
        super(ePackage);
    }

    @Override
    protected Map<String, Object> defaultOptions() {
        return BlueprintsOptionsBuilder.noOption();
    }
}
