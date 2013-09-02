/**
 *
 * $Id$
 */
package DOM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enum Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.EnumDeclaration#getSuperInterfaceTypes <em>Super Interface Types</em>}</li>
 *   <li>{@link DOM.EnumDeclaration#getEnumConstants <em>Enum Constants</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getEnumDeclaration()
 * @model
 * @generated
 */
public interface EnumDeclaration extends AbstractTypeDeclaration {

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
	 * @see DOM.DOMPackage#getEnumDeclaration_SuperInterfaceTypes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Type> getSuperInterfaceTypes();
	/**
	 * Returns the value of the '<em><b>Enum Constants</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.EnumConstantDeclaration}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Enum Constants</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enum Constants</em>' containment reference list.
	 * @see DOM.DOMPackage#getEnumDeclaration_EnumConstants()
	 * @model containment="true"
	 * @generated
	 */
	EList<EnumConstantDeclaration> getEnumConstants();



// data Class generation 
} // EnumDeclaration
