package fr.inria.atlanmod.neoemf.data.map.core.store;

import fr.inria.atlanmod.neoemf.data.map.core.MapBackend;
import fr.inria.atlanmod.neoemf.data.map.core.MapBackendStub;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;


/**
 * Created by sunye on 23/01/2017.
 */
public class MapStoreWithIndicesTest {

    private MapStoreWithIndices<MapBackend> store;


    @Test
    public void test() {

        store = new MapStoreWithIndices<MapBackend>(null, new MapBackendStub());
        InternalEObject iObject = mock(InternalEObject.class);
        EStructuralFeature feature = mock(EStructuralFeature.class);
        when(feature.isMany()).thenReturn(true);

        //assertThat(store.size(iObject, feature)).isEqualTo(0);
    }
}
