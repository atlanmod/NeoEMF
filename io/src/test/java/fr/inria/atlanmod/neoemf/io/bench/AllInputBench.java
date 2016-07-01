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

package fr.inria.atlanmod.neoemf.io.bench;

import fr.inria.atlanmod.neoemf.io.AllInputTest;

import java.io.File;

public abstract class AllInputBench extends AllInputTest {

    private static final String THIN_XMI = "/bench/fr.inria.atlanmod.kyanos.tests.xmi";
    private static final String LIGHT_XMI = "/bench/fr.inria.atlanmod.neo4emf.neo4jresolver.xmi";
    private static final String MEDIUM_XMI = "/bench/org.eclipse.gmt.modisco.java.kyanos.xmi";
    private static final String HEAVY_XMI = "/bench/org.eclipse.jdt.core.xmi";
    private static final String MONSTER_XMI = "/bench/org.eclipse.jdt.source.all.xmi";

    protected File getSet1() {
        return getResourceFile(THIN_XMI);
    }

    protected File getSet2() {
        return getResourceFile(LIGHT_XMI);
    }

    protected File getSet3() {
        return getResourceFile(MEDIUM_XMI);
    }

    protected File getSet4() {
        return getResourceFile(HEAVY_XMI);
    }

    protected File getSet5() {
        return getResourceFile(MONSTER_XMI);
    }
}
