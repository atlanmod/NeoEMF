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
- `a=<adapters>,...`    where `<adapters>`  is any of `[xmi|cdo|neo-mapdb|neo-berkeleydb|neo-tinker|neo-neo4j]`

- `r=<resources>,...`   where `<resources>` is any of:
  - the filename or the acronym of the resource, if they are included in `/resources.zip`.
    - `set1` = `fr.inria.atlanmod.kyanos.tests.xmi` (130 KB)
    - `set2` = `fr.inria.atlanmod.neo4emf.neo4jresolver.xmi` (2 MB)
    - `set3` = `org.eclipse.gmt.modisco.java.kyanos.xmi` (20 MB)
    - `set4` = `org.eclipse.jdt.core.xmi` (420 MB)
    - `set5` = `org.eclipse.jdt.source.all.xmi` (983 MB)

  - the absolute file path, otherwise.
    For now, only `*.xmi` and `*.zxmi` that use the `http://www.eclipse.org/MoDisco/Java/0.2.incubation/java` package are allowed.

- `s=<stores>,...`      where `<stores>` is a string containing the following characters (without specific order):
  - `A{<chuck>}` for auto-saving, with the specified chunk *(default = 100000)*
  - `F`          for caching the features
  - `S`          for caching the sizes
  - `P`          for caching the presence of values
  - `L{<level>}` for logging the database accesses, at the specified level *(default = INFO)*
  
**NOTE:** Values that are wrapped in `{***}` are optional.

**NOTE2:** All parameters can be multi-valued by separating them with `,` (without any space). They will be executed in different iterations.

**Example:** To run the query `traverse` on *XMI* and *Neo4j*, with the resources "set1" and "set3", with feature caching, auto-saving (chuck = 50 000) and logging (default level), you need to execute:

    java -jar ... -p a=xmi,neo-neo4j -p r=set1,set3 -p o=FA{50000}L traverse

#### Initialization _(optional)_
Backends have to be created before executing requests on it.

The creation is automatically done at the beginning of each benchmark, if the backend does not exist already in the workspace.
But this process can take a long time, according to your system configuration and the size of the resource you want to use.

To initialize them, you can execute:

    java -jar ... init <args>
    
Backends are created in the workspace directory, which is by default `<tmp-dir>/neoemf-benchmarks`.

**NOTE:** The creation time is not taken in account in benchmark results, that's why this step is optional.
