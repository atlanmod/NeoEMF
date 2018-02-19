/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.listener;

import fr.inria.atlanmod.commons.log.Level;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Listener} that logs every events.
 */
@ParametersAreNonnullByDefault
public class LoggingListener extends AbstractListener {

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
     * Constructs a new {@code LoggingListener} with the default logging level.
     */
    public LoggingListener() {
        this(Level.INFO);
    }

    /**
     * Constructs a new {@code LoggingListener} with the given logging {@code level}.
     *
     * @param level the logging level to use
     */
    public LoggingListener(Level level) {
        this.level = level;
    }

    @Override
    public void onInitialize() {
        Log.log(level, "[#] Starting document");
    }

    @Override
    public void onStartElement(BasicElement element) {
        Log.log(level, "[E] {0} : {1} = {2}",
                element.metaClass(),
                element.name(),
                element.id().toHexString());

        currentId = element.id();
    }

    @Override
    public void onAttribute(BasicAttribute attribute) {
        Log.log(level, "[A]    {0}{1} = {2}",
                attribute.name(),
                attribute.isMany() ? " many[-1]" : Strings.EMPTY,
                attribute.value());
    }

    @Override
    public void onReference(BasicReference reference) {
        Log.log(level, "[R]    {0}{1} = {2} -{3}> {4}",
                reference.name(),
                reference.isMany() ? " many[-1]" : Strings.EMPTY,
                Objects.isNull(reference.owner()) ? "this" : reference.owner().toHexString(),
                reference.isContainment() ? 'C' : '-',
                Objects.equals(reference.value(), currentId) ? "this" : reference.value().toHexString());
    }

    @Override
    public void onComplete() {
        Log.log(level, "[#] Ending document");
    }
}
