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
	
	private Map<String,Object> tmpOptions;
	
	public ChangeLog(int flushSize, INeo4emfResource resource) {
		this.flushSize = flushSize;
		this.resource = resource;
		// TODO check if this could be a static variable
		this.tmpOptions = new HashMap<String,Object>();
		this.tmpOptions.put("tmp_save", true);
	}

	@Override
	public boolean add(Entry entry) {
		boolean added = changes.add(entry);
		if(size() == flushSize) {
			flushChangeLog();
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
