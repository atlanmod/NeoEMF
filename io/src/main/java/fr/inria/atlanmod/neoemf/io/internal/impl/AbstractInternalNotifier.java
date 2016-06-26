/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.internal.impl;

import fr.inria.atlanmod.neoemf.io.impl.AbstractNotifier;
import fr.inria.atlanmod.neoemf.io.internal.InternalHandler;
import fr.inria.atlanmod.neoemf.io.internal.InternalNotifier;

/**
 *
 */
public abstract class AbstractInternalNotifier extends AbstractNotifier<InternalHandler> implements InternalNotifier {

    @Override
    public void notifyCharacters(String characters) throws Exception {
        for (InternalHandler h : getHandlers()) {
            h.handleCharacters(characters);
        }
    }
}
