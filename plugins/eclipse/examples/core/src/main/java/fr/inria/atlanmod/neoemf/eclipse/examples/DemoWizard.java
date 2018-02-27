/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.examples;

import java.util.Arrays;
import java.util.Collection;

/**
 * This will allow the user to unzip the NeoEMF demo example.
 * <p>
 * This class is reused from the plugin <code>org.eclipse.m2m.atl.examples</code>.
 */
public class DemoWizard extends AbstractExampleWizard {

    @Override
    protected Collection<ProjectDescriptor> getProjectDescriptors() {
        return Arrays.asList(
                new ProjectDescriptor(NeoExamplesPlugin.BUNDLE_ID, "fr.inria.atlanmod.neoemf.eclipse.examples.demo"),
                new ProjectDescriptor(NeoExamplesPlugin.BUNDLE_ID, "org.eclipse.gmt.modisco.java")
        );
    }
}
