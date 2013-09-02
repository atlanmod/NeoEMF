/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.AbstractTypeDeclaration;
import DOM.BodyDeclaration;
import DOM.DOMPackage;
import DOM.SimpleName;

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
 * An implementation of the model object '<em><b>Abstract Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.AbstractTypeDeclarationImpl#getBodyDeclarations <em>Body Declarations</em>}</li>
 *   <li>{@link DOM.impl.AbstractTypeDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link DOM.impl.AbstractTypeDeclarationImpl#getLocalTypeDeclaration <em>Local Type Declaration</em>}</li>
 *   <li>{@link DOM.impl.AbstractTypeDeclarationImpl#getMemberTypeDeclaration <em>Member Type Declaration</em>}</li>
 *   <li>{@link DOM.impl.AbstractTypeDeclarationImpl#getPackageMemberTypeDeclaration <em>Package Member Type Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractTypeDeclarationImpl extends BodyDeclarationImpl implements AbstractTypeDeclaration {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//BodyDeclarationImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractTypeDeclarationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ABSTRACT_TYPE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BodyDeclaration> getBodyDeclarations() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataAbstractTypeDeclaration))
			data = new DataAbstractTypeDeclaration();
				
	   
		if (((DataAbstractTypeDeclaration)data).bodyDeclarations == null) {
			((DataAbstractTypeDeclaration)data).bodyDeclarations = new EObjectContainmentEList<BodyDeclaration>(BodyDeclaration.class, this, DOMPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS);			
		}
		return ((DataAbstractTypeDeclaration)data).bodyDeclarations;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleName getName() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataAbstractTypeDeclaration))
			data = new DataAbstractTypeDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME, null, null));
		return ((DataAbstractTypeDeclaration)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(SimpleName newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataAbstractTypeDeclaration();
	
		SimpleName oldName = ((DataAbstractTypeDeclaration)data).name;
		((DataAbstractTypeDeclaration)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME, oldName, newName);
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
	public void setName(SimpleName newName) {
	
		if (data==null) data =  new DataAbstractTypeDeclaration();
		
		else if (!(data instanceof DataAbstractTypeDeclaration)) data = new DataAbstractTypeDeclaration((DataBodyDeclaration)data);
	
		if (newName != ((DataAbstractTypeDeclaration)data).name) {
			NotificationChain msgs = null;
			if (((DataAbstractTypeDeclaration)data).name != null)
				msgs = ((InternalEObject) ((DataAbstractTypeDeclaration)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getLocalTypeDeclaration() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataAbstractTypeDeclaration))
			data = new DataAbstractTypeDeclaration();
			
		if (((DataAbstractTypeDeclaration)data).localTypeDeclaration == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION, null, null));
		return ((DataAbstractTypeDeclaration)data).localTypeDeclaration;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalTypeDeclaration(Boolean newLocalTypeDeclaration) {
	
		if (data==null) data =  new DataAbstractTypeDeclaration();
		
		else if (!(data instanceof DataAbstractTypeDeclaration)) data = new DataAbstractTypeDeclaration((DataBodyDeclaration)data);
	
		Boolean oldLocalTypeDeclaration = ((DataAbstractTypeDeclaration)data).localTypeDeclaration;
		((DataAbstractTypeDeclaration)data).localTypeDeclaration = newLocalTypeDeclaration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION, oldLocalTypeDeclaration, ((DataAbstractTypeDeclaration)data).localTypeDeclaration));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getMemberTypeDeclaration() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataAbstractTypeDeclaration))
			data = new DataAbstractTypeDeclaration();
			
		if (((DataAbstractTypeDeclaration)data).memberTypeDeclaration == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION, null, null));
		return ((DataAbstractTypeDeclaration)data).memberTypeDeclaration;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMemberTypeDeclaration(Boolean newMemberTypeDeclaration) {
	
		if (data==null) data =  new DataAbstractTypeDeclaration();
		
		else if (!(data instanceof DataAbstractTypeDeclaration)) data = new DataAbstractTypeDeclaration((DataBodyDeclaration)data);
	
		Boolean oldMemberTypeDeclaration = ((DataAbstractTypeDeclaration)data).memberTypeDeclaration;
		((DataAbstractTypeDeclaration)data).memberTypeDeclaration = newMemberTypeDeclaration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION, oldMemberTypeDeclaration, ((DataAbstractTypeDeclaration)data).memberTypeDeclaration));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getPackageMemberTypeDeclaration() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataAbstractTypeDeclaration))
			data = new DataAbstractTypeDeclaration();
			
		if (((DataAbstractTypeDeclaration)data).packageMemberTypeDeclaration == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION, null, null));
		return ((DataAbstractTypeDeclaration)data).packageMemberTypeDeclaration;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageMemberTypeDeclaration(Boolean newPackageMemberTypeDeclaration) {
	
		if (data==null) data =  new DataAbstractTypeDeclaration();
		
		else if (!(data instanceof DataAbstractTypeDeclaration)) data = new DataAbstractTypeDeclaration((DataBodyDeclaration)data);
	
		Boolean oldPackageMemberTypeDeclaration = ((DataAbstractTypeDeclaration)data).packageMemberTypeDeclaration;
		((DataAbstractTypeDeclaration)data).packageMemberTypeDeclaration = newPackageMemberTypeDeclaration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION, oldPackageMemberTypeDeclaration, ((DataAbstractTypeDeclaration)data).packageMemberTypeDeclaration));
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
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				return ((InternalEList<?>)getBodyDeclarations()).basicRemove(otherEnd, msgs);
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME:
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
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				return getBodyDeclarations();
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME:
				return getName();
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION:
				return getLocalTypeDeclaration();
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION:
				return getMemberTypeDeclaration();
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION:
				return getPackageMemberTypeDeclaration();
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
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				getBodyDeclarations().clear();
				getBodyDeclarations().addAll((Collection<? extends BodyDeclaration>)newValue);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME:
				setName((SimpleName)newValue);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION:
				setLocalTypeDeclaration((Boolean)newValue);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION:
				setMemberTypeDeclaration((Boolean)newValue);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION:
				setPackageMemberTypeDeclaration((Boolean)newValue);
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
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				getBodyDeclarations().clear();
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME:
				setName((SimpleName)null);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION:
				setLocalTypeDeclaration(DataAbstractTypeDeclaration.LOCAL_TYPE_DECLARATION_EDEFAULT);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION:
				setMemberTypeDeclaration(DataAbstractTypeDeclaration.MEMBER_TYPE_DECLARATION_EDEFAULT);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION:
				setPackageMemberTypeDeclaration(DataAbstractTypeDeclaration.PACKAGE_MEMBER_TYPE_DECLARATION_EDEFAULT);
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
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				return getBodyDeclarations() != null && !getBodyDeclarations().isEmpty();
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME:
				return getName() != null;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION:
				return DataAbstractTypeDeclaration.LOCAL_TYPE_DECLARATION_EDEFAULT == null ? getLocalTypeDeclaration() != null : !DataAbstractTypeDeclaration.LOCAL_TYPE_DECLARATION_EDEFAULT.equals(getLocalTypeDeclaration());
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION:
				return DataAbstractTypeDeclaration.MEMBER_TYPE_DECLARATION_EDEFAULT == null ? getMemberTypeDeclaration() != null : !DataAbstractTypeDeclaration.MEMBER_TYPE_DECLARATION_EDEFAULT.equals(getMemberTypeDeclaration());
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION:
				return DataAbstractTypeDeclaration.PACKAGE_MEMBER_TYPE_DECLARATION_EDEFAULT == null ? getPackageMemberTypeDeclaration() != null : !DataAbstractTypeDeclaration.PACKAGE_MEMBER_TYPE_DECLARATION_EDEFAULT.equals(getPackageMemberTypeDeclaration());
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
		if (data != null) result.append(((DataAbstractTypeDeclaration)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataAbstractTypeDeclaration extends DataBodyDeclaration {


	/**
	 * The cached value of the '{@link #getBodyDeclarations() <em>Body Declarations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBodyDeclarations()
	 * @generated
	 * @ordered
	 */
	protected EList<BodyDeclaration> bodyDeclarations;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected SimpleName name;

	/**
	 * The default value of the '{@link #getLocalTypeDeclaration() <em>Local Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalTypeDeclaration()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean LOCAL_TYPE_DECLARATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocalTypeDeclaration() <em>Local Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalTypeDeclaration()
	 * @generated
	 * @ordered
	 */
	protected Boolean localTypeDeclaration = LOCAL_TYPE_DECLARATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMemberTypeDeclaration() <em>Member Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemberTypeDeclaration()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean MEMBER_TYPE_DECLARATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMemberTypeDeclaration() <em>Member Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemberTypeDeclaration()
	 * @generated
	 * @ordered
	 */
	protected Boolean memberTypeDeclaration = MEMBER_TYPE_DECLARATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getPackageMemberTypeDeclaration() <em>Package Member Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageMemberTypeDeclaration()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean PACKAGE_MEMBER_TYPE_DECLARATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageMemberTypeDeclaration() <em>Package Member Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageMemberTypeDeclaration()
	 * @generated
	 * @ordered
	 */
	protected Boolean packageMemberTypeDeclaration = PACKAGE_MEMBER_TYPE_DECLARATION_EDEFAULT;

	/**
	 *Constructor of DataAbstractTypeDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataAbstractTypeDeclaration() {
		super();
	}
	
		
	/**
	 *Constructor of DataAbstractTypeDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link BodyDeclaration }
	 * @generated
	 */
	public DataAbstractTypeDeclaration(DataBodyDeclaration data) {
		super();		
		
		modifiers = data.modifiers;
				
		javadoc = data.javadoc;
				
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (localTypeDeclaration: ");
		result.append(localTypeDeclaration);
		result.append(", memberTypeDeclaration: ");
		result.append(memberTypeDeclaration);
		result.append(", packageMemberTypeDeclaration: ");
		result.append(packageMemberTypeDeclaration);
		result.append(')');
		return result.toString();
	}
		
}
} //AbstractTypeDeclarationImpl
