/**
 *
 * $Id$
 */
package Core.util;

import Core.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see Core.CorePackage
 * @generated
 */
public class CoreSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CorePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreSwitch() {
		if (modelPackage == null) {
			modelPackage = CorePackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case CorePackage.IJAVA_ELEMENT: {
				IJavaElement iJavaElement = (IJavaElement)theEObject;
				T result = caseIJavaElement(iJavaElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.PHYSICAL_ELEMENT: {
				PhysicalElement physicalElement = (PhysicalElement)theEObject;
				T result = casePhysicalElement(physicalElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.IJAVA_MODEL: {
				IJavaModel iJavaModel = (IJavaModel)theEObject;
				T result = caseIJavaModel(iJavaModel);
				if (result == null) result = casePhysicalElement(iJavaModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.IJAVA_PROJECT: {
				IJavaProject iJavaProject = (IJavaProject)theEObject;
				T result = caseIJavaProject(iJavaProject);
				if (result == null) result = caseIJavaElement(iJavaProject);
				if (result == null) result = casePhysicalElement(iJavaProject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.IPACKAGE_FRAGMENT_ROOT: {
				IPackageFragmentRoot iPackageFragmentRoot = (IPackageFragmentRoot)theEObject;
				T result = caseIPackageFragmentRoot(iPackageFragmentRoot);
				if (result == null) result = caseIJavaElement(iPackageFragmentRoot);
				if (result == null) result = casePhysicalElement(iPackageFragmentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.BINARY_PACKAGE_FRAGMENT_ROOT: {
				BinaryPackageFragmentRoot binaryPackageFragmentRoot = (BinaryPackageFragmentRoot)theEObject;
				T result = caseBinaryPackageFragmentRoot(binaryPackageFragmentRoot);
				if (result == null) result = caseIPackageFragmentRoot(binaryPackageFragmentRoot);
				if (result == null) result = caseIJavaElement(binaryPackageFragmentRoot);
				if (result == null) result = casePhysicalElement(binaryPackageFragmentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.SOURCE_PACKAGE_FRAGMENT_ROOT: {
				SourcePackageFragmentRoot sourcePackageFragmentRoot = (SourcePackageFragmentRoot)theEObject;
				T result = caseSourcePackageFragmentRoot(sourcePackageFragmentRoot);
				if (result == null) result = caseIPackageFragmentRoot(sourcePackageFragmentRoot);
				if (result == null) result = caseIJavaElement(sourcePackageFragmentRoot);
				if (result == null) result = casePhysicalElement(sourcePackageFragmentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.IPACKAGE_FRAGMENT: {
				IPackageFragment iPackageFragment = (IPackageFragment)theEObject;
				T result = caseIPackageFragment(iPackageFragment);
				if (result == null) result = caseIJavaElement(iPackageFragment);
				if (result == null) result = casePhysicalElement(iPackageFragment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.ITYPE_ROOT: {
				ITypeRoot iTypeRoot = (ITypeRoot)theEObject;
				T result = caseITypeRoot(iTypeRoot);
				if (result == null) result = caseIJavaElement(iTypeRoot);
				if (result == null) result = caseISourceReference(iTypeRoot);
				if (result == null) result = casePhysicalElement(iTypeRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.ICOMPILATION_UNIT: {
				ICompilationUnit iCompilationUnit = (ICompilationUnit)theEObject;
				T result = caseICompilationUnit(iCompilationUnit);
				if (result == null) result = caseITypeRoot(iCompilationUnit);
				if (result == null) result = caseIJavaElement(iCompilationUnit);
				if (result == null) result = caseISourceReference(iCompilationUnit);
				if (result == null) result = casePhysicalElement(iCompilationUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.ICLASS_FILE: {
				IClassFile iClassFile = (IClassFile)theEObject;
				T result = caseIClassFile(iClassFile);
				if (result == null) result = caseITypeRoot(iClassFile);
				if (result == null) result = caseIJavaElement(iClassFile);
				if (result == null) result = caseISourceReference(iClassFile);
				if (result == null) result = casePhysicalElement(iClassFile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.ISOURCE_REFERENCE: {
				ISourceReference iSourceReference = (ISourceReference)theEObject;
				T result = caseISourceReference(iSourceReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.IIMPORT_DECLARATION: {
				IImportDeclaration iImportDeclaration = (IImportDeclaration)theEObject;
				T result = caseIImportDeclaration(iImportDeclaration);
				if (result == null) result = caseIJavaElement(iImportDeclaration);
				if (result == null) result = caseISourceReference(iImportDeclaration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.ISOURCE_RANGE: {
				ISourceRange iSourceRange = (ISourceRange)theEObject;
				T result = caseISourceRange(iSourceRange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.IMEMBER: {
				IMember iMember = (IMember)theEObject;
				T result = caseIMember(iMember);
				if (result == null) result = caseIJavaElement(iMember);
				if (result == null) result = caseISourceReference(iMember);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.ITYPE: {
				IType iType = (IType)theEObject;
				T result = caseIType(iType);
				if (result == null) result = caseIMember(iType);
				if (result == null) result = caseIJavaElement(iType);
				if (result == null) result = caseISourceReference(iType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.ITYPE_PARAMETER: {
				ITypeParameter iTypeParameter = (ITypeParameter)theEObject;
				T result = caseITypeParameter(iTypeParameter);
				if (result == null) result = caseIJavaElement(iTypeParameter);
				if (result == null) result = caseISourceReference(iTypeParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.IINITIALIZER: {
				IInitializer iInitializer = (IInitializer)theEObject;
				T result = caseIInitializer(iInitializer);
				if (result == null) result = caseIMember(iInitializer);
				if (result == null) result = caseIJavaElement(iInitializer);
				if (result == null) result = caseISourceReference(iInitializer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.IFIELD: {
				IField iField = (IField)theEObject;
				T result = caseIField(iField);
				if (result == null) result = caseIMember(iField);
				if (result == null) result = caseIJavaElement(iField);
				if (result == null) result = caseISourceReference(iField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.IMETHOD: {
				IMethod iMethod = (IMethod)theEObject;
				T result = caseIMethod(iMethod);
				if (result == null) result = caseIMember(iMethod);
				if (result == null) result = caseIJavaElement(iMethod);
				if (result == null) result = caseISourceReference(iMethod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.PARAMETER: {
				Parameter parameter = (Parameter)theEObject;
				T result = caseParameter(parameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IJava Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IJava Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIJavaElement(IJavaElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Physical Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Physical Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePhysicalElement(PhysicalElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IJava Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IJava Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIJavaModel(IJavaModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IJava Project</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IJava Project</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIJavaProject(IJavaProject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IPackage Fragment Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IPackage Fragment Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIPackageFragmentRoot(IPackageFragmentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binary Package Fragment Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binary Package Fragment Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBinaryPackageFragmentRoot(BinaryPackageFragmentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Source Package Fragment Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Source Package Fragment Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSourcePackageFragmentRoot(SourcePackageFragmentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IPackage Fragment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IPackage Fragment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIPackageFragment(IPackageFragment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IType Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IType Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseITypeRoot(ITypeRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ICompilation Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ICompilation Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseICompilationUnit(ICompilationUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IClass File</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IClass File</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIClassFile(IClassFile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ISource Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ISource Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseISourceReference(ISourceReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IImport Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IImport Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIImportDeclaration(IImportDeclaration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ISource Range</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ISource Range</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseISourceRange(ISourceRange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IMember</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IMember</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIMember(IMember object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IType</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IType</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIType(IType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IType Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IType Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseITypeParameter(ITypeParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IInitializer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IInitializer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIInitializer(IInitializer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IField</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IField</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIField(IField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IMethod</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IMethod</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIMethod(IMethod object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameter(Parameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //CoreSwitch
