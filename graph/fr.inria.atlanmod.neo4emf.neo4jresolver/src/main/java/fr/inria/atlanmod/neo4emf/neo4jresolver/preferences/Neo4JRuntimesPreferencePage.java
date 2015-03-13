package fr.inria.atlanmod.neo4emf.neo4jresolver.preferences;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import fr.inria.atlanmod.neo4emf.neo4jresolver.logger.Logger;
import fr.inria.atlanmod.neo4emf.neo4jresolver.providers.Neo4jRuntimeLabelProvider;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntime;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.internal.Neo4JRuntimesManager;

/**
 * 
 * @author abelgomez
 *
 */
public class Neo4JRuntimesPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private List<INeo4jRuntime> runtimes;

	private TableViewer viewer;
	
	private Button addButton;

	private Button removeButton;

	private Observer refreshObserver = new Observer() {
		@Override
		public void update(Observable observable, Object object) {
			Display display = Display.getCurrent() != null ? Display.getCurrent() : Display.getDefault();
			display.asyncExec(new Runnable() {
				@Override
				public void run() {
			    	if (viewer != null && !viewer.getTable().isDisposed()) {
			    		viewer.refresh();
			    	}
				}
			});
		}
	};

	
	public Neo4JRuntimesPreferencePage() {
		super("Neo4J Runtimes");
		setDescription("Available Neo4J Runtimes");
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		runtimes = Neo4JRuntimesManager.INSTANCE.getRuntimes();
		noDefaultAndApplyButton();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
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
				removeButton.setEnabled(!event.getSelection().isEmpty());
			}
		});
		Composite buttonsComposite = new Composite(composite, SWT.NULL);
		buttonsComposite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false));
		buttonsComposite.setLayout(new FillLayout(SWT.VERTICAL));
		addButton = new Button(buttonsComposite, SWT.PUSH);
		addButton.setText("&Add More...");
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Neo4JRuntimesManager.INSTANCE.launchInstallRuntimesWizard();
			}
		});
		removeButton = new Button(buttonsComposite, SWT.PUSH);
		removeButton.setText("&Uninstall");
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				if (viewer.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection structuredSelection = (IStructuredSelection) viewer.getSelection();
					INeo4jRuntime runtime = (INeo4jRuntime) structuredSelection.getFirstElement();
					boolean delete = MessageDialog.openQuestion(getShell(), "Uninstall runtime", 
							NLS.bind("Uninstall Neo4J runtime v.{0}?", runtime.getVersion()));
					if (delete) {
						try {
							Neo4JRuntimesManager.INSTANCE.uninstall(runtime.getId());
						} catch (IOException e) {
							Logger.log(Logger.SEVERITY_ERROR, 
									NLS.bind("Unable to uninstall {0} runtime", runtime.getId()), e);
						}
					}
				}
			}
		});
		if (runtimes instanceof Observable) {
			((Observable) runtimes).addObserver(refreshObserver);
		}
		return composite;
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