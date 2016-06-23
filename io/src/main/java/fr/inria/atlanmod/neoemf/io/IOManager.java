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

import fr.inria.atlanmod.neoemf.io.impl.AbstractInternalHandler;
import fr.inria.atlanmod.neoemf.io.input.Reader;

import java.io.File;

/**
 *
 */
public class IOManager {

    private IOManager() {
    }

    public static void importFromFile(File file, Class<? extends Reader> readerType, PersistenceHandler persistenceHandler) throws Exception {
        Reader reader = readerType.newInstance();
        AbstractInternalHandler internalHandler = reader.getHandlerClass().newInstance();
        internalHandler.addHandler(persistenceHandler);
        reader.addHandler(internalHandler);

        reader.read(file);
    }

}
