/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.input.Reader;
import fr.inria.atlanmod.neoemf.io.internal.InternalHandler;

import java.io.File;

/**
 *
 */
public class IOManager {

    private IOManager() {
    }

    public static void importXmi(File file, Reader reader, PersistenceHandler persistenceHandler) throws Exception {
        if (!file.getName().endsWith(".xmi")) {
            throw new IllegalArgumentException("Only XMI files can be read.");
        }

        InternalHandler internalHandler = reader.newHandler();
        internalHandler.addHandler(persistenceHandler);
        reader.addHandler(internalHandler);

        reader.read(file);
    }

}
