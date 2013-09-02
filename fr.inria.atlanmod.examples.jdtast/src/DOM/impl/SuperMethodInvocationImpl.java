/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Expression;
import DOM.Name;
import DOM.SuperMethodInvocation;
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
 * An implementation of the model object '<em><b>Super Method Invocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.SuperMethodInvocationImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link DOM.impl.SuperMethodInvocationImpl#getName <em>Name</em>}</li>
 *   <li>{@link DOM.impl.SuperMethodInvocationImpl#getQualifier <em>Qualifier</em>}</li>
 *   <li>{@link DOM.impl.SuperMethodInvocationImpl#getTypeArguments <em>Type Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SuperMethodInvocationImpl extends ExpressionImpl implements SuperMethodInvocation {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SuperMethodInvocationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.SUPER_METHOD_INVOCATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getArguments() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataSuperMethodInvocation))
			data = new DataSuperMethodInvocation();
				
	   
		if (((DataSuperMethodInvocation)data).arguments == null) {
			((DataSuperMethodInvocation)data).arguments = new EObjectContainmentEList<Expression>(Expression.class, this, DOMPackage.SUPER_METHOD_INVOCATION__ARGUMENTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.SUPER_METHOD_INVOCATION__ARGUMENTS);			
		}
		return ((DataSuperMethodInvocation)data).arguments;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Name getName() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataSuperMethodInvocation))
			data = new DataSuperMethodInvocation();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.SUPER_METHOD_INVOCATION__NAME, null, null));
		return ((DataSuperMethodInvocation)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(Name newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataSuperMethodInvocation();
	
		Name oldName = ((DataSuperMethodInvocation)data).name;
		((DataSuperMethodInvocation)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.SUPER_METHOD_INVOCATION__NAME, oldName, newName);
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
	public void setName(Name newName) {
	
		if (data==null) data =  new DataSuperMethodInvocation();
		
		else if (!(data instanceof DataSuperMethodInvocation)) data = new DataSuperMethodInvocation((DataExpression)data);
	
		if (newName != ((DataSuperMethodInvocation)data).name) {
			NotificationChain msgs = null;
			if (((DataSuperMethodInvocation)data).name != null)
				msgs = ((InternalEObject) ((DataSuperMethodInvocation)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SUPER_METHOD_INVOCATION__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SUPER_METHOD_INVOCATION__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.SUPER_METHOD_INVOCATION__NAME, newName, newName));
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
		if ( data == null || !(data instanceof DataSuperMethodInvocation))
			data = new DataSuperMethodInvocation();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.SUPER_METHOD_INVOCATION__QUALIFIER, null, null));
		return ((DataSuperMethodInvocation)data).qualifier;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQualifier(Name newQualifier, NotificationChain msgs) {
	
		if (data==null) data =  new DataSuperMethodInvocation();
	
		Name oldQualifier = ((DataSuperMethodInvocation)data).qualifier;
		((DataSuperMethodInvocation)data).qualifier = newQualifier;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.SUPER_METHOD_INVOCATION__QUALIFIER, oldQualifier, newQualifier);
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
	
		if (data==null) data =  new DataSuperMethodInvocation();
		
		else if (!(data instanceof DataSuperMethodInvocation)) data = new DataSuperMethodInvocation((DataExpression)data);
	
		if (newQualifier != ((DataSuperMethodInvocation)data).qualifier) {
			NotificationChain msgs = null;
			if (((DataSuperMethodInvocation)data).qualifier != null)
				msgs = ((InternalEObject) ((DataSuperMethodInvocation)data).qualifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SUPER_METHOD_INVOCATION__QUALIFIER, null, msgs);
			if (newQualifier != null)
				msgs = ((InternalEObject)newQualifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SUPER_METHOD_INVOCATION__QUALIFIER, null, msgs);
			msgs = basicSetQualifier(newQualifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.SUPER_METHOD_INVOCATION__QUALIFIER, newQualifier, newQualifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getTypeArguments() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataSuperMethodInvocation))
			data = new DataSuperMethodInvocation();
				
	   
		if (((DataSuperMethodInvocation)data).typeArguments == null) {
			((DataSuperMethodInvocation)data).typeArguments = new EObjectContainmentEList<Type>(Type.class, this, DOMPackage.SUPER_METHOD_INVOCATION__TYPE_ARGUMENTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.SUPER_METHOD_INVOCATION__TYPE_ARGUMENTS);			
		}
		return ((DataSuperMethodInvocation)data).typeArguments;	
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
			case DOMPackage.SUPER_METHOD_INVOCATION__ARGUMENTS:
				return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
			case DOMPackage.SUPER_METHOD_INVOCATION__NAME:
				return basicSetName(null, msgs);
			case DOMPackage.SUPER_METHOD_INVOCATION__QUALIFIER:
				return basicSetQualifier(null, msgs);
			case DOMPackage.SUPER_METHOD_INVOCATION__TYPE_ARGUMENTS:
				return ((InternalEList<?>)getTypeArguments()).basicRemove(otherEnd, msgs);
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
			case DOMPackage.SUPER_METHOD_INVOCATION__ARGUMENTS:
				return getArguments();
			case DOMPackage.SUPER_METHOD_INVOCATION__NAME:
				return getName();
			case DOMPackage.SUPER_METHOD_INVOCATION__QUALIFIER:
				return getQualifier();
			case DOMPackage.SUPER_METHOD_INVOCATION__TYPE_ARGUMENTS:
				return getTypeArguments();
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
			case DOMPackage.SUPER_METHOD_INVOCATION__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends Expression>)newValue);
				return;
			case DOMPackage.SUPER_METHOD_INVOCATION__NAME:
				setName((Name)newValue);
				return;
			case DOMPackage.SUPER_METHOD_INVOCATION__QUALIFIER:
				setQualifier((Name)newValue);
				return;
			case DOMPackage.SUPER_METHOD_INVOCATION__TYPE_ARGUMENTS:
				getTypeArguments().clear();
				getTypeArguments().addAll((Collection<? extends Type>)newValue);
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
			case DOMPackage.SUPER_METHOD_INVOCATION__ARGUMENTS:
				getArguments().clear();
				return;
			case DOMPackage.SUPER_METHOD_INVOCATION__NAME:
				setName((Name)null);
				return;
			case DOMPackage.SUPER_METHOD_INVOCATION__QUALIFIER:
				setQualifier((Name)null);
				return;
			case DOMPackage.SUPER_METHOD_INVOCATION__TYPE_ARGUMENTS:
				getTypeArguments().clear();
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
			case DOMPackage.SUPER_METHOD_INVOCATION__ARGUMENTS:
				return getArguments() != null && !getArguments().isEmpty();
			case DOMPackage.SUPER_METHOD_INVOCATION__NAME:
				return getName() != null;
			case DOMPackage.SUPER_METHOD_INVOCATION__QUALIFIER:
				return getQualifier() != null;
			case DOMPackage.SUPER_METHOD_INVOCATION__TYPE_ARGUMENTS:
				return getTypeArguments() != null && !getTypeArguments().isEmpty();
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataSuperMethodInvocation extends DataExpression {


	/**
	 * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> arguments;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected Name name;

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
	 * The cached value of the '{@link #getTypeArguments() <em>Type Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> typeArguments;

	/**
	 *Constructor of DataSuperMethodInvocation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSuperMethodInvocation() {
		super();
	}
	
		
	/**
	 *Constructor of DataSuperMethodInvocation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataSuperMethodInvocation(DataExpression data) {
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
} //SuperMethodInvocationImpl
