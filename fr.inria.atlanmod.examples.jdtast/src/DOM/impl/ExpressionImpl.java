/**
 *
 * $Id$
 */
package DOM.impl;

import Core.IType;

import DOM.DOMPackage;
import DOM.Expression;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.ExpressionImpl#getResolveBoxing <em>Resolve Boxing</em>}</li>
 *   <li>{@link DOM.impl.ExpressionImpl#getResolveUnboxing <em>Resolve Unboxing</em>}</li>
 *   <li>{@link DOM.impl.ExpressionImpl#getTypeBinding <em>Type Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ExpressionImpl extends ASTNodeImpl implements Expression {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ASTNodeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpressionImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getResolveBoxing() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataExpression))
			data = new DataExpression();
			
		if (((DataExpression)data).resolveBoxing == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.EXPRESSION__RESOLVE_BOXING, null, null));
		return ((DataExpression)data).resolveBoxing;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResolveBoxing(Boolean newResolveBoxing) {
	
		if (data==null) data =  new DataExpression();
		
		else if (!(data instanceof DataExpression)) data = new DataExpression((DataASTNode)data);
	
		Boolean oldResolveBoxing = ((DataExpression)data).resolveBoxing;
		((DataExpression)data).resolveBoxing = newResolveBoxing;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.EXPRESSION__RESOLVE_BOXING, oldResolveBoxing, ((DataExpression)data).resolveBoxing));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getResolveUnboxing() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataExpression))
			data = new DataExpression();
			
		if (((DataExpression)data).resolveUnboxing == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.EXPRESSION__RESOLVE_UNBOXING, null, null));
		return ((DataExpression)data).resolveUnboxing;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResolveUnboxing(Boolean newResolveUnboxing) {
	
		if (data==null) data =  new DataExpression();
		
		else if (!(data instanceof DataExpression)) data = new DataExpression((DataASTNode)data);
	
		Boolean oldResolveUnboxing = ((DataExpression)data).resolveUnboxing;
		((DataExpression)data).resolveUnboxing = newResolveUnboxing;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.EXPRESSION__RESOLVE_UNBOXING, oldResolveUnboxing, ((DataExpression)data).resolveUnboxing));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IType getTypeBinding() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataExpression))
			data = new DataExpression();
				
	  
		if (((DataExpression)data).typeBinding == null && getNodeId()>-1) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.EXPRESSION__TYPE_BINDING);
		}		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.EXPRESSION__TYPE_BINDING, null, null));
		return ((DataExpression)data).typeBinding;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX8
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IType basicGetTypeBinding() {
		return data != null ? ((DataExpression)data).typeBinding : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeBinding(IType newTypeBinding) {
	
		if (data==null) data =  new DataExpression();
		
		else if (!(data instanceof DataExpression)) data = new DataExpression((DataASTNode)data);
	
		IType oldTypeBinding = ((DataExpression)data).typeBinding;
		((DataExpression)data).typeBinding = newTypeBinding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.EXPRESSION__TYPE_BINDING, oldTypeBinding, ((DataExpression)data).typeBinding));
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
			case DOMPackage.EXPRESSION__RESOLVE_BOXING:
				return getResolveBoxing();
			case DOMPackage.EXPRESSION__RESOLVE_UNBOXING:
				return getResolveUnboxing();
			case DOMPackage.EXPRESSION__TYPE_BINDING:
				if (resolve) return getTypeBinding();
				return basicGetTypeBinding();
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
			case DOMPackage.EXPRESSION__RESOLVE_BOXING:
				setResolveBoxing((Boolean)newValue);
				return;
			case DOMPackage.EXPRESSION__RESOLVE_UNBOXING:
				setResolveUnboxing((Boolean)newValue);
				return;
			case DOMPackage.EXPRESSION__TYPE_BINDING:
				setTypeBinding((IType)newValue);
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
			case DOMPackage.EXPRESSION__RESOLVE_BOXING:
				setResolveBoxing(DataExpression.RESOLVE_BOXING_EDEFAULT);
				return;
			case DOMPackage.EXPRESSION__RESOLVE_UNBOXING:
				setResolveUnboxing(DataExpression.RESOLVE_UNBOXING_EDEFAULT);
				return;
			case DOMPackage.EXPRESSION__TYPE_BINDING:
				setTypeBinding((IType)null);
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
			case DOMPackage.EXPRESSION__RESOLVE_BOXING:
				return DataExpression.RESOLVE_BOXING_EDEFAULT == null ? getResolveBoxing() != null : !DataExpression.RESOLVE_BOXING_EDEFAULT.equals(getResolveBoxing());
			case DOMPackage.EXPRESSION__RESOLVE_UNBOXING:
				return DataExpression.RESOLVE_UNBOXING_EDEFAULT == null ? getResolveUnboxing() != null : !DataExpression.RESOLVE_UNBOXING_EDEFAULT.equals(getResolveUnboxing());
			case DOMPackage.EXPRESSION__TYPE_BINDING:
				return getTypeBinding() != null;
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
		if (data != null) result.append(((DataExpression)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataExpression extends DataASTNode {


	/**
	 * The default value of the '{@link #getResolveBoxing() <em>Resolve Boxing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolveBoxing()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean RESOLVE_BOXING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResolveBoxing() <em>Resolve Boxing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolveBoxing()
	 * @generated
	 * @ordered
	 */
	protected Boolean resolveBoxing = RESOLVE_BOXING_EDEFAULT;

	/**
	 * The default value of the '{@link #getResolveUnboxing() <em>Resolve Unboxing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolveUnboxing()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean RESOLVE_UNBOXING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResolveUnboxing() <em>Resolve Unboxing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolveUnboxing()
	 * @generated
	 * @ordered
	 */
	protected Boolean resolveUnboxing = RESOLVE_UNBOXING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTypeBinding() <em>Type Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeBinding()
	 * @generated
	 * @ordered
	 */
	protected IType typeBinding;

	/**
	 *Constructor of DataExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataExpression() {
		super();
	}
	
		
	/**
	 *Constructor of DataExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	public DataExpression(DataASTNode data) {
		super();		
		
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (resolveBoxing: ");
		result.append(resolveBoxing);
		result.append(", resolveUnboxing: ");
		result.append(resolveUnboxing);
		result.append(')');
		return result.toString();
	}
		
}
} //ExpressionImpl
