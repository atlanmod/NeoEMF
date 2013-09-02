/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.ExtendedModifier;
import DOM.Type;
import DOM.VariableDeclarationFragment;
import DOM.VariableDeclarationStatement;

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
 * An implementation of the model object '<em><b>Variable Declaration Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.VariableDeclarationStatementImpl#getFragments <em>Fragments</em>}</li>
 *   <li>{@link DOM.impl.VariableDeclarationStatementImpl#getModifiers <em>Modifiers</em>}</li>
 *   <li>{@link DOM.impl.VariableDeclarationStatementImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariableDeclarationStatementImpl extends StatementImpl implements VariableDeclarationStatement {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//StatementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableDeclarationStatementImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.VARIABLE_DECLARATION_STATEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariableDeclarationFragment> getFragments() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataVariableDeclarationStatement))
			data = new DataVariableDeclarationStatement();
				
	   
		if (((DataVariableDeclarationStatement)data).fragments == null) {
			((DataVariableDeclarationStatement)data).fragments = new EObjectContainmentEList<VariableDeclarationFragment>(VariableDeclarationFragment.class, this, DOMPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS);			
		}
		return ((DataVariableDeclarationStatement)data).fragments;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExtendedModifier> getModifiers() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataVariableDeclarationStatement))
			data = new DataVariableDeclarationStatement();
				
	   
		if (((DataVariableDeclarationStatement)data).modifiers == null) {
			((DataVariableDeclarationStatement)data).modifiers = new EObjectContainmentEList<ExtendedModifier>(ExtendedModifier.class, this, DOMPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIERS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIERS);			
		}
		return ((DataVariableDeclarationStatement)data).modifiers;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getType() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataVariableDeclarationStatement))
			data = new DataVariableDeclarationStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.VARIABLE_DECLARATION_STATEMENT__TYPE, null, null));
		return ((DataVariableDeclarationStatement)data).type;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(Type newType, NotificationChain msgs) {
	
		if (data==null) data =  new DataVariableDeclarationStatement();
	
		Type oldType = ((DataVariableDeclarationStatement)data).type;
		((DataVariableDeclarationStatement)data).type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION_STATEMENT__TYPE, oldType, newType);
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
	public void setType(Type newType) {
	
		if (data==null) data =  new DataVariableDeclarationStatement();
		
		else if (!(data instanceof DataVariableDeclarationStatement)) data = new DataVariableDeclarationStatement((DataStatement)data);
	
		if (newType != ((DataVariableDeclarationStatement)data).type) {
			NotificationChain msgs = null;
			if (((DataVariableDeclarationStatement)data).type != null)
				msgs = ((InternalEObject) ((DataVariableDeclarationStatement)data).type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION_STATEMENT__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION_STATEMENT__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION_STATEMENT__TYPE, newType, newType));
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
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS:
				return ((InternalEList<?>)getFragments()).basicRemove(otherEnd, msgs);
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIERS:
				return ((InternalEList<?>)getModifiers()).basicRemove(otherEnd, msgs);
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__TYPE:
				return basicSetType(null, msgs);
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
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS:
				return getFragments();
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIERS:
				return getModifiers();
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__TYPE:
				return getType();
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
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS:
				getFragments().clear();
				getFragments().addAll((Collection<? extends VariableDeclarationFragment>)newValue);
				return;
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIERS:
				getModifiers().clear();
				getModifiers().addAll((Collection<? extends ExtendedModifier>)newValue);
				return;
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__TYPE:
				setType((Type)newValue);
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
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS:
				getFragments().clear();
				return;
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIERS:
				getModifiers().clear();
				return;
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__TYPE:
				setType((Type)null);
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
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS:
				return getFragments() != null && !getFragments().isEmpty();
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__MODIFIERS:
				return getModifiers() != null && !getModifiers().isEmpty();
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT__TYPE:
				return getType() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataVariableDeclarationStatement extends DataStatement {


	/**
	 * The cached value of the '{@link #getFragments() <em>Fragments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFragments()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableDeclarationFragment> fragments;

	/**
	 * The cached value of the '{@link #getModifiers() <em>Modifiers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifiers()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtendedModifier> modifiers;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Type type;

	/**
	 *Constructor of DataVariableDeclarationStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariableDeclarationStatement() {
		super();
	}
	
		
	/**
	 *Constructor of DataVariableDeclarationStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	public DataVariableDeclarationStatement(DataStatement data) {
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
} //VariableDeclarationStatementImpl
