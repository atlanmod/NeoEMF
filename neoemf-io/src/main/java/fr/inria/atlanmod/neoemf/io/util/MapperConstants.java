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

package fr.inria.atlanmod.neoemf.io.util;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;

/**
 * A utility class that contains all the constants used in a {@link fr.inria.atlanmod.neoemf.data.mapper.DataMapper}.
 */
public interface MapperConstants {

    /**
     * The identifier of the root element.
     */
    Id ROOT_ID = StringId.of("ROOT");

    /**
     * The property key used by the root element to define its content.
     */
    String ROOT_FEATURE_NAME = "eContents";

    /**
     * The feature name of the name of an element.
     */
    String FEATURE_NAME = "name";
}
