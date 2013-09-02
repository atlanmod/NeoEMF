/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.Assignment#getLeftHandSide <em>Left Hand Side</em>}</li>
 *   <li>{@link DOM.Assignment#getOperator <em>Operator</em>}</li>
 *   <li>{@link DOM.Assignment#getRightHandSide <em>Right Hand Side</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getAssignment()
 * @model
 * @generated
 */
public interface Assignment extends Expression {

	/**
	 * Returns the value of the '<em><b>Left Hand Side</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Left Hand Side</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Hand Side</em>' containment reference.
	 * @see #setLeftHandSide(Expression)
	 * @see DOM.DOMPackage#getAssignment_LeftHandSide()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getLeftHandSide();
	/**
	 * Sets the value of the '{@link DOM.Assignment#getLeftHandSide <em>Left Hand Side</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Hand Side</em>' containment reference.
	 * @see #getLeftHandSide()
	 * @generated
	 */
	void setLeftHandSide(Expression value);

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link DOM.AssignmentOperatorKind}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see DOM.AssignmentOperatorKind
	 * @see #setOperator(AssignmentOperatorKind)
	 * @see DOM.DOMPackage#getAssignment_Operator()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	AssignmentOperatorKind getOperator();
	/**
	 * Sets the value of the '{@link DOM.Assignment#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see DOM.AssignmentOperatorKind
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(AssignmentOperatorKind value);

	/**
	 * Returns the value of the '<em><b>Right Hand Side</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Right Hand Side</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Hand Side</em>' containment reference.
	 * @see #setRightHandSide(Expression)
	 * @see DOM.DOMPackage#getAssignment_RightHandSide()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getRightHandSide();
	/**
	 * Sets the value of the '{@link DOM.Assignment#getRightHandSide <em>Right Hand Side</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Hand Side</em>' containment reference.
	 * @see #getRightHandSide()
	 * @generated
	 */
	void setRightHandSide(Expression value);




// data Class generation 
} // Assignment
