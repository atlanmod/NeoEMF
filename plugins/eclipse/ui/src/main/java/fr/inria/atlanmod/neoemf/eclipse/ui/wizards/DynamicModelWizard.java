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
package fr.inria.atlanmod.neoemf.eclipse.ui.wizards;

import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.eclipse.ui.editors.NeoEMFEditor;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFolderMainPage;
import org.eclipse.ui.part.ISetSelectionTarget;

import java.io.File;
import java.util.Map;

/**
 * This is a simple wizard for creating a new dynamic model file.
 */
public class DynamicModelWizard extends Wizard implements INewWizard {
	/**
	 * This caches the class instance.
	 */
	protected EClass eClass;

	/**
	 * This is the folder creation page.
	 */
	protected WizardNewFolderMainPage newFolderCreationPage;

	/**
	 * This is the properties page
	 */
	protected SelectBlueprintsGraphTypeWizardPage selectGraphTypePage;
	
	/**
	 * Remember the selection during initialization for populating the default
	 * container.
	 */
	protected IStructuredSelection selection;

	/**
	 * Remember the workbench during initialization.
	 */
	protected IWorkbench workbench;

	/**
	 * Creates an instance.
	 */
	public DynamicModelWizard(EClass eClass) {
		this.eClass = eClass;
	}

	/**
	 * This just records the information.
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(EMFEditPlugin.INSTANCE.getImage("full/wizban/NewModel")));
	}

	/**
	 * Create a new model.
	 */
	EObject createInitialModel() {
		return PersistenceFactory.eINSTANCE.create(eClass);
	}

	/**
	 * Do the work after everything is specified.
	 */
	@Override
	public boolean performFinish() {
		try {
			// Get the properties
			final Map<?, ?> options = selectGraphTypePage.getProperties();
			// Remember the folder.
			//
			final IFolder dbFolder = createNewFolder();
			// We need the folder path, but the folder MUST not exist yet!!
			dbFolder.delete(true, new NullProgressMonitor());
			// Get the URI of the model file.
			//
			final URI dbURI = NeoBlueprintsURI.createNeoGraphURI(new File(dbFolder.getRawLocation().toOSString()));
			
			// Do the work within an operation.
			//
			WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
				@Override
				protected void execute(IProgressMonitor progressMonitor) {
					if (progressMonitor == null) progressMonitor = new NullProgressMonitor();
					progressMonitor.beginTask("Create NeoEMF resource", 2);
					Resource resource = null;
					try {
						// Create a resource set
						//
					    PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, BlueprintsPersistenceBackendFactory.getInstance());
					    
						ResourceSet resourceSet = new ResourceSetImpl();
						resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.eINSTANCE);
						resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(true));
						
						// Create a resource for this file.
						//
						resource = resourceSet.createResource(dbURI);

						// Add the initial model object to the contents.
						//
						EObject rootObject = createInitialModel();
						if (rootObject != null) {
							resource.getContents().add(rootObject);
						}

						// Save the contents of the resource to the file system.
						//
						resource.save(options);
						dbFolder.refreshLocal(IResource.DEPTH_INFINITE, new SubProgressMonitor(progressMonitor, 1));
					} catch (Exception exception) {
						exception.printStackTrace();
						try {
							dbFolder.delete(true, new SubProgressMonitor(progressMonitor, 1));
						} catch (CoreException e) {
							e.printStackTrace();
						}
					} finally {
						// Unload resource
						//
						if (resource != null) {
							resource.unload();
						}
						progressMonitor.done();
					}
				}
			};

			getContainer().run(false, false, operation);

			// Select the new file resource in the current view.
			//
			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			final IWorkbenchPart activePart = page.getActivePart();
			if (activePart instanceof ISetSelectionTarget) {
				final ISelection targetSelection = new StructuredSelection(dbFolder);
				getShell().getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						((ISetSelectionTarget) activePart).selectReveal(targetSelection);
					}
				});
			}

			// Open an editor on the new file.
			//
			try {
				page.openEditor(new URIEditorInput(dbURI), NeoEMFEditor.EDITOR_ID);
			} catch (PartInitException exception) {
				MessageDialog.openError(workbenchWindow.getShell(), "Open Editor", exception.getMessage());
				return false;
			}

			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * The framework calls this to create the contents of the wizard.
	 */
	@Override
	public void addPages() {
		// Create a page, set the title, and the initial model file name.
		//
		String title = "Dynamic Model";
		String description = NLS.bind("Create a new dynamic {0} instance", new Object[] { eClass.getName() });

		newFolderCreationPage = new WizardNewFolderMainPage("Whatever", selection);
		newFolderCreationPage.setTitle(title);
		newFolderCreationPage.setDescription(description);
		selectGraphTypePage = new SelectBlueprintsGraphTypeWizardPage(SelectBlueprintsGraphTypeWizardPage.class.getName(), title, null);
		selectGraphTypePage.setDescription(description);
		
		addPage(newFolderCreationPage);
		addPage(selectGraphTypePage);
	}

	/**
	 * Creates the folder.
	 */
	public IFolder createNewFolder() {
		return newFolderCreationPage.createNewFolder();
	}
}
