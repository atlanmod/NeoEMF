NeoEMF
======

##What is NeoEMF?

NeoEMF is an [AtlanMod](http://www.emn.fr/z-info/atlanmod/index.php/Main_Page) research team project that aims at handling large EMF models in an efficient and scalable way.
It is based on our work on Neo4EMF, a scalable and graph based backend for persisting EMF models.
NeoEMF is a multi-backend framework developped to allow an easy integration of custom backends depending on user needs.

##What are the supported backends?

NeoEMF is provided with a complete support of MapDB and Neo4j databases. Furthermore, Neo4j implementation is based on 
the blueprints API (https://github.com/tinkerpop/blueprints/wiki). This API is an abstraction layer for a lot of graph databases and it makes it easy to switch from one to another.

##What are the features?

NeoEMF comes with some features depending on the backend used:
 - __Lazy-loading__ mechanism: a model is loaded part by part while needed (all the backends)
 - __Caching__: NeoEMF relies on database caches to retrieve EObjects. But in some situation this is not enough (eg serialize a lot of database objects to EObjects) and a dedicated EObject cache is provided (Map implementation)
 - __Auto-commit__: For the backend having restrictions on transaction sizes it is possible to use the Auto-commit feature to split large transaction into several small ones. (Neo4j)
 - __Dirty saving__: handle large models that haven't been persisted to avoid memory overhead (Neo4j)

##Why should I use NeoEMF?

NeoEMF is fully EMF compliant, that means there is no modification to do to use it with existing EMF based applications.
Additionally NeoEMF is configurable to fit the best your needs (time performance, memory consumption).
