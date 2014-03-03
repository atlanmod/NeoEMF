package fr.inria.atlanmod.neo4emf.change.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.IChangeLogFactory;

// TODO: The implementation of the ChangeLog as a ArrayList will fail if using adapers to track model changes
// Re-implement and guarantee that synchronization is properly done!!!!! 

public class ChangeLog implements IChangeLog<Entry> {
	
	private List<Entry> changes = new ArrayList<Entry>();
	private static final long serialVersionUID = 1L;
	private int flushSize;
	private INeo4emfResource resource;
	
	public ChangeLog(int flushSize, INeo4emfResource resource) {
		this.flushSize = flushSize;
		this.resource = resource;
	}

	@Override
	public void addNewEntry(Notification msg) {
		changes.add(IChangeLogFactory.eINSTANCE.createEntry(msg));
	}
	
	@Override
	public boolean add(Entry entry) {
		System.out.println("changelog add " + entry.toString());
		boolean added = changes.add(entry);
		if(size() == flushSize) {
			flushChangeLog();
		}
		return added;
	}
	
	private void flushChangeLog() {
		try {
			Map<String,Object> options = new HashMap<String,Object>();
			options.put("tmp_save", true);
			this.resource.save(options);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	// TODO: Check if this is still needed!!!
//	@Override
//	public void removeLastChange() {
//		changes.remove(changes.size() - 1);
//	}

	// TODO: Check if this is still needed!!!
//	@Override
//	public void removeLastChanges(int count) {
//		for (int i = 0; i < count; i++) {
//			removeLastChange();
//		}
//	}

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

	@Override
	public List<Entry> changes() {
		return changes;
	}
}
