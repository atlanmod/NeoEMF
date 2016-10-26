NeoEMF Benchmarks
=================

## Running the Benchmarks

 1. Install Java JDK (Java JDK 7 at least)
 2. Download Eclipse ([http://download.eclipse.org/eclipse/downloads/](http://download.eclipse.org/eclipse/downloads/)). Luna (4.4) 64 bits is the recommended version. NOTE: High values (up to 8GB) are used by the benchmarks, see notes below.
 3. Start Eclipse and install NeoEMF with all its components
   1. Go to Help -> Install New Software
   2. Add the NeoEMF Update Site ([http://atlanmod.github.io/NeoEMF/](http://atlanmod.github.io/NeoEMF/))
   3. Select all the features available (Base and Backends categories)
   4. Proceed the installation by pressing Next and accept the license agreements
 4. Import the benchmarks plugins by cloning  [https://github.com/atlanmod/NeoEMF.git](https://github.com/atlanmod/NeoEMF.git) **Note:** this operation may take a will because of the size of the models
   - Only the following projects are needed:
     - fr.inria.atlanmod.neoemf.benchmarks
     - org.eclipse.gmt.modisco.java
     - org.eclipse.gmt.modisco.java.cdo
     - org.eclipse.gmt.modisco.java.neoemf
 5. Re-create the build.xml files by re-exporting them. This is necessary to match your computer's configuration:
   1. Go to File -> Export and select General / Ant Buildfiles
   2. Select all the four projects and press finish
 6. (**Optional**) Select a working directory to run the benchmarks in the file fr.inria.atlanmod.neoemf.benchmarks/benchmarks.xml (${java.io.tmpdir}/kyanos-benchmarks is used by default)
   - Change benchmarks.dir property on line 4
 7. Run the benchmarks
   1. Right click on the banchmarks.launch file and select Run as -> Benchmarks. This will build the different projects
   2. Right click on the build.xml gile and select Run as -> Benchmarks. Select the tasks you want to perform and run
   
### Note for 32 bits configurations or computers with limited RAM

This setup is not recommended, since biggest models could not be imported from XMI in CDO and NeoEMF.
In any case, it is necessary to decrease the max heap size on line 16, 18 and 45 in file fr.inria.atlanmod.neoemf.benchmarks/benchmarks.xml:
`<property name="creator.vmargs" value="-Xmx8g" />` should be
`<property name="creator.vmargs" value="-Xmx2g" />`

### Known Issues

The task build-eclipse-compiler may crash with the following error `Class not found: org.eclipse.jdt.core.JDTCompilerAdapter`. In that case
add `org.eclipse.jdt.core_*.jar` and `jdtCompilerAdapter.jar` to the Ant classpath (Run as -> Ant Build -> Classpath).
The needed jars are located in `<Eclipse Home>/plugins/org.apache.ant_*/lib`.

### Acknowledgements

This file is inspired from the [Kyanos wiki page](http://www.emn.fr/z-info/atlanmod/index.php/Kyanos) by [Abel Gòmez](https://github.com/abelgomez).