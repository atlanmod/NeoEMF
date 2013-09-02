/**
 *
 * $Id$
 */
package DOM.impl;

import Core.IMethod;

import DOM.Block;
import DOM.DOMPackage;
import DOM.MethodDeclaration;
import DOM.Name;
import DOM.SimpleName;
import DOM.SingleVariableDeclaration;
import DOM.Type;
import DOM.TypeParameter;

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
 * An implementation of the model object '<em><b>Method Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.MethodDeclarationImpl#getBody <em>Body</em>}</li>
 *   <li>{@link DOM.impl.MethodDeclarationImpl#getExtraDimensions <em>Extra Dimensions</em>}</li>
 *   <li>{@link DOM.impl.MethodDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link DOM.impl.MethodDeclarationImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link DOM.impl.MethodDeclarationImpl#getConstructor <em>Constructor</em>}</li>
 *   <li>{@link DOM.impl.MethodDeclarationImpl#getVarargs <em>Varargs</em>}</li>
 *   <li>{@link DOM.impl.MethodDeclarationImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link DOM.impl.MethodDeclarationImpl#getThrownExceptions <em>Thrown Exceptions</em>}</li>
 *   <li>{@link DOM.impl.MethodDeclarationImpl#getTypeParameters <em>Type Parameters</em>}</li>
 *   <li>{@link DOM.impl.MethodDeclarationImpl#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodDeclarationImpl extends BodyDeclarationImpl implements MethodDeclaration {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//BodyDeclarationImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodDeclarationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.METHOD_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getBody() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataMethodDeclaration))
			data = new DataMethodDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_DECLARATION__BODY, null, null));
		return ((DataMethodDeclaration)data).body;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(Block newBody, NotificationChain msgs) {
	
		if (data==null) data =  new DataMethodDeclaration();
	
		Block oldBody = ((DataMethodDeclaration)data).body;
		((DataMethodDeclaration)data).body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_DECLARATION__BODY, oldBody, newBody);
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
	public void setBody(Block newBody) {
	
		if (data==null) data =  new DataMethodDeclaration();
		
		else if (!(data instanceof DataMethodDeclaration)) data = new DataMethodDeclaration((DataBodyDeclaration)data);
	
		if (newBody != ((DataMethodDeclaration)data).body) {
			NotificationChain msgs = null;
			if (((DataMethodDeclaration)data).body != null)
				msgs = ((InternalEObject) ((DataMethodDeclaration)data).body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_DECLARATION__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_DECLARATION__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_DECLARATION__BODY, newBody, newBody));
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
		if ( data == null || !(data instanceof DataMethodDeclaration))
			data = new DataMethodDeclaration();
			
		if (((DataMethodDeclaration)data).extraDimensions == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_DECLARATION__EXTRA_DIMENSIONS, null, null));
		return ((DataMethodDeclaration)data).extraDimensions;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtraDimensions(Integer newExtraDimensions) {
	
		if (data==null) data =  new DataMethodDeclaration();
		
		else if (!(data instanceof DataMethodDeclaration)) data = new DataMethodDeclaration((DataBodyDeclaration)data);
	
		Integer oldExtraDimensions = ((DataMethodDeclaration)data).extraDimensions;
		((DataMethodDeclaration)data).extraDimensions = newExtraDimensions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_DECLARATION__EXTRA_DIMENSIONS, oldExtraDimensions, ((DataMethodDeclaration)data).extraDimensions));
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
		if ( data == null || !(data instanceof DataMethodDeclaration))
			data = new DataMethodDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_DECLARATION__NAME, null, null));
		return ((DataMethodDeclaration)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(SimpleName newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataMethodDeclaration();
	
		SimpleName oldName = ((DataMethodDeclaration)data).name;
		((DataMethodDeclaration)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_DECLARATION__NAME, oldName, newName);
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
	
		if (data==null) data =  new DataMethodDeclaration();
		
		else if (!(data instanceof DataMethodDeclaration)) data = new DataMethodDeclaration((DataBodyDeclaration)data);
	
		if (newName != ((DataMethodDeclaration)data).name) {
			NotificationChain msgs = null;
			if (((DataMethodDeclaration)data).name != null)
				msgs = ((InternalEObject) ((DataMethodDeclaration)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_DECLARATION__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_DECLARATION__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_DECLARATION__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getReturnType() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataMethodDeclaration))
			data = new DataMethodDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_DECLARATION__RETURN_TYPE, null, null));
		return ((DataMethodDeclaration)data).returnType;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReturnType(Type newReturnType, NotificationChain msgs) {
	
		if (data==null) data =  new DataMethodDeclaration();
	
		Type oldReturnType = ((DataMethodDeclaration)data).returnType;
		((DataMethodDeclaration)data).returnType = newReturnType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_DECLARATION__RETURN_TYPE, oldReturnType, newReturnType);
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
	public void setReturnType(Type newReturnType) {
	
		if (data==null) data =  new DataMethodDeclaration();
		
		else if (!(data instanceof DataMethodDeclaration)) data = new DataMethodDeclaration((DataBodyDeclaration)data);
	
		if (newReturnType != ((DataMethodDeclaration)data).returnType) {
			NotificationChain msgs = null;
			if (((DataMethodDeclaration)data).returnType != null)
				msgs = ((InternalEObject) ((DataMethodDeclaration)data).returnType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_DECLARATION__RETURN_TYPE, null, msgs);
			if (newReturnType != null)
				msgs = ((InternalEObject)newReturnType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.METHOD_DECLARATION__RETURN_TYPE, null, msgs);
			msgs = basicSetReturnType(newReturnType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_DECLARATION__RETURN_TYPE, newReturnType, newReturnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getConstructor() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataMethodDeclaration))
			data = new DataMethodDeclaration();
			
		if (((DataMethodDeclaration)data).constructor == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_DECLARATION__CONSTRUCTOR, null, null));
		return ((DataMethodDeclaration)data).constructor;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstructor(Boolean newConstructor) {
	
		if (data==null) data =  new DataMethodDeclaration();
		
		else if (!(data instanceof DataMethodDeclaration)) data = new DataMethodDeclaration((DataBodyDeclaration)data);
	
		Boolean oldConstructor = ((DataMethodDeclaration)data).constructor;
		((DataMethodDeclaration)data).constructor = newConstructor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_DECLARATION__CONSTRUCTOR, oldConstructor, ((DataMethodDeclaration)data).constructor));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getVarargs() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataMethodDeclaration))
			data = new DataMethodDeclaration();
			
		if (((DataMethodDeclaration)data).varargs == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_DECLARATION__VARARGS, null, null));
		return ((DataMethodDeclaration)data).varargs;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVarargs(Boolean newVarargs) {
	
		if (data==null) data =  new DataMethodDeclaration();
		
		else if (!(data instanceof DataMethodDeclaration)) data = new DataMethodDeclaration((DataBodyDeclaration)data);
	
		Boolean oldVarargs = ((DataMethodDeclaration)data).varargs;
		((DataMethodDeclaration)data).varargs = newVarargs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_DECLARATION__VARARGS, oldVarargs, ((DataMethodDeclaration)data).varargs));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SingleVariableDeclaration> getParameters() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataMethodDeclaration))
			data = new DataMethodDeclaration();
				
	   
		if (((DataMethodDeclaration)data).parameters == null) {
			((DataMethodDeclaration)data).parameters = new EObjectContainmentEList<SingleVariableDeclaration>(SingleVariableDeclaration.class, this, DOMPackage.METHOD_DECLARATION__PARAMETERS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.METHOD_DECLARATION__PARAMETERS);			
		}
		return ((DataMethodDeclaration)data).parameters;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Name> getThrownExceptions() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataMethodDeclaration))
			data = new DataMethodDeclaration();
				
	   
		if (((DataMethodDeclaration)data).thrownExceptions == null) {
			((DataMethodDeclaration)data).thrownExceptions = new EObjectContainmentEList<Name>(Name.class, this, DOMPackage.METHOD_DECLARATION__THROWN_EXCEPTIONS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.METHOD_DECLARATION__THROWN_EXCEPTIONS);			
		}
		return ((DataMethodDeclaration)data).thrownExceptions;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeParameter> getTypeParameters() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataMethodDeclaration))
			data = new DataMethodDeclaration();
				
	   
		if (((DataMethodDeclaration)data).typeParameters == null) {
			((DataMethodDeclaration)data).typeParameters = new EObjectContainmentEList<TypeParameter>(TypeParameter.class, this, DOMPackage.METHOD_DECLARATION__TYPE_PARAMETERS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.METHOD_DECLARATION__TYPE_PARAMETERS);			
		}
		return ((DataMethodDeclaration)data).typeParameters;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IMethod getBinding() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataMethodDeclaration))
			data = new DataMethodDeclaration();
				
	  
		if (((DataMethodDeclaration)data).binding == null && getNodeId()>-1) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.METHOD_DECLARATION__BINDING);
		}		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.METHOD_DECLARATION__BINDING, null, null));
		return ((DataMethodDeclaration)data).binding;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX8
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IMethod basicGetBinding() {
		return data != null ? ((DataMethodDeclaration)data).binding : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(IMethod newBinding) {
	
		if (data==null) data =  new DataMethodDeclaration();
		
		else if (!(data instanceof DataMethodDeclaration)) data = new DataMethodDeclaration((DataBodyDeclaration)data);
	
		IMethod oldBinding = ((DataMethodDeclaration)data).binding;
		((DataMethodDeclaration)data).binding = newBinding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.METHOD_DECLARATION__BINDING, oldBinding, ((DataMethodDeclaration)data).binding));
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
			case DOMPackage.METHOD_DECLARATION__BODY:
				return basicSetBody(null, msgs);
			case DOMPackage.METHOD_DECLARATION__NAME:
				return basicSetName(null, msgs);
			case DOMPackage.METHOD_DECLARATION__RETURN_TYPE:
				return basicSetReturnType(null, msgs);
			case DOMPackage.METHOD_DECLARATION__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
			case DOMPackage.METHOD_DECLARATION__THROWN_EXCEPTIONS:
				return ((InternalEList<?>)getThrownExceptions()).basicRemove(otherEnd, msgs);
			case DOMPackage.METHOD_DECLARATION__TYPE_PARAMETERS:
				return ((InternalEList<?>)getTypeParameters()).basicRemove(otherEnd, msgs);
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
			case DOMPackage.METHOD_DECLARATION__BODY:
				return getBody();
			case DOMPackage.METHOD_DECLARATION__EXTRA_DIMENSIONS:
				return getExtraDimensions();
			case DOMPackage.METHOD_DECLARATION__NAME:
				return getName();
			case DOMPackage.METHOD_DECLARATION__RETURN_TYPE:
				return getReturnType();
			case DOMPackage.METHOD_DECLARATION__CONSTRUCTOR:
				return getConstructor();
			case DOMPackage.METHOD_DECLARATION__VARARGS:
				return getVarargs();
			case DOMPackage.METHOD_DECLARATION__PARAMETERS:
				return getParameters();
			case DOMPackage.METHOD_DECLARATION__THROWN_EXCEPTIONS:
				return getThrownExceptions();
			case DOMPackage.METHOD_DECLARATION__TYPE_PARAMETERS:
				return getTypeParameters();
			case DOMPackage.METHOD_DECLARATION__BINDING:
				if (resolve) return getBinding();
				return basicGetBinding();
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
			case DOMPackage.METHOD_DECLARATION__BODY:
				setBody((Block)newValue);
				return;
			case DOMPackage.METHOD_DECLARATION__EXTRA_DIMENSIONS:
				setExtraDimensions((Integer)newValue);
				return;
			case DOMPackage.METHOD_DECLARATION__NAME:
				setName((SimpleName)newValue);
				return;
			case DOMPackage.METHOD_DECLARATION__RETURN_TYPE:
				setReturnType((Type)newValue);
				return;
			case DOMPackage.METHOD_DECLARATION__CONSTRUCTOR:
				setConstructor((Boolean)newValue);
				return;
			case DOMPackage.METHOD_DECLARATION__VARARGS:
				setVarargs((Boolean)newValue);
				return;
			case DOMPackage.METHOD_DECLARATION__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends SingleVariableDeclaration>)newValue);
				return;
			case DOMPackage.METHOD_DECLARATION__THROWN_EXCEPTIONS:
				getThrownExceptions().clear();
				getThrownExceptions().addAll((Collection<? extends Name>)newValue);
				return;
			case DOMPackage.METHOD_DECLARATION__TYPE_PARAMETERS:
				getTypeParameters().clear();
				getTypeParameters().addAll((Collection<? extends TypeParameter>)newValue);
				return;
			case DOMPackage.METHOD_DECLARATION__BINDING:
				setBinding((IMethod)newValue);
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
			case DOMPackage.METHOD_DECLARATION__BODY:
				setBody((Block)null);
				return;
			case DOMPackage.METHOD_DECLARATION__EXTRA_DIMENSIONS:
				setExtraDimensions(DataMethodDeclaration.EXTRA_DIMENSIONS_EDEFAULT);
				return;
			case DOMPackage.METHOD_DECLARATION__NAME:
				setName((SimpleName)null);
				return;
			case DOMPackage.METHOD_DECLARATION__RETURN_TYPE:
				setReturnType((Type)null);
				return;
			case DOMPackage.METHOD_DECLARATION__CONSTRUCTOR:
				setConstructor(DataMethodDeclaration.CONSTRUCTOR_EDEFAULT);
				return;
			case DOMPackage.METHOD_DECLARATION__VARARGS:
				setVarargs(DataMethodDeclaration.VARARGS_EDEFAULT);
				return;
			case DOMPackage.METHOD_DECLARATION__PARAMETERS:
				getParameters().clear();
				return;
			case DOMPackage.METHOD_DECLARATION__THROWN_EXCEPTIONS:
				getThrownExceptions().clear();
				return;
			case DOMPackage.METHOD_DECLARATION__TYPE_PARAMETERS:
				getTypeParameters().clear();
				return;
			case DOMPackage.METHOD_DECLARATION__BINDING:
				setBinding((IMethod)null);
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
			case DOMPackage.METHOD_DECLARATION__BODY:
				return getBody() != null;
			case DOMPackage.METHOD_DECLARATION__EXTRA_DIMENSIONS:
				return DataMethodDeclaration.EXTRA_DIMENSIONS_EDEFAULT == null ? getExtraDimensions() != null : !DataMethodDeclaration.EXTRA_DIMENSIONS_EDEFAULT.equals(getExtraDimensions());
			case DOMPackage.METHOD_DECLARATION__NAME:
				return getName() != null;
			case DOMPackage.METHOD_DECLARATION__RETURN_TYPE:
				return getReturnType() != null;
			case DOMPackage.METHOD_DECLARATION__CONSTRUCTOR:
				return DataMethodDeclaration.CONSTRUCTOR_EDEFAULT == null ? getConstructor() != null : !DataMethodDeclaration.CONSTRUCTOR_EDEFAULT.equals(getConstructor());
			case DOMPackage.METHOD_DECLARATION__VARARGS:
				return DataMethodDeclaration.VARARGS_EDEFAULT == null ? getVarargs() != null : !DataMethodDeclaration.VARARGS_EDEFAULT.equals(getVarargs());
			case DOMPackage.METHOD_DECLARATION__PARAMETERS:
				return getParameters() != null && !getParameters().isEmpty();
			case DOMPackage.METHOD_DECLARATION__THROWN_EXCEPTIONS:
				return getThrownExceptions() != null && !getThrownExceptions().isEmpty();
			case DOMPackage.METHOD_DECLARATION__TYPE_PARAMETERS:
				return getTypeParameters() != null && !getTypeParameters().isEmpty();
			case DOMPackage.METHOD_DECLARATION__BINDING:
				return getBinding() != null;
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
		if (data != null) result.append(((DataMethodDeclaration)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataMethodDeclaration extends DataBodyDeclaration {


	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected Block body;

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
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected SimpleName name;

	/**
	 * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected Type returnType;

	/**
	 * The default value of the '{@link #getConstructor() <em>Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstructor()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean CONSTRUCTOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConstructor() <em>Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstructor()
	 * @generated
	 * @ordered
	 */
	protected Boolean constructor = CONSTRUCTOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getVarargs() <em>Varargs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVarargs()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean VARARGS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVarargs() <em>Varargs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVarargs()
	 * @generated
	 * @ordered
	 */
	protected Boolean varargs = VARARGS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<SingleVariableDeclaration> parameters;

	/**
	 * The cached value of the '{@link #getThrownExceptions() <em>Thrown Exceptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThrownExceptions()
	 * @generated
	 * @ordered
	 */
	protected EList<Name> thrownExceptions;

	/**
	 * The cached value of the '{@link #getTypeParameters() <em>Type Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeParameter> typeParameters;

	/**
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected IMethod binding;

	/**
	 *Constructor of DataMethodDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMethodDeclaration() {
		super();
	}
	
		
	/**
	 *Constructor of DataMethodDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link BodyDeclaration }
	 * @generated
	 */
	public DataMethodDeclaration(DataBodyDeclaration data) {
		super();		
		
		modifiers = data.modifiers;
				
		javadoc = data.javadoc;
				
				
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
		result.append(", constructor: ");
		result.append(constructor);
		result.append(", varargs: ");
		result.append(varargs);
		result.append(')');
		return result.toString();
	}
		
}
} //MethodDeclarationImpl
