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
public class Entry {
    private final EObject eObject;
    
    public Entry(Notification notif){
    	eObject = (EObject)notif.getNotifier();
    }
    public Entry(EObject value) {
        eObject = value;
    }
    public Entry(){ 
    		eObject=null;}
	public EObject geteObject() {
		return eObject;
	}
}
