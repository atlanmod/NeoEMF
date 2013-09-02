/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.IField;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IField</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.IFieldImpl#getConstant <em>Constant</em>}</li>
 *   <li>{@link Core.impl.IFieldImpl#getIsEnumConstant <em>Is Enum Constant</em>}</li>
 *   <li>{@link Core.impl.IFieldImpl#getTypeSignature <em>Type Signature</em>}</li>
 *   <li>{@link Core.impl.IFieldImpl#getIsVolatile <em>Is Volatile</em>}</li>
 *   <li>{@link Core.impl.IFieldImpl#getIsTransient <em>Is Transient</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IFieldImpl extends IMemberImpl implements IField {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//IMemberImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IFieldImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IFIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConstant() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIField))
			data = new DataIField();
			
		if (((DataIField)data).constant == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IFIELD__CONSTANT, null, null));
		return ((DataIField)data).constant;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstant(String newConstant) {
	
		if (data==null) data =  new DataIField();
		
		else if (!(data instanceof DataIField)) data = new DataIField((DataIMember)data);
	
		String oldConstant = ((DataIField)data).constant;
		((DataIField)data).constant = newConstant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IFIELD__CONSTANT, oldConstant, ((DataIField)data).constant));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsEnumConstant() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIField))
			data = new DataIField();
			
		if (((DataIField)data).isEnumConstant == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IFIELD__IS_ENUM_CONSTANT, null, null));
		return ((DataIField)data).isEnumConstant;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEnumConstant(Boolean newIsEnumConstant) {
	
		if (data==null) data =  new DataIField();
		
		else if (!(data instanceof DataIField)) data = new DataIField((DataIMember)data);
	
		Boolean oldIsEnumConstant = ((DataIField)data).isEnumConstant;
		((DataIField)data).isEnumConstant = newIsEnumConstant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IFIELD__IS_ENUM_CONSTANT, oldIsEnumConstant, ((DataIField)data).isEnumConstant));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeSignature() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIField))
			data = new DataIField();
			
		if (((DataIField)data).typeSignature == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IFIELD__TYPE_SIGNATURE, null, null));
		return ((DataIField)data).typeSignature;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeSignature(String newTypeSignature) {
	
		if (data==null) data =  new DataIField();
		
		else if (!(data instanceof DataIField)) data = new DataIField((DataIMember)data);
	
		String oldTypeSignature = ((DataIField)data).typeSignature;
		((DataIField)data).typeSignature = newTypeSignature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IFIELD__TYPE_SIGNATURE, oldTypeSignature, ((DataIField)data).typeSignature));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsVolatile() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIField))
			data = new DataIField();
			
		if (((DataIField)data).isVolatile == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IFIELD__IS_VOLATILE, null, null));
		return ((DataIField)data).isVolatile;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsVolatile(Boolean newIsVolatile) {
	
		if (data==null) data =  new DataIField();
		
		else if (!(data instanceof DataIField)) data = new DataIField((DataIMember)data);
	
		Boolean oldIsVolatile = ((DataIField)data).isVolatile;
		((DataIField)data).isVolatile = newIsVolatile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IFIELD__IS_VOLATILE, oldIsVolatile, ((DataIField)data).isVolatile));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsTransient() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIField))
			data = new DataIField();
			
		if (((DataIField)data).isTransient == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IFIELD__IS_TRANSIENT, null, null));
		return ((DataIField)data).isTransient;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsTransient(Boolean newIsTransient) {
	
		if (data==null) data =  new DataIField();
		
		else if (!(data instanceof DataIField)) data = new DataIField((DataIMember)data);
	
		Boolean oldIsTransient = ((DataIField)data).isTransient;
		((DataIField)data).isTransient = newIsTransient;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IFIELD__IS_TRANSIENT, oldIsTransient, ((DataIField)data).isTransient));
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
			case CorePackage.IFIELD__CONSTANT:
				return getConstant();
			case CorePackage.IFIELD__IS_ENUM_CONSTANT:
				return getIsEnumConstant();
			case CorePackage.IFIELD__TYPE_SIGNATURE:
				return getTypeSignature();
			case CorePackage.IFIELD__IS_VOLATILE:
				return getIsVolatile();
			case CorePackage.IFIELD__IS_TRANSIENT:
				return getIsTransient();
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
			case CorePackage.IFIELD__CONSTANT:
				setConstant((String)newValue);
				return;
			case CorePackage.IFIELD__IS_ENUM_CONSTANT:
				setIsEnumConstant((Boolean)newValue);
				return;
			case CorePackage.IFIELD__TYPE_SIGNATURE:
				setTypeSignature((String)newValue);
				return;
			case CorePackage.IFIELD__IS_VOLATILE:
				setIsVolatile((Boolean)newValue);
				return;
			case CorePackage.IFIELD__IS_TRANSIENT:
				setIsTransient((Boolean)newValue);
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
			case CorePackage.IFIELD__CONSTANT:
				setConstant(DataIField.CONSTANT_EDEFAULT);
				return;
			case CorePackage.IFIELD__IS_ENUM_CONSTANT:
				setIsEnumConstant(DataIField.IS_ENUM_CONSTANT_EDEFAULT);
				return;
			case CorePackage.IFIELD__TYPE_SIGNATURE:
				setTypeSignature(DataIField.TYPE_SIGNATURE_EDEFAULT);
				return;
			case CorePackage.IFIELD__IS_VOLATILE:
				setIsVolatile(DataIField.IS_VOLATILE_EDEFAULT);
				return;
			case CorePackage.IFIELD__IS_TRANSIENT:
				setIsTransient(DataIField.IS_TRANSIENT_EDEFAULT);
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
			case CorePackage.IFIELD__CONSTANT:
				return DataIField.CONSTANT_EDEFAULT == null ? getConstant() != null : !DataIField.CONSTANT_EDEFAULT.equals(getConstant());
			case CorePackage.IFIELD__IS_ENUM_CONSTANT:
				return DataIField.IS_ENUM_CONSTANT_EDEFAULT == null ? getIsEnumConstant() != null : !DataIField.IS_ENUM_CONSTANT_EDEFAULT.equals(getIsEnumConstant());
			case CorePackage.IFIELD__TYPE_SIGNATURE:
				return DataIField.TYPE_SIGNATURE_EDEFAULT == null ? getTypeSignature() != null : !DataIField.TYPE_SIGNATURE_EDEFAULT.equals(getTypeSignature());
			case CorePackage.IFIELD__IS_VOLATILE:
				return DataIField.IS_VOLATILE_EDEFAULT == null ? getIsVolatile() != null : !DataIField.IS_VOLATILE_EDEFAULT.equals(getIsVolatile());
			case CorePackage.IFIELD__IS_TRANSIENT:
				return DataIField.IS_TRANSIENT_EDEFAULT == null ? getIsTransient() != null : !DataIField.IS_TRANSIENT_EDEFAULT.equals(getIsTransient());
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
		if (data != null) result.append(((DataIField)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataIField extends DataIMember {


	/**
	 * The default value of the '{@link #getConstant() <em>Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstant()
	 * @generated
	 * @ordered
	 */
	protected static final String CONSTANT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConstant() <em>Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstant()
	 * @generated
	 * @ordered
	 */
	protected String constant = CONSTANT_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsEnumConstant() <em>Is Enum Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsEnumConstant()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_ENUM_CONSTANT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsEnumConstant() <em>Is Enum Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsEnumConstant()
	 * @generated
	 * @ordered
	 */
	protected Boolean isEnumConstant = IS_ENUM_CONSTANT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTypeSignature() <em>Type Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeSignature()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_SIGNATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTypeSignature() <em>Type Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeSignature()
	 * @generated
	 * @ordered
	 */
	protected String typeSignature = TYPE_SIGNATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsVolatile() <em>Is Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsVolatile()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_VOLATILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsVolatile() <em>Is Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsVolatile()
	 * @generated
	 * @ordered
	 */
	protected Boolean isVolatile = IS_VOLATILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsTransient() <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsTransient()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_TRANSIENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsTransient() <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsTransient()
	 * @generated
	 * @ordered
	 */
	protected Boolean isTransient = IS_TRANSIENT_EDEFAULT;

	/**
	 *Constructor of DataIField
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataIField() {
		super();
	}
	
		
	/**
	 *Constructor of DataIField
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link IMember }
	 * @generated
	 */
	public DataIField(DataIMember data) {
		super();		
		
		source = data.source;
				
		sourceRange = data.sourceRange;
				
		javadocRange = data.javadocRange;
				
		nameRange = data.nameRange;
				
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (constant: ");
		result.append(constant);
		result.append(", isEnumConstant: ");
		result.append(isEnumConstant);
		result.append(", typeSignature: ");
		result.append(typeSignature);
		result.append(", isVolatile: ");
		result.append(isVolatile);
		result.append(", isTransient: ");
		result.append(isTransient);
		result.append(')');
		return result.toString();
	}
		
}
} //IFieldImpl
