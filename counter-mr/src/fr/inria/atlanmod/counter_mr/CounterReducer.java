package fr.inria.atlanmod.atl_mr;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ATLMRReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		Logger.getGlobal().log(Level.INFO, "Setting up reducer - START");

		super.setup(context);

		Logger.getGlobal().log(Level.INFO, "Setting up reducer - END");
	}

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

		Logger.getGlobal().log(Level.INFO, "Reduce - START");

		int sum = 0;
		for (IntWritable value : values) {
			sum += value.get();
		}
		context.write(key, new IntWritable(sum));

		Logger.getGlobal().log(Level.INFO, "Reduce - START");

	}

	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		Logger.getGlobal().log(Level.INFO, "Cleaning up reducer - START");

		super.cleanup(context);

		Logger.getGlobal().log(Level.INFO, "Cleaning up reducer - END");
	}
}