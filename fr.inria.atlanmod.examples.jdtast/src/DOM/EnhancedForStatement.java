/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enhanced For Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.EnhancedForStatement#getBody <em>Body</em>}</li>
 *   <li>{@link DOM.EnhancedForStatement#getExpression <em>Expression</em>}</li>
 *   <li>{@link DOM.EnhancedForStatement#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getEnhancedForStatement()
 * @model
 * @generated
 */
public interface EnhancedForStatement extends Statement {

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
	 * @see #setBody(Statement)
	 * @see DOM.DOMPackage#getEnhancedForStatement_Body()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Statement getBody();
	/**
	 * Sets the value of the '{@link DOM.EnhancedForStatement#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(Statement value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(Expression)
	 * @see DOM.DOMPackage#getEnhancedForStatement_Expression()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getExpression();
	/**
	 * Sets the value of the '{@link DOM.EnhancedForStatement#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' containment reference.
	 * @see #setParameter(SingleVariableDeclaration)
	 * @see DOM.DOMPackage#getEnhancedForStatement_Parameter()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	SingleVariableDeclaration getParameter();
	/**
	 * Sets the value of the '{@link DOM.EnhancedForStatement#getParameter <em>Parameter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' containment reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(SingleVariableDeclaration value);




// data Class generation 
} // EnhancedForStatement
