/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.BodyDeclaration;
import DOM.DOMPackage;
import DOM.ExtendedModifier;
import DOM.Javadoc;

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
 * An implementation of the model object '<em><b>Body Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.BodyDeclarationImpl#getModifiers <em>Modifiers</em>}</li>
 *   <li>{@link DOM.impl.BodyDeclarationImpl#getJavadoc <em>Javadoc</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BodyDeclarationImpl extends ASTNodeImpl implements BodyDeclaration {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ASTNodeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BodyDeclarationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.BODY_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExtendedModifier> getModifiers() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataBodyDeclaration))
			data = new DataBodyDeclaration();
				
	   
		if (((DataBodyDeclaration)data).modifiers == null) {
			((DataBodyDeclaration)data).modifiers = new EObjectContainmentEList<ExtendedModifier>(ExtendedModifier.class, this, DOMPackage.BODY_DECLARATION__MODIFIERS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.BODY_DECLARATION__MODIFIERS);			
		}
		return ((DataBodyDeclaration)data).modifiers;	
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
		if ( data == null || !(data instanceof DataBodyDeclaration))
			data = new DataBodyDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.BODY_DECLARATION__JAVADOC, null, null));
		return ((DataBodyDeclaration)data).javadoc;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJavadoc(Javadoc newJavadoc, NotificationChain msgs) {
	
		if (data==null) data =  new DataBodyDeclaration();
	
		Javadoc oldJavadoc = ((DataBodyDeclaration)data).javadoc;
		((DataBodyDeclaration)data).javadoc = newJavadoc;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.BODY_DECLARATION__JAVADOC, oldJavadoc, newJavadoc);
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
	
		if (data==null) data =  new DataBodyDeclaration();
		
		else if (!(data instanceof DataBodyDeclaration)) data = new DataBodyDeclaration((DataASTNode)data);
	
		if (newJavadoc != ((DataBodyDeclaration)data).javadoc) {
			NotificationChain msgs = null;
			if (((DataBodyDeclaration)data).javadoc != null)
				msgs = ((InternalEObject) ((DataBodyDeclaration)data).javadoc).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.BODY_DECLARATION__JAVADOC, null, msgs);
			if (newJavadoc != null)
				msgs = ((InternalEObject)newJavadoc).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.BODY_DECLARATION__JAVADOC, null, msgs);
			msgs = basicSetJavadoc(newJavadoc, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.BODY_DECLARATION__JAVADOC, newJavadoc, newJavadoc));
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
			case DOMPackage.BODY_DECLARATION__MODIFIERS:
				return ((InternalEList<?>)getModifiers()).basicRemove(otherEnd, msgs);
			case DOMPackage.BODY_DECLARATION__JAVADOC:
				return basicSetJavadoc(null, msgs);
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
			case DOMPackage.BODY_DECLARATION__MODIFIERS:
				return getModifiers();
			case DOMPackage.BODY_DECLARATION__JAVADOC:
				return getJavadoc();
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
			case DOMPackage.BODY_DECLARATION__MODIFIERS:
				getModifiers().clear();
				getModifiers().addAll((Collection<? extends ExtendedModifier>)newValue);
				return;
			case DOMPackage.BODY_DECLARATION__JAVADOC:
				setJavadoc((Javadoc)newValue);
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
			case DOMPackage.BODY_DECLARATION__MODIFIERS:
				getModifiers().clear();
				return;
			case DOMPackage.BODY_DECLARATION__JAVADOC:
				setJavadoc((Javadoc)null);
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
			case DOMPackage.BODY_DECLARATION__MODIFIERS:
				return getModifiers() != null && !getModifiers().isEmpty();
			case DOMPackage.BODY_DECLARATION__JAVADOC:
				return getJavadoc() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataBodyDeclaration extends DataASTNode {


	/**
	 * The cached value of the '{@link #getModifiers() <em>Modifiers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifiers()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtendedModifier> modifiers;

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
	 *Constructor of DataBodyDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataBodyDeclaration() {
		super();
	}
	
		
	/**
	 *Constructor of DataBodyDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	public DataBodyDeclaration(DataASTNode data) {
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
} //BodyDeclarationImpl
