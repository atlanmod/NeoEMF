/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.CharacterLiteral;
import DOM.DOMPackage;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Character Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.CharacterLiteralImpl#getCharValue <em>Char Value</em>}</li>
 *   <li>{@link DOM.impl.CharacterLiteralImpl#getEscapedValue <em>Escaped Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CharacterLiteralImpl extends ExpressionImpl implements CharacterLiteral {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CharacterLiteralImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.CHARACTER_LITERAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCharValue() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataCharacterLiteral))
			data = new DataCharacterLiteral();
			
		if (((DataCharacterLiteral)data).charValue == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.CHARACTER_LITERAL__CHAR_VALUE, null, null));
		return ((DataCharacterLiteral)data).charValue;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharValue(String newCharValue) {
	
		if (data==null) data =  new DataCharacterLiteral();
		
		else if (!(data instanceof DataCharacterLiteral)) data = new DataCharacterLiteral((DataExpression)data);
	
		String oldCharValue = ((DataCharacterLiteral)data).charValue;
		((DataCharacterLiteral)data).charValue = newCharValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.CHARACTER_LITERAL__CHAR_VALUE, oldCharValue, ((DataCharacterLiteral)data).charValue));
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
		if ( data == null || !(data instanceof DataCharacterLiteral))
			data = new DataCharacterLiteral();
			
		if (((DataCharacterLiteral)data).escapedValue == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.CHARACTER_LITERAL__ESCAPED_VALUE, null, null));
		return ((DataCharacterLiteral)data).escapedValue;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEscapedValue(String newEscapedValue) {
	
		if (data==null) data =  new DataCharacterLiteral();
		
		else if (!(data instanceof DataCharacterLiteral)) data = new DataCharacterLiteral((DataExpression)data);
	
		String oldEscapedValue = ((DataCharacterLiteral)data).escapedValue;
		((DataCharacterLiteral)data).escapedValue = newEscapedValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.CHARACTER_LITERAL__ESCAPED_VALUE, oldEscapedValue, ((DataCharacterLiteral)data).escapedValue));
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
			case DOMPackage.CHARACTER_LITERAL__CHAR_VALUE:
				return getCharValue();
			case DOMPackage.CHARACTER_LITERAL__ESCAPED_VALUE:
				return getEscapedValue();
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
			case DOMPackage.CHARACTER_LITERAL__CHAR_VALUE:
				setCharValue((String)newValue);
				return;
			case DOMPackage.CHARACTER_LITERAL__ESCAPED_VALUE:
				setEscapedValue((String)newValue);
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
			case DOMPackage.CHARACTER_LITERAL__CHAR_VALUE:
				setCharValue(DataCharacterLiteral.CHAR_VALUE_EDEFAULT);
				return;
			case DOMPackage.CHARACTER_LITERAL__ESCAPED_VALUE:
				setEscapedValue(DataCharacterLiteral.ESCAPED_VALUE_EDEFAULT);
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
			case DOMPackage.CHARACTER_LITERAL__CHAR_VALUE:
				return DataCharacterLiteral.CHAR_VALUE_EDEFAULT == null ? getCharValue() != null : !DataCharacterLiteral.CHAR_VALUE_EDEFAULT.equals(getCharValue());
			case DOMPackage.CHARACTER_LITERAL__ESCAPED_VALUE:
				return DataCharacterLiteral.ESCAPED_VALUE_EDEFAULT == null ? getEscapedValue() != null : !DataCharacterLiteral.ESCAPED_VALUE_EDEFAULT.equals(getEscapedValue());
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
		if (data != null) result.append(((DataCharacterLiteral)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataCharacterLiteral extends DataExpression {


	/**
	 * The default value of the '{@link #getCharValue() <em>Char Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharValue()
	 * @generated
	 * @ordered
	 */
	protected static final String CHAR_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCharValue() <em>Char Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharValue()
	 * @generated
	 * @ordered
	 */
	protected String charValue = CHAR_VALUE_EDEFAULT;

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
	 *Constructor of DataCharacterLiteral
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataCharacterLiteral() {
		super();
	}
	
		
	/**
	 *Constructor of DataCharacterLiteral
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataCharacterLiteral(DataExpression data) {
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
		result.append(" (charValue: ");
		result.append(charValue);
		result.append(", escapedValue: ");
		result.append(escapedValue);
		result.append(')');
		return result.toString();
	}
		
}
} //CharacterLiteralImpl
