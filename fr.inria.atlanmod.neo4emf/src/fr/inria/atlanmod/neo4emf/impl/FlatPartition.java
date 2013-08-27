package fr.inria.atlanmod.neo4emf.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jboss.util.collection.SoftValueTreeMap;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

public class FlatPartition extends AbstractPartition {

	private SoftValueTreeMap<Long,INeo4emfObject> map ;
	@Override
	public boolean unloadable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(long nodeId) {
		return map.containsKey(nodeId);
	}

	@Override
	public INeo4emfObject get(long nodeId) {
		return map.get(nodeId);
	}

	@Override
	public List<INeo4emfObject> flattened() {
		List<INeo4emfObject> objList = new ArrayList<INeo4emfObject>();
		for (Map.Entry<Long, INeo4emfObject> entry : map.entrySet())
			objList.add(entry.getValue());
		return objList;
				
	}
	
	public FlatPartition(){
		map = new SoftValueTreeMap<Long, INeo4emfObject>();
	}

	public void put(INeo4emfObject obj) {
		map.put(obj.getNodeId(), obj);
		
	}

	@Override
	public void remove(long nodeId) {
		map.remove(nodeId);
		
	}
	
}
