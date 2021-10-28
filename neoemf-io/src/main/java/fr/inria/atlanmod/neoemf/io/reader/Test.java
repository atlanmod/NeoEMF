package fr.inria.atlanmod.neoemf.io.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Test {
	public static void main(String[] args) throws IOException {
		Path fileName = Path.of("demo.txt");
		String store_xml = Files.readString(fileName);
		System.out.println(store_xml);
	}
}
