package fr.inria.atlanmod.neo4emf.change;

import org.eclipse.emf.common.notify.Notification;

import fr.inria.atlanmod.neo4emf.change.impl.AddLink;
import fr.inria.atlanmod.neo4emf.change.impl.ChangeLogFactory;
import fr.inria.atlanmod.neo4emf.change.impl.DeleteObject;
import fr.inria.atlanmod.neo4emf.change.impl.Entry;
import fr.inria.atlanmod.neo4emf.change.impl.NewObject;
import fr.inria.atlanmod.neo4emf.change.impl.RemoveLink;
import fr.inria.atlanmod.neo4emf.change.impl.SetAttribute;


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
