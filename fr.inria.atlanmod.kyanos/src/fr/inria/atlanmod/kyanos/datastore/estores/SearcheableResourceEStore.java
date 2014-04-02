package fr.inria.atlanmod.kyanos.datastore.estores;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * This interface extends the {@link EStore} interface and allows to establish a
 * mapping between {@link Resource}s and {@link EStore}s
 * 
 * @author agomez
 * 
 */
public interface SearcheableResourceEStore extends InternalEObject.EStore {

	/**
	 * Returns the {@link Resource} to which this {@link EStore} is associated
	 * 
	 * @return
	 */
	public Resource getResource();

	/**
	 * Returns the resolved {@link EObject} identified by the given
	 * <code>id</code> or <code>null</code> if no {@link EObject} can be
	 * resolved.
	 * 
	 * @param id
	 * @return
	 */
	public EObject getEObject(String id);
	
}
