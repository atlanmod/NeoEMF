/*******************************************************************************
 * Copyright (c) 2014 Abel G�mez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel G�mez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.neoemf.logger;

import java.util.logging.Level;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import fr.inria.atlanmod.neoemf.NeoEMFPlugin;


public class Logger {

	public static final int SEVERITY_CANCEL = IStatus.CANCEL;
	public static final int SEVERITY_ERROR = IStatus.ERROR;
	public static final int SEVERITY_INFO= IStatus.INFO;
	public static final int SEVERITY_OK = IStatus.OK;
	public static final int SEVERITY_WARNING = IStatus.WARNING;
	
	private interface ILogger {
		
	    public void log(int severity, Throwable e);

	    public void log(int severity, String msg, Throwable e);
	    
	    public void log(int severity, String msg);
	}
	
	private static class OsgiLogger implements ILogger {
		
	    private ILog log = NeoEMFPlugin.getDefault().getLog();    
	    
	    @Override
		public void log(int severity, Throwable e) {
	        log.log(new Status(severity, NeoEMFPlugin.PLUGIN_ID,
	        		e.getMessage() != null ? e.getMessage() : e.toString(), e));
	    }

	    @Override
		public void log(int severity, String msg, Throwable e) {
	    	log.log(new Status(severity, NeoEMFPlugin.PLUGIN_ID, msg, e));
	    }
	    
	    @Override
		public void log(int severity, String msg) {
	        log.log(new Status(severity, NeoEMFPlugin.PLUGIN_ID, msg, null));
	    }
	}
	
	private static class JavaLogger implements ILogger {
		
		private java.util.logging.Logger log = java.util.logging.Logger.getLogger(Logger.class.getName());    
		
		@Override
		public void log(int severity, Throwable e) {
			log.log(getLevel(severity), e.getMessage() != null ? e.getMessage() : e.toString(), e);
		}
		
		@Override
		public void log(int severity, String msg, Throwable e) {
			log.log(getLevel(severity), msg, e);
		}
		
		@Override
		public void log(int severity, String msg) {
			log.log(getLevel(severity), msg);
		}
		
		private Level getLevel(int severity) {
			switch (severity) {
			case SEVERITY_CANCEL:
				return Level.INFO;
			case SEVERITY_ERROR:
				return Level.SEVERE;				
			case SEVERITY_INFO:
				return Level.INFO;
			case SEVERITY_OK:
				return Level.FINE;
			case SEVERITY_WARNING:
				return Level.WARNING;
			default:
				return Level.FINEST;
			}
		}
	}
	
    private static ILogger log;
    
    static {
    	if (NeoEMFPlugin.getDefault() != null) {
    		log = new OsgiLogger();
    	} else {
    		log = new JavaLogger();
    	}
    }
    
    public static void 	log(int severity, Throwable e) {
        log.log(severity, e);
    }

    public static void log(int severity, String msg, Throwable e) {
    	log.log(severity, msg, e);
    }
    
    public static void log(int severity, String msg) {
        log.log(severity, msg);
    }
    
}
