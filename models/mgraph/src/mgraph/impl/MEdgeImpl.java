/**
 */
package mgraph.impl;

import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

import mgraph.MEdge;
import mgraph.MGraph;
import mgraph.MNode;
import mgraph.MgraphPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MEdge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link mgraph.impl.MEdgeImpl#getName <em>Name</em>}</li>
 *   <li>{@link mgraph.impl.MEdgeImpl#getInComing <em>In Coming</em>}</li>
 *   <li>{@link mgraph.impl.MEdgeImpl#getOutGoing <em>Out Going</em>}</li>
 *   <li>{@link mgraph.impl.MEdgeImpl#getGraph <em>Graph</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MEdgeImpl extends Neo4emfObject implements MEdge {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInComing() <em>In Coming</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInComing()
	 * @generated
	 * @ordered
	 */
	protected MNode inComing;

	/**
	 * The cached value of the '{@link #getOutGoing() <em>Out Going</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutGoing()
	 * @generated
	 * @ordered
	 */
	protected MNode outGoing;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MEdgeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MgraphPackage.Literals.MEDGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MgraphPackage.MEDGE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MNode getInComing() {
		if (inComing != null && ((EObject)inComing).eIsProxy()) {
			InternalEObject oldInComing = (InternalEObject)inComing;
			inComing = (MNode)eResolveProxy(oldInComing);
			if (inComing != oldInComing) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MgraphPackage.MEDGE__IN_COMING, oldInComing, inComing));
			}
		}
		return inComing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MNode basicGetInComing() {
		return inComing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInComing(MNode newInComing, NotificationChain msgs) {
		MNode oldInComing = inComing;
		inComing = newInComing;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MgraphPackage.MEDGE__IN_COMING, oldInComing, newInComing);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInComing(MNode newInComing) {
		if (newInComing != inComing) {
			NotificationChain msgs = null;
			if (inComing != null)
				msgs = ((InternalEObject)inComing).eInverseRemove(this, MgraphPackage.MNODE__TO, MNode.class, msgs);
			if (newInComing != null)
				msgs = ((InternalEObject)newInComing).eInverseAdd(this, MgraphPackage.MNODE__TO, MNode.class, msgs);
			msgs = basicSetInComing(newInComing, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MgraphPackage.MEDGE__IN_COMING, newInComing, newInComing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MNode getOutGoing() {
		if (outGoing != null && ((EObject)outGoing).eIsProxy()) {
			InternalEObject oldOutGoing = (InternalEObject)outGoing;
			outGoing = (MNode)eResolveProxy(oldOutGoing);
			if (outGoing != oldOutGoing) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MgraphPackage.MEDGE__OUT_GOING, oldOutGoing, outGoing));
			}
		}
		return outGoing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MNode basicGetOutGoing() {
		return outGoing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutGoing(MNode newOutGoing, NotificationChain msgs) {
		MNode oldOutGoing = outGoing;
		outGoing = newOutGoing;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MgraphPackage.MEDGE__OUT_GOING, oldOutGoing, newOutGoing);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutGoing(MNode newOutGoing) {
		if (newOutGoing != outGoing) {
			NotificationChain msgs = null;
			if (outGoing != null)
				msgs = ((InternalEObject)outGoing).eInverseRemove(this, MgraphPackage.MNODE__FROM, MNode.class, msgs);
			if (newOutGoing != null)
				msgs = ((InternalEObject)newOutGoing).eInverseAdd(this, MgraphPackage.MNODE__FROM, MNode.class, msgs);
			msgs = basicSetOutGoing(newOutGoing, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MgraphPackage.MEDGE__OUT_GOING, newOutGoing, newOutGoing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MGraph getGraph() {
		if (eContainerFeatureID() != MgraphPackage.MEDGE__GRAPH) return null;
		return (MGraph)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGraph(MGraph newGraph, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGraph, MgraphPackage.MEDGE__GRAPH, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGraph(MGraph newGraph) {
		if (newGraph != eInternalContainer() || (eContainerFeatureID() != MgraphPackage.MEDGE__GRAPH && newGraph != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newGraph))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGraph != null)
				msgs = ((InternalEObject)newGraph).eInverseAdd(this, MgraphPackage.MGRAPH__EDGES, MGraph.class, msgs);
			msgs = basicSetGraph(newGraph, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MgraphPackage.MEDGE__GRAPH, newGraph, newGraph));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MgraphPackage.MEDGE__IN_COMING:
				if (inComing != null)
					msgs = ((InternalEObject)inComing).eInverseRemove(this, MgraphPackage.MNODE__TO, MNode.class, msgs);
				return basicSetInComing((MNode)otherEnd, msgs);
			case MgraphPackage.MEDGE__OUT_GOING:
				if (outGoing != null)
					msgs = ((InternalEObject)outGoing).eInverseRemove(this, MgraphPackage.MNODE__FROM, MNode.class, msgs);
				return basicSetOutGoing((MNode)otherEnd, msgs);
			case MgraphPackage.MEDGE__GRAPH:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGraph((MGraph)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MgraphPackage.MEDGE__IN_COMING:
				return basicSetInComing(null, msgs);
			case MgraphPackage.MEDGE__OUT_GOING:
				return basicSetOutGoing(null, msgs);
			case MgraphPackage.MEDGE__GRAPH:
				return basicSetGraph(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case MgraphPackage.MEDGE__GRAPH:
				return eInternalContainer().eInverseRemove(this, MgraphPackage.MGRAPH__EDGES, MGraph.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MgraphPackage.MEDGE__NAME:
				return getName();
			case MgraphPackage.MEDGE__IN_COMING:
				if (resolve) return getInComing();
				return basicGetInComing();
			case MgraphPackage.MEDGE__OUT_GOING:
				if (resolve) return getOutGoing();
				return basicGetOutGoing();
			case MgraphPackage.MEDGE__GRAPH:
				return getGraph();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MgraphPackage.MEDGE__NAME:
				setName((String)newValue);
				return;
			case MgraphPackage.MEDGE__IN_COMING:
				setInComing((MNode)newValue);
				return;
			case MgraphPackage.MEDGE__OUT_GOING:
				setOutGoing((MNode)newValue);
				return;
			case MgraphPackage.MEDGE__GRAPH:
				setGraph((MGraph)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MgraphPackage.MEDGE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MgraphPackage.MEDGE__IN_COMING:
				setInComing((MNode)null);
				return;
			case MgraphPackage.MEDGE__OUT_GOING:
				setOutGoing((MNode)null);
				return;
			case MgraphPackage.MEDGE__GRAPH:
				setGraph((MGraph)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MgraphPackage.MEDGE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MgraphPackage.MEDGE__IN_COMING:
				return inComing != null;
			case MgraphPackage.MEDGE__OUT_GOING:
				return outGoing != null;
			case MgraphPackage.MEDGE__GRAPH:
				return getGraph() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //MEdgeImpl
