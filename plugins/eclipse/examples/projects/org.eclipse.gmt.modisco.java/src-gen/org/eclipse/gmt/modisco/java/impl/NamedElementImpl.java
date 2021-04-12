/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.NamedElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Named Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.NamedElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.NamedElementImpl#isProxy <em>Proxy</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.NamedElementImpl#getUsagesInImports <em>Usages In Imports</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class NamedElementImpl extends ASTNodeImpl implements NamedElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NamedElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getNamedElement();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return (String)eGet(JavaPackage.eINSTANCE.getNamedElement_Name(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		eSet(JavaPackage.eINSTANCE.getNamedElement_Name(), newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isProxy() {
		return (Boolean)eGet(JavaPackage.eINSTANCE.getNamedElement_Proxy(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProxy(boolean newProxy) {
		eSet(JavaPackage.eINSTANCE.getNamedElement_Proxy(), newProxy);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<ImportDeclaration> getUsagesInImports() {
		return (EList<ImportDeclaration>)eGet(JavaPackage.eINSTANCE.getNamedElement_UsagesInImports(), true);
	}

} //NamedElementImpl
