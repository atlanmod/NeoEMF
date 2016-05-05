package fr.inria.atlanmod.neoemf.test.commons.models.mapSample;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage
 * @generated
 */
public interface MapSampleFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    MapSampleFactory eINSTANCE = fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.MapSampleFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Sample Model</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Sample Model</em>'.
     * @generated
     */
    SampleModel createSampleModel();

    /**
     * Returns a new object of class '<em>K</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>K</em>'.
     * @generated
     */
    K createK();

    /**
     * Returns a new object of class '<em>V</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>V</em>'.
     * @generated
     */
    V createV();

    /**
     * Returns a new object of class '<em>Sample Model Content Object</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Sample Model Content Object</em>'.
     * @generated
     */
    SampleModelContentObject createSampleModelContentObject();

    /**
     * Returns a new object of class '<em>Pack</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Pack</em>'.
     * @generated
     */
	Pack createPack();

				/**
     * Returns a new object of class '<em>Pack Content</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Pack Content</em>'.
     * @generated
     */
	PackContent createPackContent();

				/**
     * Returns a new object of class '<em>Specialized Pack Content</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Specialized Pack Content</em>'.
     * @generated
     */
    SpecializedPackContent createSpecializedPackContent();

                /**
     * Returns a new object of class '<em>Pack Content2</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Pack Content2</em>'.
     * @generated
     */
    PackContent2 createPackContent2();

                /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    MapSamplePackage getMapSamplePackage();

} //MapSampleFactory
