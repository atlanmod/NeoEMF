/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Single Member Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.SingleMemberAnnotation#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getSingleMemberAnnotation()
 * @model
 * @generated
 */
public interface SingleMemberAnnotation extends Annotation {

	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(Expression)
	 * @see DOM.DOMPackage#getSingleMemberAnnotation_Value()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getValue();
	/**
	 * Sets the value of the '{@link DOM.SingleMemberAnnotation#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Expression value);




// data Class generation 
} // SingleMemberAnnotation
