/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.AbstractNotifier;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;

import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Processor} that simply notifies registered {@link fr.inria.atlanmod.neoemf.io.writer.Writer} of events,
 * without any additional operation.
 */
@ParametersAreNonnullByDefault
public final class NoopProcessor extends AbstractNotifier<Handler> implements Processor {

    /**
     * Constructs a new {@code NoopProcessor} with the given {@code handlers}.
     *
     * @param handlers the handlers to notify
     */
    public NoopProcessor(Handler... handlers) {
        super(handlers);
    }

    @Override
    public void onInitialize() throws IOException {
        notifyInitialize();
    }

    @Override
    public void onStartElement(BasicElement element) throws IOException {
        notifyStartElement(element);
    }

    @Override
    public void onAttribute(BasicAttribute attribute) throws IOException {
        notifyAttribute(attribute);
    }

    @Override
    public void onReference(BasicReference reference) throws IOException {
        notifyReference(reference);
    }

    @Override
    public void onEndElement() throws IOException {
        notifyEndElement();
    }

    @Override
    public void onComplete() throws IOException {
        notifyComplete();
    }
}
