NeoEMF Benchmarks
=================

## Building
Firstly, you need to build the module and its dependencies by running the following command (naturally, NeoEMF must be installed):

    mvn clean package
    
## Running

### Running with java
Benchmarks are built as a JAR file. All you have to do is run the built JAR.
We use JMH as wrapper, so all JMH arguments can be used for a more custom execution.

    java -jar core/target/exec/benchmarks.jar <args>
    
Possible values for `<args>` can be found by using the `-help` argument, or [here](https://raw.githubusercontent.com/atlanmod/NeoEMF/master/benchmarks/core/jmh-usage.txt).

#### Parameters
By default, all resources are executed on all backends. But you can choose to execute one (or more) specific resource on one (or more) specific backend by using the `-p` argument with the following parameters:
- `b=<backend>,...`     where `<backend>`  is `[xmi|cdo|neo-map|neo-graph|neo-graph-neo4j]`
- `r=<resource>,...`    where `<resource>` is :
  - the filename, if it's included in `/resources.zip`.
  By default, this archive contains:
    - `fr.inria.atlanmod.kyanos.tests.xmi` (130 KB)
    - `fr.inria.atlanmod.neo4emf.neo4jresolver.xmi` (2 MB)
    - `org.eclipse.gmt.modisco.java.kyanos.xmi` (20 MB)
    - `org.eclipse.jdt.core.xmi` (420 MB)
    - `org.eclipse.jdt.source.all.xmi` (983 MB)

  - the absolute file path, otherwise.
  For now, only `*.xmi` and `*.zxmi` that use the `http://www.eclipse.org/MoDisco/Java/0.2.incubation/java` package are allowed.

__Example:__ To run the query `traverse` on XMI and Neo4j backends, with the resource "fr.inria.atlanmod.kyanos.tests.xmi", you need to execute:

    java -jar ... traverse -p b=xmi,neo-graph-neo4j -p r=fr.inria.atlanmod.kyanos.tests.xmi

#### Initialization _(optional)_
Backends have to be created before executing requests on it.

The creation is automatically done at the beginning of each benchmark, if the backend does not exist already in the workspace.
But this process can take a long time, according to your system configuration and the size of the resource you want to use.

To initialize them, you can execute:

    java -jar ... init <args>
    
Backends are created in the workspace directory, which is by default `<tmp-dir>/neoemf-benchmarks`.

__NOTE:__ The creation time is not taken in account in benchmark results, that's why this step is optional.

### Running with Maven
You can also run a pre-configured execution from Maven:
    
    mvn -f core exec:exec
    
Which is the same than execute:

    java -jar ... -rf json -rff <tmp-dir>/neoemf-benchmarks-<timestamp>.json
