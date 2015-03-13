/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neo4emf.neo4jresolver.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

/**
 * @author abelgomez
 *
 */
public class ObservableArrayList<E> extends Observable implements List<E> {

	private ArrayList<E> list = new ArrayList<E>();
	
	/* (non-Javadoc)
	 * @see java.util.List#size()
	 */
	@Override
	public int size() {
		return list.size();
	}

	/* (non-Javadoc)
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	/* (non-Javadoc)
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(E e) {
		boolean result = list.add(e);
		setChanged();
		notifyObservers();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		boolean remove = list.remove(o);
		setChanged();
		notifyObservers();
		return remove;
	}

	/* (non-Javadoc)
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean result = list.addAll(c);
		setChanged();
		notifyObservers();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean result = list.addAll(index, c);
		setChanged();
		notifyObservers();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = list.removeAll(c);
		setChanged();
		notifyObservers();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		boolean result = list.retainAll(c);
		setChanged();
		notifyObservers();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.util.List#clear()
	 */
	@Override
	public void clear() {
		list.clear();
		setChanged();
		notifyObservers();
	}

	/* (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	@Override
	public E get(int index) {
		return list.get(index);
	}

	/* (non-Javadoc)
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		E result = list.set(index, element);
		setChanged();
		notifyObservers();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		list.add(index, element);
		setChanged();
		notifyObservers();
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(int)
	 */
	@Override
	public E remove(int index) {
		E result = list.remove(index);
		setChanged();
		notifyObservers();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	/* (non-Javadoc)
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<E> listIterator() {
		return list.listIterator();
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return list.listIterator(index);
	}

	/* (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}
}
