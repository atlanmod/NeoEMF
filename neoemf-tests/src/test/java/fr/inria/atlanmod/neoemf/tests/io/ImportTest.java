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

import fr.inria.atlanmod.neoemf.Tags;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;

/**
 * A test case about the import from XMI to {@link fr.inria.atlanmod.neoemf.data.Backend}s.
 */
public class ImportTest extends AbstractIOTest {

    /**
     * Compares a model read with standard EMF and another read with NeoEMF.
     * <p>
     * The models use XPath as references.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    @Category({Tags.PersistentTests.class, Tags.IOTests.class})
    public void testCompareStandard() throws IOException {
        URI uri = IOResourceManager.xmiStandard();

        EObject actual = loadWithNeoEMF(uri);
        EObject expected = loadWithEMF(uri);

        assertNotifierAreEqual(actual, expected);
    }

    /**
     * Compares a model read with standard EMF and another read with NeoEMF.
     * <p>
     * The models use {@code xmi:id} as references.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    @Category({Tags.PersistentTests.class, Tags.IOTests.class})
    public void testCompareWithId() throws IOException {
        URI uri = IOResourceManager.xmiWithId();

        EObject actual = loadWithNeoEMF(uri);
        EObject expected = loadWithEMF(uri);

        assertNotifierAreEqual(actual, expected);
    }
}
