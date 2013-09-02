/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.NumberLiteral;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Number Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.NumberLiteralImpl#getToken <em>Token</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NumberLiteralImpl extends ExpressionImpl implements NumberLiteral {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NumberLiteralImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.NUMBER_LITERAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getToken() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataNumberLiteral))
			data = new DataNumberLiteral();
			
		if (((DataNumberLiteral)data).token == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.NUMBER_LITERAL__TOKEN, null, null));
		return ((DataNumberLiteral)data).token;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setToken(String newToken) {
	
		if (data==null) data =  new DataNumberLiteral();
		
		else if (!(data instanceof DataNumberLiteral)) data = new DataNumberLiteral((DataExpression)data);
	
		String oldToken = ((DataNumberLiteral)data).token;
		((DataNumberLiteral)data).token = newToken;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.NUMBER_LITERAL__TOKEN, oldToken, ((DataNumberLiteral)data).token));
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
			case DOMPackage.NUMBER_LITERAL__TOKEN:
				return getToken();
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
			case DOMPackage.NUMBER_LITERAL__TOKEN:
				setToken((String)newValue);
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
			case DOMPackage.NUMBER_LITERAL__TOKEN:
				setToken(DataNumberLiteral.TOKEN_EDEFAULT);
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
			case DOMPackage.NUMBER_LITERAL__TOKEN:
				return DataNumberLiteral.TOKEN_EDEFAULT == null ? getToken() != null : !DataNumberLiteral.TOKEN_EDEFAULT.equals(getToken());
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
		if (data != null) result.append(((DataNumberLiteral)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataNumberLiteral extends DataExpression {


	/**
	 * The default value of the '{@link #getToken() <em>Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToken()
	 * @generated
	 * @ordered
	 */
	protected static final String TOKEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getToken() <em>Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToken()
	 * @generated
	 * @ordered
	 */
	protected String token = TOKEN_EDEFAULT;

	/**
	 *Constructor of DataNumberLiteral
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataNumberLiteral() {
		super();
	}
	
		
	/**
	 *Constructor of DataNumberLiteral
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataNumberLiteral(DataExpression data) {
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
		result.append(" (token: ");
		result.append(token);
		result.append(')');
		return result.toString();
	}
		
}
} //NumberLiteralImpl
