package fr.inria.atlanmod.neo4emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;

public class NeoEObjectContainmentWithInverseEList<E> extends
		EObjectContainmentWithInverseEList<E> {

	private static final long serialVersionUID = 1L;

	public NeoEObjectContainmentWithInverseEList(Class<?> dataClass,
			InternalEObject owner, int featureID, int inverseFeatureID) {
		super(dataClass, owner, featureID, inverseFeatureID);
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
		return true;
	}
	
}
