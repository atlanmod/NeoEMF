/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.listener;

import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link EventListener}.
 * <p>
 * By default, all methods do nothing.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractEventListener implements EventListener {

    @Override
    public void onInitialize() {
        // Do nothing
    }

    @Override
    public void onStartElement(ProxyElement element) {
        // Do nothing
    }

    @Override
    public void onAttribute(ProxyAttribute attribute) {
        // Do nothing
    }

    @Override
    public void onReference(ProxyReference reference) {
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
