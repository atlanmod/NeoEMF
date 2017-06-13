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

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.common.log.Logger;
import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicElement;
import fr.inria.atlanmod.neoemf.io.structure.BasicId;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;

import java.util.Objects;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Processor} that logs every events.
 */
@ParametersAreNonnullByDefault
public class LoggingProcessor extends AbstractProcessor<Processor> {

    /**
     * The special logger.
     */
    private static final Logger LOG = Log.rootLogger();

    /**
     * The current identifier, used to replace a full reference by "this".
     */
    private BasicId currentId;

    /**
     * Constructs a new {@code LoggingProcessor} with the given {@code processor}.
     *
     * @param processor the processor to notify
     */
    public LoggingProcessor(Processor processor) {
        super(processor);
    }

    @Override
    public void onInitialize() {
        LOG.debug("[#] Starting document");

        notifyInitialize();
    }

    @Override
    public void onStartElement(BasicElement element) {
        LOG.debug("[E] {0}:{1} \"{2}\" : {3} = {4}",
                element.ns().prefix(),
                element.name(),
                element.className(),
                element.metaclass().name(),
                element.id());

        currentId = element.id();

        notifyStartElement(element);
    }

    @Override
    public void onAttribute(BasicAttribute attribute) {
        LOG.debug("[A]    {0}{1} = {2}",
                attribute.name(),
                attribute.isMany() ? " many[" + attribute.index() + "]" : "",
                attribute.value());

        notifyAttribute(attribute);
    }

    @Override
    public void onReference(BasicReference reference) {
        LOG.debug("[R]    {0}{1} = {2} -{3}> {4}",
                reference.name(),
                reference.isMany() ? " many[" + reference.index() + "]" : "",
                Objects.isNull(reference.id()) ? "this" : reference.id(),
                reference.isContainment() ? "C" : "-",
                Objects.equals(reference.idReference(), currentId) ? "this" : reference.idReference());

        notifyReference(reference);
    }

    @Override
    public void onComplete() {
        LOG.debug("[#] Ending document");

        notifyComplete();
    }
}
