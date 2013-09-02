/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.AbstractTypeDeclaration;
import DOM.Comment;
import DOM.CompilationUnit;
import DOM.DOMPackage;
import DOM.ImportDeclaration;
import DOM.PackageDeclaration;

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
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compilation Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.CompilationUnitImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link DOM.impl.CompilationUnitImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link DOM.impl.CompilationUnitImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link DOM.impl.CompilationUnitImpl#getTypes <em>Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompilationUnitImpl extends ASTNodeImpl implements CompilationUnit {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ASTNodeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompilationUnitImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.COMPILATION_UNIT;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Comment> getComments() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataCompilationUnit))
			data = new DataCompilationUnit();
				
	   
		if (((DataCompilationUnit)data).comments == null) {
			((DataCompilationUnit)data).comments = new EObjectContainmentEList<Comment>(Comment.class, this, DOMPackage.COMPILATION_UNIT__COMMENTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.COMPILATION_UNIT__COMMENTS);			
		}
		return ((DataCompilationUnit)data).comments;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageDeclaration getPackage() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataCompilationUnit))
			data = new DataCompilationUnit();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.COMPILATION_UNIT__PACKAGE, null, null));
		return ((DataCompilationUnit)data).package_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPackage(PackageDeclaration newPackage, NotificationChain msgs) {
	
		if (data==null) data =  new DataCompilationUnit();
	
		PackageDeclaration oldPackage = ((DataCompilationUnit)data).package_;
		((DataCompilationUnit)data).package_ = newPackage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.COMPILATION_UNIT__PACKAGE, oldPackage, newPackage);
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
	public void setPackage(PackageDeclaration newPackage) {
	
		if (data==null) data =  new DataCompilationUnit();
		
		else if (!(data instanceof DataCompilationUnit)) data = new DataCompilationUnit((DataASTNode)data);
	
		if (newPackage != ((DataCompilationUnit)data).package_) {
			NotificationChain msgs = null;
			if (((DataCompilationUnit)data).package_ != null)
				msgs = ((InternalEObject) ((DataCompilationUnit)data).package_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.COMPILATION_UNIT__PACKAGE, null, msgs);
			if (newPackage != null)
				msgs = ((InternalEObject)newPackage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.COMPILATION_UNIT__PACKAGE, null, msgs);
			msgs = basicSetPackage(newPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.COMPILATION_UNIT__PACKAGE, newPackage, newPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ImportDeclaration> getImports() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataCompilationUnit))
			data = new DataCompilationUnit();
				
	   
		if (((DataCompilationUnit)data).imports == null) {
			((DataCompilationUnit)data).imports = new EObjectContainmentEList<ImportDeclaration>(ImportDeclaration.class, this, DOMPackage.COMPILATION_UNIT__IMPORTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.COMPILATION_UNIT__IMPORTS);			
		}
		return ((DataCompilationUnit)data).imports;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractTypeDeclaration> getTypes() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataCompilationUnit))
			data = new DataCompilationUnit();
				
	   
		if (((DataCompilationUnit)data).types == null) {
			((DataCompilationUnit)data).types = new EObjectContainmentEList<AbstractTypeDeclaration>(AbstractTypeDeclaration.class, this, DOMPackage.COMPILATION_UNIT__TYPES);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.COMPILATION_UNIT__TYPES);			
		}
		return ((DataCompilationUnit)data).types;	
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
			case DOMPackage.COMPILATION_UNIT__COMMENTS:
				return ((InternalEList<?>)getComments()).basicRemove(otherEnd, msgs);
			case DOMPackage.COMPILATION_UNIT__PACKAGE:
				return basicSetPackage(null, msgs);
			case DOMPackage.COMPILATION_UNIT__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
			case DOMPackage.COMPILATION_UNIT__TYPES:
				return ((InternalEList<?>)getTypes()).basicRemove(otherEnd, msgs);
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
			case DOMPackage.COMPILATION_UNIT__COMMENTS:
				return getComments();
			case DOMPackage.COMPILATION_UNIT__PACKAGE:
				return getPackage();
			case DOMPackage.COMPILATION_UNIT__IMPORTS:
				return getImports();
			case DOMPackage.COMPILATION_UNIT__TYPES:
				return getTypes();
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
			case DOMPackage.COMPILATION_UNIT__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case DOMPackage.COMPILATION_UNIT__PACKAGE:
				setPackage((PackageDeclaration)newValue);
				return;
			case DOMPackage.COMPILATION_UNIT__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends ImportDeclaration>)newValue);
				return;
			case DOMPackage.COMPILATION_UNIT__TYPES:
				getTypes().clear();
				getTypes().addAll((Collection<? extends AbstractTypeDeclaration>)newValue);
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
			case DOMPackage.COMPILATION_UNIT__COMMENTS:
				getComments().clear();
				return;
			case DOMPackage.COMPILATION_UNIT__PACKAGE:
				setPackage((PackageDeclaration)null);
				return;
			case DOMPackage.COMPILATION_UNIT__IMPORTS:
				getImports().clear();
				return;
			case DOMPackage.COMPILATION_UNIT__TYPES:
				getTypes().clear();
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
			case DOMPackage.COMPILATION_UNIT__COMMENTS:
				return getComments() != null && !getComments().isEmpty();
			case DOMPackage.COMPILATION_UNIT__PACKAGE:
				return getPackage() != null;
			case DOMPackage.COMPILATION_UNIT__IMPORTS:
				return getImports() != null && !getImports().isEmpty();
			case DOMPackage.COMPILATION_UNIT__TYPES:
				return getTypes() != null && !getTypes().isEmpty();
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataCompilationUnit extends DataASTNode {


	/**
	 * The cached value of the '{@link #getComments() <em>Comments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComments()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> comments;

	/**
	 * The cached value of the '{@link #getPackage() <em>Package</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackage()
	 * @generated
	 * @ordered
	 */
	protected PackageDeclaration package_;

	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<ImportDeclaration> imports;

	/**
	 * The cached value of the '{@link #getTypes() <em>Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractTypeDeclaration> types;

	/**
	 *Constructor of DataCompilationUnit
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataCompilationUnit() {
		super();
	}
	
		
	/**
	 *Constructor of DataCompilationUnit
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	public DataCompilationUnit(DataASTNode data) {
		super();		
		
				
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
} //CompilationUnitImpl
