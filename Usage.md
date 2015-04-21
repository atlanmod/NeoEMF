# NeoEMF Usage Guide

### Create a new resource
    // NeoEMF proposal
    
	ResourceSet rs = new ResourceSetImpl();
	
	Map<?,?> map = rs.getResourceFactoryRegistry().getProtocolToFactoryMap();
	
	ResourceFactory mapdbResourceFactory = new NeoemfResourceFactory(backend, mapping) // ???
	ResourceFactory neo4jResourceFactory = new NeoemfResourceFactory(backend, mapping) // ???

	map.put("mapdb", mapdbResourceFactory);
	map.put("neo4j", neo4jResourceFactory);
	
	URI uri1 = URI.createURI("mapdb:/MyFirstNeo4emfResource");
	URI uri2 = URI.createURI("neo4j:/MyFirstNeo4emfResource");
	
	Resource resource = rs.createResource(uri);
	
	resource.save(options);
	
	// Blueprints Neo4j sample (work with maven refactoring)
	PersistenceBackendFactoryProvider.getFactories().put("kyanos", new GraphPersistentBackendFactory());
		ResourceSet resSet = new ResourceSetImpl();
    	resSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("kyanos", new PersistentResourceFactoryImpl());
    	Resource r = resSet.createResource(NeoURI.createNeoURI(new File("neoDb/neo")));
    	try {
    		Map<String,String> options = new HashMap<String,String>();
    		options.put("blueprints.neo4j.conf.cache_type", "soft");
    		options.put("blueprints.graph", "com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph");
    		r.save(options);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)r);	
	
	// Map sample (work with maven refactoring)
	PersistenceBackendFactoryProvider.getFactories().put("kyanosmap",new MapPersistenceBackendFactory());
    	ResourceSet resSet = new ResourceSetImpl();
    	resSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("kyanosmap", new PersistentResourceFactoryImpl());
    	Resource r = resSet.createResource(NeoURI.createNeoMapURI(new File("base")));
    	try {
    		r.save(Collections.EMPTY_MAP);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}

### Load an existing resource

To load an existing resource, one must know its location 

Packages should be registered in order to find the factory 

    // NeoEMF proposal
	ResourceSet rs = new ResourceSetImpl();
	Map<?,?> map = rs.getResourceFactoryRegistry().getProtocolToFactoryMap();
	
	ResourceFactory mapdbResourceFactory = new NeoemfResourceFactory(backend, mapping) // ???
	ResourceFactory neo4jResourceFactory = new NeoemfResourceFactory(backend, mapping) // ???
	
	map.put("mapdb", mapdbResourceFactory);
	map.put("neo4j", neo4jResourceFactory);
	
	URI uri1 = URI.createURI("mapdb:/./MyFirstNeo4emfResource");
	URI uri2 = URI.createURI("neo4j:/./MyFirstNeo4emfResource");
	
	Resource resource = rs.createResource(uri);
	
	resource.load(options);
