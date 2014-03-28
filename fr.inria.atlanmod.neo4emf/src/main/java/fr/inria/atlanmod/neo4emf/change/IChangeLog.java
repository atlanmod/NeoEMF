package fr.inria.atlanmod.neo4emf.change;

import java.util.Iterator;
import java.util.List;

public interface IChangeLog<E> {

	public boolean add(E entry);
	
	public Iterator<E> iterator();
	
	public void clear();
	
	public int size();
	
	public List<E> changes();

}
