/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.IImportDeclaration;
import Core.ISourceRange;
import Core.ISourceReference;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IImport Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.IImportDeclarationImpl#getSource <em>Source</em>}</li>
 *   <li>{@link Core.impl.IImportDeclarationImpl#getSourceRange <em>Source Range</em>}</li>
 *   <li>{@link Core.impl.IImportDeclarationImpl#getIsOnDemand <em>Is On Demand</em>}</li>
 *   <li>{@link Core.impl.IImportDeclarationImpl#getIsStatic <em>Is Static</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IImportDeclarationImpl extends IJavaElementImpl implements IImportDeclaration {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//IJavaElementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IImportDeclarationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IIMPORT_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSource() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIImportDeclaration))
			data = new DataIImportDeclaration();
			
		if (((DataIImportDeclaration)data).source == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IIMPORT_DECLARATION__SOURCE, null, null));
		return ((DataIImportDeclaration)data).source;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(String newSource) {
	
		if (data==null) data =  new DataIImportDeclaration();
		
		else if (!(data instanceof DataIImportDeclaration)) data = new DataIImportDeclaration((DataIJavaElement)data);
	
		String oldSource = ((DataIImportDeclaration)data).source;
		((DataIImportDeclaration)data).source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IIMPORT_DECLARATION__SOURCE, oldSource, ((DataIImportDeclaration)data).source));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ISourceRange getSourceRange() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIImportDeclaration))
			data = new DataIImportDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE, null, null));
		return ((DataIImportDeclaration)data).sourceRange;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSourceRange(ISourceRange newSourceRange, NotificationChain msgs) {
	
		if (data==null) data =  new DataIImportDeclaration();
	
		ISourceRange oldSourceRange = ((DataIImportDeclaration)data).sourceRange;
		((DataIImportDeclaration)data).sourceRange = newSourceRange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE, oldSourceRange, newSourceRange);
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
	public void setSourceRange(ISourceRange newSourceRange) {
	
		if (data==null) data =  new DataIImportDeclaration();
		
		else if (!(data instanceof DataIImportDeclaration)) data = new DataIImportDeclaration((DataIJavaElement)data);
	
		if (newSourceRange != ((DataIImportDeclaration)data).sourceRange) {
			NotificationChain msgs = null;
			if (((DataIImportDeclaration)data).sourceRange != null)
				msgs = ((InternalEObject) ((DataIImportDeclaration)data).sourceRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE, null, msgs);
			if (newSourceRange != null)
				msgs = ((InternalEObject)newSourceRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE, null, msgs);
			msgs = basicSetSourceRange(newSourceRange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE, newSourceRange, newSourceRange));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsOnDemand() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIImportDeclaration))
			data = new DataIImportDeclaration();
			
		if (((DataIImportDeclaration)data).isOnDemand == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IIMPORT_DECLARATION__IS_ON_DEMAND, null, null));
		return ((DataIImportDeclaration)data).isOnDemand;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsOnDemand(Boolean newIsOnDemand) {
	
		if (data==null) data =  new DataIImportDeclaration();
		
		else if (!(data instanceof DataIImportDeclaration)) data = new DataIImportDeclaration((DataIJavaElement)data);
	
		Boolean oldIsOnDemand = ((DataIImportDeclaration)data).isOnDemand;
		((DataIImportDeclaration)data).isOnDemand = newIsOnDemand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IIMPORT_DECLARATION__IS_ON_DEMAND, oldIsOnDemand, ((DataIImportDeclaration)data).isOnDemand));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsStatic() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIImportDeclaration))
			data = new DataIImportDeclaration();
			
		if (((DataIImportDeclaration)data).isStatic == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IIMPORT_DECLARATION__IS_STATIC, null, null));
		return ((DataIImportDeclaration)data).isStatic;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsStatic(Boolean newIsStatic) {
	
		if (data==null) data =  new DataIImportDeclaration();
		
		else if (!(data instanceof DataIImportDeclaration)) data = new DataIImportDeclaration((DataIJavaElement)data);
	
		Boolean oldIsStatic = ((DataIImportDeclaration)data).isStatic;
		((DataIImportDeclaration)data).isStatic = newIsStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IIMPORT_DECLARATION__IS_STATIC, oldIsStatic, ((DataIImportDeclaration)data).isStatic));
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
			case CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE:
				return basicSetSourceRange(null, msgs);
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
			case CorePackage.IIMPORT_DECLARATION__SOURCE:
				return getSource();
			case CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE:
				return getSourceRange();
			case CorePackage.IIMPORT_DECLARATION__IS_ON_DEMAND:
				return getIsOnDemand();
			case CorePackage.IIMPORT_DECLARATION__IS_STATIC:
				return getIsStatic();
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
			case CorePackage.IIMPORT_DECLARATION__SOURCE:
				setSource((String)newValue);
				return;
			case CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE:
				setSourceRange((ISourceRange)newValue);
				return;
			case CorePackage.IIMPORT_DECLARATION__IS_ON_DEMAND:
				setIsOnDemand((Boolean)newValue);
				return;
			case CorePackage.IIMPORT_DECLARATION__IS_STATIC:
				setIsStatic((Boolean)newValue);
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
			case CorePackage.IIMPORT_DECLARATION__SOURCE:
				setSource(DataIImportDeclaration.SOURCE_EDEFAULT);
				return;
			case CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE:
				setSourceRange((ISourceRange)null);
				return;
			case CorePackage.IIMPORT_DECLARATION__IS_ON_DEMAND:
				setIsOnDemand(DataIImportDeclaration.IS_ON_DEMAND_EDEFAULT);
				return;
			case CorePackage.IIMPORT_DECLARATION__IS_STATIC:
				setIsStatic(DataIImportDeclaration.IS_STATIC_EDEFAULT);
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
			case CorePackage.IIMPORT_DECLARATION__SOURCE:
				return DataIImportDeclaration.SOURCE_EDEFAULT == null ? getSource() != null : !DataIImportDeclaration.SOURCE_EDEFAULT.equals(getSource());
			case CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE:
				return getSourceRange() != null;
			case CorePackage.IIMPORT_DECLARATION__IS_ON_DEMAND:
				return DataIImportDeclaration.IS_ON_DEMAND_EDEFAULT == null ? getIsOnDemand() != null : !DataIImportDeclaration.IS_ON_DEMAND_EDEFAULT.equals(getIsOnDemand());
			case CorePackage.IIMPORT_DECLARATION__IS_STATIC:
				return DataIImportDeclaration.IS_STATIC_EDEFAULT == null ? getIsStatic() != null : !DataIImportDeclaration.IS_STATIC_EDEFAULT.equals(getIsStatic());
		}
		return super.eIsSet(featureID);
	}


	/**
	 * <!-- begin-user-doc -->
	 *YY19
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ISourceReference.class) {
			switch (derivedFeatureID) {
				case CorePackage.IIMPORT_DECLARATION__SOURCE: return CorePackage.ISOURCE_REFERENCE__SOURCE;
				case CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE: return CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY20
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ISourceReference.class) {
			switch (baseFeatureID) {
				case CorePackage.ISOURCE_REFERENCE__SOURCE: return CorePackage.IIMPORT_DECLARATION__SOURCE;
				case CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE: return CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		if (data != null) result.append(((DataIImportDeclaration)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataIImportDeclaration extends DataIJavaElement {


	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected String source = SOURCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSourceRange() <em>Source Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceRange()
	 * @generated
	 * @ordered
	 */
	protected ISourceRange sourceRange;

	/**
	 * The default value of the '{@link #getIsOnDemand() <em>Is On Demand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsOnDemand()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_ON_DEMAND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsOnDemand() <em>Is On Demand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsOnDemand()
	 * @generated
	 * @ordered
	 */
	protected Boolean isOnDemand = IS_ON_DEMAND_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsStatic() <em>Is Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsStatic()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_STATIC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsStatic() <em>Is Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsStatic()
	 * @generated
	 * @ordered
	 */
	protected Boolean isStatic = IS_STATIC_EDEFAULT;

	/**
	 *Constructor of DataIImportDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataIImportDeclaration() {
		super();
	}
	
		
	/**
	 *Constructor of DataIImportDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link IJavaElement }
	 * @generated
	 */
	public DataIImportDeclaration(DataIJavaElement data) {
		super();		
		
		elementName = data.elementName;
				
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (source: ");
		result.append(source);
		result.append(", isOnDemand: ");
		result.append(isOnDemand);
		result.append(", isStatic: ");
		result.append(isStatic);
		result.append(')');
		return result.toString();
	}
		
}
} //IImportDeclarationImpl
