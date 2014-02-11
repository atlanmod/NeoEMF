package fr.inria.atlanmod.neo4emf.change;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;

import fr.inria.atlanmod.neo4emf.drivers.ISerializer;

public interface IChangeLog<E> extends List<E>{
	

	
	public void addNewEntry (Notification msg);
	
	public void lock();
	
	public void unlock();
	
	public void setSaver(ISerializer s);
	
//	public void removeLastChange();
	
//	public void removeLastChanges(int count);
	
	
}
