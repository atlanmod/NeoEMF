/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.ISourceRange;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ISource Range</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.ISourceRangeImpl#getLength <em>Length</em>}</li>
 *   <li>{@link Core.impl.ISourceRangeImpl#getOffset <em>Offset</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ISourceRangeImpl extends Neo4emfObject implements ISourceRange {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//Neo4emfObject
	
	/**
	 * The cached value of the data structure {@link DataISourceRange <em>data</em> } 
	 * @generated
	 */
	 	protected DataISourceRange data;
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ISourceRangeImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ISOURCE_RANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getLength() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataISourceRange))
			data = new DataISourceRange();
			
		if (((DataISourceRange)data).length == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ISOURCE_RANGE__LENGTH, null, null));
		return ((DataISourceRange)data).length;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLength(Integer newLength) {
	
		if (data==null) data =  new DataISourceRange();
		
		Integer oldLength = ((DataISourceRange)data).length;
		((DataISourceRange)data).length = newLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ISOURCE_RANGE__LENGTH, oldLength, ((DataISourceRange)data).length));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getOffset() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataISourceRange))
			data = new DataISourceRange();
			
		if (((DataISourceRange)data).offset == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ISOURCE_RANGE__OFFSET, null, null));
		return ((DataISourceRange)data).offset;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOffset(Integer newOffset) {
	
		if (data==null) data =  new DataISourceRange();
		
		Integer oldOffset = ((DataISourceRange)data).offset;
		((DataISourceRange)data).offset = newOffset;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ISOURCE_RANGE__OFFSET, oldOffset, ((DataISourceRange)data).offset));
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
			case CorePackage.ISOURCE_RANGE__LENGTH:
				return getLength();
			case CorePackage.ISOURCE_RANGE__OFFSET:
				return getOffset();
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
			case CorePackage.ISOURCE_RANGE__LENGTH:
				setLength((Integer)newValue);
				return;
			case CorePackage.ISOURCE_RANGE__OFFSET:
				setOffset((Integer)newValue);
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
			case CorePackage.ISOURCE_RANGE__LENGTH:
				setLength(DataISourceRange.LENGTH_EDEFAULT);
				return;
			case CorePackage.ISOURCE_RANGE__OFFSET:
				setOffset(DataISourceRange.OFFSET_EDEFAULT);
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
			case CorePackage.ISOURCE_RANGE__LENGTH:
				return DataISourceRange.LENGTH_EDEFAULT == null ? getLength() != null : !DataISourceRange.LENGTH_EDEFAULT.equals(getLength());
			case CorePackage.ISOURCE_RANGE__OFFSET:
				return DataISourceRange.OFFSET_EDEFAULT == null ? getOffset() != null : !DataISourceRange.OFFSET_EDEFAULT.equals(getOffset());
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
		if (data != null) result.append(((DataISourceRange)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataISourceRange {


	/**
	 * The default value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected static final Integer LENGTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected Integer length = LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getOffset() <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOffset()
	 * @generated
	 * @ordered
	 */
	protected static final Integer OFFSET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOffset() <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOffset()
	 * @generated
	 * @ordered
	 */
	protected Integer offset = OFFSET_EDEFAULT;

	/**
	 *Constructor of DataISourceRange
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataISourceRange() {
		super();
	}
	
		
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (length: ");
		result.append(length);
		result.append(", offset: ");
		result.append(offset);
		result.append(')');
		return result.toString();
	}
		
}
} //ISourceRangeImpl
