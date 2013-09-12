/**
 *
 * $Id$
 */
package Core;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see Core.CorePackage
 * @generated
 */
public interface CoreFactory extends EFactory {
	
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	
	CoreFactory eINSTANCE = Core.impl.CoreFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>IJava Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IJava Model</em>'.
	 * @generated
	 */
	
	IJavaModel createIJavaModel();

	/**
	 * Returns a new object of class '<em>IJava Project</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IJava Project</em>'.
	 * @generated
	 */
	
	IJavaProject createIJavaProject();

	/**
	 * Returns a new object of class '<em>Binary Package Fragment Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binary Package Fragment Root</em>'.
	 * @generated
	 */
	
	BinaryPackageFragmentRoot createBinaryPackageFragmentRoot();

	/**
	 * Returns a new object of class '<em>Source Package Fragment Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Source Package Fragment Root</em>'.
	 * @generated
	 */
	SourcePackageFragmentRoot createSourcePackageFragmentRoot();

	/**
	 * Returns a new object of class '<em>IPackage Fragment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IPackage Fragment</em>'.
	 * @generated
	 */
	IPackageFragment createIPackageFragment();

	/**
	 * Returns a new object of class '<em>ICompilation Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ICompilation Unit</em>'.
	 * @generated
	 */
	ICompilationUnit createICompilationUnit();

	/**
	 * Returns a new object of class '<em>IClass File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IClass File</em>'.
	 * @generated
	 */
	IClassFile createIClassFile();

	/**
	 * Returns a new object of class '<em>IImport Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IImport Declaration</em>'.
	 * @generated
	 */
	IImportDeclaration createIImportDeclaration();

	/**
	 * Returns a new object of class '<em>ISource Range</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ISource Range</em>'.
	 * @generated
	 */
	ISourceRange createISourceRange();

	/**
	 * Returns a new object of class '<em>IType</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IType</em>'.
	 * @generated
	 */
	IType createIType();

	/**
	 * Returns a new object of class '<em>IType Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IType Parameter</em>'.
	 * @generated
	 */
	ITypeParameter createITypeParameter();

	/**
	 * Returns a new object of class '<em>IInitializer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IInitializer</em>'.
	 * @generated
	 */
	IInitializer createIInitializer();

	/**
	 * Returns a new object of class '<em>IField</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IField</em>'.
	 * @generated
	 */
	IField createIField();

	/**
	 * Returns a new object of class '<em>IMethod</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IMethod</em>'.
	 * @generated
	 */
	IMethod createIMethod();

	/**
	 * Returns a new object of class '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter</em>'.
	 * @generated
	 */
	Parameter createParameter();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CorePackage getCorePackage();

} //CoreFactory
