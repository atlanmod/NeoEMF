/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.inria.atlanmod.neo4emf.change.impl;

import org.eclipse.emf.common.notify.Notification;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.impl.Serializer;

/**
 * 
 * @author sunye
 */
abstract public class Entry {
	protected final INeo4emfObject eObject;

	public Entry(Notification notif) {
		eObject = (INeo4emfObject) notif.getNotifier();
	}

	public Entry(INeo4emfObject value) {
		eObject = value;
	}

	public abstract void process(Serializer serializer);
	
	@Deprecated
	public INeo4emfObject geteObject() {
		return eObject;
	}
}
