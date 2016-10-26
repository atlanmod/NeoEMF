/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.hbase.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.protobuf.ServiceException;

import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;
import org.eclipse.emf.common.util.URI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Iterator;

public class NeoHBaseUtil {

    public static final int UUID_LENGTH = 23;
    
	public static class ResourceUtil {

		private static ResourceUtil INSTANCE;

		public static ResourceUtil getInstance() {
			if (INSTANCE == null) {
				INSTANCE = new ResourceUtil();
			}
			return INSTANCE;
		}

		private final Configuration conf = HBaseConfiguration.create();

		private ResourceUtil() {
		}
		
		/**
		 * Deletes a table if exist
		 * @return {@code true} if deleted, {@code false} otherwise
		 * @throws IOException
		 */
		public boolean deleteResourceIfExists (URI modelURI) throws IOException {

			// Setting up the configuration according to the URI
			conf.set("hbase.zookeeper.quorum", modelURI.host());
			conf.set("hbase.zookeeper.property.clientPort", modelURI.port() != null ? modelURI.port() : "2181");

			// Checking HBase availability
			try {
				HBaseAdmin.checkHBaseAvailable(conf);
			} catch (MasterNotRunningException e) {
				NeoLogger.error("The Master node is not running, details below \n {0}", e.getLocalizedMessage());
			} catch (ZooKeeperConnectionException e){
				NeoLogger.error("zooKeeper connexion failed using the following configuration:\n hbase.zookeeper.quorum:{0}\nhbase.zookeeper.property.clientPort:{1}", e.getLocalizedMessage(), conf.get("hbase.zookeeper.property.clientPort"));
			} catch (IOException | ServiceException e) {
				NeoLogger.error(e);
			}

			//Connection resourceConnection = ConnectionFactory.createConnection(conf);
			HBaseAdmin admin = new HBaseAdmin(conf);
			String cloneURI = formatURI (modelURI);
			TableName tableName = TableName.valueOf(cloneURI);
			NeoLogger.error("Delete table if exists");
			try {	
			    if (admin.tableExists(tableName)) {
                    if (! admin.isTableDisabled(tableName)) {
                        admin.disableTable(tableName);
                    }
                    admin.deleteTable(tableName);
                    NeoLogger.info("Table has been deleted");
                    return true;
				}	
			} catch (IOException e) {
				NeoLogger.error(e);
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
	
	public static class EncoderUtil {
		
		public static final char VALUE_SEPERATOR_DEFAULT = ',';
		
		public static String [] toStringsReferences(byte[] value) {
			if (value != null) {
				assert (value.length) % (UUID_LENGTH + 1) == UUID_LENGTH;
				int length = (value.length + 1)/(UUID_LENGTH + 1);
				
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
				NeoLogger.error("Unable to convert ''{0}'' to byte[]", Arrays.toString(strings));
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
				NeoLogger.error("Unable to convert ''{0}'' to String[]", Arrays.toString(bytes));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(objectInputStream);
			}
			return result;
		}
	}
}