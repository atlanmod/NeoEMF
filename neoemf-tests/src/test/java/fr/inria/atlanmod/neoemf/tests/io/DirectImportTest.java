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

package fr.inria.atlanmod.neoemf.tests.io;

import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import java.io.IOException;

/**
 * A test case about the import from files to {@link fr.inria.atlanmod.neoemf.data.Backend}s.
 */
public class DirectImportTest extends AbstractMigrationTest {

    /**
     * Compares a model read with standard EMF and another read with NeoEMF.
     * <p>
     * The models use XPath as references.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    public void testCompareStandard() throws IOException {
        URI uri = IOResourceManager.xmiStandard();

        EObject actual = loadWithNeoEMF(uri);
        EObject expected = loadWithEMF(uri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);
    }

    /**
     * Compares a compressed model read with standard EMF and another read with NeoEMF.
     * <p>
     * The models use XPath as references.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    public void testCompareStandardCompressed() throws IOException {
        URI uri = IOResourceManager.zxmiStandard();

        EObject actual = loadWithNeoEMF(uri);
        EObject expected = loadWithEMF(uri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);
    }

    /**
     * Compares a model read with standard EMF and another read with NeoEMF.
     * <p>
     * The models use {@code xmi:id} as references.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    public void testCompareWithId() throws IOException {
        URI uri = IOResourceManager.xmiWithId();

        EObject actual = loadWithNeoEMF(uri);
        EObject expected = loadWithEMF(uri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);
    }

    /**
     * Compares a model read with standard EMF and another read with NeoEMF.
     * <p>
     * The models use {@code xmi:id} as references.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    public void testCompareWithIdCompressed() throws IOException {
        URI uri = IOResourceManager.zxmiWithId();

        EObject actual = loadWithNeoEMF(uri);
        EObject expected = loadWithEMF(uri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);
    }
}
