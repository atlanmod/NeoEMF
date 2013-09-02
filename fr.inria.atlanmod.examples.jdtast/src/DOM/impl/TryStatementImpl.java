/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.Block;
import DOM.CatchClause;
import DOM.DOMPackage;
import DOM.TryStatement;

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
 * An implementation of the model object '<em><b>Try Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.TryStatementImpl#getCatchClauses <em>Catch Clauses</em>}</li>
 *   <li>{@link DOM.impl.TryStatementImpl#getBody <em>Body</em>}</li>
 *   <li>{@link DOM.impl.TryStatementImpl#getFinally <em>Finally</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TryStatementImpl extends StatementImpl implements TryStatement {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//StatementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TryStatementImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.TRY_STATEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CatchClause> getCatchClauses() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataTryStatement))
			data = new DataTryStatement();
				
	   
		if (((DataTryStatement)data).catchClauses == null) {
			((DataTryStatement)data).catchClauses = new EObjectContainmentEList<CatchClause>(CatchClause.class, this, DOMPackage.TRY_STATEMENT__CATCH_CLAUSES);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.TRY_STATEMENT__CATCH_CLAUSES);			
		}
		return ((DataTryStatement)data).catchClauses;	
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
		if ( data == null || !(data instanceof DataTryStatement))
			data = new DataTryStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.TRY_STATEMENT__BODY, null, null));
		return ((DataTryStatement)data).body;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(Block newBody, NotificationChain msgs) {
	
		if (data==null) data =  new DataTryStatement();
	
		Block oldBody = ((DataTryStatement)data).body;
		((DataTryStatement)data).body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.TRY_STATEMENT__BODY, oldBody, newBody);
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
	
		if (data==null) data =  new DataTryStatement();
		
		else if (!(data instanceof DataTryStatement)) data = new DataTryStatement((DataStatement)data);
	
		if (newBody != ((DataTryStatement)data).body) {
			NotificationChain msgs = null;
			if (((DataTryStatement)data).body != null)
				msgs = ((InternalEObject) ((DataTryStatement)data).body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TRY_STATEMENT__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TRY_STATEMENT__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.TRY_STATEMENT__BODY, newBody, newBody));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getFinally() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataTryStatement))
			data = new DataTryStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.TRY_STATEMENT__FINALLY, null, null));
		return ((DataTryStatement)data).finally_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFinally(Block newFinally, NotificationChain msgs) {
	
		if (data==null) data =  new DataTryStatement();
	
		Block oldFinally = ((DataTryStatement)data).finally_;
		((DataTryStatement)data).finally_ = newFinally;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.TRY_STATEMENT__FINALLY, oldFinally, newFinally);
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
	public void setFinally(Block newFinally) {
	
		if (data==null) data =  new DataTryStatement();
		
		else if (!(data instanceof DataTryStatement)) data = new DataTryStatement((DataStatement)data);
	
		if (newFinally != ((DataTryStatement)data).finally_) {
			NotificationChain msgs = null;
			if (((DataTryStatement)data).finally_ != null)
				msgs = ((InternalEObject) ((DataTryStatement)data).finally_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TRY_STATEMENT__FINALLY, null, msgs);
			if (newFinally != null)
				msgs = ((InternalEObject)newFinally).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TRY_STATEMENT__FINALLY, null, msgs);
			msgs = basicSetFinally(newFinally, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.TRY_STATEMENT__FINALLY, newFinally, newFinally));
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
			case DOMPackage.TRY_STATEMENT__CATCH_CLAUSES:
				return ((InternalEList<?>)getCatchClauses()).basicRemove(otherEnd, msgs);
			case DOMPackage.TRY_STATEMENT__BODY:
				return basicSetBody(null, msgs);
			case DOMPackage.TRY_STATEMENT__FINALLY:
				return basicSetFinally(null, msgs);
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
			case DOMPackage.TRY_STATEMENT__CATCH_CLAUSES:
				return getCatchClauses();
			case DOMPackage.TRY_STATEMENT__BODY:
				return getBody();
			case DOMPackage.TRY_STATEMENT__FINALLY:
				return getFinally();
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
			case DOMPackage.TRY_STATEMENT__CATCH_CLAUSES:
				getCatchClauses().clear();
				getCatchClauses().addAll((Collection<? extends CatchClause>)newValue);
				return;
			case DOMPackage.TRY_STATEMENT__BODY:
				setBody((Block)newValue);
				return;
			case DOMPackage.TRY_STATEMENT__FINALLY:
				setFinally((Block)newValue);
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
			case DOMPackage.TRY_STATEMENT__CATCH_CLAUSES:
				getCatchClauses().clear();
				return;
			case DOMPackage.TRY_STATEMENT__BODY:
				setBody((Block)null);
				return;
			case DOMPackage.TRY_STATEMENT__FINALLY:
				setFinally((Block)null);
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
			case DOMPackage.TRY_STATEMENT__CATCH_CLAUSES:
				return getCatchClauses() != null && !getCatchClauses().isEmpty();
			case DOMPackage.TRY_STATEMENT__BODY:
				return getBody() != null;
			case DOMPackage.TRY_STATEMENT__FINALLY:
				return getFinally() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataTryStatement extends DataStatement {


	/**
	 * The cached value of the '{@link #getCatchClauses() <em>Catch Clauses</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCatchClauses()
	 * @generated
	 * @ordered
	 */
	protected EList<CatchClause> catchClauses;

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
	 * The cached value of the '{@link #getFinally() <em>Finally</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinally()
	 * @generated
	 * @ordered
	 */
	protected Block finally_;

	/**
	 *Constructor of DataTryStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTryStatement() {
		super();
	}
	
		
	/**
	 *Constructor of DataTryStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	public DataTryStatement(DataStatement data) {
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
} //TryStatementImpl
