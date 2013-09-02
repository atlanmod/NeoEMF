/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.MethodRefParameter;
import DOM.SimpleName;
import DOM.Type;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Ref Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.MethodRefParameterImpl#getName <em>Name</em>}</li>
 *   <li>{@link DOM.impl.MethodRefParameterImpl#getType <em>Type</em>}</li>
 *   <li>{@link DOM.impl.MethodRefParameterImpl#getVarargs <em>Varargs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodRefParameterImpl extends ASTNodeImpl implements MethodRefParameter {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ASTNodeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodRefParameterImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.METHOD_REF_PARAMETER;
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
		if ( data == null || !(data instanceof DataMethodRefParameter))
			data = new DataMethodRefParameter();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_REF_PARAMETER__NAME, null, null));
		return ((DataMethodRefParameter)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(SimpleName newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataMethodRefParameter();
	
		SimpleName oldName = ((DataMethodRefParameter)data).name;
		((DataMethodRefParameter)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_REF_PARAMETER__NAME, oldName, newName);
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
	
		if (data==null) data =  new DataMethodRefParameter();
		
		else if (!(data instanceof DataMethodRefParameter)) data = new DataMethodRefParameter((DataASTNode)data);
	
		if (newName != ((DataMethodRefParameter)data).name) {
			NotificationChain msgs = null;
			if (((DataMethodRefParameter)data).name != null)
				msgs = ((InternalEObject) ((DataMethodRefParameter)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_REF_PARAMETER__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_REF_PARAMETER__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_REF_PARAMETER__NAME, newName, newName));
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
		if ( data == null || !(data instanceof DataMethodRefParameter))
			data = new DataMethodRefParameter();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_REF_PARAMETER__TYPE, null, null));
		return ((DataMethodRefParameter)data).type;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(Type newType, NotificationChain msgs) {
	
		if (data==null) data =  new DataMethodRefParameter();
	
		Type oldType = ((DataMethodRefParameter)data).type;
		((DataMethodRefParameter)data).type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_REF_PARAMETER__TYPE, oldType, newType);
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
	
		if (data==null) data =  new DataMethodRefParameter();
		
		else if (!(data instanceof DataMethodRefParameter)) data = new DataMethodRefParameter((DataASTNode)data);
	
		if (newType != ((DataMethodRefParameter)data).type) {
			NotificationChain msgs = null;
			if (((DataMethodRefParameter)data).type != null)
				msgs = ((InternalEObject) ((DataMethodRefParameter)data).type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_REF_PARAMETER__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_REF_PARAMETER__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_REF_PARAMETER__TYPE, newType, newType));
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
		if ( data == null || !(data instanceof DataMethodRefParameter))
			data = new DataMethodRefParameter();
			
		if (((DataMethodRefParameter)data).varargs == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_REF_PARAMETER__VARARGS, null, null));
		return ((DataMethodRefParameter)data).varargs;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVarargs(Boolean newVarargs) {
	
		if (data==null) data =  new DataMethodRefParameter();
		
		else if (!(data instanceof DataMethodRefParameter)) data = new DataMethodRefParameter((DataASTNode)data);
	
		Boolean oldVarargs = ((DataMethodRefParameter)data).varargs;
		((DataMethodRefParameter)data).varargs = newVarargs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_REF_PARAMETER__VARARGS, oldVarargs, ((DataMethodRefParameter)data).varargs));
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
			case DOMPackage.METHOD_REF_PARAMETER__NAME:
				return basicSetName(null, msgs);
			case DOMPackage.METHOD_REF_PARAMETER__TYPE:
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
			case DOMPackage.METHOD_REF_PARAMETER__NAME:
				return getName();
			case DOMPackage.METHOD_REF_PARAMETER__TYPE:
				return getType();
			case DOMPackage.METHOD_REF_PARAMETER__VARARGS:
				return getVarargs();
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
			case DOMPackage.METHOD_REF_PARAMETER__NAME:
				setName((SimpleName)newValue);
				return;
			case DOMPackage.METHOD_REF_PARAMETER__TYPE:
				setType((Type)newValue);
				return;
			case DOMPackage.METHOD_REF_PARAMETER__VARARGS:
				setVarargs((Boolean)newValue);
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
			case DOMPackage.METHOD_REF_PARAMETER__NAME:
				setName((SimpleName)null);
				return;
			case DOMPackage.METHOD_REF_PARAMETER__TYPE:
				setType((Type)null);
				return;
			case DOMPackage.METHOD_REF_PARAMETER__VARARGS:
				setVarargs(DataMethodRefParameter.VARARGS_EDEFAULT);
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
			case DOMPackage.METHOD_REF_PARAMETER__NAME:
				return getName() != null;
			case DOMPackage.METHOD_REF_PARAMETER__TYPE:
				return getType() != null;
			case DOMPackage.METHOD_REF_PARAMETER__VARARGS:
				return DataMethodRefParameter.VARARGS_EDEFAULT == null ? getVarargs() != null : !DataMethodRefParameter.VARARGS_EDEFAULT.equals(getVarargs());
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
		if (data != null) result.append(((DataMethodRefParameter)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataMethodRefParameter extends DataASTNode {


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
	 *Constructor of DataMethodRefParameter
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMethodRefParameter() {
		super();
	}
	
		
	/**
	 *Constructor of DataMethodRefParameter
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	public DataMethodRefParameter(DataASTNode data) {
		super();		
		
				
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
} //MethodRefParameterImpl
