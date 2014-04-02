package fr.inria.atlanmod.kyanos.core.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

import fr.inria.atlanmod.kyanos.core.KyanosEFactory;
import fr.inria.atlanmod.kyanos.core.KyanosInternalEObject;

public class KyanosEFactoryImpl extends EFactoryImpl implements KyanosEFactory {

	@Override
	public KyanosInternalEObject create(EClass eClass) {
		KyanosEObjectImpl eObject = new KyanosEObjectImpl();
		eObject.eSetClass(eClass);
		return eObject;
	}
}
