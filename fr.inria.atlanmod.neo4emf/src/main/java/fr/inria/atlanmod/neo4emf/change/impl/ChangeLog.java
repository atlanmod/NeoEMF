package fr.inria.atlanmod.neo4emf.change.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;

import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.IChangeLogFactory;
import fr.inria.atlanmod.neo4emf.drivers.ISerializer;

public class ChangeLog implements IChangeLog<Entry> {

	/**
	 * 
	 */
	private static ChangeLog INSTANCE = null;

	private int stackSize;
	private int lock;
	private ISerializer saver;

	private List<Entry> changes = new ArrayList<Entry>();

	ChangeLog(int stackSize) {
		this.stackSize = stackSize;
		this.lock = 0;
	}

	@Override
	public void addNewEntry(Notification msg) {
		if (lock == 0) {
			// Same test in add method, avoid createEntry call
			INSTANCE.add(IChangeLogFactory.eINSTANCE.createEntry(msg));
		}
	}

	public boolean add(Entry e) {
		if (lock == 0) {
			boolean added = changes.add(e);
			if (changes.size() == stackSize) {
				commit();
			}
			return added;
		}
		return false;
	}

	@Override
	public void lock() {
		assert this.lock >= 0 : "Try to lock a negative lock";
		this.lock++;
	}

	@Override
	public void unlock() {
		assert this.lock > 0 : "Try to unlock a negative lock";
		this.lock--;
	}

	public void setSaver(ISerializer s) {
		this.saver = s;
	}

	private void commit() {
		assert saver != null : "No saver provided to the ChangeLog";
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("tmp_save", true);
		saver.save(options);
	}

	public static IChangeLog<Entry> getInstance() {
		if (INSTANCE == null) {
			INSTANCE = (ChangeLog) IChangeLogFactory.eINSTANCE
					.createChangeLog();
		}
		return INSTANCE;
	}

	public Iterator<Entry> iterator() {
		return changes.iterator();
	}

	public void clear() {
		changes.clear();
	}
	

	@Override
	public void removeLastChange() {
		if (INSTANCE != null)
			INSTANCE.changes.remove(INSTANCE.changes.size()-1);		
	}

	@Override
	public void removeLastChanges(int count) {
		if (INSTANCE != null)
			for (int i =0; i< count; i++)
				removeLastChange();
		
	}
}
