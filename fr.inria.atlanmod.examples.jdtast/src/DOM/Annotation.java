/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.Annotation#getTypeName <em>Type Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getAnnotation()
 * @model abstract="true"
 * @generated
 */
public interface Annotation extends Expression, ExtendedModifier {

	/**
	 * Returns the value of the '<em><b>Type Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Type Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Name</em>' containment reference.
	 * @see #setTypeName(Name)
	 * @see DOM.DOMPackage#getAnnotation_TypeName()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Name getTypeName();
	/**
	 * Sets the value of the '{@link DOM.Annotation#getTypeName <em>Type Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' containment reference.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(Name value);




// data Class generation 
} // Annotation
