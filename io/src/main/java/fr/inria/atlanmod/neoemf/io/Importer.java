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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Importer {

    private Importer() {
    }

    public static void fromXmi(File file, PersistenceHandler... persistenceHandlers) throws Exception {
        if (!file.getName().endsWith(".xmi")) {
            throw new IllegalArgumentException("Only XMI files can be read.");
        }

        fromXmi(new FileInputStream(file), persistenceHandlers);
    }

    public static void fromXmi(InputStream stream, PersistenceHandler... persistenceHandlers) throws Exception {
        Reader reader = new XmiStreamReader();

        Processor processor = reader.defaultProcessor();
        for (PersistenceHandler p : persistenceHandlers) {
            processor.addHandler(p);
        }

        reader.read(stream);
    }
}
