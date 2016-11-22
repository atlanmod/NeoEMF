package fr.inria.atlanmod.neoemf.hbase.datastore.store.cache;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.datastore.store.cache.FeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;

import static com.google.common.base.Preconditions.checkNotNull;

public class HBaseFeatureKey extends FeatureKey {

    private static final long serialVersionUID = 1L;

    public final EStructuralFeature feature;

    public HBaseFeatureKey(Id id, EStructuralFeature feature) {
        super(id, feature.getName());
        this.feature = checkNotNull(feature);
    }

    public EStructuralFeature feature() {
        return feature;
    }
}
