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
public class NewObject extends Entry {
	
	
    public NewObject(INeo4emfObject value) {
        super(value);
    }

	public NewObject(Notification msg) {
		this ((INeo4emfObject) msg.getNotifier());
	}

	@Override
	public void process(Serializer serializer) {
		serializer.createNewObject(eObject);
		
	}
    
}
