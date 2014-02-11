/**
 *
 * $Id$
 */
package mgraph;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MGraph</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link mgraph.MGraph#getName <em>Name</em>}</li>
 *   <li>{@link mgraph.MGraph#getNodes <em>Nodes</em>}</li>
 *   <li>{@link mgraph.MGraph#getEdges <em>Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see mgraph.MgraphPackage#getMGraph()
 * @model
 * @extends INeo4emfObject
 * @generated
 */
public interface MGraph extends INeo4emfObject {

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see mgraph.MgraphPackage#getMGraph_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();
	/**
	 * Sets the value of the '{@link mgraph.MGraph#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link mgraph.MNode}.
	 * It is bidirectional and its opposite is '{@link mgraph.MNode#getGraph <em>Graph</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see mgraph.MgraphPackage#getMGraph_Nodes()
	 * @see mgraph.MNode#getGraph
	 * @model opposite="graph" containment="true"
	 * @generated
	 */
	EList<MNode> getNodes();
	/**
	 * Returns the value of the '<em><b>Edges</b></em>' containment reference list.
	 * The list contents are of type {@link mgraph.MEdge}.
	 * It is bidirectional and its opposite is '{@link mgraph.MEdge#getGraph <em>Graph</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edges</em>' containment reference list.
	 * @see mgraph.MgraphPackage#getMGraph_Edges()
	 * @see mgraph.MEdge#getGraph
	 * @model opposite="graph" containment="true"
	 * @generated
	 */
	EList<MEdge> getEdges();




} // MGraph
