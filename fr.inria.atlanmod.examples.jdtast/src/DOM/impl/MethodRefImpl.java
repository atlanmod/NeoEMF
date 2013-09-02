/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.MethodRef;
import DOM.MethodRefParameter;
import DOM.Name;
import DOM.SimpleName;

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
 * An implementation of the model object '<em><b>Method Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.MethodRefImpl#getName <em>Name</em>}</li>
 *   <li>{@link DOM.impl.MethodRefImpl#getQualifier <em>Qualifier</em>}</li>
 *   <li>{@link DOM.impl.MethodRefImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodRefImpl extends ASTNodeImpl implements MethodRef {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ASTNodeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodRefImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.METHOD_REF;
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
		if ( data == null || !(data instanceof DataMethodRef))
			data = new DataMethodRef();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_REF__NAME, null, null));
		return ((DataMethodRef)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(SimpleName newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataMethodRef();
	
		SimpleName oldName = ((DataMethodRef)data).name;
		((DataMethodRef)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_REF__NAME, oldName, newName);
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
	
		if (data==null) data =  new DataMethodRef();
		
		else if (!(data instanceof DataMethodRef)) data = new DataMethodRef((DataASTNode)data);
	
		if (newName != ((DataMethodRef)data).name) {
			NotificationChain msgs = null;
			if (((DataMethodRef)data).name != null)
				msgs = ((InternalEObject) ((DataMethodRef)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_REF__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_REF__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_REF__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Name getQualifier() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataMethodRef))
			data = new DataMethodRef();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_REF__QUALIFIER, null, null));
		return ((DataMethodRef)data).qualifier;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQualifier(Name newQualifier, NotificationChain msgs) {
	
		if (data==null) data =  new DataMethodRef();
	
		Name oldQualifier = ((DataMethodRef)data).qualifier;
		((DataMethodRef)data).qualifier = newQualifier;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_REF__QUALIFIER, oldQualifier, newQualifier);
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
	public void setQualifier(Name newQualifier) {
	
		if (data==null) data =  new DataMethodRef();
		
		else if (!(data instanceof DataMethodRef)) data = new DataMethodRef((DataASTNode)data);
	
		if (newQualifier != ((DataMethodRef)data).qualifier) {
			NotificationChain msgs = null;
			if (((DataMethodRef)data).qualifier != null)
				msgs = ((InternalEObject) ((DataMethodRef)data).qualifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_REF__QUALIFIER, null, msgs);
			if (newQualifier != null)
				msgs = ((InternalEObject)newQualifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_REF__QUALIFIER, null, msgs);
			msgs = basicSetQualifier(newQualifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_REF__QUALIFIER, newQualifier, newQualifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MethodRefParameter> getParameters() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataMethodRef))
			data = new DataMethodRef();
				
	   
		if (((DataMethodRef)data).parameters == null) {
			((DataMethodRef)data).parameters = new EObjectContainmentEList<MethodRefParameter>(MethodRefParameter.class, this, DOMPackage.METHOD_REF__PARAMETERS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.METHOD_REF__PARAMETERS);			
		}
		return ((DataMethodRef)data).parameters;	
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
			case DOMPackage.METHOD_REF__NAME:
				return basicSetName(null, msgs);
			case DOMPackage.METHOD_REF__QUALIFIER:
				return basicSetQualifier(null, msgs);
			case DOMPackage.METHOD_REF__PARAMETERS:
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
			case DOMPackage.METHOD_REF__NAME:
				return getName();
			case DOMPackage.METHOD_REF__QUALIFIER:
				return getQualifier();
			case DOMPackage.METHOD_REF__PARAMETERS:
				return getParameters();
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
			case DOMPackage.METHOD_REF__NAME:
				setName((SimpleName)newValue);
				return;
			case DOMPackage.METHOD_REF__QUALIFIER:
				setQualifier((Name)newValue);
				return;
			case DOMPackage.METHOD_REF__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends MethodRefParameter>)newValue);
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
			case DOMPackage.METHOD_REF__NAME:
				setName((SimpleName)null);
				return;
			case DOMPackage.METHOD_REF__QUALIFIER:
				setQualifier((Name)null);
				return;
			case DOMPackage.METHOD_REF__PARAMETERS:
				getParameters().clear();
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
			case DOMPackage.METHOD_REF__NAME:
				return getName() != null;
			case DOMPackage.METHOD_REF__QUALIFIER:
				return getQualifier() != null;
			case DOMPackage.METHOD_REF__PARAMETERS:
				return getParameters() != null && !getParameters().isEmpty();
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataMethodRef extends DataASTNode {


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
	 * The cached value of the '{@link #getQualifier() <em>Qualifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifier()
	 * @generated
	 * @ordered
	 */
	protected Name qualifier;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodRefParameter> parameters;

	/**
	 *Constructor of DataMethodRef
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMethodRef() {
		super();
	}
	
		
	/**
	 *Constructor of DataMethodRef
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	public DataMethodRef(DataASTNode data) {
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
} //MethodRefImpl
