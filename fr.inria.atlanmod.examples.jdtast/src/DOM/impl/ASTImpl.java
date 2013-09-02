/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.AST;
import DOM.ASTNode;
import DOM.DOMPackage;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AST</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.ASTImpl#getCompilationUnits <em>Compilation Units</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ASTImpl extends Neo4emfObject implements AST {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//Neo4emfObject
	
	/**
	 * The cached value of the data structure {@link DataAST <em>data</em> } 
	 * @generated
	 */
	 	protected DataAST data;
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ASTImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.AST;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASTNode getCompilationUnits() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataAST))
			data = new DataAST();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.AST__COMPILATION_UNITS, null, null));
		return ((DataAST)data).compilationUnits;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompilationUnits(ASTNode newCompilationUnits, NotificationChain msgs) {
	
		if (data==null) data =  new DataAST();
	
		ASTNode oldCompilationUnits = ((DataAST)data).compilationUnits;
		((DataAST)data).compilationUnits = newCompilationUnits;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.AST__COMPILATION_UNITS, oldCompilationUnits, newCompilationUnits);
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
	public void setCompilationUnits(ASTNode newCompilationUnits) {
	
		if (data==null) data =  new DataAST();
		
		if (newCompilationUnits != ((DataAST)data).compilationUnits) {
			NotificationChain msgs = null;
			if (((DataAST)data).compilationUnits != null)
				msgs = ((InternalEObject) ((DataAST)data).compilationUnits).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.AST__COMPILATION_UNITS, null, msgs);
			if (newCompilationUnits != null)
				msgs = ((InternalEObject)newCompilationUnits).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.AST__COMPILATION_UNITS, null, msgs);
			msgs = basicSetCompilationUnits(newCompilationUnits, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.AST__COMPILATION_UNITS, newCompilationUnits, newCompilationUnits));
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
			case DOMPackage.AST__COMPILATION_UNITS:
				return basicSetCompilationUnits(null, msgs);
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
			case DOMPackage.AST__COMPILATION_UNITS:
				return getCompilationUnits();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY16
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DOMPackage.AST__COMPILATION_UNITS:
				setCompilationUnits((ASTNode)newValue);
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
			case DOMPackage.AST__COMPILATION_UNITS:
				setCompilationUnits((ASTNode)null);
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
			case DOMPackage.AST__COMPILATION_UNITS:
				return getCompilationUnits() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataAST {


	/**
	 * The cached value of the '{@link #getCompilationUnits() <em>Compilation Units</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompilationUnits()
	 * @generated
	 * @ordered
	 */
	protected ASTNode compilationUnits;

	/**
	 *Constructor of DataAST
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataAST() {
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
} //ASTImpl
