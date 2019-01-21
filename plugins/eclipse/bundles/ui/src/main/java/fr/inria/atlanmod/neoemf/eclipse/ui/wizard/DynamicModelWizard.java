/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.wizard;

import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.eclipse.ui.editor.NeoEditor;
import fr.inria.atlanmod.neoemf.util.UriFactory;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.Wizard;
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
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFolderMainPage;
import org.eclipse.ui.part.ISetSelectionTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * This is a simple {@link Wizard} for creating a new dynamic model file.
 */
public class DynamicModelWizard extends Wizard implements INewWizard {

    /**
     * The cached class instance.
     */
    private final EClass eClass;

    /**
     * The folder creation page.
     */
    private WizardNewFolderMainPage newFolderCreationPage;

    /**
     * The properties page
     */
    private SelectBlueprintsGraphTypeWizardPage selectGraphTypePage;

    /**
     * The current selection.
     */
    private IStructuredSelection selection;

    /**
     * Remember the workbench during initialization.
     */
    private IWorkbench workbench;

    /**
     * Creates an instance.
     */
    public DynamicModelWizard(EClass eClass) {
        this.eClass = eClass;
    }

    /**
     * This just records the information.
     */
    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;

        setDefaultPageImageDescriptor(
                ExtendedImageRegistry.INSTANCE.getImageDescriptor(
                        EMFEditPlugin.INSTANCE.getImage("full/wizban/NewModel")));
    }

    /**
     * The framework calls this to create the contents of the wizard.
     */
    @Override
    public void addPages() {
        // Create a page, set the title, and the initial model file name.
        String title = "Dynamic Model";
        String description = String.format("Create a new dynamic %s instance", eClass.getName());

        newFolderCreationPage = new WizardNewFolderMainPage("Whatever", selection);
        {
            newFolderCreationPage.setTitle(title);
            newFolderCreationPage.setDescription(description);
        }
        addPage(newFolderCreationPage);

        selectGraphTypePage = new SelectBlueprintsGraphTypeWizardPage(SelectBlueprintsGraphTypeWizardPage.class.getName(), title, null);
        {
            selectGraphTypePage.setDescription(description);
        }
        addPage(selectGraphTypePage);
    }

    /**
     * Do the work after everything is specified.
     */
    @Override
    public boolean performFinish() {
        try {
            // Get the properties
            final Map<String, Object> options = selectGraphTypePage.getProperties();

            // Remember the folder.
            final IFolder dbFolder = newFolderCreationPage.createNewFolder();

            // We need the folder path, but the folder MUST not exist yet!!
            dbFolder.delete(true, new NullProgressMonitor());

            // Get the URI of the model file.
            final URI uri = UriFactory.forName("blueprints").createLocalUri(dbFolder.getRawLocation().toOSString());

            // Do the work within an operation.
            WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
                @Override
                protected void execute(IProgressMonitor progressMonitor) {
                    if (isNull(progressMonitor)) {
                        progressMonitor = new NullProgressMonitor();
                    }
                    progressMonitor.beginTask("Creating NeoEMF resource", 2);
                    Resource resource = null;
                    try {
                        // Create a resource set
                        ResourceSet resourceSet = new ResourceSetImpl();
                        resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(true));

                        // Create a resource for this file.
                        resource = resourceSet.createResource(uri);

                        // Add the initial model object to the contents.
                        EObject rootObject = PersistenceFactory.newInstance(eClass);
                        resource.getContents().add(rootObject);

                        // Save the contents of the resource to the file system.
                        resource.save(options);
                        dbFolder.refreshLocal(IResource.DEPTH_INFINITE, SubMonitor.convert(progressMonitor, 1));
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                        try {
                            dbFolder.delete(true, SubMonitor.convert(progressMonitor, 1));
                        }
                        catch (CoreException e) {
                            e.printStackTrace();
                        }
                    }
                    finally {
                        // Unload resource
                        if (nonNull(resource)) {
                            resource.unload();
                        }
                        progressMonitor.done();
                    }
                }
            };

            getContainer().run(false, false, operation);

            // Select the new file resource in the current view.
            IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
            IWorkbenchPage page = window.getActivePage();

            final IWorkbenchPart activePart = page.getActivePart();
            if (activePart instanceof ISetSelectionTarget) {
                final ISelection targetSelection = new StructuredSelection(dbFolder);
                getShell().getDisplay().asyncExec(() -> ((ISetSelectionTarget) activePart).selectReveal(targetSelection));
            }

            // Open an editor on the new file.
            try {
                page.openEditor(new URIEditorInput(uri), NeoEditor.EDITOR_ID);
            }
            catch (PartInitException exception) {
                MessageDialog.openError(window.getShell(), "Opening NeoEMF Editor", exception.getMessage());
                return false;
            }

            return true;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * A {@link WizardPage} for selecting the type of a Blueprints {@code Backend}.
     */
    static class SelectBlueprintsGraphTypeWizardPage extends WizardPage {

        // TODO: In the future an extension point or reflection should be used to determine the supported graph types
        private static final String[] GRAPH_TYPES = new String[]{
                "com.tinkerpop.blueprints.impls.tg.TinkerGraph",
                "com.tinkerpop.blueprints.impls.neo4j2.Neo4j2Graph"
        };

        private final List<GraphProperty> graphProperties = new ArrayList<>();

        private ComboViewer comboViewer;

        private TableViewer tableViewer;

        private Button addButton;

        private Button removeButton;

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
                    IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
                    if (!selection.isEmpty()) {
                        GraphProperty selectedElement = (GraphProperty) selection.getFirstElement();
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
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                if (!selection.isEmpty()) {
                    String graphName = selection.getFirstElement().toString();
                    String[] splitName = graphName.split("\\.");
                    String graphId = splitName[splitName.length - 2];
                    String graphPrefix = "blueprints." + graphId;
                    graphProperties.removeIf(p -> nonNull(p.getProperty()) && !p.getProperty().startsWith(graphPrefix));

                    if (Objects.equals(GRAPH_TYPES[0], selection.getFirstElement())) {
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

        public Map<String, Object> getProperties() {
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
                return Objects.hash(property, value);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                GraphProperty that = (GraphProperty) o;
                return Objects.equals(property, that.property) &&
                        Objects.equals(value, that.value);
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
}
