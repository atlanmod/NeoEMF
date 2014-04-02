/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.kyanos.ui.commands;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.inria.atlanmod.kyanos.ui.wizards.DynamicModelWizard;



/**
 * Create a dynamic instance of an {@link EClass}.
 * @author abelgomez
 */
public class CreateDynamicInstanceCommand extends AbstractHandler {
	protected static final URI PLATFORM_RESOURCE = URI.createPlatformResourceURI("/", false);

	protected EClass eClass;

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		initializeEClass(event);
		
		URI uri = eClass.eResource().getURI();
		IStructuredSelection selection = StructuredSelection.EMPTY;
		if (uri != null && uri.isHierarchical()) {
			if (uri.isRelative() || (uri = uri.deresolve(PLATFORM_RESOURCE)).isRelative()) {
				IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toString()));
				if (file.exists()) {
					selection = new StructuredSelection(file);
				}
			}
		}

		// Register the EClass EPackage if  it is not registered yet
		if (EPackage.Registry.INSTANCE.getEPackage(eClass.getEPackage().getNsURI()) == null) {
			EPackage.Registry.INSTANCE.put(eClass.getEPackage().getNsURI(), eClass.getEPackage());
		}
		
		DynamicModelWizard dynamicModelWizard = new DynamicModelWizard(eClass);
		dynamicModelWizard.init(PlatformUI.getWorkbench(), selection);
		WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), dynamicModelWizard);

		wizardDialog.open();
		return null;
	}

	public void initializeEClass(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		ISelectionService service = window.getSelectionService();
		ISelection selection = service.getSelection();
		if (selection instanceof IStructuredSelection) {
			Object object = ((IStructuredSelection) selection).getFirstElement();
			if (object instanceof EClass) {
				eClass = (EClass) object;
				setEnabled(!eClass.isAbstract());
				return;
			}
		}
		eClass = null;
		setEnabled(false);
	}
	
}