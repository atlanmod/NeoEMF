package org.atlanmod.neoemf.io.binary.frame;

import fr.inria.atlanmod.neoemf.core.Id;
import org.atlanmod.commons.Guards;
import org.atlanmod.commons.io.Numbers;
import org.atlanmod.commons.io.UnsignedInt;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.commons.tuple.Pair;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.SerializableToBytes;

import java.util.*;
import java.util.function.Consumer;

import static org.atlanmod.commons.io.Numbers.uint;
import static org.atlanmod.commons.io.Numbers.uvarint;

/**
 * A List with bi-directional look up for {@code Id} and Indexes
 *
 */
public class Index implements Iterable<Pair<Id, Integer>>, SerializableToBytes {

    private ArrayList<Pair<Id, Integer>> ids;
    private Map<Id, Integer> indexes;

    public Index(int initialSize) {
        initialize(initialSize);
    }

    private void initialize(int initialSize) {
        this.ids = new ArrayList<>(initialSize);
        this.indexes = new HashMap<>();
    }

    public Index() {
        this(100);
    }

    public Integer indexFor(Id id) {
        return indexes.get(id);
    }

    public Pair<Id, Integer> idFor(int index) {
        Guards.checkState(Objects.nonNull(ids.get(index)));

        return ids.get(index);
    }

    public Integer store(Id objectID, Integer classIndex) {
        Integer nextIndex = ids.size();
        ids.add(Pair.of(objectID, classIndex));
        indexes.put(objectID, nextIndex);
        return nextIndex;
    }

    public int size() {
        return ids.size();
    }

    @Override
    public Iterator<Pair<Id, Integer>> iterator() {
        return ids.iterator();
    }

    @Override
    public void forEach(Consumer<? super Pair<Id, Integer>> action) {
        ids.forEach(action);
    }

    @Override
    public Spliterator<Pair<Id, Integer>> spliterator() {
        return ids.spliterator();
    }


    @Override
    public void writeOn(BufferWrangler buffer) {
        buffer.put(uint(ids.size()));
        for (Pair<Id, Integer> each : ids) {
            buffer.putLong(each.left.toLong());
            buffer.put(uvarint(each.right));
        }
    }

    public void readFrom(BufferWrangler buffer) {
        int expectedObjects = buffer.getUnsignedInt().intValue();
        this.initialize(expectedObjects);
        for (int i = 0; i < expectedObjects; i++) {
            Long value = buffer.getLong();
            Integer classIndex = buffer.getUnsignedVarInt().intValue();
            Id id = Id.getProvider().fromLong(value);
            this.store(id, classIndex);
        }
    }

    public List<Integer> idsToIndexes(List<Id> ids) {
        List<Integer> targets = new ArrayList<>(ids.size());
        for (Id each: ids) {
            targets.add(indexFor(each));
        }
        return targets;
    }
}
