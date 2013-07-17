package fr.inria.atlanmod.neo4emf.util.partition;

import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neo4emf.util.partition.impl.PartitioningFactory;

public interface IPartitioningFactory {

	IPartitioningFactory eINSTANCE = PartitioningFactory.init();
	public IPartitioning createPartioning(Resource r);
}
