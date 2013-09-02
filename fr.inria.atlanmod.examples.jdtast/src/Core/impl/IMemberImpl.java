/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.IMember;
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
 * An implementation of the model object '<em><b>IMember</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.IMemberImpl#getSource <em>Source</em>}</li>
 *   <li>{@link Core.impl.IMemberImpl#getSourceRange <em>Source Range</em>}</li>
 *   <li>{@link Core.impl.IMemberImpl#getJavadocRange <em>Javadoc Range</em>}</li>
 *   <li>{@link Core.impl.IMemberImpl#getNameRange <em>Name Range</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class IMemberImpl extends IJavaElementImpl implements IMember {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//IJavaElementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IMemberImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IMEMBER;
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
		if ( data == null || !(data instanceof DataIMember))
			data = new DataIMember();
			
		if (((DataIMember)data).source == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IMEMBER__SOURCE, null, null));
		return ((DataIMember)data).source;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(String newSource) {
	
		if (data==null) data =  new DataIMember();
		
		else if (!(data instanceof DataIMember)) data = new DataIMember((DataIJavaElement)data);
	
		String oldSource = ((DataIMember)data).source;
		((DataIMember)data).source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__SOURCE, oldSource, ((DataIMember)data).source));
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
		if ( data == null || !(data instanceof DataIMember))
			data = new DataIMember();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IMEMBER__SOURCE_RANGE, null, null));
		return ((DataIMember)data).sourceRange;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSourceRange(ISourceRange newSourceRange, NotificationChain msgs) {
	
		if (data==null) data =  new DataIMember();
	
		ISourceRange oldSourceRange = ((DataIMember)data).sourceRange;
		((DataIMember)data).sourceRange = newSourceRange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__SOURCE_RANGE, oldSourceRange, newSourceRange);
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
	
		if (data==null) data =  new DataIMember();
		
		else if (!(data instanceof DataIMember)) data = new DataIMember((DataIJavaElement)data);
	
		if (newSourceRange != ((DataIMember)data).sourceRange) {
			NotificationChain msgs = null;
			if (((DataIMember)data).sourceRange != null)
				msgs = ((InternalEObject) ((DataIMember)data).sourceRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__SOURCE_RANGE, null, msgs);
			if (newSourceRange != null)
				msgs = ((InternalEObject)newSourceRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__SOURCE_RANGE, null, msgs);
			msgs = basicSetSourceRange(newSourceRange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__SOURCE_RANGE, newSourceRange, newSourceRange));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ISourceRange getJavadocRange() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIMember))
			data = new DataIMember();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IMEMBER__JAVADOC_RANGE, null, null));
		return ((DataIMember)data).javadocRange;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJavadocRange(ISourceRange newJavadocRange, NotificationChain msgs) {
	
		if (data==null) data =  new DataIMember();
	
		ISourceRange oldJavadocRange = ((DataIMember)data).javadocRange;
		((DataIMember)data).javadocRange = newJavadocRange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__JAVADOC_RANGE, oldJavadocRange, newJavadocRange);
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
	public void setJavadocRange(ISourceRange newJavadocRange) {
	
		if (data==null) data =  new DataIMember();
		
		else if (!(data instanceof DataIMember)) data = new DataIMember((DataIJavaElement)data);
	
		if (newJavadocRange != ((DataIMember)data).javadocRange) {
			NotificationChain msgs = null;
			if (((DataIMember)data).javadocRange != null)
				msgs = ((InternalEObject) ((DataIMember)data).javadocRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__JAVADOC_RANGE, null, msgs);
			if (newJavadocRange != null)
				msgs = ((InternalEObject)newJavadocRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__JAVADOC_RANGE, null, msgs);
			msgs = basicSetJavadocRange(newJavadocRange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__JAVADOC_RANGE, newJavadocRange, newJavadocRange));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ISourceRange getNameRange() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIMember))
			data = new DataIMember();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IMEMBER__NAME_RANGE, null, null));
		return ((DataIMember)data).nameRange;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNameRange(ISourceRange newNameRange, NotificationChain msgs) {
	
		if (data==null) data =  new DataIMember();
	
		ISourceRange oldNameRange = ((DataIMember)data).nameRange;
		((DataIMember)data).nameRange = newNameRange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__NAME_RANGE, oldNameRange, newNameRange);
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
	public void setNameRange(ISourceRange newNameRange) {
	
		if (data==null) data =  new DataIMember();
		
		else if (!(data instanceof DataIMember)) data = new DataIMember((DataIJavaElement)data);
	
		if (newNameRange != ((DataIMember)data).nameRange) {
			NotificationChain msgs = null;
			if (((DataIMember)data).nameRange != null)
				msgs = ((InternalEObject) ((DataIMember)data).nameRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__NAME_RANGE, null, msgs);
			if (newNameRange != null)
				msgs = ((InternalEObject)newNameRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__NAME_RANGE, null, msgs);
			msgs = basicSetNameRange(newNameRange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__NAME_RANGE, newNameRange, newNameRange));
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
			case CorePackage.IMEMBER__SOURCE_RANGE:
				return basicSetSourceRange(null, msgs);
			case CorePackage.IMEMBER__JAVADOC_RANGE:
				return basicSetJavadocRange(null, msgs);
			case CorePackage.IMEMBER__NAME_RANGE:
				return basicSetNameRange(null, msgs);
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
			case CorePackage.IMEMBER__SOURCE:
				return getSource();
			case CorePackage.IMEMBER__SOURCE_RANGE:
				return getSourceRange();
			case CorePackage.IMEMBER__JAVADOC_RANGE:
				return getJavadocRange();
			case CorePackage.IMEMBER__NAME_RANGE:
				return getNameRange();
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
			case CorePackage.IMEMBER__SOURCE:
				setSource((String)newValue);
				return;
			case CorePackage.IMEMBER__SOURCE_RANGE:
				setSourceRange((ISourceRange)newValue);
				return;
			case CorePackage.IMEMBER__JAVADOC_RANGE:
				setJavadocRange((ISourceRange)newValue);
				return;
			case CorePackage.IMEMBER__NAME_RANGE:
				setNameRange((ISourceRange)newValue);
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
			case CorePackage.IMEMBER__SOURCE:
				setSource(DataIMember.SOURCE_EDEFAULT);
				return;
			case CorePackage.IMEMBER__SOURCE_RANGE:
				setSourceRange((ISourceRange)null);
				return;
			case CorePackage.IMEMBER__JAVADOC_RANGE:
				setJavadocRange((ISourceRange)null);
				return;
			case CorePackage.IMEMBER__NAME_RANGE:
				setNameRange((ISourceRange)null);
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
			case CorePackage.IMEMBER__SOURCE:
				return DataIMember.SOURCE_EDEFAULT == null ? getSource() != null : !DataIMember.SOURCE_EDEFAULT.equals(getSource());
			case CorePackage.IMEMBER__SOURCE_RANGE:
				return getSourceRange() != null;
			case CorePackage.IMEMBER__JAVADOC_RANGE:
				return getJavadocRange() != null;
			case CorePackage.IMEMBER__NAME_RANGE:
				return getNameRange() != null;
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
				case CorePackage.IMEMBER__SOURCE: return CorePackage.ISOURCE_REFERENCE__SOURCE;
				case CorePackage.IMEMBER__SOURCE_RANGE: return CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE;
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
				case CorePackage.ISOURCE_REFERENCE__SOURCE: return CorePackage.IMEMBER__SOURCE;
				case CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE: return CorePackage.IMEMBER__SOURCE_RANGE;
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
		if (data != null) result.append(((DataIMember)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataIMember extends DataIJavaElement {


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
	 * The cached value of the '{@link #getJavadocRange() <em>Javadoc Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavadocRange()
	 * @generated
	 * @ordered
	 */
	protected ISourceRange javadocRange;

	/**
	 * The cached value of the '{@link #getNameRange() <em>Name Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameRange()
	 * @generated
	 * @ordered
	 */
	protected ISourceRange nameRange;

	/**
	 *Constructor of DataIMember
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataIMember() {
		super();
	}
	
		
	/**
	 *Constructor of DataIMember
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link IJavaElement }
	 * @generated
	 */
	public DataIMember(DataIJavaElement data) {
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
		result.append(')');
		return result.toString();
	}
		
}
} //IMemberImpl
