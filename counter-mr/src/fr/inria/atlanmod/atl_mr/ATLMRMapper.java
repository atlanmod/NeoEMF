package fr.inria.atlanmod.atl_mr;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import fr.inria.atlanmod.kyanos.core.impl.KyanosHbaseResourceImpl;

/* *
 * @author Amine BENELALLAM
 *
 * In this version we are only distributing tasks
 * The number of task is not that big, thus Using IntWritable as a key
 *
 */

public class ATLMRMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private KyanosHbaseResourceImpl resource = null;

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		Logger.getGlobal().log(Level.INFO, "Setting up mapper - START");
		super.setup(context);
		URI inputUri = URI.createURI(context.getConfiguration().get(ATLMRMaster.INPUT_MODEL));
		resource = new KyanosHbaseResourceImpl(inputUri);
		resource.load(Collections.emptyMap());
		Logger.getGlobal().log(Level.INFO, "Setting up mapper - END");
	};

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		Logger.getGlobal().log(Level.FINEST, "Mapping - START - " + value.toString());

		String uriFragment = value.toString();

		EObject eObject = resource.getEObject(uriFragment);

		int children = 0;

		for (Iterator<EObject> it = eObject.eAllContents(); it.hasNext(); it.next(), children++) {
		}

		context.write(new Text(eObject.eClass().getName()), new IntWritable(children));

		Logger.getGlobal().log(Level.FINEST, "Mapping - END");
	}

	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		Logger.getGlobal().log(Level.INFO, "Cleaning up mapper - START");
		KyanosHbaseResourceImpl.shutdownWithoutUnload(resource);
		super.cleanup(context);
		Logger.getGlobal().log(Level.INFO, "Cleaning up mapper - END");
	}

}// end