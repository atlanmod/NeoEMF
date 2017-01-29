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

import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;
import fr.inria.atlanmod.neoemf.io.structure.RawIdentifier;
import fr.inria.atlanmod.neoemf.util.logging.Logger;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.util.Objects;

/**
 * A {@link PersistenceHandler} wrapper that logs every events.
 */
public class LoggingPersistenceHandlerDecorator extends AbstractPersistenceHandlerDecorator {

    /**
     * The special logger.
     */
    private static final Logger log = NeoLogger.rootLogger();

    /**
     * The current identifier, used to replace a full reference by "this".
     */
    private RawIdentifier currentId;

    /**
     * Constructs a new {@code LoggingPersistenceHandlerDecorator}.
     *
     * @param handler the underlying handler
     */
    public LoggingPersistenceHandlerDecorator(PersistenceHandler handler) {
        super(handler);
    }

    @Override
    public void processStartDocument() {
        log.info("Starting document");

        super.processStartDocument();
    }

    @Override
    public void processStartElement(RawClassifier classifier) {
        log.info("[E] {0}:{1} \"{2}\" : {3} = {4}",
                classifier.namespace().prefix(),
                classifier.localName(),
                classifier.className(),
                classifier.metaClassifier().localName(),
                classifier.id());

        currentId = classifier.id();

        super.processStartElement(classifier);
    }

    @Override
    public void processAttribute(RawAttribute attribute) {
        log.info("[A]    {0} ({1}) = {2}",
                attribute.localName(),
                attribute.index(),
                attribute.value());

        super.processAttribute(attribute);
    }

    @Override
    public void processReference(RawReference reference) {
        log.info("[R]    {0} ({1}) = {2} -> {3}",
                reference.localName(),
                reference.index(),
                Objects.isNull(reference.id()) ? "this" : reference.id(),
                Objects.equals(reference.idReference(), currentId) ? "this" : reference.idReference());

        super.processReference(reference);
    }

    @Override
    public void processEndDocument() {
        log.info("Ending document");

        super.processEndDocument();
    }
}
