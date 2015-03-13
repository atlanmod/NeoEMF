package fr.inria.atlanmod.neo4emf.neo4jresolver.logger;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import fr.inria.atlanmod.neo4emf.neo4jresolver.Neo4jResolverPlugin;


/**
 * Logger for the plugin
 * @author abelgomez
 *
 */
public class Logger {
	
	public static final int SEVERITY_CANCEL = IStatus.CANCEL;
	public static final int SEVERITY_ERROR = IStatus.ERROR;
	public static final int SEVERITY_INFO= IStatus.INFO;
	public static final int SEVERITY_OK = IStatus.OK;
	public static final int SEVERITY_WARNING = IStatus.WARNING;
	
    private static ILog log;
    
    static {
        log = Neo4jResolverPlugin.getDefault().getLog();    
    }
    
    public static void log(int severity, Throwable e) {
        log.log(new Status(severity, Neo4jResolverPlugin.PLUGIN_ID,
        		e.getMessage() != null ? e.getMessage() : e.toString(), e));
    }

    public static void log(int severity, String msg, Throwable e) {
    	log.log(new Status(severity, Neo4jResolverPlugin.PLUGIN_ID, msg, e));
    }
    
    public static void log(int severity, String msg) {
        log.log(new Status(severity, Neo4jResolverPlugin.PLUGIN_ID, msg, null));
    }

}
