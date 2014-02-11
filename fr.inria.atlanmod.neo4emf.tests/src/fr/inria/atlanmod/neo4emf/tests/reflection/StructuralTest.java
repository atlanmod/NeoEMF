package fr.inria.atlanmod.neo4emf.tests.reflection;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;

@RunWith(Parameterized.class)
public class StructuralTest {
	
	private EPackage ePackage;
	private EFactory eFactory;
	//private Map mapping;
	private EList<EClassifier> packageClassifiers;
	
	private INeo4emfResource neo4emfRoot;
	
	private ValueFactory valueFactory;
	
	@Parameterized.Parameters
	public static List<Object[]> data() {
		return InputData.allData("Structural");
	}
	
	public StructuralTest(Object currentEPackage, Object currentEFactory, Object currentMapping, Object neoResource) {
		ePackage = (EPackage)currentEPackage;
		eFactory = (EFactory)currentEFactory;
		//mapping = (Map)currentMapping;
		
		neo4emfRoot = (INeo4emfResource)neoResource;
		// Register the package
		EPackage.Registry.INSTANCE.put(ePackage.getName().toLowerCase(), ePackage);
		// extract classes from the package
		packageClassifiers = ePackage.getEClassifiers();
		valueFactory = new ValueFactory(ePackage, eFactory);
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		neo4emfRoot.getContents().clear();
	}
	
	/**
	 * Iterate through the EClass in the metamodel and instantiate them through the
	 * factory.
	 * Check if the instances have been correctly created and if their EAttributes are
	 * set to their default values.
	 */
	@Test
	public void testCreateClassInstance() {
		for(EClassifier currentClassifier : packageClassifiers) {
			EClass currentClass = (EClass)currentClassifier;
			System.out.println("Testing factory creation for class " + currentClass.getName());
			EObject classInstance = eFactory.create(currentClass);
			Assert.assertNotEquals("Instance of the class \""+currentClass.getName()+"\" is null.", classInstance, null);
			for(EObject it : currentClass.eContents()) {
				if(it instanceof EStructuralFeature) {
					EStructuralFeature feature = (EStructuralFeature)it;
					if(feature.isMany()) {
						// There is no default value for multi-valued features, it is always
						// an empty EList.
						Assert.assertEquals("Multi-valued feature \""+feature.getName()+"\" of object \""+currentClass.getName()+"\" is not empty.",
								ECollections.EMPTY_ELIST,
								classInstance.eGet(feature));
					}
					else {
						Assert.assertEquals("Feature \""+feature.getName()+"\" of object \""+currentClass.getName()+"\" is not equal to its default value",
								feature.getDefaultValue(),
								classInstance.eGet(feature));
					}
				}
			}
		}
	}

	/**
	 * Iterate through the EAttributes of each EClass in the metamodel and set their value to
	 * a random generated value.
	 * Check if the EAttributes have been correctly set (through eGet method) or have generated an
	 * exception (in case they are unchangeable).
	 */
	@Test
	public void testSetAttribute() {
		for(EClassifier currentClassifier : packageClassifiers) {
			EClass currentClass = (EClass)currentClassifier;
			System.out.println("Testing attributes for class " + currentClass.getName());
			for(EObject it : currentClass.eContents()) {
				EObject classInstance = eFactory.create(currentClass);
				if(it instanceof EAttribute) {
					EAttribute attribute = (EAttribute)it;
					Object attributeValue = valueFactory.generateValue(attribute);
					if(attribute.isChangeable()) {
						classInstance.eSet(attribute,attributeValue);
						Assert.assertEquals("Attribute \""+attribute.getName()+"\" of object \""+currentClass.getName()+"\" not set properly.",
								classInstance.eGet(attribute),
								attributeValue);
					}
					else {
						try {
							classInstance.eSet(attribute,attributeValue);
							fail("Unchangeable attribute \"" +attribute.getName()+"\" of object \""+currentClass.getName()+"\" has been changed.");
						}catch(IllegalArgumentException e) {
							// Check eventual side effects even if the exception has been correctly thrown
							Assert.assertEquals("Unchangeable attribute \""+attribute.getName()+"\" of object \""+currentClass.getName()+"\" is no longer equal to its default value.",
									classInstance.eGet(attribute),
									attribute.getDefaultValue());
						}
					}
				}
			}
		}
	}
	
	/**
	 * Iterate through the EStructuralFeatures of each EClass in the metamodel and check double
	 * addition in unique ones.
	 * Check that the generated EStructuralFeature contains no double elements.
	 */
	@Test
	public void testSetOrderedFeatures() {
		for(EClassifier currentClassifier : packageClassifiers) {
			EClass currentClass = (EClass)currentClassifier;
			System.out.println("Testing ordered features for class " + currentClass.getName());
			for(EObject it : currentClass.eContents()) {
				if(it instanceof EStructuralFeature) {
					EStructuralFeature feature = (EStructuralFeature)it;
					if(feature.isOrdered()) {
						if(feature.isMany()) {
							// Tests are only done on multi valued EStructuralFeatures,
							// single valued EStructuralFeatures obviously cannot contains
							// double elements.
							EObject classInstance = eFactory.create(currentClass);
							// Do not call generateValue, it will return a list.
							Object value = (Object)valueFactory.getValue(feature.getEType());
							EList<Object> toSet = new BasicEList<Object>();
							toSet.add(value);
							toSet.add(value);
							classInstance.eSet(feature, toSet);
							@SuppressWarnings("unchecked") EList<Object> inInstanceValues = (EList<Object>)classInstance.eGet(feature);
							assert inInstanceValues.size() <= 1 : "Unique attribute  \"" + feature.getName() + "\" of object \"" + 
									currentClass.getName() + "\" contains more element than expected (" + (inInstanceValues.size()) +
									" instead of 1).";
							assert inInstanceValues.size() > 0 : "Unique attribute \"" + feature.getName() + "\" of object \"" +
									currentClass.getName() + "\" is empty, expected 1 element.";
							assert inInstanceValues.get(0).equals(value) : "Unique attribute \"" + feature.getName() +
									"\" of object \"" + currentClass.getName() + "\" is not equal to the set one";
						}
					}
				}
			}
		}
	}
	
	/**
	 * Iterate through the EReferences of each EClass in the metamodel and set their value to
	 * a generated EClass instance.
	 * Check if the EReferences have been correctly set (through eGet method) or have generated an
	 * exception (in case they are unchangeable).
	 */
	@Test
	public void testSetReference() {
		for(EClassifier currentClassifier : packageClassifiers) {
			EClass currentClass = (EClass)currentClassifier;
			System.out.println("Testing references for class " + currentClass.getName());
			for(EObject it : currentClass.eContents()) {
				// loop in the contents and instantiate a model
				EObject classInstance = eFactory.create(currentClass);
				if(it instanceof EReference){
					EReference reference = (EReference)it;
					Object referenceValue = valueFactory.generateValue(reference);
					checkEReferences(classInstance, reference, referenceValue);
					checkEOppositeReference(classInstance, reference, referenceValue);
				}
			}
		}
	}
	
	/**
	 * Create an object with all its direct links (EAttribute and EReference)
	 * and delete it.
	 * The test check that
	 * 	- the object has been removed from its resource set
	 * 	- the EAttribute instances related to the object has been deleted
	 * 	- the EReference instances related to the object has been deleted
	 * 	- the opposite EReference values has been properly set to null
	 * 	- the EReference with attribute "contained" has been deleted.
	 */
	@Test
	public void testDeleteClassInstance() {
		for(EClassifier currentClassifier : packageClassifiers) {
			EClass currentClass = (EClass)currentClassifier;
			System.out.println("Testing util deletion of class " + currentClass.getName());
			EObject classInstance = eFactory.create(currentClass);
			neo4emfRoot.getContents().add(classInstance);
			// EObjects contained in classInstance.
			List<EObject> containedEObjects = new ArrayList<EObject>();
			// EObjects related to classInstance, save also the EReference handling the
			// association for convenience.
			Map<EObject,EReference> relatedEObjects = new HashMap<EObject,EReference>();
			// Populate the instance with all its direct referenced EObjects
			// EAttributes are not tested here because they are local to the instance
			// and cannot create consistency issues.
			for(EObject it : currentClass.eContents()) {
				if(it instanceof EReference) {
					EReference reference = (EReference)it;
					if(reference.isChangeable()) {
						Object referenceValue = valueFactory.generateValue(reference);
						if(referenceValue instanceof EObject) {
						neo4emfRoot.getContents().add((EObject)referenceValue);
						}
						else if(referenceValue instanceof EList) {
							neo4emfRoot.getContents().addAll((EList<EObject>)referenceValue);
						}
						else {
							fail("Fail to generate proper value for EReference \"" + reference.getName() + "\".");
						}
						classInstance.eSet(reference, referenceValue);
						if(reference.isContainment()) {
							if(referenceValue instanceof EObject) {
								containedEObjects.add((EObject)referenceValue);
							}
							else if(referenceValue instanceof EList) {
								containedEObjects.addAll((EList<EObject>)referenceValue);
							}
							else {
								fail("Fail to generate proper value for EReference \"" + reference.getName() + "\".");
							}
						}
						else {
							// Simple EReference without containment feature
							if(referenceValue instanceof EObject) {
								if(reference.getEOpposite() != null) {
									relatedEObjects.put((EObject)referenceValue,reference.getEOpposite());
								}
							}
							else if(referenceValue instanceof EList) {
								for(EObject currentReferenceValue : (EList<EObject>)referenceValue) {
									if(reference.getEOpposite() != null) {
										relatedEObjects.put(currentReferenceValue,reference.getEOpposite());
									}
								}
							}
							else {
								fail("Fail to generate proper value for EReference \"" + reference.getName() + "\".");
							}
						}
					}
				}
			}
			EObject instanceContainer = classInstance.eContainer();
			EcoreUtil.delete(classInstance);
			checkDeletedFromRoot(classInstance);
			
			if(instanceContainer != null) {
				assert !instanceContainer.eContents().contains(classInstance) : "Deleted EObject's (" + currentClass.getName() +
					") parent still contains it after its deletion (eContents method).";
			}
			// Iterate through the deleted EObject's contained elements and check they have
			// been correctly deleted.
			for(EObject containedEObject : containedEObjects) {
				checkDeletedFromRoot(containedEObject);
			}
			// Iterator through the objects referencing the deleted one and check the EReferences
			// has been correctly updated.
			for(EObject relatedObject : relatedEObjects.keySet()) {
				EReference relatedEReference = relatedEObjects.get(relatedObject);
				// Non changeable references are not updated when the EObject they reference to
				// is deleted. (strange behaviour, investigating)
				if(relatedEReference.isChangeable()) {
					Object referencedValue = relatedObject.eGet(relatedEReference);
					if(referencedValue instanceof EObject) {
						assert !referencedValue.equals(classInstance);
					}
					else if(referencedValue instanceof EList) {
						assert !((EList<EObject>)referencedValue).contains(classInstance);
					}
				}
			}
			
			// Clean the resource for the next class instance.
			neo4emfRoot.getContents().clear();
		}
	}
	
	/**
	 * Set the EReference value of classInstance to the given referenceValue and check it
	 * has been correctly updated (or not if the reference is not changeable).
	 * @param classInstance the EObject containing the EReference to check.
	 * @param reference the EReference to check.
	 * @param referenceValue the value to set and check to the EReference for the given EObject.
	 */
	private void checkEReferences(EObject classInstance, EReference reference, Object referenceValue) {
		if(reference.isChangeable()) {
			classInstance.eSet(reference,referenceValue);
			Assert.assertEquals("Reference \""+reference.getName()+ "\" (" + reference.getEReferenceType().getName() +
					") of object \"" + classInstance.eClass().getName()+" not set properly.",
					classInstance.eGet(reference),
					referenceValue);
		}
		else {
			try {
				classInstance.eSet(reference,referenceValue);
				fail("Unchangeable attribute \"" + reference.getName()+ "\" of object \""+classInstance.eClass().getName()+"\" has been changed.");
			}catch(IllegalArgumentException e) {
				// Check eventual side effects even if the exception has been correctly thrown
				Assert.assertEquals("Unchangeable attribute \""+reference.getName()+"\" of object \""+classInstance.eClass().getName()+"\" is no longer equal to its default value.",
						classInstance.eGet(reference),
						reference.getDefaultValue());
			}
		}
	}
	
	/**
	 * Check if the opposite EReference of "reference" is consistent with its current value for
	 * the given classInstance.
	 * @param classInstance the EObject containing the EReference to check.
	 * @param reference the EReference to check.
	 * @param referenceValue the current value of the EReference.
	 * @note the Changeable property is not verified because EReferences are updated even if
	 * they are not changeable.
	 */
	private void checkEOppositeReference(EObject classInstance, EReference reference, Object referenceValue) {
		EReference oppositeReference = reference.getEOpposite();
		if(oppositeReference != null) {
			if(reference.isChangeable()) {
				if(reference.isMany()) {
					// Iterate through all the referenced EObject and check their opposite
					// reference has been correctly set
					@SuppressWarnings("unchecked") EList<EObject> referencedEObjects = (EList<EObject>)referenceValue;
					for(EObject singleReferencedEObject : referencedEObjects) {
						assert(checkEReferenceContainment(singleReferencedEObject, oppositeReference, classInstance));
					}
				}
				else {
					EObject referencedEObject = (EObject)referenceValue;
					assert(checkEReferenceContainment(referencedEObject, oppositeReference, classInstance));
				}
			}
			else {
				// Check the opposite EReference hasn't been modified
				if(reference.isMany()) {
					@SuppressWarnings("unchecked") EList<EObject> referencedEObjects = (EList<EObject>)referenceValue;
					for(EObject singleReferencedEObject : referencedEObjects) {
						assert(!checkEReferenceContainment(singleReferencedEObject, oppositeReference, classInstance));
					}
				}
				else {
					EObject referencedEObject = (EObject)referenceValue;
					assert(!checkEReferenceContainment(referencedEObject, oppositeReference, classInstance));
				}
			}
		}
	}
	
	/**
	 * Check that instance is contained in the referenced elements of "from" EObject
	 * through "reference" feature. 
	 * @param from the EObject to check from
	 * @param reference the EReference
	 * @param instance the instance to look for
	 * @return true if instance is contained in the referenced elements of "from", false otherwise
	 */
	private boolean checkEReferenceContainment(EObject from, EReference reference, EObject instance) {
		Object referenceContent = from.eGet(reference);
		if(reference.isMany()) {
			@SuppressWarnings("unchecked") EList<EObject> referencedEObjects = (EList<EObject>)referenceContent;
			return(referencedEObjects.contains(instance));
		}
		else {
			EObject referencedEObject = (EObject)referenceContent;
			return(referencedEObject.equals(instance));
		}
	}
	
	/**
	 * Check the given EObject has been correctly deleted of its root resource.
	 * @param classInstance the EObject to check to.
	 */
	private void checkDeletedFromRoot(EObject classInstance) {
		EClass currentClass = classInstance.eClass();
		assert !neo4emfRoot.getContents().contains(classInstance) : "Resource previously registering the EObject \"" +
			currentClass.getName() + "\" still contains the EObject after its deletion (getContents method).";
		assert !neo4emfRoot.getAllInstances(currentClass).contains(classInstance) : "Resource previously registering the EObject \"" +
			currentClass.getName() + "\" still contains the EObject after its deletion (getAllInstances method).";
		TreeIterator<EObject> allContents = neo4emfRoot.getAllContents();
		boolean containsClassInstance = false;
		while(allContents.hasNext()) {
			if(allContents.next().equals(classInstance)) {
				containsClassInstance = true;
			}
		}
		assert !containsClassInstance : "Resource previously registering the EObject \"" + currentClass.getName() + 
			"\" still contains the EObject after its deletion (getAllContents method).";
	}
}
