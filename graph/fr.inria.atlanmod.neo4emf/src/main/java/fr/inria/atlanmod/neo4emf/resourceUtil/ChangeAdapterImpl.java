package fr.inria.atlanmod.neo4emf.resourceUtil;

import org.eclipse.emf.common.notify.impl.AdapterImpl;

public class ChangeAdapterImpl extends AdapterImpl {
	
	public ChangeAdapterImpl () {
		super();
	}
	
	// TODO check if it can be removed
	/*@Override
	public void notifyChanged(Notification msg){
		Neo4emfObject eObject = (Neo4emfObject) msg.getNotifier();
		Neo4emfResource resource = (Neo4emfResource) eObject.eResource();
		if (resource != null) {
			resource.getChangeLog().addNewEntry(msg);
		}
	}*/
}
