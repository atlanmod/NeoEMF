/**
 *
 * $Id$
 */
package DOM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.TagElement#getFragments <em>Fragments</em>}</li>
 *   <li>{@link DOM.TagElement#getTagName <em>Tag Name</em>}</li>
 *   <li>{@link DOM.TagElement#getNested <em>Nested</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getTagElement()
 * @model
 * @generated
 */
public interface TagElement extends ASTNode {

	/**
	 * Returns the value of the '<em><b>Fragments</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.ASTNode}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Fragments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fragments</em>' containment reference list.
	 * @see DOM.DOMPackage#getTagElement_Fragments()
	 * @model containment="true"
	 * @generated
	 */
	EList<ASTNode> getFragments();
	/**
	 * Returns the value of the '<em><b>Tag Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Tag Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Name</em>' attribute.
	 * @see #setTagName(String)
	 * @see DOM.DOMPackage#getTagElement_TagName()
	 * @model unique="false" dataType="PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getTagName();
	/**
	 * Sets the value of the '{@link DOM.TagElement#getTagName <em>Tag Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag Name</em>' attribute.
	 * @see #getTagName()
	 * @generated
	 */
	void setTagName(String value);

	/**
	 * Returns the value of the '<em><b>Nested</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Nested</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nested</em>' attribute.
	 * @see #setNested(Boolean)
	 * @see DOM.DOMPackage#getTagElement_Nested()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getNested();
	/**
	 * Sets the value of the '{@link DOM.TagElement#getNested <em>Nested</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nested</em>' attribute.
	 * @see #getNested()
	 * @generated
	 */
	void setNested(Boolean value);




// data Class generation 
} // TagElement
