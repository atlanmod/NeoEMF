package fr.inria.atlanmod.neo4emf.util.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

import org.neo4j.graphdb.Node;

import scala.collection.generic.BitOperations.Int;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.change.impl.ChangeLog;
import fr.inria.atlanmod.neo4emf.util.ILoader;
import fr.inria.atlanmod.neo4emf.util.IPersistenceManager;

public class Loader implements ILoader {
	/**
	 * the manager and delegator
	 */
	IPersistenceManager manager;
	/**
	 * a Map of Loaded packages
	 */
	Map<String,EPackage> ePackageMap; 

	public Loader (IPersistenceManager manager){
		this.manager=manager;
		ePackageMap = new  HashMap<String,EPackage>();
	}
	/**
	 * @see ILoader#load(Map)
	 */
	@Override
	public void load(Map<?, ?> options) {
		// TODO introduce the load strategies
		try {
			List <Node> nodes= manager.getAllRootNodes();
			List <INeo4emfObject> objects = new ArrayList<INeo4emfObject>();
			for (Node n : nodes){
				INeo4emfObject obj = getObjectsFromNode(n);
				int newId = manager.getNewPartitionID();
				obj.setPartitionId(newId);
				objects.add(obj);
				manager.createNewPartitionHistory(newId);
			}
			manager.addObjectsToContents(objects);
			manager.putAllToProxy(objects);
		}catch(Exception e) {
			manager.shutdown();
			e.printStackTrace();
		}						
	}
	/**
	 * @see ILoader#getAllInstances(EClass, List)
	 */
	@Override
	public EList<INeo4emfObject> getAllInstances(EClass eClass,
			List<Node> nodeList) {
		EList<INeo4emfObject> eObjectList = new BasicEList<INeo4emfObject>();
		EPackage ePck= eClass.getEPackage();
		int newId = manager.getNewPartitionID();
		for (Node node : nodeList){
			INeo4emfObject obj = (INeo4emfObject)ePck.getEFactoryInstance().create(eClass);
			obj.setNodeId(node.getId());
			obj.setPartitionId(newId);
			eObjectList.add(obj);
			
		}
		return eObjectList;
	}
	/**
	 * get an Object from Node 
	 * @param n  {@link Node}
	 * @return {@link INeo4emfObject}
	 */
	protected INeo4emfObject getObjectsFromNode(Node n) {
		String eClassName = manager.getNodeType(n);
		String ns_uri = manager.getNodeContainingPackage(n);
		EPackage ePck = loadMetamodelFromURI(ns_uri);
		int size = ChangeLog.getInstance().size();
		INeo4emfObject obj = (INeo4emfObject)ePck.getEFactoryInstance().create(getEClassFromNodeName(eClassName,ePck));
		obj.setNodeId(n.getId());	
		ChangeLog.getInstance().removeLastChanges(ChangeLog.getInstance().size()-size);
		return obj ;
	}
	/**
	 * Return an {@link EClass} from its class name
	 * @param eClassName {@link String}
	 * @param ePck {@link EPackage}
	 * @return {@link EClass}
	 */
	protected EClass getEClassFromNodeName(String eClassName, EPackage ePck) {

		for (EClassifier eClassifier :  ePck.getEClassifiers()) {
			if (eClassifier instanceof EClass && eClassifier.getName().equalsIgnoreCase(eClassName))
				return (EClass) eClassifier;
		}
		return null;		
	}
	/**
	 * Return the EEnum from the eClass if Exist 
	 * @param enumName {@link String}
	 * @return
	 */
	protected EEnum getEEnumFromNodeName(String enumName){
		for (Map.Entry<String, EPackage> entry : ePackageMap.entrySet()){
			for (EClassifier eClassifier : entry.getValue().getEClassifiers() ){
				if (eClassifier instanceof EEnum && eClassifier.getName().equals(enumName))
					return (EEnum) eClassifier;
			}
		}
		return null;
	}
	/**
	 * Load packages from the  metamodelUri 
	 * @param metamodelURI {@link String}
	 * @return {@link EPackage}
	 */
	protected EPackage loadMetamodelFromURI(String metamodelURI) {
		EPackage metamodel = null;
		if (ePackageMap.containsKey(metamodelURI)) {
			metamodel = ePackageMap.get(metamodelURI);
		}else {
			if (metamodelURI.equals(EcorePackage.eNS_URI)) {
				metamodel = EcorePackage.eINSTANCE;
			}

			else if(EPackage.Registry.INSTANCE.containsKey(metamodelURI)) {
				metamodel = EPackage.Registry.INSTANCE.getEPackage(metamodelURI);
				registerSubPackagesIfExists(metamodel);
			}
		}
		return metamodel;

	}
	/**
	 * register a subPackage if not exists 
	 * @param metamodel
	 */
	private void registerSubPackagesIfExists(EPackage metamodel) {
		ePackageMap.put(metamodel.getNsURI(), metamodel);

		for(EObject object : metamodel.eContents()){
			if (object instanceof EPackage) registerSubPackagesIfExists((EPackage) object);
		}

	}
	/**
	 * fetches attributes of an {@link EObject} from the Node
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void fetchAttributes(EObject obj, Node n) {
		try{
			int size = ChangeLog.getInstance().size();
			for (EAttribute attr : obj.eClass().getEAllAttributes()) {
				if (attr.getEType() instanceof EEnum){
					EEnum enumCls = getEEnumFromNodeName(attr.getEType().getName());
					obj.eSet(attr, enumCls.getEEnumLiteralByLiteral((String) n.getProperty(attr.getName())));
				}else if (attr.isMany()){

					obj.eSet(attr,Arrays.asList((List<?>)n.getProperty(attr.getName())) );

				}
				else{
					obj.eSet(attr, n.getProperty(attr.getName()));
				}
			}
			ChangeLog.getInstance().removeLastChanges(ChangeLog.getInstance().size() - size);
		}catch (Exception e)
		{	
			manager.shutdown();
		}

	}
	/**
	 * Construct the list of links of <b>obj</b> from the node List
	 */
	@SuppressWarnings("unchecked")
	@Override	
	public void getObjectsOnDemand(EObject obj, int featureId, Node node ,List<Node> nodes) {
		try {
			int size = ChangeLog.getInstance().size();
			int newId=((INeo4emfObject)obj).getPartitionId();
			if (!manager.isHead(obj)){
				newId = manager.createNewPartition(getObjectsFromNode(node),((INeo4emfObject)obj).getPartitionId());
				manager.createNewPartitionHistory(newId);
			}

			EStructuralFeature str = Loader.getFeatureFromID(obj,featureId);
			EList<EObject> objectList = new BasicEList<EObject>();
			for (Node n : nodes){
				EObject object = getObjectsFromNodeIfNotExists(obj, n, newId);
				objectList.add(object);
				manager.putToProxy((INeo4emfObject)object, str, newId);
				manager.updateProxyManager((INeo4emfObject)object, str);
			}

			if (str.isMany())
				((EList<EObject>)obj.eGet(str)).addAll(objectList);
			else obj.eSet(str, objectList.get(0));
			ChangeLog.getInstance().removeLastChanges(ChangeLog.getInstance().size() - size);}
		catch(Exception e){
			manager.shutdown();
		}

	}


	/**
	 * return the <b>Structural Feature from its ID</b>
	 * @param obj {@link EObject}
	 * @param featureId {@link Int}
	 * @return
	 */
	private static EStructuralFeature getFeatureFromID(EObject obj,
			int featureId) {
		for (EStructuralFeature ref : obj.eClass().getEAllStructuralFeatures())
			if (ref.getFeatureID()==featureId) return ref;
		return null;
	}
	/**
	 * Get object from the node if not exist 
	 * @param eObject {@link EObject}
	 * @param node {@link Node}
	 * @param newID {@link Int}
	 * @return
	 */
	private EObject getObjectsFromNodeIfNotExists(EObject eObject, Node node, int newID) {

		EObject eObj = manager.getObjectFromProxy(node.getId());
		if (eObj == null ) {
			eObj = getObjectsFromNode(node);
			((INeo4emfObject)eObj).setPartitionId(newID);}
		else {eObj = getObjectsFromNode(node);
		((INeo4emfObject)eObj).setPartitionId(((INeo4emfObject)eObject).getPartitionId());	
		((INeo4emfObject)eObj).setProxy(true);
		}
		return eObj;
	}
	



}


