package fr.inria.atlanmod.neoemf.eclipse.examples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This will allow the user to unzip the NeoEMF Tutorial example.
 * <p>
 * This class is reused from the plugin <code>org.eclipse.m2m.atl.examples</code>.
 */
public class TutorialWizard extends AbstractExampleWizard {

    @Override
    protected Collection<ProjectDescriptor> getProjectDescriptors() {
        final List<ProjectDescriptor> projects = new ArrayList<>(2);

        projects.add(new ProjectDescriptor(
                "fr.inria.atlanmod.neoemf.eclipse.examples",
                "projects/fr.inria.atlanmod.neoemf.tutorial.zip",
                "fr.inria.atlanmod.neoemf.tutorial"));

        return projects;
    }
}
