/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/

package fr.inria.atlanmod.neoemf.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class NeoLogger {

	public static final Level SEVERITY_ERROR = Level.ERROR;
	public static final Level SEVERITY_INFO= Level.INFO;
	public static final Level SEVERITY_WARNING = Level.WARN;
	public static final Level SEVERITY_DEBUG = Level.DEBUG;
	
    private static Logger log = LogManager.getRootLogger();
    
    public static void log(Level severity, Throwable e) {
        log.log(severity,
        		e.getMessage() != null ? e.getMessage() : e.toString(), e);
    }

    public static void log(Level severity, String msg, Throwable e) {
    	log.log(severity, msg, e);
    }
    
    public static void log(Level severity, String msg) {
        log.log(severity, msg);
    }
    
}
