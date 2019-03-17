/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.listener;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;

import org.atlanmod.commons.log.Level;
import org.atlanmod.commons.log.Log;
import org.atlanmod.commons.primitive.Strings;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link EventListener} that logs every events.
 */
@ParametersAreNonnullByDefault
public class LoggingEventListener extends AbstractEventListener {

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
     * Constructs a new {@code LoggingEventListener} with the default logging level.
     */
    public LoggingEventListener() {
        this(Level.INFO);
    }

    /**
     * Constructs a new {@code LoggingEventListener} with the given logging {@code level}.
     *
     * @param level the logging level to use
     */
    public LoggingEventListener(Level level) {
        this.level = level;
    }

    @Override
    public void onInitialize() {
        Log.log(level, "[#] Starting document");
    }

    @Override
    public void onStartElement(ProxyElement element) {
        Log.log(level, "[E] {0} : {1} = {2}",
                element.getMetaClass(),
                element.getName(),
                element.getId().getResolved().toHexString());

        currentId = element.getId().getResolved();
    }

    @Override
    public void onAttribute(ProxyAttribute attribute) {
        Log.log(level, "[A]    {0}{1} = {2}",
                attribute.getName(),
                attribute.isMany() ? " many[-1]" : Strings.EMPTY,
                attribute.getValue().getResolved());
    }

    @Override
    public void onReference(ProxyReference reference) {
        Log.log(level, "[R]    {0}{1} = {2} -{3}> {4}",
                reference.getName(),
                reference.isMany() ? " many[-1]" : Strings.EMPTY,
                Objects.isNull(reference.getOwner()) ? "this" : reference.getOwner().toHexString(),
                reference.isContainment() ? 'C' : '-',
                Objects.equals(reference.getValue().getResolved(), currentId) ? "this" : reference.getValue().getResolved().toHexString());
    }

    @Override
    public void onComplete() {
        Log.log(level, "[#] Ending document");
    }
}
