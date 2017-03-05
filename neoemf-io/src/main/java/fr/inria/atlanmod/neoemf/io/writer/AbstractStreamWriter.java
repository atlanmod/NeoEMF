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

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.util.log.Log;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link StreamWriter}.
 */
@Experimental
@ParametersAreNonnullByDefault
public abstract class AbstractStreamWriter implements StreamWriter {

    /**
     * Constructs a new {@code AbstractStreamWriter}.
     */
    protected AbstractStreamWriter() {
        Log.info("{0} created", getClass().getSimpleName());
    }
}
