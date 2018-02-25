/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.examples;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.util.Objects.nonNull;

/**
 * This abstract example wizard simply unzips a number of zips into the workspace as projects. It does not offer any
 * pages but can be added as a new wizard to the new wizards dialog through the {@code org.eclipse.ui.newWizards}
 * extension point.
 * <p>
 * Clients should subclass this class and override the {@link #getProjectDescriptors()} method to provide the location
 * of the project zips that should be unzipped into the workspace. Note that any projects that are already in the
 * workspace will <i>not</i> be overwritten because the user could have made changes to them that would be lost.
 * <p>
 * It is highly recommended when registering subclasses to the new wizards extension point that the wizard declaration
 * should have {@code canFinishEarly == true} and {@code hasPages == false}. Any label and icon can be freely given to
 * the wizard to suit the needs of the client.
 * <p>
 * This class originally came from plugin {@code org.eclipse.emf.ocl.examples}.
 */
public abstract class AbstractExampleWizard extends Wizard implements INewWizard {

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        // Do nothing
    }

    @Override
    public boolean performFinish() {
        Collection<ProjectDescriptor> projectDescriptors = getProjectDescriptors();

        try {
            getContainer().run(true, false, monitor -> new WorkspaceModifyOperation() {
                @Override
                protected void execute(IProgressMonitor m) throws CoreException {
                    m.beginTask("Unzipping example...", projectDescriptors.size());

                    for (ProjectDescriptor p : projectDescriptors) {
                        try {
                            extractProject(p, m);
                        }
                        catch (CoreException e) {
                            log(e);
                        }

                        m.worked(1);
                    }
                }
            }.run(monitor));
        }
        catch (InvocationTargetException e) {
            log(e);
        }
        catch (InterruptedException e) {
            // We cannot be interrupted, just proceed as normal
        }

        return true;
    }

    /**
     * The subclass provides the specific project descriptors for the projects that should be unzipped into the
     * workspace. Note that any projects that already exist in the workspace will not be overwritten as they may contain
     * changes made by the user.
     *
     * @return the collection of project descriptors that should be unzipped into the workspace.
     */
    protected abstract Collection<ProjectDescriptor> getProjectDescriptors();

    /**
     * Any exception occuring during the example initialization (projects unzipping, workspace refreshing, ...) will be
     * handed over to this method. Subclasses should override this in order to properly log them.
     *
     * @param e the exception that should be logged.F
     */
    private void log(Exception e) {
        if (CoreException.class.isInstance(e)) {
            NeoEMFExamplesPlugin.getDefault().getLog().log(CoreException.class.cast(e).getStatus());
        }
        else {
            NeoEMFExamplesPlugin.getDefault().getLog().log(
                    new Status(IStatus.ERROR, NeoEMFExamplesPlugin.PLUGIN_ID, IStatus.ERROR, e.getMessage(), e));
        }
    }

    /**
     * This will extract the project described by {@code descriptor}, open it and refresh the workspace.
     *
     * @param descriptor the description of the project as it should be unzipped.
     * @param monitor    the {@link IProgressMonitor} that will be used to monitor the operation.
     */
    private void extractProject(ProjectDescriptor descriptor, IProgressMonitor monitor) throws CoreException {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(descriptor.projectName());
        if (project.exists()) {
            return;
        }

        project.create(monitor);

        URL zipUrl = FileLocator.find(
                Platform.getBundle(descriptor.bundleName()),
                new org.eclipse.core.runtime.Path(descriptor.zipLocation()),
                null);

        try (ZipInputStream zis = new ZipInputStream(zipUrl.openStream())) {
            copyAll(zis, Paths.get(project.getLocation().toOSString()), Pattern.quote(descriptor.projectName()));
        }
        catch (IOException e) {
            log(e);
        }


        project.open(monitor);
        project.refreshLocal(IResource.DEPTH_INFINITE, monitor);

        // Close and re-open the project to force eclipse to re-evaluate any natures that this project may have.
        project.close(monitor);
        project.open(monitor);
    }

    /**
     * Copy all the content of the ZIP stream to the specified {@code rootDir}.
     *
     * @param zis         the stream to copy
     * @param rootDir     the target directory to store the content
     * @param projectName the name of the project
     *
     * @throws IOException if an I/O error occurs during the extraction
     */
    private void copyAll(ZipInputStream zis, Path rootDir, String projectName) throws IOException {
        ZipEntry entry;

        while (nonNull(entry = zis.getNextEntry())) {
            Path file = rootDir.resolve(entry.getName().replaceFirst("^" + projectName + "/", ""));

            if (!entry.isDirectory()) {
                Files.createDirectories(file.getParent());
                Files.copy(zis, file, StandardCopyOption.REPLACE_EXISTING);
            }

            zis.closeEntry();
        }
    }
}
