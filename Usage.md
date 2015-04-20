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