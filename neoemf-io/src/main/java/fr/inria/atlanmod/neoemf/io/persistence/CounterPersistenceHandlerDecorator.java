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

package fr.inria.atlanmod.neoemf.io.persistence;

import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Reference;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

/**
 * A {@link PersistenceHandler} wrapper that counts the number of different element.
 */
public class CounterPersistenceHandlerDecorator extends AbstractPersistenceHandlerDecorator {

    /**
     * The current number of created {@code CounterPersistenceHandlerDecorator}s, used for name generation.
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
     * Constructs a new {@code CounterPersistenceHandlerDecorator} on the underlying {@code handler}.
     *
     * @param handler the underlying handler
     */
    public CounterPersistenceHandlerDecorator(PersistenceHandler handler) {
        this(handler, "counter-" + ++id);
    }

    /**
     * Constructs a new {@code CounterPersistenceHandlerDecorator} with the given {@code name} on the underlying
     * {@code handler}.
     *
     * @param handler the underlying handler
     * @param name    the name of this handler
     */
    public CounterPersistenceHandlerDecorator(PersistenceHandler handler, String name) {
        super(handler);
        this.name = name;
        this.elementCount = 0;
        this.attributeCount = 0;
        this.referenceCount = 0;
    }

    @Override
    public void processStartElement(Classifier classifier) {
        elementCount++;

        super.processStartElement(classifier);
    }

    @Override
    public void processAttribute(Attribute attribute) {
        attributeCount++;

        super.processAttribute(attribute);
    }

    @Override
    public void processReference(Reference reference) {
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
