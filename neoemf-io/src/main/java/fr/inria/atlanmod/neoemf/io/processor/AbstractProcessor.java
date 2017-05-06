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

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.AbstractNotifier;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicElement;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A abstract {@link Processor} that delegates all methods to an internal {@link Processor}.
 *
 * @param <H> the type of notified {@link Handler}
 */
@ParametersAreNonnullByDefault
public class AbstractProcessor<H extends Handler> extends AbstractNotifier<H> implements Processor {

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
