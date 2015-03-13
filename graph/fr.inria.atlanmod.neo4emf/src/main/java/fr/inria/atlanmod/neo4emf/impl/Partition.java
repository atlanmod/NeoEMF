package fr.inria.atlanmod.neo4emf.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.jboss.util.collection.SoftValueTreeMap;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

public class Partition extends AbstractPartition {

	private EObject eObjet;
	private List<Tail> tails;
	public EObject geteObjet() {
		return eObjet;
	}
	
	public List<Tail> getTails(){
		return tails;
	}
	@Override
	public boolean unloadable() {
		// TODO Auto-generated method stub
		return false;
	}
	public Partition (EObject eObject){
		Assert.isNotNull(eObject, " eObject should not be null");
		this.eObjet=eObject;
		tails = new ArrayList<Tail>();
	}
	
	public void put (long key, EObject value, EStructuralFeature str  ){
		assert str instanceof EReference : "EStructuralFeature should be of Type EReference";
		Tail tail = getTail(str.getFeatureID());
		if (tail==null){
			tail=new Tail(str.getFeatureID());
			tail.put(key, value);
			tails.add(tail);		
		} else 
			tail.put(key, value);
		
	}
	
	public Tail getTail(int featureID) {
		for (Tail tail : tails)
			if (tail.getFeatureID() == featureID ) return tail;
		Tail newTail = new Tail(featureID);
		tails.add(newTail);
		return newTail;
					
	}
	
	public boolean containsKey(long nodeId) throws NullPointerException {
		if (((INeo4emfObject)eObjet).getNodeId() ==  nodeId) return true;
		for (Tail tail : tails)
			if (tail.containsKey(nodeId)) return true;
		return false;
	}
	public INeo4emfObject get(long nodeId) {
		if ( (((INeo4emfObject)eObjet).getNodeId() ==  nodeId)) return (INeo4emfObject) eObjet;
		for (Tail tail : tails)
			if (tail.containsKey(nodeId))
				return tail.get(nodeId);
		return null;
	}

	/**
	 *  Protected Class Tail
	 * @author abenelal
	 *
	 */
	
	public static class Tail {
		protected int featureID;
		protected SoftValueTreeMap<Long, INeo4emfObject> objectsMap;
		protected Tail(int featureID) {
			super();
			this.featureID = featureID;
			this.objectsMap = new SoftValueTreeMap<Long, INeo4emfObject>();
		}
		
		
		protected INeo4emfObject get(long nodeId) {
				return objectsMap.get(nodeId);
			}

		protected boolean containsKey(long nodeId) {
			return objectsMap.containsKey(nodeId);
		}
		protected int getFeatureID() {
			return featureID;
		}
		protected void setFeatureID(int featureID) {
			this.featureID = featureID;
		}
		protected SoftValueTreeMap<Long, INeo4emfObject> getObjectsMap() {
			return objectsMap;
		}
		protected void setObjectsMap(SoftValueTreeMap<Long, INeo4emfObject> objectsMap) {
			this.objectsMap = objectsMap;
		}
		
		public void put (long key, EObject value){
			objectsMap.put(key, (INeo4emfObject)value);
			
		}
		protected void remove (long key){
			if (containsKey(key)) objectsMap.remove(key);
		}

		protected Collection<? extends INeo4emfObject> flattenedObjects() {
			List<INeo4emfObject> list = new ArrayList<INeo4emfObject>();
				for (Map.Entry<Long, INeo4emfObject> entry : objectsMap.entrySet())
					list.add(entry.getValue());
			return list;
		}
	}

	public List<INeo4emfObject> flattened() {
		List <INeo4emfObject> flattenedList = new ArrayList<INeo4emfObject>();
			for (Tail tail : tails)
				flattenedList.addAll(tail.flattenedObjects());
			flattenedList.add((INeo4emfObject) eObjet);
		return flattenedList;
	}

	@Override
	public void remove(long nodeId) {
		for (Tail tail : tails){
			if (tail.containsKey(nodeId)){
				tail.remove(nodeId);
				return;
			}
				
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<INeo4emfObject> getTailByFeatureID(int featureID) {
		for (Tail tail : tails)
			if (tail.featureID == featureID){
				return (List<INeo4emfObject>) tail.flattenedObjects();
			}
		return Collections.emptyList();		
	}

	
	
}
