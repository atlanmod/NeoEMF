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

package fr.inria.atlanmod.neoemf.io.bench;

import fr.inria.atlanmod.neoemf.io.Importer;
import fr.inria.atlanmod.neoemf.io.PersistenceHandler;
import fr.inria.atlanmod.neoemf.io.impl.CounterPersistenceHandlerDecorator;
import fr.inria.atlanmod.neoemf.io.impl.TimerPersistenceHandlerDecorator;
import fr.inria.atlanmod.neoemf.io.mock.DummyPersistenceHandler;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

//@Ignore
public class XmiStreamReaderBench extends AllInputBench {

    private PersistenceHandler dummyPersistenceHandler;

    @Before
    public void setUp() throws Exception {
        dummyPersistenceHandler = new DummyPersistenceHandler();
        dummyPersistenceHandler = new TimerPersistenceHandlerDecorator(dummyPersistenceHandler, "dummy1-timer");
        dummyPersistenceHandler = new CounterPersistenceHandlerDecorator(dummyPersistenceHandler, "dummy1-counter");
    }

    @Test
    public void benchSet1() throws Exception {
        readXmi(getSet1(), dummyPersistenceHandler);
    }

    @Test
    public void benchSet2() throws Exception {
        readXmi(getSet2(), dummyPersistenceHandler);
    }

    @Test
    public void benchSet3() throws Exception {
        readXmi(getSet3(), dummyPersistenceHandler);
    }

    @Test
    @Ignore("XMI file not present in commit")
    public void benchSet4() throws Exception {
        readXmi(getSet4(), dummyPersistenceHandler);
    }

    @Test
    @Ignore("XMI file not present in commit")
    public void benchSet5() throws Exception {
        readXmi(getSet5(), dummyPersistenceHandler);
    }

    private void readXmi(File file, PersistenceHandler persistenceHandler) throws Exception {
        registerEPackageFromEcore("java", "http://www.eclipse.org/MoDisco/Java/0.2.incubation/java");
        try {
            Importer.fromXmi(file, persistenceHandler);
        }
        catch (Exception e) {
            NeoLogger.error(e);
            throw e;
        }
    }
}