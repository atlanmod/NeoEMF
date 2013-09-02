/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.LabeledStatement;
import DOM.SimpleName;
import DOM.Statement;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Labeled Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.LabeledStatementImpl#getBody <em>Body</em>}</li>
 *   <li>{@link DOM.impl.LabeledStatementImpl#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LabeledStatementImpl extends StatementImpl implements LabeledStatement {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//StatementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LabeledStatementImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.LABELED_STATEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getBody() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataLabeledStatement))
			data = new DataLabeledStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.LABELED_STATEMENT__BODY, null, null));
		return ((DataLabeledStatement)data).body;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(Statement newBody, NotificationChain msgs) {
	
		if (data==null) data =  new DataLabeledStatement();
	
		Statement oldBody = ((DataLabeledStatement)data).body;
		((DataLabeledStatement)data).body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.LABELED_STATEMENT__BODY, oldBody, newBody);
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
	public void setBody(Statement newBody) {
	
		if (data==null) data =  new DataLabeledStatement();
		
		else if (!(data instanceof DataLabeledStatement)) data = new DataLabeledStatement((DataStatement)data);
	
		if (newBody != ((DataLabeledStatement)data).body) {
			NotificationChain msgs = null;
			if (((DataLabeledStatement)data).body != null)
				msgs = ((InternalEObject) ((DataLabeledStatement)data).body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.LABELED_STATEMENT__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.LABELED_STATEMENT__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.LABELED_STATEMENT__BODY, newBody, newBody));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleName getLabel() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataLabeledStatement))
			data = new DataLabeledStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.LABELED_STATEMENT__LABEL, null, null));
		return ((DataLabeledStatement)data).label;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabel(SimpleName newLabel, NotificationChain msgs) {
	
		if (data==null) data =  new DataLabeledStatement();
	
		SimpleName oldLabel = ((DataLabeledStatement)data).label;
		((DataLabeledStatement)data).label = newLabel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.LABELED_STATEMENT__LABEL, oldLabel, newLabel);
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
	public void setLabel(SimpleName newLabel) {
	
		if (data==null) data =  new DataLabeledStatement();
		
		else if (!(data instanceof DataLabeledStatement)) data = new DataLabeledStatement((DataStatement)data);
	
		if (newLabel != ((DataLabeledStatement)data).label) {
			NotificationChain msgs = null;
			if (((DataLabeledStatement)data).label != null)
				msgs = ((InternalEObject) ((DataLabeledStatement)data).label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.LABELED_STATEMENT__LABEL, null, msgs);
			if (newLabel != null)
				msgs = ((InternalEObject)newLabel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.LABELED_STATEMENT__LABEL, null, msgs);
			msgs = basicSetLabel(newLabel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.LABELED_STATEMENT__LABEL, newLabel, newLabel));
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
			case DOMPackage.LABELED_STATEMENT__BODY:
				return basicSetBody(null, msgs);
			case DOMPackage.LABELED_STATEMENT__LABEL:
				return basicSetLabel(null, msgs);
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
			case DOMPackage.LABELED_STATEMENT__BODY:
				return getBody();
			case DOMPackage.LABELED_STATEMENT__LABEL:
				return getLabel();
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
			case DOMPackage.LABELED_STATEMENT__BODY:
				setBody((Statement)newValue);
				return;
			case DOMPackage.LABELED_STATEMENT__LABEL:
				setLabel((SimpleName)newValue);
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
			case DOMPackage.LABELED_STATEMENT__BODY:
				setBody((Statement)null);
				return;
			case DOMPackage.LABELED_STATEMENT__LABEL:
				setLabel((SimpleName)null);
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
			case DOMPackage.LABELED_STATEMENT__BODY:
				return getBody() != null;
			case DOMPackage.LABELED_STATEMENT__LABEL:
				return getLabel() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataLabeledStatement extends DataStatement {


	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected Statement body;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected SimpleName label;

	/**
	 *Constructor of DataLabeledStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataLabeledStatement() {
		super();
	}
	
		
	/**
	 *Constructor of DataLabeledStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	public DataLabeledStatement(DataStatement data) {
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
} //LabeledStatementImpl
