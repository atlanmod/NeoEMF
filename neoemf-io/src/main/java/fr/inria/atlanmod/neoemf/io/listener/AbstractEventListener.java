/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.listener;

import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link EventListener} that does nothing.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractEventListener implements EventListener {

    @Override
    public void onInitialize() {
        // Do nothing
    }

    @Override
    public void onStartElement(BasicElement element) {
        // Do nothing
    }

    @Override
    public void onAttribute(BasicAttribute attribute) {
        // Do nothing
    }

    @Override
    public void onReference(BasicReference reference) {
        // Do nothing
    }

    @Override
    public void onEndElement() {
        // Do nothing
    }

    @Override
    public void onComplete() {
        // Do nothing
    }
}
