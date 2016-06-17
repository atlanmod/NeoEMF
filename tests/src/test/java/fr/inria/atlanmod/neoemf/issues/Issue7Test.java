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

package fr.inria.atlanmod.neoemf.issues;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test case to reproduce the issue #7 @link{https://github.com/atlanmod/NeoEMF/issues/7}
 */
public class Issue7Test extends AllTest {

    @Test
    public void testIssue7() {
        //MapSamplePackage samplePackage = MapSamplePackage.eINSTANCE;
        MapSampleFactory factory = MapSampleFactory.eINSTANCE;

        SampleModel model = factory.createSampleModel();
        assertThat("Created SampleModel is null", model, notNullValue());
        assertThat("Accessed List is null", model.getContentObjects(), notNullValue());
        assertThat("Accessed List is not empty", model.getContentObjects().isEmpty(), is(true));
    }

}
