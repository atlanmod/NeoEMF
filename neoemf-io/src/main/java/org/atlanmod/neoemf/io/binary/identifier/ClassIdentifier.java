package org.atlanmod.neoemf.io.binary.identifier;

import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.eclipse.emf.ecore.EClass;

import javax.annotation.Nonnull;

public class ClassIdentifier extends Identifier {
    private final EClass eClass;

    @Nonnull
    public ClassIdentifier(int id, BinaryAdapterProvider provider, EClass eClass) {
        super(id, provider);
        this.eClass = eClass;
    }

    public EClass eClass() {
        return eClass;
    }
}
