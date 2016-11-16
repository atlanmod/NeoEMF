/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.impl;

import fr.inria.atlanmod.neoemf.io.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

/**
 * A delegated {@link PersistenceHandler} that counts the number of different element.
 */
public class CounterPersistenceHandlerDecorator extends AbstractPersistenceHandlerDecorator {

    private final String name;

    private long elementCount;
    private long attributeCount;
    private long referenceCount;

    public CounterPersistenceHandlerDecorator(PersistenceHandler handler, String name) {
        super(handler);
        this.name = name;
        this.elementCount = 0;
        this.attributeCount = 0;
        this.referenceCount = 0;
    }

    @Override
    public void processStartElement(Classifier classifier) throws Exception {
        elementCount++;

        super.processStartElement(classifier);
    }

    @Override
    public void processAttribute(Attribute attribute) throws Exception {
        attributeCount++;

        super.processAttribute(attribute);
    }

    @Override
    public void processReference(Reference reference) throws Exception {
        referenceCount++;

        super.processReference(reference);
    }

    @Override
    public void processEndDocument() throws Exception {
        NeoLogger.info("[{0}]  - Elements   : {1}", name, elementCount);
        NeoLogger.info("[{0}]  - Attributes : {1}", name, attributeCount);
        NeoLogger.info("[{0}]  - References : {1}", name, referenceCount);

        super.processEndDocument();
    }
}
