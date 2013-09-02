/**
 *
 * $Id$
 */
package Core;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
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
 * @see Core.CoreFactory
 * @model kind="package"
 * @generated
 */
public interface CorePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Core";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "org.amma.dsl.jdt.core";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "core";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CorePackage eINSTANCE = Core.impl.CorePackageImpl.init();

	/**
	 * The meta object id for the '{@link Core.impl.IJavaElementImpl <em>IJava Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.IJavaElementImpl
	 * @see Core.impl.CorePackageImpl#getIJavaElement()
	 * @generated
	 */
	int IJAVA_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_ELEMENT__ELEMENT_NAME = 0;

	/**
	 * The number of structural features of the '<em>IJava Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IJava Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Core.impl.PhysicalElementImpl <em>Physical Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.PhysicalElementImpl
	 * @see Core.impl.CorePackageImpl#getPhysicalElement()
	 * @generated
	 */
	int PHYSICAL_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ELEMENT__PATH = 0;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ELEMENT__IS_READ_ONLY = 1;

	/**
	 * The number of structural features of the '<em>Physical Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Physical Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Core.impl.IJavaModelImpl <em>IJava Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.IJavaModelImpl
	 * @see Core.impl.CorePackageImpl#getIJavaModel()
	 * @generated
	 */
	int IJAVA_MODEL = 2;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_MODEL__PATH = PHYSICAL_ELEMENT__PATH;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_MODEL__IS_READ_ONLY = PHYSICAL_ELEMENT__IS_READ_ONLY;

	/**
	 * The feature id for the '<em><b>Java Projects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_MODEL__JAVA_PROJECTS = PHYSICAL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>External Package Fragment Roots</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS = PHYSICAL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>IJava Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_MODEL_FEATURE_COUNT = PHYSICAL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>IJava Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_MODEL_OPERATION_COUNT = PHYSICAL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.IJavaProjectImpl <em>IJava Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.IJavaProjectImpl
	 * @see Core.impl.CorePackageImpl#getIJavaProject()
	 * @generated
	 */
	int IJAVA_PROJECT = 3;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_PROJECT__ELEMENT_NAME = IJAVA_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_PROJECT__PATH = IJAVA_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_PROJECT__IS_READ_ONLY = IJAVA_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Package Fragment Roots</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS = IJAVA_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>External Package Fragment Roots</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS = IJAVA_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Required Projects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_PROJECT__REQUIRED_PROJECTS = IJAVA_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>IJava Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_PROJECT_FEATURE_COUNT = IJAVA_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>IJava Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IJAVA_PROJECT_OPERATION_COUNT = IJAVA_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.IPackageFragmentRootImpl <em>IPackage Fragment Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.IPackageFragmentRootImpl
	 * @see Core.impl.CorePackageImpl#getIPackageFragmentRoot()
	 * @generated
	 */
	int IPACKAGE_FRAGMENT_ROOT = 4;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT_ROOT__ELEMENT_NAME = IJAVA_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT_ROOT__PATH = IJAVA_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY = IJAVA_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Package Fragments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS = IJAVA_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>IPackage Fragment Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT_ROOT_FEATURE_COUNT = IJAVA_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>IPackage Fragment Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT_ROOT_OPERATION_COUNT = IJAVA_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.BinaryPackageFragmentRootImpl <em>Binary Package Fragment Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.BinaryPackageFragmentRootImpl
	 * @see Core.impl.CorePackageImpl#getBinaryPackageFragmentRoot()
	 * @generated
	 */
	int BINARY_PACKAGE_FRAGMENT_ROOT = 5;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_PACKAGE_FRAGMENT_ROOT__ELEMENT_NAME = IPACKAGE_FRAGMENT_ROOT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_PACKAGE_FRAGMENT_ROOT__PATH = IPACKAGE_FRAGMENT_ROOT__PATH;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_PACKAGE_FRAGMENT_ROOT__IS_READ_ONLY = IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY;

	/**
	 * The feature id for the '<em><b>Package Fragments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_PACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS = IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS;

	/**
	 * The number of structural features of the '<em>Binary Package Fragment Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_PACKAGE_FRAGMENT_ROOT_FEATURE_COUNT = IPACKAGE_FRAGMENT_ROOT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Binary Package Fragment Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_PACKAGE_FRAGMENT_ROOT_OPERATION_COUNT = IPACKAGE_FRAGMENT_ROOT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.SourcePackageFragmentRootImpl <em>Source Package Fragment Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.SourcePackageFragmentRootImpl
	 * @see Core.impl.CorePackageImpl#getSourcePackageFragmentRoot()
	 * @generated
	 */
	int SOURCE_PACKAGE_FRAGMENT_ROOT = 6;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PACKAGE_FRAGMENT_ROOT__ELEMENT_NAME = IPACKAGE_FRAGMENT_ROOT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PACKAGE_FRAGMENT_ROOT__PATH = IPACKAGE_FRAGMENT_ROOT__PATH;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PACKAGE_FRAGMENT_ROOT__IS_READ_ONLY = IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY;

	/**
	 * The feature id for the '<em><b>Package Fragments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS = IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS;

	/**
	 * The number of structural features of the '<em>Source Package Fragment Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PACKAGE_FRAGMENT_ROOT_FEATURE_COUNT = IPACKAGE_FRAGMENT_ROOT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Source Package Fragment Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PACKAGE_FRAGMENT_ROOT_OPERATION_COUNT = IPACKAGE_FRAGMENT_ROOT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.IPackageFragmentImpl <em>IPackage Fragment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.IPackageFragmentImpl
	 * @see Core.impl.CorePackageImpl#getIPackageFragment()
	 * @generated
	 */
	int IPACKAGE_FRAGMENT = 7;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT__ELEMENT_NAME = IJAVA_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT__PATH = IJAVA_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT__IS_READ_ONLY = IJAVA_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Default Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT__IS_DEFAULT_PACKAGE = IJAVA_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Package Fragment Root</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT = IJAVA_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Class Files</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT__CLASS_FILES = IJAVA_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Compilation Units</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT__COMPILATION_UNITS = IJAVA_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>IPackage Fragment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT_FEATURE_COUNT = IJAVA_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>IPackage Fragment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPACKAGE_FRAGMENT_OPERATION_COUNT = IJAVA_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.ITypeRootImpl <em>IType Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.ITypeRootImpl
	 * @see Core.impl.CorePackageImpl#getITypeRoot()
	 * @generated
	 */
	int ITYPE_ROOT = 8;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_ROOT__ELEMENT_NAME = IJAVA_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_ROOT__SOURCE = IJAVA_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_ROOT__SOURCE_RANGE = IJAVA_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_ROOT__PATH = IJAVA_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_ROOT__IS_READ_ONLY = IJAVA_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>IType Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_ROOT_FEATURE_COUNT = IJAVA_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>IType Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_ROOT_OPERATION_COUNT = IJAVA_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.ICompilationUnitImpl <em>ICompilation Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.ICompilationUnitImpl
	 * @see Core.impl.CorePackageImpl#getICompilationUnit()
	 * @generated
	 */
	int ICOMPILATION_UNIT = 9;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPILATION_UNIT__ELEMENT_NAME = ITYPE_ROOT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPILATION_UNIT__SOURCE = ITYPE_ROOT__SOURCE;

	/**
	 * The feature id for the '<em><b>Source Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPILATION_UNIT__SOURCE_RANGE = ITYPE_ROOT__SOURCE_RANGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPILATION_UNIT__PATH = ITYPE_ROOT__PATH;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPILATION_UNIT__IS_READ_ONLY = ITYPE_ROOT__IS_READ_ONLY;

	/**
	 * The feature id for the '<em><b>All Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPILATION_UNIT__ALL_TYPE = ITYPE_ROOT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPILATION_UNIT__IMPORTS = ITYPE_ROOT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPILATION_UNIT__TYPES = ITYPE_ROOT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Primary</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPILATION_UNIT__PRIMARY = ITYPE_ROOT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Ast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPILATION_UNIT__AST = ITYPE_ROOT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>ICompilation Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPILATION_UNIT_FEATURE_COUNT = ITYPE_ROOT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>ICompilation Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPILATION_UNIT_OPERATION_COUNT = ITYPE_ROOT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.IClassFileImpl <em>IClass File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.IClassFileImpl
	 * @see Core.impl.CorePackageImpl#getIClassFile()
	 * @generated
	 */
	int ICLASS_FILE = 10;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICLASS_FILE__ELEMENT_NAME = ITYPE_ROOT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICLASS_FILE__SOURCE = ITYPE_ROOT__SOURCE;

	/**
	 * The feature id for the '<em><b>Source Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICLASS_FILE__SOURCE_RANGE = ITYPE_ROOT__SOURCE_RANGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICLASS_FILE__PATH = ITYPE_ROOT__PATH;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICLASS_FILE__IS_READ_ONLY = ITYPE_ROOT__IS_READ_ONLY;

	/**
	 * The feature id for the '<em><b>Is Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICLASS_FILE__IS_CLASS = ITYPE_ROOT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICLASS_FILE__IS_INTERFACE = ITYPE_ROOT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICLASS_FILE__TYPE = ITYPE_ROOT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>IClass File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICLASS_FILE_FEATURE_COUNT = ITYPE_ROOT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>IClass File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICLASS_FILE_OPERATION_COUNT = ITYPE_ROOT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.ISourceReferenceImpl <em>ISource Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.ISourceReferenceImpl
	 * @see Core.impl.CorePackageImpl#getISourceReference()
	 * @generated
	 */
	int ISOURCE_REFERENCE = 11;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISOURCE_REFERENCE__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Source Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISOURCE_REFERENCE__SOURCE_RANGE = 1;

	/**
	 * The number of structural features of the '<em>ISource Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISOURCE_REFERENCE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>ISource Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISOURCE_REFERENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Core.impl.IImportDeclarationImpl <em>IImport Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.IImportDeclarationImpl
	 * @see Core.impl.CorePackageImpl#getIImportDeclaration()
	 * @generated
	 */
	int IIMPORT_DECLARATION = 12;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIMPORT_DECLARATION__ELEMENT_NAME = IJAVA_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIMPORT_DECLARATION__SOURCE = IJAVA_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIMPORT_DECLARATION__SOURCE_RANGE = IJAVA_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is On Demand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIMPORT_DECLARATION__IS_ON_DEMAND = IJAVA_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Is Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIMPORT_DECLARATION__IS_STATIC = IJAVA_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>IImport Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIMPORT_DECLARATION_FEATURE_COUNT = IJAVA_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>IImport Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIMPORT_DECLARATION_OPERATION_COUNT = IJAVA_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.ISourceRangeImpl <em>ISource Range</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.ISourceRangeImpl
	 * @see Core.impl.CorePackageImpl#getISourceRange()
	 * @generated
	 */
	int ISOURCE_RANGE = 13;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISOURCE_RANGE__LENGTH = 0;

	/**
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISOURCE_RANGE__OFFSET = 1;

	/**
	 * The number of structural features of the '<em>ISource Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISOURCE_RANGE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>ISource Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISOURCE_RANGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Core.impl.IMemberImpl <em>IMember</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.IMemberImpl
	 * @see Core.impl.CorePackageImpl#getIMember()
	 * @generated
	 */
	int IMEMBER = 14;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMEMBER__ELEMENT_NAME = IJAVA_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMEMBER__SOURCE = IJAVA_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMEMBER__SOURCE_RANGE = IJAVA_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Javadoc Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMEMBER__JAVADOC_RANGE = IJAVA_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMEMBER__NAME_RANGE = IJAVA_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>IMember</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMEMBER_FEATURE_COUNT = IJAVA_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>IMember</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMEMBER_OPERATION_COUNT = IJAVA_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.ITypeImpl <em>IType</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.ITypeImpl
	 * @see Core.impl.CorePackageImpl#getIType()
	 * @generated
	 */
	int ITYPE = 15;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE__ELEMENT_NAME = IMEMBER__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE__SOURCE = IMEMBER__SOURCE;

	/**
	 * The feature id for the '<em><b>Source Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE__SOURCE_RANGE = IMEMBER__SOURCE_RANGE;

	/**
	 * The feature id for the '<em><b>Javadoc Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE__JAVADOC_RANGE = IMEMBER__JAVADOC_RANGE;

	/**
	 * The feature id for the '<em><b>Name Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE__NAME_RANGE = IMEMBER__NAME_RANGE;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE__FULLY_QUALIFIED_NAME = IMEMBER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fully Qualified Parametrized Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE__FULLY_QUALIFIED_PARAMETRIZED_NAME = IMEMBER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Initializers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE__INITIALIZERS = IMEMBER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE__FIELDS = IMEMBER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE__METHODS = IMEMBER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE__TYPES = IMEMBER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Type Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE__TYPE_PARAMETERS = IMEMBER_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>IType</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_FEATURE_COUNT = IMEMBER_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>IType</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_OPERATION_COUNT = IMEMBER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.ITypeParameterImpl <em>IType Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.ITypeParameterImpl
	 * @see Core.impl.CorePackageImpl#getITypeParameter()
	 * @generated
	 */
	int ITYPE_PARAMETER = 16;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_PARAMETER__ELEMENT_NAME = IJAVA_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_PARAMETER__SOURCE = IJAVA_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_PARAMETER__SOURCE_RANGE = IJAVA_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Bounds</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_PARAMETER__BOUNDS = IJAVA_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>IType Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_PARAMETER_FEATURE_COUNT = IJAVA_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>IType Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_PARAMETER_OPERATION_COUNT = IJAVA_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.IInitializerImpl <em>IInitializer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.IInitializerImpl
	 * @see Core.impl.CorePackageImpl#getIInitializer()
	 * @generated
	 */
	int IINITIALIZER = 17;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINITIALIZER__ELEMENT_NAME = IMEMBER__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINITIALIZER__SOURCE = IMEMBER__SOURCE;

	/**
	 * The feature id for the '<em><b>Source Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINITIALIZER__SOURCE_RANGE = IMEMBER__SOURCE_RANGE;

	/**
	 * The feature id for the '<em><b>Javadoc Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINITIALIZER__JAVADOC_RANGE = IMEMBER__JAVADOC_RANGE;

	/**
	 * The feature id for the '<em><b>Name Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINITIALIZER__NAME_RANGE = IMEMBER__NAME_RANGE;

	/**
	 * The number of structural features of the '<em>IInitializer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINITIALIZER_FEATURE_COUNT = IMEMBER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>IInitializer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINITIALIZER_OPERATION_COUNT = IMEMBER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.IFieldImpl <em>IField</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.IFieldImpl
	 * @see Core.impl.CorePackageImpl#getIField()
	 * @generated
	 */
	int IFIELD = 18;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFIELD__ELEMENT_NAME = IMEMBER__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFIELD__SOURCE = IMEMBER__SOURCE;

	/**
	 * The feature id for the '<em><b>Source Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFIELD__SOURCE_RANGE = IMEMBER__SOURCE_RANGE;

	/**
	 * The feature id for the '<em><b>Javadoc Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFIELD__JAVADOC_RANGE = IMEMBER__JAVADOC_RANGE;

	/**
	 * The feature id for the '<em><b>Name Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFIELD__NAME_RANGE = IMEMBER__NAME_RANGE;

	/**
	 * The feature id for the '<em><b>Constant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFIELD__CONSTANT = IMEMBER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Enum Constant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFIELD__IS_ENUM_CONSTANT = IMEMBER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFIELD__TYPE_SIGNATURE = IMEMBER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Is Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFIELD__IS_VOLATILE = IMEMBER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Is Transient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFIELD__IS_TRANSIENT = IMEMBER_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>IField</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFIELD_FEATURE_COUNT = IMEMBER_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>IField</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFIELD_OPERATION_COUNT = IMEMBER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.IMethodImpl <em>IMethod</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.IMethodImpl
	 * @see Core.impl.CorePackageImpl#getIMethod()
	 * @generated
	 */
	int IMETHOD = 19;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETHOD__ELEMENT_NAME = IMEMBER__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETHOD__SOURCE = IMEMBER__SOURCE;

	/**
	 * The feature id for the '<em><b>Source Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETHOD__SOURCE_RANGE = IMEMBER__SOURCE_RANGE;

	/**
	 * The feature id for the '<em><b>Javadoc Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETHOD__JAVADOC_RANGE = IMEMBER__JAVADOC_RANGE;

	/**
	 * The feature id for the '<em><b>Name Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETHOD__NAME_RANGE = IMEMBER__NAME_RANGE;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETHOD__RETURN_TYPE = IMEMBER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Constructor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETHOD__IS_CONSTRUCTOR = IMEMBER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Main Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETHOD__IS_MAIN_METHOD = IMEMBER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETHOD__PARAMETERS = IMEMBER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Exception Types</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETHOD__EXCEPTION_TYPES = IMEMBER_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>IMethod</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETHOD_FEATURE_COUNT = IMEMBER_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>IMethod</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETHOD_OPERATION_COUNT = IMEMBER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Core.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.impl.ParameterImpl
	 * @see Core.impl.CorePackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Core.Modifiers <em>Modifiers</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Core.Modifiers
	 * @see Core.impl.CorePackageImpl#getModifiers()
	 * @generated
	 */
	int MODIFIERS = 21;


	/**
	 * Returns the meta object for class '{@link Core.IJavaElement <em>IJava Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IJava Element</em>'.
	 * @see Core.IJavaElement
	 * @generated
	 */
	EClass getIJavaElement();

	/**
	 * Returns the meta object for the attribute '{@link Core.IJavaElement#getElementName <em>Element Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element Name</em>'.
	 * @see Core.IJavaElement#getElementName()
	 * @see #getIJavaElement()
	 * @generated
	 */
	EAttribute getIJavaElement_ElementName();

	/**
	 * Returns the meta object for class '{@link Core.PhysicalElement <em>Physical Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Physical Element</em>'.
	 * @see Core.PhysicalElement
	 * @generated
	 */
	EClass getPhysicalElement();

	/**
	 * Returns the meta object for the attribute '{@link Core.PhysicalElement#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see Core.PhysicalElement#getPath()
	 * @see #getPhysicalElement()
	 * @generated
	 */
	EAttribute getPhysicalElement_Path();

	/**
	 * Returns the meta object for the attribute '{@link Core.PhysicalElement#getIsReadOnly <em>Is Read Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Read Only</em>'.
	 * @see Core.PhysicalElement#getIsReadOnly()
	 * @see #getPhysicalElement()
	 * @generated
	 */
	EAttribute getPhysicalElement_IsReadOnly();

	/**
	 * Returns the meta object for class '{@link Core.IJavaModel <em>IJava Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IJava Model</em>'.
	 * @see Core.IJavaModel
	 * @generated
	 */
	EClass getIJavaModel();

	/**
	 * Returns the meta object for the containment reference list '{@link Core.IJavaModel#getJavaProjects <em>Java Projects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Java Projects</em>'.
	 * @see Core.IJavaModel#getJavaProjects()
	 * @see #getIJavaModel()
	 * @generated
	 */
	EReference getIJavaModel_JavaProjects();

	/**
	 * Returns the meta object for the containment reference list '{@link Core.IJavaModel#getExternalPackageFragmentRoots <em>External Package Fragment Roots</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>External Package Fragment Roots</em>'.
	 * @see Core.IJavaModel#getExternalPackageFragmentRoots()
	 * @see #getIJavaModel()
	 * @generated
	 */
	EReference getIJavaModel_ExternalPackageFragmentRoots();

	/**
	 * Returns the meta object for class '{@link Core.IJavaProject <em>IJava Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IJava Project</em>'.
	 * @see Core.IJavaProject
	 * @generated
	 */
	EClass getIJavaProject();

	/**
	 * Returns the meta object for the containment reference list '{@link Core.IJavaProject#getPackageFragmentRoots <em>Package Fragment Roots</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Package Fragment Roots</em>'.
	 * @see Core.IJavaProject#getPackageFragmentRoots()
	 * @see #getIJavaProject()
	 * @generated
	 */
	EReference getIJavaProject_PackageFragmentRoots();

	/**
	 * Returns the meta object for the reference list '{@link Core.IJavaProject#getExternalPackageFragmentRoots <em>External Package Fragment Roots</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>External Package Fragment Roots</em>'.
	 * @see Core.IJavaProject#getExternalPackageFragmentRoots()
	 * @see #getIJavaProject()
	 * @generated
	 */
	EReference getIJavaProject_ExternalPackageFragmentRoots();

	/**
	 * Returns the meta object for the reference list '{@link Core.IJavaProject#getRequiredProjects <em>Required Projects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Required Projects</em>'.
	 * @see Core.IJavaProject#getRequiredProjects()
	 * @see #getIJavaProject()
	 * @generated
	 */
	EReference getIJavaProject_RequiredProjects();

	/**
	 * Returns the meta object for class '{@link Core.IPackageFragmentRoot <em>IPackage Fragment Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IPackage Fragment Root</em>'.
	 * @see Core.IPackageFragmentRoot
	 * @generated
	 */
	EClass getIPackageFragmentRoot();

	/**
	 * Returns the meta object for the containment reference list '{@link Core.IPackageFragmentRoot#getPackageFragments <em>Package Fragments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Package Fragments</em>'.
	 * @see Core.IPackageFragmentRoot#getPackageFragments()
	 * @see #getIPackageFragmentRoot()
	 * @generated
	 */
	EReference getIPackageFragmentRoot_PackageFragments();

	/**
	 * Returns the meta object for class '{@link Core.BinaryPackageFragmentRoot <em>Binary Package Fragment Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary Package Fragment Root</em>'.
	 * @see Core.BinaryPackageFragmentRoot
	 * @generated
	 */
	EClass getBinaryPackageFragmentRoot();

	/**
	 * Returns the meta object for class '{@link Core.SourcePackageFragmentRoot <em>Source Package Fragment Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Source Package Fragment Root</em>'.
	 * @see Core.SourcePackageFragmentRoot
	 * @generated
	 */
	EClass getSourcePackageFragmentRoot();

	/**
	 * Returns the meta object for class '{@link Core.IPackageFragment <em>IPackage Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IPackage Fragment</em>'.
	 * @see Core.IPackageFragment
	 * @generated
	 */
	EClass getIPackageFragment();

	/**
	 * Returns the meta object for the attribute '{@link Core.IPackageFragment#getIsDefaultPackage <em>Is Default Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Default Package</em>'.
	 * @see Core.IPackageFragment#getIsDefaultPackage()
	 * @see #getIPackageFragment()
	 * @generated
	 */
	EAttribute getIPackageFragment_IsDefaultPackage();

	/**
	 * Returns the meta object for the container reference '{@link Core.IPackageFragment#getPackageFragmentRoot <em>Package Fragment Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Package Fragment Root</em>'.
	 * @see Core.IPackageFragment#getPackageFragmentRoot()
	 * @see #getIPackageFragment()
	 * @generated
	 */
	EReference getIPackageFragment_PackageFragmentRoot();

	/**
	 * Returns the meta object for the containment reference list '{@link Core.IPackageFragment#getClassFiles <em>Class Files</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Class Files</em>'.
	 * @see Core.IPackageFragment#getClassFiles()
	 * @see #getIPackageFragment()
	 * @generated
	 */
	EReference getIPackageFragment_ClassFiles();

	/**
	 * Returns the meta object for the containment reference list '{@link Core.IPackageFragment#getCompilationUnits <em>Compilation Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Compilation Units</em>'.
	 * @see Core.IPackageFragment#getCompilationUnits()
	 * @see #getIPackageFragment()
	 * @generated
	 */
	EReference getIPackageFragment_CompilationUnits();

	/**
	 * Returns the meta object for class '{@link Core.ITypeRoot <em>IType Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IType Root</em>'.
	 * @see Core.ITypeRoot
	 * @generated
	 */
	EClass getITypeRoot();

	/**
	 * Returns the meta object for class '{@link Core.ICompilationUnit <em>ICompilation Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ICompilation Unit</em>'.
	 * @see Core.ICompilationUnit
	 * @generated
	 */
	EClass getICompilationUnit();

	/**
	 * Returns the meta object for the reference list '{@link Core.ICompilationUnit#getAllType <em>All Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>All Type</em>'.
	 * @see Core.ICompilationUnit#getAllType()
	 * @see #getICompilationUnit()
	 * @generated
	 */
	EReference getICompilationUnit_AllType();

	/**
	 * Returns the meta object for the containment reference list '{@link Core.ICompilationUnit#getImports <em>Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Imports</em>'.
	 * @see Core.ICompilationUnit#getImports()
	 * @see #getICompilationUnit()
	 * @generated
	 */
	EReference getICompilationUnit_Imports();

	/**
	 * Returns the meta object for the containment reference list '{@link Core.ICompilationUnit#getTypes <em>Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Types</em>'.
	 * @see Core.ICompilationUnit#getTypes()
	 * @see #getICompilationUnit()
	 * @generated
	 */
	EReference getICompilationUnit_Types();

	/**
	 * Returns the meta object for the reference '{@link Core.ICompilationUnit#getPrimary <em>Primary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Primary</em>'.
	 * @see Core.ICompilationUnit#getPrimary()
	 * @see #getICompilationUnit()
	 * @generated
	 */
	EReference getICompilationUnit_Primary();

	/**
	 * Returns the meta object for the containment reference '{@link Core.ICompilationUnit#getAst <em>Ast</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ast</em>'.
	 * @see Core.ICompilationUnit#getAst()
	 * @see #getICompilationUnit()
	 * @generated
	 */
	EReference getICompilationUnit_Ast();

	/**
	 * Returns the meta object for class '{@link Core.IClassFile <em>IClass File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IClass File</em>'.
	 * @see Core.IClassFile
	 * @generated
	 */
	EClass getIClassFile();

	/**
	 * Returns the meta object for the attribute '{@link Core.IClassFile#getIsClass <em>Is Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Class</em>'.
	 * @see Core.IClassFile#getIsClass()
	 * @see #getIClassFile()
	 * @generated
	 */
	EAttribute getIClassFile_IsClass();

	/**
	 * Returns the meta object for the attribute '{@link Core.IClassFile#getIsInterface <em>Is Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Interface</em>'.
	 * @see Core.IClassFile#getIsInterface()
	 * @see #getIClassFile()
	 * @generated
	 */
	EAttribute getIClassFile_IsInterface();

	/**
	 * Returns the meta object for the containment reference '{@link Core.IClassFile#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see Core.IClassFile#getType()
	 * @see #getIClassFile()
	 * @generated
	 */
	EReference getIClassFile_Type();

	/**
	 * Returns the meta object for class '{@link Core.ISourceReference <em>ISource Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ISource Reference</em>'.
	 * @see Core.ISourceReference
	 * @generated
	 */
	EClass getISourceReference();

	/**
	 * Returns the meta object for the attribute '{@link Core.ISourceReference#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see Core.ISourceReference#getSource()
	 * @see #getISourceReference()
	 * @generated
	 */
	EAttribute getISourceReference_Source();

	/**
	 * Returns the meta object for the containment reference '{@link Core.ISourceReference#getSourceRange <em>Source Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source Range</em>'.
	 * @see Core.ISourceReference#getSourceRange()
	 * @see #getISourceReference()
	 * @generated
	 */
	EReference getISourceReference_SourceRange();

	/**
	 * Returns the meta object for class '{@link Core.IImportDeclaration <em>IImport Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IImport Declaration</em>'.
	 * @see Core.IImportDeclaration
	 * @generated
	 */
	EClass getIImportDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link Core.IImportDeclaration#getIsOnDemand <em>Is On Demand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is On Demand</em>'.
	 * @see Core.IImportDeclaration#getIsOnDemand()
	 * @see #getIImportDeclaration()
	 * @generated
	 */
	EAttribute getIImportDeclaration_IsOnDemand();

	/**
	 * Returns the meta object for the attribute '{@link Core.IImportDeclaration#getIsStatic <em>Is Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Static</em>'.
	 * @see Core.IImportDeclaration#getIsStatic()
	 * @see #getIImportDeclaration()
	 * @generated
	 */
	EAttribute getIImportDeclaration_IsStatic();

	/**
	 * Returns the meta object for class '{@link Core.ISourceRange <em>ISource Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ISource Range</em>'.
	 * @see Core.ISourceRange
	 * @generated
	 */
	EClass getISourceRange();

	/**
	 * Returns the meta object for the attribute '{@link Core.ISourceRange#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see Core.ISourceRange#getLength()
	 * @see #getISourceRange()
	 * @generated
	 */
	EAttribute getISourceRange_Length();

	/**
	 * Returns the meta object for the attribute '{@link Core.ISourceRange#getOffset <em>Offset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Offset</em>'.
	 * @see Core.ISourceRange#getOffset()
	 * @see #getISourceRange()
	 * @generated
	 */
	EAttribute getISourceRange_Offset();

	/**
	 * Returns the meta object for class '{@link Core.IMember <em>IMember</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IMember</em>'.
	 * @see Core.IMember
	 * @generated
	 */
	EClass getIMember();

	/**
	 * Returns the meta object for the containment reference '{@link Core.IMember#getJavadocRange <em>Javadoc Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Javadoc Range</em>'.
	 * @see Core.IMember#getJavadocRange()
	 * @see #getIMember()
	 * @generated
	 */
	EReference getIMember_JavadocRange();

	/**
	 * Returns the meta object for the containment reference '{@link Core.IMember#getNameRange <em>Name Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name Range</em>'.
	 * @see Core.IMember#getNameRange()
	 * @see #getIMember()
	 * @generated
	 */
	EReference getIMember_NameRange();

	/**
	 * Returns the meta object for class '{@link Core.IType <em>IType</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IType</em>'.
	 * @see Core.IType
	 * @generated
	 */
	EClass getIType();

	/**
	 * Returns the meta object for the attribute '{@link Core.IType#getFullyQualifiedName <em>Fully Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fully Qualified Name</em>'.
	 * @see Core.IType#getFullyQualifiedName()
	 * @see #getIType()
	 * @generated
	 */
	EAttribute getIType_FullyQualifiedName();

	/**
	 * Returns the meta object for the attribute '{@link Core.IType#getFullyQualifiedParametrizedName <em>Fully Qualified Parametrized Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fully Qualified Parametrized Name</em>'.
	 * @see Core.IType#getFullyQualifiedParametrizedName()
	 * @see #getIType()
	 * @generated
	 */
	EAttribute getIType_FullyQualifiedParametrizedName();

	/**
	 * Returns the meta object for the containment reference list '{@link Core.IType#getInitializers <em>Initializers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Initializers</em>'.
	 * @see Core.IType#getInitializers()
	 * @see #getIType()
	 * @generated
	 */
	EReference getIType_Initializers();

	/**
	 * Returns the meta object for the containment reference list '{@link Core.IType#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fields</em>'.
	 * @see Core.IType#getFields()
	 * @see #getIType()
	 * @generated
	 */
	EReference getIType_Fields();

	/**
	 * Returns the meta object for the containment reference list '{@link Core.IType#getMethods <em>Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Methods</em>'.
	 * @see Core.IType#getMethods()
	 * @see #getIType()
	 * @generated
	 */
	EReference getIType_Methods();

	/**
	 * Returns the meta object for the containment reference list '{@link Core.IType#getTypes <em>Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Types</em>'.
	 * @see Core.IType#getTypes()
	 * @see #getIType()
	 * @generated
	 */
	EReference getIType_Types();

	/**
	 * Returns the meta object for the reference list '{@link Core.IType#getTypeParameters <em>Type Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Type Parameters</em>'.
	 * @see Core.IType#getTypeParameters()
	 * @see #getIType()
	 * @generated
	 */
	EReference getIType_TypeParameters();

	/**
	 * Returns the meta object for class '{@link Core.ITypeParameter <em>IType Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IType Parameter</em>'.
	 * @see Core.ITypeParameter
	 * @generated
	 */
	EClass getITypeParameter();

	/**
	 * Returns the meta object for the attribute list '{@link Core.ITypeParameter#getBounds <em>Bounds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Bounds</em>'.
	 * @see Core.ITypeParameter#getBounds()
	 * @see #getITypeParameter()
	 * @generated
	 */
	EAttribute getITypeParameter_Bounds();

	/**
	 * Returns the meta object for class '{@link Core.IInitializer <em>IInitializer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IInitializer</em>'.
	 * @see Core.IInitializer
	 * @generated
	 */
	EClass getIInitializer();

	/**
	 * Returns the meta object for class '{@link Core.IField <em>IField</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IField</em>'.
	 * @see Core.IField
	 * @generated
	 */
	EClass getIField();

	/**
	 * Returns the meta object for the attribute '{@link Core.IField#getConstant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constant</em>'.
	 * @see Core.IField#getConstant()
	 * @see #getIField()
	 * @generated
	 */
	EAttribute getIField_Constant();

	/**
	 * Returns the meta object for the attribute '{@link Core.IField#getIsEnumConstant <em>Is Enum Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Enum Constant</em>'.
	 * @see Core.IField#getIsEnumConstant()
	 * @see #getIField()
	 * @generated
	 */
	EAttribute getIField_IsEnumConstant();

	/**
	 * Returns the meta object for the attribute '{@link Core.IField#getTypeSignature <em>Type Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Signature</em>'.
	 * @see Core.IField#getTypeSignature()
	 * @see #getIField()
	 * @generated
	 */
	EAttribute getIField_TypeSignature();

	/**
	 * Returns the meta object for the attribute '{@link Core.IField#getIsVolatile <em>Is Volatile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Volatile</em>'.
	 * @see Core.IField#getIsVolatile()
	 * @see #getIField()
	 * @generated
	 */
	EAttribute getIField_IsVolatile();

	/**
	 * Returns the meta object for the attribute '{@link Core.IField#getIsTransient <em>Is Transient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Transient</em>'.
	 * @see Core.IField#getIsTransient()
	 * @see #getIField()
	 * @generated
	 */
	EAttribute getIField_IsTransient();

	/**
	 * Returns the meta object for class '{@link Core.IMethod <em>IMethod</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IMethod</em>'.
	 * @see Core.IMethod
	 * @generated
	 */
	EClass getIMethod();

	/**
	 * Returns the meta object for the attribute '{@link Core.IMethod#getReturnType <em>Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Return Type</em>'.
	 * @see Core.IMethod#getReturnType()
	 * @see #getIMethod()
	 * @generated
	 */
	EAttribute getIMethod_ReturnType();

	/**
	 * Returns the meta object for the attribute '{@link Core.IMethod#getIsConstructor <em>Is Constructor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Constructor</em>'.
	 * @see Core.IMethod#getIsConstructor()
	 * @see #getIMethod()
	 * @generated
	 */
	EAttribute getIMethod_IsConstructor();

	/**
	 * Returns the meta object for the attribute '{@link Core.IMethod#getIsMainMethod <em>Is Main Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Main Method</em>'.
	 * @see Core.IMethod#getIsMainMethod()
	 * @see #getIMethod()
	 * @generated
	 */
	EAttribute getIMethod_IsMainMethod();

	/**
	 * Returns the meta object for the containment reference list '{@link Core.IMethod#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see Core.IMethod#getParameters()
	 * @see #getIMethod()
	 * @generated
	 */
	EReference getIMethod_Parameters();

	/**
	 * Returns the meta object for the attribute list '{@link Core.IMethod#getExceptionTypes <em>Exception Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Exception Types</em>'.
	 * @see Core.IMethod#getExceptionTypes()
	 * @see #getIMethod()
	 * @generated
	 */
	EAttribute getIMethod_ExceptionTypes();

	/**
	 * Returns the meta object for class '{@link Core.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see Core.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the attribute '{@link Core.Parameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see Core.Parameter#getName()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Name();

	/**
	 * Returns the meta object for the attribute '{@link Core.Parameter#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see Core.Parameter#getType()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Type();

	/**
	 * Returns the meta object for enum '{@link Core.Modifiers <em>Modifiers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Modifiers</em>'.
	 * @see Core.Modifiers
	 * @generated
	 */
	EEnum getModifiers();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CoreFactory getCoreFactory();

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
		 * The meta object literal for the '{@link Core.impl.IJavaElementImpl <em>IJava Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.IJavaElementImpl
		 * @see Core.impl.CorePackageImpl#getIJavaElement()
		 * @generated
		 */
		EClass IJAVA_ELEMENT = eINSTANCE.getIJavaElement();

		/**
		 * The meta object literal for the '<em><b>Element Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IJAVA_ELEMENT__ELEMENT_NAME = eINSTANCE.getIJavaElement_ElementName();

		/**
		 * The meta object literal for the '{@link Core.impl.PhysicalElementImpl <em>Physical Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.PhysicalElementImpl
		 * @see Core.impl.CorePackageImpl#getPhysicalElement()
		 * @generated
		 */
		EClass PHYSICAL_ELEMENT = eINSTANCE.getPhysicalElement();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHYSICAL_ELEMENT__PATH = eINSTANCE.getPhysicalElement_Path();

		/**
		 * The meta object literal for the '<em><b>Is Read Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHYSICAL_ELEMENT__IS_READ_ONLY = eINSTANCE.getPhysicalElement_IsReadOnly();

		/**
		 * The meta object literal for the '{@link Core.impl.IJavaModelImpl <em>IJava Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.IJavaModelImpl
		 * @see Core.impl.CorePackageImpl#getIJavaModel()
		 * @generated
		 */
		EClass IJAVA_MODEL = eINSTANCE.getIJavaModel();

		/**
		 * The meta object literal for the '<em><b>Java Projects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IJAVA_MODEL__JAVA_PROJECTS = eINSTANCE.getIJavaModel_JavaProjects();

		/**
		 * The meta object literal for the '<em><b>External Package Fragment Roots</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS = eINSTANCE.getIJavaModel_ExternalPackageFragmentRoots();

		/**
		 * The meta object literal for the '{@link Core.impl.IJavaProjectImpl <em>IJava Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.IJavaProjectImpl
		 * @see Core.impl.CorePackageImpl#getIJavaProject()
		 * @generated
		 */
		EClass IJAVA_PROJECT = eINSTANCE.getIJavaProject();

		/**
		 * The meta object literal for the '<em><b>Package Fragment Roots</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS = eINSTANCE.getIJavaProject_PackageFragmentRoots();

		/**
		 * The meta object literal for the '<em><b>External Package Fragment Roots</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS = eINSTANCE.getIJavaProject_ExternalPackageFragmentRoots();

		/**
		 * The meta object literal for the '<em><b>Required Projects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IJAVA_PROJECT__REQUIRED_PROJECTS = eINSTANCE.getIJavaProject_RequiredProjects();

		/**
		 * The meta object literal for the '{@link Core.impl.IPackageFragmentRootImpl <em>IPackage Fragment Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.IPackageFragmentRootImpl
		 * @see Core.impl.CorePackageImpl#getIPackageFragmentRoot()
		 * @generated
		 */
		EClass IPACKAGE_FRAGMENT_ROOT = eINSTANCE.getIPackageFragmentRoot();

		/**
		 * The meta object literal for the '<em><b>Package Fragments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS = eINSTANCE.getIPackageFragmentRoot_PackageFragments();

		/**
		 * The meta object literal for the '{@link Core.impl.BinaryPackageFragmentRootImpl <em>Binary Package Fragment Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.BinaryPackageFragmentRootImpl
		 * @see Core.impl.CorePackageImpl#getBinaryPackageFragmentRoot()
		 * @generated
		 */
		EClass BINARY_PACKAGE_FRAGMENT_ROOT = eINSTANCE.getBinaryPackageFragmentRoot();

		/**
		 * The meta object literal for the '{@link Core.impl.SourcePackageFragmentRootImpl <em>Source Package Fragment Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.SourcePackageFragmentRootImpl
		 * @see Core.impl.CorePackageImpl#getSourcePackageFragmentRoot()
		 * @generated
		 */
		EClass SOURCE_PACKAGE_FRAGMENT_ROOT = eINSTANCE.getSourcePackageFragmentRoot();

		/**
		 * The meta object literal for the '{@link Core.impl.IPackageFragmentImpl <em>IPackage Fragment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.IPackageFragmentImpl
		 * @see Core.impl.CorePackageImpl#getIPackageFragment()
		 * @generated
		 */
		EClass IPACKAGE_FRAGMENT = eINSTANCE.getIPackageFragment();

		/**
		 * The meta object literal for the '<em><b>Is Default Package</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IPACKAGE_FRAGMENT__IS_DEFAULT_PACKAGE = eINSTANCE.getIPackageFragment_IsDefaultPackage();

		/**
		 * The meta object literal for the '<em><b>Package Fragment Root</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT = eINSTANCE.getIPackageFragment_PackageFragmentRoot();

		/**
		 * The meta object literal for the '<em><b>Class Files</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IPACKAGE_FRAGMENT__CLASS_FILES = eINSTANCE.getIPackageFragment_ClassFiles();

		/**
		 * The meta object literal for the '<em><b>Compilation Units</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IPACKAGE_FRAGMENT__COMPILATION_UNITS = eINSTANCE.getIPackageFragment_CompilationUnits();

		/**
		 * The meta object literal for the '{@link Core.impl.ITypeRootImpl <em>IType Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.ITypeRootImpl
		 * @see Core.impl.CorePackageImpl#getITypeRoot()
		 * @generated
		 */
		EClass ITYPE_ROOT = eINSTANCE.getITypeRoot();

		/**
		 * The meta object literal for the '{@link Core.impl.ICompilationUnitImpl <em>ICompilation Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.ICompilationUnitImpl
		 * @see Core.impl.CorePackageImpl#getICompilationUnit()
		 * @generated
		 */
		EClass ICOMPILATION_UNIT = eINSTANCE.getICompilationUnit();

		/**
		 * The meta object literal for the '<em><b>All Type</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ICOMPILATION_UNIT__ALL_TYPE = eINSTANCE.getICompilationUnit_AllType();

		/**
		 * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ICOMPILATION_UNIT__IMPORTS = eINSTANCE.getICompilationUnit_Imports();

		/**
		 * The meta object literal for the '<em><b>Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ICOMPILATION_UNIT__TYPES = eINSTANCE.getICompilationUnit_Types();

		/**
		 * The meta object literal for the '<em><b>Primary</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ICOMPILATION_UNIT__PRIMARY = eINSTANCE.getICompilationUnit_Primary();

		/**
		 * The meta object literal for the '<em><b>Ast</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ICOMPILATION_UNIT__AST = eINSTANCE.getICompilationUnit_Ast();

		/**
		 * The meta object literal for the '{@link Core.impl.IClassFileImpl <em>IClass File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.IClassFileImpl
		 * @see Core.impl.CorePackageImpl#getIClassFile()
		 * @generated
		 */
		EClass ICLASS_FILE = eINSTANCE.getIClassFile();

		/**
		 * The meta object literal for the '<em><b>Is Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ICLASS_FILE__IS_CLASS = eINSTANCE.getIClassFile_IsClass();

		/**
		 * The meta object literal for the '<em><b>Is Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ICLASS_FILE__IS_INTERFACE = eINSTANCE.getIClassFile_IsInterface();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ICLASS_FILE__TYPE = eINSTANCE.getIClassFile_Type();

		/**
		 * The meta object literal for the '{@link Core.impl.ISourceReferenceImpl <em>ISource Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.ISourceReferenceImpl
		 * @see Core.impl.CorePackageImpl#getISourceReference()
		 * @generated
		 */
		EClass ISOURCE_REFERENCE = eINSTANCE.getISourceReference();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ISOURCE_REFERENCE__SOURCE = eINSTANCE.getISourceReference_Source();

		/**
		 * The meta object literal for the '<em><b>Source Range</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISOURCE_REFERENCE__SOURCE_RANGE = eINSTANCE.getISourceReference_SourceRange();

		/**
		 * The meta object literal for the '{@link Core.impl.IImportDeclarationImpl <em>IImport Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.IImportDeclarationImpl
		 * @see Core.impl.CorePackageImpl#getIImportDeclaration()
		 * @generated
		 */
		EClass IIMPORT_DECLARATION = eINSTANCE.getIImportDeclaration();

		/**
		 * The meta object literal for the '<em><b>Is On Demand</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IIMPORT_DECLARATION__IS_ON_DEMAND = eINSTANCE.getIImportDeclaration_IsOnDemand();

		/**
		 * The meta object literal for the '<em><b>Is Static</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IIMPORT_DECLARATION__IS_STATIC = eINSTANCE.getIImportDeclaration_IsStatic();

		/**
		 * The meta object literal for the '{@link Core.impl.ISourceRangeImpl <em>ISource Range</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.ISourceRangeImpl
		 * @see Core.impl.CorePackageImpl#getISourceRange()
		 * @generated
		 */
		EClass ISOURCE_RANGE = eINSTANCE.getISourceRange();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ISOURCE_RANGE__LENGTH = eINSTANCE.getISourceRange_Length();

		/**
		 * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ISOURCE_RANGE__OFFSET = eINSTANCE.getISourceRange_Offset();

		/**
		 * The meta object literal for the '{@link Core.impl.IMemberImpl <em>IMember</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.IMemberImpl
		 * @see Core.impl.CorePackageImpl#getIMember()
		 * @generated
		 */
		EClass IMEMBER = eINSTANCE.getIMember();

		/**
		 * The meta object literal for the '<em><b>Javadoc Range</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMEMBER__JAVADOC_RANGE = eINSTANCE.getIMember_JavadocRange();

		/**
		 * The meta object literal for the '<em><b>Name Range</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMEMBER__NAME_RANGE = eINSTANCE.getIMember_NameRange();

		/**
		 * The meta object literal for the '{@link Core.impl.ITypeImpl <em>IType</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.ITypeImpl
		 * @see Core.impl.CorePackageImpl#getIType()
		 * @generated
		 */
		EClass ITYPE = eINSTANCE.getIType();

		/**
		 * The meta object literal for the '<em><b>Fully Qualified Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ITYPE__FULLY_QUALIFIED_NAME = eINSTANCE.getIType_FullyQualifiedName();

		/**
		 * The meta object literal for the '<em><b>Fully Qualified Parametrized Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ITYPE__FULLY_QUALIFIED_PARAMETRIZED_NAME = eINSTANCE.getIType_FullyQualifiedParametrizedName();

		/**
		 * The meta object literal for the '<em><b>Initializers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITYPE__INITIALIZERS = eINSTANCE.getIType_Initializers();

		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITYPE__FIELDS = eINSTANCE.getIType_Fields();

		/**
		 * The meta object literal for the '<em><b>Methods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITYPE__METHODS = eINSTANCE.getIType_Methods();

		/**
		 * The meta object literal for the '<em><b>Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITYPE__TYPES = eINSTANCE.getIType_Types();

		/**
		 * The meta object literal for the '<em><b>Type Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITYPE__TYPE_PARAMETERS = eINSTANCE.getIType_TypeParameters();

		/**
		 * The meta object literal for the '{@link Core.impl.ITypeParameterImpl <em>IType Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.ITypeParameterImpl
		 * @see Core.impl.CorePackageImpl#getITypeParameter()
		 * @generated
		 */
		EClass ITYPE_PARAMETER = eINSTANCE.getITypeParameter();

		/**
		 * The meta object literal for the '<em><b>Bounds</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ITYPE_PARAMETER__BOUNDS = eINSTANCE.getITypeParameter_Bounds();

		/**
		 * The meta object literal for the '{@link Core.impl.IInitializerImpl <em>IInitializer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.IInitializerImpl
		 * @see Core.impl.CorePackageImpl#getIInitializer()
		 * @generated
		 */
		EClass IINITIALIZER = eINSTANCE.getIInitializer();

		/**
		 * The meta object literal for the '{@link Core.impl.IFieldImpl <em>IField</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.IFieldImpl
		 * @see Core.impl.CorePackageImpl#getIField()
		 * @generated
		 */
		EClass IFIELD = eINSTANCE.getIField();

		/**
		 * The meta object literal for the '<em><b>Constant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IFIELD__CONSTANT = eINSTANCE.getIField_Constant();

		/**
		 * The meta object literal for the '<em><b>Is Enum Constant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IFIELD__IS_ENUM_CONSTANT = eINSTANCE.getIField_IsEnumConstant();

		/**
		 * The meta object literal for the '<em><b>Type Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IFIELD__TYPE_SIGNATURE = eINSTANCE.getIField_TypeSignature();

		/**
		 * The meta object literal for the '<em><b>Is Volatile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IFIELD__IS_VOLATILE = eINSTANCE.getIField_IsVolatile();

		/**
		 * The meta object literal for the '<em><b>Is Transient</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IFIELD__IS_TRANSIENT = eINSTANCE.getIField_IsTransient();

		/**
		 * The meta object literal for the '{@link Core.impl.IMethodImpl <em>IMethod</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.IMethodImpl
		 * @see Core.impl.CorePackageImpl#getIMethod()
		 * @generated
		 */
		EClass IMETHOD = eINSTANCE.getIMethod();

		/**
		 * The meta object literal for the '<em><b>Return Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMETHOD__RETURN_TYPE = eINSTANCE.getIMethod_ReturnType();

		/**
		 * The meta object literal for the '<em><b>Is Constructor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMETHOD__IS_CONSTRUCTOR = eINSTANCE.getIMethod_IsConstructor();

		/**
		 * The meta object literal for the '<em><b>Is Main Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMETHOD__IS_MAIN_METHOD = eINSTANCE.getIMethod_IsMainMethod();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMETHOD__PARAMETERS = eINSTANCE.getIMethod_Parameters();

		/**
		 * The meta object literal for the '<em><b>Exception Types</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMETHOD__EXCEPTION_TYPES = eINSTANCE.getIMethod_ExceptionTypes();

		/**
		 * The meta object literal for the '{@link Core.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.impl.ParameterImpl
		 * @see Core.impl.CorePackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__NAME = eINSTANCE.getParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__TYPE = eINSTANCE.getParameter_Type();

		/**
		 * The meta object literal for the '{@link Core.Modifiers <em>Modifiers</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Core.Modifiers
		 * @see Core.impl.CorePackageImpl#getModifiers()
		 * @generated
		 */
		EEnum MODIFIERS = eINSTANCE.getModifiers();

	}

} //CorePackage
