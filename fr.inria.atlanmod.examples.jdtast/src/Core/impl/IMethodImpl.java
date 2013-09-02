/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.IMethod;
import Core.Parameter;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IMethod</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.IMethodImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link Core.impl.IMethodImpl#getIsConstructor <em>Is Constructor</em>}</li>
 *   <li>{@link Core.impl.IMethodImpl#getIsMainMethod <em>Is Main Method</em>}</li>
 *   <li>{@link Core.impl.IMethodImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link Core.impl.IMethodImpl#getExceptionTypes <em>Exception Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IMethodImpl extends IMemberImpl implements IMethod {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//IMemberImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IMethodImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IMETHOD;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReturnType() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIMethod))
			data = new DataIMethod();
			
		if (((DataIMethod)data).returnType == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IMETHOD__RETURN_TYPE, null, null));
		return ((DataIMethod)data).returnType;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnType(String newReturnType) {
	
		if (data==null) data =  new DataIMethod();
		
		else if (!(data instanceof DataIMethod)) data = new DataIMethod((DataIMember)data);
	
		String oldReturnType = ((DataIMethod)data).returnType;
		((DataIMethod)data).returnType = newReturnType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMETHOD__RETURN_TYPE, oldReturnType, ((DataIMethod)data).returnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsConstructor() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIMethod))
			data = new DataIMethod();
			
		if (((DataIMethod)data).isConstructor == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IMETHOD__IS_CONSTRUCTOR, null, null));
		return ((DataIMethod)data).isConstructor;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsConstructor(Boolean newIsConstructor) {
	
		if (data==null) data =  new DataIMethod();
		
		else if (!(data instanceof DataIMethod)) data = new DataIMethod((DataIMember)data);
	
		Boolean oldIsConstructor = ((DataIMethod)data).isConstructor;
		((DataIMethod)data).isConstructor = newIsConstructor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMETHOD__IS_CONSTRUCTOR, oldIsConstructor, ((DataIMethod)data).isConstructor));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsMainMethod() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIMethod))
			data = new DataIMethod();
			
		if (((DataIMethod)data).isMainMethod == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IMETHOD__IS_MAIN_METHOD, null, null));
		return ((DataIMethod)data).isMainMethod;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsMainMethod(Boolean newIsMainMethod) {
	
		if (data==null) data =  new DataIMethod();
		
		else if (!(data instanceof DataIMethod)) data = new DataIMethod((DataIMember)data);
	
		Boolean oldIsMainMethod = ((DataIMethod)data).isMainMethod;
		((DataIMethod)data).isMainMethod = newIsMainMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMETHOD__IS_MAIN_METHOD, oldIsMainMethod, ((DataIMethod)data).isMainMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Parameter> getParameters() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIMethod))
			data = new DataIMethod();
				
	   
		if (((DataIMethod)data).parameters == null) {
			((DataIMethod)data).parameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, CorePackage.IMETHOD__PARAMETERS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.IMETHOD__PARAMETERS);			
		}
		return ((DataIMethod)data).parameters;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getExceptionTypes() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIMethod))
			data = new DataIMethod();
			
		if (((DataIMethod)data).exceptionTypes == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	   
		if (((DataIMethod)data).exceptionTypes == null) {
			((DataIMethod)data).exceptionTypes = new EDataTypeEList<String>(String.class, this, CorePackage.IMETHOD__EXCEPTION_TYPES);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.IMETHOD__EXCEPTION_TYPES);			
		}
		return ((DataIMethod)data).exceptionTypes;	
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
			case CorePackage.IMETHOD__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
			case CorePackage.IMETHOD__RETURN_TYPE:
				return getReturnType();
			case CorePackage.IMETHOD__IS_CONSTRUCTOR:
				return getIsConstructor();
			case CorePackage.IMETHOD__IS_MAIN_METHOD:
				return getIsMainMethod();
			case CorePackage.IMETHOD__PARAMETERS:
				return getParameters();
			case CorePackage.IMETHOD__EXCEPTION_TYPES:
				return getExceptionTypes();
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
			case CorePackage.IMETHOD__RETURN_TYPE:
				setReturnType((String)newValue);
				return;
			case CorePackage.IMETHOD__IS_CONSTRUCTOR:
				setIsConstructor((Boolean)newValue);
				return;
			case CorePackage.IMETHOD__IS_MAIN_METHOD:
				setIsMainMethod((Boolean)newValue);
				return;
			case CorePackage.IMETHOD__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends Parameter>)newValue);
				return;
			case CorePackage.IMETHOD__EXCEPTION_TYPES:
				getExceptionTypes().clear();
				getExceptionTypes().addAll((Collection<? extends String>)newValue);
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
			case CorePackage.IMETHOD__RETURN_TYPE:
				setReturnType(DataIMethod.RETURN_TYPE_EDEFAULT);
				return;
			case CorePackage.IMETHOD__IS_CONSTRUCTOR:
				setIsConstructor(DataIMethod.IS_CONSTRUCTOR_EDEFAULT);
				return;
			case CorePackage.IMETHOD__IS_MAIN_METHOD:
				setIsMainMethod(DataIMethod.IS_MAIN_METHOD_EDEFAULT);
				return;
			case CorePackage.IMETHOD__PARAMETERS:
				getParameters().clear();
				return;
			case CorePackage.IMETHOD__EXCEPTION_TYPES:
				getExceptionTypes().clear();
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
			case CorePackage.IMETHOD__RETURN_TYPE:
				return DataIMethod.RETURN_TYPE_EDEFAULT == null ? getReturnType() != null : !DataIMethod.RETURN_TYPE_EDEFAULT.equals(getReturnType());
			case CorePackage.IMETHOD__IS_CONSTRUCTOR:
				return DataIMethod.IS_CONSTRUCTOR_EDEFAULT == null ? getIsConstructor() != null : !DataIMethod.IS_CONSTRUCTOR_EDEFAULT.equals(getIsConstructor());
			case CorePackage.IMETHOD__IS_MAIN_METHOD:
				return DataIMethod.IS_MAIN_METHOD_EDEFAULT == null ? getIsMainMethod() != null : !DataIMethod.IS_MAIN_METHOD_EDEFAULT.equals(getIsMainMethod());
			case CorePackage.IMETHOD__PARAMETERS:
				return getParameters() != null && !getParameters().isEmpty();
			case CorePackage.IMETHOD__EXCEPTION_TYPES:
				return getExceptionTypes() != null && !getExceptionTypes().isEmpty();
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
		if (data != null) result.append(((DataIMethod)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataIMethod extends DataIMember {


	/**
	 * The default value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected static final String RETURN_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected String returnType = RETURN_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsConstructor() <em>Is Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsConstructor()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_CONSTRUCTOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsConstructor() <em>Is Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsConstructor()
	 * @generated
	 * @ordered
	 */
	protected Boolean isConstructor = IS_CONSTRUCTOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsMainMethod() <em>Is Main Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsMainMethod()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_MAIN_METHOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsMainMethod() <em>Is Main Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsMainMethod()
	 * @generated
	 * @ordered
	 */
	protected Boolean isMainMethod = IS_MAIN_METHOD_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> parameters;

	/**
	 * The cached value of the '{@link #getExceptionTypes() <em>Exception Types</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExceptionTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> exceptionTypes;

	/**
	 *Constructor of DataIMethod
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataIMethod() {
		super();
	}
	
		
	/**
	 *Constructor of DataIMethod
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link IMember }
	 * @generated
	 */
	public DataIMethod(DataIMember data) {
		super();		
		
		source = data.source;
				
		sourceRange = data.sourceRange;
				
		javadocRange = data.javadocRange;
				
		nameRange = data.nameRange;
				
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (returnType: ");
		result.append(returnType);
		result.append(", isConstructor: ");
		result.append(isConstructor);
		result.append(", isMainMethod: ");
		result.append(isMainMethod);
		result.append(", exceptionTypes: ");
		result.append(exceptionTypes);
		result.append(')');
		return result.toString();
	}
		
}
} //IMethodImpl
