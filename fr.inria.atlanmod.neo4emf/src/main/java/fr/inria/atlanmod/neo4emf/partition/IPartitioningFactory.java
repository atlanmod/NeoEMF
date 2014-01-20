package fr.inria.atlanmod.neo4emf.partition;

import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neo4emf.partition.impl.PartitioningFactory;

public interface IPartitioningFactory {

	IPartitioningFactory eINSTANCE = PartitioningFactory.init();
	public IPartitioning createPartioning(Resource r);
}
