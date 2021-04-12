/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.ClassFile;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class File</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ClassFileImpl#getOriginalFilePath <em>Original File Path</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ClassFileImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ClassFileImpl#getAttachedSource <em>Attached Source</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ClassFileImpl#getPackage <em>Package</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClassFileImpl extends NamedElementImpl implements ClassFile {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassFileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getClassFile();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOriginalFilePath() {
		return (String)eGet(JavaPackage.eINSTANCE.getClassFile_OriginalFilePath(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginalFilePath(String newOriginalFilePath) {
		eSet(JavaPackage.eINSTANCE.getClassFile_OriginalFilePath(), newOriginalFilePath);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractTypeDeclaration getType() {
		return (AbstractTypeDeclaration)eGet(JavaPackage.eINSTANCE.getClassFile_Type(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(AbstractTypeDeclaration newType) {
		eSet(JavaPackage.eINSTANCE.getClassFile_Type(), newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnit getAttachedSource() {
		return (CompilationUnit)eGet(JavaPackage.eINSTANCE.getClassFile_AttachedSource(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttachedSource(CompilationUnit newAttachedSource) {
		eSet(JavaPackage.eINSTANCE.getClassFile_AttachedSource(), newAttachedSource);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package getPackage() {
		return (org.eclipse.gmt.modisco.java.Package)eGet(JavaPackage.eINSTANCE.getClassFile_Package(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackage(org.eclipse.gmt.modisco.java.Package newPackage) {
		eSet(JavaPackage.eINSTANCE.getClassFile_Package(), newPackage);
	}

} //ClassFileImpl
