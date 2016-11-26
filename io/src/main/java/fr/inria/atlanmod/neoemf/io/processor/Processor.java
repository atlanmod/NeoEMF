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

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.InputHandler;
import fr.inria.atlanmod.neoemf.io.InputNotifier;
import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;

/**
 * A structural handler that receives and uses events sent from a {@link InputNotifier} where it has to be registered by
 * the {@link InputNotifier#addHandler(InputHandler)} method.
 */
public interface Processor extends InputHandler, InputNotifier<PersistenceHandler> {

    /**
     * Process characters.
     */
    void processCharacters(String characters);
}
