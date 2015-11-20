package fr.inria.atlanmod.kyanos.util;

import java.io.IOException;
import java.text.MessageFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.eclipse.emf.common.util.URI;

import fr.inria.atlanmod.kyanos.logger.Logger;


public class KyanosUtil {

	public static class ResourceUtil {
		
		Configuration conf = HBaseConfiguration.create();
		
		public static  ResourceUtil INSTANCE = getInstance();

		protected ResourceUtil () {
			
		}
		
		private static ResourceUtil getInstance() {
			if (INSTANCE == null) {
				INSTANCE = new ResourceUtil(); 
			}
			return INSTANCE;
		}
		
		/**
		 * Deletes a table if exist 
		 * @param modelURI
		 * @return {@value true} if deleted, {@value false} otherwise
		 * @throws IOException
		 */
		public boolean deleteResourceIfExists (URI modelURI) throws IOException {
			{ 
				// setting up the configuration according to the URI
				conf.set("hbase.zookeeper.quorum", modelURI.host());
				conf.set("hbase.zookeeper.property.clientPort", modelURI.port() != null ? modelURI.port() : "2181");
				
				// checking HBase availability
				
				try {
					HBaseAdmin.checkHBaseAvailable(conf);
				} catch (MasterNotRunningException e) {
					Logger.log(Logger.SEVERITY_ERROR, 
							MessageFormat.format("The Master node is not running, details below \n {0}", e.getLocalizedMessage()));
					
				} catch (ZooKeeperConnectionException e){
					Logger.log(Logger.SEVERITY_ERROR, 
							MessageFormat.format("zooKeeper connexion failed using the following configuration:\n hbase.zookeeper.quorum:{0}\nhbase.zookeeper.property.clientPort:{1}", e.getLocalizedMessage()));

				} catch (IOException e) {
					// Log this 
					e.printStackTrace();
				} catch (Exception e) {
					// Log this 
					e.printStackTrace();
				}
				
			}
			
			//Connection resourceConnection = ConnectionFactory.createConnection(conf);
			HBaseAdmin admin = new HBaseAdmin(conf);
			String cloneURI = formatURI (modelURI);
			TableName tableName = TableName.valueOf(cloneURI);
			Logger.log(Logger.SEVERITY_INFO, "Delete table if exists");
			try {	
			if (admin.tableExists(tableName)) {
				
				admin.disableTable(tableName);
				admin.deleteTable(tableName);
				
				Logger.log(Logger.SEVERITY_INFO, "Table has been deleted");
				return true;
				}	
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			return false;
			
		}

		
	}
	
	public static String formatURI(URI modelURI) {
		StringBuilder strBld = new StringBuilder();
		for (int i=0; i < modelURI.segmentCount(); i++) {
			strBld.append(modelURI.segment(i).replaceAll("-","_"));
			 if (i != modelURI.segmentCount() -1 )
				 strBld.append("_");
		}
		
		return strBld.toString();
	}
	
}
