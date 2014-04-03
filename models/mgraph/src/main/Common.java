package main;

public class Common {

	/**
	 * Force the garbage collector to release all the SoftReferences which are pointing
	 * to a non strongly-referenced object.
	 * 
	 * This is a property of the garbage collector : it ensures all the cleanable references
	 * are cleaned before any OutOfMemoryError, which is thrown in this method.
	 * 
	 */
	public static void clearAllSoftReferences() {
		try {
		    Object[] ignored = new Object[(int) Runtime.getRuntime().maxMemory()];
		} catch (Throwable e) {
		    // Ignore OME
		}
	}

}
