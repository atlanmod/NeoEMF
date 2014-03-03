/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.inria.atlanmod.neo4emf.change.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.neo4emf.drivers.impl.Serializer;

/**
 * 
 * @author sunye
 */
public class AddLink extends Entry {

	private final EReference eReference;
	private final Object oldValue;
	private final Object newValue;

	public AddLink(EObject object, EReference eRef, Object oldV, Object newV) {
		super(object);
		eReference = eRef;
		oldValue = oldV;
		newValue = newV;
	}

	public AddLink(Notification msg) {
		this((EObject) msg.getNotifier(), (EReference) msg.getFeature(), msg
				.getOldValue(), msg.getNewValue());
	}

	@Override
	public void process(Serializer serializer) {
		serializer.addNewLink(eObject, eReference, newValue);
	}
}
