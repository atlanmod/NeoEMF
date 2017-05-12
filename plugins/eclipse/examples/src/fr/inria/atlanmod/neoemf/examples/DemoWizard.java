/*******************************************************************************
 * Copyright (c) 2008, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.examples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This will allow the user to unzip the public2private example.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class DemoWizard extends AbstractExampleWizard {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.m2m.atl.examples.AbstractExampleWizard#getProjectDescriptors()
     */
    @Override
    protected Collection<ProjectDescriptor> getProjectDescriptors() {
        final List<ProjectDescriptor> projects = new ArrayList<ProjectDescriptor>(2);
        projects.add(new ProjectDescriptor("fr.inria.atlanmod.neoemf.examples",
                "projects/fr.inria.atlanmod.neoemf.demo.zip",
                "fr.inria.atlanmod.neoemf.demo"));
        projects.add(new ProjectDescriptor("fr.inria.atlanmod.neoemf.examples",
                "projects/org.eclipse.gmt.modisco.java.zip",
                "org.eclipse.gmt.modisco.java"));
        return projects;
    }

}
