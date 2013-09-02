/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.ArrayCreation;
import DOM.ArrayInitializer;
import DOM.ArrayType;
import DOM.DOMPackage;
import DOM.Expression;

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
 * An implementation of the model object '<em><b>Array Creation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.ArrayCreationImpl#getDimensions <em>Dimensions</em>}</li>
 *   <li>{@link DOM.impl.ArrayCreationImpl#getInitializer <em>Initializer</em>}</li>
 *   <li>{@link DOM.impl.ArrayCreationImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArrayCreationImpl extends ExpressionImpl implements ArrayCreation {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayCreationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ARRAY_CREATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getDimensions() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataArrayCreation))
			data = new DataArrayCreation();
				
	   
		if (((DataArrayCreation)data).dimensions == null) {
			((DataArrayCreation)data).dimensions = new EObjectContainmentEList<Expression>(Expression.class, this, DOMPackage.ARRAY_CREATION__DIMENSIONS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.ARRAY_CREATION__DIMENSIONS);			
		}
		return ((DataArrayCreation)data).dimensions;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayInitializer getInitializer() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataArrayCreation))
			data = new DataArrayCreation();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ARRAY_CREATION__INITIALIZER, null, null));
		return ((DataArrayCreation)data).initializer;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInitializer(ArrayInitializer newInitializer, NotificationChain msgs) {
	
		if (data==null) data =  new DataArrayCreation();
	
		ArrayInitializer oldInitializer = ((DataArrayCreation)data).initializer;
		((DataArrayCreation)data).initializer = newInitializer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ARRAY_CREATION__INITIALIZER, oldInitializer, newInitializer);
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
	public void setInitializer(ArrayInitializer newInitializer) {
	
		if (data==null) data =  new DataArrayCreation();
		
		else if (!(data instanceof DataArrayCreation)) data = new DataArrayCreation((DataExpression)data);
	
		if (newInitializer != ((DataArrayCreation)data).initializer) {
			NotificationChain msgs = null;
			if (((DataArrayCreation)data).initializer != null)
				msgs = ((InternalEObject) ((DataArrayCreation)data).initializer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ARRAY_CREATION__INITIALIZER, null, msgs);
			if (newInitializer != null)
				msgs = ((InternalEObject)newInitializer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ARRAY_CREATION__INITIALIZER, null, msgs);
			msgs = basicSetInitializer(newInitializer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ARRAY_CREATION__INITIALIZER, newInitializer, newInitializer));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayType getType() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataArrayCreation))
			data = new DataArrayCreation();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ARRAY_CREATION__TYPE, null, null));
		return ((DataArrayCreation)data).type;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(ArrayType newType, NotificationChain msgs) {
	
		if (data==null) data =  new DataArrayCreation();
	
		ArrayType oldType = ((DataArrayCreation)data).type;
		((DataArrayCreation)data).type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ARRAY_CREATION__TYPE, oldType, newType);
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
	public void setType(ArrayType newType) {
	
		if (data==null) data =  new DataArrayCreation();
		
		else if (!(data instanceof DataArrayCreation)) data = new DataArrayCreation((DataExpression)data);
	
		if (newType != ((DataArrayCreation)data).type) {
			NotificationChain msgs = null;
			if (((DataArrayCreation)data).type != null)
				msgs = ((InternalEObject) ((DataArrayCreation)data).type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ARRAY_CREATION__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ARRAY_CREATION__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ARRAY_CREATION__TYPE, newType, newType));
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
			case DOMPackage.ARRAY_CREATION__DIMENSIONS:
				return ((InternalEList<?>)getDimensions()).basicRemove(otherEnd, msgs);
			case DOMPackage.ARRAY_CREATION__INITIALIZER:
				return basicSetInitializer(null, msgs);
			case DOMPackage.ARRAY_CREATION__TYPE:
				return basicSetType(null, msgs);
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
			case DOMPackage.ARRAY_CREATION__DIMENSIONS:
				return getDimensions();
			case DOMPackage.ARRAY_CREATION__INITIALIZER:
				return getInitializer();
			case DOMPackage.ARRAY_CREATION__TYPE:
				return getType();
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
			case DOMPackage.ARRAY_CREATION__DIMENSIONS:
				getDimensions().clear();
				getDimensions().addAll((Collection<? extends Expression>)newValue);
				return;
			case DOMPackage.ARRAY_CREATION__INITIALIZER:
				setInitializer((ArrayInitializer)newValue);
				return;
			case DOMPackage.ARRAY_CREATION__TYPE:
				setType((ArrayType)newValue);
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
			case DOMPackage.ARRAY_CREATION__DIMENSIONS:
				getDimensions().clear();
				return;
			case DOMPackage.ARRAY_CREATION__INITIALIZER:
				setInitializer((ArrayInitializer)null);
				return;
			case DOMPackage.ARRAY_CREATION__TYPE:
				setType((ArrayType)null);
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
			case DOMPackage.ARRAY_CREATION__DIMENSIONS:
				return getDimensions() != null && !getDimensions().isEmpty();
			case DOMPackage.ARRAY_CREATION__INITIALIZER:
				return getInitializer() != null;
			case DOMPackage.ARRAY_CREATION__TYPE:
				return getType() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataArrayCreation extends DataExpression {


	/**
	 * The cached value of the '{@link #getDimensions() <em>Dimensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimensions()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> dimensions;

	/**
	 * The cached value of the '{@link #getInitializer() <em>Initializer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializer()
	 * @generated
	 * @ordered
	 */
	protected ArrayInitializer initializer;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ArrayType type;

	/**
	 *Constructor of DataArrayCreation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataArrayCreation() {
		super();
	}
	
		
	/**
	 *Constructor of DataArrayCreation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataArrayCreation(DataExpression data) {
		super();		
		
		resolveBoxing = data.resolveBoxing;
				
		resolveUnboxing = data.resolveUnboxing;
				
		typeBinding = data.typeBinding;
				
				
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
} //ArrayCreationImpl
