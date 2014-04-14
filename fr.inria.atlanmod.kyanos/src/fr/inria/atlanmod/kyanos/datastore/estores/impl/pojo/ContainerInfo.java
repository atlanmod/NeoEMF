package fr.inria.atlanmod.kyanos.datastore.estores.impl.pojo;

import java.io.Serializable;

public class ContainerInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String containerId;
	
	public String containingFeatureName;
	
	public ContainerInfo(String containerId, String containingFeatureName) {
		this.containerId = containerId;
		this.containingFeatureName = containingFeatureName;
	}
	
}
