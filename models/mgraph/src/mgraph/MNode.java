/**
 */
package mgraph;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MNode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link mgraph.MNode#getName <em>Name</em>}</li>
 *   <li>{@link mgraph.MNode#getGraph <em>Graph</em>}</li>
 *   <li>{@link mgraph.MNode#getFrom <em>From</em>}</li>
 *   <li>{@link mgraph.MNode#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see mgraph.MgraphPackage#getMNode()
 * @model
 * @extends INeo4emfObject
 * @generated
 */
public interface MNode extends INeo4emfObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see mgraph.MgraphPackage#getMNode_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link mgraph.MNode#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Graph</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link mgraph.MGraph#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Graph</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Graph</em>' container reference.
	 * @see #setGraph(MGraph)
	 * @see mgraph.MgraphPackage#getMNode_Graph()
	 * @see mgraph.MGraph#getNodes
	 * @model opposite="nodes" required="true" transient="false"
	 * @generated
	 */
	MGraph getGraph();

	/**
	 * Sets the value of the '{@link mgraph.MNode#getGraph <em>Graph</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Graph</em>' container reference.
	 * @see #getGraph()
	 * @generated
	 */
	void setGraph(MGraph value);

	/**
	 * Returns the value of the '<em><b>From</b></em>' reference list.
	 * The list contents are of type {@link mgraph.MEdge}.
	 * It is bidirectional and its opposite is '{@link mgraph.MEdge#getOutGoing <em>Out Going</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference list.
	 * @see mgraph.MgraphPackage#getMNode_From()
	 * @see mgraph.MEdge#getOutGoing
	 * @model opposite="outGoing"
	 * @generated
	 */
	EList<MEdge> getFrom();

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference list.
	 * The list contents are of type {@link mgraph.MEdge}.
	 * It is bidirectional and its opposite is '{@link mgraph.MEdge#getInComing <em>In Coming</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference list.
	 * @see mgraph.MgraphPackage#getMNode_To()
	 * @see mgraph.MEdge#getInComing
	 * @model opposite="inComing"
	 * @generated
	 */
	EList<MEdge> getTo();

} // MNode
