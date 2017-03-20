NeoEMF
======
[![Build Status](https://travis-ci.org/atlanmod/NeoEMF.svg?branch=master)](https://travis-ci.org/atlanmod/NeoEMF) [![codecov](https://codecov.io/gh/atlanmod/NeoEMF/branch/backend-abstraction/graph/badge.svg)](https://codecov.io/gh/atlanmod/NeoEMF/branch/backend-abstraction) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/82af9c0b0354424f93e1044cbdc85a9e)](https://www.codacy.com/app/atlanmod/NeoEMF?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=atlanmod/NeoEMF&amp;utm_campaign=Badge_Grade) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/fr.inria.atlanmod.neoemf/neoemf/badge.svg)](https://maven-badges.herokuapp.com/maven-central/fr.inria.atlanmod.neoemf/neoemf)

## What is NeoEMF?

NeoEMF is an [AtlanMod](http://www.emn.fr/z-info/atlanmod/index.php/Main_Page) research team project that aims at handling large EMF models in an efficient and scalable way.

It is based on our work on Neo4EMF, a scalable and graph-based backend for persisting EMF models.

NeoEMF is a multi-backend framework developped to allow an easy integration of custom backends depending on user needs.

## What are the supported backends?

NeoEMF is provided with a complete support of MapDB and Blueprints databases.

Some Blueprints connectors are also available to ease the integration of graph databases (Neo4j for the moment).

## What are the features?

NeoEMF comes with some features depending on the backend used:

- __Lazy-loading__ mechanism: A model is loaded part by part while needed (**All backends**)
- __Caching__: NeoEMF relies on database caches to retrieve EObjects. But in some situation where time performance is important this is not enough and it is possible to use application-level caches to speed up 
information loading (**All backends**)
- __Auto-commit__: For the backend having restrictions on transaction sizes it is possible to use the auto-commit feature to split large transaction into several small ones (**Blueprints backend**)
- __Dirty saving__: Handle large models that haven't been persisted to avoid memory overhead (**Blueprints backend**)

## Why should I use NeoEMF?

NeoEMF is fully EMF compliant, that means there is no modification to do to use it with existing EMF based applications.

Additionally NeoEMF can be plugged with several persistence solutions (for the moment Blueprints and MapDB) and is configurable to fit the best your needs (custom caches, in-memory resources, auto-commit,...).

In addition, NeoEMF provides a query API to perform optimized OCL queries over models and reify the results as navigable models.

## Use

### Latest release

The most recent release is NeoEMF 1.0.1, released January 16, 2017.
- Javadoc: [neoemf](https://atlanmod.github.io/NeoEMF/releases/latest/doc/)
- Eclipse Plugin: [neoemf](https://atlanmod.github.io/NeoEMF/releases/latest/plugin/)

To add a dependency on NeoEMF using Maven, use the following:
```xml
<dependency>
  <groupId>fr.inria.atlanmod.neoemf</groupId>
  <artifactId>neoemf</artifactId>
  <version>1.0.1</version>
</dependency>
```

### Snapshots

Snapshots are automatically build from the `master` and are available throught Maven using `1.0.2-SNAPSHOT`.
- Javadoc: [neoemf](https://atlanmod.github.io/NeoEMF/releases/snapshot/doc/)
- Eclipse Plugin: [neoemf](https://atlanmod.github.io/NeoEMF/releases/snapshot/plugin/)

## Installation

### Update site
The simplest way to install NeoEMF is to use the update-site that is available [here](https://atlanmod.github.io/NeoEMF/releases/latest/plugin/).
Install the **Base** component, which provides NeoEMF core classes and utils.
Select the backend specific implementation(s) you want to plug in NeoEMF (currently Blueprints and MapDB are available).

### Specific backend setup
Backend specific implementations may need configuration files or require other plugins to work properly.
These requirements are available in the root of the related subprojects.

### Local build
You can build locally NeoEMF by following the instructions in the Build section.
It is then possible to install NeoEMF from the local built update-site, or by importing the generated plugins.

## Build

### NeoEMF
Download the latest release [here](https://github.com/atlanmod/NeoEMF/releases/latest) and unzip it.
Go to the root of the extracted directory and run the following command:
```bash
mvn clean install
```
This will build NeoEMF core components and database specific implementations.

_(optional)_ You can also run the tests by using :
```bash
mvn test (-pl <neoemf-core|neoemf-data|neoemf-io|...>)
```
### NeoEMF : Eclipse plugin
To build NeoEMF Eclipse plugin, you need to run the following command:
```bash
mvn clean install -f plugins/eclipse
```
NeoEMF plugins and update-site are built and stored in your local m2 repository at (`.m2/fr/inria/atlanmod/neoemf`).

## Opening in your IDE

### Eclipse
In order to import NeoEMF Git repository in Eclipse, you will need the following plugins in your installation (installable from the software repository of your Eclipse release):

 - __EGit__ : v3.4.2 or later
 - __m2e__ : v1.5.1 or later

And the following m2e connectors (installable through `Preferences > Maven > Discovery / m2e Marketplace`):

 - __Maven SCM Handler for EGit (m2e-egit)__ : v0.14 or later
 - __Tycho Project Configurators (Tycho configurator)__ : v0.8.0 or later

Steps:

 - Import the project using `File > Import > Maven > Check out Maven Projects from SCM`
 - Select "git" in the SCM URL field and paste the repository address (https://github.com/atlanmod/NeoEMF.git)
 - Choose your workspace and working sets setting and finish

The projects **project**, **parent**, **core**, **graph**, **graph.blueprints** and **map** will be imported and built.

If you want to import the projects generating Eclipse specific plugins (features, update-site), you have to import them by
hand using `File > Import > Maven > Existing Maven Projects`, and select the projects under the "plugins" folder in your local repository.

## Issues

If you experience issues installing or using NeoEMF, you can [submit an issue on github](https://github.com/atlanmod/NeoEMF/issues) or contact us at neoemf@googlegroups.com

### Known issues:
 - Only Luna plugins are fetched (the compatibility is not ensured for previous Eclipse versions)
 - The Eclipse plugins can not be built if maven can not access internet (it is needed to fetch Luna p2 repositories) 
 - Sometimes the plugin build crashes and/or freezes during p2 index fetching from Luna repositories. It is generally sufficient to cancel the build (`ctrl-c`) and to relaunch it.

## Develop your backend implementation
If you have developped a specific backend implementation that is not covered by the actual release of NeoEMF, you can submit it as a pull request. _(More information to come about custom backend implementations)_

## Credits
Performance problems and memory leaks are diagnosed with [Java Profiler](https://www.ej-technologies.com/products/jprofiler/overview.html)
