package fr.inria.atlanmod.neo4emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;

public class NeoEObjectContainmentEList<E> extends EObjectContainmentEList<E> {
	
	private static final long serialVersionUID = 1L;
	
	public NeoEObjectContainmentEList(Class<?> dataClass, InternalEObject owner, int featureID) {
		super(dataClass,owner,featureID);
	}
	
	@Override
	public boolean add(E object) {
		INeo4emfObject ownerObject = (INeo4emfObject)owner;
		boolean added = super.add(object);
		if(added && !ownerObject.isLoadingOnDemand() && ownerObject.isLoaded()) {
			ownerObject.addChangelogEntry(object, featureID);
		}
		return added;
	}
	
	@Override
	public E remove(int index) {
		E removedObject = super.remove(index);
		INeo4emfObject ownerObject = (INeo4emfObject)owner;
		if(removedObject != null && !ownerObject.isLoadingOnDemand() && ownerObject.isLoaded()) {
			ownerObject.addChangelogRemoveEntry((EObject)removedObject, featureID);
		}
		return removedObject;
	}
	
	@Override
	public boolean remove(Object object) {
		return super.remove(object);
	}
	
	@Override
	protected boolean useEquals() {
		// TODO check if its still needed (may impact perfs)
		return true;
	}
}
