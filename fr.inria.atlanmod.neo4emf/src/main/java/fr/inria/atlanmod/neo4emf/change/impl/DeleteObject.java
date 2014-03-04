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
public class DeleteObject extends Entry {

	public DeleteObject(INeo4emfObject value) {
		super(value);
	}

	public DeleteObject(Notification msg) {
		this((INeo4emfObject) msg.getNotifier());
	}

	@Override
	public void process(Serializer serializer) {
		serializer.deleteExistingObject(eObject);

	}

}
