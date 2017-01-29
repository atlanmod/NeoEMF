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

/**
 * A {@link Processor} that delegates all methods to its underlying processor.
 */
public class AbstractProcessor implements Processor {

    /**
     * The underlying processor.
     */
    private final Processor processor;

    /**
     * Constructs a new {@code AbstractProcessor} with an embedded {@code processor}.
     *
     * @param processor the embedded processor
     */
    protected AbstractProcessor(Processor processor) {
        this.processor = processor;
    }

    // Handler methods

    @Override
    public void processStartDocument() {
        notifyStartDocument();
    }

    @Override
    public void processStartElement(RawClassifier classifier) {
        notifyStartElement(classifier);
    }

    @Override
    public void processAttribute(RawAttribute attribute) {
        notifyAttribute(attribute);
    }

    @Override
    public void processReference(RawReference reference) {
        notifyReference(reference);
    }

    @Override
    public void processEndElement() {
        notifyEndElement();
    }

    @Override
    public void processEndDocument() {
        notifyEndDocument();
    }

    @Override
    public void processCharacters(String characters) {
        notifyCharacters(characters);
    }

    // Notifier methods

    @Override
    public final Handler handler() {
        return processor.handler();
    }

    @Override
    public final void handler(Handler inputHandler) {
        processor.handler(inputHandler);
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
