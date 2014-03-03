package fr.inria.atlanmod.neo4emf.change;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;

public interface IChangeLog<E> {

	public void addNewEntry(Notification msg);

//	public void removeLastChange();

//	public void removeLastChanges(int count);
	
	public boolean add(E entry);
	
	public Iterator<E> iterator();
	
	public void clear();
	
	public int size();
	
	public List<E> changes();

}
