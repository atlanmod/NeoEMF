/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.AnonymousClassDeclaration;
import DOM.ClassInstanceCreation;
import DOM.DOMPackage;
import DOM.Expression;
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
 * An implementation of the model object '<em><b>Class Instance Creation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.ClassInstanceCreationImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link DOM.impl.ClassInstanceCreationImpl#getAnonymousClassDeclaration <em>Anonymous Class Declaration</em>}</li>
 *   <li>{@link DOM.impl.ClassInstanceCreationImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link DOM.impl.ClassInstanceCreationImpl#getType <em>Type</em>}</li>
 *   <li>{@link DOM.impl.ClassInstanceCreationImpl#getTypeArguments <em>Type Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassInstanceCreationImpl extends ExpressionImpl implements ClassInstanceCreation {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassInstanceCreationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.CLASS_INSTANCE_CREATION;
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
		if ( data == null || !(data instanceof DataClassInstanceCreation))
			data = new DataClassInstanceCreation();
				
	   
		if (((DataClassInstanceCreation)data).arguments == null) {
			((DataClassInstanceCreation)data).arguments = new EObjectContainmentEList<Expression>(Expression.class, this, DOMPackage.CLASS_INSTANCE_CREATION__ARGUMENTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.CLASS_INSTANCE_CREATION__ARGUMENTS);			
		}
		return ((DataClassInstanceCreation)data).arguments;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnonymousClassDeclaration getAnonymousClassDeclaration() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataClassInstanceCreation))
			data = new DataClassInstanceCreation();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION, null, null));
		return ((DataClassInstanceCreation)data).anonymousClassDeclaration;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnonymousClassDeclaration(AnonymousClassDeclaration newAnonymousClassDeclaration, NotificationChain msgs) {
	
		if (data==null) data =  new DataClassInstanceCreation();
	
		AnonymousClassDeclaration oldAnonymousClassDeclaration = ((DataClassInstanceCreation)data).anonymousClassDeclaration;
		((DataClassInstanceCreation)data).anonymousClassDeclaration = newAnonymousClassDeclaration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION, oldAnonymousClassDeclaration, newAnonymousClassDeclaration);
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
	public void setAnonymousClassDeclaration(AnonymousClassDeclaration newAnonymousClassDeclaration) {
	
		if (data==null) data =  new DataClassInstanceCreation();
		
		else if (!(data instanceof DataClassInstanceCreation)) data = new DataClassInstanceCreation((DataExpression)data);
	
		if (newAnonymousClassDeclaration != ((DataClassInstanceCreation)data).anonymousClassDeclaration) {
			NotificationChain msgs = null;
			if (((DataClassInstanceCreation)data).anonymousClassDeclaration != null)
				msgs = ((InternalEObject) ((DataClassInstanceCreation)data).anonymousClassDeclaration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION, null, msgs);
			if (newAnonymousClassDeclaration != null)
				msgs = ((InternalEObject)newAnonymousClassDeclaration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION, null, msgs);
			msgs = basicSetAnonymousClassDeclaration(newAnonymousClassDeclaration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION, newAnonymousClassDeclaration, newAnonymousClassDeclaration));
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
		if ( data == null || !(data instanceof DataClassInstanceCreation))
			data = new DataClassInstanceCreation();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.CLASS_INSTANCE_CREATION__EXPRESSION, null, null));
		return ((DataClassInstanceCreation)data).expression;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
	
		if (data==null) data =  new DataClassInstanceCreation();
	
		Expression oldExpression = ((DataClassInstanceCreation)data).expression;
		((DataClassInstanceCreation)data).expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.CLASS_INSTANCE_CREATION__EXPRESSION, oldExpression, newExpression);
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
	
		if (data==null) data =  new DataClassInstanceCreation();
		
		else if (!(data instanceof DataClassInstanceCreation)) data = new DataClassInstanceCreation((DataExpression)data);
	
		if (newExpression != ((DataClassInstanceCreation)data).expression) {
			NotificationChain msgs = null;
			if (((DataClassInstanceCreation)data).expression != null)
				msgs = ((InternalEObject) ((DataClassInstanceCreation)data).expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CLASS_INSTANCE_CREATION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CLASS_INSTANCE_CREATION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.CLASS_INSTANCE_CREATION__EXPRESSION, newExpression, newExpression));
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
		if ( data == null || !(data instanceof DataClassInstanceCreation))
			data = new DataClassInstanceCreation();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.CLASS_INSTANCE_CREATION__TYPE, null, null));
		return ((DataClassInstanceCreation)data).type;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(Type newType, NotificationChain msgs) {
	
		if (data==null) data =  new DataClassInstanceCreation();
	
		Type oldType = ((DataClassInstanceCreation)data).type;
		((DataClassInstanceCreation)data).type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.CLASS_INSTANCE_CREATION__TYPE, oldType, newType);
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
	
		if (data==null) data =  new DataClassInstanceCreation();
		
		else if (!(data instanceof DataClassInstanceCreation)) data = new DataClassInstanceCreation((DataExpression)data);
	
		if (newType != ((DataClassInstanceCreation)data).type) {
			NotificationChain msgs = null;
			if (((DataClassInstanceCreation)data).type != null)
				msgs = ((InternalEObject) ((DataClassInstanceCreation)data).type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CLASS_INSTANCE_CREATION__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CLASS_INSTANCE_CREATION__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.CLASS_INSTANCE_CREATION__TYPE, newType, newType));
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
		if ( data == null || !(data instanceof DataClassInstanceCreation))
			data = new DataClassInstanceCreation();
				
	   
		if (((DataClassInstanceCreation)data).typeArguments == null) {
			((DataClassInstanceCreation)data).typeArguments = new EObjectContainmentEList<Type>(Type.class, this, DOMPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS);			
		}
		return ((DataClassInstanceCreation)data).typeArguments;	
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
			case DOMPackage.CLASS_INSTANCE_CREATION__ARGUMENTS:
				return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
			case DOMPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION:
				return basicSetAnonymousClassDeclaration(null, msgs);
			case DOMPackage.CLASS_INSTANCE_CREATION__EXPRESSION:
				return basicSetExpression(null, msgs);
			case DOMPackage.CLASS_INSTANCE_CREATION__TYPE:
				return basicSetType(null, msgs);
			case DOMPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS:
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
			case DOMPackage.CLASS_INSTANCE_CREATION__ARGUMENTS:
				return getArguments();
			case DOMPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION:
				return getAnonymousClassDeclaration();
			case DOMPackage.CLASS_INSTANCE_CREATION__EXPRESSION:
				return getExpression();
			case DOMPackage.CLASS_INSTANCE_CREATION__TYPE:
				return getType();
			case DOMPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS:
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
			case DOMPackage.CLASS_INSTANCE_CREATION__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends Expression>)newValue);
				return;
			case DOMPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION:
				setAnonymousClassDeclaration((AnonymousClassDeclaration)newValue);
				return;
			case DOMPackage.CLASS_INSTANCE_CREATION__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case DOMPackage.CLASS_INSTANCE_CREATION__TYPE:
				setType((Type)newValue);
				return;
			case DOMPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS:
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
			case DOMPackage.CLASS_INSTANCE_CREATION__ARGUMENTS:
				getArguments().clear();
				return;
			case DOMPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION:
				setAnonymousClassDeclaration((AnonymousClassDeclaration)null);
				return;
			case DOMPackage.CLASS_INSTANCE_CREATION__EXPRESSION:
				setExpression((Expression)null);
				return;
			case DOMPackage.CLASS_INSTANCE_CREATION__TYPE:
				setType((Type)null);
				return;
			case DOMPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS:
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
			case DOMPackage.CLASS_INSTANCE_CREATION__ARGUMENTS:
				return getArguments() != null && !getArguments().isEmpty();
			case DOMPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION:
				return getAnonymousClassDeclaration() != null;
			case DOMPackage.CLASS_INSTANCE_CREATION__EXPRESSION:
				return getExpression() != null;
			case DOMPackage.CLASS_INSTANCE_CREATION__TYPE:
				return getType() != null;
			case DOMPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS:
				return getTypeArguments() != null && !getTypeArguments().isEmpty();
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataClassInstanceCreation extends DataExpression {


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
	 * The cached value of the '{@link #getAnonymousClassDeclaration() <em>Anonymous Class Declaration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnonymousClassDeclaration()
	 * @generated
	 * @ordered
	 */
	protected AnonymousClassDeclaration anonymousClassDeclaration;

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
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Type type;

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
	 *Constructor of DataClassInstanceCreation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataClassInstanceCreation() {
		super();
	}
	
		
	/**
	 *Constructor of DataClassInstanceCreation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataClassInstanceCreation(DataExpression data) {
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
} //ClassInstanceCreationImpl
