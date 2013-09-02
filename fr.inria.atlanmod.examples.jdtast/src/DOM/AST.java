/**
 *
 * $Id$
 */
package DOM;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AST</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.AST#getCompilationUnits <em>Compilation Units</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getAST()
 * @model
 * @extends INeo4emfObject
 * @generated
 */
public interface AST extends INeo4emfObject {

	/**
	 * Returns the value of the '<em><b>Compilation Units</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Compilation Units</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compilation Units</em>' containment reference.
	 * @see #setCompilationUnits(ASTNode)
	 * @see DOM.DOMPackage#getAST_CompilationUnits()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	ASTNode getCompilationUnits();
	/**
	 * Sets the value of the '{@link DOM.AST#getCompilationUnits <em>Compilation Units</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compilation Units</em>' containment reference.
	 * @see #getCompilationUnits()
	 * @generated
	 */
	void setCompilationUnits(ASTNode value);




// data Class generation 
} // AST
