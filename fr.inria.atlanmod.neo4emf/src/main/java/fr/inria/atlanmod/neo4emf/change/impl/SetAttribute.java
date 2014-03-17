package fr.inria.atlanmod.neo4emf.change.impl;

import org.eclipse.emf.ecore.EAttribute;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.impl.Serializer;

/**
 * 
 * @author sunye
 */
public class SetAttribute extends Entry {
	private final EAttribute eAttribute;
	private final Object oldValue;
	private final Object newValue;

	public SetAttribute(INeo4emfObject obj, EAttribute attr, Object oldValue,
			Object newValue) {
		super(obj);
		eAttribute = attr;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@Override
	public void process(Serializer serializer, boolean isTmp) {
		serializer.setAttributeValue(eObject, eAttribute,newValue, isTmp);
		super.release();
	}
	
	@Deprecated
	public EAttribute geteAttribute() {
		return eAttribute;
	}

}
