/**
 */
package graph.impl;

import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;

import graph.GraphPackage;
import graph.Vertice;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vertice</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link graph.impl.VerticeImpl#getLabel <em>Label</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VerticeImpl extends DefaultPersistentEObject implements Vertice {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VerticeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphPackage.eINSTANCE.getVertice();
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
	public String getLabel() {
		return (String)eGet(GraphPackage.eINSTANCE.getVertice_Label(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(String newLabel) {
		eSet(GraphPackage.eINSTANCE.getVertice_Label(), newLabel);
	}

} //VerticeImpl
