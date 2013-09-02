/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.Comment#getAlternateRoot <em>Alternate Root</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getComment()
 * @model abstract="true"
 * @generated
 */
public interface Comment extends ASTNode {

	/**
	 * Returns the value of the '<em><b>Alternate Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Alternate Root</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alternate Root</em>' reference.
	 * @see #setAlternateRoot(ASTNode)
	 * @see DOM.DOMPackage#getComment_AlternateRoot()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ASTNode getAlternateRoot();
	/**
	 * Sets the value of the '{@link DOM.Comment#getAlternateRoot <em>Alternate Root</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alternate Root</em>' reference.
	 * @see #getAlternateRoot()
	 * @generated
	 */
	void setAlternateRoot(ASTNode value);




// data Class generation 
} // Comment
