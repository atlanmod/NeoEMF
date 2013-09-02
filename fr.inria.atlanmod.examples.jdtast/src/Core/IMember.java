/**
 *
 * $Id$
 */
package Core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IMember</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Core.IMember#getJavadocRange <em>Javadoc Range</em>}</li>
 *   <li>{@link Core.IMember#getNameRange <em>Name Range</em>}</li>
 * </ul>
 * </p>
 *
 * @see Core.CorePackage#getIMember()
 * @model abstract="true"
 * @generated
 */
public interface IMember extends IJavaElement, ISourceReference {

	/**
	 * Returns the value of the '<em><b>Javadoc Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Javadoc Range</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Javadoc Range</em>' containment reference.
	 * @see #setJavadocRange(ISourceRange)
	 * @see Core.CorePackage#getIMember_JavadocRange()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	ISourceRange getJavadocRange();
	/**
	 * Sets the value of the '{@link Core.IMember#getJavadocRange <em>Javadoc Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Javadoc Range</em>' containment reference.
	 * @see #getJavadocRange()
	 * @generated
	 */
	void setJavadocRange(ISourceRange value);

	/**
	 * Returns the value of the '<em><b>Name Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Name Range</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name Range</em>' containment reference.
	 * @see #setNameRange(ISourceRange)
	 * @see Core.CorePackage#getIMember_NameRange()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	ISourceRange getNameRange();
	/**
	 * Sets the value of the '{@link Core.IMember#getNameRange <em>Name Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name Range</em>' containment reference.
	 * @see #getNameRange()
	 * @generated
	 */
	void setNameRange(ISourceRange value);




// data Class generation 
} // IMember
