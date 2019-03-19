/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.AbstractNotifier;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;

import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Processor} that simply delegates all methods to the next {@link Processor}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractProcessor extends AbstractNotifier<Processor> implements Processor {

    @Override
    public void onInitialize() throws IOException {
        notifyInitialize();
    }

    @Override
    public void onStartElement(ProxyElement element) throws IOException {
        notifyStartElement(element);
    }

    @Override
    public void onAttribute(ProxyAttribute attribute) throws IOException {
        notifyAttribute(attribute);
    }

    @Override
    public void onReference(ProxyReference reference) throws IOException {
        notifyReference(reference);
    }

    @Override
    public void onEndElement() throws IOException {
        notifyEndElement();
    }

    @Override
    public void onComplete() throws IOException {
        notifyComplete();
    }
}
