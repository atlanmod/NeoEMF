package fr.inria.atlanmod.neo4emf.change.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.IChangeLogFactory;

public class ChangeLogFactory implements IChangeLogFactory {

	public static IChangeLogFactory init() {
		if (eINSTANCE == null) {
			return new ChangeLogFactory();
		} else {
			return eINSTANCE;
		}
	}

	@Override
	public IChangeLog<Entry> createChangeLog() {
		return new ChangeLog();
	}

	@Override
	public Entry createEntry(Notification msg) {
		switch (msg.getEventType()){
			case Notification.ADD:
				return new AddLink(msg);
			case Notification.SET:	
				if (msg.getFeature() instanceof EAttribute) {
					return new SetAttribute(msg);
				} else {
					return new AddLink(msg);
				}
			case Notification.REMOVE:
				if (msg.getFeature() instanceof EReference) {
					return new RemoveLink(msg);		
				}
			default :
				return null;
		}
	}



	@Override
	public NewObject createNewObject(Notification msg) {
		return new NewObject(msg);
	}

	@Override
	public RemoveLink CreateRemoveLink(Notification msg) {
		return new RemoveLink(msg);
	}

	@Override
	public SetAttribute createSetAttribute(Notification msg) {
		return new SetAttribute(msg);
	}

	@Override
	public DeleteObject createDeleteObject(Notification msg) {
		return new DeleteObject(msg);
	}

	@Override
	public AddLink createAddLink(Notification msg) {
		return new AddLink(msg);
	}
}
