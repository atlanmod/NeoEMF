package fr.inria.atlanmod.neo4emf;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;

public class NeoListDecorator<E> implements EList<E>, InternalEList<E> {

	private EcoreEList<E> list;
	private INeo4emfObject owner;
	private int featureID;
	
	public NeoListDecorator(EcoreEList<E> l) {
		this.list = l;
		this.owner = (INeo4emfObject)l.getEObject();
		this.featureID = l.getFeatureID();
	}

	@Override
	public boolean add(E e) {
		owner.addChangelogEntry(e, featureID);
		return list.add(e);
	}

	@Override
	public void add(int index, E element) {
		owner.addChangelogEntry(element, featureID);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean added = true;
		for(E e : c) {
			added &= add(e);
		}
		return added;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public E get(int index) {
		return list.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return list.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		owner.addChangelogRemoveEntry((INeo4emfObject)o, featureID);
		return list.remove(o);
	}

	@Override
	public E remove(int index) {
		E removed = list.remove(index);
		owner.addChangelogRemoveEntry((INeo4emfObject)removed, featureID);
		return removed;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	@Override
	public void move(int arg0, E arg1) {
		list.move(arg0, arg1);
	}

	@Override
	public E move(int arg0, int arg1) {
		return move(arg0, arg1);
	}

	@Override
	public boolean addAllUnique(Collection<? extends E> arg0) {
		boolean added = list.addAllUnique(arg0);
		// FIXME the entry is created even if the element hasn't been added
		// FIXME double iteration (one in super implementation, the other below)
		Iterator<? extends E> it = arg0.iterator();
		while(it.hasNext()) {
			owner.addChangelogEntry(it.next(), featureID);
		}
		return added;
	}

	@Override
	public boolean addAllUnique(int arg0, Collection<? extends E> arg1) {
		boolean added = list.addAllUnique(arg0, arg1);
		// FIXME the entry is created even if the element hasn't been added
		// FIXME double iteration (one in super implementation, the other below)
		Iterator<? extends E> it = arg1.iterator();
		while(it.hasNext()) {
			owner.addChangelogEntry(it.next(), featureID);
		}
		return added;
	}

	@Override
	public void addUnique(E arg0) {
		list.addUnique(arg0);
		// FIXME the entry is created even if the element hasn't been added
		owner.addChangelogEntry(arg0, featureID);
	}

	@Override
	public void addUnique(int arg0, E arg1) {
		list.addUnique(arg0, arg1);
		// FIXME the entry is created even if the element hasn't been added
		owner.addChangelogEntry(arg1, featureID);
	}

	@Override
	public NotificationChain basicAdd(E otherEnd, NotificationChain arg1) {
		owner.addChangelogEntry(otherEnd, featureID);
		return list.basicAdd(otherEnd, arg1);
	}

	@Override
	public boolean basicContains(Object arg0) {
		return list.basicContains(arg0);
	}

	@Override
	public boolean basicContainsAll(Collection<?> arg0) {
		return list.basicContainsAll(arg0);
	}

	@Override
	public E basicGet(int arg0) {
		return list.basicGet(arg0);
	}

	@Override
	public int basicIndexOf(Object arg0) {
		return list.basicIndexOf(arg0);
	}

	@Override
	public Iterator<E> basicIterator() {
		return list.basicIterator();
	}

	@Override
	public int basicLastIndexOf(Object arg0) {
		return list.basicIndexOf(arg0);
	}

	@Override
	public List<E> basicList() {
		return list.basicList();
	}

	@Override
	public ListIterator<E> basicListIterator() {
		return list.basicListIterator();
	}

	@Override
	public ListIterator<E> basicListIterator(int arg0) {
		return list.basicListIterator(arg0);
	}

	@Override
	public NotificationChain basicRemove(Object arg0, NotificationChain arg1) {
		return list.basicRemove(arg0, arg1);
	}

	@Override
	public Object[] basicToArray() {
		return list.basicToArray();
	}

	@Override
	public <T> T[] basicToArray(T[] arg0) {
		return list.basicToArray(arg0);
	}

	@Override
	public E setUnique(int arg0, E arg1) {
		throw new UnsupportedOperationException("Not implemented");
	}

}
