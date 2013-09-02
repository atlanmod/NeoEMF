/**
 *
 * $Id$
 */
package DOM.impl;

import Core.IMethod;

import DOM.DOMPackage;
import DOM.Expression;
import DOM.MethodInvocation;
import DOM.SimpleName;
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
 * An implementation of the model object '<em><b>Method Invocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.MethodInvocationImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link DOM.impl.MethodInvocationImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link DOM.impl.MethodInvocationImpl#getName <em>Name</em>}</li>
 *   <li>{@link DOM.impl.MethodInvocationImpl#getTypeArguments <em>Type Arguments</em>}</li>
 *   <li>{@link DOM.impl.MethodInvocationImpl#getMethodBinding <em>Method Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodInvocationImpl extends ExpressionImpl implements MethodInvocation {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodInvocationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.METHOD_INVOCATION;
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
		if ( data == null || !(data instanceof DataMethodInvocation))
			data = new DataMethodInvocation();
				
	   
		if (((DataMethodInvocation)data).arguments == null) {
			((DataMethodInvocation)data).arguments = new EObjectContainmentEList<Expression>(Expression.class, this, DOMPackage.METHOD_INVOCATION__ARGUMENTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.METHOD_INVOCATION__ARGUMENTS);			
		}
		return ((DataMethodInvocation)data).arguments;	
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
		if ( data == null || !(data instanceof DataMethodInvocation))
			data = new DataMethodInvocation();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_INVOCATION__EXPRESSION, null, null));
		return ((DataMethodInvocation)data).expression;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
	
		if (data==null) data =  new DataMethodInvocation();
	
		Expression oldExpression = ((DataMethodInvocation)data).expression;
		((DataMethodInvocation)data).expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_INVOCATION__EXPRESSION, oldExpression, newExpression);
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
	
		if (data==null) data =  new DataMethodInvocation();
		
		else if (!(data instanceof DataMethodInvocation)) data = new DataMethodInvocation((DataExpression)data);
	
		if (newExpression != ((DataMethodInvocation)data).expression) {
			NotificationChain msgs = null;
			if (((DataMethodInvocation)data).expression != null)
				msgs = ((InternalEObject) ((DataMethodInvocation)data).expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_INVOCATION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_INVOCATION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_INVOCATION__EXPRESSION, newExpression, newExpression));
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
		if ( data == null || !(data instanceof DataMethodInvocation))
			data = new DataMethodInvocation();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_INVOCATION__NAME, null, null));
		return ((DataMethodInvocation)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(SimpleName newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataMethodInvocation();
	
		SimpleName oldName = ((DataMethodInvocation)data).name;
		((DataMethodInvocation)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_INVOCATION__NAME, oldName, newName);
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
	
		if (data==null) data =  new DataMethodInvocation();
		
		else if (!(data instanceof DataMethodInvocation)) data = new DataMethodInvocation((DataExpression)data);
	
		if (newName != ((DataMethodInvocation)data).name) {
			NotificationChain msgs = null;
			if (((DataMethodInvocation)data).name != null)
				msgs = ((InternalEObject) ((DataMethodInvocation)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_INVOCATION__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_INVOCATION__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_INVOCATION__NAME, newName, newName));
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
		if ( data == null || !(data instanceof DataMethodInvocation))
			data = new DataMethodInvocation();
				
	   
		if (((DataMethodInvocation)data).typeArguments == null) {
			((DataMethodInvocation)data).typeArguments = new EObjectContainmentEList<Type>(Type.class, this, DOMPackage.METHOD_INVOCATION__TYPE_ARGUMENTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.METHOD_INVOCATION__TYPE_ARGUMENTS);			
		}
		return ((DataMethodInvocation)data).typeArguments;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IMethod getMethodBinding() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataMethodInvocation))
			data = new DataMethodInvocation();
				
	  
		if (((DataMethodInvocation)data).methodBinding == null && getNodeId()>-1) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.METHOD_INVOCATION__METHOD_BINDING);
		}		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_INVOCATION__METHOD_BINDING, null, null));
		return ((DataMethodInvocation)data).methodBinding;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX8
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IMethod basicGetMethodBinding() {
		return data != null ? ((DataMethodInvocation)data).methodBinding : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodBinding(IMethod newMethodBinding) {
	
		if (data==null) data =  new DataMethodInvocation();
		
		else if (!(data instanceof DataMethodInvocation)) data = new DataMethodInvocation((DataExpression)data);
	
		IMethod oldMethodBinding = ((DataMethodInvocation)data).methodBinding;
		((DataMethodInvocation)data).methodBinding = newMethodBinding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_INVOCATION__METHOD_BINDING, oldMethodBinding, ((DataMethodInvocation)data).methodBinding));
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
			case DOMPackage.METHOD_INVOCATION__ARGUMENTS:
				return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
			case DOMPackage.METHOD_INVOCATION__EXPRESSION:
				return basicSetExpression(null, msgs);
			case DOMPackage.METHOD_INVOCATION__NAME:
				return basicSetName(null, msgs);
			case DOMPackage.METHOD_INVOCATION__TYPE_ARGUMENTS:
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
			case DOMPackage.METHOD_INVOCATION__ARGUMENTS:
				return getArguments();
			case DOMPackage.METHOD_INVOCATION__EXPRESSION:
				return getExpression();
			case DOMPackage.METHOD_INVOCATION__NAME:
				return getName();
			case DOMPackage.METHOD_INVOCATION__TYPE_ARGUMENTS:
				return getTypeArguments();
			case DOMPackage.METHOD_INVOCATION__METHOD_BINDING:
				if (resolve) return getMethodBinding();
				return basicGetMethodBinding();
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
			case DOMPackage.METHOD_INVOCATION__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends Expression>)newValue);
				return;
			case DOMPackage.METHOD_INVOCATION__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case DOMPackage.METHOD_INVOCATION__NAME:
				setName((SimpleName)newValue);
				return;
			case DOMPackage.METHOD_INVOCATION__TYPE_ARGUMENTS:
				getTypeArguments().clear();
				getTypeArguments().addAll((Collection<? extends Type>)newValue);
				return;
			case DOMPackage.METHOD_INVOCATION__METHOD_BINDING:
				setMethodBinding((IMethod)newValue);
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
			case DOMPackage.METHOD_INVOCATION__ARGUMENTS:
				getArguments().clear();
				return;
			case DOMPackage.METHOD_INVOCATION__EXPRESSION:
				setExpression((Expression)null);
				return;
			case DOMPackage.METHOD_INVOCATION__NAME:
				setName((SimpleName)null);
				return;
			case DOMPackage.METHOD_INVOCATION__TYPE_ARGUMENTS:
				getTypeArguments().clear();
				return;
			case DOMPackage.METHOD_INVOCATION__METHOD_BINDING:
				setMethodBinding((IMethod)null);
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
			case DOMPackage.METHOD_INVOCATION__ARGUMENTS:
				return getArguments() != null && !getArguments().isEmpty();
			case DOMPackage.METHOD_INVOCATION__EXPRESSION:
				return getExpression() != null;
			case DOMPackage.METHOD_INVOCATION__NAME:
				return getName() != null;
			case DOMPackage.METHOD_INVOCATION__TYPE_ARGUMENTS:
				return getTypeArguments() != null && !getTypeArguments().isEmpty();
			case DOMPackage.METHOD_INVOCATION__METHOD_BINDING:
				return getMethodBinding() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataMethodInvocation extends DataExpression {


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
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected SimpleName name;

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
	 * The cached value of the '{@link #getMethodBinding() <em>Method Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodBinding()
	 * @generated
	 * @ordered
	 */
	protected IMethod methodBinding;

	/**
	 *Constructor of DataMethodInvocation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMethodInvocation() {
		super();
	}
	
		
	/**
	 *Constructor of DataMethodInvocation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataMethodInvocation(DataExpression data) {
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
} //MethodInvocationImpl
