/**
 *
 * $Id$
 */
package mgraph.impl;

import java.lang.ref.SoftReference;

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

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

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
	 * The cached value of the data structure {@link DataMEdge <em>data</em> } 
	 * @generated
	 */
	 	protected DataMEdge data;
	 
	 
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
	 
	protected DataMEdge getData(){
		if(data == null || !(data instanceof DataMEdge)) {
			data = new DataMEdge();
			if(isLoaded()) {
				((INeo4emfResource)this.eResource()).fetchAttributes(this);
			}
		}
		return (DataMEdge)data;
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
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {	
	  		
		if ( isLoaded()) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, MgraphPackage.MEDGE__NAME, null, null));
		return getData().name;	
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setName(String newName) {
	
		
		String oldName = getData().name;
		getData().name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			MgraphPackage.MEDGE__NAME,
			oldName, getData().name));
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MNode getInComing() {	
		SoftReference<MNode> inComing = getData().inComing;
		if(inComing == null) {
			getData().inComing = new SoftReference<MNode>(null,garbagedData);
			if(isLoaded()) {
				((INeo4emfResource)this.eResource()).getOnDemand(this, MgraphPackage.MEDGE__IN_COMING);
				eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, MgraphPackage.MEDGE__IN_COMING, null, null));
			}
			return getData().inComing.get();
		}
		else {
			MNode inComingNode = inComing.get();
			if(inComing.isEnqueued()) {
				assert isLoaded() : "The SoftReference has been enqueued but the EObject ("+toString()+") hasn't been saved into the database";
				((INeo4emfResource)this.eResource()).getOnDemand(this, MgraphPackage.MEDGE__IN_COMING);
				eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, MgraphPackage.MEDGE__IN_COMING, null, null));
				return inComing.get();
			}
			else {
				if(inComingNode == null) {
					if(isLoaded()) {
						((INeo4emfResource)this.eResource()).getOnDemand(this, MgraphPackage.MEDGE__IN_COMING);
						eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, MgraphPackage.MEDGE__IN_COMING, null, null));
					}
					return inComing.get();
				}
				return inComingNode;
			}
		}
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX8
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MNode basicGetInComing() {
		return data != null && data.inComing != null ? getData().inComing.get() : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInComing(MNode newInComing, NotificationChain msgs) {
		MNode oldInComing = null;
		if(getData().inComing != null) {
			oldInComing = getData().inComing.get();
		}
		getData().inComing = new SoftReference<MNode>(newInComing,garbagedData);
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MgraphPackage.MEDGE__IN_COMING, oldInComing, newInComing);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setInComing(MNode newInComing) {
	
		
		if (newInComing != getData().inComing) {
			NotificationChain msgs = null;
			if (getData().inComing != null)
				msgs = ((InternalEObject) getData().inComing).eInverseRemove(this, MgraphPackage.MNODE__TO, MNode.class, msgs);
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
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MNode getOutGoing() {	
		SoftReference<MNode> outGoing = getData().outGoing;
		if(outGoing == null) {
			getData().outGoing = new SoftReference<MNode>(null,garbagedData);
			if(isLoaded()) {
				((INeo4emfResource)this.eResource()).getOnDemand(this, MgraphPackage.MEDGE__OUT_GOING);
				eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, MgraphPackage.MEDGE__OUT_GOING, null, null));
			}
			return getData().outGoing.get();
		}
		else {
			MNode outGoingNode = outGoing.get();
			if(outGoing.isEnqueued()) {
				assert isLoaded() : "The SoftReference has been enqueued but the EObject ("+toString()+") hasn't been saved into the database";
				((INeo4emfResource)this.eResource()).getOnDemand(this, MgraphPackage.MEDGE__OUT_GOING);
				eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, MgraphPackage.MEDGE__OUT_GOING, null, null));
				return outGoing.get();
			}
			else {
				if(outGoingNode == null) {
					if(isLoaded()) {
						((INeo4emfResource)this.eResource()).getOnDemand(this, MgraphPackage.MEDGE__OUT_GOING);
						eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, MgraphPackage.MEDGE__OUT_GOING, null, null));
					}
					return outGoing.get();
				}
				return outGoingNode;
			}
		}
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX8
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MNode basicGetOutGoing() {
		return data != null && data.outGoing != null ? getData().outGoing.get() : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutGoing(MNode newOutGoing, NotificationChain msgs) {
		MNode oldOutGoing = null;
		if(getData().outGoing != null) {
			oldOutGoing = getData().outGoing.get();
		}
		getData().outGoing = new SoftReference<MNode>(newOutGoing,garbagedData);
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MgraphPackage.MEDGE__OUT_GOING, oldOutGoing, newOutGoing);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setOutGoing(MNode newOutGoing) {
	
		
		if (newOutGoing != getData().outGoing) {
			NotificationChain msgs = null;
			if (getData().outGoing != null)
				msgs = ((InternalEObject) getData().outGoing).eInverseRemove(this, MgraphPackage.MNODE__FROM, MNode.class, msgs);
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
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MGraph getGraph() {	
		if (isLoaded() && eContainer() == null) {
			MGraph graph = (MGraph) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, MgraphPackage.MEDGE__GRAPH);
			basicSetGraph(graph,null);}
		return (MGraph)eContainer();	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGraph(MGraph newGraph, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newGraph, MgraphPackage.MEDGE__GRAPH, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
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
	 *YY12
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MgraphPackage.MEDGE__IN_COMING:
				if (getData().inComing != null)
					msgs = ((InternalEObject)getData().inComing).eInverseRemove(this, MgraphPackage.MNODE__TO, MNode.class, msgs);
				return basicSetInComing((MNode)otherEnd, msgs);
			case MgraphPackage.MEDGE__OUT_GOING:
				if (getData().outGoing != null)
					msgs = ((InternalEObject)getData().outGoing).eInverseRemove(this, MgraphPackage.MNODE__FROM, MNode.class, msgs);
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
	 *YY13
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
	 *YY14
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
	 *YY15
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
	 *YY16
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
	 *YY17
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MgraphPackage.MEDGE__NAME:
				setName(DataMEdge.NAME_EDEFAULT);
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
	 *YY18
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MgraphPackage.MEDGE__NAME:
				return DataMEdge.NAME_EDEFAULT == null ? getName() != null : !DataMEdge.NAME_EDEFAULT.equals(getName());
			case MgraphPackage.MEDGE__IN_COMING:
				return getInComing() != null;
			case MgraphPackage.MEDGE__OUT_GOING:
				return getOutGoing() != null;
			case MgraphPackage.MEDGE__GRAPH:
				return getGraph() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY27
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		if (data != null) result.append(data.toString());
		
		return result.toString();
		}




// data Class generation 
protected static  class DataMEdge {


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
	protected SoftReference<MNode> inComing;

	/**
	 * The cached value of the '{@link #getOutGoing() <em>Out Going</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutGoing()
	 * @generated
	 * @ordered
	 */
	protected SoftReference<MNode> outGoing;

	/**
	 *Constructor of DataMEdge
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMEdge() {
		
	}
	
		
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}
		
}//end data class
} //MEdgeImpl
