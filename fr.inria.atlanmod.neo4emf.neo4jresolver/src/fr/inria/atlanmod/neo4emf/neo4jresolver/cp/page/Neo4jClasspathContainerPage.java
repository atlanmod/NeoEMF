package fr.inria.atlanmod.neo4emf.neo4jresolver.cp.page;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import fr.inria.atlanmod.neo4emf.neo4jresolver.cp.container.INeo4jClasspathContainer;
import fr.inria.atlanmod.neo4emf.neo4jresolver.cp.container.internal.Neo4jClasspathContainer;
import fr.inria.atlanmod.neo4emf.neo4jresolver.providers.Neo4jRuntimeLabelProvider;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntime;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.internal.Neo4JRuntimesManager;

/**
 * 
 * @author abelgomez
 *
 */
public class Neo4jClasspathContainerPage extends WizardPage implements IClasspathContainerPage {

	private List<INeo4jRuntime> runtimes;
	
	private TableViewer viewer;
	
	private Observer refreshObserver = new Observer() {
		@Override
		public void update(Observable observable, Object object) {
			getShell().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
			    	if (viewer != null && !viewer.getTable().isDisposed()) {
			    		viewer.refresh();
			    	}
				}
			});
		}
	};
	
    public Neo4jClasspathContainerPage() {
        super(Neo4jClasspathContainer.class.getName(), "Neo4J Libraries", null);
        setDescription("Select version of Neo4J Libraries");
        setPageComplete(false);
        runtimes = Neo4JRuntimesManager.INSTANCE.getRuntimes();
    }
    
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(2, false));
		Table table = new Table(composite, SWT.BORDER);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		viewer = new TableViewer(table);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new Neo4jRuntimeLabelProvider());
		viewer.setInput(runtimes);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				setPageComplete(!event.getSelection().isEmpty());
			}
		});
		
		Composite buttonsComposite = new Composite(composite, SWT.NULL);
		buttonsComposite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false));
		buttonsComposite.setLayout(new FillLayout(SWT.VERTICAL));
		Button button = new Button(buttonsComposite, SWT.PUSH);
		button.setText("&Add More...");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Neo4JRuntimesManager.INSTANCE.launchInstallRuntimesWizard();
			}
		});
		if (runtimes instanceof Observable) {
			((Observable) runtimes).addObserver(refreshObserver);
		}
        setControl(composite);    
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#finish()
     */
    public boolean finish() {
    	return getSelectedVersion() != null; 
    }

    /* (non-Javadoc)
     * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#getSelection()
     */
    public IClasspathEntry getSelection() {
        return JavaCore.newContainerEntry(INeo4jClasspathContainer.ID.append(getSelectedVersion()));
    }

    /* (non-Javadoc)
     * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#setSelection(org.eclipse.jdt.core.IClasspathEntry)
     */
    public void setSelection(IClasspathEntry containerEntry) {
    }
    
    private String getSelectedVersion() {
    	ISelection selection = viewer.getSelection();
    	if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
    		Object selectedElement = ((IStructuredSelection) selection).getFirstElement();
			return ((INeo4jRuntime)selectedElement).getVersion();
    	}
		return null;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#dispose()
     */
    @Override
    public void dispose() {
		if (runtimes instanceof Observable) {
			((Observable) runtimes).deleteObserver(refreshObserver);
		}
    	super.dispose();
    }
}
