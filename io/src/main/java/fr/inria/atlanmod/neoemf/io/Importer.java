/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.processor.Processor;
import fr.inria.atlanmod.neoemf.io.reader.Reader;
import fr.inria.atlanmod.neoemf.io.reader.xmi.XmiStreamReader;

import java.io.InputStream;
import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;

public class Importer {

    private Importer() {
    }

    public static void fromXmi(InputStream stream, PersistenceHandler... persistenceHandlers) throws Exception {
        checkArgument(persistenceHandlers.length > 0);

        Reader reader = new XmiStreamReader();
        Processor processor = reader.defaultProcessor();

        Arrays.stream(persistenceHandlers).forEach(processor::addHandler);
        reader.addHandler(processor);

        reader.read(stream);
    }
}
