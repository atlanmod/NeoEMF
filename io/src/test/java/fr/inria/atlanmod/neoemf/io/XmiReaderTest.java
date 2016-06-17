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

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.io.input.xmi.XmiStreamReader;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.fail;

/**
 *
 */
public class XmiReaderTest extends AllTest {

    private static final String THIN_XMI = "/fr.inria.atlanmod.kyanos.tests.xmi";
    private static final String LIGHT_XMI = "/fr.inria.atlanmod.neo4emf.neo4jresolver.xmi";
    private static final String MEDIUM_XMI = "/org.eclipse.gmt.modisco.java.kyanos.xmi";
    private static final String HEAVY_XMI = "/org.eclipse.jdt.core.xmi";
    private static final String MONSTER_XMI = "/org.eclipse.jdt.source.all.xmi";

    private PersistenceHandler handlerConsole;

    @Before
    public void setUp() throws Exception {
        handlerConsole = new ConsoleHandler("console1");
    }

    @Test
    public void readThin() throws Exception {
        File xmi = new File(XmiReaderTest.class.getResource(THIN_XMI).getFile());
        read(xmi);
    }

    @Test
    public void readLight() throws Exception {
        File xmi = new File(XmiReaderTest.class.getResource(LIGHT_XMI).getFile());
        read(xmi);
    }

    @Test
    public void readMedium() throws Exception {
        File xmi = new File(XmiReaderTest.class.getResource(MEDIUM_XMI).getFile());
        read(xmi);
    }

    @Test
    @Ignore
    public void readHeavy() throws Exception {
        File xmi = new File(XmiReaderTest.class.getResource(HEAVY_XMI).getFile());
        read(xmi);
    }

    @Test
    @Ignore
    public void readMonster() throws Exception {
        File xmi = new File(XmiReaderTest.class.getResource(MONSTER_XMI).getFile());
        read(xmi);
    }

    private void read(File file) throws Exception {
        try {
            IOManager.importFromFile(file, XmiStreamReader.class, handlerConsole);
        } catch (Exception e) {
            NeoLogger.error(e);
            throw e;
        }
    }
}