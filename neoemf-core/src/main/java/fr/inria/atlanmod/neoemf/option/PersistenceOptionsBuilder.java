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

package fr.inria.atlanmod.neoemf.option;

import java.util.Map;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A builder of {@link PersistenceOptions}.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 */
@ParametersAreNonnullByDefault
public interface PersistenceOptionsBuilder {

    /**
     * Returns an immutable {@link Map} containing all defined options.
     *
     * @return an immutable {@link Map}
     *
     * @throws InvalidOptionException if a conflict is detected during building
     */
    Map<String, Object> asMap() throws InvalidOptionException;
}
