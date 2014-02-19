package fr.inria.atlanmod.neo4emf.change;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;

import fr.inria.atlanmod.neo4emf.drivers.ISerializer;

public interface IChangeLog<E> {

	
	public void addNewEntry (Notification msg);
	
	public boolean add(E e);
	
	public void lock();
	
	public void unlock();
	
	public void setSaver(ISerializer s);
	
	public Iterator<E> iterator();

	public void removeLastChange();
	
	public void removeLastChanges(int count);
	
	public void clear();
	
}
