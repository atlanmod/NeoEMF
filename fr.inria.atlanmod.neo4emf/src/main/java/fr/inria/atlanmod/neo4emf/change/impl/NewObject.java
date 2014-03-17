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
	 * TODO ToFix, may be fixed with the proper solution to handle
	 * resource.add(..) at the end of the execution (= a way to store 
	 * properly strong references when there is no ChangeLog accessible from
	 * a given object).
	 * 
	 * In that case the idea is to store strong references at the creation of the
	 * object and allow the changelog to remove those references when the objects are
	 * saved (accessible from the member values of the entries).
	 */
	//private Object[] refs;
	
    public NewObject(INeo4emfObject value) {
        super(value);
        //int refSize = value.eClass().getEStructuralFeatures().size();
       /* refs = new Object[refSize];
        Iterator<EStructuralFeature> it = value.eClass().getEStructuralFeatures().iterator();
        int i = 0;
        while(it.hasNext()) {
        	refs[i] = value.eGet(it.next());
        	i++;
        }*/
    }

	@Override
	public void process(Serializer serializer, boolean isTmp) {
		serializer.createNewObject(eObject,isTmp);
		super.release();
	}
    
}
