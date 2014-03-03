/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.inria.atlanmod.neo4emf.change.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;

import fr.inria.atlanmod.neo4emf.drivers.impl.Serializer;

/**
 * 
 * @author sunye
 */
abstract public class Entry {
	protected final EObject eObject;

	public Entry(Notification notif) {
		eObject = (EObject) notif.getNotifier();
	}

	public Entry(EObject value) {
		eObject = value;
	}

	public abstract void process(Serializer serializer);
	
	@Deprecated
	public EObject geteObject() {
		return eObject;
	}
}
