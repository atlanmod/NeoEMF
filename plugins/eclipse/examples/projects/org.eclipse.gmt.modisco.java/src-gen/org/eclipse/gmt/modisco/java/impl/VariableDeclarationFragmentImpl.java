/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AbstractVariablesContainer;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Declaration Fragment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.VariableDeclarationFragmentImpl#getVariablesContainer <em>Variables Container</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VariableDeclarationFragmentImpl extends VariableDeclarationImpl implements VariableDeclarationFragment {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableDeclarationFragmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getVariableDeclarationFragment();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractVariablesContainer getVariablesContainer() {
		return (AbstractVariablesContainer)eGet(JavaPackage.eINSTANCE.getVariableDeclarationFragment_VariablesContainer(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariablesContainer(AbstractVariablesContainer newVariablesContainer) {
		eSet(JavaPackage.eINSTANCE.getVariableDeclarationFragment_VariablesContainer(), newVariablesContainer);
	}

} //VariableDeclarationFragmentImpl
