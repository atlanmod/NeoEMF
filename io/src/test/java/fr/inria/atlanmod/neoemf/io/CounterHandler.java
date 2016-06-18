/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.impl.AbstractDelegatedPersistenceHandler;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.joda.time.Duration;
import org.joda.time.Instant;

/**
 * A persistence handler that counts the number of different element, and gives the time to process.
 * <p/>
 * Using for basic tests.
 */
public class CounterHandler extends AbstractDelegatedPersistenceHandler {

    private Instant start;

    private String name;

    private long elementCount;
    private long attributeCount;
    private long referenceCount;

    public CounterHandler(PersistenceHandler handler, String name) {
        super(handler);
        this.name = name;
        this.elementCount = 0;
        this.attributeCount = 0;
        this.referenceCount = 0;
    }

    @Override
    public void handleStartDocument() throws Exception {
        NeoLogger.info("[" + name +  "] Document parsing...");
        start = Instant.now();
        super.handleStartDocument();
    }

    @Override
    public void handleStartElement(Id id, String namespace, String localName) throws Exception {
        elementCount++;
        super.handleStartElement(id, namespace, localName);
    }

    @Override
    public void handleAttribute(Id id, String namespace, String localName, String value) throws Exception {
        attributeCount++;
        super.handleAttribute(id, namespace, localName, value);
    }

    @Override
    public void handleReference(Id id, String namespace, String localName, Id idReference) throws Exception {
        referenceCount++;
        super.handleReference(id, namespace, localName, idReference);
    }

    @Override
    public void handleEndDocument() throws Exception {
        Instant end = Instant.now();
        NeoLogger.info("[" + name +  "] Document parsed in " + new Duration(start, end));
        NeoLogger.info("[" + name +  "]  - Elements   : " + elementCount);
        NeoLogger.info("[" + name +  "]  - Attributes : " + attributeCount);
        NeoLogger.info("[" + name +  "]  - References : " + referenceCount);
        super.handleEndDocument();
    }
}