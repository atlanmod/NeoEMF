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
    public void processStartElement(RawClassifier classifier) {
        elementCount++;

        super.processStartElement(classifier);
    }

    @Override
    public void processAttribute(RawAttribute attribute) {
        attributeCount++;

        super.processAttribute(attribute);
    }

    @Override
    public void processReference(RawReference reference) {
        referenceCount++;

        super.processReference(reference);
    }

    @Override
    public void processEndDocument() {
        NeoLogger.info("Elements   : {0}", elementCount);
        NeoLogger.info("Attributes : {0}", attributeCount);
        NeoLogger.info("References : {0}", referenceCount);

        super.processEndDocument();
    }
}
