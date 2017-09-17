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

import fr.inria.atlanmod.commons.log.Level;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Processor} that logs every events.
 */
@ParametersAreNonnullByDefault
public class LoggingProcessor extends AbstractProcessor<Handler> {

    /**
     * The {@link Level} for logging.
     */
    @Nonnull
    private final Level level;

    /**
     * The current identifier, used to replace a full reference by "this".
     */
    private Id currentId;

    /**
     * Constructs a new {@code LoggingProcessor} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public LoggingProcessor(Handler handler) {
        this(handler, Level.INFO);
    }

    /**
     * Constructs a new {@code LoggingProcessor} with the given {@code handler}.
     *
     * @param handler the handler to notify
     * @param level   the logging level to use
     */
    public LoggingProcessor(Handler handler, Level level) {
        super(handler);
        this.level = level;
    }

    @Override
    public void onInitialize() {
        Log.log(level, "[#] Starting document");

        notifyInitialize();
    }

    @Override
    public void onStartElement(BasicElement element) {
        Log.log(level, "[E] {0} : {1} = {2}",
                element.metaClass(),
                element.name(),
                element.id());

        currentId = element.id();

        notifyStartElement(element);
    }

    @Override
    public void onAttribute(BasicAttribute attribute) {
        Log.log(level, "[A]    {0}{1} = {2}",
                attribute.name(),
                attribute.isMany() ? " many[-1]" : Strings.EMPTY,
                attribute.value());

        notifyAttribute(attribute);
    }

    @Override
    public void onReference(BasicReference reference) {
        Log.log(level, "[R]    {0}{1} = {2} -{3}> {4}",
                reference.name(),
                reference.isMany() ? " many[-1]" : Strings.EMPTY,
                Objects.isNull(reference.owner()) ? "this" : reference.owner(),
                reference.isContainment() ? 'C' : '-',
                Objects.equals(reference.value(), currentId) ? "this" : reference.value());

        notifyReference(reference);
    }

    @Override
    public void onComplete() {
        Log.log(level, "[#] Ending document");

        notifyComplete();
    }
}
