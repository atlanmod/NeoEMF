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

import fr.inria.atlanmod.neoemf.io.AbstractInputNotifier;

/**
 * An abstract implementation of an {@link ProcessorNotifier} of {@link Processor}.
 */
public abstract class AbstractProcessorNotifier extends AbstractInputNotifier<Processor> implements ProcessorNotifier {

    @Override
    public void notifyCharacters(String characters) throws Exception {
        for (Processor h : getHandlers()) {
            h.processCharacters(characters);
        }
    }
}
