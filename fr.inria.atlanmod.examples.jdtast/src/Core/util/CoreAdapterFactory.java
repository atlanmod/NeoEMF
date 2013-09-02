/**
 *
 * $Id$
 */
package Core.util;


import Core.*;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import fr.inria.atlanmod.neo4emf.change.impl.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see Core.CorePackage
 * @generated
 */
public class CoreAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CorePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = CorePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CoreSwitch<Adapter> modelSwitch =
		new CoreSwitch<Adapter>() {
			@Override
			public Adapter caseIJavaElement(IJavaElement object) {
				return createIJavaElementAdapter();
			}
			@Override
			public Adapter casePhysicalElement(PhysicalElement object) {
				return createPhysicalElementAdapter();
			}
			@Override
			public Adapter caseIJavaModel(IJavaModel object) {
				return createIJavaModelAdapter();
			}
			@Override
			public Adapter caseIJavaProject(IJavaProject object) {
				return createIJavaProjectAdapter();
			}
			@Override
			public Adapter caseIPackageFragmentRoot(IPackageFragmentRoot object) {
				return createIPackageFragmentRootAdapter();
			}
			@Override
			public Adapter caseBinaryPackageFragmentRoot(BinaryPackageFragmentRoot object) {
				return createBinaryPackageFragmentRootAdapter();
			}
			@Override
			public Adapter caseSourcePackageFragmentRoot(SourcePackageFragmentRoot object) {
				return createSourcePackageFragmentRootAdapter();
			}
			@Override
			public Adapter caseIPackageFragment(IPackageFragment object) {
				return createIPackageFragmentAdapter();
			}
			@Override
			public Adapter caseITypeRoot(ITypeRoot object) {
				return createITypeRootAdapter();
			}
			@Override
			public Adapter caseICompilationUnit(ICompilationUnit object) {
				return createICompilationUnitAdapter();
			}
			@Override
			public Adapter caseIClassFile(IClassFile object) {
				return createIClassFileAdapter();
			}
			@Override
			public Adapter caseISourceReference(ISourceReference object) {
				return createISourceReferenceAdapter();
			}
			@Override
			public Adapter caseIImportDeclaration(IImportDeclaration object) {
				return createIImportDeclarationAdapter();
			}
			@Override
			public Adapter caseISourceRange(ISourceRange object) {
				return createISourceRangeAdapter();
			}
			@Override
			public Adapter caseIMember(IMember object) {
				return createIMemberAdapter();
			}
			@Override
			public Adapter caseIType(IType object) {
				return createITypeAdapter();
			}
			@Override
			public Adapter caseITypeParameter(ITypeParameter object) {
				return createITypeParameterAdapter();
			}
			@Override
			public Adapter caseIInitializer(IInitializer object) {
				return createIInitializerAdapter();
			}
			@Override
			public Adapter caseIField(IField object) {
				return createIFieldAdapter();
			}
			@Override
			public Adapter caseIMethod(IMethod object) {
				return createIMethodAdapter();
			}
			@Override
			public Adapter caseParameter(Parameter object) {
				return createParameterAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link Core.IJavaElement <em>IJava Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.IJavaElement
	 * @generated
	 */
	public Adapter createIJavaElementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.PhysicalElement <em>Physical Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.PhysicalElement
	 * @generated
	 */
	public Adapter createPhysicalElementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.IJavaModel <em>IJava Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.IJavaModel
	 * @generated
	 */
	public Adapter createIJavaModelAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.IJavaProject <em>IJava Project</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.IJavaProject
	 * @generated
	 */
	public Adapter createIJavaProjectAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.IPackageFragmentRoot <em>IPackage Fragment Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.IPackageFragmentRoot
	 * @generated
	 */
	public Adapter createIPackageFragmentRootAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.BinaryPackageFragmentRoot <em>Binary Package Fragment Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.BinaryPackageFragmentRoot
	 * @generated
	 */
	public Adapter createBinaryPackageFragmentRootAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.SourcePackageFragmentRoot <em>Source Package Fragment Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.SourcePackageFragmentRoot
	 * @generated
	 */
	public Adapter createSourcePackageFragmentRootAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.IPackageFragment <em>IPackage Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.IPackageFragment
	 * @generated
	 */
	public Adapter createIPackageFragmentAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.ITypeRoot <em>IType Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.ITypeRoot
	 * @generated
	 */
	public Adapter createITypeRootAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.ICompilationUnit <em>ICompilation Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.ICompilationUnit
	 * @generated
	 */
	public Adapter createICompilationUnitAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.IClassFile <em>IClass File</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.IClassFile
	 * @generated
	 */
	public Adapter createIClassFileAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.ISourceReference <em>ISource Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.ISourceReference
	 * @generated
	 */
	public Adapter createISourceReferenceAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.IImportDeclaration <em>IImport Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.IImportDeclaration
	 * @generated
	 */
	public Adapter createIImportDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.ISourceRange <em>ISource Range</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.ISourceRange
	 * @generated
	 */
	public Adapter createISourceRangeAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.IMember <em>IMember</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.IMember
	 * @generated
	 */
	public Adapter createIMemberAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.IType <em>IType</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.IType
	 * @generated
	 */
	public Adapter createITypeAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.ITypeParameter <em>IType Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.ITypeParameter
	 * @generated
	 */
	public Adapter createITypeParameterAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.IInitializer <em>IInitializer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.IInitializer
	 * @generated
	 */
	public Adapter createIInitializerAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.IField <em>IField</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.IField
	 * @generated
	 */
	public Adapter createIFieldAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.IMethod <em>IMethod</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.IMethod
	 * @generated
	 */
	public Adapter createIMethodAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link Core.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Core.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}
 	
}	
//CoreAdapterFactory