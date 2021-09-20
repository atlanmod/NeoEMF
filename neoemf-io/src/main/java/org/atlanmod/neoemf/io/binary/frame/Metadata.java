package org.atlanmod.neoemf.io.binary.frame;

import org.atlanmod.neoemf.io.binary.adapter.PackageAdapter;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;
import org.eclipse.emf.ecore.EPackage;

public class Metadata {
    protected final BinaryAdapterProvider provider;

    public Metadata(BinaryAdapterProvider provider) {
        this.provider = provider;
    }

    protected PackageAdapter createAdapter(EPackage ePackage) {
        return provider.createEPackageAdapter(ePackage);
    }
}
