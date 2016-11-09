NeoEMF Benchmarks
=================

## Building
Firstly, you need to build the module and its dependencies by running the following command (naturally, NeoEMF must be installed):

    mvn clean install -f benchmarks
    
## Running

### Running with java
Benchmarks are built as a JAR file. All you have to do is run the built JAR.
We use JMH as wrapper, so all JMH arguments can be used for a more custom execution.

    java -jar benchmarks/core/target/exec/benchmarks.jar <args>
    
Possible values for `<args>` can be found by using the `-help` argument, or [here](https://github.com/yvernageau/NeoEMF/blob/master/benchmarks/core/jmh-usage.txt).

#### Parameters

By default, all resources are executed on all backends. But you can choose to execute one (or more) specific resource on one (or more) specific backend by using the `-p` argument with the following parameters:
- `backend=<xmi|cdo|neo-map|neo-graph|neo-graph-neo4j>,...`
- `resource=<resource name without extension>,...`

Actually, only XMI files included in `src/main/resources/resources.zip` are taked in account.

#### Example

So, to run the query `traverse` on XMI and Neo4j backends with the resource "fr.inria.atlanmod.kyanos.tests.xmi", you need to execute the following command:

    java -jar ... traverse -p backend=xmi,neo-graph-neo4j -p resource=fr.inria.atlanmod.kyanos.tests

### Running with Maven
You can also run a pre-configured execution from Maven:
    
    mvn -f benchmarks/core exec:exec
    
Which is the same than execute:

    java -jar ... -rf json -rff <tmp-dir>/neoemf-benchmarks-<timestamp>.json
