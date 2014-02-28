package fr.inria.atlanmod.neo4emf.change.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;

import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.IChangeLogFactory;

// TODO: The implementation of the ChangeLog as a ArrayList will fail if using adapers to track model changes
// Re-implement and guarantee that synchronization is properly done!!!!! 

public class ChangeLog implements IChangeLog<Entry> {
	
	private List<Entry> changes = new ArrayList<Entry>();

	@Override
	public void addNewEntry(Notification msg) {
		changes.add(IChangeLogFactory.eINSTANCE.createEntry(msg));
	}

	// TODO: Check if this is still needed!!!
	@Override
	public void removeLastChange() {
		changes.remove(changes.size() - 1);
	}

	// TODO: Check if this is still needed!!!
	@Override
	public void removeLastChanges(int count) {
		for (int i = 0; i < count; i++) {
			removeLastChange();
		}
	}

	@Override
	public void add(Entry entry) {
		changes.add(entry);
		
	}

	@Override
	public Iterator<Entry> iterator() {
		return changes.iterator();
	}

	@Override
	public void clear() {
		changes.clear();
	}

	@Override
	public int size() {
		return changes.size();
	}
}
