### Supported tags

*   `v1.0.2`, `latest`: the latest release
*   `dev`: the current development snapshot (could be unstable)


## NeoEMF : Benchmarks

### JMH parameters

Benchmarks are built with [JMH][jmh], an highly customizable benchmark harness for Java.
All JMH options are available, and can be used to customize your execution.


### NeoEMF parameters

In addition to JMH options, some parameters allow you to customize the NeoEMF behavior.
These parameters are available with the `-p` argument.

*   `a=<adapter>,...` where `<adapter>` is any of:
    -   `tinker`: use a Tingergraph database
    -   `neo4j`: use a Neo4j database
    -   `mapdb-<mapping>`: use a MapDB database
    -   `berkeleydb-<mapping>`: use a BerkeleyDB database
    -   `xmi`: use stantard EMF, in an XMI file
    -   `cdo`: use CDO, in a H2 database

    Some adapters provide several `<mapping>`s, used to manage multi-valued features differently:
    -   `i`: stored separately, identified with their position (`feature:1=value1, feature:2=value2,...`)
    -   `a`: grouped and stored in arrays (`feature=[1=value1, 2=value2,...]`)
    -   `l`: similar to `a`, but with lists (`feature=List[1=value1, 2=value2,...]`)

  __NOTE:__ `xmi` and `cdo` are not part of NeoEMF, and are used to compare existing solutions.

*   `r=<resource>,...` where `<resource>` is any of:
    -   a file name, or acronym, of an embedded resource (included in `resources.zip`):
        -   `set1` = `fr.inria.atlanmod.kyanos.tests.xmi` (130 KB)
        -   `set2` = `fr.inria.atlanmod.neo4emf.neo4jresolver.xmi` (2 MB)
        -   `set3` = `org.eclipse.gmt.modisco.java.kyanos.xmi` (20 MB)
        -   `set4` = `org.eclipse.jdt.core.xmi` (420 MB)
        -   `set5` = `org.eclipse.jdt.source.all.xmi` (983 MB)
    -   an absolute file path.

  __WARN:__ Only `*.xmi` and `*.zxmi` files that use the `http://www.eclipse.org/MoDisco/Java/0.2.incubation/java` package are allowed

*   `o=<options>,...` where `<options>` is a combination of:
    -   `A` for auto-saving; __*highly recommended*__
    -   `M` for caching meta-classes
    -   `C` for caching containers
    -   `F` for caching feature values
    -   `S` for caching sizes of multi-valued features
    -   `L` for logging each database accesses

*   `direct=[true|false]`:
    -   `true`: the direct import will be used to create datastores
    -   `false`: the standard EMF way will be used

__NOTE:__ All parameters can be multi-valued by separating them with `,` (without any space). They will be executed in different iterations.


### Run with Docker

```bash
docker run \
  -v <local_directory>:/root/ws \
  atlanmod/neoemf \
  <args>
```

Where:
*   `<local_directory>` is the directory where databases and resources are extracted and stored.
    The usage of this volume is __*highly recommended*__: each Docker instance starts with an empty workspace, and each resource and database must be build before executing benchmarks.
    This step can take a long time, depending on your system configuration and the size of the resource you want to use.

*   `<args>` corresponds to JMH options (with NeoEMF options).
    Possible values are displayed with the `-help` argument.

    __Default arguments:__ Without specific arguments, all queries are executed with:
    -   `a=xmi,cdo,neo4j,berkeleydb-i,mapdb-i`
    -   `r=set1,set2,set3`
    -   `o=AMC`
    -   `direct=true`
    -   All default JMH options

__Example:__ To run the query `grabats` on XMI and Neo4j, with the resources "set1" and "set3", with feature caching, auto-saving and logging, you need to execute:

```bash
docker run \
  -v <local_directory>:/root/ws \
  atlanmod/neoemf \
  -p a=xmi,neo4j \
  -p r=set1,set3 \
  -p o=FAL \
  grabats
```


#### Initialization _(optional)_

Backends have to be created before executing requests on them.

This step is automatically done at the beginning of each benchmark, if the backend does not already exists in the workspace.
But this process can take a long time, depending on your system configuration and the size of the resource you want to use.

To initialize them, you can firstly execute:

```bash
docker run \
  -v <local_directory>:/root/ws \
  -p a=<adapter>,... \
  -p r=<resource>,... \
  -p o=A \
  init
```

__NOTE:__ Ignore this step if you're not using a local directory: the created resources and databases are not shared between different executions.

__NOTE2:__ The creation time is not taken in account in benchmark results, that's why this step is optional.


### Run without Docker

If you don't want to use Docker, you need to build the module and its dependencies by running the following command (naturally, NeoEMF must be installed):

```bash
mvn clean package -f benchmarks
```

The configuration is the same than with Docker, you just need to replace:

```bash
docker run ... atlanmod/neoemf <args>
```

by

```bash
java -jar benchmarks/core/target/exec/benchmarks.jar <args>
```

__NOTE:__ In these examples, all calls are made from the root directory.


[jmh]: http://openjdk.java.net/projects/code-tools/jmh/
