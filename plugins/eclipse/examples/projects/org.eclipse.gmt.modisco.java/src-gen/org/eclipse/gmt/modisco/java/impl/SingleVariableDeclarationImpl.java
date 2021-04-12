/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.CatchClause;
import org.eclipse.gmt.modisco.java.EnhancedForStatement;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Single Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.SingleVariableDeclarationImpl#getModifier <em>Modifier</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.SingleVariableDeclarationImpl#isVarargs <em>Varargs</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.SingleVariableDeclarationImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.SingleVariableDeclarationImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.SingleVariableDeclarationImpl#getMethodDeclaration <em>Method Declaration</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.SingleVariableDeclarationImpl#getCatchClause <em>Catch Clause</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.SingleVariableDeclarationImpl#getEnhancedForStatement <em>Enhanced For Statement</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SingleVariableDeclarationImpl extends VariableDeclarationImpl implements SingleVariableDeclaration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SingleVariableDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getSingleVariableDeclaration();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Modifier getModifier() {
		return (Modifier)eGet(JavaPackage.eINSTANCE.getSingleVariableDeclaration_Modifier(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifier(Modifier newModifier) {
		eSet(JavaPackage.eINSTANCE.getSingleVariableDeclaration_Modifier(), newModifier);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVarargs() {
		return (Boolean)eGet(JavaPackage.eINSTANCE.getSingleVariableDeclaration_Varargs(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVarargs(boolean newVarargs) {
		eSet(JavaPackage.eINSTANCE.getSingleVariableDeclaration_Varargs(), newVarargs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getType() {
		return (TypeAccess)eGet(JavaPackage.eINSTANCE.getSingleVariableDeclaration_Type(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(TypeAccess newType) {
		eSet(JavaPackage.eINSTANCE.getSingleVariableDeclaration_Type(), newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Annotation> getAnnotations() {
		return (EList<Annotation>)eGet(JavaPackage.eINSTANCE.getSingleVariableDeclaration_Annotations(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractMethodDeclaration getMethodDeclaration() {
		return (AbstractMethodDeclaration)eGet(JavaPackage.eINSTANCE.getSingleVariableDeclaration_MethodDeclaration(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodDeclaration(AbstractMethodDeclaration newMethodDeclaration) {
		eSet(JavaPackage.eINSTANCE.getSingleVariableDeclaration_MethodDeclaration(), newMethodDeclaration);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CatchClause getCatchClause() {
		return (CatchClause)eGet(JavaPackage.eINSTANCE.getSingleVariableDeclaration_CatchClause(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCatchClause(CatchClause newCatchClause) {
		eSet(JavaPackage.eINSTANCE.getSingleVariableDeclaration_CatchClause(), newCatchClause);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnhancedForStatement getEnhancedForStatement() {
		return (EnhancedForStatement)eGet(JavaPackage.eINSTANCE.getSingleVariableDeclaration_EnhancedForStatement(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnhancedForStatement(EnhancedForStatement newEnhancedForStatement) {
		eSet(JavaPackage.eINSTANCE.getSingleVariableDeclaration_EnhancedForStatement(), newEnhancedForStatement);
	}

} //SingleVariableDeclarationImpl
