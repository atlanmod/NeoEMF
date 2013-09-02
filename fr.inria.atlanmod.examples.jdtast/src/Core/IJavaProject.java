/**
 *
 * $Id$
 */
package Core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IJava Project</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Core.IJavaProject#getPackageFragmentRoots <em>Package Fragment Roots</em>}</li>
 *   <li>{@link Core.IJavaProject#getExternalPackageFragmentRoots <em>External Package Fragment Roots</em>}</li>
 *   <li>{@link Core.IJavaProject#getRequiredProjects <em>Required Projects</em>}</li>
 * </ul>
 * </p>
 *
 * @see Core.CorePackage#getIJavaProject()
 * @model
 * @generated
 */
public interface IJavaProject extends IJavaElement, PhysicalElement {

	/**
	 * Returns the value of the '<em><b>Package Fragment Roots</b></em>' containment reference list.
	 * The list contents are of type {@link Core.IPackageFragmentRoot}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Package Fragment Roots</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Fragment Roots</em>' containment reference list.
	 * @see Core.CorePackage#getIJavaProject_PackageFragmentRoots()
	 * @model containment="true"
	 * @generated
	 */
	EList<IPackageFragmentRoot> getPackageFragmentRoots();
	/**
	 * Returns the value of the '<em><b>External Package Fragment Roots</b></em>' reference list.
	 * The list contents are of type {@link Core.IPackageFragmentRoot}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>External Package Fragment Roots</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Package Fragment Roots</em>' reference list.
	 * @see Core.CorePackage#getIJavaProject_ExternalPackageFragmentRoots()
	 * @model ordered="false"
	 * @generated
	 */
	EList<IPackageFragmentRoot> getExternalPackageFragmentRoots();
	/**
	 * Returns the value of the '<em><b>Required Projects</b></em>' reference list.
	 * The list contents are of type {@link Core.IJavaProject}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Required Projects</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Projects</em>' reference list.
	 * @see Core.CorePackage#getIJavaProject_RequiredProjects()
	 * @model
	 * @generated
	 */
	EList<IJavaProject> getRequiredProjects();



// data Class generation 
} // IJavaProject
