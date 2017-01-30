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
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Element;
import fr.inria.atlanmod.neoemf.io.structure.Reference;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

/**
 * An {@link Processor} that counts the number of different element.
 */
public class CounterProcessor extends AbstractProcessor {

    /**
     * The current number of created {@code CounterProcessor}s, used for name generation.
     */
    private static int id = 0;

    /**
     * The current number of element.
     */
    private long elementCount;

    /**
     * The current number of attribute.
     */
    private long attributeCount;

    /**
     * The current number of reference.
     */
    private long referenceCount;

    /**
     * Constructs a new {@code CounterProcessor} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public CounterProcessor(Handler handler) {
        super(handler);
        this.elementCount = 0;
        this.attributeCount = 0;
        this.referenceCount = 0;
    }

    @Override
    public void handleStartElement(Element element) {
        elementCount++;

        super.handleStartElement(element);
    }

    @Override
    public void handleAttribute(Attribute attribute) {
        attributeCount++;

        super.handleAttribute(attribute);
    }

    @Override
    public void handleReference(Reference reference) {
        referenceCount++;

        super.handleReference(reference);
    }

    @Override
    public void handleEndDocument() {
        NeoLogger.info("Elements   : {0}", elementCount);
        NeoLogger.info("Attributes : {0}", attributeCount);
        NeoLogger.info("References : {0}", referenceCount);

        super.handleEndDocument();
    }
}
