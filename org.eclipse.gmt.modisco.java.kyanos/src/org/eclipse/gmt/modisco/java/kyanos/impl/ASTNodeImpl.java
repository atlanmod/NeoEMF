/**
 */
package org.eclipse.gmt.modisco.java.kyanos.impl;

import fr.inria.atlanmod.neoemf.core.impl.NeoEMFEObjectImpl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.ClassFile;
import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.kyanos.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AST Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.kyanos.impl.ASTNodeImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.kyanos.impl.ASTNodeImpl#getOriginalCompilationUnit <em>Original Compilation Unit</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.kyanos.impl.ASTNodeImpl#getOriginalClassFile <em>Original Class File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ASTNodeImpl extends NeoEMFEObjectImpl implements ASTNode {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ASTNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getASTNode();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Comment> getComments() {
		return (EList<Comment>)eGet(JavaPackage.eINSTANCE.getASTNode_Comments(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnit getOriginalCompilationUnit() {
		return (CompilationUnit)eGet(JavaPackage.eINSTANCE.getASTNode_OriginalCompilationUnit(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginalCompilationUnit(CompilationUnit newOriginalCompilationUnit) {
		eSet(JavaPackage.eINSTANCE.getASTNode_OriginalCompilationUnit(), newOriginalCompilationUnit);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassFile getOriginalClassFile() {
		return (ClassFile)eGet(JavaPackage.eINSTANCE.getASTNode_OriginalClassFile(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginalClassFile(ClassFile newOriginalClassFile) {
		eSet(JavaPackage.eINSTANCE.getASTNode_OriginalClassFile(), newOriginalClassFile);
	}

} //ASTNodeImpl
