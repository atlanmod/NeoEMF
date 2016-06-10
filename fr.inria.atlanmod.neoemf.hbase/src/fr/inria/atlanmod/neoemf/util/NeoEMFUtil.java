package fr.inria.atlanmod.neoemf.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;
import org.eclipse.emf.common.util.URI;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import fr.inria.atlanmod.neoemf.core.NeoEMFEFactory;
import fr.inria.atlanmod.neoemf.logger.Logger;


public class NeoEMFUtil {

	public static class ResourceUtil {
		
		Configuration conf = HBaseConfiguration.create();
		
		public static  ResourceUtil INSTANCE = getInstance();

		public ResourceUtil () {
			
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
							MessageFormat.format("zooKeeper connexion failed using the following configuration:\n hbase.zookeeper.quorum:{0}\nhbase.zookeeper.property.clientPort:{1}", e.getLocalizedMessage(), conf.get("hbase.zookeeper.property.clientPort")));

				} catch (IOException e) {
					// Log this 
					e.printStackTrace();
				} catch (Exception e) {
					// Log this 
					e.printStackTrace();
				}
				
			}
			
			//Connection resourceConnection = ConnectionFactory.createConnection(conf);
			@SuppressWarnings("resource")
			HBaseAdmin admin = new HBaseAdmin(conf);
			String cloneURI = formatURI (modelURI);
			TableName tableName = TableName.valueOf(cloneURI);
			Logger.log(Logger.SEVERITY_INFO, "Delete table if exists");
			try {	
			if (admin.tableExists(tableName)) {
				
				if (! admin.isTableDisabled(tableName)) {
					admin.disableTable(tableName);
				}
				
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
	
	/**
	 * 
	 * @author Amine  BENELALLAM
	 *
	 */
	
	public static class EncoderUtil {
		
		public static final char VALUE_SEPERATOR_DEFAULT = ',';
		
		public static String [] toStringsReferences(byte[] value) {
			int uidLength = NeoEMFEFactory.UUID_LENGTH;
			if (value != null) {
				assert (value.length) % (uidLength + 1) == uidLength;
				int length = (value.length + 1)/(uidLength + 1);
				
				Iterator<String> iterator =  Splitter.on(VALUE_SEPERATOR_DEFAULT).split(Bytes.toString(value)).iterator();
				//List<String>  strings = new LinkedList<String>();
				String[] strings = new String[length];
				int index = 0;
				while (iterator.hasNext()) {
					//strings.add(iterator.next());
					strings[index++] = iterator.next();
				}
				//return strings.toArray(new String[strings.size()]);
				return strings;
			}	
			return null;
		}


		public static byte[] toBytesReferences (String[] strings) {
			if (strings != null) {
				return Joiner.on(VALUE_SEPERATOR_DEFAULT).join(strings).getBytes();
			}
			return null;
		}
		public static byte[] toBytes(String[] strings) {
			try {
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
				objectOutputStream.writeObject(strings);
				objectOutputStream.flush();
				objectOutputStream.close();
				return byteArrayOutputStream.toByteArray();
			} catch (IOException e) {
				Logger.log(Logger.SEVERITY_ERROR, MessageFormat.format("Unable to convert ''{0}'' to byte[]", strings.toString()));
			}
			return null;
		}

		public static String[] toStrings(byte[] bytes) {
			
			if (bytes == null) {
				return null;
			}
			
			String[] result = null;
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInputStream = null;
			try {
				objectInputStream = new ObjectInputStream(byteArrayInputStream);
				result = (String[]) objectInputStream.readObject();

			} catch (IOException e) {
				Logger.log(Logger.SEVERITY_ERROR, MessageFormat.format("Unable to convert ''{0}'' to String[]", bytes.toString()));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(objectInputStream);
			}
			return result;

		}
	}
	
}
