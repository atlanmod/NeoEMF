package fr.inria.atlanmod.neo4emf.util;

import fr.inria.atlanmod.neo4emf.impl.Partition;

/**
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 * Descritpion ! To come
 * @author Amine BENELALLAM
 * */

public interface IUnloader {
	public static final String TIMER_TO_AWAKE = "timer_to_awake";
	public final int DEFAULT_TIMER = 10000;
	public static final String UNLOAD_STRATEGY = "unload_strategy";
	public static final int LIFO = 0;
	public static final int  FIFO = 1;
	public static final int LEAST_RECENTLY_USED = 2;
	public static final int LEAST_FREQUENTLY_USED = 3;
	String [] unloadOptions = {TIMER_TO_AWAKE,UNLOAD_STRATEGY};
	Object [] unloadDeafultValues = {DEFAULT_TIMER,LIFO};
	public void unloadPartition(Partition partition);
	
}
