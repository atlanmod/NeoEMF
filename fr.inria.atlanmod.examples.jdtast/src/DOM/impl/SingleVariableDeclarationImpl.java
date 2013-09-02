/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.ExtendedModifier;
import DOM.SingleVariableDeclaration;
import DOM.Type;

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
 * An implementation of the model object '<em><b>Single Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.SingleVariableDeclarationImpl#getType <em>Type</em>}</li>
 *   <li>{@link DOM.impl.SingleVariableDeclarationImpl#getVarargs <em>Varargs</em>}</li>
 *   <li>{@link DOM.impl.SingleVariableDeclarationImpl#getModifiers <em>Modifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SingleVariableDeclarationImpl extends VariableDeclarationImpl implements SingleVariableDeclaration {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//VariableDeclarationImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SingleVariableDeclarationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.SINGLE_VARIABLE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getType() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataSingleVariableDeclaration))
			data = new DataSingleVariableDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.SINGLE_VARIABLE_DECLARATION__TYPE, null, null));
		return ((DataSingleVariableDeclaration)data).type;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(Type newType, NotificationChain msgs) {
	
		if (data==null) data =  new DataSingleVariableDeclaration();
	
		Type oldType = ((DataSingleVariableDeclaration)data).type;
		((DataSingleVariableDeclaration)data).type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.SINGLE_VARIABLE_DECLARATION__TYPE, oldType, newType);
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
	public void setType(Type newType) {
	
		if (data==null) data =  new DataSingleVariableDeclaration();
		
		else if (!(data instanceof DataSingleVariableDeclaration)) data = new DataSingleVariableDeclaration((DataVariableDeclaration)data);
	
		if (newType != ((DataSingleVariableDeclaration)data).type) {
			NotificationChain msgs = null;
			if (((DataSingleVariableDeclaration)data).type != null)
				msgs = ((InternalEObject) ((DataSingleVariableDeclaration)data).type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SINGLE_VARIABLE_DECLARATION__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SINGLE_VARIABLE_DECLARATION__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.SINGLE_VARIABLE_DECLARATION__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getVarargs() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataSingleVariableDeclaration))
			data = new DataSingleVariableDeclaration();
			
		if (((DataSingleVariableDeclaration)data).varargs == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.SINGLE_VARIABLE_DECLARATION__VARARGS, null, null));
		return ((DataSingleVariableDeclaration)data).varargs;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVarargs(Boolean newVarargs) {
	
		if (data==null) data =  new DataSingleVariableDeclaration();
		
		else if (!(data instanceof DataSingleVariableDeclaration)) data = new DataSingleVariableDeclaration((DataVariableDeclaration)data);
	
		Boolean oldVarargs = ((DataSingleVariableDeclaration)data).varargs;
		((DataSingleVariableDeclaration)data).varargs = newVarargs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.SINGLE_VARIABLE_DECLARATION__VARARGS, oldVarargs, ((DataSingleVariableDeclaration)data).varargs));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExtendedModifier> getModifiers() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataSingleVariableDeclaration))
			data = new DataSingleVariableDeclaration();
				
	   
		if (((DataSingleVariableDeclaration)data).modifiers == null) {
			((DataSingleVariableDeclaration)data).modifiers = new EObjectContainmentEList<ExtendedModifier>(ExtendedModifier.class, this, DOMPackage.SINGLE_VARIABLE_DECLARATION__MODIFIERS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.SINGLE_VARIABLE_DECLARATION__MODIFIERS);			
		}
		return ((DataSingleVariableDeclaration)data).modifiers;	
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
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__TYPE:
				return basicSetType(null, msgs);
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__MODIFIERS:
				return ((InternalEList<?>)getModifiers()).basicRemove(otherEnd, msgs);
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
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__TYPE:
				return getType();
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__VARARGS:
				return getVarargs();
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__MODIFIERS:
				return getModifiers();
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
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__TYPE:
				setType((Type)newValue);
				return;
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__VARARGS:
				setVarargs((Boolean)newValue);
				return;
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__MODIFIERS:
				getModifiers().clear();
				getModifiers().addAll((Collection<? extends ExtendedModifier>)newValue);
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
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__TYPE:
				setType((Type)null);
				return;
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__VARARGS:
				setVarargs(DataSingleVariableDeclaration.VARARGS_EDEFAULT);
				return;
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__MODIFIERS:
				getModifiers().clear();
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
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__TYPE:
				return getType() != null;
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__VARARGS:
				return DataSingleVariableDeclaration.VARARGS_EDEFAULT == null ? getVarargs() != null : !DataSingleVariableDeclaration.VARARGS_EDEFAULT.equals(getVarargs());
			case DOMPackage.SINGLE_VARIABLE_DECLARATION__MODIFIERS:
				return getModifiers() != null && !getModifiers().isEmpty();
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
		if (data != null) result.append(((DataSingleVariableDeclaration)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataSingleVariableDeclaration extends DataVariableDeclaration {


	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Type type;

	/**
	 * The default value of the '{@link #getVarargs() <em>Varargs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVarargs()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean VARARGS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVarargs() <em>Varargs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVarargs()
	 * @generated
	 * @ordered
	 */
	protected Boolean varargs = VARARGS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getModifiers() <em>Modifiers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifiers()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtendedModifier> modifiers;

	/**
	 *Constructor of DataSingleVariableDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSingleVariableDeclaration() {
		super();
	}
	
		
	/**
	 *Constructor of DataSingleVariableDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link VariableDeclaration }
	 * @generated
	 */
	public DataSingleVariableDeclaration(DataVariableDeclaration data) {
		super();		
		
		extraDimensions = data.extraDimensions;
				
		initializer = data.initializer;
				
		name = data.name;
				
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (varargs: ");
		result.append(varargs);
		result.append(')');
		return result.toString();
	}
		
}
} //SingleVariableDeclarationImpl
