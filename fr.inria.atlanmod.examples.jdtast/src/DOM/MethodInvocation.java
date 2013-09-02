/**
 *
 * $Id$
 */
package DOM;

import Core.IMethod;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Invocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.MethodInvocation#getArguments <em>Arguments</em>}</li>
 *   <li>{@link DOM.MethodInvocation#getExpression <em>Expression</em>}</li>
 *   <li>{@link DOM.MethodInvocation#getName <em>Name</em>}</li>
 *   <li>{@link DOM.MethodInvocation#getTypeArguments <em>Type Arguments</em>}</li>
 *   <li>{@link DOM.MethodInvocation#getMethodBinding <em>Method Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getMethodInvocation()
 * @model
 * @generated
 */
public interface MethodInvocation extends Expression {

	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.Expression}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Arguments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arguments</em>' containment reference list.
	 * @see DOM.DOMPackage#getMethodInvocation_Arguments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getArguments();
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(Expression)
	 * @see DOM.DOMPackage#getMethodInvocation_Expression()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getExpression();
	/**
	 * Sets the value of the '{@link DOM.MethodInvocation#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

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
	 * @see DOM.DOMPackage#getMethodInvocation_Name()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	SimpleName getName();
	/**
	 * Sets the value of the '{@link DOM.MethodInvocation#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(SimpleName value);

	/**
	 * Returns the value of the '<em><b>Type Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.Type}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Type Arguments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Arguments</em>' containment reference list.
	 * @see DOM.DOMPackage#getMethodInvocation_TypeArguments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Type> getTypeArguments();
	/**
	 * Returns the value of the '<em><b>Method Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Method Binding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Binding</em>' reference.
	 * @see #setMethodBinding(IMethod)
	 * @see DOM.DOMPackage#getMethodInvocation_MethodBinding()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	IMethod getMethodBinding();
	/**
	 * Sets the value of the '{@link DOM.MethodInvocation#getMethodBinding <em>Method Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Binding</em>' reference.
	 * @see #getMethodBinding()
	 * @generated
	 */
	void setMethodBinding(IMethod value);




// data Class generation 
} // MethodInvocation
