package fr.inria.atlanmod.neoemf.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * <p>
 * This abstract example wizard simply unzips a number of zips into the workspace as projects. It does not
 * offer any pages but can be added as a new wizard to the new wizards dialog through the
 * org.eclipse.ui.newWizards extension point.
 * </p>
 * <p>
 * Clients should subclass this class and override the <code>getProjectDescriptor()</code> method to provide
 * the location of the project zips that should be unzipped into the workspace. Note that any projects that
 * are already in the workspace will <i>not</i> be overwritten because the user could have made changes to
 * them that would be lost.
 * </p>
 * <p>
 * It is highly recommended when registering subclasses to the new wizards extension point that the wizard
 * declaration should have canFinishEarly = true and hasPages = false. Any label and icon can be freely given
 * to the wizard to suit the needs of the client.
 * </p>
 * <p>
 * This class originally came from plugin <code>org.eclipse.emf.ocl.examples</code>.
 * </p>
 */
public abstract class AbstractExampleWizard extends Wizard implements INewWizard {
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// No code is necessary.
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		final Collection<ProjectDescriptor> projectDescriptors = getProjectDescriptors();

		try {
			getContainer().run(true, false, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException,
						InterruptedException {

					final WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
						@Override
						protected void execute(IProgressMonitor m)

						throws CoreException, InvocationTargetException, InterruptedException {
							m.beginTask("Unzipping example...", projectDescriptors.size()); //$NON-NLS-1$

							for (final ProjectDescriptor project : projectDescriptors) {
								unzipProject(project, m);
								m.worked(1);
							}
						}
					};
					op.run(monitor);
				}
			});
		} catch (final InvocationTargetException e) {
			log(e);
		} catch (final InterruptedException e) {
			// We cannot be interrupted, just proceed as normal.
		}

		return true;
	}

	/**
	 * The subclass provides the specific project descriptors for the projects that should be unzipped into
	 * the workspace. Note that any projects that already exist in the workspace will not be overwritten as
	 * they may contain changes made by the user.
	 * 
	 * @return The collection of project descriptors that should be unzipped into the workspace.
	 */
	protected abstract Collection<ProjectDescriptor> getProjectDescriptors();

	/**
	 * Any exception occuring during the example initialization (projects unzipping, workspace refreshing,
	 * ...) will be handed over to this method. Subclasses should override this in order to properly log them.
	 * 
	 * @param e
	 *            Exception that should be logged.
	 */
	protected void log(Exception e) {
		if (e instanceof CoreException) {
			NeoEMFExamplesPlugin.getDefault().getLog().log(((CoreException)e).getStatus());
		} else {
			NeoEMFExamplesPlugin.getDefault().getLog().log(
					new Status(IStatus.ERROR, NeoEMFExamplesPlugin.PLUGIN_ID, IStatus.ERROR, e.getMessage(), e));
		}
	}

	/**
	 * This will unzip the project described by <code>descriptor</code>, open it and refresh the workspace.
	 * 
	 * @param descriptor
	 *            Description of the project as it should be unzipped.
	 * @param monitor
	 *            {@link IProgressMonitor} that will be used to monitor the operation.
	 */
	protected void unzipProject(ProjectDescriptor descriptor, IProgressMonitor monitor) {
		final String bundleName = descriptor.getBundleName();
		final String zipLocation = descriptor.getZipLocation();
		final String projectName = descriptor.getProjectName();

		final URL interpreterZipUrl = FileLocator.find(Platform.getBundle(bundleName), new Path(zipLocation),
				null);

		final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

		if (project.exists()) {
			return;
		}

		try {
			// We make sure that the project is created from this point forward.
			project.create(monitor);

			final ZipInputStream zipFileStream = new ZipInputStream(interpreterZipUrl.openStream());
			ZipEntry zipEntry = zipFileStream.getNextEntry();

			// We derive a regexedProjectName so that the dots don't end up being
			// interpreted as the dot operator in the regular expression language.
			final String regexedProjectName = projectName.replaceAll("\\.", "\\."); //$NON-NLS-1$ //$NON-NLS-2$

			while (zipEntry != null) {
				// We will construct the new file but we will strip off the project
				// directory from the beginning of the path because we have already
				// created the destination project for this zip.
				final File file = new File(project.getLocation().toString(), zipEntry.getName().replaceFirst(
						"^" + regexedProjectName + "/", "")); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$

				if (!zipEntry.isDirectory()) {

					/*
					 * Copy files (and make sure parent directory exist)
					 */
					final File parentFile = file.getParentFile();
					if (null != parentFile && !parentFile.exists()) {
						parentFile.mkdirs();
					}

					OutputStream os = null;

					try {
						os = new FileOutputStream(file);

						final int bufferSize = 102400;
						final byte[] buffer = new byte[bufferSize];
						while (true) {
							final int len = zipFileStream.read(buffer);
							if (zipFileStream.available() == 0) {
								break;
							}
							os.write(buffer, 0, len);
						}
					} finally {
						if (null != os) {
							os.close();
						}
					}
				}

				zipFileStream.closeEntry();
				zipEntry = zipFileStream.getNextEntry();
			}

			project.open(monitor);
			project.refreshLocal(IResource.DEPTH_INFINITE, monitor);

			// Close and re-open the project to force eclipse to re-evaluate
			// any natures that this project may have.
			project.close(monitor);
			project.open(monitor);
		} catch (final IOException e) {
			log(e);
		} catch (final CoreException e) {
			log(e);
		}
	}

	/**
	 * A descriptor class that describes where to find the zipped contents of a project and what that project
	 * should be named when unzipped into the workspace.
	 */
	public static class ProjectDescriptor {
		/** Name of the plugin where the zip file is located. */
		private final String bundleName;

		/** Name of the project that should be created when unzipping. */
		private final String projectName;

		/** Location (relative to the bundle root) of the file to unzip. */
		private final String zipLocation;

		/**
		 * Construct a descriptor that points to a zip file located in a particular bundle at the given
		 * location within that bundle. Also provided is the project name for which the zip is the contents.
		 * Note that this project name should be the same as is in the contents not some alternative name.
		 * 
		 * @param bundleName
		 *            The bundle in the runtime that contains the zipped up project contents.
		 * @param zipLocation
		 *            The location within the bundle where the zip file is located.
		 * @param projectName
		 *            The project name in the workspace that will be created to house the project contents.
		 */
		public ProjectDescriptor(String bundleName, String zipLocation, String projectName) {
			super();
			this.bundleName = bundleName;
			this.zipLocation = zipLocation;
			this.projectName = projectName;
		}

		/**
		 * Returns the bundle name.
		 * 
		 * @return The bundle name.
		 */
		public String getBundleName() {
			return bundleName;
		}

		/**
		 * Returns the project name.
		 * 
		 * @return The project name.
		 */
		public String getProjectName() {
			return projectName;
		}

		/**
		 * Returns the zip file location.
		 * 
		 * @return The zip file location.
		 */
		public String getZipLocation() {
			return zipLocation;
		}
	}
}
