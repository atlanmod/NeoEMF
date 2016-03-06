/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.issues;

import org.junit.Test;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel;

/**
 * Test case to reproduce the issue #7 @link{https://github.com/atlanmod/NeoEMF/issues/7}
 */
public class Issue7Test {
    
    @Test
    public void testIssue7() {
        MapSamplePackage samplePackage = MapSamplePackage.eINSTANCE;
        MapSampleFactory factory = MapSampleFactory.eINSTANCE;
        
        SampleModel model = factory.createSampleModel();
        assert model != null : "Created SampleModel is null";
        assert model.getContentObjects() != null : "Accessed List is null";
        assert model.getContentObjects().isEmpty() : "Accessed List is not empty";
    }
    
}
