package fr.inria.atlanmod.neoemf.graph.blueprints.option;

public class BlueprintsOptionsBuilder extends AbstractBlueprintsOptionsBuilder<BlueprintsOptionsBuilder> {

    protected BlueprintsOptionsBuilder() {
        graph(BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
    }

    public static BlueprintsOptionsBuilder newBuilder() {
        return new BlueprintsOptionsBuilder();
    }
}
