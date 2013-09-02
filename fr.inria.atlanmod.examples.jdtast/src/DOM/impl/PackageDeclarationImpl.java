/**
 *
 * $Id$
 */
package DOM.impl;

import Core.IPackageFragment;

import DOM.Annotation;
import DOM.DOMPackage;
import DOM.Javadoc;
import DOM.Name;
import DOM.PackageDeclaration;

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
 * An implementation of the model object '<em><b>Package Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.PackageDeclarationImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link DOM.impl.PackageDeclarationImpl#getJavadoc <em>Javadoc</em>}</li>
 *   <li>{@link DOM.impl.PackageDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link DOM.impl.PackageDeclarationImpl#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageDeclarationImpl extends ASTNodeImpl implements PackageDeclaration {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ASTNodeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageDeclarationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.PACKAGE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Annotation> getAnnotations() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataPackageDeclaration))
			data = new DataPackageDeclaration();
				
	   
		if (((DataPackageDeclaration)data).annotations == null) {
			((DataPackageDeclaration)data).annotations = new EObjectContainmentEList<Annotation>(Annotation.class, this, DOMPackage.PACKAGE_DECLARATION__ANNOTATIONS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.PACKAGE_DECLARATION__ANNOTATIONS);			
		}
		return ((DataPackageDeclaration)data).annotations;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Javadoc getJavadoc() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataPackageDeclaration))
			data = new DataPackageDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.PACKAGE_DECLARATION__JAVADOC, null, null));
		return ((DataPackageDeclaration)data).javadoc;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJavadoc(Javadoc newJavadoc, NotificationChain msgs) {
	
		if (data==null) data =  new DataPackageDeclaration();
	
		Javadoc oldJavadoc = ((DataPackageDeclaration)data).javadoc;
		((DataPackageDeclaration)data).javadoc = newJavadoc;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.PACKAGE_DECLARATION__JAVADOC, oldJavadoc, newJavadoc);
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
	public void setJavadoc(Javadoc newJavadoc) {
	
		if (data==null) data =  new DataPackageDeclaration();
		
		else if (!(data instanceof DataPackageDeclaration)) data = new DataPackageDeclaration((DataASTNode)data);
	
		if (newJavadoc != ((DataPackageDeclaration)data).javadoc) {
			NotificationChain msgs = null;
			if (((DataPackageDeclaration)data).javadoc != null)
				msgs = ((InternalEObject) ((DataPackageDeclaration)data).javadoc).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.PACKAGE_DECLARATION__JAVADOC, null, msgs);
			if (newJavadoc != null)
				msgs = ((InternalEObject)newJavadoc).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.PACKAGE_DECLARATION__JAVADOC, null, msgs);
			msgs = basicSetJavadoc(newJavadoc, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.PACKAGE_DECLARATION__JAVADOC, newJavadoc, newJavadoc));
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
		if ( data == null || !(data instanceof DataPackageDeclaration))
			data = new DataPackageDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.PACKAGE_DECLARATION__NAME, null, null));
		return ((DataPackageDeclaration)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(Name newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataPackageDeclaration();
	
		Name oldName = ((DataPackageDeclaration)data).name;
		((DataPackageDeclaration)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.PACKAGE_DECLARATION__NAME, oldName, newName);
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
	
		if (data==null) data =  new DataPackageDeclaration();
		
		else if (!(data instanceof DataPackageDeclaration)) data = new DataPackageDeclaration((DataASTNode)data);
	
		if (newName != ((DataPackageDeclaration)data).name) {
			NotificationChain msgs = null;
			if (((DataPackageDeclaration)data).name != null)
				msgs = ((InternalEObject) ((DataPackageDeclaration)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.PACKAGE_DECLARATION__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.PACKAGE_DECLARATION__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.PACKAGE_DECLARATION__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IPackageFragment getBinding() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataPackageDeclaration))
			data = new DataPackageDeclaration();
				
	  
		if (((DataPackageDeclaration)data).binding == null && getNodeId()>-1) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.PACKAGE_DECLARATION__BINDING);
		}		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.PACKAGE_DECLARATION__BINDING, null, null));
		return ((DataPackageDeclaration)data).binding;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX8
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IPackageFragment basicGetBinding() {
		return data != null ? ((DataPackageDeclaration)data).binding : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(IPackageFragment newBinding) {
	
		if (data==null) data =  new DataPackageDeclaration();
		
		else if (!(data instanceof DataPackageDeclaration)) data = new DataPackageDeclaration((DataASTNode)data);
	
		IPackageFragment oldBinding = ((DataPackageDeclaration)data).binding;
		((DataPackageDeclaration)data).binding = newBinding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.PACKAGE_DECLARATION__BINDING, oldBinding, ((DataPackageDeclaration)data).binding));
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
			case DOMPackage.PACKAGE_DECLARATION__ANNOTATIONS:
				return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
			case DOMPackage.PACKAGE_DECLARATION__JAVADOC:
				return basicSetJavadoc(null, msgs);
			case DOMPackage.PACKAGE_DECLARATION__NAME:
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
			case DOMPackage.PACKAGE_DECLARATION__ANNOTATIONS:
				return getAnnotations();
			case DOMPackage.PACKAGE_DECLARATION__JAVADOC:
				return getJavadoc();
			case DOMPackage.PACKAGE_DECLARATION__NAME:
				return getName();
			case DOMPackage.PACKAGE_DECLARATION__BINDING:
				if (resolve) return getBinding();
				return basicGetBinding();
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
			case DOMPackage.PACKAGE_DECLARATION__ANNOTATIONS:
				getAnnotations().clear();
				getAnnotations().addAll((Collection<? extends Annotation>)newValue);
				return;
			case DOMPackage.PACKAGE_DECLARATION__JAVADOC:
				setJavadoc((Javadoc)newValue);
				return;
			case DOMPackage.PACKAGE_DECLARATION__NAME:
				setName((Name)newValue);
				return;
			case DOMPackage.PACKAGE_DECLARATION__BINDING:
				setBinding((IPackageFragment)newValue);
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
			case DOMPackage.PACKAGE_DECLARATION__ANNOTATIONS:
				getAnnotations().clear();
				return;
			case DOMPackage.PACKAGE_DECLARATION__JAVADOC:
				setJavadoc((Javadoc)null);
				return;
			case DOMPackage.PACKAGE_DECLARATION__NAME:
				setName((Name)null);
				return;
			case DOMPackage.PACKAGE_DECLARATION__BINDING:
				setBinding((IPackageFragment)null);
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
			case DOMPackage.PACKAGE_DECLARATION__ANNOTATIONS:
				return getAnnotations() != null && !getAnnotations().isEmpty();
			case DOMPackage.PACKAGE_DECLARATION__JAVADOC:
				return getJavadoc() != null;
			case DOMPackage.PACKAGE_DECLARATION__NAME:
				return getName() != null;
			case DOMPackage.PACKAGE_DECLARATION__BINDING:
				return getBinding() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataPackageDeclaration extends DataASTNode {


	/**
	 * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotations()
	 * @generated
	 * @ordered
	 */
	protected EList<Annotation> annotations;

	/**
	 * The cached value of the '{@link #getJavadoc() <em>Javadoc</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavadoc()
	 * @generated
	 * @ordered
	 */
	protected Javadoc javadoc;

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
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected IPackageFragment binding;

	/**
	 *Constructor of DataPackageDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataPackageDeclaration() {
		super();
	}
	
		
	/**
	 *Constructor of DataPackageDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	public DataPackageDeclaration(DataASTNode data) {
		super();		
		
				
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
} //PackageDeclarationImpl
