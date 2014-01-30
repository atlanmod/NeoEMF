package fr.inria.atlanmod.neo4emf.tests.reflection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ValueFactory {

	private EPackage ePackage;
	private EFactory eFactory;
	
	public ValueFactory(EPackage ePackage, EFactory eFactory) {
		this.ePackage = ePackage;
		this.eFactory = eFactory;
	}
	
	/**
	 * Returns a generated value for the given EObject.
	 * @param eObject the object to generate a value for.
	 * @return Object the generated value.
	 * @note If the given EObject is multi-valued the returned Object
	 * will be a EList<Object>.
	 */
	public Object generateValue(EStructuralFeature eFeature) {
		if(eFeature.isMany()) {
			EList<Object> features = new BasicEList<Object>();
			features.add(getValue(eFeature.getEType()));
			features.add(getValue(eFeature.getEType()));
			return features;
		}
		return getValue(eFeature.getEType());
	}
	
	/**
	 * Returns a single generated value for the given EClassifier.
	 * @param classifier the classifier to generate a value for.
	 * @return Object the generated value.
	 * @note This method is used internally by generateValue().
	 * @bug The generation of EDataType values is not correctly supported
	 * (it can only generate values for basic types like EString, EInt ...)
	 */
	public Object getValue(EClassifier classifier) {
		// TODO handle datatypes properly
		// TODO handle enums (they are subclasses of EClassifier)
		if(ePackage.eContents().contains(classifier)) {
			// The classifier is a generated class
			return eFactory.create((EClass)classifier);
		}
		return EcoreUtil.createFromString((EDataType) classifier, Integer.toString((int)(Math.random()*100)));
	}
	
}
