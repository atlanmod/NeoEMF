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
import fr.inria.atlanmod.neoemf.io.input.xmi.XmiStaxReader;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

public class XmiReaderTest extends AllIOTest {

    private PersistenceHandler counterHandler;

    @Before
    public void setUp() throws Exception {
        counterHandler = new CounterHandler(new VoidHandler(), "counter1");
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

    @Test
    public void readThinWithStax() throws Exception {
        readWithStax(getThinXmi());
    }

    @Test
    public void readLightWithStax() throws Exception {
        readWithStax(getLightXmi());
    }

    @Test
    public void readMediumWithStax() throws Exception {
        readWithStax(getMediumXmi());
    }

    @Test
    @Ignore("Stax reader is not optimized / XMI file not present in commit")
    public void readHeavyWithStax() throws Exception {
        readWithStax(getHeavyXmi());
    }

    @Test
    @Ignore("Stax reader is not optimized / XMI file not present in commit")
    public void readMonsterWithStax() throws Exception {
        readWithStax(getMonsterXmi());
    }

    /**
     * Reads a file with a StAX reader.
     * <p/>
     * For now, it is not optimized and crash due to too many characters in attributes for big files.
     */
    private void readWithStax(File file) throws Exception {
        read(file, XmiStaxReader.class);
    }

    /**
     * Reads a file with a SAX reader.
     */
    private void readWithSax(File file) throws Exception {
        read(file, XmiSaxReader.class);
    }

    /**
     * Reads a file with the given {@link Reader reader type}.
     */
    private void read(File file, Class<? extends Reader> reader) throws Exception {
        try {
            IOManager.importFromFile(file, reader, counterHandler);
        } catch (Exception e) {
            NeoLogger.error(e);
            throw e;
        }
    }
}