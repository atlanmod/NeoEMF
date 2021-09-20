package org.atlanmod.neoemf.io.binary.frame;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;
import org.atlanmod.commons.collect.Flags;
import org.atlanmod.commons.log.Log;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.SerializableToBytes;
import org.atlanmod.neoemf.io.binary.adapter.ClassAdapter;
import org.atlanmod.neoemf.io.binary.adapter.ReferenceAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.atlanmod.commons.Preconditions.requireThat;

public class References implements SerializableToBytes {
    private final Map<Id, PendingReferenceHolder> pendingReferences = new HashMap<>();
    private final Index index;


    public References(Index index) {
        this.index = index;
    }

    @Override
    public void readFrom(BufferWrangler buffer) {

    }

    public int estimatedSize() {
        return pendingReferences.size() * 64; // TODO: Remove magic number
    }

    @Override
    public void writeOn(BufferWrangler buffer) {
        Log.info("Writing references ");

        final References.PendingReferenceHolder[] holders = new References.PendingReferenceHolder[this.pendingReferences.size()];
        for (Map.Entry<Id, References.PendingReferenceHolder> each : this.pendingReferences.entrySet()) {
            int objectIndex = index.indexFor(each.getKey());
            holders[objectIndex] = each.getValue();
        }

        this.pendingReferences.clear();

        for (int i = 0; i < holders.length; i++) {
            References.PendingReferenceHolder each = holders[i];
            writePendingReference(buffer, each);
        }
        Log.info("Wrote {0} references. References size: {1}", holders.length, buffer.position());
    }

    private void writePendingReference(BufferWrangler buffer, References.PendingReferenceHolder each) {
        ClassAdapter classAdapter = each.classAdapter;
        // Write
        buffer.put(each.flags.toBytes());
        for (int j = 0; j < each.values.length; j++){
            if (each.flags.get(j)) {
                List<Integer> targets = index.idsToIndexes(each.values[j]);
                ReferenceAdapter adapter = classAdapter.referenceAdapterFor(j).withValues(targets);
                adapter.writeValuesOn(buffer);
            }
        }
    }

    public void onStartElement(ClassAdapter classAdapter, Id id) {
        PendingReferenceHolder holder = new PendingReferenceHolder(classAdapter);
        pendingReferences.put(id, holder);
    }

    public void onReference(ProxyReference reference, List<Id> values) {
        /*
        The reference may be a reference from the current element to others (the values),
        or a (containment) reference from the previous element to the current one.
        */
        requireThat(pendingReferences.containsKey(reference.getOwner())).isTrue();

        References.PendingReferenceHolder pending = pendingReferences.get(reference.getOwner());
        pending.onReference(reference.getId(), values);
    }


    /**
     * A Class for storing pending references during model traversal.
     */
    public static class PendingReferenceHolder {
        List<Id>[] values;
        private final ClassAdapter classAdapter;
        private final Flags flags;

        public PendingReferenceHolder(ClassAdapter classAdapter) {
            this.classAdapter = classAdapter;
            int referenceCount = classAdapter.getReferenceCount();
            this.flags = new Flags(referenceCount);
            values = new List[referenceCount];
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("PendingReference{");
            sb.append("values=");
            for (int i = 0; i < values.length; i++) {
                sb.append(i)
                        .append(" = ")
                        .append(values[i])
                        .append("; ");
            }
            sb.append('}');
            return sb.toString();
        }

        public void onReference(int featureID, List<Id> values) {
            // Note that the same feature may receive more than on event with values
            // In this case, values must be merged
            int index = classAdapter.indexFor(featureID);

            if (!flags.get(index)) {
                flags.set(index);
                this.values[index] = new LinkedList<>();
            }
            this.values[index].addAll(values);
        }
    }
}
