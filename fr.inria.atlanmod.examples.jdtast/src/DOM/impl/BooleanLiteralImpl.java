/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.BooleanLiteral;
import DOM.DOMPackage;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.BooleanLiteralImpl#getBooleanValue <em>Boolean Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BooleanLiteralImpl extends ExpressionImpl implements BooleanLiteral {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanLiteralImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.BOOLEAN_LITERAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getBooleanValue() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataBooleanLiteral))
			data = new DataBooleanLiteral();
			
		if (((DataBooleanLiteral)data).booleanValue == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.BOOLEAN_LITERAL__BOOLEAN_VALUE, null, null));
		return ((DataBooleanLiteral)data).booleanValue;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBooleanValue(Boolean newBooleanValue) {
	
		if (data==null) data =  new DataBooleanLiteral();
		
		else if (!(data instanceof DataBooleanLiteral)) data = new DataBooleanLiteral((DataExpression)data);
	
		Boolean oldBooleanValue = ((DataBooleanLiteral)data).booleanValue;
		((DataBooleanLiteral)data).booleanValue = newBooleanValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.BOOLEAN_LITERAL__BOOLEAN_VALUE, oldBooleanValue, ((DataBooleanLiteral)data).booleanValue));
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
			case DOMPackage.BOOLEAN_LITERAL__BOOLEAN_VALUE:
				return getBooleanValue();
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
			case DOMPackage.BOOLEAN_LITERAL__BOOLEAN_VALUE:
				setBooleanValue((Boolean)newValue);
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
			case DOMPackage.BOOLEAN_LITERAL__BOOLEAN_VALUE:
				setBooleanValue(DataBooleanLiteral.BOOLEAN_VALUE_EDEFAULT);
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
			case DOMPackage.BOOLEAN_LITERAL__BOOLEAN_VALUE:
				return DataBooleanLiteral.BOOLEAN_VALUE_EDEFAULT == null ? getBooleanValue() != null : !DataBooleanLiteral.BOOLEAN_VALUE_EDEFAULT.equals(getBooleanValue());
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
		if (data != null) result.append(((DataBooleanLiteral)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataBooleanLiteral extends DataExpression {


	/**
	 * The default value of the '{@link #getBooleanValue() <em>Boolean Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBooleanValue()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean BOOLEAN_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBooleanValue() <em>Boolean Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBooleanValue()
	 * @generated
	 * @ordered
	 */
	protected Boolean booleanValue = BOOLEAN_VALUE_EDEFAULT;

	/**
	 *Constructor of DataBooleanLiteral
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataBooleanLiteral() {
		super();
	}
	
		
	/**
	 *Constructor of DataBooleanLiteral
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataBooleanLiteral(DataExpression data) {
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
		result.append(" (booleanValue: ");
		result.append(booleanValue);
		result.append(')');
		return result.toString();
	}
		
}
} //BooleanLiteralImpl
