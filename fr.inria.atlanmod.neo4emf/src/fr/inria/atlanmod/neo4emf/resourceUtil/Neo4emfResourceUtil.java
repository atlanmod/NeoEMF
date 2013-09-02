package fr.inria.atlanmod.neo4emf.resourceUtil;

import fr.inria.atlanmod.neo4emf.Point;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;


import fr.inria.atlanmod.neo4emf.drivers.IPersistenceService;
import fr.inria.atlanmod.neo4emf.drivers.ISerializer;
import fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceService;

public class Neo4emfResourceUtil {
	
	public void importFromXMI(Resource resource, String xmiPath, String outputPath){
		// Init variables 
		deleteFileOrDirectory(new File(outputPath));
		IPersistenceService graphDB = new PersistenceService(outputPath,null);
		// Serialize the resource in Neo4j DB	
		serializeResource(graphDB,xmiPath, resource);
		
	}
	
	private void serializeResource(IPersistenceService graphDB, String xmiPath, Resource resource){
		
		try {
		EList<EObject> objectsList= resourceToObjectsList(xmiPath);
		Map<EObject,Long> eObjectsToNodes =  persistNodes(graphDB, objectsList);
		System.out.println("persist the nodes");
		persistReferences(graphDB, objectsList, eObjectsToNodes, resource);
		System.out.println("Finish");
		}finally{
			graphDB.shutdown();
		}
	}	


private Map<Point, RelationshipType> createRelTypesMap(Resource resource) {
		Map<Point, RelationshipType> map = new HashMap<Point, RelationshipType>();
		for (EPackage pck : getResourcePackages(resource)){
		for (EClass clsfier : getOrderedClassifiers(pck)){
			for (EStructuralFeature str : clsfier.getEAllReferences())
				if (map.containsKey(new Point(clsfier.getClassifierID(), str.getFeatureID())))
				map.put(new Point(clsfier.getClassifierID(), str.getFeatureID()), DynamicRelationshipType.withName(formatRelationshipName(str)));
			}
		}
		return map;
	}


private String formatRelationshipName(EStructuralFeature str) {
	return FormatClassifierName(str.getEType()) + "__" + CodeGenUtil.format(str.getName(), '_', null, false, false).toUpperCase();
	
}
private String FormatClassifierName(EClassifier cls)
{
  String name = cls.getName();
  String prefix = cls.getEPackage().getNsPrefix();
  return CodeGenUtil.format(name, '_', prefix, true, true).toUpperCase();
}

private EList<EClass> getOrderedClassifiers(EPackage package_) {
	EList<EClass> result = new BasicEList<EClass>();
	Set<EClass> resultSet = new HashSet<EClass>();

    for (Iterator<EClass> iter = getEClasses(package_).iterator(); iter.hasNext(); )
    {
      List<EClass> extendChain = new LinkedList<EClass>();
      Set<EClass> visited = new HashSet<EClass>();
      for (EClass class_ = iter.next(); class_ != null && visited.add(class_); class_ = class_.getESuperTypes().get(0))
      {
        if (package_ == class_.getEPackage() && resultSet.add(class_))
        {
          extendChain.add(0, class_);
        }
      }
      result.addAll(extendChain);
    }
	return result;
}


private EList<EClass> getEClasses(EPackage package_) {
	EList<EClass> listCls = new BasicEList<EClass>();
	
	return listCls;
	
	
}


private EList<EPackage> getResourcePackages(Resource resource){
	EList<EPackage> pckList = new BasicEList<EPackage>(); 
	Iterator<EObject> iterator = resource.getAllContents();
	while (iterator.hasNext()){
		EObject obj = iterator.next();
		if (obj instanceof EPackage)
			pckList.add((EPackage) obj);
	}
	return pckList;
}


/**
 * Constructor
 * {@code DB_PATH} the path of the data base & {@code xmiPath} path of the data base
 * @param DB_PATH 
 * @param xmiPath
 */

	private static void deleteFileOrDirectory( final File file )
    {
        if ( !file.exists() ) return;
        if ( file.isDirectory() )
            for ( File child : file.listFiles() )
            	deleteFileOrDirectory( child );
        else
            file.delete();
    }



	private void persistReferences(IPersistenceService graphDB, 
			EList<EObject> objectsList,
			Map<EObject,Long> eObjectsToNodes,
			Resource resource) {
		
		Map<Point,RelationshipType> reltypesMap = createRelTypesMap(resource);
		
		int i=0;
		Transaction tx = graphDB.beginTx();
		try {
			
		for (EObject eObject : objectsList){
			for (EReference eRef : eObject.eClass().getEAllReferences()){
				if (eObject.eGet(eRef) != null)
					if (eRef.isMany()){
						@SuppressWarnings({"unchecked"})
						EList<EObject> objList = (EList<EObject>) eObject.eGet(eRef);
						for (EObject eObjectEnd : objList){
							createPropertyForObjects(graphDB, eObjectsToNodes, eObject, eObjectEnd, eRef, reltypesMap);
							
								}
							}
					else {
							EObject singleEObject = (EObject) eObject.eGet(eRef);
							createPropertyForObjects(graphDB, eObjectsToNodes, eObject, singleEObject, eRef, reltypesMap);
						}
				i++;
				if (i==ISerializer.DEFAULT_TRANSACTIONS_COUNT){
					tx.success();
					tx.finish();
					tx=graphDB.beginTx();
					i=0;
						}
					
					
			}
				
		}
		}finally{
			tx.success();
			tx.finish();
			
		}
	}

	private void createPropertyForObjects(IPersistenceService graphDB,
						Map<EObject, Long> eObjectsToNodes,
					EObject eObject,
				EObject eObjectEnd, 
			EReference eRef,
			Map<Point,RelationshipType> reltypes) {
		
		Node node = graphDB.getNodeById(eObjectsToNodes.get(eObject));
		Node nodeEnd = graphDB.getNodeById(eObjectsToNodes.get(eObjectEnd));
		node.createRelationshipTo(nodeEnd, reltypes.get(new Point(eObject.eClass().getClassifierID(), eRef.getFeatureID())));		
		// get the reltype
	}

	private EList<EObject> resourceToObjectsList(String xmiPath){
		EList<EObject> objectsList = new BasicEList<EObject>();	
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> map = reg.getExtensionToFactoryMap();
		map.put("xmi", new XMIResourceFactoryImpl());
		// Create a resource set to hold the resources.
		ResourceSet resourceSet = new ResourceSetImpl();
		// Create a new empty resource.
		//File file = new File(xmiPath);
		URI uri = URI.createFileURI(xmiPath);

		Resource resource = resourceSet.getResource(uri, true);
		//System.out.println(" roots count is :  "+resource.getContents().size());
		//EObject root = resource.getContents().get(0);

		TreeIterator<EObject> iterator = resource.getAllContents();
		while (iterator.hasNext()){
			Object obj = iterator.next();
			if (obj instanceof EObject)
				objectsList.add((EObject)obj);
			//System.out.println("The size of the resource is : "+objectsList.size());	
		}
		//System.out.println("The size of the resource is : "+objectsList.size());
		return objectsList;
	}

	public Node createWithIndexIfNotExists(EClass c, 
			IPersistenceService graphDB ){		
		if (getMetaIndex(graphDB).get(IPersistenceService.ID_META, c.getEPackage().getName()+"_"+c.getClassifierID()).getSingle() != null)
			return getMetaIndex(graphDB).get(IPersistenceService.ID_META, c.getEPackage().getName()+"_"+c.getClassifierID()).getSingle();
		Node n = graphDB.createNode();
		n.setProperty(IPersistenceService.ECLASS_NAME,c.getName() );
		n.setProperty(IPersistenceService.NS_URI, c.getEPackage().getNsURI());
		getMetaIndex(graphDB).putIfAbsent(n, IPersistenceService.ID_META, c.getEPackage().getName()+"_"+c.getClassifierID());
		return n;					
	}

	public Node createResourceNodeIfAbsent(IPersistenceService graphDB){
		if (getMetaIndex(graphDB).get(IPersistenceService.ID_META, IPersistenceService.RESOURCE_NODE).getSingle() != null)
			return getMetaIndex(graphDB).get(IPersistenceService.ID_META, IPersistenceService.RESOURCE_NODE).getSingle();
		Node n = graphDB.createNode();
		getMetaIndex(graphDB).putIfAbsent(n, IPersistenceService.ID_META, IPersistenceService.RESOURCE_NODE);
		return n;					
	}

	public Index<Node> getMetaIndex(IPersistenceService graphDB){
		
		IndexManager index = graphDB.index();
		return index.forNodes(IPersistenceService.META_ELEMENTS);
			
	}

	public Node createNodeFromEObject(EObject eObject,IPersistenceService graphDB) {
		Node node = graphDB.createNode();
		Node typeNode = createWithIndexIfNotExists(eObject.eClass(), null);
		node.createRelationshipTo(typeNode, IPersistenceService.INSTANCE_OF);
		if (isRoot(eObject))
			createResourceNodeIfAbsent(graphDB).createRelationshipTo(node, IPersistenceService.IS_ROOT);		
		return node;
	}
	
	private boolean isRoot(EObject eObject) {		
		if (eObject.eContainer() instanceof EPackage 
				|| eObject.eContainer() == null ) 
			return true;
		return false;
	}

	private Map<EObject, Long> persistNodes(IPersistenceService graphDB,		
							EList<EObject> objectsList){
		
		Map<EObject, Long>  eObjectsToNodes= new HashMap<EObject,Long>();
		Transaction tx = graphDB.beginTx();
		try{
			int i = 0;
			for (EObject eObject : objectsList){
				 Node	n = createNodeFromEObject(eObject, graphDB);
				 eObjectsToNodes.put(eObject, n.getId());
				 setupAttributes(eObject,n);
				i++; 
				if (i> ISerializer.DEFAULT_TRANSACTIONS_COUNT){			
					i=0;
					tx.success();
					tx.finish();
					tx= graphDB.beginTx();
				}
			}
		}finally{
			tx.success();
			tx.finish();
		}
		return eObjectsToNodes;
	}
	
	@SuppressWarnings("unchecked")
	private void setupAttributes(EObject eObject, Node n) {
		// TODO Auto-generated method stub
		EList<EAttribute> atrList= eObject.eClass().getEAllAttributes();
		java.util.Iterator<EAttribute> it = atrList.iterator();
		while(it.hasNext()){			
			EAttribute at =it.next();			
			if (eObject.eGet(at)!= null && !at.isMany()){
				if (at.getEType() instanceof EEnum)
					n.setProperty(at.getName(), eObject.eGet(at).toString());
				else if (isPrimitive(at.getName()))
					n.setProperty(at.getName(), eObject.eGet(at));
				else 
					n.setProperty(at.getName(), eObject.eGet(at).toString());
					}			
			else if (eObject.eGet(at) != null && at.isMany()){
				n.setProperty(at.getName(), ((EList<EDataType>) eObject.eGet(at)).toArray());}
			else if (!at.isMany()){ 
				if (at.getEType().getName().equals("Boolean"))
					n.setProperty(at.getName(), false );
				else if (at.getEType().getName().equals("String"))
					n.setProperty(at.getName(), "");
				else 
					n.setProperty(at.getName(), 0);
				}
			else {n.setProperty(at.getName(), new Object[1]);}
		}
	}
	
	private boolean isPrimitive(String str){
		if (str.equals("Boolean") 
				|| str.equals("Integer") 
				|| str.equals("Short") 
				|| str.equals("Long") 
				|| str.equals("Float")
				|| str.equals("String") 
				|| str.equals("Double"))
			return false;
		return true;
	}
}
