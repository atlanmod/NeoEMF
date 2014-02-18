package fr.inria.atlanmod.neo4emf.change;

import java.util.List;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;

import fr.inria.atlanmod.neo4emf.change.impl.Entry;
import fr.inria.atlanmod.neo4emf.drivers.ISerializer;

public interface IChangeLog<E> {

	
	public void addNewEntry (Notification msg);
	
	public boolean add(E e);
	
	public void lock();
	
	public void unlock();
	
	public void setSaver(ISerializer s);
	
	public Iterator<E> iterator();
	
	public void clear();
	
}
