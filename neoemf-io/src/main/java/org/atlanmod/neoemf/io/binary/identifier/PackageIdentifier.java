package org.atlanmod.neoemf.io.binary.identifier;

import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.eclipse.emf.ecore.EPackage;

public class PackageIdentifier extends Identifier {
    private final EPackage ePackage;
    public PackageIdentifier(int index, BinaryAdapterProvider provider, EPackage ePackage) {
        super(index, provider);
        this.ePackage = ePackage;
    }

    public EPackage ePackage() {
        return ePackage;
    }
}
