package fr.inria.atlanmod.neo4emf.util.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.impl.AbstractPartition;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;
import fr.inria.atlanmod.neo4emf.impl.Partition;
import fr.inria.atlanmod.neo4emf.util.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.util.IUnloader;

public class Unloader implements IUnloader {

	private IPersistenceManager manager;
	private Map<String, Object> options;
	public Unloader(IPersistenceManager manager, Map<String, Object> options) {
		super();
		this.manager = manager;
		//this.start();
		options = mergeWithDefaultOptions(options);
	}
	private void unloadElement(EObject object) {
		INeo4emfObject neoObj = (INeo4emfObject)object;
		if (neoObj.eIsProxy())
			deleteObject(neoObj);	
		else
			delegateAndDelete(neoObj);
	}

	private void deleteObject(EObject neoObj) {
		List<EReference> refList=neoObj.eClass().getEAllReferences();
		
		((Neo4emfObject)neoObj).clearId();
		for (EReference str : refList) {
			
			if (neoObj.eGet(str)!=null)
				neoObj.eSet(str, null);	
		}
		neoObj = null;
	}

	private void delegateAndDelete(EObject eObject){
		manager.delegate(eObject);
		deleteObject(eObject);

	}

	@Override
	public void unloadPartition(AbstractPartition partition) { 
		for (INeo4emfObject neoObj : partition.flattened()){
			unloadElement(neoObj);			
		}
		//unloadElement(partition.geteObjet());
	}


	//@Override 
	public void run(){
		while (! manager.doUnload()){
			try {
				Thread.sleep(DEFAULT_TIMER);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		manager.unload((int)options.get(UNLOAD_STRATEGY));

	}


	@SuppressWarnings("deprecation")
	public void shutUnloader(){
		//this.stop();
	}
	/**
	 * merges the options in the save method's parameters with 
	 * the default options 
	 * @param options
	 * @return {@link Map} merged options
	 */
	private Map<String, Object> mergeWithDefaultOptions(Map<String, Object> option) {
		initOptions();
		if (option==null) return options;
		for (int i=0; i< unloadOptions.length; i++)
			if (option.containsKey(unloadOptions[i])){
				options.remove(unloadOptions[i]);
				options.put(unloadOptions[i], option.get(unloadOptions[i]));	}
		return options;
	}

	private void initOptions() {
		options =  new HashMap<String, Object>();
		for (int i=0;i<unloadOptions.length; i++ )
			options.put(unloadOptions[i], unloadDeafultValues[i]);

	}
}





