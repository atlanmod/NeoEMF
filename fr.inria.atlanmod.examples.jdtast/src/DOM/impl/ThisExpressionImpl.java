/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Name;
import DOM.ThisExpression;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>This Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.ThisExpressionImpl#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ThisExpressionImpl extends ExpressionImpl implements ThisExpression {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ThisExpressionImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.THIS_EXPRESSION;
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
		if ( data == null || !(data instanceof DataThisExpression))
			data = new DataThisExpression();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.THIS_EXPRESSION__QUALIFIER, null, null));
		return ((DataThisExpression)data).qualifier;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQualifier(Name newQualifier, NotificationChain msgs) {
	
		if (data==null) data =  new DataThisExpression();
	
		Name oldQualifier = ((DataThisExpression)data).qualifier;
		((DataThisExpression)data).qualifier = newQualifier;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.THIS_EXPRESSION__QUALIFIER, oldQualifier, newQualifier);
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
	
		if (data==null) data =  new DataThisExpression();
		
		else if (!(data instanceof DataThisExpression)) data = new DataThisExpression((DataExpression)data);
	
		if (newQualifier != ((DataThisExpression)data).qualifier) {
			NotificationChain msgs = null;
			if (((DataThisExpression)data).qualifier != null)
				msgs = ((InternalEObject) ((DataThisExpression)data).qualifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.THIS_EXPRESSION__QUALIFIER, null, msgs);
			if (newQualifier != null)
				msgs = ((InternalEObject)newQualifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.THIS_EXPRESSION__QUALIFIER, null, msgs);
			msgs = basicSetQualifier(newQualifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.THIS_EXPRESSION__QUALIFIER, newQualifier, newQualifier));
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
			case DOMPackage.THIS_EXPRESSION__QUALIFIER:
				return basicSetQualifier(null, msgs);
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
			case DOMPackage.THIS_EXPRESSION__QUALIFIER:
				return getQualifier();
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
			case DOMPackage.THIS_EXPRESSION__QUALIFIER:
				setQualifier((Name)newValue);
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
			case DOMPackage.THIS_EXPRESSION__QUALIFIER:
				setQualifier((Name)null);
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
			case DOMPackage.THIS_EXPRESSION__QUALIFIER:
				return getQualifier() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataThisExpression extends DataExpression {


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
	 *Constructor of DataThisExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataThisExpression() {
		super();
	}
	
		
	/**
	 *Constructor of DataThisExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataThisExpression(DataExpression data) {
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
} //ThisExpressionImpl
