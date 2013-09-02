/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.SimpleName;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simple Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.SimpleNameImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link DOM.impl.SimpleNameImpl#getDeclaration <em>Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SimpleNameImpl extends NameImpl implements SimpleName {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//NameImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SimpleNameImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.SIMPLE_NAME;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdentifier() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataSimpleName))
			data = new DataSimpleName();
			
		if (((DataSimpleName)data).identifier == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.SIMPLE_NAME__IDENTIFIER, null, null));
		return ((DataSimpleName)data).identifier;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentifier(String newIdentifier) {
	
		if (data==null) data =  new DataSimpleName();
		
		else if (!(data instanceof DataSimpleName)) data = new DataSimpleName((DataName)data);
	
		String oldIdentifier = ((DataSimpleName)data).identifier;
		((DataSimpleName)data).identifier = newIdentifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.SIMPLE_NAME__IDENTIFIER, oldIdentifier, ((DataSimpleName)data).identifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getDeclaration() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataSimpleName))
			data = new DataSimpleName();
			
		if (((DataSimpleName)data).declaration == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.SIMPLE_NAME__DECLARATION, null, null));
		return ((DataSimpleName)data).declaration;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaration(Boolean newDeclaration) {
	
		if (data==null) data =  new DataSimpleName();
		
		else if (!(data instanceof DataSimpleName)) data = new DataSimpleName((DataName)data);
	
		Boolean oldDeclaration = ((DataSimpleName)data).declaration;
		((DataSimpleName)data).declaration = newDeclaration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.SIMPLE_NAME__DECLARATION, oldDeclaration, ((DataSimpleName)data).declaration));
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
			case DOMPackage.SIMPLE_NAME__IDENTIFIER:
				return getIdentifier();
			case DOMPackage.SIMPLE_NAME__DECLARATION:
				return getDeclaration();
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
			case DOMPackage.SIMPLE_NAME__IDENTIFIER:
				setIdentifier((String)newValue);
				return;
			case DOMPackage.SIMPLE_NAME__DECLARATION:
				setDeclaration((Boolean)newValue);
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
			case DOMPackage.SIMPLE_NAME__IDENTIFIER:
				setIdentifier(DataSimpleName.IDENTIFIER_EDEFAULT);
				return;
			case DOMPackage.SIMPLE_NAME__DECLARATION:
				setDeclaration(DataSimpleName.DECLARATION_EDEFAULT);
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
			case DOMPackage.SIMPLE_NAME__IDENTIFIER:
				return DataSimpleName.IDENTIFIER_EDEFAULT == null ? getIdentifier() != null : !DataSimpleName.IDENTIFIER_EDEFAULT.equals(getIdentifier());
			case DOMPackage.SIMPLE_NAME__DECLARATION:
				return DataSimpleName.DECLARATION_EDEFAULT == null ? getDeclaration() != null : !DataSimpleName.DECLARATION_EDEFAULT.equals(getDeclaration());
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
		if (data != null) result.append(((DataSimpleName)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataSimpleName extends DataName {


	/**
	 * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String identifier = IDENTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeclaration() <em>Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaration()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean DECLARATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDeclaration() <em>Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaration()
	 * @generated
	 * @ordered
	 */
	protected Boolean declaration = DECLARATION_EDEFAULT;

	/**
	 *Constructor of DataSimpleName
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSimpleName() {
		super();
	}
	
		
	/**
	 *Constructor of DataSimpleName
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Name }
	 * @generated
	 */
	public DataSimpleName(DataName data) {
		super();		
		
		fullyQualifiedName = data.fullyQualifiedName;
				
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (identifier: ");
		result.append(identifier);
		result.append(", declaration: ");
		result.append(declaration);
		result.append(')');
		return result.toString();
	}
		
}
} //SimpleNameImpl
