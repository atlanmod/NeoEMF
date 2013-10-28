package fr.inria.atlanmod.neo4emf.ui.editors;

import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neo4emf.impl.Neo4emfResource;

public class Neo4emfEditor extends EcoreEditor {

	
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
        for (Resource resource : editingDomain.getResourceSet().getResources()) {
        	if (resource instanceof Neo4emfResource) {
        		for (EObject eObject : resource.getContents()) {
        			Iterator<EObject> iterator = eObject.eAllContents();
        			while (iterator.hasNext()) {
						iterator.next();
					}
        		}
        	}
        }
		super.doSave(progressMonitor);
	}
	
	@Override
	public void dispose() {
        for (Resource resource : editingDomain.getResourceSet().getResources()) {
        	if (resource instanceof Neo4emfResource) {
        		((Neo4emfResource) resource).shutdown();
        	}
        }
		super.dispose();
	}
}
