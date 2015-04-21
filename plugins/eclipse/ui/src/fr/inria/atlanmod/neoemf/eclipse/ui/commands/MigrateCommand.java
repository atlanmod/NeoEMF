/*******************************************************************************
 * Copyright (c) 2014 Abel Gómez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel Gómez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.neoemf.eclipse.ui.commands;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.inria.atlanmod.neoemf.eclipse.ui.migrator.KyanosImporter;
import fr.inria.atlanmod.neoemf.eclipse.ui.migrator.KyanosMigratorUtil;

/**
 * 
 * @author abelgomez
 *
 */
public class MigrateCommand extends AbstractHandler {

	protected ISelection selection;
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		ISelectionService service = window.getSelectionService();
		selection = service.getSelection();
	 
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
							String msg = KyanosMigratorUtil.adjustGenModel(genModel);
							if (msg == null) {
								showMessage("The selected generator model was already migrated.", false);
							} else {
								genModel.eResource().save(null);
								showMessage("The selected generator model has been migrated:" + "\n\n" + msg, false);
							}
						}
					}
				} catch (Exception ex) {
					return new Status(IStatus.ERROR, KyanosImporter.IMPORTER_ID, "Problem while migrating EMF model", ex);
				}

				return Status.OK_STATUS;
			}
		}.schedule();
		return null;
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
				@Override
				public void run() {
					try {
						final Shell shell = new Shell(display);
						if (error) {
							MessageDialog.openError(shell, "Kyanos Migrator", msg);
						} else {
							MessageDialog.openInformation(shell, "Kyanos Migrator", msg);
						}
					} catch (RuntimeException ignore) {
					}
				}
			});
		} catch (RuntimeException ignore) {
		}
	}

}
