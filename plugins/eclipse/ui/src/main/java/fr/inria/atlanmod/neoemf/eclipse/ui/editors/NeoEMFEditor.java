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
package fr.inria.atlanmod.neoemf.eclipse.ui.editors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.emf.ecore.presentation.EcoreEditorPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Tree;

import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.map.resources.MapResourceOptions;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.StoreOption;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceFactoryImpl;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;

public class NeoEMFEditor extends EcoreEditor {

	public static final String EDITOR_ID = NeoEMFEditor.class.getName();
	
	public NeoEMFEditor() {
	    super();
	    this.editingDomain.getResourceSet().getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME,
                new PersistentResourceFactoryImpl());
        this.editingDomain.getResourceSet().getResourceFactoryRegistry().getProtocolToFactoryMap()
                .put(NeoMapURI.NEO_MAP_SCHEME, new PersistentResourceFactoryImpl());
    }
	
	@Override
	public void createModel() {
        URI resourceURI = EditUIUtil.getURI(getEditorInput());
        Resource resource = editingDomain.getResourceSet().createResource(resourceURI);
        editingDomain.getResourceSet().eAdapters().add(problemIndicationAdapter);
        // Create the store options depending of the backend
        List<StoreOption> storeOptions = new ArrayList<StoreOption>();
        storeOptions.add(PersistentResourceOptions.EStoreOption.LOGGING);
        Map<Object,Object> options = new HashMap<Object,Object>();
        options.put(PersistentResourceOptions.STORE_OPTIONS,storeOptions);
        if(resource.getURI().scheme().equals(NeoMapURI.NEO_MAP_SCHEME)) {
            storeOptions.add(MapResourceOptions.EStoreMapOption.DIRECT_WRITE);
            
        } else if(resource.getURI().scheme().equals(NeoBlueprintsURI.NEO_GRAPH_SCHEME)) {
            storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.DIRECT_WRITE);
        }
        try {
            resource.load(options);
	    } catch(IOException e1) {
	        NeoLogger.error("Unable to create model for the editor");
	        NeoLogger.error(e1);
	        for(Resource r : editingDomain.getResourceSet().getResources()) {
	            NeoLogger.info(resource.getURI().toString());
	            if(r instanceof PersistentResource) {
	                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)resource);
	            }
	            else {
	                r.unload();
	            }
	        }
	    }
	}
	
	@Override
	  public void createPages()
	  {
	    long begin = System.currentTimeMillis();
	    createModel();
	    // Only creates the other pages if there is something that can be edited
	    //
	    if (!getEditingDomain().getResourceSet().getResources().isEmpty())
	    {
	      // Create a page for the selection tree view.
	      Tree tree = new Tree(getContainer(),SWT.VIRTUAL | SWT.FULL_SELECTION);
//	      Tree tree = new Tree(getContainer(), SWT.VIRTUAL | SWT.MULTI);
	      selectionViewer = new TreeViewer(tree);
	      setCurrentViewer(selectionViewer);

//	      selectionViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
	      selectionViewer.setContentProvider(new LazyAdapterFactoryContentProvider(adapterFactory));
//	      selectionViewer.setLabelProvider(new DecoratingColumLabelProvider(new AdapterFactoryLabelProvider(adapterFactory), new DiagnosticDecorator(editingDomain, selectionViewer, EcoreEditorPlugin.getPlugin().getDialogSettings())));
	      selectionViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
	      selectionViewer.setUseHashlookup(true);
	      selectionViewer.setInput(editingDomain.getResourceSet());
//	      selectionViewer.setSelection(new StructuredSelection(editingDomain.getResourceSet().getResources().get(0)), true);

//	      new AdapterFactoryTreeEditor(selectionViewer.getTree(), adapterFactory);
//	      new ColumnViewerInformationControlToolTipSupport(selectionViewer, new DiagnosticDecorator.EditingDomainLocationListener(editingDomain, selectionViewer));

	      createContextMenuFor(selectionViewer);
	      int pageIndex = addPage(tree);
	      setPageText(pageIndex, EcoreEditorPlugin.INSTANCE.getString("_UI_SelectionPage_label"));

	      getSite().getShell().getDisplay().asyncExec
	        (new Runnable()
	         {
	           public void run()
	           {
	             setActivePage(0);
	           }
	         });
	    }

	    // Ensures that this editor will only display the page's tab
	    // area if there are more than one page
	    //
	    getContainer().addControlListener
	      (new ControlAdapter()
	       {
	        boolean guard = false;
	        @Override
	        public void controlResized(ControlEvent event)
	        {
	          if (!guard)
	          {
	            guard = true;
	            hideTabs();
	            guard = false;
	          }
	        }
	       });

	    getSite().getShell().getDisplay().asyncExec
	      (new Runnable()
	       {
	         public void run()
	         {
	           updateProblemIndication();
	         }
	       });
	    long end = System.currentTimeMillis();
	    NeoLogger.info("NeoEMF Editor Opened in {0} ms", (end-begin));
	  }
	
	@Override
	public void dispose() {
	    NeoLogger.info("Disposing NeoEditor");
        for (Resource resource : editingDomain.getResourceSet().getResources()) {
        	if(resource instanceof PersistentResource) {
        	    PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)resource);
        	}
        	else {
        	    resource.unload();
        	}
        }
		super.dispose();
	}
	
	
}
