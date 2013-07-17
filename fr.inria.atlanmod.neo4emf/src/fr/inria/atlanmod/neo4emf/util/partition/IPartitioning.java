package fr.inria.atlanmod.neo4emf.util.partition;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

public interface IPartitioning {


	List<EPackage> getAllPacakges();

	List<EClass> getAllClasses();

	List<EReference> getAllContainments();

	EClass getClassByName(String name);

}
