package fr.inria.atlanmod.neo4emf.change.impl;

import java.util.ArrayList;

import org.eclipse.emf.common.notify.Notification;

import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.IChangeLogFactory;


public class ChangeLog extends ArrayList<Entry> implements IChangeLog<Entry> {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void addNewEntry(Notification msg) {
		add(IChangeLogFactory.eINSTANCE.createEntry(msg));	
	}

	@Override
	public void removeLastChange() {
		remove(size() - 1);
	}

	@Override
	public void removeLastChanges(int count) {
		for (int i = 0; i < count; i++) {
			removeLastChange();
		}
	}
}
