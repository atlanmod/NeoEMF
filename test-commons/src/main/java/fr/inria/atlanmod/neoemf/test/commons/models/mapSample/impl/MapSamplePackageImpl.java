/**
 */
package fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.AbstractPackContent;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.PackContent;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.PackContent2;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModelContentObject;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SpecializedPackContent;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MapSamplePackageImpl extends EPackageImpl implements MapSamplePackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sampleModelEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass stringToStringMapEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass vEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kToVMapEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sampleModelContentObjectEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass packEClass = null;

				/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractPackContentEClass = null;

                /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass packContentEClass = null;

				/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass specializedPackContentEClass = null;

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass packContent2EClass = null;

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
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private MapSamplePackageImpl() {
        super(eNS_URI, MapSampleFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link MapSamplePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static MapSamplePackage init() {
        if (isInited) return (MapSamplePackage)EPackage.Registry.INSTANCE.getEPackage(MapSamplePackage.eNS_URI);

        // Obtain or create and register package
        MapSamplePackageImpl theMapSamplePackage = (MapSamplePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MapSamplePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MapSamplePackageImpl());

        isInited = true;

        // Create package meta-data objects
        theMapSamplePackage.createPackageContents();

        // Initialize created meta-data
        theMapSamplePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theMapSamplePackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(MapSamplePackage.eNS_URI, theMapSamplePackage);
        return theMapSamplePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSampleModel() {
        return sampleModelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSampleModel_Name() {
        return (EAttribute)sampleModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSampleModel_Map() {
        return (EReference)sampleModelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSampleModel_KvMap() {
        return (EReference)sampleModelEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSampleModel_ContentObjects() {
        return (EReference)sampleModelEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStringToStringMap() {
        return stringToStringMapEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStringToStringMap_Key() {
        return (EAttribute)stringToStringMapEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStringToStringMap_Value() {
        return (EAttribute)stringToStringMapEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getK() {
        return kEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getK_KName() {
        return (EAttribute)kEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getK_KInt() {
        return (EAttribute)kEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getV() {
        return vEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getV_VName() {
        return (EAttribute)vEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getV_VInt() {
        return (EAttribute)vEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKToVMap() {
        return kToVMapEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKToVMap_Key() {
        return (EReference)kToVMapEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKToVMap_Value() {
        return (EReference)kToVMapEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSampleModelContentObject() {
        return sampleModelContentObjectEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSampleModelContentObject_Name() {
        return (EAttribute)sampleModelContentObjectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getSampleModelContentObject_Parent() {
        return (EReference)sampleModelContentObjectEClass.getEStructuralFeatures().get(1);
    }

				/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPack() {
        return packEClass;
    }

				/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPack_Packs() {
        return (EReference)packEClass.getEStructuralFeatures().get(0);
    }

				/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPack_ParentPack() {
        return (EReference)packEClass.getEStructuralFeatures().get(1);
    }

				/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPack_OwnedContents() {
        return (EReference)packEClass.getEStructuralFeatures().get(2);
    }

				/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPack_Name() {
        return (EAttribute)packEClass.getEStructuralFeatures().get(3);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAbstractPackContent() {
        return abstractPackContentEClass;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractPackContent_ParentPack() {
        return (EReference)abstractPackContentEClass.getEStructuralFeatures().get(0);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractPackContent_Name() {
        return (EAttribute)abstractPackContentEClass.getEStructuralFeatures().get(1);
    }

                /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPackContent() {
        return packContentEClass;
    }

				/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSpecializedPackContent() {
        return specializedPackContentEClass;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPackContent2() {
        return packContent2EClass;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MapSampleFactory getMapSampleFactory() {
        return (MapSampleFactory)getEFactoryInstance();
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
        sampleModelEClass = createEClass(SAMPLE_MODEL);
        createEAttribute(sampleModelEClass, SAMPLE_MODEL__NAME);
        createEReference(sampleModelEClass, SAMPLE_MODEL__MAP);
        createEReference(sampleModelEClass, SAMPLE_MODEL__KV_MAP);
        createEReference(sampleModelEClass, SAMPLE_MODEL__CONTENT_OBJECTS);

        stringToStringMapEClass = createEClass(STRING_TO_STRING_MAP);
        createEAttribute(stringToStringMapEClass, STRING_TO_STRING_MAP__KEY);
        createEAttribute(stringToStringMapEClass, STRING_TO_STRING_MAP__VALUE);

        kEClass = createEClass(K);
        createEAttribute(kEClass, K__KNAME);
        createEAttribute(kEClass, K__KINT);

        vEClass = createEClass(V);
        createEAttribute(vEClass, V__VNAME);
        createEAttribute(vEClass, V__VINT);

        kToVMapEClass = createEClass(KTO_VMAP);
        createEReference(kToVMapEClass, KTO_VMAP__KEY);
        createEReference(kToVMapEClass, KTO_VMAP__VALUE);

        sampleModelContentObjectEClass = createEClass(SAMPLE_MODEL_CONTENT_OBJECT);
        createEAttribute(sampleModelContentObjectEClass, SAMPLE_MODEL_CONTENT_OBJECT__NAME);
        createEReference(sampleModelContentObjectEClass, SAMPLE_MODEL_CONTENT_OBJECT__PARENT);

        packEClass = createEClass(PACK);
        createEReference(packEClass, PACK__PACKS);
        createEReference(packEClass, PACK__PARENT_PACK);
        createEReference(packEClass, PACK__OWNED_CONTENTS);
        createEAttribute(packEClass, PACK__NAME);

        abstractPackContentEClass = createEClass(ABSTRACT_PACK_CONTENT);
        createEReference(abstractPackContentEClass, ABSTRACT_PACK_CONTENT__PARENT_PACK);
        createEAttribute(abstractPackContentEClass, ABSTRACT_PACK_CONTENT__NAME);

        packContentEClass = createEClass(PACK_CONTENT);

        specializedPackContentEClass = createEClass(SPECIALIZED_PACK_CONTENT);

        packContent2EClass = createEClass(PACK_CONTENT2);
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
        packContentEClass.getESuperTypes().add(this.getAbstractPackContent());
        specializedPackContentEClass.getESuperTypes().add(this.getPackContent());
        packContent2EClass.getESuperTypes().add(this.getAbstractPackContent());

        // Initialize classes, features, and operations; add parameters
        initEClass(sampleModelEClass, SampleModel.class, "SampleModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSampleModel_Name(), ecorePackage.getEString(), "name", null, 0, 1, SampleModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSampleModel_Map(), this.getStringToStringMap(), null, "map", null, 0, -1, SampleModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSampleModel_KvMap(), this.getKToVMap(), null, "kvMap", null, 0, -1, SampleModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSampleModel_ContentObjects(), this.getSampleModelContentObject(), this.getSampleModelContentObject_Parent(), "contentObjects", null, 0, -1, SampleModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(stringToStringMapEClass, Map.Entry.class, "StringToStringMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStringToStringMap_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getStringToStringMap_Value(), ecorePackage.getEString(), "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kEClass, fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K.class, "K", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getK_KName(), ecorePackage.getEString(), "kName", null, 0, 1, fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getK_KInt(), ecorePackage.getEInt(), "kInt", null, 0, 1, fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(vEClass, fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V.class, "V", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getV_VName(), ecorePackage.getEString(), "vName", null, 0, 1, fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getV_VInt(), ecorePackage.getEInt(), "vInt", null, 0, 1, fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kToVMapEClass, Map.Entry.class, "KToVMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKToVMap_Key(), this.getK(), null, "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getKToVMap_Value(), this.getV(), null, "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(sampleModelContentObjectEClass, SampleModelContentObject.class, "SampleModelContentObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSampleModelContentObject_Name(), ecorePackage.getEString(), "name", null, 0, 1, SampleModelContentObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSampleModelContentObject_Parent(), this.getSampleModel(), this.getSampleModel_ContentObjects(), "parent", null, 0, 1, SampleModelContentObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(packEClass, Pack.class, "Pack", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPack_Packs(), this.getPack(), this.getPack_ParentPack(), "packs", null, 0, -1, Pack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPack_ParentPack(), this.getPack(), this.getPack_Packs(), "parentPack", null, 0, 1, Pack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPack_OwnedContents(), this.getAbstractPackContent(), this.getAbstractPackContent_ParentPack(), "ownedContents", null, 0, -1, Pack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPack_Name(), ecorePackage.getEString(), "name", null, 0, 1, Pack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractPackContentEClass, AbstractPackContent.class, "AbstractPackContent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAbstractPackContent_ParentPack(), this.getPack(), this.getPack_OwnedContents(), "parentPack", null, 0, 1, AbstractPackContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractPackContent_Name(), ecorePackage.getEString(), "name", null, 0, 1, AbstractPackContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(packContentEClass, PackContent.class, "PackContent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(specializedPackContentEClass, SpecializedPackContent.class, "SpecializedPackContent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(packContent2EClass, PackContent2.class, "PackContent2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);
    }

} //MapSamplePackageImpl
