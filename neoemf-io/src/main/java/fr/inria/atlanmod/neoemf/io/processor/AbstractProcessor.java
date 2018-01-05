/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.AbstractNotifier;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A abstract {@link Processor} that delegates all methods to an internal {@link Processor}.
 *
 * @param <H> the type of notified {@link Handler}
 */
@ParametersAreNonnullByDefault
public abstract class AbstractProcessor<H extends Handler> extends AbstractNotifier<H> implements Processor {

    /**
     * Constructs a new {@code AbstractProcessor} with the given {@code handlers}.
     *
     * @param handlers the handlers to notify
     */
    @SafeVarargs
    public AbstractProcessor(H... handlers) {
        super(handlers);
    }

    @Override
    public void onInitialize() {
        notifyInitialize();
    }

    @Override
    public void onStartElement(BasicElement element) {
        notifyStartElement(element);
    }

    @Override
    public void onAttribute(BasicAttribute attribute) {
        notifyAttribute(attribute);
    }

    @Override
    public void onReference(BasicReference reference) {
        notifyReference(reference);
    }

    @Override
    public void onCharacters(String characters) {
        notifyCharacters(characters);
    }

    @Override
    public void onEndElement() {
        notifyEndElement();
    }

    @Override
    public void onComplete() {
        notifyComplete();
    }
}
