/**
 *
 * $Id$
 */
package mteach;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Topic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link mteach.Topic#getTitle <em>Title</em>}</li>
 *   <li>{@link mteach.Topic#getCourse <em>Course</em>}</li>
 * </ul>
 * </p>
 *
 * @see mteach.MteachPackage#getTopic()
 * @model
 * @extends INeo4emfObject
 * @generated
 */
public interface Topic extends INeo4emfObject {

	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see mteach.MteachPackage#getTopic_Title()
	 * @model required="true"
	 * @generated
	 */
	String getTitle();
	/**
	 * Sets the value of the '{@link mteach.Topic#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);
	/**
	 * Returns the value of the '<em><b>Course</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link mteach.Course#getTopics <em>Topics</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Course</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Course</em>' reference.
	 * @see mteach.MteachPackage#getTopic_Course()
	 * @see mteach.Course#getTopics
	 * @model opposite="topics" changeable="false"
	 * @generated
	 */
	Course getCourse();





} // Topic
