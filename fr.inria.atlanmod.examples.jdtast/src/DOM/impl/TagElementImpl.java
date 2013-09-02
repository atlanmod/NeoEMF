/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.ASTNode;
import DOM.DOMPackage;
import DOM.TagElement;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.TagElementImpl#getFragments <em>Fragments</em>}</li>
 *   <li>{@link DOM.impl.TagElementImpl#getTagName <em>Tag Name</em>}</li>
 *   <li>{@link DOM.impl.TagElementImpl#getNested <em>Nested</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagElementImpl extends ASTNodeImpl implements TagElement {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ASTNodeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TagElementImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.TAG_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ASTNode> getFragments() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataTagElement))
			data = new DataTagElement();
				
	   
		if (((DataTagElement)data).fragments == null) {
			((DataTagElement)data).fragments = new EObjectContainmentEList<ASTNode>(ASTNode.class, this, DOMPackage.TAG_ELEMENT__FRAGMENTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.TAG_ELEMENT__FRAGMENTS);			
		}
		return ((DataTagElement)data).fragments;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTagName() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataTagElement))
			data = new DataTagElement();
			
		if (((DataTagElement)data).tagName == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.TAG_ELEMENT__TAG_NAME, null, null));
		return ((DataTagElement)data).tagName;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTagName(String newTagName) {
	
		if (data==null) data =  new DataTagElement();
		
		else if (!(data instanceof DataTagElement)) data = new DataTagElement((DataASTNode)data);
	
		String oldTagName = ((DataTagElement)data).tagName;
		((DataTagElement)data).tagName = newTagName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.TAG_ELEMENT__TAG_NAME, oldTagName, ((DataTagElement)data).tagName));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getNested() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataTagElement))
			data = new DataTagElement();
			
		if (((DataTagElement)data).nested == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.TAG_ELEMENT__NESTED, null, null));
		return ((DataTagElement)data).nested;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNested(Boolean newNested) {
	
		if (data==null) data =  new DataTagElement();
		
		else if (!(data instanceof DataTagElement)) data = new DataTagElement((DataASTNode)data);
	
		Boolean oldNested = ((DataTagElement)data).nested;
		((DataTagElement)data).nested = newNested;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.TAG_ELEMENT__NESTED, oldNested, ((DataTagElement)data).nested));
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
			case DOMPackage.TAG_ELEMENT__FRAGMENTS:
				return ((InternalEList<?>)getFragments()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case DOMPackage.TAG_ELEMENT__FRAGMENTS:
				return getFragments();
			case DOMPackage.TAG_ELEMENT__TAG_NAME:
				return getTagName();
			case DOMPackage.TAG_ELEMENT__NESTED:
				return getNested();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY16
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DOMPackage.TAG_ELEMENT__FRAGMENTS:
				getFragments().clear();
				getFragments().addAll((Collection<? extends ASTNode>)newValue);
				return;
			case DOMPackage.TAG_ELEMENT__TAG_NAME:
				setTagName((String)newValue);
				return;
			case DOMPackage.TAG_ELEMENT__NESTED:
				setNested((Boolean)newValue);
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
			case DOMPackage.TAG_ELEMENT__FRAGMENTS:
				getFragments().clear();
				return;
			case DOMPackage.TAG_ELEMENT__TAG_NAME:
				setTagName(DataTagElement.TAG_NAME_EDEFAULT);
				return;
			case DOMPackage.TAG_ELEMENT__NESTED:
				setNested(DataTagElement.NESTED_EDEFAULT);
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
			case DOMPackage.TAG_ELEMENT__FRAGMENTS:
				return getFragments() != null && !getFragments().isEmpty();
			case DOMPackage.TAG_ELEMENT__TAG_NAME:
				return DataTagElement.TAG_NAME_EDEFAULT == null ? getTagName() != null : !DataTagElement.TAG_NAME_EDEFAULT.equals(getTagName());
			case DOMPackage.TAG_ELEMENT__NESTED:
				return DataTagElement.NESTED_EDEFAULT == null ? getNested() != null : !DataTagElement.NESTED_EDEFAULT.equals(getNested());
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
		if (data != null) result.append(((DataTagElement)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataTagElement extends DataASTNode {


	/**
	 * The cached value of the '{@link #getFragments() <em>Fragments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFragments()
	 * @generated
	 * @ordered
	 */
	protected EList<ASTNode> fragments;

	/**
	 * The default value of the '{@link #getTagName() <em>Tag Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagName()
	 * @generated
	 * @ordered
	 */
	protected static final String TAG_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTagName() <em>Tag Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagName()
	 * @generated
	 * @ordered
	 */
	protected String tagName = TAG_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNested() <em>Nested</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNested()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean NESTED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNested() <em>Nested</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNested()
	 * @generated
	 * @ordered
	 */
	protected Boolean nested = NESTED_EDEFAULT;

	/**
	 *Constructor of DataTagElement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTagElement() {
		super();
	}
	
		
	/**
	 *Constructor of DataTagElement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	public DataTagElement(DataASTNode data) {
		super();		
		
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (tagName: ");
		result.append(tagName);
		result.append(", nested: ");
		result.append(nested);
		result.append(')');
		return result.toString();
	}
		
}
} //TagElementImpl
