package fr.inria.atlanmod.neo4emf.ui.popup.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.UIJob;

public class OpenNeo4emfDb implements IObjectActionDelegate {

	private Shell shell;
	private IFolder folder;
	
	/**
	 * Constructor for Action1.
	 */
	public OpenNeo4emfDb() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {

		new UIJob(shell.getDisplay(), "Dummy job") {

			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				try {
					URI uri = URI.createURI("neo4emf:" + folder.getLocationURI().getRawSchemeSpecificPart());
					URIEditorInput editorInput = new URIEditorInput(uri);
					if (editorInput != null) {
						IWorkbench workbench = PlatformUI.getWorkbench();
						IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
						page.openEditor(editorInput, "fr.inria.atlanmod.neo4emf.ui.Neo4emfEditor");
					}
				} catch (PartInitException e) {
					e.printStackTrace();
				}
				return Status.OK_STATUS;
			}
		}.schedule();
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		Object elt = structuredSelection.getFirstElement();
		if (elt instanceof IFolder) {
			folder = (IFolder) elt;
		}
		
	}

}
