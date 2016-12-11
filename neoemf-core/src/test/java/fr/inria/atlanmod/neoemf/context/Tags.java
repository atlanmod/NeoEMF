package fr.inria.atlanmod.neoemf.context;

public final class Tags {

    private Tags() {
    }

    /**
     * Category marker for tests using a persistent store.
     */
    public interface PersistentTests {}

    /**
     * Category marker for tests using a transient store.
     */
    public interface TransientTests {}
}
