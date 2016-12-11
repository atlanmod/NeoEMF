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

package fr.inria.atlanmod.neoemf.tests.issues;

import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test case to reproduce the issue <a href="https://github.com/atlanmod/NeoEMF/issues/7">#7</a>.
 */
public class Issue7Test extends AbstractIssueTest {

    @Test
    public void testIssue7() {
        MapSampleFactory factory = MapSampleFactory.eINSTANCE;

        SampleModel model = factory.createSampleModel();
        assertThat(model).isNotNull();
        assertThat(model.getContentObjects()).isNotNull().isEmpty();
    }
}
