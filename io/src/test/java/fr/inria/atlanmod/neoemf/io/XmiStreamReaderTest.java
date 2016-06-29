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

import fr.inria.atlanmod.neoemf.io.impl.CounterDelegatedPersistenceHandler;
import fr.inria.atlanmod.neoemf.io.mock.DummyPersistenceHandler;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

public class XmiStreamReaderTest extends AllIOTest {

    private PersistenceHandler dummyPersistenceHandler;

    @Before
    public void setUp() throws Exception {
        dummyPersistenceHandler = new CounterDelegatedPersistenceHandler(new DummyPersistenceHandler(), "dummy1");
    }

    @Test
    public void readSet1() throws Exception {
        read(getSet1());
    }

    @Test
    public void readSet2() throws Exception {
        read(getSet2());
    }

    @Test
    public void readSet3() throws Exception {
        read(getSet3());
    }

    @Test
    @Ignore("XMI file not present in commit")
    public void readSet4() throws Exception {
        read(getSet4());
    }

    @Test
    @Ignore("XMI file not present in commit")
    public void readSet5() throws Exception {
        read(getSet5());
    }

    private void read(File file) throws Exception {
        registerJavaEPackage();
        try {
            IOFactory.importXmi(file, dummyPersistenceHandler);
        } catch (Exception e) {
            NeoLogger.error(e);
            throw e;
        }
    }
}