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

import com.google.common.base.Stopwatch;

import fr.inria.atlanmod.neoemf.io.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

/**
 * A delegated {@link PersistenceHandler persistence handler} that counts the number of different element, and gives the
 * time to process.
 */
public class CounterDelegatedPersistenceHandler extends AbstractDelegatedPersistenceHandler {

    private Stopwatch stopWatch;

    private final String name;

    private long elementCount;
    private long attributeCount;
    private long referenceCount;

    public CounterDelegatedPersistenceHandler(PersistenceHandler handler, String name) {
        super(handler);
        this.name = name;
        this.elementCount = 0;
        this.attributeCount = 0;
        this.referenceCount = 0;
    }

    @Override
    public void handleStartDocument() throws Exception {
        NeoLogger.info("[" + name +  "] Document analysis in progress...");
        stopWatch = Stopwatch.createStarted();

        super.handleStartDocument();
    }

    @Override
    public void handleStartElement(Classifier classifier) throws Exception {
        elementCount++;

        super.handleStartElement(classifier);
    }

    @Override
    public void handleAttribute(Attribute attribute) throws Exception {
        attributeCount++;

        super.handleAttribute(attribute);
    }

    @Override
    public void handleReference(Reference reference) throws Exception {
        referenceCount++;

        super.handleReference(reference);
    }

    @Override
    public void handleEndDocument() throws Exception {
        NeoLogger.info("[{0}] Document analysis done in {1}", name, stopWatch.stop());
        NeoLogger.info("[{0}]  - Elements   : {1}", name, elementCount);
        NeoLogger.info("[{0}]  - Attributes : {1}", name, attributeCount);
        NeoLogger.info("[{0}]  - References : {1}", name, referenceCount);

        super.handleEndDocument();
    }
}
