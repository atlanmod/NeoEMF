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
public class DeleteObject extends Entry {
    
    public DeleteObject(EObject value) {
        super(value);
    }

	public DeleteObject(Notification msg) {
		this((EObject)msg.getNotifier());
	}
    
}
