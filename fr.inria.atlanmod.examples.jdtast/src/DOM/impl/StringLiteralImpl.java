/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.StringLiteral;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.StringLiteralImpl#getEscapedValue <em>Escaped Value</em>}</li>
 *   <li>{@link DOM.impl.StringLiteralImpl#getLiteralValue <em>Literal Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StringLiteralImpl extends ExpressionImpl implements StringLiteral {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StringLiteralImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.STRING_LITERAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEscapedValue() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataStringLiteral))
			data = new DataStringLiteral();
			
		if (((DataStringLiteral)data).escapedValue == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.STRING_LITERAL__ESCAPED_VALUE, null, null));
		return ((DataStringLiteral)data).escapedValue;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEscapedValue(String newEscapedValue) {
	
		if (data==null) data =  new DataStringLiteral();
		
		else if (!(data instanceof DataStringLiteral)) data = new DataStringLiteral((DataExpression)data);
	
		String oldEscapedValue = ((DataStringLiteral)data).escapedValue;
		((DataStringLiteral)data).escapedValue = newEscapedValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.STRING_LITERAL__ESCAPED_VALUE, oldEscapedValue, ((DataStringLiteral)data).escapedValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteralValue() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataStringLiteral))
			data = new DataStringLiteral();
			
		if (((DataStringLiteral)data).literalValue == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.STRING_LITERAL__LITERAL_VALUE, null, null));
		return ((DataStringLiteral)data).literalValue;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLiteralValue(String newLiteralValue) {
	
		if (data==null) data =  new DataStringLiteral();
		
		else if (!(data instanceof DataStringLiteral)) data = new DataStringLiteral((DataExpression)data);
	
		String oldLiteralValue = ((DataStringLiteral)data).literalValue;
		((DataStringLiteral)data).literalValue = newLiteralValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.STRING_LITERAL__LITERAL_VALUE, oldLiteralValue, ((DataStringLiteral)data).literalValue));
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
			case DOMPackage.STRING_LITERAL__ESCAPED_VALUE:
				return getEscapedValue();
			case DOMPackage.STRING_LITERAL__LITERAL_VALUE:
				return getLiteralValue();
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
			case DOMPackage.STRING_LITERAL__ESCAPED_VALUE:
				setEscapedValue((String)newValue);
				return;
			case DOMPackage.STRING_LITERAL__LITERAL_VALUE:
				setLiteralValue((String)newValue);
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
			case DOMPackage.STRING_LITERAL__ESCAPED_VALUE:
				setEscapedValue(DataStringLiteral.ESCAPED_VALUE_EDEFAULT);
				return;
			case DOMPackage.STRING_LITERAL__LITERAL_VALUE:
				setLiteralValue(DataStringLiteral.LITERAL_VALUE_EDEFAULT);
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
			case DOMPackage.STRING_LITERAL__ESCAPED_VALUE:
				return DataStringLiteral.ESCAPED_VALUE_EDEFAULT == null ? getEscapedValue() != null : !DataStringLiteral.ESCAPED_VALUE_EDEFAULT.equals(getEscapedValue());
			case DOMPackage.STRING_LITERAL__LITERAL_VALUE:
				return DataStringLiteral.LITERAL_VALUE_EDEFAULT == null ? getLiteralValue() != null : !DataStringLiteral.LITERAL_VALUE_EDEFAULT.equals(getLiteralValue());
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
		if (data != null) result.append(((DataStringLiteral)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataStringLiteral extends DataExpression {


	/**
	 * The default value of the '{@link #getEscapedValue() <em>Escaped Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEscapedValue()
	 * @generated
	 * @ordered
	 */
	protected static final String ESCAPED_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEscapedValue() <em>Escaped Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEscapedValue()
	 * @generated
	 * @ordered
	 */
	protected String escapedValue = ESCAPED_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLiteralValue() <em>Literal Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLiteralValue()
	 * @generated
	 * @ordered
	 */
	protected static final String LITERAL_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLiteralValue() <em>Literal Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLiteralValue()
	 * @generated
	 * @ordered
	 */
	protected String literalValue = LITERAL_VALUE_EDEFAULT;

	/**
	 *Constructor of DataStringLiteral
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataStringLiteral() {
		super();
	}
	
		
	/**
	 *Constructor of DataStringLiteral
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataStringLiteral(DataExpression data) {
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
		result.append(" (escapedValue: ");
		result.append(escapedValue);
		result.append(", literalValue: ");
		result.append(literalValue);
		result.append(')');
		return result.toString();
	}
		
}
} //StringLiteralImpl
