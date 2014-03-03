/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.inria.atlanmod.neo4emf.change.impl;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import fr.inria.atlanmod.neo4emf.drivers.impl.Serializer;

/**
 *
 * @author sunye
 */
public class NewObject extends Entry {
	
	/**
	 * ToFix
	 */
	private Object[] refs;
	
    public NewObject(EObject value) {
        super(value);
        int refSize = value.eClass().getEStructuralFeatures().size();
        refs = new Object[refSize];
        Iterator<EStructuralFeature> it = value.eClass().getEStructuralFeatures().iterator();
        int i = 0;
        while(it.hasNext()) {
        	refs[i] = value.eGet(it.next());
        	i++;
        }
    }

	public NewObject(Notification msg) {
		this ((EObject) msg.getNotifier());
	}

	@Override
	public void process(Serializer serializer, boolean isTmp) {
		serializer.createNewObject(eObject,isTmp);
	}
    
}
