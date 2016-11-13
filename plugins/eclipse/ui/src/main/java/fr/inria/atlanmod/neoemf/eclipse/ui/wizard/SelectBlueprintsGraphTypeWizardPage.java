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

package fr.inria.atlanmod.neoemf.eclipse.ui.wizard;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;

class SelectBlueprintsGraphTypeWizardPage extends WizardPage {

    // TODO: In the future an extension point or reflection should be used to determine the supported graph types
    private static final String[] GRAPH_TYPES = new String[]{
            "com.tinkerpop.blueprints.impls.tg.TinkerGraph",
            "com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph"
    };
    private ComboViewer comboViewer;
    private TableViewer tableViewer;

    private Button addButton;
    private Button removeButton;

    private List<GraphProperty> graphProperties = new ArrayList<>();

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
        tableViewer.addSelectionChangedListener(event -> removeButton.setEnabled(!event.getSelection().isEmpty()));

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
                IStructuredSelection selection = IStructuredSelection.class.cast(tableViewer.getSelection());
                if (!selection.isEmpty()) {
                    GraphProperty selectedElement = GraphProperty.class.cast(selection.getFirstElement());
                    graphProperties.remove(selectedElement);
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

    private void configureDefaultValues() {
        comboViewer.addSelectionChangedListener(event -> {
            IStructuredSelection selection = IStructuredSelection.class.cast(event.getSelection());
            if (!selection.isEmpty()) {
                String graphName = selection.getFirstElement().toString();
                String[] splitName = graphName.split("\\.");
                String graphId = splitName[splitName.length - 2];
                String graphPrefix = "blueprints." + graphId;
                graphProperties.removeIf(p -> !isNull(p.getProperty()) && !p.getProperty().startsWith(graphPrefix));

                if (GRAPH_TYPES[0].equals(selection.getFirstElement())) {
                    GraphProperty property = new GraphProperty("blueprints.tg.file-type", "JAVA");
                    if (!graphProperties.contains(property)) {
                        graphProperties.add(property);
                    }
                }
                tableViewer.refresh();
            }
        });
        comboViewer.setSelection(new StructuredSelection(GRAPH_TYPES[0]));
    }

    private void createColumns() {
        String[] titles = {"Property", "Value"};
        int[] bounds = {100, 100};

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

    private TableViewerColumn createTableViewerColumn(String title, int bound, @SuppressWarnings("unused") final int colNumber) {
        final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        final TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(bound);
        column.setResizable(true);
        column.setMoveable(true);
        return viewerColumn;
    }

    public Map<?, ?> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        Object graphType = ((IStructuredSelection) comboViewer.getSelection()).getFirstElement();
        properties.put("blueprints.graph", graphType);
        for (GraphProperty property : graphProperties) {
            if ("TRUE".equalsIgnoreCase(property.getValue()) || "FALSE".equalsIgnoreCase(property.getValue())) {
                properties.put(property.getProperty(), Boolean.parseBoolean(property.getValue()));
            }
            else {
                properties.put(property.getProperty(), property.getValue());
            }
        }
        return properties;
    }

    private class GraphProperty {

        private String property;
        private String value;

        public GraphProperty(String property, String value) {
            this.property = property;
            this.value = value;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(getOuterType(), property, value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (isNull(obj) || getClass() != obj.getClass()) {
                return false;
            }
            GraphProperty other = (GraphProperty) obj;
            return Objects.equals(getOuterType(), other.getOuterType())
                    && Objects.equals(property, other.getProperty())
                    && Objects.equals(value, other.getValue());
        }

        private SelectBlueprintsGraphTypeWizardPage getOuterType() {
            return SelectBlueprintsGraphTypeWizardPage.this;
        }
    }

    private abstract class GraphPropertiesEditingSupport extends EditingSupport {

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

    private class PropertyEditingSupport extends GraphPropertiesEditingSupport {

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

    private class ValueEditingSupport extends GraphPropertiesEditingSupport {

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
}
