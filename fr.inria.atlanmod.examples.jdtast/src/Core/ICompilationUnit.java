/**
 *
 * $Id$
 */
package Core;

import DOM.CompilationUnit;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ICompilation Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Core.ICompilationUnit#getAllType <em>All Type</em>}</li>
 *   <li>{@link Core.ICompilationUnit#getImports <em>Imports</em>}</li>
 *   <li>{@link Core.ICompilationUnit#getTypes <em>Types</em>}</li>
 *   <li>{@link Core.ICompilationUnit#getPrimary <em>Primary</em>}</li>
 *   <li>{@link Core.ICompilationUnit#getAst <em>Ast</em>}</li>
 * </ul>
 * </p>
 *
 * @see Core.CorePackage#getICompilationUnit()
 * @model
 * @generated
 */
public interface ICompilationUnit extends ITypeRoot {

	/**
	 * Returns the value of the '<em><b>All Type</b></em>' reference list.
	 * The list contents are of type {@link Core.IType}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>All Type</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Type</em>' reference list.
	 * @see Core.CorePackage#getICompilationUnit_AllType()
	 * @model ordered="false"
	 * @generated
	 */
	EList<IType> getAllType();
	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
	 * The list contents are of type {@link Core.IImportDeclaration}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference list.
	 * @see Core.CorePackage#getICompilationUnit_Imports()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<IImportDeclaration> getImports();
	/**
	 * Returns the value of the '<em><b>Types</b></em>' containment reference list.
	 * The list contents are of type {@link Core.IType}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Types</em>' containment reference list.
	 * @see Core.CorePackage#getICompilationUnit_Types()
	 * @model containment="true"
	 * @generated
	 */
	EList<IType> getTypes();
	/**
	 * Returns the value of the '<em><b>Primary</b></em>' reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Primary</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary</em>' reference.
	 * @see #setPrimary(ICompilationUnit)
	 * @see Core.CorePackage#getICompilationUnit_Primary()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ICompilationUnit getPrimary();
	/**
	 * Sets the value of the '{@link Core.ICompilationUnit#getPrimary <em>Primary</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary</em>' reference.
	 * @see #getPrimary()
	 * @generated
	 */
	void setPrimary(ICompilationUnit value);

	/**
	 * Returns the value of the '<em><b>Ast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Ast</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ast</em>' containment reference.
	 * @see #setAst(CompilationUnit)
	 * @see Core.CorePackage#getICompilationUnit_Ast()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	CompilationUnit getAst();
	/**
	 * Sets the value of the '{@link Core.ICompilationUnit#getAst <em>Ast</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ast</em>' containment reference.
	 * @see #getAst()
	 * @generated
	 */
	void setAst(CompilationUnit value);




// data Class generation 
} // ICompilationUnit
