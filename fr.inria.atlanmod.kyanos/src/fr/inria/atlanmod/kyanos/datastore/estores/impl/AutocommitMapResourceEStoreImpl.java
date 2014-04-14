package fr.inria.atlanmod.kyanos.datastore.estores.impl;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;
import org.mapdb.DB;

public class AutocommitMapResourceEStoreImpl extends DirectWriteMapResourceEStoreImpl {

	/**
	 * Default number of allowed modifications (100000) between commits on the
	 * underlying db
	 */
	protected static final int OPS_BETWEEN_COMMITS_DEFAULT = 100000;

	/**
	 * Number of allowed modifications between commits on the underlying db
	 * for this {@link AutocommitMapResourceEStoreImpl}
	 */
	protected final int OPS_BETWEEN_COMMITS;

	/**
	 * Current number of modifications modulo {@link #OPS_BETWEEN_COMMITS}
	 */
	protected static int opCount = 0;

	/**
	 * Constructor for this {@link DB}-based {@link EStore}. Allows to
	 * specify the number of allowed modification on the underlying db before
	 * calling the {@link DB#commit()} method automatically.
	 * 
	 * @param resource
	 * @param db
	 * @param opsBetweenCommits
	 */
	public AutocommitMapResourceEStoreImpl(Resource.Internal resource, DB db, int opsBetweenCommits) {
		super(resource, db);
		this.OPS_BETWEEN_COMMITS = opsBetweenCommits;
	}

	/**
	 * Constructor for this {@link DB}-based {@link EStore}. Allows to
	 * make {@link #OPS_BETWEEN_COMMITS_DEFAULT} modifications on the underlying
	 * db before calling the {@link DB#commit()} method
	 * automatically.
	 * 
	 * @param resource
	 * @param db
	 */
	public AutocommitMapResourceEStoreImpl(Resource.Internal resource, DB db) {
		this(resource, db, OPS_BETWEEN_COMMITS_DEFAULT);
	}

	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		super.add(object, feature, index, value);
		opCount = (opCount + 1) % OPS_BETWEEN_COMMITS;
		if (opCount == 0) {
			db.commit();
		}
	}

	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Object returnValue = super.set(object, feature, index, value);
		opCount = (opCount + 1) % OPS_BETWEEN_COMMITS;
		if (opCount == 0) {
			db.commit();
		}
		return returnValue;
	}

	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		Object returnValue = super.move(object, feature, targetIndex, sourceIndex);
		opCount = (opCount + 1) % OPS_BETWEEN_COMMITS;
		if (opCount == 0) {
			db.commit();
		}
		return returnValue;
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		Object returnValue = super.remove(object, feature, index);
		opCount = (opCount + 1) % OPS_BETWEEN_COMMITS;
		if (opCount == 0) {
			db.commit();
		}
		return returnValue;
	}

	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		super.unset(object, feature);
		opCount = (opCount + 1) % OPS_BETWEEN_COMMITS;
		if (opCount == 0) {
			db.commit();
		}
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		super.clear(object, feature);
		opCount = (opCount + 1) % OPS_BETWEEN_COMMITS;
		if (opCount == 0) {
			db.commit();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		db.commit();
	}
}
