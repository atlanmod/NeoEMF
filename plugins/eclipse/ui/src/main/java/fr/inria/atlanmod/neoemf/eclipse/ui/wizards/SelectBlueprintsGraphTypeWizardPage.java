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

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SelectBlueprintsGraphTypeWizardPage extends WizardPage {

	protected class GraphProperty {
		protected String property;
		protected String value;

		public GraphProperty(String property, String value) {
			this.property = property;
			this.value = value;
		}
		
		public String getProperty() {
			return property;
		}

		public String getValue() {
			return value;
		}
		
		public void setProperty(String property) {
			this.property = property;
		}
		
		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((property == null) ? 0 : property.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GraphProperty other = (GraphProperty) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (property == null) {
				if (other.property != null)
					return false;
			} else if (!property.equals(other.property))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		private SelectBlueprintsGraphTypeWizardPage getOuterType() {
			return SelectBlueprintsGraphTypeWizardPage.this;
		}
	}
	
	// TODO: In the future an extension point or reflection should be
	// used to determine the supported graph types
	protected static final String[] GRAPH_TYPES = new String[] { 
		"com.tinkerpop.blueprints.impls.tg.TinkerGraph", 
		"com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph" };

	
	protected ComboViewer comboViewer;
	protected TableViewer tableViewer;
	protected Button addButton;
	protected Button removeButton;

	protected List<GraphProperty> graphProperties = new ArrayList<>();


	
	protected SelectBlueprintsGraphTypeWizardPage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	@Override
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Label typeLabel = new Label(composite, SWT.NONE);
		typeLabel.setText("Graph type:");

		comboViewer = new ComboViewer(composite, SWT.READ_ONLY);
		comboViewer.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		comboViewer.setContentProvider(new ArrayContentProvider());
		comboViewer.setInput(GRAPH_TYPES);
		
		Label properties = new Label(composite, SWT.NONE);
		properties.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false, 2, 1));
		properties.setText("Additional properties:");
		
	
		Composite tableComposite = new Composite(composite, SWT.NONE);
		tableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		GridLayout tableLayout = new GridLayout(2, false);
		tableLayout.marginWidth = 0;
		tableLayout.marginHeight = 0;
		tableComposite.setLayout(tableLayout);

		Composite tableLayoutComposite = new Composite(tableComposite, SWT.NONE);
		tableLayoutComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		TableColumnLayout tableColumnLayout = new TableColumnLayout();
		tableLayoutComposite.setLayout(tableColumnLayout);
		
		tableViewer = new TableViewer(tableLayoutComposite, SWT.FULL_SELECTION | SWT.BORDER);
		tableViewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.getTable().setLinesVisible(true);

		createColumns();
		tableColumnLayout.setColumnData(tableViewer.getTable().getColumn(0), new ColumnWeightData(20, 200, true)); 
		tableColumnLayout.setColumnData(tableViewer.getTable().getColumn(1), new ColumnWeightData(20, 200, true)); 

		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setInput(graphProperties);
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				removeButton.setEnabled(!event.getSelection().isEmpty());
			}
		});
		
		Composite buttonsComposite = new Composite(tableComposite, SWT.NONE);
		buttonsComposite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false));
		buttonsComposite.setLayout(new FillLayout(SWT.VERTICAL));

		addButton = new Button(buttonsComposite, SWT.PUSH);
		addButton.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ADD));
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				graphProperties.add(new GraphProperty("new.property.name", "value"));
				tableViewer.refresh();
			}
		});
		
		removeButton = new Button(buttonsComposite, SWT.PUSH);
		removeButton.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_ETOOL_DELETE));
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
				if (!selection.isEmpty()) {
					graphProperties.remove(selection.getFirstElement());
					tableViewer.refresh();
				}
			}
		});
		removeButton.setEnabled(false);
		
		configureDefaultValues();
		setErrorMessage(null);
		setMessage(null);
		setControl(composite);
	}


	protected void configureDefaultValues() {
		comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				if (!selection.isEmpty()) {
					String graphName = selection.getFirstElement().toString();
					String[] splitName = graphName.split("\\.");
					String graphId = splitName[splitName.length - 2];
					String graphPrefix = "blueprints." + graphId;
					Iterator<GraphProperty> iterator = graphProperties.iterator();
					while (iterator.hasNext()) {
						GraphProperty property = iterator.next();
						if (property.getProperty() != null && !property.getProperty().startsWith(graphPrefix)) {
							iterator.remove();
						}
					}
					if (GRAPH_TYPES[0].equals(selection.getFirstElement())) {
						GraphProperty property = new GraphProperty("blueprints.tg.file-type", "JAVA");
						if (!graphProperties.contains(property)) {
							graphProperties.add(property);
						}
					}
					tableViewer.refresh();
				}
			}
		});
		comboViewer.setSelection(new StructuredSelection(GRAPH_TYPES[0]));
	}

	protected void createColumns() {
		String[] titles = { "Property", "Value" };
		int[] bounds = { 100, 100 };

		// First column is for the first name
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new CellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setText(((GraphProperty) cell.getElement()).getProperty());
			}
		});
		col.setEditingSupport(new PropertyEditingSupport(tableViewer));

		// Second column is for the last name
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new CellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setText(((GraphProperty) cell.getElement()).getValue());
			}
		});
		col.setEditingSupport(new ValueEditingSupport(tableViewer));

	}

	protected TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
	
	public abstract class GraphPropertiesEditingSupport extends EditingSupport {

		protected final TableViewer viewer;
		protected final CellEditor editor;

		public GraphPropertiesEditingSupport(TableViewer viewer) {
			super(viewer);
			this.viewer = viewer;
			this.editor = new TextCellEditor(viewer.getTable());
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return editor;
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}
	}
	
	public class PropertyEditingSupport extends GraphPropertiesEditingSupport {

		public PropertyEditingSupport(TableViewer viewer) {
			super(viewer);
		}

		@Override
		protected Object getValue(Object element) {
			return ((GraphProperty) element).getProperty();
		}

		@Override
		protected void setValue(Object element, Object value) {
			((GraphProperty) element).setProperty(String.valueOf(value));
			viewer.update(element, null);
		}
	}

	public class ValueEditingSupport extends GraphPropertiesEditingSupport {

		public ValueEditingSupport(TableViewer viewer) {
			super(viewer);
		}

		@Override
		protected Object getValue(Object element) {
			return ((GraphProperty) element).getValue();
		}

		@Override
		protected void setValue(Object element, Object value) {
			((GraphProperty) element).setValue(String.valueOf(value));
			viewer.update(element, null);
		}
	}
	
	public Map<?, ?> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		Object graphType = ((IStructuredSelection) comboViewer.getSelection()).getFirstElement();
		properties.put("blueprints.graph", graphType); 
		for (GraphProperty property : graphProperties) {
			if ("TRUE".equalsIgnoreCase(property.getValue()) || "FALSE".equalsIgnoreCase(property.getValue())) {
				properties.put(property.getProperty(), Boolean.parseBoolean(property.getValue()));
			} else {
				properties.put(property.getProperty(), property.getValue());
			}
		}
		return properties;
	}
}
