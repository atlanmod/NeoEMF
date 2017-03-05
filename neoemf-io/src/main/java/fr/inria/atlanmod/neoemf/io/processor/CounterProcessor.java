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
import fr.inria.atlanmod.neoemf.io.structure.RawElement;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;
import fr.inria.atlanmod.neoemf.util.log.Log;

import javax.annotation.Nonnegative;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Processor} that counts the number of different element.
 */
@ParametersAreNonnullByDefault
public class CounterProcessor extends AbstractProcessor<Processor> {

    /**
     * The current number of element.
     */
    @Nonnegative
    private long elementCount;

    /**
     * The current number of attribute.
     */
    @Nonnegative
    private long attributeCount;

    /**
     * The current number of reference.
     */
    @Nonnegative
    private long referenceCount;

    /**
     * Constructs a new {@code CounterProcessor} with the given {@code processors}.
     *
     * @param processors the processors to notify
     */
    public CounterProcessor(Processor... processors) {
        super(processors);
        this.elementCount = 0;
        this.attributeCount = 0;
        this.referenceCount = 0;
    }

    @Override
    public void onStartElement(RawElement element) {
        elementCount++;

        notifyStartElement(element);
    }

    @Override
    public void onAttribute(RawAttribute attribute) {
        attributeCount++;

        notifyAttribute(attribute);
    }

    @Override
    public void onReference(RawReference reference) {
        referenceCount++;

        notifyReference(reference);
    }

    @Override
    public void onComplete() {
        Log.info("Elements   : {0}", elementCount);
        Log.info("Attributes : {0}", attributeCount);
        Log.info("References : {0}", referenceCount);

        notifyComplete();
    }
}
