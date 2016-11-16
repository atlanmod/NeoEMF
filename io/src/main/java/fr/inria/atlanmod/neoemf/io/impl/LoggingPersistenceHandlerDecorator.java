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
import fr.inria.atlanmod.neoemf.io.impl.AbstractPersistenceHandlerDecorator;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

public class LoggingPersistenceHandlerDecorator extends AbstractPersistenceHandlerDecorator {

    public LoggingPersistenceHandlerDecorator(PersistenceHandler handler) {
        super(handler);
    }

    @Override
    public void processStartDocument() throws Exception {
        NeoLogger.debug("Starting document");

        super.processStartDocument();
    }

    @Override
    public void processStartElement(Classifier classifier) throws Exception {
        NeoLogger.debug("{0}:{1} @ {2} -> {3} = {4}",
                classifier.getNamespace().getPrefix(),
                classifier.getLocalName(),
                classifier.getClassName(),
                classifier.getMetaClassifier().getLocalName(),
                classifier.getId());

        super.processStartElement(classifier);
    }

    @Override
    public void processAttribute(Attribute attribute) throws Exception {
        NeoLogger.debug("   {0} ({1}) = {2}",
                attribute.getLocalName(),
                attribute.getIndex(),
                attribute.getValue());

        super.processAttribute(attribute);
    }

    @Override
    public void processReference(Reference reference) throws Exception {
        NeoLogger.debug("   {0} ({1}) = {2}",
                reference.getLocalName(),
                reference.getIndex(),
                reference.getIdReference().getValue());

        super.processReference(reference);
    }

    @Override
    public void processEndDocument() throws Exception {
        NeoLogger.debug("Ending document");

        super.processEndDocument();
    }
}
