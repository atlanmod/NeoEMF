package fr.inria.atlanmod.neo4emf.util.partition.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;


import fr.inria.atlanmod.neo4emf.util.partition.IPartition;
import fr.inria.atlanmod.neo4emf.util.partition.IPartitioning;

public class Partitioning implements IPartitioning {
	
	private Resource resource;
	private List<IPartition> partitions;
	public Partitioning(){}
	public Partitioning (Resource r) {
		resource = r;
		partitions=new ArrayList<IPartition>();
		for (EReference ref : getAllContainments()){
			partitions.add(new Partition(ref));
		}
	}
@Override 
	public List<EPackage> getAllPacakges(){
	if (resource == null || resource.getContents()==null)
		return null;
	List<EPackage> listPck = new ArrayList<EPackage>();
	for (EObject obj : resource.getContents())
		if (obj instanceof EPackage){
			listPck.add((EPackage)obj);
			getSubPackages(obj,listPck);
		}
	return listPck;
			
	}
public Resource getResource() {
	return resource;
}

public List<IPartition> getPartitions() {
	return partitions;
}

private void getSubPackages(EObject obj, List<EPackage> listPck) {
	if (obj.eContents() == null) return;
	
	for (EObject object : obj.eContents())
		if (object instanceof EPackage)
		{
			listPck.add((EPackage)object);
			getSubPackages(object, listPck);
		}
	
}
@Override 
public List<EClass> getAllClasses(){
	List<EPackage> listPck = getAllPacakges();
	if (listPck == null) return null;
	List<EClass> listCls = new ArrayList<EClass>();
	for (EPackage pck : listPck){
		if (pck.eContents() ==  null) continue;
		for (EObject obj : pck.eContents()){
			if (obj instanceof EClass && !((EClass) obj).isAbstract())
				listCls.add((EClass)obj);
		}
	}
	return listCls;
}
@Override 
public List<EReference> getAllContainments(){
	List<EClass> listCls = getAllClasses();
	if (listCls == null) return null;
	List<EReference> listCont = new ArrayList<EReference>();
	for (EClass cls : listCls){
		if (cls.getEAllContainments()== null ) continue;
			listCont.addAll(cls.getEAllContainments());
	}
	return listCont;
}

@Override 
public EClass getClassByName(String name){
	if (getAllClasses()==null) return null;
	for (EClass cls : getAllClasses()){
		if (name.equals(cls.getName()))
			return cls;
	}
	return null;
}

}
