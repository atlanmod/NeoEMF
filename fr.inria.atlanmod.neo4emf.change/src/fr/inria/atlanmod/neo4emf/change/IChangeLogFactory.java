package fr.inria.atlanmod.neo4emf.change;

import org.eclipse.emf.common.notify.Notification;

import fr.inria.atlanmod.neo4emf.change.impl.*;


public interface IChangeLogFactory {
	
	IChangeLogFactory eINSTANCE = ChangeLogFactory.init();
	
	public IChangeLog<Entry> createChangeLog();
	
	public Entry createEntry(Notification msg);
	
	public NewObject createNewObject(Notification msg);
	
	public RemoveLink CreateRemoveLink(Notification msg);
	
	public SetAttribute createSetAttribute(Notification msg);
	
	public DeleteObject createDeleteObject(Notification msg);
	
	public AddLink createAddLink(Notification msg);

}
