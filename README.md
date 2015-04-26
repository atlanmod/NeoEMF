NeoEMF
======

## What is NeoEMF?

NeoEMF is an [AtlanMod](http://www.emn.fr/z-info/atlanmod/index.php/Main_Page) research team project that aims at handling large EMF models in an efficient and scalable way.
It is based on our work on Neo4EMF, a scalable and graph based backend for persisting EMF models.
NeoEMF is a multi-backend framework developped to allow an easy integration of custom backends depending on user needs.

## What are the supported backends?

NeoEMF is provided with a complete support of MapDB and Blueprints databases.
Some Blueprints connectors are also available to ease the integration of graph databases (Neo4j for the moment).

## What are the features?

NeoEMF comes with some features depending on the backend used:
 - __Lazy-loading__ mechanism: a model is loaded part by part while needed (**All the backends**)
 - __Caching__: NeoEMF relies on database caches to retrieve EObjects. But in some situation where time performance is important this is not enough and it is possible to use application-level caches to speed up 
information loading (**All the backends**)
 - __Auto-commit__: For the backend having restrictions on transaction sizes it is possible to use the Auto-commit feature to split large transaction into several small ones (**Blueprints backend**)
 - __Dirty saving__: handle large models that haven't been persisted to avoid memory overhead (**Blueprints backend**)

## Why should I use NeoEMF?

NeoEMF is fully EMF compliant, that means there is no modification to do to use it with existing EMF based applications.
Additionally NeoEMF can be plugged with several persistence solutions (for the moment Blueprints and MapDB) and is configurable to fit the best your needs (custom caches, in-memory resources,
auto-commit ...).

In addition, NeoEMF provides a query API to perform optimized OCL queries over models and reify the results as navigable models.

## Installation

## Update Site
The simplest way to install NeoEMF is to use the update site that is available [here](http://atlanmod.github.io/NeoEMF/).
Install the **Base** compenent, which provides NeoEMF core classes and utils.
Select the backend specific implementation(s) you want to plug in NeoEMF (currently Blueprints and MapDB are available).

### Specific Backend Setup
Backend specific implementations may need configuration files or require other plugins to work properly.
These requirements are available in the root of the related subprojects.

## Local Build
You can build locally NeoEMF by following the instructions in th Build section.
It is then possible to install NeoEMF from the local built update site, or by importing the generated plugins.

## Build
To build NeoEMF first download the latest release [here](https://github.com/atlanmod/NeoEMF/releases) and unzip it. Then go to the root of the extracted directory and run `mvn clean install`. This will build NeoEMF core components and database specific implementations. You can also run the tests by using "mvn test (-pl <core|map|graph>)".

To build NeoEMF Eclipse plugins you need to perform the following steps:
 - Move to eclipse folder `cd plugins/eclipse`
 - Run maven `mvn clean install`
 - NeoEMF plugins and update site are built and stored in your local m2 repository (<m2>/fr/inria/atlanmod/neoemf)

### Known Issues:
 - The Eclipse plugins can not be built if maven can not access internet (it is needed to fetch Luna p2 repositories) 
 - Sometimes the plugin build crashes and/or freezes during p2 index fetching from Luna repositories. It is generally sufficient to cancel the build (`ctrl-c`) and to relaunch it.

## Issues
If you experience issues installing or using NeoEMF, you can [submit an issue on github](https://github.com/atlanmod/NeoEMF/issues) or contact us at contact@neoemf.com

## Develop Your Backend Implementation
If you have developped a specific backend implementation that is not covered by the actual release of NeoEMF, you can submit it as a pull request.
(More informations to come about custom backend implementations).
 
