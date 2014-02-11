package fr.inria.atlanmod.neo4emf.tests.reflection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.change.impl.AddLink;
import fr.inria.atlanmod.neo4emf.change.impl.ChangeLog;
import fr.inria.atlanmod.neo4emf.change.impl.Entry;
import fr.inria.atlanmod.neo4emf.change.impl.NewObject;
import fr.inria.atlanmod.neo4emf.change.impl.SetAttribute;

@RunWith(Parameterized.class)
public class ChangelogTest {

	private EPackage ePackage;
	private EFactory eFactory;
	//private Map mapping;
	private EList<EClassifier> packageClassifiers;
	private INeo4emfResource neo4emfRoot;
	
	private ValueFactory valueFactory;
	
	@Parameterized.Parameters
	public static List<Object[]> data() {
		return InputData.allData("Changelog");
	}
	
	public ChangelogTest(Object currentEPackage, Object currentEFactory, Object currentMapping, Object neoResource) {
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
		ChangeLog.getInstance().clear();
	}

	@After
	public void tearDown() throws Exception {
		ChangeLog.getInstance().clear();
		//neo4emfRoot.shutdown();
	}
	
	@Test
	public void testCreateClasses() {
		Map<EClass, Integer> eObjectBaseNumber = new HashMap<EClass,Integer>();
		Map<EClass, Integer> eObjectCount = new HashMap<EClass,Integer>();
		
		// Generate the base number for each eClass
		for(EClassifier currentClassifier : packageClassifiers) {
			if(currentClassifier instanceof EClass) {
				int instanceNumber = (int)(Math.random()*100);
				eObjectBaseNumber.put((EClass)currentClassifier, instanceNumber);
				// Create the instances for the current class
				for(int i = 0; i < instanceNumber; i++) {
					EObject classInstance = eFactory.create((EClass)currentClassifier);
					neo4emfRoot.getContents().add(classInstance);
				}
			}
		}
		// Iterate in the Changelog to find all the NewObject entries
		Iterator<Entry> it = ChangeLog.getInstance().iterator();
		while(it.hasNext()) {
			Entry currentEntry = it.next();
			if(currentEntry instanceof NewObject)  {
				NewObject newCommand = (NewObject)currentEntry;
				EClass newCommandClass = newCommand.geteObject().eClass();
				if(eObjectCount.containsKey(newCommandClass)) {
					int count = eObjectCount.get(newCommandClass);
					eObjectCount.put(newCommandClass, count + 1);
				}
				else {
					eObjectCount.put(newCommandClass, 1);
				}
			}
		}
		// Check that NewObject entry count is equal to the number of each
		// created EObjects.
		Iterator<EClass> classes = eObjectCount.keySet().iterator();
		while(classes.hasNext()) {
			EClass currentClass = classes.next();
			assert eObjectBaseNumber.get(currentClass) != null : "A NewCommand entry refers to a non-instanciated class \"" + currentClass.getName() + "\".";
			assert eObjectBaseNumber.get(currentClass).equals(eObjectCount.get(currentClass)) : "Invalid NewCommand number : found " + eObjectCount.get(currentClass) + ", expected " + eObjectBaseNumber.get(currentClass);
		}
	}
	
	
	@Test
	public void testSetAttributes() {
		List<EAttribute> setAttributeBase = new ArrayList<EAttribute>();
		List<EAttribute> setAttribute = new ArrayList<EAttribute>();
		
		// Generate the base number for each eClass
		for(EClassifier currentClassifier : packageClassifiers) {
			if(currentClassifier instanceof EClass) {
				EClass currentClass = (EClass)currentClassifier;
				//int instanceNumber = (int)(Math.random()*100);
				int instanceNumber = 1;
				//setBaseNumber.put(currentClass, 0);
				// Create the instances for the current class
				for(int i = 0; i < instanceNumber; i++) {
					EObject classInstance = eFactory.create(currentClass);
					neo4emfRoot.getContents().add(classInstance);
					// Set the attributes for the current class
					for(EObject it : currentClass.eContents()) {
						if(it instanceof EAttribute) {
							EAttribute attribute = (EAttribute)it;
							Object attributeValue = valueFactory.generateValue(attribute);
							if(attribute.isChangeable()) {
								classInstance.eSet(attribute,attributeValue);
								setAttributeBase.add(attribute);
							}
						}
					}
				}
			}
		}
		
		Iterator<Entry> it = ChangeLog.getInstance().iterator();
		while(it.hasNext()) {
			Entry currentEntry = it.next();
			if(currentEntry instanceof SetAttribute) {
				SetAttribute setAttributeEntry = (SetAttribute)currentEntry;
				setAttribute.add(setAttributeEntry.geteAttribute());
			}
		}
		
		// Check that all the SetAttribute entries are consistent with the generated
		// EAttributes
		for(EAttribute effectiveSetAttribute : setAttribute) {
			assert setAttributeBase.contains(effectiveSetAttribute) : "A SetAttribute entry refers to a non set feature\"" + effectiveSetAttribute.getName() + "\" (class \"" + effectiveSetAttribute.getEContainingClass().getName() +  "\").";
		}
		// Check that all the created EAttributes has generated a SetAttributeEnctry
		for(EAttribute attribute : setAttributeBase) {
			assert setAttribute.contains(attribute) : "Created EAttribute \""+attribute.getName()+ "\" (class \"" + attribute.getEContainingClass().getName() +  "\" )" +" hasn't generated a SetAttribute entry";
		}
		
	}
	
	@Test
	public void testSetAttributeSingleValueOnMany() {
		List<EAttribute> setAttributeBase = new ArrayList<EAttribute>();
		List<EAttribute> setAttribute = new ArrayList<EAttribute>();
		
		// Generate the base number for each eClass
		for(EClassifier currentClassifier : packageClassifiers) {
			if(currentClassifier instanceof EClass) {
				EClass currentClass = (EClass)currentClassifier;
				//int instanceNumber = (int)(Math.random()*100);
				int instanceNumber = 1;
				//setBaseNumber.put(currentClass, 0);
				// Create the instances for the current class
				for(int i = 0; i < instanceNumber; i++) {
					EObject classInstance = eFactory.create(currentClass);
					neo4emfRoot.getContents().add(classInstance);
					// Set the attributes for the current class
					for(EObject it : currentClass.eContents()) {
						if(it instanceof EAttribute) {
							EAttribute attribute = (EAttribute)it;
							if(attribute.isChangeable()) {
								if(attribute.isMany()) {
									Object value = valueFactory.getValue(attribute.getEType());
									EList<Object> attributeValue = new BasicEList<Object>();
									attributeValue.add(value);
									classInstance.eSet(attribute, attributeValue);
									setAttributeBase.add(attribute);
								}
							}
						}
					}
				}
			}
		}
		
		Iterator<Entry> it = ChangeLog.getInstance().iterator();
		while(it.hasNext()) {
			Entry currentEntry = it.next();
			if(currentEntry instanceof SetAttribute) {
				SetAttribute setAttributeEntry = (SetAttribute)currentEntry;
				setAttribute.add(setAttributeEntry.geteAttribute());
			}
		}
		
		// Check that all the SetAttribute entries are consistent with the generated
		// EAttributes
		for(EAttribute effectiveSetAttribute : setAttribute) {
			assert setAttributeBase.contains(effectiveSetAttribute) : "A SetAttribute entry refers to a non set feature\"" + effectiveSetAttribute.getName() + "\" (class \"" + effectiveSetAttribute.getEContainingClass().getName() +  "\").";
		}
		// Check that all the created EAttributes has generated a SetAttributeEnctry
		for(EAttribute attribute : setAttributeBase) {
			assert setAttribute.contains(attribute) : "Created EAttribute \""+attribute.getName()+ "\" (class \"" + attribute.getEContainingClass().getName() +  "\" )" +" hasn't generated a SetAttribute entry";
		}
	}
	
	@Test
	public void testSetAttributeUniqueWithDouble() {
		List<EAttribute> setAttributeBase = new ArrayList<EAttribute>();
		List<EAttribute> setAttribute = new ArrayList<EAttribute>();
		
		// Generate the base number for each eClass
		for(EClassifier currentClassifier : packageClassifiers) {
			if(currentClassifier instanceof EClass) {
				EClass currentClass = (EClass)currentClassifier;
				//int instanceNumber = (int)(Math.random()*100);
				int instanceNumber = 1;
				//setBaseNumber.put(currentClass, 0);
				// Create the instances for the current class
				for(int i = 0; i < instanceNumber; i++) {
					EObject classInstance = eFactory.create(currentClass);
					neo4emfRoot.getContents().add(classInstance);
					// Set the attributes for the current class
					for(EObject it : currentClass.eContents()) {
						if(it instanceof EAttribute) {
							EAttribute attribute = (EAttribute)it;
							if(attribute.isChangeable()) {
								if(attribute.isMany() && attribute.isUnique()) {
									Object value = valueFactory.getValue(attribute.getEType());
									EList<Object> attributeValue = new BasicEList<Object>();
									attributeValue.add(value);
									attributeValue.add(value);
									classInstance.eSet(attribute, attributeValue);
									setAttributeBase.add(attribute);
									setAttributeBase.add(attribute);
								}
							}
						}
					}
				}
			}
		}
		
		Iterator<Entry> it = ChangeLog.getInstance().iterator();
		while(it.hasNext()) {
			Entry currentEntry = it.next();
			if(currentEntry instanceof SetAttribute) {
				SetAttribute setAttributeEntry = (SetAttribute)currentEntry;
				setAttribute.add(setAttributeEntry.geteAttribute());
			}
		}
		
		// Check that all the SetAttribute entries are consistent with the generated
		// EAttributes
		for(EAttribute effectiveSetAttribute : setAttribute) {
			assert setAttributeBase.contains(effectiveSetAttribute) : "A SetAttribute entry refers to a non set feature\"" + effectiveSetAttribute.getName() + "\" (class \"" + effectiveSetAttribute.getEContainingClass().getName() +  "\").";
		}
		// Check that all the created EAttributes has generated a SetAttributeEnctry
		for(EAttribute attribute : setAttributeBase) {
			assert setAttribute.contains(attribute) : "Created EAttribute \""+attribute.getName()+ "\" (class \"" + attribute.getEContainingClass().getName() +  "\" )" +" hasn't generated a SetAttribute entry";
		}
		assert setAttribute.size() == setAttributeBase.size() / 2 : "Changelog does not contain the right number of SetAttribute entry : expected " + setAttributeBase.size()/2 + ", found " + setAttribute.size();
	}
	
	@Test
	public void testSetReference() {
		List<EReference> setReferenceBase = new ArrayList<EReference>();
		List<EReference> setReference = new ArrayList<EReference>();
		
		// Generate the base number for each eClass
		for(EClassifier currentClassifier : packageClassifiers) {
			if(currentClassifier instanceof EClass) {
				EClass currentClass = (EClass)currentClassifier;
				//int instanceNumber = (int)(Math.random()*100);
				int instanceNumber = 1;
				//setBaseNumber.put(currentClass, 0);
				// Create the instances for the current class
				for(int i = 0; i < instanceNumber; i++) {
					EObject classInstance = eFactory.create(currentClass);
					neo4emfRoot.getContents().add(classInstance);
					// Set the attributes for the current class
					for(EObject it : currentClass.eContents()) {
						if(it instanceof EReference) {
							EReference reference= (EReference)it;
							Object referenceValue = valueFactory.generateValue(reference);
							if(reference.isChangeable()) {
								classInstance.eSet(reference, referenceValue);
								setReferenceBase.add(reference);
								// Add the EOpposite EReference if it exists (EMF ensure
								// bidirectional update of cross EReferences)
								if(reference.getEOpposite() != null) {
									setReferenceBase.add(reference.getEOpposite());
								}
							}
						}
					}
				}
			}
		}
		
		Iterator<Entry> it = ChangeLog.getInstance().iterator();
		while(it.hasNext()) {
			Entry currentEntry = it.next();
			if(currentEntry instanceof AddLink) {
				AddLink addReferenceEntry = (AddLink)currentEntry;
				setReference.add(addReferenceEntry.geteReference());
			}
		}
		// Check that all the SetAttribute entries are consistent with the generated
		// EAttributes
		for(EReference effectiveSetReference : setReference) {
			assert setReferenceBase.contains(effectiveSetReference) : "An AddLink entry refers to a non set feature\"" + effectiveSetReference.getName() + "\" (class \"" + effectiveSetReference.getEContainingClass().getName() +  "\").";
		}
		// Check that all the created EAttributes has generated a SetAttributeEnctry
		for(EReference reference : setReferenceBase) {
			assert setReference.contains(reference) : "Created EReference \""+reference.getName()+ "\" (class \"" + reference.getEContainingClass().getName() +  "\" )" +" hasn't generated a AddLink entry";
		}
	}
	
	@Test
	public void testSetReferenceSingleValueOnMany() {
		List<EReference> setReferenceBase = new ArrayList<EReference>();
		List<EReference> setReference = new ArrayList<EReference>();
		
		// Generate the base number for each eClass
		for(EClassifier currentClassifier : packageClassifiers) {
			if(currentClassifier instanceof EClass) {
				EClass currentClass = (EClass)currentClassifier;
				//int instanceNumber = (int)(Math.random()*100);
				int instanceNumber = 1;
				//setBaseNumber.put(currentClass, 0);
				// Create the instances for the current class
				for(int i = 0; i < instanceNumber; i++) {
					EObject classInstance = eFactory.create(currentClass);
					neo4emfRoot.getContents().add(classInstance);
					// Set the attributes for the current class
					for(EObject it : currentClass.eContents()) {
						if(it instanceof EReference) {
							EReference reference= (EReference)it;
							if(reference.isChangeable()) {
								if(reference.isMany()) {
									Object value = valueFactory.getValue(reference.getEType());
									EList<Object> referenceValue = new BasicEList<Object>();
									referenceValue.add(value);
									classInstance.eSet(reference, referenceValue);
									setReferenceBase.add(reference);
									// Add the EOpposite EReference if it exists (EMF ensure
									// bidirectional update of cross EReferences)
									if(reference.getEOpposite() != null) {
										setReferenceBase.add(reference.getEOpposite());
									}
								}
							}
						}
					}
				}
			}
		}
		
		Iterator<Entry> it = ChangeLog.getInstance().iterator();
		while(it.hasNext()) {
			Entry currentEntry = it.next();
			if(currentEntry instanceof AddLink) {
				AddLink addReferenceEntry = (AddLink)currentEntry;
				setReference.add(addReferenceEntry.geteReference());
			}
		}
		System.out.println(setReferenceBase.size());
		System.out.println(setReference.size());
		// Check that all the SetAttribute entries are consistent with the generated
		// EAttributes
		for(EReference effectiveSetReference : setReference) {
			assert setReferenceBase.contains(effectiveSetReference) : "An AddLink entry refers to a non set feature\"" + effectiveSetReference.getName() + "\" (class \"" + effectiveSetReference.getEContainingClass().getName() +  "\").";
		}
		// Check that all the created EAttributes has generated a SetAttributeEnctry
		for(EReference reference : setReferenceBase) {
			assert setReference.contains(reference) : "Created EReference \""+reference.getName()+ "\" (class \"" + reference.getEContainingClass().getName() +  "\" )" +" hasn't generated a AddLink entry";
		}
	}
	
	@Test
	public void testSetReferenceUniqueWithDouble() {
		List<EReference> setReferenceBase = new ArrayList<EReference>();
		List<EReference> setReference = new ArrayList<EReference>();
		
		// Generate the base number for each eClass
		for(EClassifier currentClassifier : packageClassifiers) {
			if(currentClassifier instanceof EClass) {
				EClass currentClass = (EClass)currentClassifier;
				//int instanceNumber = (int)(Math.random()*100);
				int instanceNumber = 1;
				//setBaseNumber.put(currentClass, 0);
				// Create the instances for the current class
				for(int i = 0; i < instanceNumber; i++) {
					EObject classInstance = eFactory.create(currentClass);
					neo4emfRoot.getContents().add(classInstance);
					// Set the attributes for the current class
					for(EObject it : currentClass.eContents()) {
						if(it instanceof EReference) {
							EReference reference = (EReference)it;
							if(reference.isChangeable()) {
								if(reference.isMany() && reference.isUnique()) {
									Object value = valueFactory.getValue(reference.getEType());
									EList<Object> attributeValue = new BasicEList<Object>();
									attributeValue.add(value);
									attributeValue.add(value);
									classInstance.eSet(reference, attributeValue);
									setReferenceBase.add(reference);
									setReferenceBase.add(reference);
									if(reference.getEOpposite() != null) {
										setReferenceBase.add(reference.getEOpposite());
										setReferenceBase.add(reference.getEOpposite());
									}
								}
							}
						}
					}
				}
			}
		}
		
		Iterator<Entry> it = ChangeLog.getInstance().iterator();
		while(it.hasNext()) {
			Entry currentEntry = it.next();
			if(currentEntry instanceof AddLink) {
				AddLink setReferenceEntry = (AddLink)currentEntry;
				setReference.add(setReferenceEntry.geteReference());
			}
		}
		
		// Check that all the AddLink entries are consistent with the generated
		// EAttributes
		for(EReference effectiveSetReference : setReference) {
			assert setReferenceBase.contains(effectiveSetReference) : "A AddLink entry refers to a non set feature\"" + effectiveSetReference.getName() + "\" (class \"" + effectiveSetReference.getEContainingClass().getName() +  "\").";
		}
		// Check that all the created EAttributes has generated a SetAttributeEnctry
		for(EReference reference : setReferenceBase) {
			assert setReference.contains(reference) : "Created EAttribute \""+reference.getName()+ "\" (class \"" + reference.getEContainingClass().getName() +  "\" )" +" hasn't generated a SetAttribute entry";
		}
		assert setReference.size() == setReferenceBase.size() / 2 : "Changelog does not contain the right number of SetAttribute entry : expected " + setReferenceBase.size()/2 + ", found " + setReference.size();
	}
	/*
	@Test
	public void testSetReferenceGraph() {
		Map<EClass, Integer> eObjectCount = new HashMap<EClass, Integer>();
		
		Iterator<Entry> it = ChangeLog.getInstance().iterator();
		while(it.hasNext()) {
			Entry currentEntry = it.next();
			if(currentEntry instanceof AddLink) {
				AddLink addLink = (AddLink)currentEntry;
				if(eObjectCount.containsKey(addLink.geteObject().eClass())) {
					eObjectCount.put(addLink.geteObject().eClass(), eObjectCount.get(addLink.geteObject().eClass()) + 1);
				}
				else {
					eObjectCount.put(addLink.geteObject().eClass(), 1);
				}
			}
		}
		System.out.println(eObjectCount.get(graphs[0].eClass()));
		System.out.println(eObjectCount.get(nodes[0].eClass()));
		System.out.println(eObjectCount.get(edges[0].eClass()));
		assert(eObjectCount.get(graphs[0].eClass()) == graphCount*(nodeCount + edgeCount));
		assert(eObjectCount.get(nodes[0].eClass()) == nodeCount*graphCount + (graphCount*edgeCount)*2);
		assert(eObjectCount.get(edges[0].eClass()) == edgeCount*graphCount + (graphCount*edgeCount)*2);
	}
	
	@Test
	public void testDeleteElementGraph() {
		// pour le moment le tests ne peut pas passer car le changelog n'est pas informé du
		// delete
		Map<EClass, Integer> eObjectCount = new HashMap<EClass, Integer>();
		
		neo4emfRoot.getContents().add(graphs[0]); // todo : mettre ça dans la factory
		EcoreUtil.delete(nodes[0]);
		
		Iterator<Entry> it = ChangeLog.getInstance().iterator();
		while(it.hasNext()) {
			Entry currentEntry = it.next();
			if(currentEntry instanceof DeleteObject) {
				DeleteObject deleteObject = (DeleteObject)currentEntry;
				System.out.println("pouet");
				if(eObjectCount.containsKey(deleteObject.geteObject().eClass())) {
					eObjectCount.put(deleteObject.geteObject().eClass(), eObjectCount.get(deleteObject.geteObject().eClass()) + 1);
				}
				else {
					eObjectCount.put(deleteObject.geteObject().eClass(), 1);
				}
			}
		}
		assert(eObjectCount.get(graphs[0].eClass()) != null);
		assert(eObjectCount.get(graphs[0].eClass()) == 1);
	}
	*/
}