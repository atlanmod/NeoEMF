package fr.inria.atlanmod.neo4emf.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeoFactory;
import fr.inria.atlanmod.neo4emf.resourceUtil.ChangeAdapterImpl;

public class NeoFactory extends EFactoryImpl implements INeoFactory{
	
	public NeoFactory(){
		super();
	}

	public static INeoFactory init() {
		if (eINSTANCE != null)
			return eINSTANCE;
		else 
			return new NeoFactory();
		
	}
	
	@Override
	
	protected EObject basicCreate(EClass eClass){
		INeo4emfObject eObject = new Neo4emfObject(eClass);
//		ChangeLog.getInstance().add(new NewObject(eObject));
		eObject.eAdapters().add(new ChangeAdapterImpl());
		return eObject;
		
	}
	
}
