/**
 *
 * $Id$
 */
package Core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IJava Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Core.IJavaModel#getJavaProjects <em>Java Projects</em>}</li>
 *   <li>{@link Core.IJavaModel#getExternalPackageFragmentRoots <em>External Package Fragment Roots</em>}</li>
 * </ul>
 * </p>
 *
 * @see Core.CorePackage#getIJavaModel()
 * @model
 * @generated
 */
public interface IJavaModel extends PhysicalElement {

	/**
	 * Returns the value of the '<em><b>Java Projects</b></em>' containment reference list.
	 * The list contents are of type {@link Core.IJavaProject}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Java Projects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Java Projects</em>' containment reference list.
	 * @see Core.CorePackage#getIJavaModel_JavaProjects()
	 * @model containment="true"
	 * @generated
	 */
	EList<IJavaProject> getJavaProjects();
	/**
	 * Returns the value of the '<em><b>External Package Fragment Roots</b></em>' containment reference list.
	 * The list contents are of type {@link Core.IPackageFragmentRoot}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>External Package Fragment Roots</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Package Fragment Roots</em>' containment reference list.
	 * @see Core.CorePackage#getIJavaModel_ExternalPackageFragmentRoots()
	 * @model containment="true"
	 * @generated
	 */
	EList<IPackageFragmentRoot> getExternalPackageFragmentRoots();



// data Class generation 
} // IJavaModel
