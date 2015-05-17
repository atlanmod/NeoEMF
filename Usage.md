# NeoEMF Usage Guide

## Initialize NeoEMF

### Add a Persistence Backend Factory

In order to use NeoEMF, you first need to register a persistence backend factory (concrete subclass of [AbstractPersistenceBackendFactory](https://github.com/atlanmod/NeoEMF/blob/master/core/src/main/java/fr/inria/atlanmod/neoemf/datastore/AbstractPersistenceBackendFactory.java)).
This factory is responsible of the creation of the persistence backend (concrete subclass of [PersistenceBackend](https://github.com/atlanmod/NeoEMF/blob/master/core/src/main/java/fr/inria/atlanmod/neoemf/datastore/PersistenceBackend.java)) that is in charge of
the model storage.

Persistence backend factories bundled with NeoEMF are
 - Neo4j under the Blueprints API (dedicated page [here](https://github.com/atlanmod/NeoEMF/tree/master/graph/blueprints-neo4j))
 - MapDB (dedicated page [here](https://github.com/atlanmod/NeoEMF/tree/master/map))

#### Neo4j under Blueprints API

    PersistenceBackendFactoryRegistry.getFactories().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, 
        new BlueprintsPersistenceBackendFactory());

#### MapDB

    PersistenceBackendFactoryRegistry.getFactories().put(NeoMapURI.NEO_MAP_SCHEME, 
        new MapPersistenceBackendFactory());

### Register the Persistent Resource Factory

As regular EMF initialization, you need to register the [PersistentResourceFactory](https://github.com/atlanmod/NeoEMF/blob/master/core/src/main/java/fr/inria/atlanmod/neoemf/resources/PersistentResourceFactory.java) implementation in the resource set
specifying the URI protocol. Each backend implementation provide a subclass of [NeoURI](https://github.com/atlanmod/NeoEMF/blob/master/core/src/main/java/fr/inria/atlanmod/neoemf/util/NeoURI.java) to ease protocol definition.
Note that the associated [PersistentResourceFactoryImpl](https://github.com/atlanmod/NeoEMF/blob/master/core/src/main/java/fr/inria/atlanmod/neoemf/resources/impl/PersistentResourceFactoryImpl.java) and the created [PersistentResource](https://github.com/atlanmod/NeoEMF/blob/master/core/src/main/java/fr/inria/atlanmod/neoemf/resources/impl/PersistentResourceImpl.java) do not depend on the selected backend.

#### Neo4j under Blueprints API

    ResourceSet resSet = new ResourceSetImpl();
    resSet.getResourceFactoryRegistry().getProtocolToFactoryMap().
        put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, new PersistentResourceFactoryImpl());
    Resource resource = resSet.createResource(
        NeoBlueprintsURI.createNeoGraphURI(new File("path_to_neodb")));
    
#### MapDB

    ResourceSet resSet = new ResourceSetImpl();
    resSet.getResourceFactoryRegistry().getProtocolToFactoryMap().
        put(NeoMapURI.NEO_MAP_SCHEME, new PersistentResourceFactoryImpl());
    Resource resource = resSet.createResource(
        NeoMapURI.createNeoMapURI(new File("path_to_mapdb")));


Once this two initialization steps has been performed, the resulting resource can be used as a regular EMF [Resource](http://download.eclipse.org/modeling/emf/emf/javadoc/2.4.3/org/eclipse/emf/ecore/resource/Resource.html).

## Save a Resource
	
    /* Initialization depending on the backend */
    try {
        resource.save(Collections.EMPTY_MAP);
    } catch(IOException e) {
        e.printStackTrace();
    }

## Load an existing Resource
	
    /* Initialization depending on the backend */
	Resource resource = resSet.createResource(createURI);
	try {
        resource.load(Collections.EMPTY_MAP);
    } catch(IOExceptio e) {
        e.printStackTrace();
    }

## Neo4j Blueprints Complete Example: modify an existing Resource
	
    PersistenceBackendFactoryRegistry.getFactories().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, 
        new BlueprintsPersistenceBackendFactory());
	ResourceSet resSet = new ResourceSetImpl();
    resSet.getResourceFactoryRegistry().getProtocolToFactoryMap().
        put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, new PersistentResourceFactoryImpl());
    Resource resource = resSet.createResource(NeoBlueprintsURI.createNeoGraphURI(new File("path_to_neodb")));
	
    // load the resource
	try {
		resource.load(Collections.EMPTY_MAP);
	} catch (IOException e) {
		e.printStackTrace();
	}
	// perform modifications
	MyClass myClass = (MyClass)r.getContents().get(0);
	myClass.setName("NewName");
    
	// save with specific options
	try {
		resource.save(Collections.EMPTY_MAP);
	} catch (IOException e) {
		e.printStackTrace();
	}
	// unload the resource and shutdown the database engine
	resource.unload();

## MapDB Complete Example: modify an existing Resource

    PersistenceBackendFactoryRegistry.getFactories().put(NeoMapURI.NEO_MAP_SCHEME, 
        new MapPersistenceBackendFactory());
    ResourceSet resSet = new ResourceSetImpl();
    resSet.getResourceFactoryRegistry().getProtocolToFactoryMap().
        put(NeoMapURI.NEO_MAP_SCHEME, new PersistentResourceFactoryImpl());
    Resource resource = resSet.createResource(NeoMapURI.createNeoMapURI(new File("path_to_mapdb")));
    
    // load the resource
	try {
		resource.load(Collections.EMPTY_MAP);
	} catch (IOException e) {
		e.printStackTrace();
	}
	// perform modifications
	MyClass myClass = (MyClass)r.getContents().get(0);
	myClass.setName("NewName");
    
	// save with specific options
	try {
		resource.save(Collections.EMPTY_MAP);
	} catch (IOException e) {
		e.printStackTrace();
	}
	// unload the resource and shutdown the database engine
	resource.unload();
    
## Backend Specific Options

NeoEMF can handle backend specific options using the standard option mechanism (a Map containing options as key-value pairs provided as a parameter at Resource loading and saving).
See backend pages to have the list of available options.
 - [Neo4j under Blueprints](https://github.com/atlanmod/NeoEMF/tree/master/graph/blueprints-neo4j)
 - [MapDB](https://github.com/atlanmod/NeoEMF/tree/master/map)