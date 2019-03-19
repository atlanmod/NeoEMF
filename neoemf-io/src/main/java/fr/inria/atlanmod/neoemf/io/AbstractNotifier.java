/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Preconditions.checkNotNull;
import static org.atlanmod.commons.Preconditions.checkState;

/**
 * An abstract {@link Notifier} that provides overall behavior for the management of handlers.
 *
 * @param <H> the type of the notified {@link Handler}
 */
@ParametersAreNonnullByDefault
public abstract class AbstractNotifier<H extends Handler> implements Notifier {

    /**
     * The handlers to notify.
     */
    @Nonnull
    private final List<H> handlers = new ArrayList<>();

    /**
     * Adds the {@code handler} that will be notified.
     *
     * @param handler the handler
     */
    public void addNext(H handler) {
        checkNotNull(handlers, "handler");
        checkState(supportsMultiTargets() || this.handlers.isEmpty(), "This notifier does not support multiple targets");

        this.handlers.add(handler);
    }

    /**
     * Adds all the {@code handlers} that will be notified.
     *
     * @param handlers the handlers
     */
    protected void addNext(Collection<H> handlers) {
        checkNotNull(handlers, "handlers");
        for (H h : handlers) {
            addNext(h);
        }
    }

    /**
     * Returns {@code true} if this notifier allow to notify several handlers.
     *
     * @return {@code true} if this notifier allow to notify several handlers
     */
    protected boolean supportsMultiTargets() {
        return false;
    }

    @Override
    public void notifyInitialize() throws IOException {
        for (H h : handlers) {
            h.onInitialize();
        }
    }

    @Override
    public void notifyStartElement(ProxyElement element) throws IOException {
        checkNotNull(element, "element");

        for (H h : handlers) {
            h.onStartElement(element);
        }
    }

    @Override
    public void notifyAttribute(ProxyAttribute attribute) throws IOException {
        checkNotNull(attribute, "attribute");

        for (H h : handlers) {
            h.onAttribute(attribute);
        }
    }

    @Override
    public void notifyReference(ProxyReference reference) throws IOException {
        checkNotNull(reference, "reference");

        for (H h : handlers) {
            h.onReference(reference);
        }
    }

    @Override
    public void notifyEndElement() throws IOException {
        for (H h : handlers) {
            h.onEndElement();
        }
    }

    @Override
    public void notifyComplete() throws IOException {
        for (H h : handlers) {
            h.onComplete();
        }
    }
}
