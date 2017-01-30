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
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Element;
import fr.inria.atlanmod.neoemf.io.structure.RawId;
import fr.inria.atlanmod.neoemf.io.structure.Reference;
import fr.inria.atlanmod.neoemf.util.logging.Logger;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.util.Objects;

/**
 * An {@link Processor} that logs every events.
 */
public class LoggingProcessor extends AbstractProcessor {

    /**
     * The special logger.
     */
    private static final Logger log = NeoLogger.rootLogger();

    /**
     * The current identifier, used to replace a full reference by "this".
     */
    private RawId currentId;

    /**
     * Constructs a new {@code LoggingProcessor} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public LoggingProcessor(Handler handler) {
        super(handler);
    }

    @Override
    public void handleStartDocument() {
        log.debug("[#] Starting document");

        super.handleStartDocument();
    }

    @Override
    public void handleStartElement(Element element) {
        log.debug("[E] {0}:{1} \"{2}\" : {3} = {4}",
                element.ns().prefix(),
                element.name(),
                element.className(),
                element.metaClass().name(),
                element.id());

        currentId = element.id();

        super.handleStartElement(element);
    }

    @Override
    public void handleAttribute(Attribute attribute) {
        log.debug("[A]    {0} ({1}) = {2}",
                attribute.name(),
                attribute.index(),
                attribute.value());

        super.handleAttribute(attribute);
    }

    @Override
    public void handleReference(Reference reference) {
        log.debug("[R]    {0} ({1}) = {2} -> {3}",
                reference.name(),
                reference.index(),
                Objects.isNull(reference.id()) ? "this" : reference.id(),
                Objects.equals(reference.idReference(), currentId) ? "this" : reference.idReference());

        super.handleReference(reference);
    }

    @Override
    public void handleEndDocument() {
        log.debug("[#] Ending document");

        super.handleEndDocument();
    }
}
