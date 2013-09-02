/**
 *
 * $Id$
 */
package DOM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.AbstractTypeDeclaration#getBodyDeclarations <em>Body Declarations</em>}</li>
 *   <li>{@link DOM.AbstractTypeDeclaration#getName <em>Name</em>}</li>
 *   <li>{@link DOM.AbstractTypeDeclaration#getLocalTypeDeclaration <em>Local Type Declaration</em>}</li>
 *   <li>{@link DOM.AbstractTypeDeclaration#getMemberTypeDeclaration <em>Member Type Declaration</em>}</li>
 *   <li>{@link DOM.AbstractTypeDeclaration#getPackageMemberTypeDeclaration <em>Package Member Type Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getAbstractTypeDeclaration()
 * @model abstract="true"
 * @generated
 */
public interface AbstractTypeDeclaration extends BodyDeclaration {

	/**
	 * Returns the value of the '<em><b>Body Declarations</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.BodyDeclaration}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Body Declarations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Declarations</em>' containment reference list.
	 * @see DOM.DOMPackage#getAbstractTypeDeclaration_BodyDeclarations()
	 * @model containment="true"
	 * @generated
	 */
	EList<BodyDeclaration> getBodyDeclarations();
	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(SimpleName)
	 * @see DOM.DOMPackage#getAbstractTypeDeclaration_Name()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	SimpleName getName();
	/**
	 * Sets the value of the '{@link DOM.AbstractTypeDeclaration#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(SimpleName value);

	/**
	 * Returns the value of the '<em><b>Local Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Local Type Declaration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Type Declaration</em>' attribute.
	 * @see #setLocalTypeDeclaration(Boolean)
	 * @see DOM.DOMPackage#getAbstractTypeDeclaration_LocalTypeDeclaration()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getLocalTypeDeclaration();
	/**
	 * Sets the value of the '{@link DOM.AbstractTypeDeclaration#getLocalTypeDeclaration <em>Local Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local Type Declaration</em>' attribute.
	 * @see #getLocalTypeDeclaration()
	 * @generated
	 */
	void setLocalTypeDeclaration(Boolean value);

	/**
	 * Returns the value of the '<em><b>Member Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Member Type Declaration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Member Type Declaration</em>' attribute.
	 * @see #setMemberTypeDeclaration(Boolean)
	 * @see DOM.DOMPackage#getAbstractTypeDeclaration_MemberTypeDeclaration()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getMemberTypeDeclaration();
	/**
	 * Sets the value of the '{@link DOM.AbstractTypeDeclaration#getMemberTypeDeclaration <em>Member Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Member Type Declaration</em>' attribute.
	 * @see #getMemberTypeDeclaration()
	 * @generated
	 */
	void setMemberTypeDeclaration(Boolean value);

	/**
	 * Returns the value of the '<em><b>Package Member Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Package Member Type Declaration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Member Type Declaration</em>' attribute.
	 * @see #setPackageMemberTypeDeclaration(Boolean)
	 * @see DOM.DOMPackage#getAbstractTypeDeclaration_PackageMemberTypeDeclaration()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getPackageMemberTypeDeclaration();
	/**
	 * Sets the value of the '{@link DOM.AbstractTypeDeclaration#getPackageMemberTypeDeclaration <em>Package Member Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Member Type Declaration</em>' attribute.
	 * @see #getPackageMemberTypeDeclaration()
	 * @generated
	 */
	void setPackageMemberTypeDeclaration(Boolean value);




// data Class generation 
} // AbstractTypeDeclaration
