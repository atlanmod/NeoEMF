/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Catch Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.CatchClause#getBody <em>Body</em>}</li>
 *   <li>{@link DOM.CatchClause#getException <em>Exception</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getCatchClause()
 * @model
 * @generated
 */
public interface CatchClause extends ASTNode {

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Body</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(Block)
	 * @see DOM.DOMPackage#getCatchClause_Body()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Block getBody();
	/**
	 * Sets the value of the '{@link DOM.CatchClause#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(Block value);

	/**
	 * Returns the value of the '<em><b>Exception</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Exception</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception</em>' containment reference.
	 * @see #setException(SingleVariableDeclaration)
	 * @see DOM.DOMPackage#getCatchClause_Exception()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	SingleVariableDeclaration getException();
	/**
	 * Sets the value of the '{@link DOM.CatchClause#getException <em>Exception</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exception</em>' containment reference.
	 * @see #getException()
	 * @generated
	 */
	void setException(SingleVariableDeclaration value);




// data Class generation 
} // CatchClause
