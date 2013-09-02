/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.AnonymousClassDeclaration;
import DOM.DOMPackage;
import DOM.EnumConstantDeclaration;
import DOM.Expression;
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
 * An implementation of the model object '<em><b>Enum Constant Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.EnumConstantDeclarationImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link DOM.impl.EnumConstantDeclarationImpl#getAnonymousClassDeclaration <em>Anonymous Class Declaration</em>}</li>
 *   <li>{@link DOM.impl.EnumConstantDeclarationImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumConstantDeclarationImpl extends BodyDeclarationImpl implements EnumConstantDeclaration {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//BodyDeclarationImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumConstantDeclarationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ENUM_CONSTANT_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getArguments() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataEnumConstantDeclaration))
			data = new DataEnumConstantDeclaration();
				
	   
		if (((DataEnumConstantDeclaration)data).arguments == null) {
			((DataEnumConstantDeclaration)data).arguments = new EObjectContainmentEList<Expression>(Expression.class, this, DOMPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS);			
		}
		return ((DataEnumConstantDeclaration)data).arguments;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnonymousClassDeclaration getAnonymousClassDeclaration() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataEnumConstantDeclaration))
			data = new DataEnumConstantDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION, null, null));
		return ((DataEnumConstantDeclaration)data).anonymousClassDeclaration;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnonymousClassDeclaration(AnonymousClassDeclaration newAnonymousClassDeclaration, NotificationChain msgs) {
	
		if (data==null) data =  new DataEnumConstantDeclaration();
	
		AnonymousClassDeclaration oldAnonymousClassDeclaration = ((DataEnumConstantDeclaration)data).anonymousClassDeclaration;
		((DataEnumConstantDeclaration)data).anonymousClassDeclaration = newAnonymousClassDeclaration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION, oldAnonymousClassDeclaration, newAnonymousClassDeclaration);
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
	public void setAnonymousClassDeclaration(AnonymousClassDeclaration newAnonymousClassDeclaration) {
	
		if (data==null) data =  new DataEnumConstantDeclaration();
		
		else if (!(data instanceof DataEnumConstantDeclaration)) data = new DataEnumConstantDeclaration((DataBodyDeclaration)data);
	
		if (newAnonymousClassDeclaration != ((DataEnumConstantDeclaration)data).anonymousClassDeclaration) {
			NotificationChain msgs = null;
			if (((DataEnumConstantDeclaration)data).anonymousClassDeclaration != null)
				msgs = ((InternalEObject) ((DataEnumConstantDeclaration)data).anonymousClassDeclaration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION, null, msgs);
			if (newAnonymousClassDeclaration != null)
				msgs = ((InternalEObject)newAnonymousClassDeclaration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION, null, msgs);
			msgs = basicSetAnonymousClassDeclaration(newAnonymousClassDeclaration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION, newAnonymousClassDeclaration, newAnonymousClassDeclaration));
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
		if ( data == null || !(data instanceof DataEnumConstantDeclaration))
			data = new DataEnumConstantDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ENUM_CONSTANT_DECLARATION__NAME, null, null));
		return ((DataEnumConstantDeclaration)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(SimpleName newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataEnumConstantDeclaration();
	
		SimpleName oldName = ((DataEnumConstantDeclaration)data).name;
		((DataEnumConstantDeclaration)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ENUM_CONSTANT_DECLARATION__NAME, oldName, newName);
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
	
		if (data==null) data =  new DataEnumConstantDeclaration();
		
		else if (!(data instanceof DataEnumConstantDeclaration)) data = new DataEnumConstantDeclaration((DataBodyDeclaration)data);
	
		if (newName != ((DataEnumConstantDeclaration)data).name) {
			NotificationChain msgs = null;
			if (((DataEnumConstantDeclaration)data).name != null)
				msgs = ((InternalEObject) ((DataEnumConstantDeclaration)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENUM_CONSTANT_DECLARATION__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENUM_CONSTANT_DECLARATION__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ENUM_CONSTANT_DECLARATION__NAME, newName, newName));
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
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS:
				return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION:
				return basicSetAnonymousClassDeclaration(null, msgs);
			case DOMPackage.ENUM_CONSTANT_DECLARATION__NAME:
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
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS:
				return getArguments();
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION:
				return getAnonymousClassDeclaration();
			case DOMPackage.ENUM_CONSTANT_DECLARATION__NAME:
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
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends Expression>)newValue);
				return;
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION:
				setAnonymousClassDeclaration((AnonymousClassDeclaration)newValue);
				return;
			case DOMPackage.ENUM_CONSTANT_DECLARATION__NAME:
				setName((SimpleName)newValue);
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
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS:
				getArguments().clear();
				return;
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION:
				setAnonymousClassDeclaration((AnonymousClassDeclaration)null);
				return;
			case DOMPackage.ENUM_CONSTANT_DECLARATION__NAME:
				setName((SimpleName)null);
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
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS:
				return getArguments() != null && !getArguments().isEmpty();
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION:
				return getAnonymousClassDeclaration() != null;
			case DOMPackage.ENUM_CONSTANT_DECLARATION__NAME:
				return getName() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataEnumConstantDeclaration extends DataBodyDeclaration {


	/**
	 * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> arguments;

	/**
	 * The cached value of the '{@link #getAnonymousClassDeclaration() <em>Anonymous Class Declaration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnonymousClassDeclaration()
	 * @generated
	 * @ordered
	 */
	protected AnonymousClassDeclaration anonymousClassDeclaration;

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
	 *Constructor of DataEnumConstantDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataEnumConstantDeclaration() {
		super();
	}
	
		
	/**
	 *Constructor of DataEnumConstantDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link BodyDeclaration }
	 * @generated
	 */
	public DataEnumConstantDeclaration(DataBodyDeclaration data) {
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
} //EnumConstantDeclarationImpl
