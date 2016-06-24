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
import fr.inria.atlanmod.neoemf.io.input.xmi.XmiSaxReader;
import fr.inria.atlanmod.neoemf.io.mock.CounterHandler;
import fr.inria.atlanmod.neoemf.io.mock.MuteHandler;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

public class XmiSaxReaderTest extends AllIOTest {

    private PersistenceHandler counterHandler;

    @Before
    public void setUp() throws Exception {
        counterHandler = new CounterHandler(new MuteHandler(), "counter1");
    }

    @Test
    public void readThinWithSax() throws Exception {
        readWithSax(getThinXmi());
    }

    @Test
    public void readLightWithSax() throws Exception {
        readWithSax(getLightXmi());
    }

    @Test
    public void readMediumWithSax() throws Exception {
        readWithSax(getMediumXmi());
    }

    @Test
    @Ignore("XMI file not present in commit")
    public void readHeavyWithSax() throws Exception {
        readWithSax(getHeavyXmi());
    }

    @Test
    @Ignore("XMI file not present in commit")
    public void readMonsterWithSax() throws Exception {
        readWithSax(getMonsterXmi());
    }

    /**
     * Reads a file with a SAX reader.
     */
    private void readWithSax(File file) throws Exception {
        read(file, new XmiSaxReader());
    }

    /**
     * Reads a file with the given {@link Reader reader type}.
     */
    private void read(File file, Reader reader) throws Exception {
        try {
            IOManager.importFromFile(file, reader, counterHandler);
        } catch (Exception e) {
            NeoLogger.error(e);
            throw e;
        }
    }
}