/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AbstractVariablesContainer;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationExpression;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Declaration Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.VariableDeclarationExpressionImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.VariableDeclarationExpressionImpl#getFragments <em>Fragments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.VariableDeclarationExpressionImpl#getModifier <em>Modifier</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.VariableDeclarationExpressionImpl#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VariableDeclarationExpressionImpl extends ExpressionImpl implements VariableDeclarationExpression {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableDeclarationExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getVariableDeclarationExpression();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getType() {
		return (TypeAccess)eGet(JavaPackage.eINSTANCE.getAbstractVariablesContainer_Type(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(TypeAccess newType) {
		eSet(JavaPackage.eINSTANCE.getAbstractVariablesContainer_Type(), newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<VariableDeclarationFragment> getFragments() {
		return (EList<VariableDeclarationFragment>)eGet(JavaPackage.eINSTANCE.getAbstractVariablesContainer_Fragments(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Modifier getModifier() {
		return (Modifier)eGet(JavaPackage.eINSTANCE.getVariableDeclarationExpression_Modifier(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifier(Modifier newModifier) {
		eSet(JavaPackage.eINSTANCE.getVariableDeclarationExpression_Modifier(), newModifier);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Annotation> getAnnotations() {
		return (EList<Annotation>)eGet(JavaPackage.eINSTANCE.getVariableDeclarationExpression_Annotations(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractVariablesContainer.class) {
			switch (derivedFeatureID) {
				case JavaPackage.VARIABLE_DECLARATION_EXPRESSION__TYPE: return JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE;
				case JavaPackage.VARIABLE_DECLARATION_EXPRESSION__FRAGMENTS: return JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractVariablesContainer.class) {
			switch (baseFeatureID) {
				case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE: return JavaPackage.VARIABLE_DECLARATION_EXPRESSION__TYPE;
				case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS: return JavaPackage.VARIABLE_DECLARATION_EXPRESSION__FRAGMENTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //VariableDeclarationExpressionImpl
