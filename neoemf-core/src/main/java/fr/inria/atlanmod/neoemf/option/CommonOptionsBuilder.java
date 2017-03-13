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

import fr.inria.atlanmod.neoemf.annotations.VisibleForTesting;
import fr.inria.atlanmod.neoemf.data.PersistentBackend;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A common {@link PersistenceOptionsBuilder} that creates common options that are available for all back-end
 * implementations.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 * <p>
 * <b>Note:</b> This class is visible only for testing. In a standard use, you should use the {@link PersistenceOptions}
 * implementation corresponding to the {@link PersistentBackend} you want.
 */
@VisibleForTesting
@ParametersAreNonnullByDefault
public class CommonOptionsBuilder extends AbstractPersistenceOptionsBuilder<CommonOptionsBuilder, CommonOptions> {

    /**
     * Constructs a new {@code CommonOptionsBuilder}.
     */
    protected CommonOptionsBuilder() {
    }
}
