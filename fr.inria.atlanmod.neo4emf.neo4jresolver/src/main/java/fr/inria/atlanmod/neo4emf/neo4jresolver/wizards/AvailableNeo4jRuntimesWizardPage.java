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
package fr.inria.atlanmod.neo4emf.neo4jresolver.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import fr.inria.atlanmod.neo4emf.neo4jresolver.providers.Neo4jRuntimeInstallerLabelProvider;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.AbstractNeo4jRuntimeInstaller;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntime;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntimeListener;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.internal.Neo4JRuntimesManager;

/**
 * @author abelgomez
 *
 */
public class AvailableNeo4jRuntimesWizardPage extends WizardPage {

	private InstallRuntimesWizardData data;
	private List<AbstractNeo4jRuntimeInstaller> installers;
	private CheckboxTableViewer viewer;
	private Text descText;
	
	INeo4jRuntimeListener listener = new INeo4jRuntimeListener() {
		@Override
		public void notifyChange(Event event) {
			final AbstractNeo4jRuntimeInstaller source = event.getSource();
			getShell().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					if (viewer != null && !viewer.getTable().isDisposed()) {
						if (viewer.getSelection() instanceof IStructuredSelection) {
							IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
							if (selection.getFirstElement() != null) {
								AbstractNeo4jRuntimeInstaller installer = (AbstractNeo4jRuntimeInstaller) selection.getFirstElement();
								if (source.equals(installer) && descText != null && !descText.isDisposed())  {
									descText.setText(installer.getDescription());
								}
							}
						}
					}
				}
			});
		}
	};
	
	public AvailableNeo4jRuntimesWizardPage(InstallRuntimesWizardData data) {
		super(AvailableNeo4jRuntimesWizardPage.class.getName(),
				"Install Neo4J runtime Libraries", null);
		setDescription("Available Neo4J Runtimes to Install");
		this.data = data;
		computeInstallers();
		addInstallersListeners();
	}

	private void computeInstallers() {
		installers = new ArrayList<AbstractNeo4jRuntimeInstaller>();
		List<IPath> installedRuntimes = new ArrayList<IPath>();
		for (INeo4jRuntime runtime : Neo4JRuntimesManager.INSTANCE.getRuntimes()) {
			installedRuntimes.add(new Path(runtime.getVersion()).append(runtime.getId()));
		}
		for (AbstractNeo4jRuntimeInstaller installer : Neo4JRuntimesManager.INSTANCE.getInstallers()) {
			if (!installedRuntimes.contains(new Path(installer.getVersion()).append(installer.getId()))) {
				installers.add(installer);
			}
		}
	}

	private void addInstallersListeners() {
		for (AbstractNeo4jRuntimeInstaller installer : installers) {
			installer.addChangeListener(listener);
		}
	}

	private void removeInstallersListeners() {
		for (AbstractNeo4jRuntimeInstaller installer : installers) {
			installer.removeChangeListener(listener);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());

		Table table = new Table(composite, SWT.BORDER | SWT.CHECK);
		GridData tableLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tableLayoutData.heightHint = 150;
		table.setLayoutData(tableLayoutData);
		viewer = new CheckboxTableViewer(table);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new Neo4jRuntimeInstallerLabelProvider());
		viewer.setInput(installers);
		viewer.addCheckStateListener(new ICheckStateListener() {
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				AbstractNeo4jRuntimeInstaller installer = (AbstractNeo4jRuntimeInstaller) event.getElement();
				if (event.getChecked()) {
					data.getInstallersToInstall().add(installer);
				} else {
					data.getInstallersToInstall().remove(installer);
				}
				setPageComplete(!data.getInstallersToInstall().isEmpty());
			}
		});
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (!event.getSelection().isEmpty() && event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					AbstractNeo4jRuntimeInstaller installer = (AbstractNeo4jRuntimeInstaller) selection.getFirstElement();
					descText.setText(installer.getDescription());
				} else {
					descText.setText("");
				}
			}
		});
		Label descLabel = new Label(composite, SWT.NONE);
		descLabel.setText("Description");
		descText = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		GridData textLayoutData = new GridData(SWT.FILL, SWT.END, true, false);
		textLayoutData.heightHint = 60;
		descText.setLayoutData(textLayoutData);
		descText.setEditable(false);
		checkCanInstallSomething();
		setPageComplete(false);
		setControl(composite);
	}

	private void checkCanInstallSomething() {
		if (installers.isEmpty()) {
			viewer.getTable().setEnabled(false);
			setErrorMessage("No additional runtimes can be installed");
		}
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (viewer != null && !viewer.getTable().isDisposed()) {
			viewer.refresh();
			if (viewer.getElementAt(0) != null) {
				viewer.setSelection(new StructuredSelection(viewer.getElementAt(0)));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	@Override
	public void dispose() {
		removeInstallersListeners();
		super.dispose();
	}
}
