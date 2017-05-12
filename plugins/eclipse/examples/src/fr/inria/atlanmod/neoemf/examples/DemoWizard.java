package fr.inria.atlanmod.neoemf.examples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This will allow the user to unzip the NeoEMF demo example.
 * <p>
 * This class is reused from the plugin <code>org.eclipse.m2m.atl.examples</code>.
 */
public class DemoWizard extends AbstractExampleWizard {
    /**
     * {@inheritDoc}
     * 
     * @see AbstractExampleWizard#getProjectDescriptors()
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
