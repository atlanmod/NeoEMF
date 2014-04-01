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
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
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
	 * The meta object id for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.ContainerTypeImpl <em>Container Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.ContainerTypeImpl
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getContainerType()
	 * @generated
	 */
	int CONTAINER_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_TYPE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_TYPE__NODES = 1;

	/**
	 * The feature id for the '<em><b>Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_TYPE__LINKS = 2;

	/**
	 * The number of structural features of the '<em>Container Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_TYPE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Container Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.NamedElementImpl
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkImpl <em>Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.LinkImpl
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getLink()
	 * @generated
	 */
	int LINK = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Out Going</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__OUT_GOING = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__CONTAINER = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>In Coming</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__IN_COMING = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl <em>Vertex</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.VertexImpl
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getVertex()
	 * @generated
	 */
	int VERTEX = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>VString</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__VSTRING = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>ATransient Integer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__ATRANSIENT_INTEGER = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>ADate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__ADATE = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>AVolatile Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__AVOLATILE_DATE = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>ABoolean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__ABOOLEAN = NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>AReal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__AREAL = NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>An Integer Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__AN_INTEGER_ARRAY = NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>AReal Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__AREAL_ARRAY = NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>AString Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__ASTRING_ARRAY = NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>ANon Unique Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__ANON_UNIQUE_ARRAY = NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>ANon Ordered Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__ANON_ORDERED_ARRAY = NAMED_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>ATen String Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__ATEN_STRING_ARRAY = NAMED_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__TEMPERATURE = NAMED_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Vcontainer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__VCONTAINER = NAMED_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__FROM = NAMED_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__TO = NAMED_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The number of operations of the '<em>Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl <em>Link Vertex</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.LinkVertexImpl
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getLinkVertex()
	 * @generated
	 */
	int LINK_VERTEX = 4;

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
	 * The feature id for the '<em><b>VString</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__VSTRING = LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>ATransient Integer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__ATRANSIENT_INTEGER = LINK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>ADate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__ADATE = LINK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>AVolatile Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__AVOLATILE_DATE = LINK_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>ABoolean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__ABOOLEAN = LINK_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>AReal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__AREAL = LINK_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>An Integer Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__AN_INTEGER_ARRAY = LINK_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>AReal Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__AREAL_ARRAY = LINK_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>AString Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__ASTRING_ARRAY = LINK_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>ANon Unique Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__ANON_UNIQUE_ARRAY = LINK_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>ANon Ordered Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__ANON_ORDERED_ARRAY = LINK_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>ATen String Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__ATEN_STRING_ARRAY = LINK_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__TEMPERATURE = LINK_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Vcontainer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__VCONTAINER = LINK_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__FROM = LINK_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__TO = LINK_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__FIRST_NAME = LINK_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__LINK = LINK_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Vertex</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX__VERTEX = LINK_FEATURE_COUNT + 18;

	/**
	 * The number of structural features of the '<em>Link Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX_FEATURE_COUNT = LINK_FEATURE_COUNT + 19;

	/**
	 * The number of operations of the '<em>Link Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_VERTEX_OPERATION_COUNT = LINK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.ColoredVertexImpl <em>Colored Vertex</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.ColoredVertexImpl
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getColoredVertex()
	 * @generated
	 */
	int COLORED_VERTEX = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__NAME = VERTEX__NAME;

	/**
	 * The feature id for the '<em><b>VString</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__VSTRING = VERTEX__VSTRING;

	/**
	 * The feature id for the '<em><b>ATransient Integer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__ATRANSIENT_INTEGER = VERTEX__ATRANSIENT_INTEGER;

	/**
	 * The feature id for the '<em><b>ADate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__ADATE = VERTEX__ADATE;

	/**
	 * The feature id for the '<em><b>AVolatile Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__AVOLATILE_DATE = VERTEX__AVOLATILE_DATE;

	/**
	 * The feature id for the '<em><b>ABoolean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__ABOOLEAN = VERTEX__ABOOLEAN;

	/**
	 * The feature id for the '<em><b>AReal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__AREAL = VERTEX__AREAL;

	/**
	 * The feature id for the '<em><b>An Integer Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__AN_INTEGER_ARRAY = VERTEX__AN_INTEGER_ARRAY;

	/**
	 * The feature id for the '<em><b>AReal Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__AREAL_ARRAY = VERTEX__AREAL_ARRAY;

	/**
	 * The feature id for the '<em><b>AString Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__ASTRING_ARRAY = VERTEX__ASTRING_ARRAY;

	/**
	 * The feature id for the '<em><b>ANon Unique Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__ANON_UNIQUE_ARRAY = VERTEX__ANON_UNIQUE_ARRAY;

	/**
	 * The feature id for the '<em><b>ANon Ordered Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__ANON_ORDERED_ARRAY = VERTEX__ANON_ORDERED_ARRAY;

	/**
	 * The feature id for the '<em><b>ATen String Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__ATEN_STRING_ARRAY = VERTEX__ATEN_STRING_ARRAY;

	/**
	 * The feature id for the '<em><b>Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__TEMPERATURE = VERTEX__TEMPERATURE;

	/**
	 * The feature id for the '<em><b>Vcontainer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__VCONTAINER = VERTEX__VCONTAINER;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__FROM = VERTEX__FROM;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__TO = VERTEX__TO;

	/**
	 * The feature id for the '<em><b>ANatural</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX__ANATURAL = VERTEX_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Colored Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX_FEATURE_COUNT = VERTEX_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Colored Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLORED_VERTEX_OPERATION_COUNT = VERTEX_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.neo4emf.testdata.Temperature <em>Temperature</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.neo4emf.testdata.Temperature
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getTemperature()
	 * @generated
	 */
	int TEMPERATURE = 6;

	/**
	 * The meta object id for the '<em>Date</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Date
	 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getDate()
	 * @generated
	 */
	int DATE = 7;


	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.neo4emf.testdata.ContainerType <em>Container Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container Type</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.ContainerType
	 * @generated
	 */
	EClass getContainerType();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.ContainerType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.ContainerType#getName()
	 * @see #getContainerType()
	 * @generated
	 */
	EAttribute getContainerType_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.neo4emf.testdata.ContainerType#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.ContainerType#getNodes()
	 * @see #getContainerType()
	 * @generated
	 */
	EReference getContainerType_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.neo4emf.testdata.ContainerType#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Links</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.ContainerType#getLinks()
	 * @see #getContainerType()
	 * @generated
	 */
	EReference getContainerType_Links();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.neo4emf.testdata.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

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
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getVString <em>VString</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VString</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getVString()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_VString();

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
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getADate <em>ADate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ADate</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getADate()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_ADate();

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
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getAReal <em>AReal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>AReal</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getAReal()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_AReal();

	/**
	 * Returns the meta object for the attribute list '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getAnIntegerArray <em>An Integer Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>An Integer Array</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getAnIntegerArray()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_AnIntegerArray();

	/**
	 * Returns the meta object for the attribute list '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getARealArray <em>AReal Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>AReal Array</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getARealArray()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_ARealArray();

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
	 * Returns the meta object for class '{@link fr.inria.atlanmod.neo4emf.testdata.ColoredVertex <em>Colored Vertex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Colored Vertex</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.ColoredVertex
	 * @generated
	 */
	EClass getColoredVertex();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neo4emf.testdata.ColoredVertex#getANatural <em>ANatural</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ANatural</em>'.
	 * @see fr.inria.atlanmod.neo4emf.testdata.ColoredVertex#getANatural()
	 * @see #getColoredVertex()
	 * @generated
	 */
	EAttribute getColoredVertex_ANatural();

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
	 * Returns the meta object for data type '{@link java.util.Date <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Date</em>'.
	 * @see java.util.Date
	 * @model instanceClass="java.util.Date"
	 * @generated
	 */
	EDataType getDate();

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
		 * The meta object literal for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.ContainerTypeImpl <em>Container Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.ContainerTypeImpl
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getContainerType()
		 * @generated
		 */
		EClass CONTAINER_TYPE = eINSTANCE.getContainerType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_TYPE__NAME = eINSTANCE.getContainerType_Name();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_TYPE__NODES = eINSTANCE.getContainerType_Nodes();

		/**
		 * The meta object literal for the '<em><b>Links</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_TYPE__LINKS = eINSTANCE.getContainerType_Links();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.NamedElementImpl
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

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
		 * The meta object literal for the '<em><b>VString</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__VSTRING = eINSTANCE.getVertex_VString();

		/**
		 * The meta object literal for the '<em><b>ATransient Integer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__ATRANSIENT_INTEGER = eINSTANCE.getVertex_ATransientInteger();

		/**
		 * The meta object literal for the '<em><b>ADate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__ADATE = eINSTANCE.getVertex_ADate();

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
		 * The meta object literal for the '<em><b>AReal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__AREAL = eINSTANCE.getVertex_AReal();

		/**
		 * The meta object literal for the '<em><b>An Integer Array</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__AN_INTEGER_ARRAY = eINSTANCE.getVertex_AnIntegerArray();

		/**
		 * The meta object literal for the '<em><b>AReal Array</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__AREAL_ARRAY = eINSTANCE.getVertex_ARealArray();

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
		 * The meta object literal for the '{@link fr.inria.atlanmod.neo4emf.testdata.impl.ColoredVertexImpl <em>Colored Vertex</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.ColoredVertexImpl
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getColoredVertex()
		 * @generated
		 */
		EClass COLORED_VERTEX = eINSTANCE.getColoredVertex();

		/**
		 * The meta object literal for the '<em><b>ANatural</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLORED_VERTEX__ANATURAL = eINSTANCE.getColoredVertex_ANatural();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.neo4emf.testdata.Temperature <em>Temperature</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.neo4emf.testdata.Temperature
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getTemperature()
		 * @generated
		 */
		EEnum TEMPERATURE = eINSTANCE.getTemperature();

		/**
		 * The meta object literal for the '<em>Date</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Date
		 * @see fr.inria.atlanmod.neo4emf.testdata.impl.TestPackageImpl#getDate()
		 * @generated
		 */
		EDataType DATE = eINSTANCE.getDate();

	}

} //TestPackage
