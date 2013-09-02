/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Expression;
import DOM.SuperConstructorInvocation;
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
 * An implementation of the model object '<em><b>Super Constructor Invocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.SuperConstructorInvocationImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link DOM.impl.SuperConstructorInvocationImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link DOM.impl.SuperConstructorInvocationImpl#getTypeArguments <em>Type Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SuperConstructorInvocationImpl extends StatementImpl implements SuperConstructorInvocation {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//StatementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SuperConstructorInvocationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.SUPER_CONSTRUCTOR_INVOCATION;
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
		if ( data == null || !(data instanceof DataSuperConstructorInvocation))
			data = new DataSuperConstructorInvocation();
				
	   
		if (((DataSuperConstructorInvocation)data).arguments == null) {
			((DataSuperConstructorInvocation)data).arguments = new EObjectContainmentEList<Expression>(Expression.class, this, DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS);			
		}
		return ((DataSuperConstructorInvocation)data).arguments;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataSuperConstructorInvocation))
			data = new DataSuperConstructorInvocation();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION, null, null));
		return ((DataSuperConstructorInvocation)data).expression;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
	
		if (data==null) data =  new DataSuperConstructorInvocation();
	
		Expression oldExpression = ((DataSuperConstructorInvocation)data).expression;
		((DataSuperConstructorInvocation)data).expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION, oldExpression, newExpression);
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
	public void setExpression(Expression newExpression) {
	
		if (data==null) data =  new DataSuperConstructorInvocation();
		
		else if (!(data instanceof DataSuperConstructorInvocation)) data = new DataSuperConstructorInvocation((DataStatement)data);
	
		if (newExpression != ((DataSuperConstructorInvocation)data).expression) {
			NotificationChain msgs = null;
			if (((DataSuperConstructorInvocation)data).expression != null)
				msgs = ((InternalEObject) ((DataSuperConstructorInvocation)data).expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION, newExpression, newExpression));
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
		if ( data == null || !(data instanceof DataSuperConstructorInvocation))
			data = new DataSuperConstructorInvocation();
				
	   
		if (((DataSuperConstructorInvocation)data).typeArguments == null) {
			((DataSuperConstructorInvocation)data).typeArguments = new EObjectContainmentEList<Type>(Type.class, this, DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS);			
		}
		return ((DataSuperConstructorInvocation)data).typeArguments;	
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
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS:
				return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION:
				return basicSetExpression(null, msgs);
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS:
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
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS:
				return getArguments();
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION:
				return getExpression();
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS:
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
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends Expression>)newValue);
				return;
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS:
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
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS:
				getArguments().clear();
				return;
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION:
				setExpression((Expression)null);
				return;
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS:
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
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS:
				return getArguments() != null && !getArguments().isEmpty();
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION:
				return getExpression() != null;
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS:
				return getTypeArguments() != null && !getTypeArguments().isEmpty();
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataSuperConstructorInvocation extends DataStatement {


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
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression expression;

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
	 *Constructor of DataSuperConstructorInvocation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSuperConstructorInvocation() {
		super();
	}
	
		
	/**
	 *Constructor of DataSuperConstructorInvocation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	public DataSuperConstructorInvocation(DataStatement data) {
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
} //SuperConstructorInvocationImpl
