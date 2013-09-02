/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.SimpleName;
import DOM.Type;
import DOM.TypeParameter;

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
 * An implementation of the model object '<em><b>Type Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.TypeParameterImpl#getName <em>Name</em>}</li>
 *   <li>{@link DOM.impl.TypeParameterImpl#getTypeBounds <em>Type Bounds</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeParameterImpl extends ASTNodeImpl implements TypeParameter {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ASTNodeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeParameterImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.TYPE_PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleName getName() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataTypeParameter))
			data = new DataTypeParameter();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.TYPE_PARAMETER__NAME, null, null));
		return ((DataTypeParameter)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(SimpleName newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataTypeParameter();
	
		SimpleName oldName = ((DataTypeParameter)data).name;
		((DataTypeParameter)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.TYPE_PARAMETER__NAME, oldName, newName);
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
	public void setName(SimpleName newName) {
	
		if (data==null) data =  new DataTypeParameter();
		
		else if (!(data instanceof DataTypeParameter)) data = new DataTypeParameter((DataASTNode)data);
	
		if (newName != ((DataTypeParameter)data).name) {
			NotificationChain msgs = null;
			if (((DataTypeParameter)data).name != null)
				msgs = ((InternalEObject) ((DataTypeParameter)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TYPE_PARAMETER__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TYPE_PARAMETER__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.TYPE_PARAMETER__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getTypeBounds() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataTypeParameter))
			data = new DataTypeParameter();
				
	   
		if (((DataTypeParameter)data).typeBounds == null) {
			((DataTypeParameter)data).typeBounds = new EObjectContainmentEList<Type>(Type.class, this, DOMPackage.TYPE_PARAMETER__TYPE_BOUNDS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.TYPE_PARAMETER__TYPE_BOUNDS);			
		}
		return ((DataTypeParameter)data).typeBounds;	
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
			case DOMPackage.TYPE_PARAMETER__NAME:
				return basicSetName(null, msgs);
			case DOMPackage.TYPE_PARAMETER__TYPE_BOUNDS:
				return ((InternalEList<?>)getTypeBounds()).basicRemove(otherEnd, msgs);
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
			case DOMPackage.TYPE_PARAMETER__NAME:
				return getName();
			case DOMPackage.TYPE_PARAMETER__TYPE_BOUNDS:
				return getTypeBounds();
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
			case DOMPackage.TYPE_PARAMETER__NAME:
				setName((SimpleName)newValue);
				return;
			case DOMPackage.TYPE_PARAMETER__TYPE_BOUNDS:
				getTypeBounds().clear();
				getTypeBounds().addAll((Collection<? extends Type>)newValue);
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
			case DOMPackage.TYPE_PARAMETER__NAME:
				setName((SimpleName)null);
				return;
			case DOMPackage.TYPE_PARAMETER__TYPE_BOUNDS:
				getTypeBounds().clear();
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
			case DOMPackage.TYPE_PARAMETER__NAME:
				return getName() != null;
			case DOMPackage.TYPE_PARAMETER__TYPE_BOUNDS:
				return getTypeBounds() != null && !getTypeBounds().isEmpty();
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataTypeParameter extends DataASTNode {


	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected SimpleName name;

	/**
	 * The cached value of the '{@link #getTypeBounds() <em>Type Bounds</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeBounds()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> typeBounds;

	/**
	 *Constructor of DataTypeParameter
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTypeParameter() {
		super();
	}
	
		
	/**
	 *Constructor of DataTypeParameter
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	public DataTypeParameter(DataASTNode data) {
		super();		
		
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(')');
		return result.toString();
	}
		
}
} //TypeParameterImpl
