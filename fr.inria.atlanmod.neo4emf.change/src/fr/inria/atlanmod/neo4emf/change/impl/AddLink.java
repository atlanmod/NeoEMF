/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.inria.atlanmod.neo4emf.change.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 *
 * @author sunye
 */
public class AddLink extends Entry {
	
	private final EReference eReference;
    private final Object oldValue;
    private final Object newValue;
    
	public AddLink(EObject object, EReference eRef, Object oldV, Object newV ) {
		super(object);
		eReference= eRef;
		oldValue=oldV;
		newValue = newV;
	}
    
	public AddLink (Notification msg){
	this ((EObject)msg.getNotifier(),(EReference)msg.getFeature(),msg.getOldValue(),msg.getNewValue());
	}

	public EReference geteReference() {
		return eReference;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public Object getNewValue() {
		return newValue;
	}
}
