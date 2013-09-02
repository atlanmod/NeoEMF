/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Modifier;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Modifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.ModifierImpl#getAbstract <em>Abstract</em>}</li>
 *   <li>{@link DOM.impl.ModifierImpl#getFinal <em>Final</em>}</li>
 *   <li>{@link DOM.impl.ModifierImpl#getNative <em>Native</em>}</li>
 *   <li>{@link DOM.impl.ModifierImpl#getNone <em>None</em>}</li>
 *   <li>{@link DOM.impl.ModifierImpl#getPrivate <em>Private</em>}</li>
 *   <li>{@link DOM.impl.ModifierImpl#getProtected <em>Protected</em>}</li>
 *   <li>{@link DOM.impl.ModifierImpl#getPublic <em>Public</em>}</li>
 *   <li>{@link DOM.impl.ModifierImpl#getStatic <em>Static</em>}</li>
 *   <li>{@link DOM.impl.ModifierImpl#getStrictfp <em>Strictfp</em>}</li>
 *   <li>{@link DOM.impl.ModifierImpl#getSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link DOM.impl.ModifierImpl#getTransient <em>Transient</em>}</li>
 *   <li>{@link DOM.impl.ModifierImpl#getVolatile <em>Volatile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModifierImpl extends ASTNodeImpl implements Modifier {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ASTNodeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModifierImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.MODIFIER;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getAbstract() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataModifier))
			data = new DataModifier();
			
		if (((DataModifier)data).abstract_ == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.MODIFIER__ABSTRACT, null, null));
		return ((DataModifier)data).abstract_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(Boolean newAbstract) {
	
		if (data==null) data =  new DataModifier();
		
		else if (!(data instanceof DataModifier)) data = new DataModifier((DataASTNode)data);
	
		Boolean oldAbstract = ((DataModifier)data).abstract_;
		((DataModifier)data).abstract_ = newAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__ABSTRACT, oldAbstract, ((DataModifier)data).abstract_));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getFinal() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataModifier))
			data = new DataModifier();
			
		if (((DataModifier)data).final_ == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.MODIFIER__FINAL, null, null));
		return ((DataModifier)data).final_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinal(Boolean newFinal) {
	
		if (data==null) data =  new DataModifier();
		
		else if (!(data instanceof DataModifier)) data = new DataModifier((DataASTNode)data);
	
		Boolean oldFinal = ((DataModifier)data).final_;
		((DataModifier)data).final_ = newFinal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__FINAL, oldFinal, ((DataModifier)data).final_));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getNative() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataModifier))
			data = new DataModifier();
			
		if (((DataModifier)data).native_ == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.MODIFIER__NATIVE, null, null));
		return ((DataModifier)data).native_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNative(Boolean newNative) {
	
		if (data==null) data =  new DataModifier();
		
		else if (!(data instanceof DataModifier)) data = new DataModifier((DataASTNode)data);
	
		Boolean oldNative = ((DataModifier)data).native_;
		((DataModifier)data).native_ = newNative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__NATIVE, oldNative, ((DataModifier)data).native_));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getNone() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataModifier))
			data = new DataModifier();
			
		if (((DataModifier)data).none == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.MODIFIER__NONE, null, null));
		return ((DataModifier)data).none;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNone(Boolean newNone) {
	
		if (data==null) data =  new DataModifier();
		
		else if (!(data instanceof DataModifier)) data = new DataModifier((DataASTNode)data);
	
		Boolean oldNone = ((DataModifier)data).none;
		((DataModifier)data).none = newNone;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__NONE, oldNone, ((DataModifier)data).none));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getPrivate() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataModifier))
			data = new DataModifier();
			
		if (((DataModifier)data).private_ == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.MODIFIER__PRIVATE, null, null));
		return ((DataModifier)data).private_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrivate(Boolean newPrivate) {
	
		if (data==null) data =  new DataModifier();
		
		else if (!(data instanceof DataModifier)) data = new DataModifier((DataASTNode)data);
	
		Boolean oldPrivate = ((DataModifier)data).private_;
		((DataModifier)data).private_ = newPrivate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__PRIVATE, oldPrivate, ((DataModifier)data).private_));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getProtected() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataModifier))
			data = new DataModifier();
			
		if (((DataModifier)data).protected_ == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.MODIFIER__PROTECTED, null, null));
		return ((DataModifier)data).protected_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProtected(Boolean newProtected) {
	
		if (data==null) data =  new DataModifier();
		
		else if (!(data instanceof DataModifier)) data = new DataModifier((DataASTNode)data);
	
		Boolean oldProtected = ((DataModifier)data).protected_;
		((DataModifier)data).protected_ = newProtected;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__PROTECTED, oldProtected, ((DataModifier)data).protected_));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getPublic() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataModifier))
			data = new DataModifier();
			
		if (((DataModifier)data).public_ == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.MODIFIER__PUBLIC, null, null));
		return ((DataModifier)data).public_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPublic(Boolean newPublic) {
	
		if (data==null) data =  new DataModifier();
		
		else if (!(data instanceof DataModifier)) data = new DataModifier((DataASTNode)data);
	
		Boolean oldPublic = ((DataModifier)data).public_;
		((DataModifier)data).public_ = newPublic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__PUBLIC, oldPublic, ((DataModifier)data).public_));
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
		if ( data == null || !(data instanceof DataModifier))
			data = new DataModifier();
			
		if (((DataModifier)data).static_ == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.MODIFIER__STATIC, null, null));
		return ((DataModifier)data).static_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(Boolean newStatic) {
	
		if (data==null) data =  new DataModifier();
		
		else if (!(data instanceof DataModifier)) data = new DataModifier((DataASTNode)data);
	
		Boolean oldStatic = ((DataModifier)data).static_;
		((DataModifier)data).static_ = newStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__STATIC, oldStatic, ((DataModifier)data).static_));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getStrictfp() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataModifier))
			data = new DataModifier();
			
		if (((DataModifier)data).strictfp_ == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.MODIFIER__STRICTFP, null, null));
		return ((DataModifier)data).strictfp_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrictfp(Boolean newStrictfp) {
	
		if (data==null) data =  new DataModifier();
		
		else if (!(data instanceof DataModifier)) data = new DataModifier((DataASTNode)data);
	
		Boolean oldStrictfp = ((DataModifier)data).strictfp_;
		((DataModifier)data).strictfp_ = newStrictfp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__STRICTFP, oldStrictfp, ((DataModifier)data).strictfp_));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getSynchronized() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataModifier))
			data = new DataModifier();
			
		if (((DataModifier)data).synchronized_ == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.MODIFIER__SYNCHRONIZED, null, null));
		return ((DataModifier)data).synchronized_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSynchronized(Boolean newSynchronized) {
	
		if (data==null) data =  new DataModifier();
		
		else if (!(data instanceof DataModifier)) data = new DataModifier((DataASTNode)data);
	
		Boolean oldSynchronized = ((DataModifier)data).synchronized_;
		((DataModifier)data).synchronized_ = newSynchronized;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__SYNCHRONIZED, oldSynchronized, ((DataModifier)data).synchronized_));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getTransient() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataModifier))
			data = new DataModifier();
			
		if (((DataModifier)data).transient_ == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.MODIFIER__TRANSIENT, null, null));
		return ((DataModifier)data).transient_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransient(Boolean newTransient) {
	
		if (data==null) data =  new DataModifier();
		
		else if (!(data instanceof DataModifier)) data = new DataModifier((DataASTNode)data);
	
		Boolean oldTransient = ((DataModifier)data).transient_;
		((DataModifier)data).transient_ = newTransient;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__TRANSIENT, oldTransient, ((DataModifier)data).transient_));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getVolatile() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataModifier))
			data = new DataModifier();
			
		if (((DataModifier)data).volatile_ == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.MODIFIER__VOLATILE, null, null));
		return ((DataModifier)data).volatile_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVolatile(Boolean newVolatile) {
	
		if (data==null) data =  new DataModifier();
		
		else if (!(data instanceof DataModifier)) data = new DataModifier((DataASTNode)data);
	
		Boolean oldVolatile = ((DataModifier)data).volatile_;
		((DataModifier)data).volatile_ = newVolatile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__VOLATILE, oldVolatile, ((DataModifier)data).volatile_));
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
			case DOMPackage.MODIFIER__ABSTRACT:
				return getAbstract();
			case DOMPackage.MODIFIER__FINAL:
				return getFinal();
			case DOMPackage.MODIFIER__NATIVE:
				return getNative();
			case DOMPackage.MODIFIER__NONE:
				return getNone();
			case DOMPackage.MODIFIER__PRIVATE:
				return getPrivate();
			case DOMPackage.MODIFIER__PROTECTED:
				return getProtected();
			case DOMPackage.MODIFIER__PUBLIC:
				return getPublic();
			case DOMPackage.MODIFIER__STATIC:
				return getStatic();
			case DOMPackage.MODIFIER__STRICTFP:
				return getStrictfp();
			case DOMPackage.MODIFIER__SYNCHRONIZED:
				return getSynchronized();
			case DOMPackage.MODIFIER__TRANSIENT:
				return getTransient();
			case DOMPackage.MODIFIER__VOLATILE:
				return getVolatile();
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
			case DOMPackage.MODIFIER__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__FINAL:
				setFinal((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__NATIVE:
				setNative((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__NONE:
				setNone((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__PRIVATE:
				setPrivate((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__PROTECTED:
				setProtected((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__PUBLIC:
				setPublic((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__STATIC:
				setStatic((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__STRICTFP:
				setStrictfp((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__SYNCHRONIZED:
				setSynchronized((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__TRANSIENT:
				setTransient((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__VOLATILE:
				setVolatile((Boolean)newValue);
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
			case DOMPackage.MODIFIER__ABSTRACT:
				setAbstract(DataModifier.ABSTRACT_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__FINAL:
				setFinal(DataModifier.FINAL_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__NATIVE:
				setNative(DataModifier.NATIVE_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__NONE:
				setNone(DataModifier.NONE_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__PRIVATE:
				setPrivate(DataModifier.PRIVATE_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__PROTECTED:
				setProtected(DataModifier.PROTECTED_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__PUBLIC:
				setPublic(DataModifier.PUBLIC_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__STATIC:
				setStatic(DataModifier.STATIC_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__STRICTFP:
				setStrictfp(DataModifier.STRICTFP_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__SYNCHRONIZED:
				setSynchronized(DataModifier.SYNCHRONIZED_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__TRANSIENT:
				setTransient(DataModifier.TRANSIENT_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__VOLATILE:
				setVolatile(DataModifier.VOLATILE_EDEFAULT);
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
			case DOMPackage.MODIFIER__ABSTRACT:
				return DataModifier.ABSTRACT_EDEFAULT == null ? getAbstract() != null : !DataModifier.ABSTRACT_EDEFAULT.equals(getAbstract());
			case DOMPackage.MODIFIER__FINAL:
				return DataModifier.FINAL_EDEFAULT == null ? getFinal() != null : !DataModifier.FINAL_EDEFAULT.equals(getFinal());
			case DOMPackage.MODIFIER__NATIVE:
				return DataModifier.NATIVE_EDEFAULT == null ? getNative() != null : !DataModifier.NATIVE_EDEFAULT.equals(getNative());
			case DOMPackage.MODIFIER__NONE:
				return DataModifier.NONE_EDEFAULT == null ? getNone() != null : !DataModifier.NONE_EDEFAULT.equals(getNone());
			case DOMPackage.MODIFIER__PRIVATE:
				return DataModifier.PRIVATE_EDEFAULT == null ? getPrivate() != null : !DataModifier.PRIVATE_EDEFAULT.equals(getPrivate());
			case DOMPackage.MODIFIER__PROTECTED:
				return DataModifier.PROTECTED_EDEFAULT == null ? getProtected() != null : !DataModifier.PROTECTED_EDEFAULT.equals(getProtected());
			case DOMPackage.MODIFIER__PUBLIC:
				return DataModifier.PUBLIC_EDEFAULT == null ? getPublic() != null : !DataModifier.PUBLIC_EDEFAULT.equals(getPublic());
			case DOMPackage.MODIFIER__STATIC:
				return DataModifier.STATIC_EDEFAULT == null ? getStatic() != null : !DataModifier.STATIC_EDEFAULT.equals(getStatic());
			case DOMPackage.MODIFIER__STRICTFP:
				return DataModifier.STRICTFP_EDEFAULT == null ? getStrictfp() != null : !DataModifier.STRICTFP_EDEFAULT.equals(getStrictfp());
			case DOMPackage.MODIFIER__SYNCHRONIZED:
				return DataModifier.SYNCHRONIZED_EDEFAULT == null ? getSynchronized() != null : !DataModifier.SYNCHRONIZED_EDEFAULT.equals(getSynchronized());
			case DOMPackage.MODIFIER__TRANSIENT:
				return DataModifier.TRANSIENT_EDEFAULT == null ? getTransient() != null : !DataModifier.TRANSIENT_EDEFAULT.equals(getTransient());
			case DOMPackage.MODIFIER__VOLATILE:
				return DataModifier.VOLATILE_EDEFAULT == null ? getVolatile() != null : !DataModifier.VOLATILE_EDEFAULT.equals(getVolatile());
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
		if (data != null) result.append(((DataModifier)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataModifier extends DataASTNode {


	/**
	 * The default value of the '{@link #getAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ABSTRACT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbstract()
	 * @generated
	 * @ordered
	 */
	protected Boolean abstract_ = ABSTRACT_EDEFAULT;

	/**
	 * The default value of the '{@link #getFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinal()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FINAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinal()
	 * @generated
	 * @ordered
	 */
	protected Boolean final_ = FINAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getNative() <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNative()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean NATIVE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNative() <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNative()
	 * @generated
	 * @ordered
	 */
	protected Boolean native_ = NATIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNone() <em>None</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNone()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean NONE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNone() <em>None</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNone()
	 * @generated
	 * @ordered
	 */
	protected Boolean none = NONE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrivate() <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivate()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean PRIVATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrivate() <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivate()
	 * @generated
	 * @ordered
	 */
	protected Boolean private_ = PRIVATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getProtected() <em>Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProtected()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean PROTECTED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProtected() <em>Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProtected()
	 * @generated
	 * @ordered
	 */
	protected Boolean protected_ = PROTECTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getPublic() <em>Public</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPublic()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean PUBLIC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPublic() <em>Public</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPublic()
	 * @generated
	 * @ordered
	 */
	protected Boolean public_ = PUBLIC_EDEFAULT;

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
	 * The default value of the '{@link #getStrictfp() <em>Strictfp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrictfp()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean STRICTFP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStrictfp() <em>Strictfp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrictfp()
	 * @generated
	 * @ordered
	 */
	protected Boolean strictfp_ = STRICTFP_EDEFAULT;

	/**
	 * The default value of the '{@link #getSynchronized() <em>Synchronized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynchronized()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean SYNCHRONIZED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSynchronized() <em>Synchronized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynchronized()
	 * @generated
	 * @ordered
	 */
	protected Boolean synchronized_ = SYNCHRONIZED_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransient() <em>Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransient()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean TRANSIENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransient() <em>Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransient()
	 * @generated
	 * @ordered
	 */
	protected Boolean transient_ = TRANSIENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getVolatile() <em>Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVolatile()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean VOLATILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVolatile() <em>Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVolatile()
	 * @generated
	 * @ordered
	 */
	protected Boolean volatile_ = VOLATILE_EDEFAULT;

	/**
	 *Constructor of DataModifier
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataModifier() {
		super();
	}
	
		
	/**
	 *Constructor of DataModifier
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	public DataModifier(DataASTNode data) {
		super();		
		
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (abstract: ");
		result.append(abstract_);
		result.append(", final: ");
		result.append(final_);
		result.append(", native: ");
		result.append(native_);
		result.append(", none: ");
		result.append(none);
		result.append(", private: ");
		result.append(private_);
		result.append(", protected: ");
		result.append(protected_);
		result.append(", public: ");
		result.append(public_);
		result.append(", static: ");
		result.append(static_);
		result.append(", strictfp: ");
		result.append(strictfp_);
		result.append(", synchronized: ");
		result.append(synchronized_);
		result.append(", transient: ");
		result.append(transient_);
		result.append(", volatile: ");
		result.append(volatile_);
		result.append(')');
		return result.toString();
	}
		
}
} //ModifierImpl
