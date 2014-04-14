package fr.inria.atlanmod.kyanos.datastore.estores.impl.pojo;

import java.io.Serializable;

public class EClassInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String nsURI;
	
	public String className;
	
	public EClassInfo(String nsURI, String className) {
		this.nsURI = nsURI;
		this.className = className;
	}
}

