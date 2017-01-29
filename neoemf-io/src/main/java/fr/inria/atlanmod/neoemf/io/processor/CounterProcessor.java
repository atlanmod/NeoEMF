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
     * The name of this handler.
     */
    private final String name;

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
     * Constructs a new {@code CounterProcessor} on the underlying {@code processor}.
     *
     * @param processor the underlying processor
     */
    public CounterProcessor(Processor processor) {
        this(processor, "counter-" + ++id);
    }

    /**
     * Constructs a new {@code CounterProcessor} with the given {@code name} on the underlying
     * {@code processor}.
     *
     * @param processor the underlying processor
     * @param name    the name of this processor
     */
    public CounterProcessor(Processor processor, String name) {
        super(processor);
        this.name = name;
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
        NeoLogger.info("[{0}] Elements   : {1}", name, elementCount);
        NeoLogger.info("[{0}] Attributes : {1}", name, attributeCount);
        NeoLogger.info("[{0}] References : {1}", name, referenceCount);

        super.processEndDocument();
    }
}
