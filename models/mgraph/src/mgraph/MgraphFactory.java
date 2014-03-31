/**
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 * Descritpion ! To come
 * @author Amine BENELALLAM
**/
package mgraph;
 
import org.eclipse.emf.ecore.EFactory;

import fr.inria.mgraph.MGraph;
import fr.inria.mgraph.MNode;
import fr.inria.mgraph.MEdge;
/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see mgraph.MgraphPackage
 * @generated
 */
public interface MgraphFactory extends fr.inria.mgraph.emf.MgraphFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MgraphFactory eINSTANCE = mgraph.impl.MgraphFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>MGraph</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>MGraph</em>'.
	 * @generated
	 */
	MGraph createMGraph();

	/**
	 * Returns a new object of class '<em>MEdge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>MEdge</em>'.
	 * @generated
	 */
	MEdge createMEdge();

	/**
	 * Returns a new object of class '<em>MNode</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>MNode</em>'.
	 * @generated
	 */
	MNode createMNode();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	MgraphPackage getMgraphPackage();

} //MgraphFactory
