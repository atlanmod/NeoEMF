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

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.io.Notifier;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Notifier} that reads data from a file.
 *
 * @note It correspond to the head of the parsing process in case of an import.
 */
@ParametersAreNonnullByDefault
public interface StreamReader extends Reader<InputStream> {

    @Override
    void read(InputStream stream) throws IOException;
}
