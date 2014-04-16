/**
 */
package mgraph;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MEdge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link mgraph.MEdge#getName <em>Name</em>}</li>
 *   <li>{@link mgraph.MEdge#getInComing <em>In Coming</em>}</li>
 *   <li>{@link mgraph.MEdge#getOutGoing <em>Out Going</em>}</li>
 *   <li>{@link mgraph.MEdge#getGraph <em>Graph</em>}</li>
 * </ul>
 * </p>
 *
 * @see mgraph.MgraphPackage#getMEdge()
 * @model
 * @extends INeo4emfObject
 * @generated
 */
public interface MEdge extends INeo4emfObject {
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
	 * @see mgraph.MgraphPackage#getMEdge_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link mgraph.MEdge#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>In Coming</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link mgraph.MNode#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Coming</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Coming</em>' reference.
	 * @see #setInComing(MNode)
	 * @see mgraph.MgraphPackage#getMEdge_InComing()
	 * @see mgraph.MNode#getTo
	 * @model opposite="to" required="true"
	 * @generated
	 */
	MNode getInComing();

	/**
	 * Sets the value of the '{@link mgraph.MEdge#getInComing <em>In Coming</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Coming</em>' reference.
	 * @see #getInComing()
	 * @generated
	 */
	void setInComing(MNode value);

	/**
	 * Returns the value of the '<em><b>Out Going</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link mgraph.MNode#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Going</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Going</em>' reference.
	 * @see #setOutGoing(MNode)
	 * @see mgraph.MgraphPackage#getMEdge_OutGoing()
	 * @see mgraph.MNode#getFrom
	 * @model opposite="from" required="true"
	 * @generated
	 */
	MNode getOutGoing();

	/**
	 * Sets the value of the '{@link mgraph.MEdge#getOutGoing <em>Out Going</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Out Going</em>' reference.
	 * @see #getOutGoing()
	 * @generated
	 */
	void setOutGoing(MNode value);

	/**
	 * Returns the value of the '<em><b>Graph</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link mgraph.MGraph#getEdges <em>Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Graph</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Graph</em>' container reference.
	 * @see #setGraph(MGraph)
	 * @see mgraph.MgraphPackage#getMEdge_Graph()
	 * @see mgraph.MGraph#getEdges
	 * @model opposite="edges" required="true" transient="false"
	 * @generated
	 */
	MGraph getGraph();

	/**
	 * Sets the value of the '{@link mgraph.MEdge#getGraph <em>Graph</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Graph</em>' container reference.
	 * @see #getGraph()
	 * @generated
	 */
	void setGraph(MGraph value);

} // MEdge
