/**
 *
 * $Id$
 */
package DOM;

import Core.IType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.Expression#getResolveBoxing <em>Resolve Boxing</em>}</li>
 *   <li>{@link DOM.Expression#getResolveUnboxing <em>Resolve Unboxing</em>}</li>
 *   <li>{@link DOM.Expression#getTypeBinding <em>Type Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getExpression()
 * @model abstract="true"
 * @generated
 */
public interface Expression extends ASTNode {

	/**
	 * Returns the value of the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Resolve Boxing</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolve Boxing</em>' attribute.
	 * @see #setResolveBoxing(Boolean)
	 * @see DOM.DOMPackage#getExpression_ResolveBoxing()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getResolveBoxing();
	/**
	 * Sets the value of the '{@link DOM.Expression#getResolveBoxing <em>Resolve Boxing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolve Boxing</em>' attribute.
	 * @see #getResolveBoxing()
	 * @generated
	 */
	void setResolveBoxing(Boolean value);

	/**
	 * Returns the value of the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Resolve Unboxing</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolve Unboxing</em>' attribute.
	 * @see #setResolveUnboxing(Boolean)
	 * @see DOM.DOMPackage#getExpression_ResolveUnboxing()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getResolveUnboxing();
	/**
	 * Sets the value of the '{@link DOM.Expression#getResolveUnboxing <em>Resolve Unboxing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolve Unboxing</em>' attribute.
	 * @see #getResolveUnboxing()
	 * @generated
	 */
	void setResolveUnboxing(Boolean value);

	/**
	 * Returns the value of the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Type Binding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Binding</em>' reference.
	 * @see #setTypeBinding(IType)
	 * @see DOM.DOMPackage#getExpression_TypeBinding()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	IType getTypeBinding();
	/**
	 * Sets the value of the '{@link DOM.Expression#getTypeBinding <em>Type Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Binding</em>' reference.
	 * @see #getTypeBinding()
	 * @generated
	 */
	void setTypeBinding(IType value);




// data Class generation 
} // Expression
