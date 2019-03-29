NeoEMF Demo
===========

This folder contains examples you can run to try NeoEMF basic and advanced features. This examples are designed to be executable in an Eclipse
environment. Maven-based examples will be added in the future.

## Installation

NeoEMF demo runs on Eclipse Oxygen or newer, and you need the following plugins in your installation to resolve the dependencies (these are installable from the software repository of your Eclipse release):
 - __EMF plugins__: v2.6.0 or later
 - __NeoEMF plugins__ : v2.0.1
 - __EMF Compare__: v3.1 or later
 - __Guava Library__ : v11 or later (required by EMF Compare)
 - __Eclipse OCL plugins__: v3.3.1 or later

Note that all these dependencies can be found in `fr.inria.atlanmod.neoemf.demo/META-INF/MANIFEST.MF`.

Once this initial step is done, import the projects `fr.inria.atlanmod.neoemf.demo` and `org.eclipse.gmt.modisco.java` into your workspace. You can check that everything is fine by looking at the *dependencies* in the MANIFEST file of the demo.

## Run

The project `fr.inria.atlanmod.neoemf.demo` contains a set of packages presenting different features of NeoEMF. Each package contains a set of examples using this feature on top of each backend. Navigate in the packages and run the snippet you are interested in!

## Any Issue?

This demonstration is frequently updated, and may contain mistakes. If you experience any issue please contact us at neoemf@googlegroups.com, or [submit an issue](https://github.com/atlanmod/NeoEMF/issues).
