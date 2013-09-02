/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.AbstractTypeDeclaration;
import DOM.DOMPackage;
import DOM.TypeDeclarationStatement;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Declaration Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.TypeDeclarationStatementImpl#getDeclaration <em>Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeDeclarationStatementImpl extends StatementImpl implements TypeDeclarationStatement {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//StatementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeDeclarationStatementImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.TYPE_DECLARATION_STATEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractTypeDeclaration getDeclaration() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataTypeDeclarationStatement))
			data = new DataTypeDeclarationStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.TYPE_DECLARATION_STATEMENT__DECLARATION, null, null));
		return ((DataTypeDeclarationStatement)data).declaration;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeclaration(AbstractTypeDeclaration newDeclaration, NotificationChain msgs) {
	
		if (data==null) data =  new DataTypeDeclarationStatement();
	
		AbstractTypeDeclaration oldDeclaration = ((DataTypeDeclarationStatement)data).declaration;
		((DataTypeDeclarationStatement)data).declaration = newDeclaration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.TYPE_DECLARATION_STATEMENT__DECLARATION, oldDeclaration, newDeclaration);
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
	public void setDeclaration(AbstractTypeDeclaration newDeclaration) {
	
		if (data==null) data =  new DataTypeDeclarationStatement();
		
		else if (!(data instanceof DataTypeDeclarationStatement)) data = new DataTypeDeclarationStatement((DataStatement)data);
	
		if (newDeclaration != ((DataTypeDeclarationStatement)data).declaration) {
			NotificationChain msgs = null;
			if (((DataTypeDeclarationStatement)data).declaration != null)
				msgs = ((InternalEObject) ((DataTypeDeclarationStatement)data).declaration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TYPE_DECLARATION_STATEMENT__DECLARATION, null, msgs);
			if (newDeclaration != null)
				msgs = ((InternalEObject)newDeclaration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TYPE_DECLARATION_STATEMENT__DECLARATION, null, msgs);
			msgs = basicSetDeclaration(newDeclaration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.TYPE_DECLARATION_STATEMENT__DECLARATION, newDeclaration, newDeclaration));
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
			case DOMPackage.TYPE_DECLARATION_STATEMENT__DECLARATION:
				return basicSetDeclaration(null, msgs);
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
			case DOMPackage.TYPE_DECLARATION_STATEMENT__DECLARATION:
				return getDeclaration();
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
			case DOMPackage.TYPE_DECLARATION_STATEMENT__DECLARATION:
				setDeclaration((AbstractTypeDeclaration)newValue);
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
			case DOMPackage.TYPE_DECLARATION_STATEMENT__DECLARATION:
				setDeclaration((AbstractTypeDeclaration)null);
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
			case DOMPackage.TYPE_DECLARATION_STATEMENT__DECLARATION:
				return getDeclaration() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataTypeDeclarationStatement extends DataStatement {


	/**
	 * The cached value of the '{@link #getDeclaration() <em>Declaration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaration()
	 * @generated
	 * @ordered
	 */
	protected AbstractTypeDeclaration declaration;

	/**
	 *Constructor of DataTypeDeclarationStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTypeDeclarationStatement() {
		super();
	}
	
		
	/**
	 *Constructor of DataTypeDeclarationStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	public DataTypeDeclarationStatement(DataStatement data) {
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
} //TypeDeclarationStatementImpl
