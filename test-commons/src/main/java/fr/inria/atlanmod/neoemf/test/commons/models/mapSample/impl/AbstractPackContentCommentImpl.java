/**
 */
package fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl;

import fr.inria.atlanmod.neoemf.core.impl.PersistentEObjectImpl;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.AbstractPackContentComment;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Pack Content Comment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.AbstractPackContentCommentImpl#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbstractPackContentCommentImpl extends PersistentEObjectImpl implements AbstractPackContentComment {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractPackContentCommentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MapSamplePackage.Literals.ABSTRACT_PACK_CONTENT_COMMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected int eStaticFeatureCount() {
        return 0;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getContent() {
        return (String)eGet(MapSamplePackage.Literals.ABSTRACT_PACK_CONTENT_COMMENT__CONTENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContent(String newContent) {
        eSet(MapSamplePackage.Literals.ABSTRACT_PACK_CONTENT_COMMENT__CONTENT, newContent);
    }

} //AbstractPackContentCommentImpl
