package fr.inria.atlanmod.neo4emf.resourceUtil;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

public class ChangeAdapterImpl extends AdapterImpl {
	
	public ChangeAdapterImpl () {
		super();
	}
	
	@Override
	public void notifyChanged(Notification msg){
		
//		if (msg.getEventType()==INeo4emfNotification.GET){
//			EObject eObject = (EObject)msg.getNotifier();
//			((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
//		}
//			ChangeLog.getInstance().addNewEntry(msg);
	}
}
