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

import fr.inria.atlanmod.neoemf.io.InputNotifier;

/**
 * An {@link InputNotifier} that notifies {@link Processor}.
 */
public interface ProcessorNotifier extends InputNotifier<Processor> {

    /**
     * Notifies a new set of characters.
     */
    void notifyCharacters(String characters) throws Exception;
}
