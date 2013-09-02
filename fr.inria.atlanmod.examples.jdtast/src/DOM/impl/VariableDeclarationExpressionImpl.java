/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.ExtendedModifier;
import DOM.Type;
import DOM.VariableDeclarationExpression;
import DOM.VariableDeclarationFragment;

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
 * An implementation of the model object '<em><b>Variable Declaration Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.VariableDeclarationExpressionImpl#getFragments <em>Fragments</em>}</li>
 *   <li>{@link DOM.impl.VariableDeclarationExpressionImpl#getModifiers <em>Modifiers</em>}</li>
 *   <li>{@link DOM.impl.VariableDeclarationExpressionImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariableDeclarationExpressionImpl extends ExpressionImpl implements VariableDeclarationExpression {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableDeclarationExpressionImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.VARIABLE_DECLARATION_EXPRESSION;
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
		if ( data == null || !(data instanceof DataVariableDeclarationExpression))
			data = new DataVariableDeclarationExpression();
				
	   
		if (((DataVariableDeclarationExpression)data).fragments == null) {
			((DataVariableDeclarationExpression)data).fragments = new EObjectContainmentEList<VariableDeclarationFragment>(VariableDeclarationFragment.class, this, DOMPackage.VARIABLE_DECLARATION_EXPRESSION__FRAGMENTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.VARIABLE_DECLARATION_EXPRESSION__FRAGMENTS);			
		}
		return ((DataVariableDeclarationExpression)data).fragments;	
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
		if ( data == null || !(data instanceof DataVariableDeclarationExpression))
			data = new DataVariableDeclarationExpression();
				
	   
		if (((DataVariableDeclarationExpression)data).modifiers == null) {
			((DataVariableDeclarationExpression)data).modifiers = new EObjectContainmentEList<ExtendedModifier>(ExtendedModifier.class, this, DOMPackage.VARIABLE_DECLARATION_EXPRESSION__MODIFIERS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.VARIABLE_DECLARATION_EXPRESSION__MODIFIERS);			
		}
		return ((DataVariableDeclarationExpression)data).modifiers;	
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
		if ( data == null || !(data instanceof DataVariableDeclarationExpression))
			data = new DataVariableDeclarationExpression();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.VARIABLE_DECLARATION_EXPRESSION__TYPE, null, null));
		return ((DataVariableDeclarationExpression)data).type;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(Type newType, NotificationChain msgs) {
	
		if (data==null) data =  new DataVariableDeclarationExpression();
	
		Type oldType = ((DataVariableDeclarationExpression)data).type;
		((DataVariableDeclarationExpression)data).type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION_EXPRESSION__TYPE, oldType, newType);
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
	
		if (data==null) data =  new DataVariableDeclarationExpression();
		
		else if (!(data instanceof DataVariableDeclarationExpression)) data = new DataVariableDeclarationExpression((DataExpression)data);
	
		if (newType != ((DataVariableDeclarationExpression)data).type) {
			NotificationChain msgs = null;
			if (((DataVariableDeclarationExpression)data).type != null)
				msgs = ((InternalEObject) ((DataVariableDeclarationExpression)data).type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION_EXPRESSION__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION_EXPRESSION__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION_EXPRESSION__TYPE, newType, newType));
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
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__FRAGMENTS:
				return ((InternalEList<?>)getFragments()).basicRemove(otherEnd, msgs);
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__MODIFIERS:
				return ((InternalEList<?>)getModifiers()).basicRemove(otherEnd, msgs);
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__TYPE:
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
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__FRAGMENTS:
				return getFragments();
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__MODIFIERS:
				return getModifiers();
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__TYPE:
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
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__FRAGMENTS:
				getFragments().clear();
				getFragments().addAll((Collection<? extends VariableDeclarationFragment>)newValue);
				return;
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__MODIFIERS:
				getModifiers().clear();
				getModifiers().addAll((Collection<? extends ExtendedModifier>)newValue);
				return;
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__TYPE:
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
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__FRAGMENTS:
				getFragments().clear();
				return;
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__MODIFIERS:
				getModifiers().clear();
				return;
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__TYPE:
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
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__FRAGMENTS:
				return getFragments() != null && !getFragments().isEmpty();
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__MODIFIERS:
				return getModifiers() != null && !getModifiers().isEmpty();
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION__TYPE:
				return getType() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataVariableDeclarationExpression extends DataExpression {


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
	 *Constructor of DataVariableDeclarationExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariableDeclarationExpression() {
		super();
	}
	
		
	/**
	 *Constructor of DataVariableDeclarationExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataVariableDeclarationExpression(DataExpression data) {
		super();		
		
		resolveBoxing = data.resolveBoxing;
				
		resolveUnboxing = data.resolveUnboxing;
				
		typeBinding = data.typeBinding;
				
				
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
} //VariableDeclarationExpressionImpl
