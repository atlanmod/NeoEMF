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

package fr.inria.atlanmod.neoemf.io.mock;

import fr.inria.atlanmod.neoemf.io.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.io.impl.AbstractDelegatedPersistenceHandler;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

/**
 *
 */
public class VerboseHandler extends AbstractDelegatedPersistenceHandler {

    public VerboseHandler(PersistenceHandler handler) {
        super(handler);
    }

    @Override
    public void handleStartDocument() throws Exception {
        NeoLogger.debug("Starting document");

        super.handleStartDocument();
    }

    @Override
    public void handleStartElement(Classifier classifier) throws Exception {
        NeoLogger.debug(classifier.getNamespace().getPrefix() + ":" + classifier.getLocalName() + " @ " + classifier.getClassName() + " -> " + classifier.getMetaClassifier().getLocalName() + " = " + classifier.getId());

        super.handleStartElement(classifier);
    }

    @Override
    public void handleAttribute(Attribute attribute) throws Exception {
        NeoLogger.debug("   " + attribute.getLocalName() + " (" + attribute.getIndex() + ") = " + attribute.getValue());

        super.handleAttribute(attribute);
    }

    @Override
    public void handleReference(Reference reference) throws Exception {
        NeoLogger.debug("   " + reference.getLocalName() + " (" + reference.getIndex() + ") = " + reference.getIdReference().getValue());

        super.handleReference(reference);
    }

    @Override
    public void handleEndDocument() throws Exception {
        NeoLogger.debug("Ending document");

        super.handleEndDocument();
    }
}
