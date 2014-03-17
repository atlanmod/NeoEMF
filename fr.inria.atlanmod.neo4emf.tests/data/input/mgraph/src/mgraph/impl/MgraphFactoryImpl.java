/**
 *
 * $Id$
 */
package mgraph.impl;



import mgraph.MEdge;
import mgraph.MGraph;
import mgraph.MNode;
import mgraph.MgraphFactory;
import mgraph.MgraphPackage;
import mgraph.util.MgraphAdapterFactory;

import org.eclipse.emf.common.notify.Adapter;
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
	 * AdapterFactory instance
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	protected MgraphAdapterFactory adapterFactory = new MgraphAdapterFactory();
	
	
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MgraphFactory init() {
		try {
			MgraphFactory theMgraphFactory = (MgraphFactory)EPackage.Registry.INSTANCE.getEFactory("http://mgraph/1.1"); 
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
		Adapter adapter = adapterFactory.createMGraphAdapter();
		if (adapter != null) {
			mGraph.eAdapters().add(adapter);
		}
		return mGraph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MEdge createMEdge() {
		MEdgeImpl mEdge = new MEdgeImpl();
		Adapter adapter = adapterFactory.createMEdgeAdapter();
		if (adapter != null) {
			mEdge.eAdapters().add(adapter);
		}
		return mEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MNode createMNode() {
		MNodeImpl mNode = new MNodeImpl();
		Adapter adapter = adapterFactory.createMNodeAdapter();
		if (adapter != null) {
			mNode.eAdapters().add(adapter);
		}
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
