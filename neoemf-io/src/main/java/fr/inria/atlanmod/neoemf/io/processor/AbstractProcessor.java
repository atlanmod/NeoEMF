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
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawElement;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Processor} that delegates all methods to its underlying processor.
 *
 * @param <P> the type of notified {@link Handler}
 */
@ParametersAreNonnullByDefault
public class AbstractProcessor<P extends Handler> extends AbstractNotifier<P> implements Processor {

    /**
     * Constructs a new {@code AbstractProcessor} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    @SafeVarargs
    public AbstractProcessor(P... handler) {
        super(handler);
    }

    @Override
    public void onInitialize() {
        notifyInitialize();
    }

    @Override
    public void onStartElement(RawElement element) {
        notifyStartElement(element);
    }

    @Override
    public void onAttribute(RawAttribute attribute) {
        notifyAttribute(attribute);
    }

    @Override
    public void onReference(RawReference reference) {
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
