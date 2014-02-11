package fr.inria.atlanmod.neo4emf.change.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;

import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.IChangeLogFactory;
import fr.inria.atlanmod.neo4emf.drivers.ISerializer;


public class ChangeLog extends ArrayList<Entry> implements IChangeLog<Entry> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ChangeLog INSTANCE=null; 
	
	private int stackSize;
	private int lock;
	private ISerializer saver;
	
	ChangeLog(int stackSize) {
		this.stackSize = stackSize;
		this.lock = 0;
	}
	
	@Override
	public void addNewEntry(Notification msg) {
		System.out.println("add");
		if(lock == 0) {
			// Same test in add method, avoid createEntry call
			INSTANCE.add(IChangeLogFactory.eINSTANCE.createEntry(msg));	
		}
	}
	
	@Override
	public boolean add(Entry e) {
		if(lock == 0) {
			if(size() == stackSize-1) {
				System.out.println("need a commit");
				commit();
			}
			return super.add(e);
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
		if(saver != null) {
			System.out.println("flushing changelog");
			Map<String,Object> options = new HashMap<String,Object>();
			options.put("tmp_save", true);
			saver.save(options);
			System.out.println(size());
		}
		else {
			System.out.println("no saver for the changelog");
		}
	}

	public static IChangeLog<Entry> getInstance() {
		if (INSTANCE==null)
				{ INSTANCE = (ChangeLog) IChangeLogFactory.eINSTANCE.createChangeLog();}
		return INSTANCE ;
	}
}

//	@Override
//	public void removeLastChange() {
//		if (INSTANCE != null)
//			INSTANCE.remove(INSTANCE.size()-1);		
//	}
//
//	@Override
//	public void removeLastChanges(int count) {
//		if (INSTANCE != null)
//			for (int i =0; i< count; i++)
//				removeLastChange();
//		
//	}

//}
