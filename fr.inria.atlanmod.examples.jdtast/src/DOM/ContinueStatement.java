/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Continue Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.ContinueStatement#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getContinueStatement()
 * @model
 * @generated
 */
public interface ContinueStatement extends Statement {

	/**
	 * Returns the value of the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Label</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' containment reference.
	 * @see #setLabel(SimpleName)
	 * @see DOM.DOMPackage#getContinueStatement_Label()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	SimpleName getLabel();
	/**
	 * Sets the value of the '{@link DOM.ContinueStatement#getLabel <em>Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' containment reference.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(SimpleName value);




// data Class generation 
} // ContinueStatement
