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
package fr.inria.atlanmod.neo4emf.testdata;

import fr.inria.atlanmod.neo4emf.PersistentPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * NEO4EMF WAS HERE
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see fr.inria.atlanmod.neo4emf.testdata.TestFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/OCL/Import ecore='http://www.eclipse.org/emf/2002/Ecore#/'"
 * @generated
 */
public interface TestPackage extends PersistentPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "testdata";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://test.data/1.1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "fr.inria.atlanmod.neo4emf.test.data";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestPackage eINSTANCE = fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.ContainerImpl
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__NODES = 1;

	/**
	 * The feature id for the '<em><b>Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__LINKS = 2;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkImpl <em>Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.LinkImpl
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getLink()
	 * @generated
	 */
	int LINK = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__NAME = 0;

	/**
	 * The feature id for the '<em><b>Out Going</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__OUT_GOING = 1;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__CONTAINER = 2;

	/**
	 * The feature id for the '<em><b>In Coming</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__IN_COMING = 3;

	/**
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl <em>Vertex</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getVertex()
	 * @generated
	 */
	int VERTEX = 2;

	/**
	 * The feature id for the '<em><b>Vname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__VNAME = 0;

	/**
	 * The feature id for the '<em><b>ATransient Integer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__ATRANSIENT_INTEGER = 1;

	/**
	 * The feature id for the '<em><b>AVolatile Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__AVOLATILE_DATE = 2;

	/**
	 * The feature id for the '<em><b>ABoolean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__ABOOLEAN = 3;

	/**
	 * The feature id for the '<em><b>AString Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__ASTRING_ARRAY = 4;

	/**
	 * The feature id for the '<em><b>ANon Unique Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__ANON_UNIQUE_ARRAY = 5;

	/**
	 * The feature id for the '<em><b>ANon Ordered Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__ANON_ORDERED_ARRAY = 6;

	/**
	 * The feature id for the '<em><b>ATen String Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__ATEN_STRING_ARRAY = 7;

	/**
	 * The feature id for the '<em><b>Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__TEMPERATURE = 8;

	/**
	 * The feature id for the '<em><b>Vcontainer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__VCONTAINER = 9;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__FROM = 10;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__TO = 11;

	/**
	 * The number of structural features of the '<em>Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_FEATURE_COUNT = 12;

	/**
	 * The number of operations of the '<em>Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl <em>Link Vertex</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getLinkVertex()
	 * @generated
	 */
	int LINK_VERTEX = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__NAME = LINK__NAME;

	/**
	 * The feature id for the '<em><b>Out Going</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__OUT_GOING = LINK__OUT_GOING;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__CONTAINER = LINK__CONTAINER;

	/**
	 * The feature id for the '<em><b>In Coming</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__IN_COMING = LINK__IN_COMING;

	/**
	 * The feature id for the '<em><b>Vname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__VNAME = LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>ATransient Integer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__ATRANSIENT_INTEGER = LINK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>AVolatile Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__AVOLATILE_DATE = LINK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>ABoolean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__ABOOLEAN = LINK_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>AString Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__ASTRING_ARRAY = LINK_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>ANon Unique Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__ANON_UNIQUE_ARRAY = LINK_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>ANon Ordered Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__ANON_ORDERED_ARRAY = LINK_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>ATen String Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__ATEN_STRING_ARRAY = LINK_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__TEMPERATURE = LINK_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Vcontainer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__VCONTAINER = LINK_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__FROM = LINK_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__TO = LINK_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__FIRST_NAME = LINK_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__LINK = LINK_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Vertex</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__VERTEX = LINK_FEATURE_COUNT + 14;

	/**
	 * The number of structural features of the '<em>Link Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX_FEATURE_COUNT = LINK_FEATURE_COUNT + 15;

	/**
	 * The number of operations of the '<em>Link Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX_OPERATION_COUNT = LINK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.neo4emf.testdata.Temperature <em>Temperature</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.neo4emf.testdata.Temperature
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getTemperature()
	 * @generated
	 */
	int TEMPERATURE = 4;


	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.neo4emf.testdata.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.Container#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Container#getName()
	 * @see #getContainer()
	 * @generated
	 */
	EAttribute getContainer_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.neo4emf.testdata.Container#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Container#getNodes()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.neo4emf.testdata.Container#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Links</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Container#getLinks()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Links();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.neo4emf.testdata.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Link
	 * @generated
	 */
	EClass getLink();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.Link#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Link#getName()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Name();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.neo4emf.testdata.Link#getOutGoing <em>Out Going</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Out Going</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Link#getOutGoing()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_OutGoing();

	/**
	 * Returns the meta object for the container reference '{@link fr.inria.atlanmod.neo4emf.testdata.Link#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Container</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Link#getContainer()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Container();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.neo4emf.testdata.Link#getInComing <em>In Coming</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>In Coming</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Link#getInComing()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_InComing();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex <em>Vertex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertex</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex
	 * @generated
	 */
	EClass getVertex();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getVname <em>Vname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vname</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getVname()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_Vname();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getATransientInteger <em>ATransient Integer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ATransient Integer</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getATransientInteger()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_ATransientInteger();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getAVolatileDate <em>AVolatile Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>AVolatile Date</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getAVolatileDate()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_AVolatileDate();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#isABoolean <em>ABoolean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ABoolean</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#isABoolean()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_ABoolean();

	/**
	 * Returns the meta object for the attribute list '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getAStringArray <em>AString Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>AString Array</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getAStringArray()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_AStringArray();

	/**
	 * Returns the meta object for the attribute list '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getANonUniqueArray <em>ANon Unique Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>ANon Unique Array</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getANonUniqueArray()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_ANonUniqueArray();

	/**
	 * Returns the meta object for the attribute list '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getANonOrderedArray <em>ANon Ordered Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>ANon Ordered Array</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getANonOrderedArray()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_ANonOrderedArray();

	/**
	 * Returns the meta object for the attribute list '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getATenStringArray <em>ATen String Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>ATen String Array</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getATenStringArray()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_ATenStringArray();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getTemperature <em>Temperature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temperature</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getTemperature()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_Temperature();

	/**
	 * Returns the meta object for the container reference '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getVcontainer <em>Vcontainer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Vcontainer</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getVcontainer()
	 * @see #getVertex()
	 * @generated
	 */
	EReference getVertex_Vcontainer();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>From</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getFrom()
	 * @see #getVertex()
	 * @generated
	 */
	EReference getVertex_From();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>To</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getTo()
	 * @see #getVertex()
	 * @generated
	 */
	EReference getVertex_To();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.neo4emf.testdata.LinkVertex <em>Link Vertex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link Vertex</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.LinkVertex
	 * @generated
	 */
	EClass getLinkVertex();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.LinkVertex#getFirstName <em>First Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Name</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.LinkVertex#getFirstName()
	 * @see #getLinkVertex()
	 * @generated
	 */
	EAttribute getLinkVertex_FirstName();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.neo4emf.testdata.LinkVertex#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Link</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.LinkVertex#getLink()
	 * @see #getLinkVertex()
	 * @generated
	 */
	EReference getLinkVertex_Link();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.neo4emf.testdata.LinkVertex#getVertex <em>Vertex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Vertex</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.LinkVertex#getVertex()
	 * @see #getLinkVertex()
	 * @generated
	 */
	EReference getLinkVertex_Vertex();

	/**
	 * Returns the meta object for enum '{@link fr.inria.atlanmod.neo4emf.testdata.Temperature <em>Temperature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Temperature</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Temperature
	 * @generated
	 */
	EEnum getTemperature();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TestFactory getTestFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.ContainerImpl
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER__NAME = eINSTANCE.getContainer_Name();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__NODES = eINSTANCE.getContainer_Nodes();

		/**
		 * The meta object literal for the '<em><b>Links</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__LINKS = eINSTANCE.getContainer_Links();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkImpl <em>Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.LinkImpl
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getLink()
		 * @generated
		 */
		EClass LINK = eINSTANCE.getLink();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__NAME = eINSTANCE.getLink_Name();

		/**
		 * The meta object literal for the '<em><b>Out Going</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__OUT_GOING = eINSTANCE.getLink_OutGoing();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__CONTAINER = eINSTANCE.getLink_Container();

		/**
		 * The meta object literal for the '<em><b>In Coming</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__IN_COMING = eINSTANCE.getLink_InComing();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl <em>Vertex</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getVertex()
		 * @generated
		 */
		EClass VERTEX = eINSTANCE.getVertex();

		/**
		 * The meta object literal for the '<em><b>Vname</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__VNAME = eINSTANCE.getVertex_Vname();

		/**
		 * The meta object literal for the '<em><b>ATransient Integer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__ATRANSIENT_INTEGER = eINSTANCE.getVertex_ATransientInteger();

		/**
		 * The meta object literal for the '<em><b>AVolatile Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__AVOLATILE_DATE = eINSTANCE.getVertex_AVolatileDate();

		/**
		 * The meta object literal for the '<em><b>ABoolean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__ABOOLEAN = eINSTANCE.getVertex_ABoolean();

		/**
		 * The meta object literal for the '<em><b>AString Array</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__ASTRING_ARRAY = eINSTANCE.getVertex_AStringArray();

		/**
		 * The meta object literal for the '<em><b>ANon Unique Array</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__ANON_UNIQUE_ARRAY = eINSTANCE.getVertex_ANonUniqueArray();

		/**
		 * The meta object literal for the '<em><b>ANon Ordered Array</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__ANON_ORDERED_ARRAY = eINSTANCE.getVertex_ANonOrderedArray();

		/**
		 * The meta object literal for the '<em><b>ATen String Array</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__ATEN_STRING_ARRAY = eINSTANCE.getVertex_ATenStringArray();

		/**
		 * The meta object literal for the '<em><b>Temperature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__TEMPERATURE = eINSTANCE.getVertex_Temperature();

		/**
		 * The meta object literal for the '<em><b>Vcontainer</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERTEX__VCONTAINER = eINSTANCE.getVertex_Vcontainer();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERTEX__FROM = eINSTANCE.getVertex_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERTEX__TO = eINSTANCE.getVertex_To();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl <em>Link Vertex</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getLinkVertex()
		 * @generated
		 */
		EClass LINK_VERTEX = eINSTANCE.getLinkVertex();

		/**
		 * The meta object literal for the '<em><b>First Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK_VERTEX__FIRST_NAME = eINSTANCE.getLinkVertex_FirstName();

		/**
		 * The meta object literal for the '<em><b>Link</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK_VERTEX__LINK = eINSTANCE.getLinkVertex_Link();

		/**
		 * The meta object literal for the '<em><b>Vertex</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK_VERTEX__VERTEX = eINSTANCE.getLinkVertex_Vertex();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.neo4emf.testdata.Temperature <em>Temperature</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.neo4emf.testdata.Temperature
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getTemperature()
		 * @generated
		 */
		EEnum TEMPERATURE = eINSTANCE.getTemperature();

	}

} //TestPackage
