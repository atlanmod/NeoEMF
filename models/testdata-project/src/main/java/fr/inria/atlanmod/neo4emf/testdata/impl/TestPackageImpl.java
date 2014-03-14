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
package fr.inria.atlanmod.neo4emf.testdata.impl;

import fr.inria.atlanmod.neo4emf.RelationshipMapping;

import fr.inria.atlanmod.neo4emf.testdata.Link;
import fr.inria.atlanmod.neo4emf.testdata.LinkVertex;
import fr.inria.atlanmod.neo4emf.testdata.Temperature;
import fr.inria.atlanmod.neo4emf.testdata.TestFactory;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;
import fr.inria.atlanmod.neo4emf.testdata.Vertex;

import fr.inria.atlanmod.neo4emf.testdata.reltypes.Reltypes;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.neo4j.graphdb.RelationshipType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestPackageImpl extends EPackageImpl implements TestPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vertexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linkVertexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum temperatureEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TestPackageImpl() {
		super(eNS_URI, TestFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TestPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TestPackage init() {
		if (isInited) return (TestPackage)EPackage.Registry.INSTANCE.getEPackage(TestPackage.eNS_URI);

		// Obtain or create and register package
		TestPackageImpl theTestPackage = (TestPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TestPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TestPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTestPackage.createPackageContents();

		// Initialize created meta-data
		theTestPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTestPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TestPackage.eNS_URI, theTestPackage);
		return theTestPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer() {
		return containerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContainer_Name() {
		return (EAttribute)containerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainer_Nodes() {
		return (EReference)containerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainer_Links() {
		return (EReference)containerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLink() {
		return linkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLink_Name() {
		return (EAttribute)linkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLink_OutGoing() {
		return (EReference)linkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLink_Container() {
		return (EReference)linkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLink_InComing() {
		return (EReference)linkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVertex() {
		return vertexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVertex_Vname() {
		return (EAttribute)vertexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVertex_ATransientInteger() {
		return (EAttribute)vertexEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVertex_AVolatileDate() {
		return (EAttribute)vertexEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVertex_ABoolean() {
		return (EAttribute)vertexEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVertex_AStringArray() {
		return (EAttribute)vertexEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVertex_ANonUniqueArray() {
		return (EAttribute)vertexEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVertex_ANonOrderedArray() {
		return (EAttribute)vertexEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVertex_ATenStringArray() {
		return (EAttribute)vertexEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVertex_Temperature() {
		return (EAttribute)vertexEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVertex_Vcontainer() {
		return (EReference)vertexEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVertex_From() {
		return (EReference)vertexEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVertex_To() {
		return (EReference)vertexEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLinkVertex() {
		return linkVertexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLinkVertex_FirstName() {
		return (EAttribute)linkVertexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLinkVertex_Link() {
		return (EReference)linkVertexEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLinkVertex_Vertex() {
		return (EReference)linkVertexEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTemperature() {
		return temperatureEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestFactory getTestFactory() {
		return (TestFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		containerEClass = createEClass(CONTAINER);
		createEAttribute(containerEClass, CONTAINER__NAME);
		createEReference(containerEClass, CONTAINER__NODES);
		createEReference(containerEClass, CONTAINER__LINKS);

		linkEClass = createEClass(LINK);
		createEAttribute(linkEClass, LINK__NAME);
		createEReference(linkEClass, LINK__OUT_GOING);
		createEReference(linkEClass, LINK__CONTAINER);
		createEReference(linkEClass, LINK__IN_COMING);

		vertexEClass = createEClass(VERTEX);
		createEAttribute(vertexEClass, VERTEX__VNAME);
		createEAttribute(vertexEClass, VERTEX__ATRANSIENT_INTEGER);
		createEAttribute(vertexEClass, VERTEX__AVOLATILE_DATE);
		createEAttribute(vertexEClass, VERTEX__ABOOLEAN);
		createEAttribute(vertexEClass, VERTEX__ASTRING_ARRAY);
		createEAttribute(vertexEClass, VERTEX__ANON_UNIQUE_ARRAY);
		createEAttribute(vertexEClass, VERTEX__ANON_ORDERED_ARRAY);
		createEAttribute(vertexEClass, VERTEX__ATEN_STRING_ARRAY);
		createEAttribute(vertexEClass, VERTEX__TEMPERATURE);
		createEReference(vertexEClass, VERTEX__VCONTAINER);
		createEReference(vertexEClass, VERTEX__FROM);
		createEReference(vertexEClass, VERTEX__TO);

		linkVertexEClass = createEClass(LINK_VERTEX);
		createEAttribute(linkVertexEClass, LINK_VERTEX__FIRST_NAME);
		createEReference(linkVertexEClass, LINK_VERTEX__LINK);
		createEReference(linkVertexEClass, LINK_VERTEX__VERTEX);

		// Create enums
		temperatureEEnum = createEEnum(TEMPERATURE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		linkVertexEClass.getESuperTypes().add(this.getLink());
		linkVertexEClass.getESuperTypes().add(this.getVertex());

		// Initialize classes, features, and operations; add parameters
		initEClass(containerEClass, fr.inria.atlanmod.neo4emf.testdata.Container.class, "Container", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContainer_Name(), ecorePackage.getEString(), "name", null, 1, 1, fr.inria.atlanmod.neo4emf.testdata.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_Nodes(), this.getVertex(), this.getVertex_Vcontainer(), "nodes", null, 0, -1, fr.inria.atlanmod.neo4emf.testdata.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_Links(), this.getLink(), this.getLink_Container(), "links", null, 0, -1, fr.inria.atlanmod.neo4emf.testdata.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(linkEClass, Link.class, "Link", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLink_Name(), ecorePackage.getEString(), "name", null, 1, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLink_OutGoing(), this.getVertex(), this.getVertex_From(), "outGoing", null, 1, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLink_Container(), this.getContainer(), this.getContainer_Links(), "container", null, 1, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLink_InComing(), this.getVertex(), this.getVertex_To(), "inComing", null, 1, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(vertexEClass, Vertex.class, "Vertex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVertex_Vname(), ecorePackage.getEString(), "vname", null, 1, 1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVertex_ATransientInteger(), ecorePackage.getEBigInteger(), "aTransientInteger", null, 1, 1, Vertex.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVertex_AVolatileDate(), ecorePackage.getEDate(), "aVolatileDate", null, 1, 1, Vertex.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVertex_ABoolean(), ecorePackage.getEBoolean(), "aBoolean", null, 1, 1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVertex_AStringArray(), ecorePackage.getEString(), "aStringArray", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getVertex_ANonUniqueArray(), ecorePackage.getEBigDecimal(), "aNonUniqueArray", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getVertex_ANonOrderedArray(), ecorePackage.getEBoolean(), "aNonOrderedArray", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getVertex_ATenStringArray(), ecorePackage.getEString(), "aTenStringArray", null, 10, 10, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getVertex_Temperature(), this.getTemperature(), "temperature", null, 1, 1, Vertex.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVertex_Vcontainer(), this.getContainer(), this.getContainer_Nodes(), "vcontainer", null, 1, 1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVertex_From(), this.getLink(), this.getLink_OutGoing(), "from", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVertex_To(), this.getLink(), this.getLink_InComing(), "to", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(linkVertexEClass, LinkVertex.class, "LinkVertex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLinkVertex_FirstName(), ecorePackage.getEString(), "firstName", null, 1, 1, LinkVertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLinkVertex_Link(), this.getLink(), null, "link", null, 1, 1, LinkVertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLinkVertex_Vertex(), this.getVertex(), null, "vertex", null, 1, 1, LinkVertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(temperatureEEnum, Temperature.class, "Temperature");
		addEEnumLiteral(temperatureEEnum, Temperature.HOT);
		addEEnumLiteral(temperatureEEnum, Temperature.COLD);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/OCL/Import
		createImportAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/OCL/Import</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createImportAnnotations() {
		String source = "http://www.eclipse.org/OCL/Import";	
		addAnnotation
		  (this, 
		   source, 
		   new String[] {
			 "ecore", "http://www.eclipse.org/emf/2002/Ecore#/"
		   });
	}

/* Neo4EMF Inserted Code -- Begin*/

private RelationshipMapping relationshipMapping = new TestPackageRelationshipMapping();

@Override
public RelationshipMapping getRelationshipMapping() {
	return relationshipMapping;
}

class TestPackageRelationshipMapping implements RelationshipMapping {

	private RelationshipType[][] mapping = new RelationshipType[5][];

	public TestPackageRelationshipMapping() {
	
		mapping[CONTAINER] = new RelationshipType[3];
		mapping[CONTAINER][CONTAINER__NODES] = Reltypes.CONTAINER__NODES;
		mapping[CONTAINER][CONTAINER__LINKS] = Reltypes.CONTAINER__LINKS;
		mapping[LINK] = new RelationshipType[4];
		mapping[LINK][LINK__OUT_GOING] = Reltypes.LINK__OUT_GOING;
		mapping[LINK][LINK__CONTAINER] = Reltypes.LINK__CONTAINER;
		mapping[LINK][LINK__IN_COMING] = Reltypes.LINK__IN_COMING;
		mapping[VERTEX] = new RelationshipType[12];
		mapping[VERTEX][VERTEX__VCONTAINER] = Reltypes.VERTEX__VCONTAINER;
		mapping[VERTEX][VERTEX__FROM] = Reltypes.VERTEX__FROM;
		mapping[VERTEX][VERTEX__TO] = Reltypes.VERTEX__TO;
		mapping[LINK_VERTEX] = new RelationshipType[19];
		mapping[LINK_VERTEX][LINK_VERTEX__VCONTAINER] = Reltypes.LINK_VERTEX__VCONTAINER;
		mapping[LINK_VERTEX][LINK_VERTEX__FROM] = Reltypes.LINK_VERTEX__FROM;
		mapping[LINK_VERTEX][LINK_VERTEX__TO] = Reltypes.LINK_VERTEX__TO;
		mapping[LINK_VERTEX][LINK_VERTEX__LINK] = Reltypes.LINK_VERTEX__LINK;
		mapping[LINK_VERTEX][LINK_VERTEX__VERTEX] = Reltypes.LINK_VERTEX__VERTEX;
	} // TestPackageRelationshipMapping()
	
	public RelationshipType relationshipAt(int classID, int referenceID) {
		assert classID >= 0 && classID < mapping.length : "Invalid Class ID";
		assert referenceID >= 0 && referenceID < mapping[classID].length: "Invalid Reference ID";
		
		return mapping[classID][referenceID];
	}
}// class TestPackageRelationshipMapping
/* Neo4EMF Inserted Code -- End*/
} //TestPackageImpl
