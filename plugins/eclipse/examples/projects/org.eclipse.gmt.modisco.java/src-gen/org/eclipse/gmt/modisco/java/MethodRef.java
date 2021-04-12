/**
 */
package org.eclipse.gmt.modisco.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.MethodRef#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.MethodRef#getQualifier <em>Qualifier</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.MethodRef#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getMethodRef()
 * @model
 * @generated
 */
public interface MethodRef extends ASTNode {
	/**
	 * Returns the value of the '<em><b>Method</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.AbstractMethodDeclaration#getUsagesInDocComments <em>Usages In Doc Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' reference.
	 * @see #setMethod(AbstractMethodDeclaration)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getMethodRef_Method()
	 * @see org.eclipse.gmt.modisco.java.AbstractMethodDeclaration#getUsagesInDocComments
	 * @model opposite="usagesInDocComments" required="true" ordered="false"
	 * @generated
	 */
	AbstractMethodDeclaration getMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.MethodRef#getMethod <em>Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method</em>' reference.
	 * @see #getMethod()
	 * @generated
	 */
	void setMethod(AbstractMethodDeclaration value);

	/**
	 * Returns the value of the '<em><b>Qualifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualifier</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualifier</em>' containment reference.
	 * @see #setQualifier(TypeAccess)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getMethodRef_Qualifier()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	TypeAccess getQualifier();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.MethodRef#getQualifier <em>Qualifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualifier</em>' containment reference.
	 * @see #getQualifier()
	 * @generated
	 */
	void setQualifier(TypeAccess value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.MethodRefParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getMethodRef_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<MethodRefParameter> getParameters();

} // MethodRef
