/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Expression;
import DOM.SimpleName;
import DOM.VariableDeclaration;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.VariableDeclarationImpl#getExtraDimensions <em>Extra Dimensions</em>}</li>
 *   <li>{@link DOM.impl.VariableDeclarationImpl#getInitializer <em>Initializer</em>}</li>
 *   <li>{@link DOM.impl.VariableDeclarationImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class VariableDeclarationImpl extends ASTNodeImpl implements VariableDeclaration {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ASTNodeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableDeclarationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.VARIABLE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getExtraDimensions() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataVariableDeclaration))
			data = new DataVariableDeclaration();
			
		if (((DataVariableDeclaration)data).extraDimensions == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.VARIABLE_DECLARATION__EXTRA_DIMENSIONS, null, null));
		return ((DataVariableDeclaration)data).extraDimensions;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtraDimensions(Integer newExtraDimensions) {
	
		if (data==null) data =  new DataVariableDeclaration();
		
		else if (!(data instanceof DataVariableDeclaration)) data = new DataVariableDeclaration((DataASTNode)data);
	
		Integer oldExtraDimensions = ((DataVariableDeclaration)data).extraDimensions;
		((DataVariableDeclaration)data).extraDimensions = newExtraDimensions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION__EXTRA_DIMENSIONS, oldExtraDimensions, ((DataVariableDeclaration)data).extraDimensions));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getInitializer() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataVariableDeclaration))
			data = new DataVariableDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.VARIABLE_DECLARATION__INITIALIZER, null, null));
		return ((DataVariableDeclaration)data).initializer;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInitializer(Expression newInitializer, NotificationChain msgs) {
	
		if (data==null) data =  new DataVariableDeclaration();
	
		Expression oldInitializer = ((DataVariableDeclaration)data).initializer;
		((DataVariableDeclaration)data).initializer = newInitializer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION__INITIALIZER, oldInitializer, newInitializer);
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
	public void setInitializer(Expression newInitializer) {
	
		if (data==null) data =  new DataVariableDeclaration();
		
		else if (!(data instanceof DataVariableDeclaration)) data = new DataVariableDeclaration((DataASTNode)data);
	
		if (newInitializer != ((DataVariableDeclaration)data).initializer) {
			NotificationChain msgs = null;
			if (((DataVariableDeclaration)data).initializer != null)
				msgs = ((InternalEObject) ((DataVariableDeclaration)data).initializer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION__INITIALIZER, null, msgs);
			if (newInitializer != null)
				msgs = ((InternalEObject)newInitializer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION__INITIALIZER, null, msgs);
			msgs = basicSetInitializer(newInitializer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION__INITIALIZER, newInitializer, newInitializer));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleName getName() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataVariableDeclaration))
			data = new DataVariableDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.VARIABLE_DECLARATION__NAME, null, null));
		return ((DataVariableDeclaration)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(SimpleName newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataVariableDeclaration();
	
		SimpleName oldName = ((DataVariableDeclaration)data).name;
		((DataVariableDeclaration)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION__NAME, oldName, newName);
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
	public void setName(SimpleName newName) {
	
		if (data==null) data =  new DataVariableDeclaration();
		
		else if (!(data instanceof DataVariableDeclaration)) data = new DataVariableDeclaration((DataASTNode)data);
	
		if (newName != ((DataVariableDeclaration)data).name) {
			NotificationChain msgs = null;
			if (((DataVariableDeclaration)data).name != null)
				msgs = ((InternalEObject) ((DataVariableDeclaration)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION__NAME, newName, newName));
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
			case DOMPackage.VARIABLE_DECLARATION__INITIALIZER:
				return basicSetInitializer(null, msgs);
			case DOMPackage.VARIABLE_DECLARATION__NAME:
				return basicSetName(null, msgs);
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
			case DOMPackage.VARIABLE_DECLARATION__EXTRA_DIMENSIONS:
				return getExtraDimensions();
			case DOMPackage.VARIABLE_DECLARATION__INITIALIZER:
				return getInitializer();
			case DOMPackage.VARIABLE_DECLARATION__NAME:
				return getName();
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
			case DOMPackage.VARIABLE_DECLARATION__EXTRA_DIMENSIONS:
				setExtraDimensions((Integer)newValue);
				return;
			case DOMPackage.VARIABLE_DECLARATION__INITIALIZER:
				setInitializer((Expression)newValue);
				return;
			case DOMPackage.VARIABLE_DECLARATION__NAME:
				setName((SimpleName)newValue);
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
			case DOMPackage.VARIABLE_DECLARATION__EXTRA_DIMENSIONS:
				setExtraDimensions(DataVariableDeclaration.EXTRA_DIMENSIONS_EDEFAULT);
				return;
			case DOMPackage.VARIABLE_DECLARATION__INITIALIZER:
				setInitializer((Expression)null);
				return;
			case DOMPackage.VARIABLE_DECLARATION__NAME:
				setName((SimpleName)null);
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
			case DOMPackage.VARIABLE_DECLARATION__EXTRA_DIMENSIONS:
				return DataVariableDeclaration.EXTRA_DIMENSIONS_EDEFAULT == null ? getExtraDimensions() != null : !DataVariableDeclaration.EXTRA_DIMENSIONS_EDEFAULT.equals(getExtraDimensions());
			case DOMPackage.VARIABLE_DECLARATION__INITIALIZER:
				return getInitializer() != null;
			case DOMPackage.VARIABLE_DECLARATION__NAME:
				return getName() != null;
		}
		return super.eIsSet(featureID);
	}


	/**
	 * <!-- begin-user-doc -->
	 *YY27
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		if (data != null) result.append(((DataVariableDeclaration)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataVariableDeclaration extends DataASTNode {


	/**
	 * The default value of the '{@link #getExtraDimensions() <em>Extra Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtraDimensions()
	 * @generated
	 * @ordered
	 */
	protected static final Integer EXTRA_DIMENSIONS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExtraDimensions() <em>Extra Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtraDimensions()
	 * @generated
	 * @ordered
	 */
	protected Integer extraDimensions = EXTRA_DIMENSIONS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInitializer() <em>Initializer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializer()
	 * @generated
	 * @ordered
	 */
	protected Expression initializer;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected SimpleName name;

	/**
	 *Constructor of DataVariableDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariableDeclaration() {
		super();
	}
	
		
	/**
	 *Constructor of DataVariableDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	public DataVariableDeclaration(DataASTNode data) {
		super();		
		
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (extraDimensions: ");
		result.append(extraDimensions);
		result.append(')');
		return result.toString();
	}
		
}
} //VariableDeclarationImpl
