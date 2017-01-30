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
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Element;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * A {@link Processor} that delegates all methods to its underlying processor.
 */
public class AbstractProcessor extends AbstractNotifier<Handler> implements Processor {

    /**
     * Constructs a new {@code AbstractProcessor} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public AbstractProcessor(Handler handler) {
        super(handler);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void handleStartDocument() {
        notifyStartDocument();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void handleStartElement(Element element) {
        notifyStartElement(element);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void handleAttribute(Attribute attribute) {
        notifyAttribute(attribute);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void handleReference(Reference reference) {
        notifyReference(reference);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void handleEndElement() {
        notifyEndElement();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void handleEndDocument() {
        notifyEndDocument();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void handleCharacters(String characters) {
        notifyCharacters(characters);
    }
}
