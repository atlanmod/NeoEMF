package fr.inria.atlanmod.kyanos.util;

import java.io.IOException;
import java.text.MessageFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.eclipse.emf.common.util.URI;

import fr.inria.atlanmod.kyanos.logger.Logger;


public class KyanosUtil {

	public static class ResourceUtil {
		
		Configuration conf = HBaseConfiguration.create();
		
		public static  ResourceUtil INSTANCE = getInstance();

		// to disable the 
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
				Logger.log(Logger.SEVERITY_INFO, MessageFormat.format("", modelURI.toString()));
				conf.set("hbase.zookeeper.quorum", modelURI.host());
				conf.set("hbase.zookeeper.property.clientPort", modelURI.port() != null ? modelURI.port() : "2181");
				
				// checking HBase availability
				
//				try {
//					HBaseAdmin.checkHBaseAvailable(conf);
//				} catch (MasterNotRunningException e) {
//					Logger.log(Logger.SEVERITY_ERROR, 
//					MessageFormat.format("The Master node is not running, details below \n {0}", e.getLocalizedMessage()));
//					
//				} catch (ZooKeeperConnectionException e){
//					Logger.log(Logger.SEVERITY_ERROR, 
//					MessageFormat.format("zooKeeper conenxion failed using the follwing configuration:\n hbase.zookeeper.quorum:{0}\nhbase.zookeeper.property.clientPort:{1}", e.getLocalizedMessage()));
//
//				} catch (IOException e) {
//					// Log this 
//					e.printStackTrace();
//				} catch (Exception e) {
//					// Log this 
//					e.printStackTrace();
//				}
//				
			}
			
			Connection resourceConnection = ConnectionFactory.createConnection(conf);
			Admin admin = resourceConnection.getAdmin();
			
			TableName tableName = TableName.valueOf(modelURI.devicePath().replaceAll("/", "_"));

			if (admin.tableExists(tableName)) {
				
				admin.disableTable(tableName);
				admin.deleteTable(tableName);
				
				// log table deleted 
				return true;
			}		
			return false;
			
		}
		
	}
}
