/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.ISourceRange;
import Core.ISourceReference;
import Core.ITypeParameter;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IType Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.ITypeParameterImpl#getSource <em>Source</em>}</li>
 *   <li>{@link Core.impl.ITypeParameterImpl#getSourceRange <em>Source Range</em>}</li>
 *   <li>{@link Core.impl.ITypeParameterImpl#getBounds <em>Bounds</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ITypeParameterImpl extends IJavaElementImpl implements ITypeParameter {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//IJavaElementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ITypeParameterImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ITYPE_PARAMETER;
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
		if ( data == null || !(data instanceof DataITypeParameter))
			data = new DataITypeParameter();
			
		if (((DataITypeParameter)data).source == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ITYPE_PARAMETER__SOURCE, null, null));
		return ((DataITypeParameter)data).source;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(String newSource) {
	
		if (data==null) data =  new DataITypeParameter();
		
		else if (!(data instanceof DataITypeParameter)) data = new DataITypeParameter((DataIJavaElement)data);
	
		String oldSource = ((DataITypeParameter)data).source;
		((DataITypeParameter)data).source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ITYPE_PARAMETER__SOURCE, oldSource, ((DataITypeParameter)data).source));
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
		if ( data == null || !(data instanceof DataITypeParameter))
			data = new DataITypeParameter();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ITYPE_PARAMETER__SOURCE_RANGE, null, null));
		return ((DataITypeParameter)data).sourceRange;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSourceRange(ISourceRange newSourceRange, NotificationChain msgs) {
	
		if (data==null) data =  new DataITypeParameter();
	
		ISourceRange oldSourceRange = ((DataITypeParameter)data).sourceRange;
		((DataITypeParameter)data).sourceRange = newSourceRange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.ITYPE_PARAMETER__SOURCE_RANGE, oldSourceRange, newSourceRange);
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
	
		if (data==null) data =  new DataITypeParameter();
		
		else if (!(data instanceof DataITypeParameter)) data = new DataITypeParameter((DataIJavaElement)data);
	
		if (newSourceRange != ((DataITypeParameter)data).sourceRange) {
			NotificationChain msgs = null;
			if (((DataITypeParameter)data).sourceRange != null)
				msgs = ((InternalEObject) ((DataITypeParameter)data).sourceRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.ITYPE_PARAMETER__SOURCE_RANGE, null, msgs);
			if (newSourceRange != null)
				msgs = ((InternalEObject)newSourceRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.ITYPE_PARAMETER__SOURCE_RANGE, null, msgs);
			msgs = basicSetSourceRange(newSourceRange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ITYPE_PARAMETER__SOURCE_RANGE, newSourceRange, newSourceRange));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getBounds() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataITypeParameter))
			data = new DataITypeParameter();
			
		if (((DataITypeParameter)data).bounds == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	   
		if (((DataITypeParameter)data).bounds == null) {
			((DataITypeParameter)data).bounds = new EDataTypeEList<String>(String.class, this, CorePackage.ITYPE_PARAMETER__BOUNDS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.ITYPE_PARAMETER__BOUNDS);			
		}
		return ((DataITypeParameter)data).bounds;	
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
			case CorePackage.ITYPE_PARAMETER__SOURCE_RANGE:
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
			case CorePackage.ITYPE_PARAMETER__SOURCE:
				return getSource();
			case CorePackage.ITYPE_PARAMETER__SOURCE_RANGE:
				return getSourceRange();
			case CorePackage.ITYPE_PARAMETER__BOUNDS:
				return getBounds();
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
			case CorePackage.ITYPE_PARAMETER__SOURCE:
				setSource((String)newValue);
				return;
			case CorePackage.ITYPE_PARAMETER__SOURCE_RANGE:
				setSourceRange((ISourceRange)newValue);
				return;
			case CorePackage.ITYPE_PARAMETER__BOUNDS:
				getBounds().clear();
				getBounds().addAll((Collection<? extends String>)newValue);
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
			case CorePackage.ITYPE_PARAMETER__SOURCE:
				setSource(DataITypeParameter.SOURCE_EDEFAULT);
				return;
			case CorePackage.ITYPE_PARAMETER__SOURCE_RANGE:
				setSourceRange((ISourceRange)null);
				return;
			case CorePackage.ITYPE_PARAMETER__BOUNDS:
				getBounds().clear();
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
			case CorePackage.ITYPE_PARAMETER__SOURCE:
				return DataITypeParameter.SOURCE_EDEFAULT == null ? getSource() != null : !DataITypeParameter.SOURCE_EDEFAULT.equals(getSource());
			case CorePackage.ITYPE_PARAMETER__SOURCE_RANGE:
				return getSourceRange() != null;
			case CorePackage.ITYPE_PARAMETER__BOUNDS:
				return getBounds() != null && !getBounds().isEmpty();
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
				case CorePackage.ITYPE_PARAMETER__SOURCE: return CorePackage.ISOURCE_REFERENCE__SOURCE;
				case CorePackage.ITYPE_PARAMETER__SOURCE_RANGE: return CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE;
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
				case CorePackage.ISOURCE_REFERENCE__SOURCE: return CorePackage.ITYPE_PARAMETER__SOURCE;
				case CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE: return CorePackage.ITYPE_PARAMETER__SOURCE_RANGE;
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
		if (data != null) result.append(((DataITypeParameter)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataITypeParameter extends DataIJavaElement {


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
	 * The cached value of the '{@link #getBounds() <em>Bounds</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBounds()
	 * @generated
	 * @ordered
	 */
	protected EList<String> bounds;

	/**
	 *Constructor of DataITypeParameter
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataITypeParameter() {
		super();
	}
	
		
	/**
	 *Constructor of DataITypeParameter
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link IJavaElement }
	 * @generated
	 */
	public DataITypeParameter(DataIJavaElement data) {
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
		result.append(", bounds: ");
		result.append(bounds);
		result.append(')');
		return result.toString();
	}
		
}
} //ITypeParameterImpl
