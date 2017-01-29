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

import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;

import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * A {@link Processor} that delegates all methods to its underlying processor.
 */
public class AbstractProcessor implements Processor {

    /**
     * The processor to notify.
     */
    private final Processor processor;

    /**
     * Constructs a new {@code AbstractProcessor} with the given {@code processor}.
     *
     * @param processor the processor to notify
     */
    protected AbstractProcessor(Processor processor) {
        this.processor = processor;
    }

    // Handler methods

    @Override
    @OverridingMethodsMustInvokeSuper
    public void processStartDocument() {
        notifyStartDocument();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void processStartElement(RawClassifier classifier) {
        notifyStartElement(classifier);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void processAttribute(RawAttribute attribute) {
        notifyAttribute(attribute);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void processReference(RawReference reference) {
        notifyReference(reference);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void processEndElement() {
        notifyEndElement();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void processEndDocument() {
        notifyEndDocument();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void processCharacters(String characters) {
        notifyCharacters(characters);
    }

    // Notifier methods

    @Override
    public final Handler handler() {
        return processor.handler();
    }

    @Override
    public final void andThen(Handler inputHandler) {
        processor.andThen(inputHandler);
    }

    @Override
    public final boolean hasHandler() {
        return processor.hasHandler();
    }

    @Override
    public final void notifyStartDocument() {
        processor.processStartDocument();
    }

    @Override
    public final void notifyStartElement(RawClassifier classifier) {
        processor.processStartElement(classifier);
    }

    @Override
    public final void notifyAttribute(RawAttribute attribute) {
        processor.processAttribute(attribute);
    }

    @Override
    public final void notifyReference(RawReference reference) {
        processor.processReference(reference);
    }

    @Override
    public final void notifyCharacters(String characters) {
        processor.processCharacters(characters);
    }

    @Override
    public final void notifyEndElement() {
        processor.processEndElement();
    }

    @Override
    public final void notifyEndDocument() {
        processor.processEndDocument();
    }
}
