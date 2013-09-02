/**
 *
 * $Id$
 */
package DOM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.TypeDeclaration#getSuperclassType <em>Superclass Type</em>}</li>
 *   <li>{@link DOM.TypeDeclaration#getInterface <em>Interface</em>}</li>
 *   <li>{@link DOM.TypeDeclaration#getSuperInterfaceTypes <em>Super Interface Types</em>}</li>
 *   <li>{@link DOM.TypeDeclaration#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getTypeDeclaration()
 * @model
 * @generated
 */
public interface TypeDeclaration extends AbstractTypeDeclaration {

	/**
	 * Returns the value of the '<em><b>Superclass Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Superclass Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Superclass Type</em>' containment reference.
	 * @see #setSuperclassType(Type)
	 * @see DOM.DOMPackage#getTypeDeclaration_SuperclassType()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Type getSuperclassType();
	/**
	 * Sets the value of the '{@link DOM.TypeDeclaration#getSuperclassType <em>Superclass Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Superclass Type</em>' containment reference.
	 * @see #getSuperclassType()
	 * @generated
	 */
	void setSuperclassType(Type value);

	/**
	 * Returns the value of the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' attribute.
	 * @see #setInterface(Boolean)
	 * @see DOM.DOMPackage#getTypeDeclaration_Interface()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getInterface();
	/**
	 * Sets the value of the '{@link DOM.TypeDeclaration#getInterface <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface</em>' attribute.
	 * @see #getInterface()
	 * @generated
	 */
	void setInterface(Boolean value);

	/**
	 * Returns the value of the '<em><b>Super Interface Types</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.Type}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Super Interface Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Interface Types</em>' containment reference list.
	 * @see DOM.DOMPackage#getTypeDeclaration_SuperInterfaceTypes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Type> getSuperInterfaceTypes();
	/**
	 * Returns the value of the '<em><b>Type Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.TypeParameter}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Type Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Parameters</em>' containment reference list.
	 * @see DOM.DOMPackage#getTypeDeclaration_TypeParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<TypeParameter> getTypeParameters();



// data Class generation 
} // TypeDeclaration
