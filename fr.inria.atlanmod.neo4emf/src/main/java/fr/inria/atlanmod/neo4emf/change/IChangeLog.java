package fr.inria.atlanmod.neo4emf.change;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;

public interface IChangeLog<E> extends List<E> {
	

	
	public void addNewEntry (Notification msg);
	
	public void removeLastChange();
	
	public void removeLastChanges(int count);
	
	
}
