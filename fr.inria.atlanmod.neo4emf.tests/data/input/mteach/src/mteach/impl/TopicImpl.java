/**
 *
 * $Id$
 */
package mteach.impl;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

import mteach.Course;
import mteach.MteachPackage;
import mteach.Topic;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Topic</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link mteach.impl.TopicImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link mteach.impl.TopicImpl#getCourse <em>Course</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TopicImpl extends Neo4emfObject implements Topic {

	 
	
	/**
	 * The cached value of the data structure {@link DataTopic <em>data</em> } 
	 * @generated
	 */
	 	protected DataTopic data;
	 
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TopicImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 
	protected DataTopic getData(){
		if ( data == null || !(data instanceof DataTopic)){
			data = new DataTopic();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataTopic) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MteachPackage.Literals.TOPIC;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitle() {	
	  		
		if ( isLoaded()) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, MteachPackage.TOPIC__TITLE, null, null));
		return getData().title;	
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setTitle(String newTitle) {
	
		
		String oldTitle = getData().title;
		getData().title = newTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			MteachPackage.TOPIC__TITLE,
			oldTitle, getData().title));
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Course getCourse() {	
	  
		if (getData().course == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, MteachPackage.TOPIC__COURSE);
		}		
		if ( isLoaded()) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, MteachPackage.TOPIC__COURSE, null, null));
		return getData().course;	
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX8
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Course basicGetCourse() {
		return data != null ? getData().course : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCourse(Course newCourse, NotificationChain msgs) {
	
		
	
		Course oldCourse = getData().course;
		getData().course = newCourse;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MteachPackage.TOPIC__COURSE, oldCourse, newCourse);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY12
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MteachPackage.TOPIC__COURSE:
				if (getData().course != null)
					msgs = ((InternalEObject)getData().course).eInverseRemove(this, MteachPackage.COURSE__TOPICS, Course.class, msgs);
				return basicSetCourse((Course)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
			case MteachPackage.TOPIC__COURSE:
				return basicSetCourse(null, msgs);
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
			case MteachPackage.TOPIC__TITLE:
				return getTitle();
			case MteachPackage.TOPIC__COURSE:
				if (resolve) return getCourse();
				return basicGetCourse();
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
			case MteachPackage.TOPIC__TITLE:
				setTitle((String)newValue);
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
			case MteachPackage.TOPIC__TITLE:
				setTitle(DataTopic.TITLE_EDEFAULT);
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
			case MteachPackage.TOPIC__TITLE:
				return DataTopic.TITLE_EDEFAULT == null ? getTitle() != null : !DataTopic.TITLE_EDEFAULT.equals(getTitle());
			case MteachPackage.TOPIC__COURSE:
				return getCourse() != null;
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
		if (data != null) result.append(data.toString());
		
		return result.toString();
		}




// data Class generation 
protected static  class DataTopic {


	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCourse() <em>Course</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCourse()
	 * @generated
	 * @ordered
	 */
	protected Course course;

	/**
	 *Constructor of DataTopic
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTopic() {
		
	}
	
		
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (title: ");
		result.append(title);
		result.append(')');
		return result.toString();
	}
		
}//end data class
} //TopicImpl
