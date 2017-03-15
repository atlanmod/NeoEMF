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

package fr.inria.atlanmod.neoemf.data.berkeleydb.option;

import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;
import fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link PersistenceOptionsBuilder} that creates BerkeleyDB specific options.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 */
@ParametersAreNonnullByDefault
public class BerkeleyDbOptionsBuilder extends AbstractPersistenceOptionsBuilder<BerkeleyDbOptionsBuilder, BerkeleyDbOptions> {

    /**
     * Constructs a new {@code BerkeleyDbOptionsBuilder}.
     */
    protected BerkeleyDbOptionsBuilder() {
    }
}
