package fr.inria.atlanmod.neo4emf.util.partition.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.neo4emf.util.partition.IPartition;

public class Partition implements IPartition {

	public Partition(EReference ref) {
		orderedReferences= new ArrayList<EReference>();
		orderedReferences.add(ref);
		startCls= ref.getEContainingClass();
	}
	@SuppressWarnings("unused")
	private EClass startCls;
	private List<EReference> orderedReferences;
	
}
