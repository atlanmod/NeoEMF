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
package fr.inria.atlanmod.neo4emf.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.progress.UIJob;

import fr.inria.atlanmod.neo4emf.ui.Neo4emfUiPlugin;
import fr.inria.atlanmod.neo4emf.ui.editors.Neo4emfEditor;

/**
 * 
 * @author abelgomez
 *
 */
public class OpenNeo4emfDbCommand extends AbstractHandler {

	private IFolder folder;

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		ISelectionService service = window.getSelectionService();
		ISelection selection = service.getSelection();
		
		folder = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object elt = structuredSelection.getFirstElement();
			if (elt instanceof IFolder) {
				folder = (IFolder) elt;
			}
		}
		if (folder == null) return null;
		
		new UIJob(window.getShell().getDisplay(), "Create Dynamic Instance") {

			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
					URI uri = URI.createURI("neo4emf:" + folder.getLocationURI().getRawSchemeSpecificPart());
					URIEditorInput editorInput = new URIEditorInput(uri);
					if (editorInput != null) {
						IWorkbench workbench = PlatformUI.getWorkbench();
						IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
						try {
							page.openEditor(editorInput, Neo4emfEditor.EDITOR_ID);
						} catch (PartInitException e) {
							return new Status(IStatus.ERROR, Neo4emfUiPlugin.PLUGIN_ID, "Unable to open editor", e);
						}
					}
				return Status.OK_STATUS;
			}
		}.schedule();
		return null;
	}
}
