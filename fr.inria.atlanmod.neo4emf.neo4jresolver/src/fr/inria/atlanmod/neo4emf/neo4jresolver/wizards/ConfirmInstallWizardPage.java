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

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import fr.inria.atlanmod.neo4emf.neo4jresolver.providers.Neo4jRuntimeInstallerLabelProvider;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.AbstractNeo4jRuntimeInstaller;

/**
 * @author abelgomez
 *
 */
public class ConfirmInstallWizardPage extends WizardPage {

	private InstallRuntimesWizardData data;
	private TableViewer viewer;
	private Text licenseText;
	private Button acceptButton;
	private Button rejectButton;
	
	public ConfirmInstallWizardPage(InstallRuntimesWizardData data) {
		super(ConfirmInstallWizardPage.class.getName(),
				"Install Neo4J runtime Libraries", null);
		setDescription("Available Neo4J Runtimes to Install");
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.justify = true;
		layout.spacing = 5;
		composite.setLayout(layout);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("The following runtimes will be installed:");
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setLayoutData(new RowData(700, 250));
		Table table = new Table(sashForm, SWT.BORDER);
		viewer = new TableViewer(table);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new Neo4jRuntimeInstallerLabelProvider());
		viewer.setInput(data.getInstallersToInstall());
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (!event.getSelection().isEmpty() && event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					AbstractNeo4jRuntimeInstaller installer = (AbstractNeo4jRuntimeInstaller) selection.getFirstElement();
					licenseText.setText(installer.getLicenseText());
				} else {
					licenseText.setText("");
				}
			}
		});
		licenseText = new Text(sashForm, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI | SWT.V_SCROLL);
		sashForm.setWeights(new int[] { 250, 450} );
		
		acceptButton = new Button(composite, SWT.RADIO);
		acceptButton.setText("I &accept the terms in the license agreements");
		acceptButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setPageComplete(acceptButton.getSelection());
			}
		});
		
		rejectButton = new Button(composite, SWT.RADIO);
		rejectButton.setText("I do &not accept the terms in the license agreements");
		rejectButton.setSelection(true);
		
		setControl(composite);
		setPageComplete(false);
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
	
}
