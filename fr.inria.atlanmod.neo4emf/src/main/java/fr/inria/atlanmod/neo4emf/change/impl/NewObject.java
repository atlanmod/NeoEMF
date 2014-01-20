/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.inria.atlanmod.neo4emf.change.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * @author sunye
 */
public class NewObject extends Entry {
	
	
    public NewObject(EObject value) {
        super(value);
    }

	public NewObject(Notification msg) {
		this ((EObject) msg.getNotifier());
	}
    
}
