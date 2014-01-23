package fr.inria.atlanmod.neo4emf.resourceUtil;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfResource;

public class ChangeAdapterImpl extends AdapterImpl {
	
	public ChangeAdapterImpl () {
		super();
	}
	
	@Override
	public void notifyChanged(Notification msg){
		Neo4emfObject eObject = (Neo4emfObject) msg.getNotifier();
		Neo4emfResource resource = (Neo4emfResource) eObject.eResource();
		if (resource != null) {
			resource.getChangeLog().addNewEntry(msg);
		}
	}
}
