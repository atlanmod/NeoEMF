/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.NamedElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ImportDeclarationImpl#isStatic <em>Static</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ImportDeclarationImpl#getImportedElement <em>Imported Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ImportDeclarationImpl extends ASTNodeImpl implements ImportDeclaration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImportDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getImportDeclaration();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStatic() {
		return (Boolean)eGet(JavaPackage.eINSTANCE.getImportDeclaration_Static(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(boolean newStatic) {
		eSet(JavaPackage.eINSTANCE.getImportDeclaration_Static(), newStatic);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedElement getImportedElement() {
		return (NamedElement)eGet(JavaPackage.eINSTANCE.getImportDeclaration_ImportedElement(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImportedElement(NamedElement newImportedElement) {
		eSet(JavaPackage.eINSTANCE.getImportDeclaration_ImportedElement(), newImportedElement);
	}

} //ImportDeclarationImpl
