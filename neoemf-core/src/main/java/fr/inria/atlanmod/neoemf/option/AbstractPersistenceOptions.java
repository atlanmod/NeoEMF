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

import fr.inria.atlanmod.neoemf.annotations.Experimental;

import java.util.Map;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The abstract implementation of {@link PersistenceOptions}.
 * <p>
 * <b>Note:</b> Not implemented yet.
 * <p>
 * <b>Future:</b> This class is not used in the current release of the tool, it will simplify option management in the
 * near future.
 *
 * @see AbstractPersistenceOptionsBuilder
 */
@Experimental
@ParametersAreNonnullByDefault
public abstract class AbstractPersistenceOptions implements PersistenceOptions {

    @SuppressWarnings("JavaDoc")
    protected AbstractPersistenceOptions() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Map<String, Object> toMap() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void fromMap(Map<String, Object> options) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
