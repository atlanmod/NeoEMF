/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.io.AbstractNotifier;
import fr.inria.atlanmod.neoemf.io.processor.AbstractProcessor;
import fr.inria.atlanmod.neoemf.io.processor.Processor;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Reader}.
 *
 * @param <T> the type of the source
 */
@ParametersAreNonnullByDefault
public abstract class AbstractReader<T> extends AbstractNotifier<Processor> implements Reader<T> {

    @Override
    public void addNext(Processor processor) {
        Processor next = processor;

        final List<AbstractProcessor> processors = createProcessors();

        List<AbstractProcessor> reverseProcessors = processors.subList(0, processors.size());
        Collections.reverse(reverseProcessors);

        for (AbstractProcessor p : reverseProcessors) {
            p.addNext(next);
            next = p;
        }

        super.addNext(next);
    }

    /**
     * Creates all processors required for analyzing the read source.
     *
     * @return an ordered list of processors
     */
    @Nonnull
    protected List<AbstractProcessor> createProcessors() {
        return Collections.emptyList();
    }
}
