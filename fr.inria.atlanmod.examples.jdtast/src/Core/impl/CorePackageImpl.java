/**
 *
 * $Id$
 */
package Core.impl;

import Core.BinaryPackageFragmentRoot;
import Core.CoreFactory;
import Core.CorePackage;
import Core.IClassFile;
import Core.ICompilationUnit;
import Core.IField;
import Core.IImportDeclaration;
import Core.IInitializer;
import Core.IJavaElement;
import Core.IJavaModel;
import Core.IJavaProject;
import Core.IMember;
import Core.IMethod;
import Core.IPackageFragment;
import Core.IPackageFragmentRoot;
import Core.ISourceRange;
import Core.ISourceReference;
import Core.IType;
import Core.ITypeParameter;
import Core.ITypeRoot;
import Core.Modifiers;
import Core.Parameter;
import Core.PhysicalElement;
import Core.SourcePackageFragmentRoot;

import DOM.DOMPackage;

import DOM.impl.DOMPackageImpl;

import PrimitiveTypes.PrimitiveTypesPackage;

import PrimitiveTypes.impl.PrimitiveTypesPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CorePackageImpl extends EPackageImpl implements CorePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iJavaElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass physicalElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iJavaModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iJavaProjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iPackageFragmentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass binaryPackageFragmentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sourcePackageFragmentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iPackageFragmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iTypeRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iCompilationUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iClassFileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iSourceReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iImportDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iSourceRangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iMemberEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iTypeParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iInitializerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iMethodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum modifiersEEnum = null;

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
	 * @see Core.CorePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CorePackageImpl() {
		super(eNS_URI, CoreFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link CorePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CorePackage init() {
		if (isInited) return (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Obtain or create and register package
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CorePackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		DOMPackageImpl theDOMPackage = (DOMPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DOMPackage.eNS_URI) instanceof DOMPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DOMPackage.eNS_URI) : DOMPackage.eINSTANCE);
		PrimitiveTypesPackageImpl thePrimitiveTypesPackage = (PrimitiveTypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PrimitiveTypesPackage.eNS_URI) instanceof PrimitiveTypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PrimitiveTypesPackage.eNS_URI) : PrimitiveTypesPackage.eINSTANCE);

		// Create package meta-data objects
		theCorePackage.createPackageContents();
		theDOMPackage.createPackageContents();
		thePrimitiveTypesPackage.createPackageContents();

		// Initialize created meta-data
		theCorePackage.initializePackageContents();
		theDOMPackage.initializePackageContents();
		thePrimitiveTypesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCorePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CorePackage.eNS_URI, theCorePackage);
		return theCorePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIJavaElement() {
		return iJavaElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIJavaElement_ElementName() {
		return (EAttribute)iJavaElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPhysicalElement() {
		return physicalElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPhysicalElement_Path() {
		return (EAttribute)physicalElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPhysicalElement_IsReadOnly() {
		return (EAttribute)physicalElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIJavaModel() {
		return iJavaModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIJavaModel_JavaProjects() {
		return (EReference)iJavaModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIJavaModel_ExternalPackageFragmentRoots() {
		return (EReference)iJavaModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIJavaProject() {
		return iJavaProjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIJavaProject_PackageFragmentRoots() {
		return (EReference)iJavaProjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIJavaProject_ExternalPackageFragmentRoots() {
		return (EReference)iJavaProjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIJavaProject_RequiredProjects() {
		return (EReference)iJavaProjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIPackageFragmentRoot() {
		return iPackageFragmentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIPackageFragmentRoot_PackageFragments() {
		return (EReference)iPackageFragmentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBinaryPackageFragmentRoot() {
		return binaryPackageFragmentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSourcePackageFragmentRoot() {
		return sourcePackageFragmentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIPackageFragment() {
		return iPackageFragmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIPackageFragment_IsDefaultPackage() {
		return (EAttribute)iPackageFragmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIPackageFragment_PackageFragmentRoot() {
		return (EReference)iPackageFragmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIPackageFragment_ClassFiles() {
		return (EReference)iPackageFragmentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIPackageFragment_CompilationUnits() {
		return (EReference)iPackageFragmentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getITypeRoot() {
		return iTypeRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getICompilationUnit() {
		return iCompilationUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getICompilationUnit_AllType() {
		return (EReference)iCompilationUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getICompilationUnit_Imports() {
		return (EReference)iCompilationUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getICompilationUnit_Types() {
		return (EReference)iCompilationUnitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getICompilationUnit_Primary() {
		return (EReference)iCompilationUnitEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getICompilationUnit_Ast() {
		return (EReference)iCompilationUnitEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIClassFile() {
		return iClassFileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIClassFile_IsClass() {
		return (EAttribute)iClassFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIClassFile_IsInterface() {
		return (EAttribute)iClassFileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIClassFile_Type() {
		return (EReference)iClassFileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getISourceReference() {
		return iSourceReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getISourceReference_Source() {
		return (EAttribute)iSourceReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getISourceReference_SourceRange() {
		return (EReference)iSourceReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIImportDeclaration() {
		return iImportDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIImportDeclaration_IsOnDemand() {
		return (EAttribute)iImportDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIImportDeclaration_IsStatic() {
		return (EAttribute)iImportDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getISourceRange() {
		return iSourceRangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getISourceRange_Length() {
		return (EAttribute)iSourceRangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getISourceRange_Offset() {
		return (EAttribute)iSourceRangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIMember() {
		return iMemberEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIMember_JavadocRange() {
		return (EReference)iMemberEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIMember_NameRange() {
		return (EReference)iMemberEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIType() {
		return iTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIType_FullyQualifiedName() {
		return (EAttribute)iTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIType_FullyQualifiedParametrizedName() {
		return (EAttribute)iTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIType_Initializers() {
		return (EReference)iTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIType_Fields() {
		return (EReference)iTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIType_Methods() {
		return (EReference)iTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIType_Types() {
		return (EReference)iTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIType_TypeParameters() {
		return (EReference)iTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getITypeParameter() {
		return iTypeParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getITypeParameter_Bounds() {
		return (EAttribute)iTypeParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIInitializer() {
		return iInitializerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIField() {
		return iFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIField_Constant() {
		return (EAttribute)iFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIField_IsEnumConstant() {
		return (EAttribute)iFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIField_TypeSignature() {
		return (EAttribute)iFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIField_IsVolatile() {
		return (EAttribute)iFieldEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIField_IsTransient() {
		return (EAttribute)iFieldEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIMethod() {
		return iMethodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIMethod_ReturnType() {
		return (EAttribute)iMethodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIMethod_IsConstructor() {
		return (EAttribute)iMethodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIMethod_IsMainMethod() {
		return (EAttribute)iMethodEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIMethod_Parameters() {
		return (EReference)iMethodEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIMethod_ExceptionTypes() {
		return (EAttribute)iMethodEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameter() {
		return parameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParameter_Name() {
		return (EAttribute)parameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParameter_Type() {
		return (EAttribute)parameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getModifiers() {
		return modifiersEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreFactory getCoreFactory() {
		return (CoreFactory)getEFactoryInstance();
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
		iJavaElementEClass = createEClass(IJAVA_ELEMENT);
		createEAttribute(iJavaElementEClass, IJAVA_ELEMENT__ELEMENT_NAME);

		physicalElementEClass = createEClass(PHYSICAL_ELEMENT);
		createEAttribute(physicalElementEClass, PHYSICAL_ELEMENT__PATH);
		createEAttribute(physicalElementEClass, PHYSICAL_ELEMENT__IS_READ_ONLY);

		iJavaModelEClass = createEClass(IJAVA_MODEL);
		createEReference(iJavaModelEClass, IJAVA_MODEL__JAVA_PROJECTS);
		createEReference(iJavaModelEClass, IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS);

		iJavaProjectEClass = createEClass(IJAVA_PROJECT);
		createEReference(iJavaProjectEClass, IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS);
		createEReference(iJavaProjectEClass, IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS);
		createEReference(iJavaProjectEClass, IJAVA_PROJECT__REQUIRED_PROJECTS);

		iPackageFragmentRootEClass = createEClass(IPACKAGE_FRAGMENT_ROOT);
		createEReference(iPackageFragmentRootEClass, IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS);

		binaryPackageFragmentRootEClass = createEClass(BINARY_PACKAGE_FRAGMENT_ROOT);

		sourcePackageFragmentRootEClass = createEClass(SOURCE_PACKAGE_FRAGMENT_ROOT);

		iPackageFragmentEClass = createEClass(IPACKAGE_FRAGMENT);
		createEAttribute(iPackageFragmentEClass, IPACKAGE_FRAGMENT__IS_DEFAULT_PACKAGE);
		createEReference(iPackageFragmentEClass, IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT);
		createEReference(iPackageFragmentEClass, IPACKAGE_FRAGMENT__CLASS_FILES);
		createEReference(iPackageFragmentEClass, IPACKAGE_FRAGMENT__COMPILATION_UNITS);

		iTypeRootEClass = createEClass(ITYPE_ROOT);

		iCompilationUnitEClass = createEClass(ICOMPILATION_UNIT);
		createEReference(iCompilationUnitEClass, ICOMPILATION_UNIT__ALL_TYPE);
		createEReference(iCompilationUnitEClass, ICOMPILATION_UNIT__IMPORTS);
		createEReference(iCompilationUnitEClass, ICOMPILATION_UNIT__TYPES);
		createEReference(iCompilationUnitEClass, ICOMPILATION_UNIT__PRIMARY);
		createEReference(iCompilationUnitEClass, ICOMPILATION_UNIT__AST);

		iClassFileEClass = createEClass(ICLASS_FILE);
		createEAttribute(iClassFileEClass, ICLASS_FILE__IS_CLASS);
		createEAttribute(iClassFileEClass, ICLASS_FILE__IS_INTERFACE);
		createEReference(iClassFileEClass, ICLASS_FILE__TYPE);

		iSourceReferenceEClass = createEClass(ISOURCE_REFERENCE);
		createEAttribute(iSourceReferenceEClass, ISOURCE_REFERENCE__SOURCE);
		createEReference(iSourceReferenceEClass, ISOURCE_REFERENCE__SOURCE_RANGE);

		iImportDeclarationEClass = createEClass(IIMPORT_DECLARATION);
		createEAttribute(iImportDeclarationEClass, IIMPORT_DECLARATION__IS_ON_DEMAND);
		createEAttribute(iImportDeclarationEClass, IIMPORT_DECLARATION__IS_STATIC);

		iSourceRangeEClass = createEClass(ISOURCE_RANGE);
		createEAttribute(iSourceRangeEClass, ISOURCE_RANGE__LENGTH);
		createEAttribute(iSourceRangeEClass, ISOURCE_RANGE__OFFSET);

		iMemberEClass = createEClass(IMEMBER);
		createEReference(iMemberEClass, IMEMBER__JAVADOC_RANGE);
		createEReference(iMemberEClass, IMEMBER__NAME_RANGE);

		iTypeEClass = createEClass(ITYPE);
		createEAttribute(iTypeEClass, ITYPE__FULLY_QUALIFIED_NAME);
		createEAttribute(iTypeEClass, ITYPE__FULLY_QUALIFIED_PARAMETRIZED_NAME);
		createEReference(iTypeEClass, ITYPE__INITIALIZERS);
		createEReference(iTypeEClass, ITYPE__FIELDS);
		createEReference(iTypeEClass, ITYPE__METHODS);
		createEReference(iTypeEClass, ITYPE__TYPES);
		createEReference(iTypeEClass, ITYPE__TYPE_PARAMETERS);

		iTypeParameterEClass = createEClass(ITYPE_PARAMETER);
		createEAttribute(iTypeParameterEClass, ITYPE_PARAMETER__BOUNDS);

		iInitializerEClass = createEClass(IINITIALIZER);

		iFieldEClass = createEClass(IFIELD);
		createEAttribute(iFieldEClass, IFIELD__CONSTANT);
		createEAttribute(iFieldEClass, IFIELD__IS_ENUM_CONSTANT);
		createEAttribute(iFieldEClass, IFIELD__TYPE_SIGNATURE);
		createEAttribute(iFieldEClass, IFIELD__IS_VOLATILE);
		createEAttribute(iFieldEClass, IFIELD__IS_TRANSIENT);

		iMethodEClass = createEClass(IMETHOD);
		createEAttribute(iMethodEClass, IMETHOD__RETURN_TYPE);
		createEAttribute(iMethodEClass, IMETHOD__IS_CONSTRUCTOR);
		createEAttribute(iMethodEClass, IMETHOD__IS_MAIN_METHOD);
		createEReference(iMethodEClass, IMETHOD__PARAMETERS);
		createEAttribute(iMethodEClass, IMETHOD__EXCEPTION_TYPES);

		parameterEClass = createEClass(PARAMETER);
		createEAttribute(parameterEClass, PARAMETER__NAME);
		createEAttribute(parameterEClass, PARAMETER__TYPE);

		// Create enums
		modifiersEEnum = createEEnum(MODIFIERS);
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

		// Obtain other dependent packages
		PrimitiveTypesPackage thePrimitiveTypesPackage = (PrimitiveTypesPackage)EPackage.Registry.INSTANCE.getEPackage(PrimitiveTypesPackage.eNS_URI);
		DOMPackage theDOMPackage = (DOMPackage)EPackage.Registry.INSTANCE.getEPackage(DOMPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		iJavaModelEClass.getESuperTypes().add(this.getPhysicalElement());
		iJavaProjectEClass.getESuperTypes().add(this.getIJavaElement());
		iJavaProjectEClass.getESuperTypes().add(this.getPhysicalElement());
		iPackageFragmentRootEClass.getESuperTypes().add(this.getIJavaElement());
		iPackageFragmentRootEClass.getESuperTypes().add(this.getPhysicalElement());
		binaryPackageFragmentRootEClass.getESuperTypes().add(this.getIPackageFragmentRoot());
		sourcePackageFragmentRootEClass.getESuperTypes().add(this.getIPackageFragmentRoot());
		iPackageFragmentEClass.getESuperTypes().add(this.getIJavaElement());
		iPackageFragmentEClass.getESuperTypes().add(this.getPhysicalElement());
		iTypeRootEClass.getESuperTypes().add(this.getIJavaElement());
		iTypeRootEClass.getESuperTypes().add(this.getISourceReference());
		iTypeRootEClass.getESuperTypes().add(this.getPhysicalElement());
		iCompilationUnitEClass.getESuperTypes().add(this.getITypeRoot());
		iClassFileEClass.getESuperTypes().add(this.getITypeRoot());
		iImportDeclarationEClass.getESuperTypes().add(this.getIJavaElement());
		iImportDeclarationEClass.getESuperTypes().add(this.getISourceReference());
		iMemberEClass.getESuperTypes().add(this.getIJavaElement());
		iMemberEClass.getESuperTypes().add(this.getISourceReference());
		iTypeEClass.getESuperTypes().add(this.getIMember());
		iTypeParameterEClass.getESuperTypes().add(this.getIJavaElement());
		iTypeParameterEClass.getESuperTypes().add(this.getISourceReference());
		iInitializerEClass.getESuperTypes().add(this.getIMember());
		iFieldEClass.getESuperTypes().add(this.getIMember());
		iMethodEClass.getESuperTypes().add(this.getIMember());

		// Initialize classes, features, and operations; add parameters
		initEClass(iJavaElementEClass, IJavaElement.class, "IJavaElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIJavaElement_ElementName(), thePrimitiveTypesPackage.getString(), "elementName", null, 1, 1, IJavaElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(physicalElementEClass, PhysicalElement.class, "PhysicalElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPhysicalElement_Path(), thePrimitiveTypesPackage.getString(), "path", null, 1, 1, PhysicalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getPhysicalElement_IsReadOnly(), thePrimitiveTypesPackage.getBoolean(), "isReadOnly", null, 1, 1, PhysicalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iJavaModelEClass, IJavaModel.class, "IJavaModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIJavaModel_JavaProjects(), this.getIJavaProject(), null, "javaProjects", null, 0, -1, IJavaModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIJavaModel_ExternalPackageFragmentRoots(), this.getIPackageFragmentRoot(), null, "externalPackageFragmentRoots", null, 0, -1, IJavaModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iJavaProjectEClass, IJavaProject.class, "IJavaProject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIJavaProject_PackageFragmentRoots(), this.getIPackageFragmentRoot(), null, "packageFragmentRoots", null, 0, -1, IJavaProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIJavaProject_ExternalPackageFragmentRoots(), this.getIPackageFragmentRoot(), null, "externalPackageFragmentRoots", null, 0, -1, IJavaProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getIJavaProject_RequiredProjects(), this.getIJavaProject(), null, "requiredProjects", null, 0, -1, IJavaProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iPackageFragmentRootEClass, IPackageFragmentRoot.class, "IPackageFragmentRoot", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIPackageFragmentRoot_PackageFragments(), this.getIPackageFragment(), this.getIPackageFragment_PackageFragmentRoot(), "packageFragments", null, 0, -1, IPackageFragmentRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(binaryPackageFragmentRootEClass, BinaryPackageFragmentRoot.class, "BinaryPackageFragmentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sourcePackageFragmentRootEClass, SourcePackageFragmentRoot.class, "SourcePackageFragmentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iPackageFragmentEClass, IPackageFragment.class, "IPackageFragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIPackageFragment_IsDefaultPackage(), thePrimitiveTypesPackage.getBoolean(), "isDefaultPackage", null, 1, 1, IPackageFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getIPackageFragment_PackageFragmentRoot(), this.getIPackageFragmentRoot(), this.getIPackageFragmentRoot_PackageFragments(), "packageFragmentRoot", null, 1, 1, IPackageFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getIPackageFragment_ClassFiles(), this.getIClassFile(), null, "classFiles", null, 0, -1, IPackageFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIPackageFragment_CompilationUnits(), this.getICompilationUnit(), null, "compilationUnits", null, 0, -1, IPackageFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iTypeRootEClass, ITypeRoot.class, "ITypeRoot", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iCompilationUnitEClass, ICompilationUnit.class, "ICompilationUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getICompilationUnit_AllType(), this.getIType(), null, "allType", null, 0, -1, ICompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getICompilationUnit_Imports(), this.getIImportDeclaration(), null, "imports", null, 0, -1, ICompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getICompilationUnit_Types(), this.getIType(), null, "types", null, 0, -1, ICompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getICompilationUnit_Primary(), this.getICompilationUnit(), null, "primary", null, 1, 1, ICompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getICompilationUnit_Ast(), theDOMPackage.getCompilationUnit(), null, "ast", null, 1, 1, ICompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iClassFileEClass, IClassFile.class, "IClassFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIClassFile_IsClass(), thePrimitiveTypesPackage.getBoolean(), "isClass", null, 1, 1, IClassFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getIClassFile_IsInterface(), thePrimitiveTypesPackage.getBoolean(), "isInterface", null, 1, 1, IClassFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getIClassFile_Type(), this.getIType(), null, "type", null, 1, 1, IClassFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iSourceReferenceEClass, ISourceReference.class, "ISourceReference", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getISourceReference_Source(), thePrimitiveTypesPackage.getString(), "source", null, 1, 1, ISourceReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getISourceReference_SourceRange(), this.getISourceRange(), null, "sourceRange", null, 1, 1, ISourceReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iImportDeclarationEClass, IImportDeclaration.class, "IImportDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIImportDeclaration_IsOnDemand(), thePrimitiveTypesPackage.getBoolean(), "isOnDemand", null, 1, 1, IImportDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getIImportDeclaration_IsStatic(), thePrimitiveTypesPackage.getBoolean(), "isStatic", null, 1, 1, IImportDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iSourceRangeEClass, ISourceRange.class, "ISourceRange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getISourceRange_Length(), thePrimitiveTypesPackage.getInteger(), "length", null, 1, 1, ISourceRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getISourceRange_Offset(), thePrimitiveTypesPackage.getInteger(), "offset", null, 1, 1, ISourceRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iMemberEClass, IMember.class, "IMember", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIMember_JavadocRange(), this.getISourceRange(), null, "javadocRange", null, 0, 1, IMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getIMember_NameRange(), this.getISourceRange(), null, "nameRange", null, 0, 1, IMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iTypeEClass, IType.class, "IType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIType_FullyQualifiedName(), thePrimitiveTypesPackage.getString(), "fullyQualifiedName", null, 1, 1, IType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getIType_FullyQualifiedParametrizedName(), thePrimitiveTypesPackage.getString(), "fullyQualifiedParametrizedName", null, 1, 1, IType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getIType_Initializers(), this.getIInitializer(), null, "initializers", null, 0, -1, IType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIType_Fields(), this.getIField(), null, "fields", null, 0, -1, IType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIType_Methods(), this.getIMethod(), null, "methods", null, 0, -1, IType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIType_Types(), this.getIType(), null, "types", null, 0, -1, IType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIType_TypeParameters(), this.getITypeParameter(), null, "typeParameters", null, 0, -1, IType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iTypeParameterEClass, ITypeParameter.class, "ITypeParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getITypeParameter_Bounds(), thePrimitiveTypesPackage.getString(), "bounds", null, 0, -1, ITypeParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iInitializerEClass, IInitializer.class, "IInitializer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iFieldEClass, IField.class, "IField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIField_Constant(), thePrimitiveTypesPackage.getString(), "constant", null, 0, 1, IField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getIField_IsEnumConstant(), thePrimitiveTypesPackage.getBoolean(), "isEnumConstant", null, 1, 1, IField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getIField_TypeSignature(), thePrimitiveTypesPackage.getString(), "typeSignature", null, 1, 1, IField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getIField_IsVolatile(), thePrimitiveTypesPackage.getBoolean(), "isVolatile", null, 1, 1, IField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getIField_IsTransient(), thePrimitiveTypesPackage.getBoolean(), "isTransient", null, 1, 1, IField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iMethodEClass, IMethod.class, "IMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIMethod_ReturnType(), thePrimitiveTypesPackage.getString(), "returnType", null, 1, 1, IMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getIMethod_IsConstructor(), thePrimitiveTypesPackage.getBoolean(), "isConstructor", null, 1, 1, IMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getIMethod_IsMainMethod(), thePrimitiveTypesPackage.getBoolean(), "isMainMethod", null, 1, 1, IMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getIMethod_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, IMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIMethod_ExceptionTypes(), thePrimitiveTypesPackage.getString(), "exceptionTypes", null, 0, -1, IMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameter_Name(), thePrimitiveTypesPackage.getString(), "name", null, 1, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getParameter_Type(), thePrimitiveTypesPackage.getString(), "type", null, 1, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(modifiersEEnum, Modifiers.class, "Modifiers");
		addEEnumLiteral(modifiersEEnum, Modifiers.ABSTRACT);
		addEEnumLiteral(modifiersEEnum, Modifiers.ANNOTATION);
		addEEnumLiteral(modifiersEEnum, Modifiers.BRIDGE);
		addEEnumLiteral(modifiersEEnum, Modifiers.DEFAULT);
		addEEnumLiteral(modifiersEEnum, Modifiers.DEPRECATED);
		addEEnumLiteral(modifiersEEnum, Modifiers.ENUM);
		addEEnumLiteral(modifiersEEnum, Modifiers.FINAL);
		addEEnumLiteral(modifiersEEnum, Modifiers.INTERFACE);
		addEEnumLiteral(modifiersEEnum, Modifiers.NATIVE);
		addEEnumLiteral(modifiersEEnum, Modifiers.PRIVATE);
		addEEnumLiteral(modifiersEEnum, Modifiers.PROTECTED);
		addEEnumLiteral(modifiersEEnum, Modifiers.PUBLIC);
		addEEnumLiteral(modifiersEEnum, Modifiers.STATIC);
		addEEnumLiteral(modifiersEEnum, Modifiers.STRICTFP);
		addEEnumLiteral(modifiersEEnum, Modifiers.SUPER);
		addEEnumLiteral(modifiersEEnum, Modifiers.SYNCHRONIZED);
		addEEnumLiteral(modifiersEEnum, Modifiers.SYNTHETIC);
		addEEnumLiteral(modifiersEEnum, Modifiers.TRANSIENT);
		addEEnumLiteral(modifiersEEnum, Modifiers.VARARGS);
		addEEnumLiteral(modifiersEEnum, Modifiers.VOLATILE);

		// Create resource
		createResource(eNS_URI);
	}

} //CorePackageImpl
