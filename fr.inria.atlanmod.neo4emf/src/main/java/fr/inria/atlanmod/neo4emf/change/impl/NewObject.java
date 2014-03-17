package fr.inria.atlanmod.neo4emf.change.impl;

import java.util.Iterator;

import org.eclipse.emf.ecore.EStructuralFeature;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.impl.Serializer;

/**
 *
 * @author sunye
 */
public class NewObject extends Entry {
	
	/**
	 * TODO ToFix
	 */
	private Object[] refs;
	
    public NewObject(INeo4emfObject value) {
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

	@Override
	public void process(Serializer serializer, boolean isTmp) {
		serializer.createNewObject(eObject,isTmp);
	}
    
}
