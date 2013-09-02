/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.AnnotationTypeMemberDeclaration;
import DOM.DOMPackage;
import DOM.Expression;
import DOM.SimpleName;
import DOM.Type;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Annotation Type Member Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.AnnotationTypeMemberDeclarationImpl#getDefault <em>Default</em>}</li>
 *   <li>{@link DOM.impl.AnnotationTypeMemberDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link DOM.impl.AnnotationTypeMemberDeclarationImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnnotationTypeMemberDeclarationImpl extends BodyDeclarationImpl implements AnnotationTypeMemberDeclaration {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//BodyDeclarationImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnnotationTypeMemberDeclarationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ANNOTATION_TYPE_MEMBER_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getDefault() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataAnnotationTypeMemberDeclaration))
			data = new DataAnnotationTypeMemberDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT, null, null));
		return ((DataAnnotationTypeMemberDeclaration)data).default_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefault(Expression newDefault, NotificationChain msgs) {
	
		if (data==null) data =  new DataAnnotationTypeMemberDeclaration();
	
		Expression oldDefault = ((DataAnnotationTypeMemberDeclaration)data).default_;
		((DataAnnotationTypeMemberDeclaration)data).default_ = newDefault;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT, oldDefault, newDefault);
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
	public void setDefault(Expression newDefault) {
	
		if (data==null) data =  new DataAnnotationTypeMemberDeclaration();
		
		else if (!(data instanceof DataAnnotationTypeMemberDeclaration)) data = new DataAnnotationTypeMemberDeclaration((DataBodyDeclaration)data);
	
		if (newDefault != ((DataAnnotationTypeMemberDeclaration)data).default_) {
			NotificationChain msgs = null;
			if (((DataAnnotationTypeMemberDeclaration)data).default_ != null)
				msgs = ((InternalEObject) ((DataAnnotationTypeMemberDeclaration)data).default_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT, null, msgs);
			if (newDefault != null)
				msgs = ((InternalEObject)newDefault).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT, null, msgs);
			msgs = basicSetDefault(newDefault, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT, newDefault, newDefault));
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
		if ( data == null || !(data instanceof DataAnnotationTypeMemberDeclaration))
			data = new DataAnnotationTypeMemberDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__NAME, null, null));
		return ((DataAnnotationTypeMemberDeclaration)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(SimpleName newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataAnnotationTypeMemberDeclaration();
	
		SimpleName oldName = ((DataAnnotationTypeMemberDeclaration)data).name;
		((DataAnnotationTypeMemberDeclaration)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__NAME, oldName, newName);
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
	
		if (data==null) data =  new DataAnnotationTypeMemberDeclaration();
		
		else if (!(data instanceof DataAnnotationTypeMemberDeclaration)) data = new DataAnnotationTypeMemberDeclaration((DataBodyDeclaration)data);
	
		if (newName != ((DataAnnotationTypeMemberDeclaration)data).name) {
			NotificationChain msgs = null;
			if (((DataAnnotationTypeMemberDeclaration)data).name != null)
				msgs = ((InternalEObject) ((DataAnnotationTypeMemberDeclaration)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getType() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataAnnotationTypeMemberDeclaration))
			data = new DataAnnotationTypeMemberDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE, null, null));
		return ((DataAnnotationTypeMemberDeclaration)data).type;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(Type newType, NotificationChain msgs) {
	
		if (data==null) data =  new DataAnnotationTypeMemberDeclaration();
	
		Type oldType = ((DataAnnotationTypeMemberDeclaration)data).type;
		((DataAnnotationTypeMemberDeclaration)data).type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE, oldType, newType);
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
	public void setType(Type newType) {
	
		if (data==null) data =  new DataAnnotationTypeMemberDeclaration();
		
		else if (!(data instanceof DataAnnotationTypeMemberDeclaration)) data = new DataAnnotationTypeMemberDeclaration((DataBodyDeclaration)data);
	
		if (newType != ((DataAnnotationTypeMemberDeclaration)data).type) {
			NotificationChain msgs = null;
			if (((DataAnnotationTypeMemberDeclaration)data).type != null)
				msgs = ((InternalEObject) ((DataAnnotationTypeMemberDeclaration)data).type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE, newType, newType));
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
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT:
				return basicSetDefault(null, msgs);
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__NAME:
				return basicSetName(null, msgs);
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE:
				return basicSetType(null, msgs);
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
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT:
				return getDefault();
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__NAME:
				return getName();
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE:
				return getType();
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
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT:
				setDefault((Expression)newValue);
				return;
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__NAME:
				setName((SimpleName)newValue);
				return;
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE:
				setType((Type)newValue);
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
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT:
				setDefault((Expression)null);
				return;
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__NAME:
				setName((SimpleName)null);
				return;
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE:
				setType((Type)null);
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
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT:
				return getDefault() != null;
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__NAME:
				return getName() != null;
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE:
				return getType() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataAnnotationTypeMemberDeclaration extends DataBodyDeclaration {


	/**
	 * The cached value of the '{@link #getDefault() <em>Default</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefault()
	 * @generated
	 * @ordered
	 */
	protected Expression default_;

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
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Type type;

	/**
	 *Constructor of DataAnnotationTypeMemberDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataAnnotationTypeMemberDeclaration() {
		super();
	}
	
		
	/**
	 *Constructor of DataAnnotationTypeMemberDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link BodyDeclaration }
	 * @generated
	 */
	public DataAnnotationTypeMemberDeclaration(DataBodyDeclaration data) {
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
		result.append(')');
		return result.toString();
	}
		
}
} //AnnotationTypeMemberDeclarationImpl
