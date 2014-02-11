/**
 *
 * $Id$
 */
package mteach;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Course</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link mteach.Course#getName <em>Name</em>}</li>
 *   <li>{@link mteach.Course#getTime <em>Time</em>}</li>
 *   <li>{@link mteach.Course#getTopics <em>Topics</em>}</li>
 *   <li>{@link mteach.Course#getCoefficient <em>Coefficient</em>}</li>
 *   <li>{@link mteach.Course#getProfessor <em>Professor</em>}</li>
 * </ul>
 * </p>
 *
 * @see mteach.MteachPackage#getCourse()
 * @model
 * @extends INeo4emfObject
 * @generated
 */
public interface Course extends INeo4emfObject {

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see mteach.MteachPackage#getCourse_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();
	/**
	 * Sets the value of the '{@link mteach.Course#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(int)
	 * @see mteach.MteachPackage#getCourse_Time()
	 * @model required="true"
	 * @generated
	 */
	int getTime();
	/**
	 * Sets the value of the '{@link mteach.Course#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(int value);

	/**
	 * Returns the value of the '<em><b>Topics</b></em>' reference list.
	 * The list contents are of type {@link mteach.Topic}.
	 * It is bidirectional and its opposite is '{@link mteach.Topic#getCourse <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Topics</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topics</em>' reference list.
	 * @see mteach.MteachPackage#getCourse_Topics()
	 * @see mteach.Topic#getCourse
	 * @model opposite="course" required="true"
	 * @generated
	 */
	EList<Topic> getTopics();
	/**
	 * Returns the value of the '<em><b>Coefficient</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Coefficient</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coefficient</em>' attribute.
	 * @see mteach.MteachPackage#getCourse_Coefficient()
	 * @model default="1" required="true" changeable="false"
	 * @generated
	 */
	float getCoefficient();
	/**
	 * Returns the value of the '<em><b>Professor</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link mteach.Professor#getTeachedCourses <em>Teached Courses</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Professor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Professor</em>' reference.
	 * @see #setProfessor(Professor)
	 * @see mteach.MteachPackage#getCourse_Professor()
	 * @see mteach.Professor#getTeachedCourses
	 * @model opposite="teachedCourses"
	 * @generated
	 */
	Professor getProfessor();
	/**
	 * Sets the value of the '{@link mteach.Course#getProfessor <em>Professor</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Professor</em>' reference.
	 * @see #getProfessor()
	 * @generated
	 */
	void setProfessor(Professor value);




} // Course
