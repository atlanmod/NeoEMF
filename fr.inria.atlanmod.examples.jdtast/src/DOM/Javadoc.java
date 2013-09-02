/**
 *
 * $Id$
 */
package DOM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Javadoc</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.Javadoc#getTags <em>Tags</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getJavadoc()
 * @model
 * @generated
 */
public interface Javadoc extends Comment {

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.TagElement}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Tags</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference list.
	 * @see DOM.DOMPackage#getJavadoc_Tags()
	 * @model containment="true"
	 * @generated
	 */
	EList<TagElement> getTags();



// data Class generation 
} // Javadoc
