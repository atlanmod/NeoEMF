package fr.inria.atlanmod.neo4emf.partition.impl;

import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neo4emf.partition.IPartitioning;
import fr.inria.atlanmod.neo4emf.partition.IPartitioningFactory;

public class PartitioningFactory implements IPartitioningFactory {

	@Override
	public IPartitioning createPartioning(Resource r) {
		if (r != null  )
			return new Partitioning(r);
		return null;
	}

	public static IPartitioningFactory init() {
		if (eINSTANCE == null)
			return  new PartitioningFactory();
		return eINSTANCE;
	}

}
