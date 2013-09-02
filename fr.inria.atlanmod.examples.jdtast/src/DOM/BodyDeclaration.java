/**
 *
 * $Id$
 */
package DOM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Body Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.BodyDeclaration#getModifiers <em>Modifiers</em>}</li>
 *   <li>{@link DOM.BodyDeclaration#getJavadoc <em>Javadoc</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getBodyDeclaration()
 * @model abstract="true"
 * @generated
 */
public interface BodyDeclaration extends ASTNode {

	/**
	 * Returns the value of the '<em><b>Modifiers</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.ExtendedModifier}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Modifiers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modifiers</em>' containment reference list.
	 * @see DOM.DOMPackage#getBodyDeclaration_Modifiers()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExtendedModifier> getModifiers();
	/**
	 * Returns the value of the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Javadoc</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Javadoc</em>' containment reference.
	 * @see #setJavadoc(Javadoc)
	 * @see DOM.DOMPackage#getBodyDeclaration_Javadoc()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Javadoc getJavadoc();
	/**
	 * Sets the value of the '{@link DOM.BodyDeclaration#getJavadoc <em>Javadoc</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Javadoc</em>' containment reference.
	 * @see #getJavadoc()
	 * @generated
	 */
	void setJavadoc(Javadoc value);




// data Class generation 
} // BodyDeclaration
