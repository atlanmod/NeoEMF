package fr.inria.atlanmod.kyanos.datastore;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import fr.inria.atlanmod.kyanos.datastore.exceptions.InvalidDataStoreException;

public interface PersistenceBackend {

	/**
	 * Starts the underlying data store with the given <code>options</code>
	 * 
	 * @param options
	 * @throws InvalidDataStoreException
	 */
	public void start(Map<?, ?> options) throws InvalidDataStoreException;
	
	/**
	 * Returns whether the underlying data store has been started or not.
	 * 
	 * @return
	 */
	public boolean isStarted();

	/**
	 * Cleanly stops the underlying data store.
	 */
	public void stop();
	
	/**
	 * Saves the modifications of the owned {@link EObject}s in the persistence
	 * back-end
	 */
	public void save();
}

