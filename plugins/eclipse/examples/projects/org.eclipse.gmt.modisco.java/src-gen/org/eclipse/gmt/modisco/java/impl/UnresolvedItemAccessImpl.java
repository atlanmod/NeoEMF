/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.UnresolvedItem;
import org.eclipse.gmt.modisco.java.UnresolvedItemAccess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unresolved Item Access</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.UnresolvedItemAccessImpl#getElement <em>Element</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.UnresolvedItemAccessImpl#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UnresolvedItemAccessImpl extends ExpressionImpl implements UnresolvedItemAccess {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnresolvedItemAccessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getUnresolvedItemAccess();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedItem getElement() {
		return (UnresolvedItem)eGet(JavaPackage.eINSTANCE.getUnresolvedItemAccess_Element(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(UnresolvedItem newElement) {
		eSet(JavaPackage.eINSTANCE.getUnresolvedItemAccess_Element(), newElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASTNode getQualifier() {
		return (ASTNode)eGet(JavaPackage.eINSTANCE.getUnresolvedItemAccess_Qualifier(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualifier(ASTNode newQualifier) {
		eSet(JavaPackage.eINSTANCE.getUnresolvedItemAccess_Qualifier(), newQualifier);
	}

} //UnresolvedItemAccessImpl
