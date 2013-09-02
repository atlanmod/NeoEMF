/**
 *
 * $Id$
 */
package DOM;

import Core.IPackageFragment;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.PackageDeclaration#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link DOM.PackageDeclaration#getJavadoc <em>Javadoc</em>}</li>
 *   <li>{@link DOM.PackageDeclaration#getName <em>Name</em>}</li>
 *   <li>{@link DOM.PackageDeclaration#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getPackageDeclaration()
 * @model
 * @generated
 */
public interface PackageDeclaration extends ASTNode {

	/**
	 * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.Annotation}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Annotations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotations</em>' containment reference list.
	 * @see DOM.DOMPackage#getPackageDeclaration_Annotations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Annotation> getAnnotations();
	/**
	 * Returns the value of the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Javadoc</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Javadoc</em>' containment reference.
	 * @see #setJavadoc(Javadoc)
	 * @see DOM.DOMPackage#getPackageDeclaration_Javadoc()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Javadoc getJavadoc();
	/**
	 * Sets the value of the '{@link DOM.PackageDeclaration#getJavadoc <em>Javadoc</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Javadoc</em>' containment reference.
	 * @see #getJavadoc()
	 * @generated
	 */
	void setJavadoc(Javadoc value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(Name)
	 * @see DOM.DOMPackage#getPackageDeclaration_Name()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Name getName();
	/**
	 * Sets the value of the '{@link DOM.PackageDeclaration#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(Name value);

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Binding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' reference.
	 * @see #setBinding(IPackageFragment)
	 * @see DOM.DOMPackage#getPackageDeclaration_Binding()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	IPackageFragment getBinding();
	/**
	 * Sets the value of the '{@link DOM.PackageDeclaration#getBinding <em>Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(IPackageFragment value);




// data Class generation 
} // PackageDeclaration
