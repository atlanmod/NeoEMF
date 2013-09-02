/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.ICompilationUnit;
import Core.IImportDeclaration;
import Core.IType;

import DOM.CompilationUnit;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ICompilation Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.ICompilationUnitImpl#getAllType <em>All Type</em>}</li>
 *   <li>{@link Core.impl.ICompilationUnitImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link Core.impl.ICompilationUnitImpl#getTypes <em>Types</em>}</li>
 *   <li>{@link Core.impl.ICompilationUnitImpl#getPrimary <em>Primary</em>}</li>
 *   <li>{@link Core.impl.ICompilationUnitImpl#getAst <em>Ast</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ICompilationUnitImpl extends ITypeRootImpl implements ICompilationUnit {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ITypeRootImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ICompilationUnitImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ICOMPILATION_UNIT;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IType> getAllType() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataICompilationUnit))
			data = new DataICompilationUnit();
				
	   
		if (((DataICompilationUnit)data).allType == null) {
			((DataICompilationUnit)data).allType = new EObjectResolvingEList<IType>(IType.class, this, CorePackage.ICOMPILATION_UNIT__ALL_TYPE);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.ICOMPILATION_UNIT__ALL_TYPE);			
		}
		return ((DataICompilationUnit)data).allType;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IImportDeclaration> getImports() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataICompilationUnit))
			data = new DataICompilationUnit();
				
	   
		if (((DataICompilationUnit)data).imports == null) {
			((DataICompilationUnit)data).imports = new EObjectContainmentEList<IImportDeclaration>(IImportDeclaration.class, this, CorePackage.ICOMPILATION_UNIT__IMPORTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.ICOMPILATION_UNIT__IMPORTS);			
		}
		return ((DataICompilationUnit)data).imports;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IType> getTypes() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataICompilationUnit))
			data = new DataICompilationUnit();
				
	   
		if (((DataICompilationUnit)data).types == null) {
			((DataICompilationUnit)data).types = new EObjectContainmentEList<IType>(IType.class, this, CorePackage.ICOMPILATION_UNIT__TYPES);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.ICOMPILATION_UNIT__TYPES);			
		}
		return ((DataICompilationUnit)data).types;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ICompilationUnit getPrimary() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataICompilationUnit))
			data = new DataICompilationUnit();
				
	  
		if (((DataICompilationUnit)data).primary == null && getNodeId()>-1) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.ICOMPILATION_UNIT__PRIMARY);
		}		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ICOMPILATION_UNIT__PRIMARY, null, null));
		return ((DataICompilationUnit)data).primary;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX8
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ICompilationUnit basicGetPrimary() {
		return data != null ? ((DataICompilationUnit)data).primary : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimary(ICompilationUnit newPrimary) {
	
		if (data==null) data =  new DataICompilationUnit();
		
		else if (!(data instanceof DataICompilationUnit)) data = new DataICompilationUnit((DataITypeRoot)data);
	
		ICompilationUnit oldPrimary = ((DataICompilationUnit)data).primary;
		((DataICompilationUnit)data).primary = newPrimary;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ICOMPILATION_UNIT__PRIMARY, oldPrimary, ((DataICompilationUnit)data).primary));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnit getAst() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataICompilationUnit))
			data = new DataICompilationUnit();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ICOMPILATION_UNIT__AST, null, null));
		return ((DataICompilationUnit)data).ast;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAst(CompilationUnit newAst, NotificationChain msgs) {
	
		if (data==null) data =  new DataICompilationUnit();
	
		CompilationUnit oldAst = ((DataICompilationUnit)data).ast;
		((DataICompilationUnit)data).ast = newAst;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.ICOMPILATION_UNIT__AST, oldAst, newAst);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAst(CompilationUnit newAst) {
	
		if (data==null) data =  new DataICompilationUnit();
		
		else if (!(data instanceof DataICompilationUnit)) data = new DataICompilationUnit((DataITypeRoot)data);
	
		if (newAst != ((DataICompilationUnit)data).ast) {
			NotificationChain msgs = null;
			if (((DataICompilationUnit)data).ast != null)
				msgs = ((InternalEObject) ((DataICompilationUnit)data).ast).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.ICOMPILATION_UNIT__AST, null, msgs);
			if (newAst != null)
				msgs = ((InternalEObject)newAst).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.ICOMPILATION_UNIT__AST, null, msgs);
			msgs = basicSetAst(newAst, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ICOMPILATION_UNIT__AST, newAst, newAst));
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY13
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.ICOMPILATION_UNIT__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
			case CorePackage.ICOMPILATION_UNIT__TYPES:
				return ((InternalEList<?>)getTypes()).basicRemove(otherEnd, msgs);
			case CorePackage.ICOMPILATION_UNIT__AST:
				return basicSetAst(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY15
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.ICOMPILATION_UNIT__ALL_TYPE:
				return getAllType();
			case CorePackage.ICOMPILATION_UNIT__IMPORTS:
				return getImports();
			case CorePackage.ICOMPILATION_UNIT__TYPES:
				return getTypes();
			case CorePackage.ICOMPILATION_UNIT__PRIMARY:
				if (resolve) return getPrimary();
				return basicGetPrimary();
			case CorePackage.ICOMPILATION_UNIT__AST:
				return getAst();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY16
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CorePackage.ICOMPILATION_UNIT__ALL_TYPE:
				getAllType().clear();
				getAllType().addAll((Collection<? extends IType>)newValue);
				return;
			case CorePackage.ICOMPILATION_UNIT__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends IImportDeclaration>)newValue);
				return;
			case CorePackage.ICOMPILATION_UNIT__TYPES:
				getTypes().clear();
				getTypes().addAll((Collection<? extends IType>)newValue);
				return;
			case CorePackage.ICOMPILATION_UNIT__PRIMARY:
				setPrimary((ICompilationUnit)newValue);
				return;
			case CorePackage.ICOMPILATION_UNIT__AST:
				setAst((CompilationUnit)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY17
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CorePackage.ICOMPILATION_UNIT__ALL_TYPE:
				getAllType().clear();
				return;
			case CorePackage.ICOMPILATION_UNIT__IMPORTS:
				getImports().clear();
				return;
			case CorePackage.ICOMPILATION_UNIT__TYPES:
				getTypes().clear();
				return;
			case CorePackage.ICOMPILATION_UNIT__PRIMARY:
				setPrimary((ICompilationUnit)null);
				return;
			case CorePackage.ICOMPILATION_UNIT__AST:
				setAst((CompilationUnit)null);
				return;
		}
		super.eUnset(featureID);
	}

/**
	 * <!-- begin-user-doc -->
	 *YY18
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CorePackage.ICOMPILATION_UNIT__ALL_TYPE:
				return getAllType() != null && !getAllType().isEmpty();
			case CorePackage.ICOMPILATION_UNIT__IMPORTS:
				return getImports() != null && !getImports().isEmpty();
			case CorePackage.ICOMPILATION_UNIT__TYPES:
				return getTypes() != null && !getTypes().isEmpty();
			case CorePackage.ICOMPILATION_UNIT__PRIMARY:
				return getPrimary() != null;
			case CorePackage.ICOMPILATION_UNIT__AST:
				return getAst() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataICompilationUnit extends DataITypeRoot {


	/**
	 * The cached value of the '{@link #getAllType() <em>All Type</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllType()
	 * @generated
	 * @ordered
	 */
	protected EList<IType> allType;

	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<IImportDeclaration> imports;

	/**
	 * The cached value of the '{@link #getTypes() <em>Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<IType> types;

	/**
	 * The cached value of the '{@link #getPrimary() <em>Primary</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimary()
	 * @generated
	 * @ordered
	 */
	protected ICompilationUnit primary;

	/**
	 * The cached value of the '{@link #getAst() <em>Ast</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAst()
	 * @generated
	 * @ordered
	 */
	protected CompilationUnit ast;

	/**
	 *Constructor of DataICompilationUnit
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataICompilationUnit() {
		super();
	}
	
		
	/**
	 *Constructor of DataICompilationUnit
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ITypeRoot }
	 * @generated
	 */
	public DataICompilationUnit(DataITypeRoot data) {
		super();		
		
		source = data.source;
				
		sourceRange = data.sourceRange;
				
		path = data.path;
				
		isReadOnly = data.isReadOnly;
				
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(')');
		return result.toString();
	}
		
}
} //ICompilationUnitImpl
