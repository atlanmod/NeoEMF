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
import fr.inria.atlanmod.neoemf.io.structure.RawId;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;
import fr.inria.atlanmod.neoemf.util.logging.Logger;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.util.Objects;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Processor} that logs every events.
 */
@ParametersAreNonnullByDefault
public class LoggingProcessor extends AbstractProcessor<Processor> {

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
    public LoggingProcessor(Processor handler) {
        super(handler);
    }

    @Override
    public void handleStartDocument() {
        log.info("[#] Starting document");

        super.handleStartDocument();
    }

    @Override
    public void handleStartElement(RawElement element) {
        log.info("[E] {0}:{1} \"{2}\" : {3} = {4}",
                element.ns().prefix(),
                element.name(),
                element.className(),
                element.metaClass().name(),
                element.id());

        currentId = element.id();

        super.handleStartElement(element);
    }

    @Override
    public void handleAttribute(RawAttribute attribute) {
        log.info("[A]    {0} ({1}) = {2}",
                attribute.name(),
                attribute.index(),
                attribute.value());

        super.handleAttribute(attribute);
    }

    @Override
    public void handleReference(RawReference reference) {
        log.info("[R]    {0} ({1}) = {2} -> {3}",
                reference.name(),
                reference.index(),
                Objects.isNull(reference.id()) ? "this" : reference.id(),
                Objects.equals(reference.idReference(), currentId) ? "this" : reference.idReference());

        super.handleReference(reference);
    }

    @Override
    public void handleEndDocument() {
        log.info("[#] Ending document");

        super.handleEndDocument();
    }
}
