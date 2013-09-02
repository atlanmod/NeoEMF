/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.ImportDeclaration;
import DOM.Name;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.ImportDeclarationImpl#getOnDemand <em>On Demand</em>}</li>
 *   <li>{@link DOM.impl.ImportDeclarationImpl#getStatic <em>Static</em>}</li>
 *   <li>{@link DOM.impl.ImportDeclarationImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImportDeclarationImpl extends ASTNodeImpl implements ImportDeclaration {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ASTNodeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImportDeclarationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.IMPORT_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getOnDemand() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataImportDeclaration))
			data = new DataImportDeclaration();
			
		if (((DataImportDeclaration)data).onDemand == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.IMPORT_DECLARATION__ON_DEMAND, null, null));
		return ((DataImportDeclaration)data).onDemand;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnDemand(Boolean newOnDemand) {
	
		if (data==null) data =  new DataImportDeclaration();
		
		else if (!(data instanceof DataImportDeclaration)) data = new DataImportDeclaration((DataASTNode)data);
	
		Boolean oldOnDemand = ((DataImportDeclaration)data).onDemand;
		((DataImportDeclaration)data).onDemand = newOnDemand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.IMPORT_DECLARATION__ON_DEMAND, oldOnDemand, ((DataImportDeclaration)data).onDemand));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getStatic() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataImportDeclaration))
			data = new DataImportDeclaration();
			
		if (((DataImportDeclaration)data).static_ == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.IMPORT_DECLARATION__STATIC, null, null));
		return ((DataImportDeclaration)data).static_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(Boolean newStatic) {
	
		if (data==null) data =  new DataImportDeclaration();
		
		else if (!(data instanceof DataImportDeclaration)) data = new DataImportDeclaration((DataASTNode)data);
	
		Boolean oldStatic = ((DataImportDeclaration)data).static_;
		((DataImportDeclaration)data).static_ = newStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.IMPORT_DECLARATION__STATIC, oldStatic, ((DataImportDeclaration)data).static_));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Name getName() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataImportDeclaration))
			data = new DataImportDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.IMPORT_DECLARATION__NAME, null, null));
		return ((DataImportDeclaration)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(Name newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataImportDeclaration();
	
		Name oldName = ((DataImportDeclaration)data).name;
		((DataImportDeclaration)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.IMPORT_DECLARATION__NAME, oldName, newName);
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
	public void setName(Name newName) {
	
		if (data==null) data =  new DataImportDeclaration();
		
		else if (!(data instanceof DataImportDeclaration)) data = new DataImportDeclaration((DataASTNode)data);
	
		if (newName != ((DataImportDeclaration)data).name) {
			NotificationChain msgs = null;
			if (((DataImportDeclaration)data).name != null)
				msgs = ((InternalEObject) ((DataImportDeclaration)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.IMPORT_DECLARATION__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.IMPORT_DECLARATION__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.IMPORT_DECLARATION__NAME, newName, newName));
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
			case DOMPackage.IMPORT_DECLARATION__NAME:
				return basicSetName(null, msgs);
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
			case DOMPackage.IMPORT_DECLARATION__ON_DEMAND:
				return getOnDemand();
			case DOMPackage.IMPORT_DECLARATION__STATIC:
				return getStatic();
			case DOMPackage.IMPORT_DECLARATION__NAME:
				return getName();
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
			case DOMPackage.IMPORT_DECLARATION__ON_DEMAND:
				setOnDemand((Boolean)newValue);
				return;
			case DOMPackage.IMPORT_DECLARATION__STATIC:
				setStatic((Boolean)newValue);
				return;
			case DOMPackage.IMPORT_DECLARATION__NAME:
				setName((Name)newValue);
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
			case DOMPackage.IMPORT_DECLARATION__ON_DEMAND:
				setOnDemand(DataImportDeclaration.ON_DEMAND_EDEFAULT);
				return;
			case DOMPackage.IMPORT_DECLARATION__STATIC:
				setStatic(DataImportDeclaration.STATIC_EDEFAULT);
				return;
			case DOMPackage.IMPORT_DECLARATION__NAME:
				setName((Name)null);
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
			case DOMPackage.IMPORT_DECLARATION__ON_DEMAND:
				return DataImportDeclaration.ON_DEMAND_EDEFAULT == null ? getOnDemand() != null : !DataImportDeclaration.ON_DEMAND_EDEFAULT.equals(getOnDemand());
			case DOMPackage.IMPORT_DECLARATION__STATIC:
				return DataImportDeclaration.STATIC_EDEFAULT == null ? getStatic() != null : !DataImportDeclaration.STATIC_EDEFAULT.equals(getStatic());
			case DOMPackage.IMPORT_DECLARATION__NAME:
				return getName() != null;
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
		if (data != null) result.append(((DataImportDeclaration)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataImportDeclaration extends DataASTNode {


	/**
	 * The default value of the '{@link #getOnDemand() <em>On Demand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnDemand()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ON_DEMAND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnDemand() <em>On Demand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnDemand()
	 * @generated
	 * @ordered
	 */
	protected Boolean onDemand = ON_DEMAND_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatic()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean STATIC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatic()
	 * @generated
	 * @ordered
	 */
	protected Boolean static_ = STATIC_EDEFAULT;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected Name name;

	/**
	 *Constructor of DataImportDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataImportDeclaration() {
		super();
	}
	
		
	/**
	 *Constructor of DataImportDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	public DataImportDeclaration(DataASTNode data) {
		super();		
		
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (onDemand: ");
		result.append(onDemand);
		result.append(", static: ");
		result.append(static_);
		result.append(')');
		return result.toString();
	}
		
}
} //ImportDeclarationImpl
