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
import fr.inria.atlanmod.neoemf.io.structure.RawClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;

/**
 * A {@link Processor} that notifies registered {@link Handler} of events.
 */
public final class DefaultProcessor extends AbstractNotifier<Handler> implements Processor {

    /**
     * Constructs a new {@code DefaultProcessor}.
     */
    public DefaultProcessor() {
    }

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
}
