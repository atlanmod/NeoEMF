/**
 */
package mgraph.impl;

import mgraph.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MgraphFactoryImpl extends EFactoryImpl implements MgraphFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MgraphFactory init() {
		try {
			MgraphFactory theMgraphFactory = (MgraphFactory)EPackage.Registry.INSTANCE.getEFactory(MgraphPackage.eNS_URI);
			if (theMgraphFactory != null) {
				return theMgraphFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MgraphFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MgraphFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MgraphPackage.MGRAPH: return (EObject)createMGraph();
			case MgraphPackage.MEDGE: return (EObject)createMEdge();
			case MgraphPackage.MNODE: return (EObject)createMNode();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MGraph createMGraph() {
		MGraphImpl mGraph = new MGraphImpl();
		return mGraph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MEdge createMEdge() {
		MEdgeImpl mEdge = new MEdgeImpl();
		return mEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MNode createMNode() {
		MNodeImpl mNode = new MNodeImpl();
		return mNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MgraphPackage getMgraphPackage() {
		return (MgraphPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MgraphPackage getPackage() {
		return MgraphPackage.eINSTANCE;
	}

} //MgraphFactoryImpl
