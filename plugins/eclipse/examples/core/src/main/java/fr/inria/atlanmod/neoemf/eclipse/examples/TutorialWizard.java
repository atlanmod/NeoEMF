/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.examples;

import java.util.Collection;
import java.util.Collections;

/**
 * This will allow the user to unzip the NeoEMF Tutorial example.
 * <p>
 * This class is reused from the plugin <code>org.eclipse.m2m.atl.examples</code>.
 */
public class TutorialWizard extends AbstractExampleWizard {

    @Override
    protected Collection<ProjectDescriptor> getProjectDescriptors() {
        return Collections.singletonList(
                new ProjectDescriptor(NeoExamplesPlugin.BUNDLE_ID, "fr.inria.atlanmod.neoemf.eclipse.examples.tutorial")
        );
    }
}
