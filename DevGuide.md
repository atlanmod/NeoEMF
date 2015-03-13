# NeoEMF Developpers Guide
## Project organization
Except for some platform-specific artifacts, for instance Eclipse plugins, project should follow Apache Maven's conventions.

All software dependencies should be correctly declared in the Project Object Model (pom.xml file).

### Package organization
Keep together classes that implement a same feature. 
Organize packages by features, not by layers.
Read this [article](http://www.javapractices.com/topic/TopicAction.do?Id=205) for more information. 

Avoid packages named `beans`, `exceptions`, `factories`, or `collections`.

## Code conventions
Except for the cases enumerated below, the Java source code should follow Google's Style Guide: <http://google-styleguide.googlecode.com/svn/trunk/javaguide.html>

### Method names
Part of the source code is meant to be specialized by automatically generated code. 
To avoid naming conflicts, classes that belong to the `emf.ecore` hierarchy should avoid standard *Getter* and *Setter* names.

For instance, access methods for an attribute named `foo` should be:

    String foo(); 			// The Getter
    void foo(String str);	// The Setter
    
Access methods from other classes should use standard names.

### Assertions
Use Java assertions instead of comments for specifying method pre-conditions: 

    public void foo(int i) {
    	assert i > 0 && i <= 12 : "Out of range"
    	// method code
    }
Use assertions also for checking invariants in method bodies.

## Software quality
### Unit tests
### System tests

Benchmarks.

## Design tips
### Supertypes should not depend on subtypes.
More precisely, an interface should not reference its implementation classes. For instance, the following code excerpt should be avoided: 

	public interface IFactory {
		IFactory eINSTANCE = FactoryImpl.init();
	}
	
	
<!-- Avoid casts -->
<!--Avoid conditional behavior -->
<!--Design first for testability, then for performance -->