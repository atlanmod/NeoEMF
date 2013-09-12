/**
 *
 * $Id$
 */
package Core.impl;



import Core.*;

import Core.util.CoreAdapterFactory;

import fr.inria.atlanmod.neo4emf.change.impl.ChangeLog;
import fr.inria.atlanmod.neo4emf.change.impl.NewObject;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CoreFactoryImpl extends EFactoryImpl implements CoreFactory {

	
	/**
	 * AdapterFactory instance
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	protected CoreAdapterFactory adapterFactory = new CoreAdapterFactory();
	
	
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	
	public static CoreFactory init() {
		try {
			CoreFactory theCoreFactory = (CoreFactory)EPackage.Registry.INSTANCE.getEFactory("org.amma.dsl.jdt.core"); 
			if (theCoreFactory != null) {
				return theCoreFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CoreFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CorePackage.IJAVA_MODEL: return (EObject)createIJavaModel();
			case CorePackage.IJAVA_PROJECT: return (EObject)createIJavaProject();
			case CorePackage.BINARY_PACKAGE_FRAGMENT_ROOT: return (EObject)createBinaryPackageFragmentRoot();
			case CorePackage.SOURCE_PACKAGE_FRAGMENT_ROOT: return (EObject)createSourcePackageFragmentRoot();
			case CorePackage.IPACKAGE_FRAGMENT: return (EObject)createIPackageFragment();
			case CorePackage.ICOMPILATION_UNIT: return (EObject)createICompilationUnit();
			case CorePackage.ICLASS_FILE: return (EObject)createIClassFile();
			case CorePackage.IIMPORT_DECLARATION: return (EObject)createIImportDeclaration();
			case CorePackage.ISOURCE_RANGE: return (EObject)createISourceRange();
			case CorePackage.ITYPE: return (EObject)createIType();
			case CorePackage.ITYPE_PARAMETER: return (EObject)createITypeParameter();
			case CorePackage.IINITIALIZER: return (EObject)createIInitializer();
			case CorePackage.IFIELD: return (EObject)createIField();
			case CorePackage.IMETHOD: return (EObject)createIMethod();
			case CorePackage.PARAMETER: return (EObject)createParameter();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case CorePackage.MODIFIERS:
				return createModifiersFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case CorePackage.MODIFIERS:
				return convertModifiersToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IJavaModel createIJavaModel() {
		IJavaModelImpl iJavaModel = new IJavaModelImpl();
		iJavaModel.eAdapters().add(adapterFactory.createIJavaModelAdapter());
		ChangeLog.getInstance().add(new NewObject(iJavaModel));
		return iJavaModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IJavaProject createIJavaProject() {
		IJavaProjectImpl iJavaProject = new IJavaProjectImpl();
		iJavaProject.eAdapters().add(adapterFactory.createIJavaProjectAdapter());
		ChangeLog.getInstance().add(new NewObject(iJavaProject));
		return iJavaProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryPackageFragmentRoot createBinaryPackageFragmentRoot() {
		BinaryPackageFragmentRootImpl binaryPackageFragmentRoot = new BinaryPackageFragmentRootImpl();
		binaryPackageFragmentRoot.eAdapters().add(adapterFactory.createBinaryPackageFragmentRootAdapter());
		ChangeLog.getInstance().add(new NewObject(binaryPackageFragmentRoot));
		return binaryPackageFragmentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourcePackageFragmentRoot createSourcePackageFragmentRoot() {
		SourcePackageFragmentRootImpl sourcePackageFragmentRoot = new SourcePackageFragmentRootImpl();
		sourcePackageFragmentRoot.eAdapters().add(adapterFactory.createSourcePackageFragmentRootAdapter());
		ChangeLog.getInstance().add(new NewObject(sourcePackageFragmentRoot));
		return sourcePackageFragmentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IPackageFragment createIPackageFragment() {
		IPackageFragmentImpl iPackageFragment = new IPackageFragmentImpl();
		iPackageFragment.eAdapters().add(adapterFactory.createIPackageFragmentAdapter());
		ChangeLog.getInstance().add(new NewObject(iPackageFragment));
		return iPackageFragment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ICompilationUnit createICompilationUnit() {
		ICompilationUnitImpl iCompilationUnit = new ICompilationUnitImpl();
		iCompilationUnit.eAdapters().add(adapterFactory.createICompilationUnitAdapter());
		ChangeLog.getInstance().add(new NewObject(iCompilationUnit));
		return iCompilationUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IClassFile createIClassFile() {
		IClassFileImpl iClassFile = new IClassFileImpl();
		iClassFile.eAdapters().add(adapterFactory.createIClassFileAdapter());
		ChangeLog.getInstance().add(new NewObject(iClassFile));
		return iClassFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IImportDeclaration createIImportDeclaration() {
		IImportDeclarationImpl iImportDeclaration = new IImportDeclarationImpl();
		iImportDeclaration.eAdapters().add(adapterFactory.createIImportDeclarationAdapter());
		ChangeLog.getInstance().add(new NewObject(iImportDeclaration));
		return iImportDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ISourceRange createISourceRange() {
		ISourceRangeImpl iSourceRange = new ISourceRangeImpl();
		iSourceRange.eAdapters().add(adapterFactory.createISourceRangeAdapter());
		ChangeLog.getInstance().add(new NewObject(iSourceRange));
		return iSourceRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IType createIType() {
		ITypeImpl iType = new ITypeImpl();
		iType.eAdapters().add(adapterFactory.createITypeAdapter());
		ChangeLog.getInstance().add(new NewObject(iType));
		return iType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ITypeParameter createITypeParameter() {
		ITypeParameterImpl iTypeParameter = new ITypeParameterImpl();
		iTypeParameter.eAdapters().add(adapterFactory.createITypeParameterAdapter());
		ChangeLog.getInstance().add(new NewObject(iTypeParameter));
		return iTypeParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IInitializer createIInitializer() {
		IInitializerImpl iInitializer = new IInitializerImpl();
		iInitializer.eAdapters().add(adapterFactory.createIInitializerAdapter());
		ChangeLog.getInstance().add(new NewObject(iInitializer));
		return iInitializer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IField createIField() {
		IFieldImpl iField = new IFieldImpl();
		iField.eAdapters().add(adapterFactory.createIFieldAdapter());
		ChangeLog.getInstance().add(new NewObject(iField));
		return iField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IMethod createIMethod() {
		IMethodImpl iMethod = new IMethodImpl();
		iMethod.eAdapters().add(adapterFactory.createIMethodAdapter());
		ChangeLog.getInstance().add(new NewObject(iMethod));
		return iMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		parameter.eAdapters().add(adapterFactory.createParameterAdapter());
		ChangeLog.getInstance().add(new NewObject(parameter));
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Modifiers createModifiersFromString(EDataType eDataType, String initialValue) {
		Modifiers result = Modifiers.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertModifiersToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorePackage getCorePackage() {
		return (CorePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CorePackage getPackage() {
		return CorePackage.eINSTANCE;
	}

} //CoreFactoryImpl
