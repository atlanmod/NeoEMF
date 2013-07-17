package fr.inria.atlanmod.neo4emf.impl;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;

public class ENotification extends ENotificationImpl implements
		INeo4emfNotification {

	public ENotification(InternalEObject notifier, int eventType,
			EStructuralFeature feature, Object oldValue, Object newValue,
			boolean isSetChange) {
		super(notifier, eventType, feature, oldValue, newValue, isSetChange);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			EStructuralFeature feature, Object oldValue, Object newValue,
			int position, boolean wasSet) {
		super(notifier, eventType, feature, oldValue, newValue, position, wasSet);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			EStructuralFeature feature, Object oldValue, Object newValue,
			int position) {
		super(notifier, eventType, feature, oldValue, newValue, position);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			EStructuralFeature feature, Object oldValue, Object newValue) {
		super(notifier, eventType, feature, oldValue, newValue);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, boolean oldBooleanValue, boolean newBooleanValue,
			boolean isSetChange) {
		super(notifier, eventType, featureID, oldBooleanValue, newBooleanValue,
				isSetChange);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, boolean oldBooleanValue, boolean newBooleanValue) {
		super(notifier, eventType, featureID, oldBooleanValue, newBooleanValue);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, byte oldByteValue, byte newByteValue,
			boolean isSetChange) {
		super(notifier, eventType, featureID, oldByteValue, newByteValue, isSetChange);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, byte oldByteValue, byte newByteValue) {
		super(notifier, eventType, featureID, oldByteValue, newByteValue);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, char oldCharValue, char newCharValue,
			boolean isSetChange) {
		super(notifier, eventType, featureID, oldCharValue, newCharValue, isSetChange);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, char oldCharValue, char newCharValue) {
		super(notifier, eventType, featureID, oldCharValue, newCharValue);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, double oldDoubleValue, double newDoubleValue,
			boolean isSetChange) {
		super(notifier, eventType, featureID, oldDoubleValue, newDoubleValue,
				isSetChange);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, double oldDoubleValue, double newDoubleValue) {
		super(notifier, eventType, featureID, oldDoubleValue, newDoubleValue);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, float oldFloatValue, float newFloatValue,
			boolean isSetChange) {
		super(notifier, eventType, featureID, oldFloatValue, newFloatValue, isSetChange);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, float oldFloatValue, float newFloatValue) {
		super(notifier, eventType, featureID, oldFloatValue, newFloatValue);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, int oldIntValue, int newIntValue, boolean isSetChange) {
		super(notifier, eventType, featureID, oldIntValue, newIntValue, isSetChange);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, int oldIntValue, int newIntValue) {
		super(notifier, eventType, featureID, oldIntValue, newIntValue);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, long oldLongValue, long newLongValue,
			boolean isSetChange) {
		super(notifier, eventType, featureID, oldLongValue, newLongValue, isSetChange);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, long oldLongValue, long newLongValue) {
		super(notifier, eventType, featureID, oldLongValue, newLongValue);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, Object oldValue, Object newValue, boolean isSetChange) {
		super(notifier, eventType, featureID, oldValue, newValue, isSetChange);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, Object oldValue, Object newValue, int position,
			boolean wasSet) {
		super(notifier, eventType, featureID, oldValue, newValue, position, wasSet);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, Object oldValue, Object newValue, int position) {
		super(notifier, eventType, featureID, oldValue, newValue, position);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, Object oldValue, Object newValue) {
		super(notifier, eventType, featureID, oldValue, newValue);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, short oldShortValue, short newShortValue,
			boolean isSetChange) {
		super(notifier, eventType, featureID, oldShortValue, newShortValue, isSetChange);
	
	}

	public ENotification(InternalEObject notifier, int eventType,
			int featureID, short oldShortValue, short newShortValue) {
		super(notifier, eventType, featureID, oldShortValue, newShortValue);
	
	}



}
