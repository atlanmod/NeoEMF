package fr.inria.atlanmod.neo4emf.change.impl;

import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.emf.common.notify.Notification;

import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.IChangeLogFactory;

// TODO: The implementation of the ChangeLog as a CopyOnWriteArrayList is buggy
// Re-implement and guarantee that synchronization is properly done!!!!! 
public class ChangeLog extends CopyOnWriteArrayList<Entry> implements IChangeLog<Entry> {
	
//	private List<Entry> internalChangelog = Collections.synchronizedList(new ArrayList<Entry>()); 
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void addNewEntry(Notification msg) {
		add(IChangeLogFactory.eINSTANCE.createEntry(msg));	
	}

	// TODO: Check if this is still needed!!!
	@Override
	public void removeLastChange() {
		remove(size() - 1);
	}

	// TODO: Check if this is still needed!!!
	@Override
	public void removeLastChanges(int count) {
		for (int i = 0; i < count; i++) {
			removeLastChange();
		}
	}

	/*
	@Override
	public boolean add(Entry e) {
		return internalChangelog.add(e);
	}

	@Override
	public void add(int index, Entry element) {
		internalChangelog.add(index, element);
		
	}

	@Override
	public boolean addAll(Collection<? extends Entry> c) {
		return internalChangelog.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Entry> c) {
		return internalChangelog.addAll(index, c);
	}

	@Override
	public void clear() {
		internalChangelog.clear();
	}

	@Override
	public boolean contains(Object o) {
		return internalChangelog.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return internalChangelog.containsAll(c);
	}

	@Override
	public Entry get(int index) {
		return internalChangelog.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return internalChangelog.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return internalChangelog.isEmpty();
	}

	@Override
	public Iterator<Entry> iterator() {
		return internalChangelog.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return internalChangelog.lastIndexOf(o);
	}

	@Override
	public ListIterator<Entry> listIterator() {
		return internalChangelog.listIterator();
	}

	@Override
	public ListIterator<Entry> listIterator(int index) {
		return internalChangelog.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return internalChangelog.remove(o);
	}

	@Override
	public Entry remove(int index) {
		return internalChangelog.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return internalChangelog.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return internalChangelog.retainAll(c);
	}

	@Override
	public Entry set(int index, Entry element) {
		return internalChangelog.set(index, element);
	}

	@Override
	public int size() {
		return internalChangelog.size();
	}

	@Override
	public List<Entry> subList(int fromIndex, int toIndex) {
		return internalChangelog.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return internalChangelog.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return internalChangelog.toArray(a);
	}
	*/
}
