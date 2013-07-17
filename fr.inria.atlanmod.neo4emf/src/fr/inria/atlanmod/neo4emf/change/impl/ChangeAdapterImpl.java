package fr.inria.atlanmod.neo4emf.change.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

public class ChangeAdapterImpl extends AdapterImpl {
	
	public ChangeAdapterImpl () {
		super();
	}
	
	@Override
	public void notifyChanged(Notification msg){
		
		if (msg.getEventType()==((INeo4emfNotification)msg).GET){
			EObject eObject = (EObject)msg.getNotifier();
			((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
		}
			ChangeLog.getInstance().addNewEntry(msg);
		}
	

}
