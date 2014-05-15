package fr.inria.atlanmod.neo4emf.change.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.change.IChangeLog;

public class ChangeLog implements IChangeLog<Entry> {
	
	private List<Entry> changes = new ArrayList<Entry>();
	private int flushSize;
	private INeo4emfResource resource;
	private long maxMemory;
	
	private Map<String,Object> tmpOptions;
	
	public ChangeLog(int flushSize, INeo4emfResource resource) {
		this.flushSize = flushSize;
		this.resource = resource;
		// TODO check if this could be a static variable
		this.tmpOptions = new HashMap<String,Object>();
		this.tmpOptions.put("tmp_save", true);
		this.maxMemory = Runtime.getRuntime().maxMemory();
		System.out.println("CL created, max mem : " + (maxMemory/1000000));
	}

	@Override
	public boolean add(Entry entry) {
//		System.out.println(entry.toString());
		boolean added = changes.add(entry);
//		System.out.println(entry);
		long usedMemory = Runtime.getRuntime().totalMemory() -Runtime.getRuntime().freeMemory();
//		if((double)usedMemory/maxMemory > 0.1) {
		if(size() == 50000) {
			System.out.println("max mem, flushing");
			flushChangeLog();
			System.out.println("flushed");
//		}
		}
		return added;
	}
	
	private void flushChangeLog() {
		try {
			this.resource.save(this.tmpOptions);
		}catch(IOException e) {
			e.printStackTrace();
		}
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

	@Override
	public List<Entry> changes() {
		return changes;
	}
}
