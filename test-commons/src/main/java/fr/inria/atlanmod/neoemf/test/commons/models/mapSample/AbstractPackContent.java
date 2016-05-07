package fr.inria.atlanmod.neoemf.test.commons.models.mapSample;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Pack Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.AbstractPackContent#getParentPack <em>Parent Pack</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.AbstractPackContent#getName <em>Name</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.AbstractPackContent#getContainmentNoOppositeRefComment <em>Containment No Opposite Ref Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getAbstractPackContent()
 * @model abstract="true"
 * @extends PersistentEObject
 * @generated
 */
public interface AbstractPackContent extends PersistentEObject {
    /**
     * Returns the value of the '<em><b>Parent Pack</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getOwnedContents <em>Owned Contents</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent Pack</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent Pack</em>' container reference.
     * @see #setParentPack(Pack)
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getAbstractPackContent_ParentPack()
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getOwnedContents
     * @model opposite="ownedContents" transient="false"
     * @generated
     */
    Pack getParentPack();

    /**
     * Sets the value of the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.AbstractPackContent#getParentPack <em>Parent Pack</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent Pack</em>' container reference.
     * @see #getParentPack()
     * @generated
     */
    void setParentPack(Pack value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getAbstractPackContent_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.AbstractPackContent#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Containment No Opposite Ref Comment</b></em>' containment reference list.
     * The list contents are of type {@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.AbstractPackContentComment}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Containment No Opposite Ref Comment</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Containment No Opposite Ref Comment</em>' containment reference list.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getAbstractPackContent_ContainmentNoOppositeRefComment()
     * @model containment="true"
     * @generated
     */
    EList<AbstractPackContentComment> getContainmentNoOppositeRefComment();

} // AbstractPackContent
