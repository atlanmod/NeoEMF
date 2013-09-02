/**
 *
 * $Id$
 */
package Core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IType</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Core.IType#getFullyQualifiedName <em>Fully Qualified Name</em>}</li>
 *   <li>{@link Core.IType#getFullyQualifiedParametrizedName <em>Fully Qualified Parametrized Name</em>}</li>
 *   <li>{@link Core.IType#getInitializers <em>Initializers</em>}</li>
 *   <li>{@link Core.IType#getFields <em>Fields</em>}</li>
 *   <li>{@link Core.IType#getMethods <em>Methods</em>}</li>
 *   <li>{@link Core.IType#getTypes <em>Types</em>}</li>
 *   <li>{@link Core.IType#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see Core.CorePackage#getIType()
 * @model
 * @generated
 */
public interface IType extends IMember {

	/**
	 * Returns the value of the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Fully Qualified Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fully Qualified Name</em>' attribute.
	 * @see #setFullyQualifiedName(String)
	 * @see Core.CorePackage#getIType_FullyQualifiedName()
	 * @model unique="false" dataType="PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getFullyQualifiedName();
	/**
	 * Sets the value of the '{@link Core.IType#getFullyQualifiedName <em>Fully Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fully Qualified Name</em>' attribute.
	 * @see #getFullyQualifiedName()
	 * @generated
	 */
	void setFullyQualifiedName(String value);

	/**
	 * Returns the value of the '<em><b>Fully Qualified Parametrized Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Fully Qualified Parametrized Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fully Qualified Parametrized Name</em>' attribute.
	 * @see #setFullyQualifiedParametrizedName(String)
	 * @see Core.CorePackage#getIType_FullyQualifiedParametrizedName()
	 * @model unique="false" dataType="PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getFullyQualifiedParametrizedName();
	/**
	 * Sets the value of the '{@link Core.IType#getFullyQualifiedParametrizedName <em>Fully Qualified Parametrized Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fully Qualified Parametrized Name</em>' attribute.
	 * @see #getFullyQualifiedParametrizedName()
	 * @generated
	 */
	void setFullyQualifiedParametrizedName(String value);

	/**
	 * Returns the value of the '<em><b>Initializers</b></em>' containment reference list.
	 * The list contents are of type {@link Core.IInitializer}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Initializers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initializers</em>' containment reference list.
	 * @see Core.CorePackage#getIType_Initializers()
	 * @model containment="true"
	 * @generated
	 */
	EList<IInitializer> getInitializers();
	/**
	 * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
	 * The list contents are of type {@link Core.IField}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Fields</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fields</em>' containment reference list.
	 * @see Core.CorePackage#getIType_Fields()
	 * @model containment="true"
	 * @generated
	 */
	EList<IField> getFields();
	/**
	 * Returns the value of the '<em><b>Methods</b></em>' containment reference list.
	 * The list contents are of type {@link Core.IMethod}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Methods</em>' containment reference list.
	 * @see Core.CorePackage#getIType_Methods()
	 * @model containment="true"
	 * @generated
	 */
	EList<IMethod> getMethods();
	/**
	 * Returns the value of the '<em><b>Types</b></em>' containment reference list.
	 * The list contents are of type {@link Core.IType}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Types</em>' containment reference list.
	 * @see Core.CorePackage#getIType_Types()
	 * @model containment="true"
	 * @generated
	 */
	EList<IType> getTypes();
	/**
	 * Returns the value of the '<em><b>Type Parameters</b></em>' reference list.
	 * The list contents are of type {@link Core.ITypeParameter}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Type Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Parameters</em>' reference list.
	 * @see Core.CorePackage#getIType_TypeParameters()
	 * @model ordered="false"
	 * @generated
	 */
	EList<ITypeParameter> getTypeParameters();



// data Class generation 
} // IType
