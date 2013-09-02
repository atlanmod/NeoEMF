/**
 *
 * $Id$
 */
package DOM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Anonymous Class Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.AnonymousClassDeclaration#getBodyDeclarations <em>Body Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getAnonymousClassDeclaration()
 * @model
 * @generated
 */
public interface AnonymousClassDeclaration extends ASTNode {

	/**
	 * Returns the value of the '<em><b>Body Declarations</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.BodyDeclaration}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Body Declarations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Declarations</em>' containment reference list.
	 * @see DOM.DOMPackage#getAnonymousClassDeclaration_BodyDeclarations()
	 * @model containment="true"
	 * @generated
	 */
	EList<BodyDeclaration> getBodyDeclarations();



// data Class generation 
} // AnonymousClassDeclaration
