package fr.inria.atlanmod.kyanos.core;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;

import fr.inria.atlanmod.kyanos.core.impl.KyanosEFactoryImpl;


public interface KyanosEFactory extends EFactory {

	public static KyanosEFactory eINSTANCE = new KyanosEFactoryImpl();

	@Override
	public KyanosEObject create(EClass eClass);
	
}
