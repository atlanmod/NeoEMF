NeoEMF
======
[![Build Status](https://www.travis-ci.org/atlanmod/NeoEMF.svg?branch=master)](https://www.travis-ci.org/atlanmod/NeoEMF)
[![CodeCov](https://codecov.io/gh/atlanmod/NeoEMF/branch/master/graph/badge.svg)](https://codecov.io/gh/atlanmod/NeoEMF)
[![Codacy](https://api.codacy.com/project/badge/Grade/82af9c0b0354424f93e1044cbdc85a9e)](https://www.codacy.com/app/atlanmod/NeoEMF?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=atlanmod/NeoEMF&amp;utm_campaign=Badge_Grade)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/fr.inria.atlanmod.neoemf/neoemf/badge.svg)](https://maven-badges.herokuapp.com/maven-central/fr.inria.atlanmod.neoemf/neoemf)
[![Javadoc](https://img.shields.io/badge/javadoc--blue.svg)](https://atlanmod.github.io/NeoEMF/releases/latest/doc/)
[![Plugin](https://img.shields.io/badge/plugin--blue.svg)](https://atlanmod.github.io/NeoEMF/releases/latest/plugin/)
[![Licence](https://img.shields.io/badge/licence-EPL--2.0-blue.svg)](https://www.eclipse.org/legal/epl-2.0/)
[![Javadocs](https://www.javadoc.io/badge/fr.inria.atlanmod.neoemf/neoemf.svg)](https://www.javadoc.io/doc/fr.inria.atlanmod.neoemf/neoemf)

Check out our [wiki][wiki] for further information on the installation, usages, supported backends, code examples, developer resources, etc.


## What Is NeoEMF?

NeoEMF is an [AtlanMod][atlanmod-home] research team project that aims at handling large EMF models in an efficient and scalable way.

It is based on our work on Neo4EMF, a scalable and graph-based backend for persisting EMF models.

NeoEMF is a multi-backend framework developped to allow an easy integration of custom backends depending on user needs.


## What Are the Features?

NeoEMF comes with some features depending on the backend used:

-   **Lazy-loading** mechanism: A model is loaded part by part while needed
-   **Caching**: NeoEMF relies on database caches to retrieve EObjects
    But in some situation where time performance is important this is not enough and it is possible to use application-level caches to speed up information loading
-   **Auto-commit**: For the backend having restrictions on transaction sizes it is possible to use the auto-commit feature to split large transaction into several small ones
-   **Dirty saving**: Handle large models that haven't been persisted to avoid memory overhead


## Why Should I Use NeoEMF?

NeoEMF is fully EMF compliant, that means there is no modification to do to use it with existing EMF based applications.

Additionally NeoEMF can be plugged with several persistence solutions and is configurable to fit the best your needs (custom caches, in-memory resources, auto-commit,...).

In addition, NeoEMF provides a query API to perform optimized OCL queries over models and reify the results as navigable models.


## Usage

### Latest Release

The most recent release is NeoEMF 2.0.0, released March 21, 2019.
-   Javadoc: [neoemf][release-doc]
-   Eclipse Plugin: [neoemf][release-plugin]

To add a dependency on NeoEMF using Maven, use the following:
```xml
<dependencies>
  <dependency>
    <groupId>fr.inria.atlanmod.neoemf</groupId>
    <artifactId>neoemf-core</artifactId>
    <version>2.0.0</version>
  </dependency>

  <dependency>
    <groupId>fr.inria.atlanmod.neoemf</groupId>
    <artifactId>neoemf-io</artifactId>
    <version>2.0.0</version>
  </dependency>
</dependencies>
```

#### Supported Implementations

All native implementations are located under the `neoemf-data` artifact.
To add the dependency of the specific implementation you want to use, simply use:

```xml
<dependency>
  <groupId>fr.inria.atlanmod.neoemf</groupId>
  <artifactId>neoemf-data-{name}</artifactId>
  <version>2.0.0</version>
</dependency>
```

Where `{name}` is any of:
*   Blueprints:
    -   TinkerGraph : `blueprints-core`
    -   Neo4j: `blueprints-neo4j` *(requires `blueprints-core`)*
*   MapDB : `mapdb`
*   BerkeleyDB: `berkeleydb`
*   HBase: `hbase`
*   MongoDB: `mongodb` _(beta)_

### Snapshots

Snapshots are automatically build from the `master` and are available throught Maven using `2.0.1-SNAPSHOT`.
-   Javadoc: [neoemf][snapshot-doc]
-   Eclipse Plugin: [neoemf][snapshot-plugin]


## Installation

Dedicated pages are available in our wiki for the [installation](https://github.com/atlanmod/NeoEMF/wiki/Installation) and the [build](https://github.com/atlanmod/NeoEMF/wiki/Build).

### Update Site

The simplest way to install NeoEMF is to use the update-site that is available [here][release-plugin].
Install the **NeoEMF Persistence Framework** component, which provides NeoEMF core classes and utils.
Select the backend specific implementation(s) you want to plug in NeoEMF.

### Local Build

You can build locally NeoEMF by following the instructions in the Build section.
It is then possible to install NeoEMF from the local built update-site, or by importing the generated plugins.


## Benchmarks

A full benchmarks description is available at [here][benchmarks].


## Issues

If you experience issues installing or using NeoEMF, you can [submit an issue on github][issues] or contact us at neoemf@googlegroups.com

### Known issues:
-   Only Oxygen plugins are fetched (the compatibility is not ensured for previous Eclipse versions)
-   The Eclipse plugins can not be built if Maven can not access internet (it is needed to fetch Oxygen p2 repositories)
-   Sometimes the plugin build crashes and/or freezes during p2 index fetching from Oxygen repositories. It is generally sufficient to cancel the build (`ctrl-c`) and to relaunch it.


## Credits
Performance problems and memory leaks are diagnosed with [Java Profiler](https://www.ej-technologies.com/products/jprofiler/overview.html)


[atlanmod-home]: http://www.emn.fr/z-info/atlanmod/index.php/Main_Page

[wiki]: https://github.com/atlanmod/NeoEMF/wiki
[issues]: https://github.com/atlanmod/NeoEMF/issues
[benchmarks]: https://github.com/atlanmod/NeoEMF/tree/master/benchmarks

[release-doc]: https://atlanmod.github.io/NeoEMF/releases/latest/doc/
[release-plugin]: https://atlanmod.github.io/NeoEMF/releases/latest/plugin/

[snapshot-doc]: https://atlanmod.github.io/NeoEMF/releases/snapshot/doc/
[snapshot-plugin]: https://atlanmod.github.io/NeoEMF/releases/snapshot/plugin/
