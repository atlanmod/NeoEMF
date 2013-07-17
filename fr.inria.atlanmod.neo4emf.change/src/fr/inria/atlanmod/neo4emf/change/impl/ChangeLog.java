package fr.inria.atlanmod.neo4emf.change.impl;

import java.util.ArrayList;

import org.eclipse.emf.common.notify.Notification;

import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.IChangeLogFactory;


public class ChangeLog extends ArrayList<Entry> implements IChangeLog<Entry> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ChangeLog INSTANCE=null; 
	@Override
	public void addNewEntry(Notification msg) {
		INSTANCE.add(IChangeLogFactory.eINSTANCE.createEntry(msg));
		
	}

	public static IChangeLog<Entry> getInstance() {
		if (INSTANCE==null)
				{ INSTANCE = (ChangeLog) IChangeLogFactory.eINSTANCE.createChangeLog();}
		return INSTANCE ;
	}

	@Override
	public void removeLastChange() {
		if (INSTANCE != null)
			INSTANCE.remove(INSTANCE.size()-1);		
	}

	@Override
	public void removeLastChanges(int count) {
		if (INSTANCE != null)
			for (int i =0; i< count; i++)
				removeLastChange();
		
	}

}
