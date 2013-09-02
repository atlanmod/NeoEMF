/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Expression;
import DOM.SingleMemberAnnotation;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Single Member Annotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.SingleMemberAnnotationImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SingleMemberAnnotationImpl extends AnnotationImpl implements SingleMemberAnnotation {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//AnnotationImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SingleMemberAnnotationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.SINGLE_MEMBER_ANNOTATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getValue() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataSingleMemberAnnotation))
			data = new DataSingleMemberAnnotation();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.SINGLE_MEMBER_ANNOTATION__VALUE, null, null));
		return ((DataSingleMemberAnnotation)data).value;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValue(Expression newValue, NotificationChain msgs) {
	
		if (data==null) data =  new DataSingleMemberAnnotation();
	
		Expression oldValue = ((DataSingleMemberAnnotation)data).value;
		((DataSingleMemberAnnotation)data).value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.SINGLE_MEMBER_ANNOTATION__VALUE, oldValue, newValue);
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
	public void setValue(Expression newValue) {
	
		if (data==null) data =  new DataSingleMemberAnnotation();
		
		else if (!(data instanceof DataSingleMemberAnnotation)) data = new DataSingleMemberAnnotation((DataAnnotation)data);
	
		if (newValue != ((DataSingleMemberAnnotation)data).value) {
			NotificationChain msgs = null;
			if (((DataSingleMemberAnnotation)data).value != null)
				msgs = ((InternalEObject) ((DataSingleMemberAnnotation)data).value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SINGLE_MEMBER_ANNOTATION__VALUE, null, msgs);
			if (newValue != null)
				msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SINGLE_MEMBER_ANNOTATION__VALUE, null, msgs);
			msgs = basicSetValue(newValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.SINGLE_MEMBER_ANNOTATION__VALUE, newValue, newValue));
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
			case DOMPackage.SINGLE_MEMBER_ANNOTATION__VALUE:
				return basicSetValue(null, msgs);
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
			case DOMPackage.SINGLE_MEMBER_ANNOTATION__VALUE:
				return getValue();
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
			case DOMPackage.SINGLE_MEMBER_ANNOTATION__VALUE:
				setValue((Expression)newValue);
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
			case DOMPackage.SINGLE_MEMBER_ANNOTATION__VALUE:
				setValue((Expression)null);
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
			case DOMPackage.SINGLE_MEMBER_ANNOTATION__VALUE:
				return getValue() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataSingleMemberAnnotation extends DataAnnotation {


	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected Expression value;

	/**
	 *Constructor of DataSingleMemberAnnotation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSingleMemberAnnotation() {
		super();
	}
	
		
	/**
	 *Constructor of DataSingleMemberAnnotation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Annotation }
	 * @generated
	 */
	public DataSingleMemberAnnotation(DataAnnotation data) {
		super();		
		
		typeName = data.typeName;
				
				
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
} //SingleMemberAnnotationImpl
