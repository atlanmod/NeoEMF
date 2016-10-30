/**
 */
package org.eclipse.gmt.modisco.java.neoemf.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.Comment;

import org.eclipse.gmt.modisco.java.neoemf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neoemf.impl.CommentImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neoemf.impl.CommentImpl#isEnclosedByParent <em>Enclosed By Parent</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neoemf.impl.CommentImpl#isPrefixOfParent <em>Prefix Of Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CommentImpl extends ASTNodeImpl implements Comment {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CommentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return JavaPackage.eINSTANCE.getComment();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getContent() {
        return (String)eGet(JavaPackage.eINSTANCE.getComment_Content(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContent(String newContent) {
        eSet(JavaPackage.eINSTANCE.getComment_Content(), newContent);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isEnclosedByParent() {
        return (Boolean)eGet(JavaPackage.eINSTANCE.getComment_EnclosedByParent(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEnclosedByParent(boolean newEnclosedByParent) {
        eSet(JavaPackage.eINSTANCE.getComment_EnclosedByParent(), newEnclosedByParent);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isPrefixOfParent() {
        return (Boolean)eGet(JavaPackage.eINSTANCE.getComment_PrefixOfParent(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPrefixOfParent(boolean newPrefixOfParent) {
        eSet(JavaPackage.eINSTANCE.getComment_PrefixOfParent(), newPrefixOfParent);
    }

} //CommentImpl
