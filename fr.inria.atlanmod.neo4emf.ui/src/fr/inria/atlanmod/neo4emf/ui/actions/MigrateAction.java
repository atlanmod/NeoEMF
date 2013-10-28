/*
 * Copyright (c) 2008-2012 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package fr.inria.atlanmod.neo4emf.ui.actions;

import org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import fr.inria.atlanmod.neo4emf.ui.migrator.Neo4emfImporter;
import fr.inria.atlanmod.neo4emf.ui.migrator.Neo4emfMigratorUtil;

import java.util.Map;

/**
 * @author Eike Stepper
 */
public class MigrateAction implements IObjectActionDelegate {
	private ISelection selection;

	public MigrateAction() {
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		new Job("Migrating EMF model") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					IFile file = getFile();
					if (file == null) {
						showMessage("The selected element is not a *.genmodel file.", true);
					} else {
						GenModel genModel = getGenModel(file);
						if (genModel == null) {
							showMessage("The selected file does not contain a generator model.", true);
						} else {
							String msg = Neo4emfMigratorUtil.adjustGenModel(genModel);
							if (msg == null) {
								showMessage("The selected generator model was already migrated.", false);
							} else {
								genModel.eResource().save(null);
								showMessage("The selected generator model has been migrated:" + msg, false);
							}
						}
					}
				} catch (Exception ex) {
					return new Status(IStatus.ERROR, Neo4emfImporter.IMPORTER_ID, "Problem while migrating EMF model", ex);
				}

				return Status.OK_STATUS;
			}
		}.schedule();
	}

	protected GenDelegationKind getFeatureDelegation() {
		return GenDelegationKind.REFLECTIVE_LITERAL;
	}

	protected IFile getFile() {
		if (selection instanceof IStructuredSelection) {
			Object element = ((IStructuredSelection) selection).getFirstElement();
			if (element instanceof IFile) {
				IFile file = (IFile) element;
				if ("genmodel".equals(file.getFileExtension())) {
					return file;
				}
			}
		}

		return null;
	}

	protected GenModel getGenModel(IFile file) {
		ResourceSet resourceSet = new ResourceSetImpl();

		Map<String, Object> map = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		map.put("*", new XMIResourceFactoryImpl());

		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), false);
		Resource resource = resourceSet.getResource(uri, true);

		EList<EObject> contents = resource.getContents();
		if (!contents.isEmpty()) {
			EObject object = contents.get(0);
			if (object instanceof GenModel) {
				return (GenModel) object;
			}
		}

		return null;
	}

	protected void showMessage(final String msg, final boolean error) {
		try {
			final Display display = PlatformUI.getWorkbench().getDisplay();
			display.syncExec(new Runnable() {
				public void run() {
					try {
						final Shell shell = new Shell(display);
						if (error) {
							MessageDialog.openError(shell, "Neo4EMF Migrator", msg);
						} else {
							MessageDialog.openInformation(shell, "Neo4EMF Migrator", msg);
						}
					} catch (RuntimeException ignore) {
					}
				}
			});
		} catch (RuntimeException ignore) {
		}
	}
}
